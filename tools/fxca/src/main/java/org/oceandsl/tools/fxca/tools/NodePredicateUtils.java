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
public class NodePredicateUtils {

    public static Predicate<Node> isProgramStatement = NodeProcessingUtils.hasName("program-stmt");
    public static Predicate<Node> isEndProgramStatement = NodeProcessingUtils.hasName("end-program-stmt");

    public static Predicate<Node> isSubroutineStatement = NodeProcessingUtils.hasName("subroutine-stmt");
    public static Predicate<Node> isFunctionStatement = NodeProcessingUtils.hasName("function-stmt");
    public static Predicate<Node> isEntryStatement = NodeProcessingUtils.hasName("entry-stmt");
    public static Predicate<Node> isOperationStatement = NodePredicateUtils.isSubroutineStatement
            .or(NodePredicateUtils.isFunctionStatement).or(NodePredicateUtils.isEntryStatement)
            .or(NodePredicateUtils.isProgramStatement);

    public static Predicate<Node> isEndSubroutineStatement = NodeProcessingUtils.hasName("end-subroutine-stmt");
    public static Predicate<Node> isEndFunctionStatement = NodeProcessingUtils.hasName("end-function-stmt");

    public static Predicate<Node> isModuleStatement = NodeProcessingUtils.hasName("module-stmt");
    public static Predicate<Node> isUseStatement = NodeProcessingUtils.hasName("use-stmt");

    public static Predicate<Node> isCallStatement = NodeProcessingUtils.hasName("call-stmt");

    public static Predicate<Node> isProgramName = NodeProcessingUtils.hasName("program-N");
    public static Predicate<Node> isSubroutineName = NodeProcessingUtils.hasName("subroutine-N");
    public static Predicate<Node> isFunctionName = NodeProcessingUtils.hasName("function-N");
    public static Predicate<Node> isEntryName = NodeProcessingUtils.hasName("entry-N");
    public static Predicate<Node> isArgumentName = NodeProcessingUtils.hasName("arg-N");

    public static Predicate<Node> isArgumentSpecification = NodeProcessingUtils.hasName("arg-spec");
    public static Predicate<Node> isArgument = NodeProcessingUtils.hasName("arg");

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
    public static Predicate<Node> isRegularLeftParanthesis = NodePredicateUtils.isParensR
            .and(node -> node.getTextContent().startsWith("("));
    public static Predicate<Node> isCommonStatement = NodeProcessingUtils.hasName("common-stmt");
    public static Predicate<Node> isCommonBlockObjectStatement = NodeProcessingUtils.hasName("common-block-obj-N");
    public static Predicate<Node> isLocalAccess = node -> LocalExpressionAccess.isLocalAccess(node);
    public static Predicate<Node> isDummyArgumentLT = NodeProcessingUtils.hasName("dummy-arg-LT");

    public static Predicate<Node> isNamedExpressionAccess = NodePredicateUtils.isNamedExpression
            .and(NodeProcessingUtils.childSatisfies("0", NodePredicateUtils.isBigN))
            .and(NodeProcessingUtils.childSatisfies("0,0", NodePredicateUtils.isSmallN))
            .and(NodeProcessingUtils.childSatisfies("1", NodePredicateUtils.isRLT))
            .and(NodeProcessingUtils.childSatisfies("1,0", NodePredicateUtils.isRegularLeftParanthesis));

    public static Pair<Predicate<Node>, Predicate<Node>> endFunctionToBeginFunction = new Pair<>(
            NodePredicateUtils.isEndFunctionStatement, NodePredicateUtils.isFunctionStatement);

    public static Pair<Predicate<Node>, Predicate<Node>> endSubroutineToBeginSubroutine = new Pair<>(
            NodePredicateUtils.isEndSubroutineStatement, NodePredicateUtils.isSubroutineStatement);

    public static List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes = List
            .of(NodePredicateUtils.endFunctionToBeginFunction, NodePredicateUtils.endSubroutineToBeginSubroutine);
    public static Predicate<Node> isIfThenStatement = NodeProcessingUtils.hasName("if-then-stmt");
    public static Predicate<Node> isEndIfStatement = NodeProcessingUtils.hasName("end-if-stmt");
    public static Predicate<Node> isSelectCaseStatement = NodeProcessingUtils.hasName("select-case-stmt");
    public static Predicate<Node> isDoStatement = NodeProcessingUtils.hasName("do-stmt");
    public static Predicate<Node> isAssignmentStatement = NodeProcessingUtils.hasName("a-stmt");
    public static Predicate<Node> isENdeclLT = NodeProcessingUtils.hasName("EN-decl-LT");

    public static Predicate<Node> isOpE = NodeProcessingUtils.hasName("op-E");
    public static Predicate<Node> isLiteralE = NodeProcessingUtils.hasName("literal-E");
    public static Predicate<Node> isOp = NodeProcessingUtils.hasName("op");
    public static Predicate<Node> isM = NodeProcessingUtils.hasName("m");
    public static Predicate<Node> isCnt = NodeProcessingUtils.hasName("cnt");
    public static Predicate<Node> isText = node -> !node.getTextContent().isEmpty();

    public static Predicate<Node> isAssignmentE1 = NodeProcessingUtils.hasName("E-1");
    public static Predicate<Node> isAssignmentE2 = NodeProcessingUtils.hasName("E-2");
    public static Predicate<Node> isFile = NodeProcessingUtils.hasName("file");

    public static Predicate<Node> isLowerBound = NodeProcessingUtils.hasName("lower-bound");
    public static Predicate<Node> isUpperBound = NodeProcessingUtils.hasName("upper-bound");
}
