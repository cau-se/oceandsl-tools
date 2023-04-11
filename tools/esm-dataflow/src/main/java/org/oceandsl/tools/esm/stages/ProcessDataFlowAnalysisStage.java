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

import org.w3c.dom.Node;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.esm.util.Output;
import org.oceandsl.tools.esm.util.XPathParser;
import org.oceandsl.tools.fxca.model.CommonBlock;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.model.IDataflowEndpoint;
import org.oceandsl.tools.fxca.tools.Predicates;
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;
import org.oceandsl.tools.fxca.tools.Pair;

public class ProcessDataFlowAnalysisStage extends AbstractTransformation<FortranProject, Output> {

    private static final String READ = "READ";
    private static final String WRITE = "WRITE";
    private static final String BOTH = "BOTH";

    private final Output out = new Output();

    private final String defaultModuleName;

    public ProcessDataFlowAnalysisStage(final String defaultModuleName) {
        this.defaultModuleName = defaultModuleName != null ? defaultModuleName : "<unknown>";
    }

    @Override
    protected void execute(final FortranProject project) throws Exception {
        project.getModules().values().stream().filter(module -> !module.getModuleName().equals(this.defaultModuleName))
                .forEach(module -> {
                    this.analyzeOperations(module, project);
                    this.analyzeMain(module, project);
                });

        this.outputPort.send(this.out);
    }

    private void analyzeOperations(final FortranModule module, final FortranProject project) {
        for (final FortranOperation operation : module.getOperations().values()) {
            this.writeCommonBlocksName(operation.getCommonBlocks(), module.getFileName());
            try {
                this.out.getFileContent().addRow(module.getFileName(), module.getModuleName(), operation.getName());
            } catch (final ValueConversionErrorException e) {
                this.logger.error("File content table error: {}", e.getLocalizedMessage());
            }

            this.analyzeExecutionPart(operation.getNode(), module, project, operation.getName());
            this.analyzeBodyPart(project, module, operation);
        }
    }

    private void analyzeMain(final FortranModule module, final FortranProject project) {
        this.writeCommonBlocksName(module.getCommonBlocks(), module.getFileName());
        this.analyzeExecutionPart(module.getDocument(), module, project, "main");
    }

    private void analyzeBodyPart(final FortranProject project, final FortranModule module,
            final FortranOperation operation) {
        System.err.println("caller " + operation.getName());
        operation.getParameters().keySet().forEach(p -> System.err.println("\tP " + p));
        operation.getVariables().keySet().forEach(v -> System.err.println("\tV " + v));
        NodeProcessingUtils
                .findAllSiblings(operation.getNode(), o -> true,
                        Predicates.isEndSubroutineStatement.or(Predicates.isEndFunctionStatement))
                .forEach(node -> this.analyzeNode(node, project, module, operation));
    }

    private void analyzeNode(final Node node, final FortranProject project, final FortranModule module,
            final FortranOperation operation) {
        if (Predicates.isCallStatement.test(node)) {
            this.analyzeCallStatement(node, project, module, operation);
        } else if (Predicates.isAssignmentStatement.test(node)) {
            this.analyzeAssignmentStatement(node, project, module, operation);
        } else if (Predicates.isIfThenStatement.test(node)) {
            this.analyzeIfThenStatement(node, project, module, operation);
        } else if (Predicates.isSelectCaseStatement.test(node)) {
            this.analyzeSelectCaseStatement(node, project, module, operation);
        } else if (Predicates.isDoStatement.test(node)) {
            this.analyzeDoStatement(node, project, module, operation);
        }
    }

    private void analyzeCallStatement(final Node node, final FortranProject project, final FortranModule module,
            final FortranOperation operation) {
        final String calleeName = NodeProcessingUtils.getCalleeNameFromCall(node);
        System.err.println("callee " + calleeName);
        final Pair<FortranModule, FortranOperation> callee = this.findOperation(project.getModules().values(),
                calleeName);
        final Node argumentSpec = NodeProcessingUtils.findChildFirst(node, Predicates.isArgumentSpecification);
        if (argumentSpec != null) {
            final List<Node> arguments = NodeProcessingUtils.findAllSiblings(argumentSpec.getFirstChild(),
                    Predicates.isArgument, o -> false);
            arguments.forEach(argument -> {
                this.analyzeParameter(project, module, operation, callee, argument.getFirstChild());
            });
        }
    }

