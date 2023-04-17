/***************************************************************************
 * Copyright (C) 2023 OceanDSL (https://oceandsl.uni-kiel.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package org.oceandsl.tools.esm.stages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.w3c.dom.Node;

import teetime.framework.OutputPort;
import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.esm.util.Output;
import org.oceandsl.tools.fxca.model.CommonBlock;
import org.oceandsl.tools.fxca.model.EDirection;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranParameter;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.model.FortranVariable;
import org.oceandsl.tools.fxca.model.IDataflowSource;
import org.oceandsl.tools.fxca.stages.XPathParser;
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;
import org.oceandsl.tools.fxca.tools.Pair;
import org.oceandsl.tools.fxca.tools.Predicates;

public class DataFlowAnalysisStage2 extends AbstractTransformation<FortranProject, Output> {

    private static final String READ = "READ";
    private static final String WRITE = "WRITE";
    private static final String BOTH = "BOTH";

    private final OutputPort<CommonBlockEntry> commonBlockOutputPort = this.createOutputPort(CommonBlockEntry.class);
    private final OutputPort<IDataflowEntry> dataflowOutputPort = this.createOutputPort(IDataflowEntry.class);

    private final Output out = new Output();

    private final String defaultModuleName;

    public DataFlowAnalysisStage2(final String defaultModuleName) {
        this.defaultModuleName = defaultModuleName;
    }

    public OutputPort<CommonBlockEntry> getCommonBlockOutputPort() {
        return this.commonBlockOutputPort;
    }

    @Override
    protected void execute(final FortranProject project) throws Exception {
        project.getModules().values().stream().filter(module -> !module.getModuleName().equals(this.defaultModuleName))
                .forEach(module -> {
                    module.getCommonBlocks().values()
                            .forEach(commonBlock -> this.analyzeCommonBlock(module, commonBlock));
                    module.getOperations().values()
                            .forEach(operation -> this.analyzeOperation(project, module, operation));
                });

        this.outputPort.send(this.out);
    }

    private void analyzeCommonBlock(final FortranModule module, final CommonBlock commonBlock) {
        final CommonBlockEntry entry = new CommonBlockEntry(commonBlock.getName());
        entry.getModules().add(module);
        commonBlock.getVariables().values().forEach(variable -> entry.getVariables().add(variable.getName()));
        this.commonBlockOutputPort.send(entry);
    }

    private void analyzeOperation(final FortranProject project, final FortranModule module,
            final FortranOperation operation) {
        operation.getCommonBlocks().values().forEach(commonBlock -> this.analyzeCommonBlock(module, commonBlock));

        NodeProcessingUtils
                .findAllSiblings(operation.getNode(), o -> true,
                        Predicates.isEndSubroutineStatement.or(Predicates.isEndFunctionStatement))
                .forEach(node -> this.analyzeNode(node, project, module, operation));
    }

    private void analyzeNode(final Node node, final FortranProject project, final FortranModule module,
            final FortranOperation operation) {
        if (Predicates.isCallStatement.test(node)) {
            this.analyzeCallStatement(project, module, operation, node);
        } else if (Predicates.isAssignmentStatement.test(node)) {
            this.analyzeAssignmentStatement(project, module, operation, node);
        }
        // else if (Predicates.isIfThenStatement.test(node)) {
        // this.analyzeIfThenStatement(node, project, module, operation);
        // } else if (Predicates.isSelectCaseStatement.test(node)) {
        // this.analyzeSelectCaseStatement(node, project, module, operation);
        // } else if (Predicates.isDoStatement.test(node)) {
        // this.analyzeDoStatement(node, project, module, operation);
        // }
    }

    private void analyzeCallStatement(final FortranProject project, final FortranModule contextModule,
            final FortranOperation conextOperation, final Node callNode) {
        final String calleeName = NodeProcessingUtils.getCalleeNameFromCall(callNode);
        final Pair<FortranModule, FortranOperation> callee = this.findOperation(project.getModules().values(),
                calleeName);
        final Node argumentSpecification = NodeProcessingUtils.findChildFirst(callNode,
                Predicates.isArgumentSpecification);
        if (argumentSpecification != null) {
            final List<Node> arguments = NodeProcessingUtils.findAllSiblings(argumentSpecification.getFirstChild(),
                    Predicates.isArgument, o -> false);
            for (int i = 0; i < arguments.size(); i++) {
                this.analyzeArgument(project, contextModule, conextOperation, contextModule, conextOperation,
                        callee.first, callee.second, arguments.get(i).getFirstChild(), i);
            }
        }
    }

    /**
     * Process one argument of a callee.
     *
     * @param project
     *            project to be analyzed
     * @param contextModule
     *            module context
     * @param contextOperation
     *            operation context
     * @param callee
     *            called operation
     * @param argumentNode
     *            node of the argument of the called operation to be processed
     * @param argumentIndex
     *            index of the argument
     */
    private void analyzeArgument(final FortranProject project, final FortranModule contextModule,
            final FortranOperation contextOperation, final FortranModule callerModule,
            final FortranOperation callerOperation, final FortranModule calleeModule,
            final FortranOperation calleeOperation, final Node argumentNode, final int argumentIndex) {
        final Optional<FortranParameter> calleeParameter = this.findCalleeParameter(calleeOperation, argumentIndex);
        if (calleeParameter.isEmpty()) {
            System.err.printf("++ error no parameter exists for argument index %d %s:%s -> %s:%s\n", argumentIndex,
                    contextModule.getFileName(), contextOperation.getName(), calleeModule.getFileName(),
                    calleeOperation.getName());
        }
        if (Predicates.isNamedExpressionAccess.test(argumentNode)) {
            this.analyzeNamedExpressionAccess(project, contextModule, contextOperation, contextModule, contextOperation,
                    calleeModule, calleeOperation, argumentNode, calleeParameter);
        } else if (Predicates.isNamedExpression.test(argumentNode)) {
            this.analyzeNamedExpression(project, contextModule, contextOperation, callerModule, callerOperation,
                    calleeModule, calleeOperation, argumentNode, calleeParameter);
        } else if (Predicates.isOperandExpression.test(argumentNode)) {
            this.analyzeExpression(project, contextModule, contextOperation, contextModule, contextOperation,
                    calleeModule, calleeOperation, argumentNode);
        } else if (Predicates.isParensE.test(argumentNode)) {
            this.analyzeExpression(project, contextModule, contextOperation, contextModule, contextOperation,
                    calleeModule, calleeOperation, argumentNode);
        } else if (Predicates.isLiteralE.or(Predicates.isStringE).test(argumentNode)) {
            // System.err.println("Literal can be ignored " +
            // argument.getFirstChild().getTextContent());
        } else {
            this.logger.error("Internal error: unknown argument content {} for argument {} in {}::{} -> {}::{}",
                    argumentNode, argumentIndex, contextModule.getFileName(), contextOperation.getName(),
                    calleeModule.getFileName(), calleeOperation.getName());
        }
    }

    private Optional<FortranParameter> findCalleeParameter(final FortranOperation calleeOperation,
            final int argumentIndex) {
        final int corectedIndex = this.computeArgumentIndex(calleeOperation, argumentIndex);
        return calleeOperation.getParameters().values().stream()
                .filter(parameter -> parameter.getPosition() == corectedIndex).findFirst();
    }

    private void analyzeNamedExpressionAccess(final FortranProject project, final FortranModule contextModule,
            final FortranOperation contextOperation, final FortranModule contextModule2,
            final FortranOperation contextOperation2, final FortranModule calleeModule,
            final FortranOperation calleeOperation, final Node argumentNode,
            final Optional<FortranParameter> calleeParameter) {
        final String argumentName = NodeProcessingUtils.getName(argumentNode);
        if (contextOperation.getParameters().containsKey(argumentName)
                || contextOperation.getVariables().containsKey(argumentName)) {
            this.analyzeNamedExpression(project, contextModule, contextOperation, contextModule, contextOperation,
                    calleeModule, calleeOperation, argumentNode, calleeParameter);
        } else {
            this.analyzeFunctionCall(project, contextModule, contextOperation, contextModule, contextOperation,
                    argumentNode);
        }
    }

    /**
     * Process a named expression element in an argument, where the callee is the function which has
     * arguments which is processed and caller is the function that receives the result of the
     * callee. And context is the function or subroutine which provides parameter and variable
     * names.
     *
     * @param project
     *            the project
     * @param contextModule
     *            scope
     * @param contextOperation
     *            scope
     * @param callerModule
     * @param callerOperation
     * @param calleeModule
     *            callee module
     * @param calleeOperation
     *            callee operation
     * @param argumentNode
     * @param parameter
     */
    private void analyzeNamedExpression(final FortranProject project, final FortranModule contextModule,
            final FortranOperation contextOperation, final FortranModule callerModule,
            final FortranOperation callerOperation, final FortranModule calleeModule,
            final FortranOperation calleeOperation, final Node argumentNode,
            final Optional<FortranParameter> parameter) {
        final String argumentName = NodeProcessingUtils.getName(argumentNode);

        final FortranParameter argument = parameter.get();

        if (contextOperation.getParameters().containsKey(argumentName)) {
            // named argument is a parameter
            this.createParameterToArgumentLink(contextModule, contextOperation, calleeModule, calleeOperation,
                    argument);
        } else if (contextOperation.getVariables().containsKey(argumentName)) {
            // named argument is a variable
            this.analyzeVariableDataflow(project, contextModule, contextOperation, argumentName, calleeModule,
                    calleeOperation, argument);
        } else {
            this.analyzeFunctionCall(project, contextModule, contextOperation, calleeModule, calleeOperation,
                    argumentNode);
        }
    }

    private int computeArgumentIndex(final FortranOperation operation, final int argumentIndex) {
        if (operation.isVariableArguments() && (argumentIndex >= operation.getParameters().size())) {
            return operation.getParameters().size() - 1;
        } else {
            return argumentIndex;
        }
    }

    /**
     * Analyze whether a variable is linked directly or indirectly to a parameter or a common block
     * and create a dataflow object accordingly. In case there is no link to an parameter or common
     * block do not create a dataflow element.
     *
     * @param project
     *            project object
     * @param callerModule
     *            module context
     * @param callerOperation
     *            caller
     * @param variableName
     *            name of the variable
     * @param calleeModle
     *            callee module
     * @param calleeOperation
     *            callee operation
     * @param parameter
     *            parameter of the callee
     */
    private void analyzeVariableDataflow(final FortranProject project, final FortranModule callerModule,
            final FortranOperation callerOperation, final String variableName, final FortranModule calleeModule,
            final FortranOperation calleeOperation, final FortranParameter parameter) {
        final CommonBlock commonBlock = this.findCommonBlock(callerModule, callerOperation, variableName);
        if (commonBlock != null) {
            // communication between argument and common block
            this.createArgumentToCommonBlockLink(commonBlock, calleeModule, callerOperation, parameter);
        } else {
            // is variable connected to another variable or parameter?
            final FortranVariable variable = callerOperation.getVariables().get(variableName);
            variable.getSources().stream().filter(source -> source instanceof FortranParameter)
                    .forEach(source -> this.createParameterToArgumentLink(callerModule, callerOperation, calleeModule,
                            callerOperation, parameter));
            if (!this.seekAndLinkDataOrigin(callerModule, callerOperation, variable, calleeModule, callerOperation,
                    parameter)) {
                this.createCallerCalleeDataflow(callerModule, callerOperation, variable, calleeModule, callerOperation,
                        parameter);
            }
        }
    }

    private boolean seekAndLinkDataOrigin(final FortranModule module, final FortranOperation operation,
            final FortranVariable variable, final FortranModule calleeModule, final FortranOperation calleeOperation,
            final FortranParameter calleeArgument) {
        final Stream<IDataflowSource> variables = variable.getSources().stream()
                .filter(source -> source instanceof FortranVariable);

        return variables.map(source -> {
            final CommonBlock commonBlock = this.findCommonBlock(module, operation, variable.getName());
            if (commonBlock == null) {
                return this.seekAndLinkDataOrigin(module, operation, (FortranVariable) source, calleeModule,
                        calleeOperation, calleeArgument);
            } else {
                // Variable is transitively from common block. link callee to common block
                this.createArgumentToCommonBlockLink(commonBlock, calleeModule, calleeOperation, calleeArgument);
                return true;
            }
        }).reduce(false, (result, value) -> result || value);
    }

    /** create dataflow output. */

    private void createArgumentToCommonBlockLink(final CommonBlock commonBlock, final FortranModule calleeModule,
            final FortranOperation calleeOperation, final FortranParameter calleeArgument) {
        this.dataflowOutputPort.send(new CommonBlockArgumentDataflow(commonBlock.getName(), calleeModule.getFileName(),
                calleeModule.getModuleName(), calleeArgument.getDirection()));
    }

    private void createCallerCalleeDataflow(final FortranModule callerModule, final FortranOperation callerOperation,
            final FortranVariable variable, final FortranModule calleeModule, final FortranOperation calleeOperation,
            final FortranParameter argument) {
        this.dataflowOutputPort.send(new CallerCalleeDataflow(callerModule.getFileName(), callerModule.getModuleName(),
                callerOperation.getName(), calleeModule.getFileName(), calleeModule.getModuleName(),
                calleeOperation.getName(), argument.getDirection()));
    }

    private void createCallerCalleeDataflowRead(final FortranModule callerModule,
            final FortranOperation callerOperation, final FortranModule calleeModule,
            final FortranOperation calleeOperation) {
        this.dataflowOutputPort.send(new CallerCalleeDataflow(callerModule.getFileName(), callerModule.getModuleName(),
                callerOperation.getName(), calleeModule.getFileName(), calleeModule.getModuleName(),
                calleeOperation.getName(), EDirection.READ));
    }

    private void createParameterToArgumentLink(final FortranModule callerModule, final FortranOperation callerOperation,
            final FortranModule calleeModule, final FortranOperation calleeOperation, final FortranParameter argument) {
        this.dataflowOutputPort.send(new CallerCalleeDataflow(callerModule.getFileName(), callerModule.getModuleName(),
                callerOperation.getName(), calleeModule.getFileName(), calleeModule.getModuleName(),
                calleeOperation.getName(), argument.getDirection()));
    }

    /** helper. */

    private CommonBlock findCommonBlock(final FortranModule module, final FortranOperation operation,
            final String variableName) {
        final Optional<CommonBlock> commonBlock = this.findCommonBlockForVariable(operation.getCommonBlocks(),
                variableName);
        if (commonBlock.isPresent()) {
            return commonBlock.get();
        } else {
            final Optional<CommonBlock> moduleCommonBlock = this.findCommonBlockForVariable(module.getCommonBlocks(),
                    variableName);
            if (moduleCommonBlock.isPresent()) {
                return moduleCommonBlock.get();
            } else {
                return null;
            }
        }
    }

    private Optional<CommonBlock> findCommonBlockForVariable(final Map<String, CommonBlock> commonBlocks,
            final String variableName) {
        return commonBlocks.values().stream().filter(block -> block.getVariables().get(variableName) != null)
                .findFirst();
    }

    private void analyzeExpression(final FortranProject project, final FortranModule contextModule,
            final FortranOperation contextOperation, final FortranModule callerModule,
            final FortranOperation callerOperation, final FortranModule calleeModule,
            final FortranOperation calleeOperation, final Node content) {
        final List<Node> nodes = NodeProcessingUtils.findAllSiblings(content.getFirstChild(),
                Predicates.isNamedExpression, o -> false);
        nodes.forEach(node -> {
            final String nodeName = NodeProcessingUtils.getName(node);
            if (contextOperation.getVariables().keySet().contains(nodeName)) {
                this.createCallerCalleeDataflowRead(contextModule, contextOperation, calleeModule, calleeOperation);
            } else if (contextOperation.getParameters().get(nodeName) != null) {
                this.createCallerCalleeDataflowRead(contextModule, contextOperation, calleeModule, calleeOperation);
            } else {
                this.analyzeFunctionCall(project, contextModule, contextOperation, calleeModule, calleeOperation, node);
            }
        });
    }

    private void analyzeFunctionCall(final FortranProject project, final FortranModule contextModule,
            final FortranOperation contextOperation, final FortranModule callerModule,
            final FortranOperation callerOperation, final Node calleeNode) {
        final String functionName = NodeProcessingUtils.getName(calleeNode);
        final Pair<FortranModule, FortranOperation> callee = this.findOperation(project.getModules().values(),
                functionName);
        if (callee != null) {
            final List<Node> rlt = NodeProcessingUtils.findAllSiblings(calleeNode.getFirstChild(), Predicates.isRLT,
                    o -> false);
            if (rlt.size() > 0) {
                final Node parensR = rlt.get(0).getFirstChild();
                if (Predicates.isParensR.test(parensR)) {
                    final Node elementLT = NodeProcessingUtils.findChildFirst(parensR, Predicates.isElementLT);
                    final List<Node> arguments = NodeProcessingUtils.findAllSiblings(elementLT.getFirstChild(),
                            Predicates.isElement, o -> false);
                    for (int i = 0; i < arguments.size(); i++) {
                        this.analyzeArgument(project, contextModule, contextOperation, callerModule, callerOperation,
                                callee.first, callee.second, arguments.get(i).getFirstChild(), i);
                    }
                } else {
                    this.logger.error("Internal error: Unknown function parameter definition {}", parensR);
                }
            } else {
                this.logger.warn("No parameter for function '{}' in context {}::{} in invocation {}::{} -> {}::{}",
                        functionName, contextModule.getFileName(), contextOperation.getName(),
                        callerModule.getFileName(), callerOperation.getName(), callee.getFirst().getFileName(),
                        callee.getSecond().getName());
            }
        } else {
            this.logger.error("In expression context {}::{} unknown function {} invoked for {}::{}",
                    contextModule.getFileName(), contextOperation.getName(), functionName, callerModule.getFileName(),
                    callerOperation.getName());
        }
    }

    /**
     *
     * @param project
     * @param contextModule
     * @param contextOperation
     * @param content
     */
    private void analyzeAssignmentExpression(final FortranProject project, final FortranModule contextModule,
            final FortranOperation contextOperation, final Node content) {
        final List<Node> nodes = NodeProcessingUtils.findAllSiblings(content.getFirstChild(),
                Predicates.isNamedExpression, o -> false);
        nodes.forEach(node -> {
            final String nodeName = NodeProcessingUtils.getName(node);
            if (!contextOperation.getVariables().keySet().contains(nodeName)
                    && !contextOperation.getParameters().containsKey(nodeName)) {
                // could be a function
                final Pair<FortranModule, FortranOperation> function = this.findOperation(project.getModules().values(),
                        nodeName);
                if (function != null) {
                    this.analyzeFunctionCall(project, contextModule, contextOperation, function.getFirst(),
                            function.getSecond(), node);
                } else {
                    this.logger.warn("Named element {} in expression context in {}::{} is not a function.", nodeName,
                            contextModule.getFileName(), contextOperation.getName());
                }

            }
        });
    }

    private boolean isOperationParameterOrVariable(final FortranOperation operation, final String argumentName) {
        if (operation.getParameters().get(argumentName) != null) {
            return true;
        }
        return operation.getVariables().containsKey(argumentName);
    }

    private boolean isOperationCommonBlock(final FortranOperation operation, final String argumentName) {
        return operation.getCommonBlocks().values().stream()
                .anyMatch(commonBlock -> commonBlock.getVariables().containsKey(argumentName));
    }

    private boolean isModuleCommonBlock(final FortranModule module, final String argumentName) {
        return module.getCommonBlocks().values().stream()
                .anyMatch(commonBlock -> commonBlock.getVariables().containsKey(argumentName));
    }

    private void analyzeAssignmentStatement(final FortranProject project, final FortranModule module,
            final FortranOperation operation, final Node node) {
        final Node expessionNode = XPathParser.getAssignmentExpression(node);
        if (Predicates.isAssignmentE2.test(expessionNode)) {
            this.analyzeAssignmentExpression(project, module, operation, expessionNode);
        } else {
            this.logger.warn("Unknown expression type {}", node.toString());
        }
    }

    private void analyzeIfThenStatement(final Node node, final FortranProject project, final FortranModule module,
            final FortranOperation operation) {
        System.err.println("Missing if then");
    }

    private void analyzeSelectCaseStatement(final Node node, final FortranProject project, final FortranModule module,
            final FortranOperation operation) {
        System.err.println("Missing select case");
    }

    private void analyzeDoStatement(final Node node, final FortranProject project, final FortranModule module,
            final FortranOperation operation) {
        System.err.println("Missing do loop");
    }

    private void analyzeExecutionPart(final FortranProject project, final FortranModule module,
            final FortranOperation operation) {

        final Node operationNode = operation.getNode();

        if (operation.getNode() == null) {
            return;
        }

        NodeProcessingUtils
                .findAllSiblings(operationNode.getFirstChild(), o -> true, Predicates.isEndSubroutineStatement)
                .forEach(statement -> {
                    final String caller = operation.getName();

                    if (Predicates.isAssignmentStatement.test(statement)) {
                        this.analyzeAssignment(project, module, operation, statement);
                    } else if (Predicates.isCallStatement.test(statement)) {
                        this.analyzeCallStatement(project, module, operation, statement);
                    } else if (Predicates.isIfThenStatement.test(statement)) {
                        // this.analyzeIfThen(operation, statement);
                    } else if (Predicates.isIfStatement.test(statement)) {
                        this.analyzeReadsFromStatement(statement, caller, module, project);
                        // this.analyzeIf(operation, statement);
                    } else if (Predicates.isElseIfStatement.test(statement)) {
                        // this.analyzeIfThen(operation, statement);
                    } else if (Predicates.isDoStatement.or(Predicates.isDoLabelStatement).test(statement)) {
                        // this.analyzeDoStatement(operation, statement);
                    } else if (Predicates.isReadStatement.test(statement)) {
                        // this.analyzeReadStatement(operation, statement);
                    } else if (Predicates.isWriteStatement.test(statement)) {
                        // this.analyzeWriteStatement(operation, statement);
                    } else if (Predicates.isSaveStatement.test(statement)) {
                        // this.analyzeSaveStatement(operation, statement);
                    } else if (Predicates.isDataStatement.test(statement)) {
                        // this.analyzeDataStatement(operation, statement);
                    } else if (Predicates.isPrintStatement.test(statement)) {
                        // this.analyzePrintStatement(operation, statement);
                    } else if (Predicates.isWhereStatement.test(statement)) {
                        // this.analyzeWhereStatement(operation, statement);
                    } else if (Predicates.isCloseStatement.test(statement)) {
                        // this.analyzeCloseStatement(operation, statement);
                    } else if (Predicates.isOpenStatement.test(statement)) {
                        // this.analyzeOpenStatement(operation, statement);
                    } else if (Predicates.isDIMStatement.test(statement)) {
                        // this.analyzeDIMStatement(operation, statement);
                    } else if (Predicates.isEndFileStatement.test(statement)) {
                        // this.analyzeEndFileStatement(operation, statement);
                    } else if (Predicates.isNamelistStatement.test(statement)) {
                        // this.checkNamelistStatement(operation, statement);
                    } else if (Predicates.isImplicitNoneStmt.test(statement)) {
                        operation.setImplicit(false);
                    } else if (Predicates.isM.or(Predicates.isC).or(Predicates.isTDeclStmt).or(Predicates.isFile)
                            .or(Predicates.isInclude).or(Predicates.isOperationStatement).or(Predicates.isEndStatement)
                            .or(Predicates.isGotoStatement).or(Predicates.isLabel).or(Predicates.isContinueStatement)
                            .or(Predicates.isFormatStatement).or(Predicates.isElseStatement)
                            .or(Predicates.isReturnStatement).or(Predicates.isRewindStatement)
                            .or(Predicates.isStopStatement).or(Predicates.isAllocateStatement)
                            .or(Predicates.isDeallocateStatement).or(Predicates.isInquireStatement)
                            .or(Predicates.isParameterStatement).or(Predicates.isCommonStatement)
                            .or(Predicates.isExitStatement).test(statement)) {
                        // ignore
                    } else if (statement.getNodeType() == Node.TEXT_NODE) {
                        // ignore text
                    } else {
                        this.logger.warn("Unknown statement {} ", statement.toString());
                    }
                });

    }

    private void analyzeAssignment(final FortranProject project, final FortranModule module,
            final FortranOperation caller, final Node statement) {
        final Node assignedContent = XPathParser.getAssigmentVariable(statement); // left part
        final Node assigningContent = XPathParser.getAssignmentExpression(statement); // right part

        // getName as a String
        final String assignedVar = XPathParser.getAssignTargetIdentifier(statement);
        // left part is in common Block
        final List<String> blockIdentifierAssign = this.getCommonBlockIdsForGivenVariable(assignedVar,
                module.getCommonBlocks());

        // neither funcs or arrays
        final List<Node> namesRight = XPathParser.getNames(assigningContent);
        // something with args
        final List<Node> potentialFuncs = XPathParser.getPotentialFuncs(assigningContent);
        // func no args
        final List<Node> nonArgsFunc = XPathParser.getNonArgsFunc(assigningContent);

        if (potentialFuncs.size() > 0) { // potential function analysis
            final List<String> blockIdentifierList = this.analyzePotentialFuncStmt(potentialFuncs, new ArrayList<>(),
                    module, caller.getName(), project);
            final List<String> namesAsString = this.convertToString(namesRight);
            blockIdentifierList.addAll(this.checkNamesWithCommon(namesAsString, module.getCommonBlocks()));
            // delete duplicates

            if (blockIdentifierAssign.size() == 0) {
                for (final String blockId : blockIdentifierList) {
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "FILE",
                                "/" + blockId + "/", "VARIABLES", DataFlowAnalysisStage2.READ);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add read dataflow call->cb {}", e.getLocalizedMessage());
                    }
                }
            } else if (blockIdentifierList.size() > 0) {
                this.writeCommonDataflow(blockIdentifierAssign.get(0), blockIdentifierList, caller.getName(), module,
                        project);
            } else {
                final String blockId = blockIdentifierAssign.get(0);
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "FILE",
                            "/" + blockId + "/", "VARIABLES", DataFlowAnalysisStage2.WRITE);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add write dataflow call->cb {}", e.getLocalizedMessage());
                }
            }
        } else if (nonArgsFunc.size() > 0) {
            for (final Node cons : nonArgsFunc) {
                this.checkAssignWithCommon(assignedVar, caller.getName(), module, project);
                final String functionId = XPathParser.getStructureConstructorIdentifier(cons);
                final Pair<FortranModule, FortranOperation> operation = this
                        .findOperation(project.getModules().values(), functionId);
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller,
                            operation.getFirst().getFileName(), operation.getFirst().getModuleName(), functionId,
                            DataFlowAnalysisStage2.READ);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add read dataflow call->cb {}", e.getLocalizedMessage());
                }
            }
        } else {
            final List<String> namesAsString = this.convertToString(namesRight);
            final List<String> blockIdentifierList = this.checkNamesWithCommon(namesAsString, module.getCommonBlocks());

            if (blockIdentifierAssign.size() > 0) {

                if (blockIdentifierList.size() == 0) {
                    final String blockId = blockIdentifierAssign.get(0);
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                                blockId, DataFlowAnalysisStage2.WRITE);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add write dataflow call->cb {}", e.getLocalizedMessage());
                    }
                } else {
                    this.writeCommonDataflow(blockIdentifierAssign.get(0), blockIdentifierList, caller.getName(),
                            module, project);
                }
            } else {
                for (final String blockId : blockIdentifierList) {
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller.getName(),
                                "X", "X", blockId, DataFlowAnalysisStage2.READ);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add read dataflow call->cb {}", e.getLocalizedMessage());
                    }
                }
            }
        }
    }

    private void analyzeReadsFromStatement(final Node stmt, final String caller, final FortranModule module,
            final FortranProject project) {
        final List<String> names = XPathParser.getNamesFromStatement(stmt);
        final List<String> blocks = this.checkNamesWithCommon(names, module.getCommonBlocks());
        for (final String block : blocks) {
            try {
                this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "BLOCK",
                        "/" + block + "/", "VARIABLES", DataFlowAnalysisStage2.READ);
            } catch (final ValueConversionErrorException e) {
                this.logger.error("Dataflow for reading from common block failed {}", e.getLocalizedMessage());
            }
        }
    }

    private List<String> convertToString(final List<Node> namesRight) {
        final List<String> result = new ArrayList<>();
        for (final Node node : namesRight) {
            result.add(node.getTextContent());
        }
        return result;
    }

    private List<String> analyzePotentialFuncStmt(final List<Node> funcs, final List<String> blacklist,
            final FortranModule module, final String caller, final FortranProject project) {
        final List<String> blockIdentifierList = new ArrayList<>();
        for (final Node funcNode : funcs) {
            final String functionIdentifier = XPathParser.getPartRefNodeIdentifier(funcNode);
            final List<String> functionInCommonBlockList = this.getCommonBlockIdsForGivenVariable(functionIdentifier,
                    module.getCommonBlocks());

            if (functionInCommonBlockList.size() > 0) {
                blockIdentifierList.addAll(functionInCommonBlockList);
                final List<String> args = XPathParser.getArgumentList(funcNode);
                for (final String arg : args) {
                    this.getCommonBlockIdsForGivenVariable(arg, module.getCommonBlocks());
                    // dataflowLinesRest.addAll(this.isVarFromCommonBlock(arg, commonBlocks));
                }

            } else if (!blacklist.contains(functionIdentifier)) {
                final List<String> args = XPathParser.getArgumentList(funcNode);
                if (args.size() > 0) {
                    for (final String arg : args) {
                        blockIdentifierList
                                .addAll(this.getCommonBlockIdsForGivenVariable(arg, module.getCommonBlocks()));
                    }

                    final Pair<FortranModule, FortranOperation> operation = this
                            .findOperation(project.getModules().values(), functionIdentifier);

                    final String moduleName;
                    final String fileName;
                    if (operation == null) {
                        moduleName = this.defaultModuleName;
                        fileName = this.defaultModuleName;
                    } else {
                        moduleName = operation.getFirst().getModuleName();
                        fileName = operation.getFirst().getFileName();
                    }

                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, fileName,
                                moduleName, functionIdentifier, DataFlowAnalysisStage2.BOTH);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add both dataflow call->f {}", e.getLocalizedMessage());
                    }
                } else {
                    final Pair<FortranModule, FortranOperation> operation = this
                            .findOperation(project.getModules().values(), functionIdentifier);

                    final String moduleName;
                    final String fileName;
                    if (operation == null) {
                        moduleName = this.defaultModuleName;
                        fileName = this.defaultModuleName;
                    } else {
                        moduleName = operation.getFirst().getModuleName();
                        fileName = operation.getFirst().getFileName();
                    }

                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, fileName,
                                moduleName, functionIdentifier, DataFlowAnalysisStage2.BOTH);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add both dataflow call->f {}", e.getLocalizedMessage());
                    }
                }
            }

        }

        return blockIdentifierList;
    }

    private void writeCommonDataflow(final String blockIdentifierAssign, final List<String> blockIdentifierList,
            final String caller, final FortranModule module, final FortranProject project) {

        for (final String blockIdentifier : blockIdentifierList) {
            if (blockIdentifierAssign.equals(blockIdentifier)) {
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                            blockIdentifier, DataFlowAnalysisStage2.BOTH);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add bidirection dataflow {}", e.getLocalizedMessage());
                }
            } else {
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                            blockIdentifier, DataFlowAnalysisStage2.READ);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add read dataflow {}", e.getLocalizedMessage());
                }

                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                            blockIdentifier, "WRTE");
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add write dataflow {}", e.getLocalizedMessage());
                }
            }
        }

    }

    /**
     * Check whether a given name is a variale reference.
     *
     * @param module
     *            module to check in
     * @param variableName
     *            name of the suspected variable
     * @return returns true in case it is a variable
     */
    private boolean isVariableReference(final FortranModule module, final String variableName) {
        return module.getVariables().containsKey(variableName.toLowerCase(Locale.getDefault()));
    }

    private List<String> getCommonBlockIdsForGivenVariable(final String variableName,
            final Map<String, CommonBlock> commonBlocks) {
        final List<String> blockIdentifierList = new ArrayList<>();
        for (final CommonBlock commonBlock : commonBlocks.values()) {
            final String blockIdentifier = commonBlock.getName();
            if (commonBlock.getVariables().containsKey(variableName)) {
                blockIdentifierList.add(blockIdentifier);
            }
        }
        return blockIdentifierList;
    }

    private void checkAssignWithCommon(final String assignedVar, final String caller, final FortranModule module,
            final FortranProject project) {

        final List<String> blockIdentifierList = this.getCommonBlockIdsForGivenVariable(assignedVar,
                module.getCommonBlocks());
        if (blockIdentifierList.size() > 0) {
            for (final String blockId : blockIdentifierList) {
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                            blockId, DataFlowAnalysisStage2.WRITE);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add write dataflow {}", e.getLocalizedMessage());
                }
            }
        }

    }

    private List<String> checkNamesWithCommon(final List<String> names, final Map<String, CommonBlock> commonBlocks) {
        final List<String> blockIdentifierList = new ArrayList<>();
        if (commonBlocks.size() > 0) {
            for (final String name : names) {
                blockIdentifierList.addAll(this.getCommonBlockIdsForGivenVariable(name, commonBlocks));
            }
            return blockIdentifierList;
        }
        return blockIdentifierList;
    }

    // TODO duplicate form ProcessOperationCallStage
    private Pair<FortranModule, FortranOperation> findOperation(final Collection<FortranModule> modules,
            final String signature) {
        final Optional<FortranModule> moduleOptional = modules.stream().filter(module -> module.getOperations().values()
                .stream().anyMatch(operation -> operation.getName().equals(signature))).findFirst();
        if (moduleOptional.isPresent()) {
            return new Pair<>(moduleOptional.get(), moduleOptional.get().getOperations().get(signature));
        } else {
            return null;
        }
    }

}
