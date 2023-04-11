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
import java.util.Optional;

import org.w3c.dom.Node;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.tools.esm.util.XPathParser;
import org.oceandsl.tools.fxca.model.EDirection;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranParameter;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.model.FortranVariable;
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;
import org.oceandsl.tools.fxca.tools.Predicates;

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
                operation.getParameters().values()
                        .forEach(p -> System.err.printf("%s %s ", p.getName(), p.getDirection().name()));
                System.err.println();
            });
        });
        this.outputPort.send(project);
    }

    private void computeParameterDirectionality(final FortranOperation operation) {
        final List<Node> siblings = NodeProcessingUtils.findAllSiblings(operation.getNode(), o -> true,
                Predicates.isEndSubroutineStatement.or(Predicates.isEndProgramStatement)
                        .or(Predicates.isEndFunctionStatement));
        siblings.forEach(sibling -> {
            if (Predicates.isAssignmentStatement.test(sibling)) {
                this.checkAssignee(operation, sibling);
                this.checkAssignmentExpression(operation, sibling);
            } else if (Predicates.isCallStatement.test(sibling)) {
                this.checkCall(operation, sibling);
            } else if (Predicates.isIfThenStatement.test(sibling)) {
                this.checkIfThen(operation, sibling);
            } else if (Predicates.isIfStatement.test(sibling)) {
                this.checkIf(operation, sibling);
            } else if (Predicates.isElseIfStatement.test(sibling)) {
                this.checkIfThen(operation, sibling);
            } else if (Predicates.isDoStatement.test(sibling)) {
                this.checkDoLoop(operation, sibling);
            } else if (Predicates.isWriteStatement.test(sibling)) {
                this.checkWriteStatement(operation, sibling);
            } else if (Predicates.isSaveStatement.test(sibling)) {
                this.checkSaveStatement(operation, sibling);
            } else if (Predicates.isDataStatement.test(sibling)) {
                this.checkDataStatement(operation, sibling);
            } else if (Predicates.isM.or(Predicates.isC).or(Predicates.isTDeclStmt).or(Predicates.isFile)
                    .or(Predicates.isInclude).or(Predicates.isOperationStatement).or(Predicates.isImplicitNoneStmt)
                    .or(Predicates.isEndStatement).or(Predicates.isGotoStatement).or(Predicates.isLabel)
                    .or(Predicates.isContinueStatement).or(Predicates.isFormatStatement).or(Predicates.isElseStatement)
                    .or(Predicates.isRewindStatement).test(sibling)) {
                // ignore
            } else if (sibling.getNodeType() == Node.TEXT_NODE) {
                // ignore text
            } else {
                System.err.println(" --> unknown sibling" + sibling);
            }
        });
    }

    private void checkAssignee(final FortranOperation operation, final Node assignment) {
        final String name = NodeProcessingUtils.getName(XPathParser.getAssigmentVariable(assignment));
        // System.err.println(" assign to " + name);
        final FortranParameter parameter = operation.getParameters().get(name);
        if (parameter != null) {
            parameter.addDirection(EDirection.WRITE);
        }
    }

    private void checkAssignmentExpression(final FortranOperation operation, final Node assignment) {
        final Node node = XPathParser.getAssignmentExpression(assignment);
        if (Predicates.isNamedExpression.test(node)) {
            // System.err.println(" named expression " + NodeProcessingUtils.getName(node));
        } else if (Predicates.isAssignmentE2.test(node)) {
            this.checkExpression(operation, node);
        } else {
            System.err.println(" unknown expression type " + node);
        }
        // System.err.println(" end assignment");
    }

    private void checkDoLoop(final FortranOperation operation, final Node doLoop) {
        // System.err.println(" do loop"); // set variable to write
        final Node lowerBound = NodeProcessingUtils.findChildFirst(doLoop, Predicates.isLowerBound);
        final Node upperBound = NodeProcessingUtils.findChildFirst(doLoop, Predicates.isUpperBound);
        if (lowerBound != null) {
            this.checkExpression(operation, lowerBound);
        }
        if (upperBound != null) {
            this.checkExpression(operation, upperBound);
        }
    }

    private void checkWriteStatement(final FortranOperation operation, final Node write) {
        final Node controls = NodeProcessingUtils.findChildFirst(write, Predicates.isIOControlSpec);
        if (controls != null) {
            NodeProcessingUtils.findAllSiblings(controls.getFirstChild(), Predicates.isIOControl, o -> false)
                    .forEach(value -> this.checkExpression(operation, value));
        }

        final Node outputs = NodeProcessingUtils.findChildFirst(write, Predicates.isOutputItemLT);
        if (outputs != null) {
            NodeProcessingUtils.findAllSiblings(outputs.getFirstChild(), Predicates.isOutputItem, o -> false)
                    .forEach(value -> this.checkExpression(operation, value));
        }
    }

    private void checkSaveStatement(final FortranOperation operation, final Node saveStatement) {
        final Node savedEnLt = NodeProcessingUtils.findChildFirst(saveStatement, Predicates.isSavedEnLt);
        NodeProcessingUtils.findAllSiblings(savedEnLt.getFirstChild(), Predicates.isSavedEn, o -> false)
                .forEach(savedEn -> {
                    final Node enN = NodeProcessingUtils.findChildFirst(savedEn, Predicates.isEnN);
                    if (enN != null) {
                        final Node bigN = NodeProcessingUtils.findChildFirst(enN, Predicates.isBigN);
                        final Node smallN = NodeProcessingUtils.findChildFirst(bigN, Predicates.isSmallN);
                        this.checkVariable(operation, smallN.getTextContent(), EDirection.READ);
                    }
                });
    }

    private void checkDataStatement(final FortranOperation operation, final Node dataStatement) {
        final Node dataStementSet = NodeProcessingUtils.findChildFirst(dataStatement, Predicates.isDataStatementSet);
        final Node objectLT = NodeProcessingUtils.findChildFirst(dataStementSet, Predicates.isDataStatementObjectLT);
        final Node object = NodeProcessingUtils.findChildFirst(objectLT, Predicates.isDataStatementObject);
        final String dataName = NodeProcessingUtils.getName(object);
        this.checkVariable(operation, dataName, EDirection.WRITE);
    }

    private void checkIfThen(final FortranOperation operation, final Node ifThen) {
        final Node condition = ifThen.getFirstChild().getNextSibling();
        this.checkExpression(operation, condition);
    }

    private void checkIf(final FortranOperation operation, final Node ifStatement) {
        final Node condition = ifStatement.getFirstChild().getNextSibling();
        this.checkExpression(operation, condition);
        NodeProcessingUtils.findChildFirst(ifStatement, Predicates.isActionStatement);
    }

    private void checkCall(final FortranOperation operation, final Node call) {
        // System.err
        // .println("Call " +
        // NodeProcessingUtils.getName(call.getFirstChild().getNextSibling().getFirstChild()));
        final Node argumentSpecification = NodeProcessingUtils.findChildFirst(call, Predicates.isArgumentSpecification);
        if (argumentSpecification != null) {
            final List<Node> arguments = NodeProcessingUtils.findAllSiblings(argumentSpecification.getFirstChild(),
                    Predicates.isArgument, o -> false);
            arguments.forEach(argument -> {
                // System.err.println(" arg " + argument.getFirstChild());
                this.checkExpression(operation, argument);
            });
        } else {
            // System.err.println(" no arguments");
        }
        // System.err.println("end call");
    }

    private void checkExpression(final FortranOperation operation, final Node node) {
        final List<Node> expression = NodeProcessingUtils.findAllSiblings(node.getFirstChild(), o -> true, o -> false);
        expression.forEach(expressionElement -> {
            if (Predicates.isNamedExpression.test(expressionElement)) {
                final String elementName = NodeProcessingUtils.getName(expressionElement);
                if (!this.checkParameter(operation, elementName)
                        && !this.checkVariable(operation, elementName, EDirection.READ)
                        && !this.checkFunction(operation, expressionElement, elementName)) {
                    // System.err.println(" other " + elementName + " " + expressionElement);
                }
            } else if (Predicates.isOperand.test(expressionElement)) {
                // System.err.println(" operand " + expressionElement.getTextContent());
            } else if (Predicates.isM.test(expressionElement)) {
                // System.err.println(" m");
            } else if (Predicates.isCnt.test(expressionElement)) {
                // System.err.println(" cnt");
            } else if (Predicates.isLiteralE.test(expressionElement)) {
                // System.err.println(" literal " + expressionElement.getTextContent());
            } else if (Predicates.isStringE.test(expressionElement)) {
                // System.err.println(" string literal " + expressionElement.getTextContent());
            } else if (Predicates.isOperandExpression.test(expressionElement)) {
                // System.err.println(" operand expression");EDirection.READ
                this.checkExpression(operation, expressionElement);
            } else if (Predicates.isParensE.test(expressionElement)) {
                // System.err.println(" parenthesis expression " +
                // expressionElement.getTextContent());
                this.checkExpression(operation, expressionElement);
            } else if (Predicates.isIterator.test(expressionElement)) {
                this.checkIterator(operation, expressionElement);
            } else if (expressionElement.getNodeType() == Node.TEXT_NODE) {
                // System.err.println(" text node " + expressionElement.getTextContent());
            } else {
                System.err.println(" oops " + expressionElement);
            }
        });
    }

    private boolean checkParameter(final FortranOperation operation, final String elementName) {
        final FortranParameter parameter = operation.getParameters().get(elementName);
        if (parameter != null) {
            // System.err.println(" param " + elementName);
            parameter.addDirection(EDirection.READ);
            return true;
        } else {
            return false;
        }
    }

    private boolean checkVariable(final FortranOperation operation, final String elementName,
            final EDirection direction) {
        if (operation.getVariables().containsKey(elementName)) {
            // System.err.println(" local var " + elementName);
            final FortranVariable variable = operation.getVariables().get(elementName);
            variable.addDirection(direction);
            return true;
        } else if (((FortranModule) operation.getParent()).getVariables().containsKey(elementName)) {
            // System.err.println(" module var " + elementName);
            final FortranVariable variable = ((FortranModule) operation.getParent()).getVariables().get(elementName);
            variable.addDirection(direction);
            return true;
        } else {
            final Optional<FortranVariable> variableOptional = ((FortranModule) operation.getParent()).getCommonBlocks()
                    .values().stream().map(commonBlock -> {
                        // System.err.println(" common block " + commonBlock.getName());
                        return commonBlock.getVariables().get(elementName);
                    }).filter(v -> v != null).findFirst();
            if (variableOptional.isPresent()) {
                variableOptional.get().addDirection(direction);
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean checkFunction(final FortranOperation operation, final Node expressionElement,
            final String elementName) {
        if (Predicates.isNamedExpressionAccess.test(expressionElement)) {
            // System.err.println("function " + elementName);
            final Node rlt = NodeProcessingUtils.findChildFirst(expressionElement, Predicates.isRLT);
            final Node parensR = NodeProcessingUtils.findChildFirst(rlt, Predicates.isParensR);
            final Node elementLT = NodeProcessingUtils.findChildFirst(parensR, Predicates.isElementLT);
            final List<Node> elements = NodeProcessingUtils.findAllSiblings(elementLT.getFirstChild(),
                    Predicates.isElement, o -> false);
            elements.forEach(element -> this.checkExpression(operation, element));
            return true;
        } else {
            return false;
        }
    }

    private void checkIterator(final FortranOperation operation, final Node iterator) {
        final List<Node> definitions = NodeProcessingUtils.findAllSiblings(iterator, Predicates.isIteratorDefinitionLT,
                o -> false);
        definitions.forEach(definition -> {
            NodeProcessingUtils.findAllSiblings(definition.getFirstChild(), o -> true, o -> false)
                    .forEach(element -> this.checkExpression(operation, element));
        });
    }
}