    private void analyzeParameter(final FortranProject project, final FortranModule module,
            final FortranOperation operation, final Pair<FortranModule, FortranOperation> callee, final Node content) {
        if (Predicates.isNamedExpressionAccess.test(content)) {
            this.analyzeFunctionCall(project, module, operation, content);
        } else if (Predicates.isNamedExpression.test(content)) {
            final String argumentName = NodeProcessingUtils.getName(content);
            if (this.isOperationParameterOrVariable(operation, argumentName)) {
                System.err.println("  operation parameter or variable " + argumentName);
                project.getDataflows().add(this.createDataFlow(module, operation, callee.first, callee.second));
            } else if (this.isModuleCommonBlock(module, argumentName)) {
                System.err.println("  module common block " + argumentName);
                project.getDataflows().add(this.createDataFlow(module, argumentName, callee.first, callee.second));
            } else if (this.isOperationCommonBlock(operation, argumentName)) {
                System.err.println("  operation common block " + argumentName);
                project.getDataflows()
                        .add(this.createDataFlow(module, operation, argumentName, callee.first, callee.second));
            } else {
                System.err.println("argument name " + argumentName);
            }
        } else if (Predicates.isOperandExpression.test(content)) {
            this.analyzeExpression(project, module, operation, callee, content);
        } else if (Predicates.isLiteralE.test(content)) {
            System.err.println("Literal can be ignored " + content.getFirstChild().getTextContent());
        } else {
            System.err.println("<> unknown content " + content + " in " + module.getFileName());
        }
    }

    private void analyzeExpression(final FortranProject project, final FortranModule module,
            final FortranOperation operation, final Pair<FortranModule, FortranOperation> callee, final Node content) {
        final List<Node> nodes = NodeProcessingUtils.findAllSiblings(content.getFirstChild(),
                Predicates.isNamedExpression, o -> false);
        nodes.forEach(node -> {
            if (Predicates.isNamedExpression.test(node)) {
                final String nodeName = NodeProcessingUtils.getName(node);
                if (operation.getVariables().keySet().contains(nodeName)) {
                    System.err.println(
                            "got variable " + nodeName + " in callee parameter " + callee.getSecond().getName());
                } else if (operation.getParameters().get(nodeName) != null) {
                    System.err.println(
                            "got parameter " + nodeName + " in callee parameter " + callee.getSecond().getName());
                } else {
                    System.err.println("not a variable or parameter " + nodeName);
                }
            }
        });
    }

    private void analyzeFunctionCall(final FortranProject project, final FortranModule module,
            final FortranOperation operation, final Node content) {
        final String functionName = NodeProcessingUtils.getName(content);
        final Pair<FortranModule, FortranOperation> callee = this.findOrCreateOperation(project, functionName);
        final List<Node> rlt = NodeProcessingUtils.findAllSiblings(content.getFirstChild(), Predicates.isRLT,
                o -> false);
        final Node parensR = rlt.get(0).getFirstChild();
        if (Predicates.isParensR.test(parensR)) {
            final Node elementLT = NodeProcessingUtils.findChildFirst(parensR, Predicates.isElementLT);
            final List<Node> elements = NodeProcessingUtils.findAllSiblings(elementLT.getFirstChild(),
                    Predicates.isElement, o -> false);
            elements.forEach(element -> {
                this.analyzeParameter(project, module, operation, callee, element.getFirstChild());
            });
        } else {
            System.err.println("unknown function parameter.");
        }
    }

    private Pair<FortranModule, FortranOperation> findOrCreateOperation(final FortranProject project,
            final String functionName) {
        final Pair<FortranModule, FortranOperation> callee = this.findOperation(project.getModules().values(),
                functionName);
        if (callee == null) { // built-in or library function
            FortranModule builtInModule = project.getDefaultModule();
            if (builtInModule == null) {
                builtInModule = new FortranModule("<runtime>", "<runtime>", true, null);
                project.setDefaultModule(builtInModule);
            }
            FortranOperation builtInOperation = builtInModule.getOperations().get(functionName);
            if (builtInOperation == null) {
                builtInOperation = new FortranOperation(functionName, null);
                builtInModule.getOperations().put(functionName, builtInOperation);
            }

            return new Pair<>(builtInModule, builtInOperation);
        } else { // built-in or library function
            return callee;
        }
    }

    private Pair<Pair<FortranModule, IDataflowEndpoint>, Pair<FortranModule, IDataflowEndpoint>> createDataFlow(
            final FortranModule module, final FortranOperation operation, final String argumentName,
            final FortranModule targetModule, final FortranOperation targetOperation) {
        final Optional<CommonBlock> commonBlockOptional = operation.getCommonBlocks().values().stream()
                .filter(commonBlock -> commonBlock.getVariables().containsKey(argumentName)).findFirst();
        return new Pair<>(new Pair<>(module, commonBlockOptional.get()), new Pair<>(targetModule, targetOperation));
    }

