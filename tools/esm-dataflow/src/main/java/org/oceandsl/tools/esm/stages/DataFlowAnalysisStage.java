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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.w3c.dom.Node;

import teetime.framework.OutputPort;
import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.tools.esm.util.Output;
import org.oceandsl.tools.fxca.model.CommonBlock;
import org.oceandsl.tools.fxca.model.EDirection;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranParameter;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.model.FortranVariable;
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;
import org.oceandsl.tools.fxca.tools.Pair;
import org.oceandsl.tools.fxca.tools.Predicates;

public class DataFlowAnalysisStage extends AbstractTransformation<FortranProject, Output> {

    private final OutputPort<CommonBlockEntry> commonBlockOutputPort = this.createOutputPort(CommonBlockEntry.class);
    private final OutputPort<IDataflowEntry> dataflowOutputPort = this.createOutputPort(IDataflowEntry.class);

    private final Output out = new Output();

    private final String defaultModuleName;

    public DataFlowAnalysisStage(final String defaultModuleName) {
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

    private void analyzeNode(final Node statement, final FortranProject project, final FortranModule module,
            final FortranOperation operation) {
        if (Predicates.isCallStatement.test(statement)) {
            this.analyzeCallStatement(project, module, operation, statement);
        } else if (Predicates.isAssignmentStatement.test(statement)) {
            this.analyzeAssignmentStatement(project, module, operation, statement);
        } else if (Predicates.isIfThenStatement.test(statement)) {
            this.analyzeIfThenStatement(project, module, operation, statement);
        } else if (Predicates.isSelectCaseStatement.test(statement)) {
            this.analyzeSelectCaseStatement(project, module, operation, statement);
        } else if (Predicates.isDoStatement.test(statement)) {
            this.analyzeDoStatement(project, module, operation, statement);
        }
    }

    /**
     * Analyze a call statement for data flow. The dataflow is from all variables and parameters of
     * the operation, module and common blocks, to the called subroutine and potentially invoked
     * functions.
     *
     * @param project
     *            project context
     * @param module
     *            module context
     * @param operation
     *            operation context
     * @param statement
     *            statement
     */
    private void analyzeCallStatement(final FortranProject project, final FortranModule module,
            final FortranOperation operation, final Node statement) {
        // get the called subroutine
        final String calleeName = NodeProcessingUtils.getCalleeNameFromCall(statement);
        final Pair<FortranModule, FortranOperation> callee = this.findOperation(project.getModules().values(),
                calleeName);
        // get all arguments for the call
        final Node argumentSpecification = NodeProcessingUtils.findChildFirst(statement,
                Predicates.isArgumentSpecification);
        if (argumentSpecification != null) {
            final List<Node> arguments = NodeProcessingUtils.findAllSiblings(argumentSpecification.getFirstChild(),
                    Predicates.isArgument, o -> false);
            for (int i = 0; i < arguments.size(); i++) {
                this.analyzeArgument(project, module, operation, callee.first, callee.second,
                        arguments.get(i).getFirstChild(), i);
            }
        }
    }

    private void analyzeAssignmentStatement(final FortranProject project, final FortranModule module,
            final FortranOperation operation, final Node statement) {
        System.err.println("ASSIGNMENT");
    }

    private void analyzeDoStatement(final FortranProject project, final FortranModule module,
            final FortranOperation operation, final Node statement) {
        System.err.println("DO");
    }

    private void analyzeSelectCaseStatement(final FortranProject project, final FortranModule module,
            final FortranOperation operation, final Node statement) {
        System.err.println("CASE");
    }

    private void analyzeIfThenStatement(final FortranProject project, final FortranModule module,
            final FortranOperation operation, final Node statement) {
        System.err.println("IF");
    }

    /**
     * Analyze the dataflow of one argument. Target of the flow is the called subroutine (callee),
     * the variable and parameter context is the context. In case the argument is the argument of a
     * function, then the result of the function is send to the caller function.
     *
     * @param project
     * @param contextModule
     * @param contextOperation
     * @param callerModule
     * @param operation2
     * @param first
     * @param second
     * @param firstChild
     * @param i
     */
    private void analyzeArgument(final FortranProject project, final FortranModule contextModule,
            final FortranOperation contextOperation, final FortranModule calleeModule,
            final FortranOperation calleeOperation, final Node argumentNode, final int argumentIndex) {
        // get the callee's parameter declaration
        final Optional<FortranParameter> calleeParameter = this.findCalleeParameter(calleeOperation, argumentIndex);
        if (calleeParameter.isEmpty()) {
            this.logger.error("Error: Context {}::{}: No parameter exists for argument index {} of {}::{}",
                    contextModule.getFileName(), contextOperation.getName(), argumentIndex, calleeModule.getFileName(),
                    calleeOperation.getName());
        }
        final IDataflowEndpoint endpoint = this.analyzeExpression(project, contextModule, contextOperation,
                calleeModule, calleeOperation, argumentNode);
        // create dataflow from the expression to the callee
        this.crateFlowFromEndpointToOperation(endpoint, calleeModule, calleeOperation);
    }

    private void crateFlowFromEndpointToOperation(final IDataflowEndpoint endpoint, final FortranModule calleeModule,
            final FortranOperation calleeOperation) {
        if (endpoint != null) {
            if (endpoint instanceof DataflowEndpoint) {
                System.err.printf("CREATE FLOW from %s -> %s::%s\n", endpoint.toString(), calleeModule.getFileName(),
                        calleeOperation.getName());
            } else if (endpoint instanceof MultipleDataflowEndpoint) {
                ((MultipleDataflowEndpoint) endpoint).getEndpoints().forEach(e -> {
                    System.err.printf("CREATE FLOW from %s -> %s::%s\n", e.toString(), calleeModule.getFileName(),
                            calleeOperation.getName());
                });
            }
        } else {
            System.err.println("No endpoint");
        }
    }

    /**
     * The dataflow is from the expression to the callee. The context provides parameter and
     * variable accesses.
     *
     * @param project
     *            project context
     * @param contextModule
     * @param contextOperation
     * @param calleeModule
     * @param calleeOperation
     * @param expressionNode
     *            data source node
     */
    private IDataflowEndpoint analyzeExpression(final FortranProject project, final FortranModule contextModule,
            final FortranOperation contextOperation, final FortranModule calleeModule,
            final FortranOperation calleeOperation, final Node expressionNode) {
        if (Predicates.isNamedExpressionAccess.test(expressionNode)) {
            return this.analyzeNamedExpressionAccess(project, contextModule, contextOperation, calleeModule,
                    calleeOperation, expressionNode);
        } else if (Predicates.isNamedExpression.test(expressionNode)) {
            return this.analyzeNamedExpressionAccess(project, contextModule, contextOperation, calleeModule,
                    calleeOperation, expressionNode);
        } else if (Predicates.isOperandExpression.test(expressionNode)) {
            return this.analyzeOperationExpression(project, contextModule, contextOperation, calleeModule,
                    calleeOperation, expressionNode);
        } else if (Predicates.isParensE.test(expressionNode)) {
            return this.analyzeExpression(project, contextModule, contextOperation, calleeModule, calleeOperation,
                    expressionNode.getFirstChild().getNextSibling());
        } else if (Predicates.isLiteralE.or(Predicates.isStringE).test(expressionNode)) {
            // skip
            return null;
        } else {
            System.err.println("ILLEGAL " + expressionNode);
            return new DataflowEndpoint(calleeModule, calleeOperation, EDirection.NONE);
        }
    }

    /**
     * An operation may contain many sub elements which serve as data endpoint.
     *
     * @param project
     * @param contextModule
     * @param contextOperation
     * @param calleeModule
     * @param calleeOperation
     * @param expressionNode
     * @return
     */
    private IDataflowEndpoint analyzeOperationExpression(final FortranProject project,
            final FortranModule contextModule, final FortranOperation contextOperation,
            final FortranModule calleeModule, final FortranOperation calleeOperation, final Node expressionNode) {
        final List<Node> operators = NodeProcessingUtils.findAllSiblings(expressionNode.getFirstChild(),
                Predicates.isOperand.negate(), o -> false);

        final MultipleDataflowEndpoint multipleEndpoint = new MultipleDataflowEndpoint();
        operators.stream().map(operator -> this.analyzeExpression(project, contextModule, contextOperation,
                calleeModule, calleeOperation, operator)).forEach(endpoint -> multipleEndpoint.add(endpoint));

        return multipleEndpoint;
    }

    private IDataflowEndpoint analyzeNamedExpressionAccess(final FortranProject project,
            final FortranModule contextModule, final FortranOperation contextOperation,
            final FortranModule calleeModule, final FortranOperation calleeOperation, final Node namedElementNode) {
        final String elementName = NodeProcessingUtils.getName(namedElementNode);
        // check whether this is a parameter or variable
        final FortranParameter parameter = contextOperation.getParameters().get(elementName);
        if (parameter != null) {
            return this.createFlowEndpoint(contextModule, contextOperation, parameter.getDirection());
        } else {
            final FortranVariable variable = contextOperation.getVariables().get(elementName);
            if (variable != null) {
                return this.createFlowEndpoint(contextModule, contextOperation, variable.getDirection());
            } else {
                return this.analyzeFunctionCall(project, contextModule, contextOperation, elementName,
                        namedElementNode);
            }
        }
    }

    private DataflowEndpoint analyzeFunctionCall(final FortranProject project, final FortranModule contextModule,
            final FortranOperation contextOperation, final String functionName, final Node functionNode) {
        final Pair<FortranModule, FortranOperation> callee = this.findOperation(project.getModules().values(),
                functionName);

        if (callee != null) {
            final List<Node> rlt = NodeProcessingUtils.findAllSiblings(functionNode.getFirstChild(), Predicates.isRLT,
                    o -> false);
            if (rlt.size() > 0) {
                final Node parensR = rlt.get(0).getFirstChild();
                if (Predicates.isParensR.test(parensR)) {
                    final Node elementLT = NodeProcessingUtils.findChildFirst(parensR, Predicates.isElementLT);
                    final List<Node> arguments = NodeProcessingUtils.findAllSiblings(elementLT.getFirstChild(),
                            Predicates.isElement, o -> false);
                    for (int i = 0; i < arguments.size(); i++) {
                        this.analyzeArgument(project, contextModule, contextOperation, callee.first, callee.second,
                                arguments.get(i).getFirstChild(), i);
                    }
                } else {
                    this.logger.error("Internal error: Unknown function parameter definition {}", parensR);
                }
            } else {
                this.logger.warn("No parameter for function '{}' in context {}::{} in invocation {}::{}", functionName,
                        contextModule.getFileName(), contextOperation.getName(), callee.getFirst().getFileName(),
                        callee.getSecond().getName());
            }
        } else {
            this.logger.error("In expression context {}::{} unknown function {} invoked", contextModule.getFileName(),
                    contextOperation.getName(), functionName);
        }

        return new DataflowEndpoint(callee.first, callee.second, EDirection.READ);
    }

    private IDataflowEndpoint createFlowEndpoint(final FortranModule module, final FortranOperation operation,
            final EDirection direction) {
        return new DataflowEndpoint(module, operation, direction);
    }

    private Optional<FortranParameter> findCalleeParameter(final FortranOperation calleeOperation,
            final int argumentIndex) {
        final int corectedIndex = this.computeArgumentIndex(calleeOperation, argumentIndex);
        return calleeOperation.getParameters().values().stream()
                .filter(parameter -> parameter.getPosition() == corectedIndex).findFirst();
    }

    private int computeArgumentIndex(final FortranOperation operation, final int argumentIndex) {
        if (operation.isVariableArguments() && (argumentIndex >= operation.getParameters().size())) {
            return operation.getParameters().size() - 1;
        } else {
            return argumentIndex;
        }
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