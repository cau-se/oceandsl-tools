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

import java.util.List;
import java.util.Set;

import org.w3c.dom.Node;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.tools.esm.util.XPathParser;
import org.oceandsl.tools.fxca.model.EDirection;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranParameter;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.tools.NodePredicateUtils;
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;

/**
 * @author Reiner Jung
 * @since 1.3.0
 */
public class ComputeDirectionalityOfParametersStage extends AbstractTransformation<FortranProject, FortranProject> {

    @Override
    protected void execute(final FortranProject project) throws Exception {
        project.getModules().values().forEach(module -> {
            System.err.println("Processing module " + module.getFileName());
            module.getOperations().values().forEach(operation -> {
                System.err.println("  processing operation " + operation.getName());
                this.computeParameterDirectionality(operation);
            });
        });
        // this.outputPort.send(project);
    }

    private void computeParameterDirectionality(final FortranOperation operation) {
        final List<Node> siblings = NodeProcessingUtils.findAllSiblings(operation.getNode(), o -> true,
                NodePredicateUtils.isEndSubroutineStatement.or(NodePredicateUtils.isEndProgramStatement)
                        .or(NodePredicateUtils.isEndFunctionStatement));
        siblings.forEach(sibling -> {
            final Set<Node> assignments = NodeProcessingUtils.allDescendents(sibling,
                    NodePredicateUtils.isAssignmentStatement, false);
            assignments.forEach(assignment -> {
                this.checkAssignee(operation, assignment);
                this.checkAssignmentExpression(operation, assignment);
            });
            final Set<Node> calls = NodeProcessingUtils.allDescendents(sibling, NodePredicateUtils.isCallStatement,
                    false);
            calls.forEach(call -> {
                this.checkCall(operation, call);
            });
        });

    }

    private void checkCall(final FortranOperation operation, final Node call) {
        System.err
                .println("Call " + NodeProcessingUtils.getName(call.getFirstChild().getNextSibling().getFirstChild()));
        final Node argumentSpecification = NodeProcessingUtils.findChildFirst(call,
                NodePredicateUtils.isArgumentSpecification);
        if (argumentSpecification != null) {
            final List<Node> arguments = NodeProcessingUtils.findAllSiblings(argumentSpecification.getFirstChild(),
                    NodePredicateUtils.isArgument, o -> false);
            arguments.forEach(argument -> {
                System.err.println(" arg " + argument.getFirstChild());
                this.checkExpression(operation, argument.getFirstChild());
            });
        } else {
            System.err.println(" no arguments");
        }
        System.err.println("end call");
    }

    private void checkAssignmentExpression(final FortranOperation operation, final Node assignment) {
        final Node node = XPathParser.getAssignmentExpression(assignment);
        if (NodePredicateUtils.isNamedExpression.test(node)) {
            System.err.println(" named expression " + NodeProcessingUtils.getName(node));
        } else if (NodePredicateUtils.isAssignmentE2.test(node)) {
            this.checkExpression(operation, node);
        } else {
            System.err.println(" unknown expression type " + node);
        }
    }

    private void checkExpression(final FortranOperation operation, final Node node) {
        final Node operationNode = node.getFirstChild();
        if (NodePredicateUtils.isOpE.test(operationNode)) {
            final List<Node> expression = NodeProcessingUtils.findAllSiblings(operationNode.getFirstChild(), o -> true,
                    o -> false);
            expression.forEach(element -> {
                if (NodePredicateUtils.isNamedExpression.test(element)) {
                    final String elementName = NodeProcessingUtils.getName(element);
                    final FortranParameter parameter = operation.getParameters().get(elementName);
                    if (parameter != null) {
                        parameter.addDirection(EDirection.READ);
                    } else if (operation.getVariables().contains(elementName)) {
                        System.err.println(" variable " + elementName);
                    } else {
                        System.err.println(" other " + elementName + " " + element);
                    }
                } else if (NodePredicateUtils.isOp.test(element) || NodePredicateUtils.isM.test(element)
                        || NodePredicateUtils.isText.test(element) || NodePredicateUtils.isCnt.test(element)) {
                    // skip operators and ms
                } else {
                    System.err.println(" oops " + element);
                }
            });
        }
    }

    private void checkAssignee(final FortranOperation operation, final Node assignment) {
        final String name = NodeProcessingUtils.getName(XPathParser.getAssigmentVariable(assignment));
        final FortranParameter parameter = operation.getParameters().get(name);
        if (parameter != null) {
            parameter.addDirection(EDirection.WRITE);
        }
    }
}
