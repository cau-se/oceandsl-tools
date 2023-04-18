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

import org.oceandsl.tools.fxca.model.EDirection;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranParameter;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.model.FortranVariable;
import org.oceandsl.tools.fxca.stages.XPathParser;
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;
import org.oceandsl.tools.fxca.tools.Predicates;

/**
 * @author Reiner Jung
 * @since 1.3.0
 */
public class ComputeDirectionalityOfParametersStage extends AbstractTransformation<FortranProject, FortranProject> {

    @Override
    protected void execute(final FortranProject project) throws Exception {
        project.getModules().values().forEach(module -> module.getOperations().values()
                .forEach(operation -> this.computeParameterDirectionality(module, operation)));
        this.logger.info("Computed parameter directionality and variable dependencies.");
        this.outputPort.send(project);
    }

    private void computeParameterDirectionality(final FortranModule module, final FortranOperation operation) {
        final List<Node> statements = NodeProcessingUtils.findAllSiblings(operation.getNode(), o -> true,
                Predicates.isEndSubroutineStatement.or(Predicates.isEndProgramStatement)
                        .or(Predicates.isEndFunctionStatement));
        statements.forEach(statement -> {
            if (Predicates.isAssignmentStatement.test(statement)) {
                this.checkAssignment(operation, statement);
            } else if (Predicates.isCallStatement.test(statement)) {
                this.checkCall(operation, statement);
            } else if (Predicates.isIfThenStatement.test(statement)) {
                this.checkIfThen(operation, statement);
            } else if (Predicates.isIfStatement.test(statement)) {
                this.checkIf(operation, statement);
            } else if (Predicates.isElseIfStatement.test(statement)) {
                this.checkIfThen(operation, statement);
            } else if (Predicates.isDoStatement.or(Predicates.isDoLabelStatement).test(statement)) {
                this.checkDoStatement(module, operation, statement);
            } else if (Predicates.isReadStatement.test(statement)) {
                this.checkReadStatement(operation, statement);
            } else if (Predicates.isWriteStatement.test(statement)) {
                this.checkWriteStatement(operation, statement);
            } else if (Predicates.isSaveStatement.test(statement)) {
                this.checkSaveStatement(module, operation, statement);
            } else if (Predicates.isDataStatement.test(statement)) {
                this.checkDataStatement(operation, statement);
            } else if (Predicates.isPrintStatement.test(statement)) {
                this.checkPrintStatement(operation, statement);
            } else if (Predicates.isWhereStatement.test(statement)) {
                this.checkWhereStatement(operation, statement);
            } else if (Predicates.isCloseStatement.test(statement)) {
                this.checkCloseStatement(operation, statement);
            } else if (Predicates.isOpenStatement.test(statement)) {
                this.checkOpenStatement(operation, statement);
            } else if (Predicates.isDIMStatement.test(statement)) {
                this.checkDIMStatement(module, operation, statement);
            } else if (Predicates.isEndFileStatement.test(statement)) {
                this.checkEndFileStatement(operation, statement);
            } else if (Predicates.isNamelistStatement.test(statement)) {
                this.checkNamelistStatement(operation, statement);
            } else if (Predicates.isImplicitNoneStmt.test(statement)) {
                operation.setImplicit(false);
            } else if (Predicates.isM.or(Predicates.isC).or(Predicates.isTDeclStmt).or(Predicates.isFile)
                    .or(Predicates.isInclude).or(Predicates.isOperationStatement).or(Predicates.isEndStatement)
                    .or(Predicates.isGotoStatement).or(Predicates.isLabel).or(Predicates.isContinueStatement)
                    .or(Predicates.isFormatStatement).or(Predicates.isElseStatement).or(Predicates.isReturnStatement)
                    .or(Predicates.isRewindStatement).or(Predicates.isStopStatement).or(Predicates.isAllocateStatement)
                    .or(Predicates.isDeallocateStatement).or(Predicates.isInquireStatement)
                    .or(Predicates.isParameterStatement).or(Predicates.isCommonStatement).or(Predicates.isExitStatement)
                    .test(statement)) {
                // ignore
            } else if (statement.getNodeType() == Node.TEXT_NODE) {
                // ignore text
            } else {
                this.logger.warn("Unknown statement {} ", statement.toString());
            }
        });
    }

