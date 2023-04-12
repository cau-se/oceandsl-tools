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
package org.oceandsl.tools.fxca.tools;

import java.util.List;
import java.util.function.Predicate;

import org.w3c.dom.Node;

/**
 * Predicates for node selection and attribute checks.
 *
 * @author Henning Schnoor
 * @author Reiner Jung -- refactoring
 *
 * @since 1.3.0
 */
public class Predicates {

    public static Predicate<Node> isProgramStatement = NodeProcessingUtils.hasName("program-stmt");
    public static Predicate<Node> isEndProgramStatement = NodeProcessingUtils.hasName("end-program-stmt");

    public static Predicate<Node> isSubroutineStatement = NodeProcessingUtils.hasName("subroutine-stmt");
    public static Predicate<Node> isFunctionStatement = NodeProcessingUtils.hasName("function-stmt");
    public static Predicate<Node> isEntryStatement = NodeProcessingUtils.hasName("entry-stmt");
    public static Predicate<Node> isOperationStatement = Predicates.isSubroutineStatement
            .or(Predicates.isFunctionStatement).or(Predicates.isEntryStatement).or(Predicates.isProgramStatement);

    public static Predicate<Node> isEndSubroutineStatement = NodeProcessingUtils.hasName("end-subroutine-stmt");
    public static Predicate<Node> isEndFunctionStatement = NodeProcessingUtils.hasName("end-function-stmt");

    public static Predicate<Node> isModuleStatement = NodeProcessingUtils.hasName("module-stmt");
    public static Predicate<Node> isUseStatement = NodeProcessingUtils.hasName("use-stmt");

    public static Predicate<Node> isImplicitNoneStmt = NodeProcessingUtils.hasName("implicit-none-stmt");

    public static Predicate<Node> isCallStatement = NodeProcessingUtils.hasName("call-stmt");
    public static Predicate<Node> isC = NodeProcessingUtils.hasName("C");
    public static Predicate<Node> isInclude = NodeProcessingUtils.hasName("include");

    public static Predicate<Node> isProgramName = NodeProcessingUtils.hasName("program-N");
    public static Predicate<Node> isSubroutineName = NodeProcessingUtils.hasName("subroutine-N");
    public static Predicate<Node> isFunctionName = NodeProcessingUtils.hasName("function-N");
    public static Predicate<Node> isEntryName = NodeProcessingUtils.hasName("entry-N");

    public static Predicate<Node> isArgumentSpecification = NodeProcessingUtils.hasName("arg-spec");
    public static Predicate<Node> isArgument = NodeProcessingUtils.hasName("arg");
    public static Predicate<Node> isArgumentName = NodeProcessingUtils.hasName("arg-N");

    public static Predicate<Node> isNamedExpression = NodeProcessingUtils.hasName("named-E");
    public static Predicate<Node> isBigN = NodeProcessingUtils.hasName("N");
    public static Predicate<Node> isTDeclStmt = NodeProcessingUtils.hasName("T-decl-stmt");
    public static Predicate<Node> isDimStmt = NodeProcessingUtils.hasName("DIM-stmt");
    public static Predicate<Node> isEnDcl = NodeProcessingUtils.hasName("EN-decl");
    public static Predicate<Node> isSmallN = NodeProcessingUtils.hasName("n");
    public static Predicate<Node> isElementLT = NodeProcessingUtils.hasName("element-LT");
    public static Predicate<Node> isRLT = NodeProcessingUtils.hasName("R-LT");
    public static Predicate<Node> isElement = NodeProcessingUtils.hasName("element");
    public static Predicate<Node> isParensR = NodeProcessingUtils.hasName("parens-R");
    public static Predicate<Node> isRegularLeftParanthesis = Predicates.isParensR
            .and(node -> node.getTextContent().startsWith("("));
    public static Predicate<Node> isCommonStatement = NodeProcessingUtils.hasName("common-stmt");
    public static Predicate<Node> isCommonBlockObjectStatement = NodeProcessingUtils.hasName("common-block-obj-N");
    public static Predicate<Node> isLocalAccess = node -> LocalExpressionAccess.isLocalAccess(node);
    public static Predicate<Node> isDummyArgumentLT = NodeProcessingUtils.hasName("dummy-arg-LT");

    public static Predicate<Node> isNamedExpressionAccess = Predicates.isNamedExpression
            .and(NodeProcessingUtils.childSatisfies("0", Predicates.isBigN))
            .and(NodeProcessingUtils.childSatisfies("0,0", Predicates.isSmallN))
            .and(NodeProcessingUtils.childSatisfies("1", Predicates.isRLT))
            .and(NodeProcessingUtils.childSatisfies("1,0", Predicates.isRegularLeftParanthesis));