    private Pair<Pair<FortranModule, IDataflowEndpoint>, Pair<FortranModule, IDataflowEndpoint>> createDataFlow(
            final FortranModule module, final String argumentName, final FortranModule targetModel,
            final FortranOperation targetOperation) {
        final Optional<CommonBlock> commonBlockOptional = module.getCommonBlocks().values().stream()
                .filter(commonBlock -> commonBlock.getVariables().containsKey(argumentName)).findFirst();
        return new Pair<>(new Pair<>(module, commonBlockOptional.get()), new Pair<>(targetModel, targetOperation));
    }

    private Pair<Pair<FortranModule, IDataflowEndpoint>, Pair<FortranModule, IDataflowEndpoint>> createDataFlow(
            final FortranModule module, final FortranOperation operation, final FortranModule targetModel,
            final FortranOperation targetOperation) {
        return new Pair<>(new Pair<>(module, operation), new Pair<>(targetModel, targetOperation));
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

    private void analyzeCallStatements(final List<Node> callStatements, final String caller, final FortranModule module,
            final FortranProject project) {

        for (final Node callStmt : callStatements) {
            final List<String> args = XPathParser.callHasArgs(callStmt);
            if (!args.isEmpty()) {
                // this.checkArgsWithCommon(args, commonBlocList, dataflowLineRest, caller);
                final String calleeName = NodeProcessingUtils.getCalleeNameFromCall(callStmt);

                final Pair<FortranModule, FortranOperation> callee = this.findOperation(project.getModules().values(),
                        calleeName);

                if (callee == null) {
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller,
                                this.defaultModuleName, this.defaultModuleName, calleeName,
                                ProcessDataFlowAnalysisStage.WRITE);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Dataflow cannot be added {}", e.getLocalizedMessage());
                    }
                } else {
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller,
                                callee.first.getFileName(), callee.first.getModuleName(), calleeName,
                                ProcessDataFlowAnalysisStage.WRITE);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Dataflow cannot be added {}", e.getLocalizedMessage());
                    }
                }
            }
        }
    }

    private void analyzeAssignmentStatement(final Node node, final FortranProject project, final FortranModule module,
            final FortranOperation operation) {
        System.err.println("Missing assignment");
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

    private void analyzeExecutionPart(final Node parent, final FortranModule module, final FortranProject project,
            final String caller) {

        if (parent == null) {
            return;
        }

        // Dataflow call stmt
        final List<Node> callStmts = XPathParser.getCallStmts(parent);
        this.analyzeCallStatements(callStmts, caller, module, project);

        // Dataflow ifelse
        final List<Node> ifElseStmts = XPathParser.getIfElseStmts(parent);
        this.analyzeReadsFromStatements(ifElseStmts, caller, module, project);

        // Dataflow select case
        final List<Node> selectStmts = XPathParser.getSelectStmts(parent);
        this.analyzeReadsFromStatements(selectStmts, caller, module, project);

        // Dataflow do while
        final List<Node> loopCtrlStmts = XPathParser.getLoopCtrlStmts(parent);
        for (final Node loopCtrl : loopCtrlStmts) {
            final String loopAssignedVar = XPathParser.getLoopControlVar(loopCtrl);
            final List<String> isInBlock = this.getCommonBlockIdsForGivenVariable(loopAssignedVar,
                    module.getCommonBlocks());
            if (!isInBlock.isEmpty()) {
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                            isInBlock.get(0), ProcessDataFlowAnalysisStage.WRITE);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add row to dataflow table {}", e.getLocalizedMessage());
                }
            }
        }
        // Analyze Reads
        this.analyzeReadsFromStatements(loopCtrlStmts, caller, module, project);

        // Dataflow assignments
        final List<Node> assignStmts = XPathParser.getAssignmentStmts(parent);
        this.analyzeAssignmentStatements(assignStmts, null, caller, module, project);
    }

    private void analyzeReadsFromStatements(final List<Node> stmts, final String caller, final FortranModule module,
            final FortranProject project) {
        for (final Node stmt : stmts) {
            final List<String> names = XPathParser.getNamesFromStatement(stmt);
            final List<String> blocks = this.checkNamesWithCommon(names, module.getCommonBlocks());
            for (final String block : blocks) {
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "BLOCK",
                            "/" + block + "/", "VARIABLES", ProcessDataFlowAnalysisStage.READ);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Dataflow for reading from common block failed {}", e.getLocalizedMessage());
                }
            }
        }

    }

    private void analyzeAssignmentStatements(final List<Node> stmts, final List<String> bl, final String caller,
            final FortranModule module, final FortranProject project) {

        for (final Node stmt : stmts) {
            final Node assignedContent = XPathParser.getAssigmentVariable(stmt); // left part
            final Node assigningContent = XPathParser.getAssignmentExpression(stmt); // right part

            // getName as a String
            final String assignedVar = XPathParser.getAssignTargetIdentifier(stmt);
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
                final List<String> blockIdentifierList = this.analyzePotentialFuncStmt(potentialFuncs, bl, module,
                        caller, project);
                final List<String> namesAsString = this.convertToString(namesRight);
                blockIdentifierList.addAll(this.checkNamesWithCommon(namesAsString, module.getCommonBlocks()));
                // delete duplicates

                if (blockIdentifierAssign.size() == 0) {
                    for (final String blockId : blockIdentifierList) {
                        try {
                            this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "FILE",
                                    "/" + blockId + "/", "VARIABLES", ProcessDataFlowAnalysisStage.READ);
                        } catch (final ValueConversionErrorException e) {
                            this.logger.error("Cannot add read dataflow call->cb {}", e.getLocalizedMessage());
                        }
                    }
                } else if (blockIdentifierList.size() > 0) {
                    this.writeCommonDataflow(blockIdentifierAssign.get(0), blockIdentifierList, caller, module,
                            project);
                } else {
                    final String blockId = blockIdentifierAssign.get(0);
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "FILE",
                                "/" + blockId + "/", "VARIABLES", ProcessDataFlowAnalysisStage.WRITE);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add write dataflow call->cb {}", e.getLocalizedMessage());
                    }
                }
            } else if (nonArgsFunc.size() > 0) {
                for (final Node cons : nonArgsFunc) {
                    this.checkAssignWithCommon(assignedVar, caller, module, project);
                    final String functionId = XPathParser.getStructureConstructorIdentifier(cons);
                    final Pair<FortranModule, FortranOperation> operation = this
                            .findOperation(project.getModules().values(), functionId);
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller,
                                operation.getFirst().getFileName(), operation.getFirst().getModuleName(), functionId,
                                ProcessDataFlowAnalysisStage.READ);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add read dataflow call->cb {}", e.getLocalizedMessage());
                    }
                }
            } else {
                final List<String> namesAsString = this.convertToString(namesRight);
                final List<String> blockIdentifierList = this.checkNamesWithCommon(namesAsString,
                        module.getCommonBlocks());

                if (blockIdentifierAssign.size() > 0) {

                    if (blockIdentifierList.size() == 0) {
                        final String blockId = blockIdentifierAssign.get(0);
                        try {
                            this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X",
                                    "X", blockId, ProcessDataFlowAnalysisStage.WRITE);
                        } catch (final ValueConversionErrorException e) {
                            this.logger.error("Cannot add write dataflow call->cb {}", e.getLocalizedMessage());
                        }
                    } else {
                        this.writeCommonDataflow(blockIdentifierAssign.get(0), blockIdentifierList, caller, module,
                                project);
                    }
                } else {
                    for (final String blockId : blockIdentifierList) {
                        try {
                            this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X",
                                    "X", blockId, ProcessDataFlowAnalysisStage.READ);
                        } catch (final ValueConversionErrorException e) {
                            this.logger.error("Cannot add read dataflow call->cb {}", e.getLocalizedMessage());
                        }
                    }
                }
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

    private List<String> analyzePotentialFuncStmt(final List<Node> funcs, final List<String> bl,
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

            } else if (!bl.contains(functionIdentifier)) {
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
                                moduleName, functionIdentifier, ProcessDataFlowAnalysisStage.BOTH);
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
                                moduleName, functionIdentifier, ProcessDataFlowAnalysisStage.BOTH);
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
                            blockIdentifier, ProcessDataFlowAnalysisStage.BOTH);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add bidirection dataflow {}", e.getLocalizedMessage());
                }
            } else {
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                            blockIdentifier, ProcessDataFlowAnalysisStage.READ);
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
                            blockId, ProcessDataFlowAnalysisStage.WRITE);
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

    private void writeCommonBlocksName(final Map<String, CommonBlock> commonBlocks, final String filename) {
        commonBlocks.values().forEach(commonBlock -> {
            try {
                this.out.getCommonBlocks().addRow(filename, "<no-module>", "<no-operation>", commonBlock.getName(),
                        commonBlock.getVariables());
            } catch (final ValueConversionErrorException e) {
                this.logger.error("Common blocks file error {}", e.getLocalizedMessage());
            }
        });
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