    private void checkOpenStatement(final FortranOperation operation, final Node sibling) {
        // no dataflow
    }

    private void checkDIMStatement(final FortranModule module, final FortranOperation operation,
            final Node dimStatement) {
        final List<Node> enDecls = NodeProcessingUtils.findAllSiblings(dimStatement.getFirstChild(),
                Predicates.isENDeclLT, o -> false);
        enDecls.forEach(enDecl -> this.checkEnDecl(module, operation, enDecl));
    }

    private void checkEnDecl(final FortranModule module, final FortranOperation operation, final Node enDecl) {
        final Node enN = NodeProcessingUtils.findChildFirst(enDecl, Predicates.isEnN);
        if (enN != null) {
            final Node bigN = NodeProcessingUtils.findChildFirst(enN, Predicates.isBigN);
            final Node smallN = NodeProcessingUtils.findChildFirst(bigN, Predicates.isSmallN);
            this.checkVariable(operation, smallN.getTextContent(), EDirection.READ);

            NodeProcessingUtils.findAllSiblings(enN, Predicates.isArraySpecification, o -> false)
                    .forEach(arraySpecification -> {
                        final Node shapeSpecLT = NodeProcessingUtils.findChildFirst(arraySpecification,
                                Predicates.isShapeSpecLT);
                        NodeProcessingUtils
                                .findAllSiblings(shapeSpecLT.getFirstChild(), Predicates.isShapeSpec, o -> false)
                                .forEach(shapeSpec -> this.checkLimits(module, operation, null, shapeSpec));
                    });
        }
    }

    private void checkEndFileStatement(final FortranOperation operation, final Node sibling) {
        // no dataflow
    }

    private void checkNamelistStatement(final FortranOperation operation, final Node namelistStatement) {
        final Node namelistGroupObjLT = NodeProcessingUtils.findChildFirst(namelistStatement,
                Predicates.isNamelistGroupObjLT);
        NodeProcessingUtils.findAllSiblings(namelistGroupObjLT, Predicates.isNamelistGroupObj, o -> false)
                .forEach(namelistGroupObj -> this.checkNamelistGroupObj(operation, namelistGroupObj));
    }

    private void checkNamelistGroupObj(final FortranOperation operation, final Node namelistGroupObj) {
        final String elementName = NodeProcessingUtils.getName(namelistGroupObj);
        final FortranParameter parameter = operation.getParameters().get(elementName);
        if (parameter != null) {
            parameter.addDirection(EDirection.WRITE);
        } else {
            final FortranVariable variable = operation.getVariables().get(elementName);
            variable.addDirection(EDirection.WRITE);
        }
    }

    private void checkCloseStatement(final FortranOperation operation, final Node sibling) {
        // no dataflow
    }

    private void checkAssignment(final FortranOperation operation, final Node assignment) {
        this.checkAssignee(operation, assignment);
        this.checkAssignmentExpression(operation, assignment);
    }

    private void checkAssignee(final FortranOperation operation, final Node assignment) {
        final String name = NodeProcessingUtils.getName(XPathParser.getAssigmentVariable(assignment));
        final FortranParameter parameter = operation.getParameters().get(name);
        if (parameter != null) {
            parameter.addDirection(EDirection.WRITE);
        }
        final FortranVariable variable = operation.getVariables().get(name);
        if (variable != null) {
            variable.addDirection(EDirection.WRITE);
        }
    }