    public static Pair<Predicate<Node>, Predicate<Node>> endFunctionToBeginFunction = new Pair<>(
            Predicates.isEndFunctionStatement, Predicates.isFunctionStatement);

    public static Pair<Predicate<Node>, Predicate<Node>> endSubroutineToBeginSubroutine = new Pair<>(
            Predicates.isEndSubroutineStatement, Predicates.isSubroutineStatement);

    public static List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes = List
            .of(Predicates.endFunctionToBeginFunction, Predicates.endSubroutineToBeginSubroutine);
    public static Predicate<Node> isIfThenStatement = NodeProcessingUtils.hasName("if-then-stmt");
    public static Predicate<Node> isElseIfStatement = NodeProcessingUtils.hasName("else-if-stmt");

    public static Predicate<Node> isIfStatement = NodeProcessingUtils.hasName("if-stmt");

    public static Predicate<Node> isEndIfStatement = NodeProcessingUtils.hasName("end-if-stmt");
    public static Predicate<Node> isSelectCaseStatement = NodeProcessingUtils.hasName("select-case-stmt");
    public static Predicate<Node> isDoStatement = NodeProcessingUtils.hasName("do-stmt");
    public static Predicate<Node> isEndDoStatement = NodeProcessingUtils.hasName("end-do-stmt");
    public static Predicate<Node> isAssignmentStatement = NodeProcessingUtils.hasName("a-stmt");
    public static Predicate<Node> isENDeclLT = NodeProcessingUtils.hasName("EN-decl-LT");

    public static Predicate<Node> isOperandExpression = NodeProcessingUtils.hasName("op-E");
    public static Predicate<Node> isLiteralE = NodeProcessingUtils.hasName("literal-E");
    public static Predicate<Node> isStringE = NodeProcessingUtils.hasName("string-E");
    public static Predicate<Node> isOperand = NodeProcessingUtils.hasName("op");
    public static Predicate<Node> isParensE = NodeProcessingUtils.hasName("parens-E");
    public static Predicate<Node> isM = NodeProcessingUtils.hasName("m");
    public static Predicate<Node> isCnt = NodeProcessingUtils.hasName("cnt");
    public static Predicate<Node> isText = node -> !node.getTextContent().isEmpty();

    public static Predicate<Node> isIterator = NodeProcessingUtils.hasName("iterator");
    public static Predicate<Node> isIteratorDefinitionLT = NodeProcessingUtils.hasName("iterator-definition-LT");

    public static Predicate<Node> isAssignmentE1 = NodeProcessingUtils.hasName("E-1");
    public static Predicate<Node> isAssignmentE2 = NodeProcessingUtils.hasName("E-2");
    public static Predicate<Node> isFile = NodeProcessingUtils.hasName("file");

    public static Predicate<Node> isLowerBound = NodeProcessingUtils.hasName("lower-bound");
    public static Predicate<Node> isUpperBound = NodeProcessingUtils.hasName("upper-bound");
    public static Predicate<Node> isEndStatement = Predicates.isEndFunctionStatement.or(Predicates.isEndIfStatement)
            .or(Predicates.isEndProgramStatement).or(Predicates.isEndSubroutineStatement)
            .or(Predicates.isEndDoStatement);

    public static Predicate<Node> isSaveStatement = NodeProcessingUtils.hasName("save-stmt");
    public static Predicate<Node> isDataStatement = NodeProcessingUtils.hasName("data-stmt");
    public static Predicate<Node> isDataStatementSet = NodeProcessingUtils.hasName("data-stmt-set");
    public static Predicate<Node> isDataStatementObjectLT = NodeProcessingUtils.hasName("data-stmt-obj-LT");
    public static Predicate<Node> isDataStatementObject = NodeProcessingUtils.hasName("data-stmt-obj");
    public static Predicate<Node> isDataStatementValueLT = NodeProcessingUtils.hasName("data-stmt-value-LT");
    public static Predicate<Node> isDataStatementValue = NodeProcessingUtils.hasName("data-stmt-value");
    public static Predicate<Node> isDataStatementConstant = NodeProcessingUtils.hasName("data-stmt-constant");

    public static Predicate<Node> isReturnStatement = NodeProcessingUtils.hasName("return-stmt");
    public static Predicate<Node> isWhereStatement = NodeProcessingUtils.hasName("where-stmt");
    public static Predicate<Node> isPrintStatement = NodeProcessingUtils.hasName("print-stmt");