    private void checkAssignmentExpression(final FortranOperation operation, final Node assignment) {
        final Node node = XPathParser.getAssignmentExpression(assignment);
        if (Predicates.isNamedExpression.test(node)) {
            // System.err.println(" named expression " + NodeProcessingUtils.getName(node));
        } else if (Predicates.isAssignmentE2.test(node)) {
            this.checkExpression(operation, node, EDirection.READ);
        } else {
            this.logger.warn("Unknown expression type {}", node.toString());
        }
    }

    private void checkDoStatement(final FortranModule module, final FortranOperation operation,
            final Node doStatement) {
        final Node doV = NodeProcessingUtils.findChildFirst(doStatement, Predicates.isDoV);
        if (doV == null) { // do while
            final Node testE = NodeProcessingUtils.findChildFirst(doStatement, Predicates.isTestExpression);
            this.checkExpression(operation, testE, EDirection.READ);
        } else { // do statement or do label statement
            final String elementName = NodeProcessingUtils.getName(doV);
            FortranVariable loopVariable = this.checkVariable(operation, elementName, EDirection.WRITE);
            if (!this.checkParameter(operation, elementName) && (loopVariable == null)) {
                if (!operation.isImplicit()) {
                    this.logger.error("Unknown value assignee {} after do statement in {}::{}", elementName,
                            module.getFileName(), operation.getName());
                } else {
                    loopVariable = new FortranVariable(elementName);
                    loopVariable.setDirection(EDirection.WRITE);
                    operation.getVariables().put(elementName, loopVariable);
                }
            }
            this.checkLimits(module, operation, loopVariable, doStatement);
        }
    }

    private void checkLimits(final FortranModule module, final FortranOperation operation,
            final FortranVariable variable, final Node range) {
        final Node lowerBound = NodeProcessingUtils.findChildFirst(range, Predicates.isLowerBound);
        final Node upperBound = NodeProcessingUtils.findChildFirst(range, Predicates.isUpperBound);
        if (lowerBound != null) {
            this.checkExpression(operation, lowerBound, EDirection.READ);
            if (variable != null) {
                this.linkVariables(module, operation, variable, lowerBound);
            }
        }
        if (upperBound != null) {
            this.checkExpression(operation, upperBound, EDirection.READ);
            if (variable != null) {
                this.linkVariables(module, operation, variable, upperBound);
            }
        }
    }

    private void linkVariables(final FortranModule module, final FortranOperation operation,
            final FortranVariable variable, final Node bound) {
        NodeProcessingUtils.allDescendents(bound, Predicates.isNamedExpression, true).stream().forEach(element -> {
            final String elementName = NodeProcessingUtils.getName(element);
            final FortranVariable sourceVariable = operation.getVariables().get(elementName);
            if (sourceVariable != null) {
                variable.getSources().add(sourceVariable);
            }
            final FortranVariable sourceModuleVariable = module.getVariables().get(elementName);
            if (sourceModuleVariable != null) {
                variable.getSources().add(sourceModuleVariable);
            }
            final FortranParameter sourceParameter = operation.getParameters().get(elementName);
            if (sourceParameter != null) {
                variable.getSources().add(sourceParameter);
            }
        });
    }

    private void checkReadStatement(final FortranOperation operation, final Node readStatement) {
        final Node controls = NodeProcessingUtils.findChildFirst(readStatement, Predicates.isIOControlSpec);
        if (controls != null) {
            NodeProcessingUtils.findAllSiblings(controls.getFirstChild(), Predicates.isIOControl, o -> false)
                    .forEach(value -> this.checkExpression(operation, value, EDirection.READ));
        }

        final Node outputs = NodeProcessingUtils.findChildFirst(readStatement, Predicates.isInputItemLT);
        if (outputs != null) {
            NodeProcessingUtils.findAllSiblings(outputs.getFirstChild(), Predicates.isInputItem, o -> false).forEach(
                    value -> this.checkVariable(operation, NodeProcessingUtils.getName(value), EDirection.WRITE));
        }
    }

    private void checkWriteStatement(final FortranOperation operation, final Node writeStatement) {
        final Node controls = NodeProcessingUtils.findChildFirst(writeStatement, Predicates.isIOControlSpec);
        if (controls != null) {
            NodeProcessingUtils.findAllSiblings(controls.getFirstChild(), Predicates.isIOControl, o -> false)
                    .forEach(value -> this.checkExpression(operation, value, EDirection.READ));
        }

        final Node outputs = NodeProcessingUtils.findChildFirst(writeStatement, Predicates.isOutputItemLT);
        if (outputs != null) {
            NodeProcessingUtils.findAllSiblings(outputs.getFirstChild(), Predicates.isOutputItem, o -> false).forEach(
                    value -> this.checkVariable(operation, NodeProcessingUtils.getName(value), EDirection.READ));
        }
    }

    private void checkPrintStatement(final FortranOperation operation, final Node printStatement) {
        final Node outputs = NodeProcessingUtils.findChildFirst(printStatement, Predicates.isOutputItemLT);
        if (outputs != null) {
            NodeProcessingUtils.findAllSiblings(outputs.getFirstChild(), Predicates.isOutputItem, o -> false).forEach(
                    value -> this.checkVariable(operation, NodeProcessingUtils.getName(value), EDirection.READ));
        }
    }

    private void checkSaveStatement(final FortranModule module, final FortranOperation operation,
            final Node saveStatement) {
        final Node savedEnLt = NodeProcessingUtils.findChildFirst(saveStatement, Predicates.isSavedEnLT);
        NodeProcessingUtils.findAllSiblings(savedEnLt.getFirstChild(), Predicates.isSavedEn, o -> false)
                .forEach(savedEn -> this.checkEnDecl(module, operation, savedEn));
    }

    private void checkWhereStatement(final FortranOperation operation, final Node whereStatement) {
        final Node maskExpression = NodeProcessingUtils.findChildFirst(whereStatement, Predicates.isMaskExpression);
        this.checkExpression(operation, maskExpression, EDirection.READ);
        this.checkActionStatement(operation,
                NodeProcessingUtils.findChildFirst(whereStatement, Predicates.isActionStatement));
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
        this.checkExpression(operation, condition, EDirection.READ);
    }

    private void checkIf(final FortranOperation operation, final Node ifStatement) {
        final Node condition = ifStatement.getFirstChild().getNextSibling();
        this.checkExpression(operation, condition, EDirection.READ);
        this.checkActionStatement(operation,
                NodeProcessingUtils.findChildFirst(ifStatement, Predicates.isActionStatement));
    }

    private void checkActionStatement(final FortranOperation operation, final Node actionStatement) {
        NodeProcessingUtils
                .findAllSiblings(actionStatement.getFirstChild(), Predicates.isAssignmentStatement, o -> false)
                .forEach(assignment -> this.checkAssignment(operation, assignment));
    }

    private void checkCall(final FortranOperation operation, final Node call) {
        final String calleeName = NodeProcessingUtils.getName(call.getFirstChild().getNextSibling().getFirstChild());
        final FortranOperation callee = this
                .findOperation((FortranProject) ((FortranModule) operation.getParent()).getParent(), calleeName);

        if (callee != null) {
            final Node argumentSpecification = NodeProcessingUtils.findChildFirst(call,
                    Predicates.isArgumentSpecification);
            if (argumentSpecification != null) {
                final List<Node> arguments = NodeProcessingUtils.findAllSiblings(argumentSpecification.getFirstChild(),
                        Predicates.isArgument, o -> false);
                for (int i = 0; i < arguments.size(); i++) {
                    final Node argument = arguments.get(i);
                    final FortranParameter parameter = this.findParameter(callee, i);
                    this.checkExpression(operation, argument, parameter.getDirection());
                }
            }
        } else {
            this.logger.warn("Missing subroutine, function or library function {}", calleeName);
        }
    }