    public static Predicate<Node> isWriteStatement = NodeProcessingUtils.hasName("write-stmt");
    public static Predicate<Node> isLabel = NodeProcessingUtils.hasName("label");
    public static Predicate<Node> isFormatStatement = NodeProcessingUtils.hasName("format-stmt");
    public static Predicate<Node> isRewindStatement = NodeProcessingUtils.hasName("rewind-stmt");
    public static Predicate<Node> isContinueStatement = NodeProcessingUtils.hasName("continue-stmt");
    public static Predicate<Node> isGotoStatement = NodeProcessingUtils.hasName("goto-stmt");
    public static Predicate<Node> isIOControlSpec = NodeProcessingUtils.hasName("io-control-spec");
    public static Predicate<Node> isIOControl = NodeProcessingUtils.hasName("io-control");
    public static Predicate<Node> isOutputItemLT = NodeProcessingUtils.hasName("output-item-LT");
    public static Predicate<Node> isOutputItem = NodeProcessingUtils.hasName("output-item");

    public static Predicate<Node> isReadStatement = NodeProcessingUtils.hasName("read-stmt");
    public static Predicate<Node> isInputItemLT = NodeProcessingUtils.hasName("input-item-LT");
    public static Predicate<Node> isInputItem = NodeProcessingUtils.hasName("input-item");

    public static Predicate<Node> isStopStatement = NodeProcessingUtils.hasName("stop-stmt");

    public static Predicate<Node> isActionStatement = NodeProcessingUtils.hasName("action-stmt");
    public static Predicate<Node> isElseStatement = NodeProcessingUtils.hasName("else-stmt");
    public static Predicate<Node> isSavedEnLT = NodeProcessingUtils.hasName("saved-EN-LT");
    public static Predicate<Node> isSavedEn = NodeProcessingUtils.hasName("saved-EN");
    public static Predicate<Node> isEnN = NodeProcessingUtils.hasName("EN-N");
    public static Predicate<Node> isMaskExpression = NodeProcessingUtils.hasName("mask-E");
    public static Predicate<Node> isAllocateStatement = NodeProcessingUtils.hasName("allocate-stmt");
    public static Predicate<Node> isDeallocateStatement = NodeProcessingUtils.hasName("deallocate-stmt");

    public static Predicate<Node> isParameterStatement = NodeProcessingUtils.hasName("parameter-stmt");
    public static Predicate<Node> isInquireStatement = NodeProcessingUtils.hasName("inquire-stmt");
    public static Predicate<Node> isArrayConstructorExpression = NodeProcessingUtils.hasName("array-constructor-E");
    public static Predicate<Node> isAcValueLT = NodeProcessingUtils.hasName("ac-value-LT");
    public static Predicate<Node> isAcValue = NodeProcessingUtils.hasName("ac-value");

    public static Predicate<Node> isCloseStatement = NodeProcessingUtils.hasName("close-stmt");
    public static Predicate<Node> isDIMStatement = NodeProcessingUtils.hasName("DIM-stmt");
    public static Predicate<Node> isDoLabelStatement = NodeProcessingUtils.hasName("do-label-stmt");
    public static Predicate<Node> isEndFileStatement = NodeProcessingUtils.hasName("end-file-stmt");
    public static Predicate<Node> isExitStatement = NodeProcessingUtils.hasName("exit-stmt");
    public static Predicate<Node> isNamelistStatement = NodeProcessingUtils.hasName("namelist-stmt");
    public static Predicate<Node> isOpenStatement = NodeProcessingUtils.hasName("open-stmt");

    public static Predicate<Node> isArraySpecification = NodeProcessingUtils.hasName("array-spec");
    public static Predicate<Node> isShapeSpecLT = NodeProcessingUtils.hasName("shape-spec-LT");
    public static Predicate<Node> isShapeSpec = NodeProcessingUtils.hasName("shape-spec");
    public static Predicate<Node> isDoV = NodeProcessingUtils.hasName("do-V");

    public static Predicate<Node> isNamelistGroupObjLT = NodeProcessingUtils.hasName("namelist-group-obj-LT");
    public static Predicate<Node> isNamelistGroupObj = NodeProcessingUtils.hasName("namelist-group-obj");
    public static Predicate<Node> isNamelistGroupObjN = NodeProcessingUtils.hasName("namelist-group-obj-N");
    public static Predicate<Node> isTestExpression = NodeProcessingUtils.hasName("test-E");;
}