    private FortranParameter findParameter(final FortranOperation operation, final int i) {
        final Optional<FortranParameter> parameterOptional = operation.getParameters().values().stream()
                .filter(parameter -> parameter.getPosition() == i).findFirst();
        if (parameterOptional.isPresent()) {
            return parameterOptional.get();
        } else {
            return null;
        }
    }

    private FortranOperation findOperation(final FortranProject project, final String operationName) {
        for (final FortranModule module : project.getModules().values()) {
            for (final FortranOperation operation : module.getOperations().values()) {
                if (operation.getName().equals(operationName)) {
                    return operation;
                }
            }
        }
        return null;
    }

    /**
     * check dataflow in expressions.
     *
     * @param operation
     *            operation context
     * @param node
     *            expression node
     * @param direction
     *            direction of variable value access
     */
    private void checkExpression(final FortranOperation operation, final Node node, final EDirection direction) {
        final List<Node> expression = NodeProcessingUtils.findAllSiblings(node.getFirstChild(), o -> true, o -> false);
        expression.forEach(expressionElement -> {
            if (Predicates.isNamedExpression.test(expressionElement)) {
                final String elementName = NodeProcessingUtils.getName(expressionElement);
                this.checkVariable(operation, elementName, direction);
                this.checkParameter(operation, elementName);
                this.checkFunction(operation, expressionElement, elementName);
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
                this.checkExpression(operation, expressionElement, EDirection.READ);
            } else if (Predicates.isParensExpression.test(expressionElement)) {
                this.checkExpression(operation, expressionElement, EDirection.READ);
            } else if (Predicates.isIterator.test(expressionElement)) {
                this.checkIterator(operation, expressionElement);
            } else if (Predicates.isArgumentName.test(expressionElement)) {
                this.checkArgumentName(operation, expressionElement);
            } else if (Predicates.isArrayConstructorExpression.test(expressionElement)) {
                this.checkArrayConstructorExpression(operation, expressionElement, direction);
            } else if (Predicates.isC.or(Predicates.isLabel).test(expressionElement)) {
                // ignore
            } else if (expressionElement.getNodeType() == Node.TEXT_NODE) {
                // System.err.println(" text node " + expressionElement.getTextContent());
            } else {
                this.logger.error("Unknown expression element {}", expressionElement.toString());
            }
        });
    }

    private void checkArrayConstructorExpression(final FortranOperation operation, final Node expression,
            final EDirection direction) {
        final Node acValueLT = NodeProcessingUtils.findChildFirst(expression, Predicates.isAcValueLT);
        NodeProcessingUtils.findAllSiblings(acValueLT.getFirstChild(), Predicates.isAcValue, o -> false)
                .forEach(value -> this.checkExpression(operation, value, direction));
    }

    private void checkArgumentName(final FortranOperation operation, final Node expression) {
        // TODO we do not know how to handle argument names in read and write statements
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

    private FortranVariable checkVariable(final FortranOperation operation, final String elementName,
            final EDirection direction) {
        if (operation.getVariables().containsKey(elementName)) {
            final FortranVariable variable = operation.getVariables().get(elementName);
            variable.addDirection(direction);
            return variable;
        } else if (((FortranModule) operation.getParent()).getVariables().containsKey(elementName)) {
            final FortranVariable variable = ((FortranModule) operation.getParent()).getVariables().get(elementName);
            variable.addDirection(direction);
            return variable;
        } else {
            final Optional<FortranVariable> variableOptional = ((FortranModule) operation.getParent()).getCommonBlocks()
                    .values().stream().map(commonBlock -> {
                        return commonBlock.getVariables().get(elementName);
                    }).filter(v -> v != null).findFirst();
            if (variableOptional.isPresent()) {
                variableOptional.get().addDirection(direction);
                return variableOptional.get();
            } else {
                return null;
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
            elements.forEach(element -> this.checkExpression(operation, element, EDirection.READ));
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
                    .forEach(element -> this.checkExpression(operation, element, EDirection.READ));
        });
    }
}
