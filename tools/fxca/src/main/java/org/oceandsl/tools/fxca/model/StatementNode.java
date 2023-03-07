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
package org.oceandsl.tools.fxca.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.oceandsl.tools.fxca.tools.ListTools;
import org.oceandsl.tools.fxca.tools.Pair;

// No own attributes, only accessor functions for nodes. Implemented as class in order to allow
// chaining.

/**
 *
 * @author Henning Schnoor
 * @since 1.3.0
 */
public class StatementNode {

    public static Predicate<Node> isSubroutineStatement = StatementNode.hasName("subroutine-stmt");
    public static Predicate<Node> isEndSubroutineStatement = StatementNode.hasName("end-subroutine-stmt");
    public static Predicate<Node> isFunctionStatement = StatementNode.hasName("function-stmt");
    public static Predicate<Node> isOperationStatement = StatementNode.isSubroutineStatement
            .or(StatementNode.isFunctionStatement);
    public static Predicate<Node> isEndFunctionStatement = StatementNode.hasName("end-function-stmt");
    public static Predicate<Node> isModuleStatement = StatementNode.hasName("module-stmt");
    public static Predicate<Node> isUseStatement = StatementNode.hasName("use-stmt");
    public static Predicate<Node> isCallStatement = StatementNode.hasName("call-stmt");
    public static Predicate<Node> isSubroutineName = StatementNode.hasName("subroutine-N");
    public static Predicate<Node> isFunctionName = StatementNode.hasName("function-N");
    public static Predicate<Node> isNamedExpression = StatementNode.hasName("named-E");
    public static Predicate<Node> isBigN = StatementNode.hasName("N");
    public static Predicate<Node> isArgN = StatementNode.hasName("arg-N");
    public static Predicate<Node> isTDeclStmt = StatementNode.hasName("T-decl-stmt");
    public static Predicate<Node> isEnDcl = StatementNode.hasName("EN-decl");
    public static Predicate<Node> isSmallN = StatementNode.hasName("n");
    public static Predicate<Node> isElementLT = StatementNode.hasName("element-LT");
    public static Predicate<Node> isRLT = StatementNode.hasName("R-LT");
    public static Predicate<Node> isElement = StatementNode.hasName("element");
    public static Predicate<Node> isParensR = StatementNode.hasName("parens-R");
    public static Predicate<Node> isRegularLeftParanthesis = StatementNode.isParensR
            .and(node -> node.getTextContent().startsWith("("));
    public static Predicate<Node> isCommonStatement = StatementNode.hasName("common-stmt");
    public static Predicate<Node> isCommonBlockObjectStatement = StatementNode.hasName("common-block-obj-N");
    public static Predicate<Node> isLocalAccess = node -> LocalExpressionAccess.isLocalAccess(node);

    public static Predicate<Node> namedExpressionAccess = StatementNode.isNamedExpression
            .and(StatementNode.childSatisfies("0", StatementNode.isBigN))
            .and(StatementNode.childSatisfies("0,0", StatementNode.isSmallN))
            .and(StatementNode.childSatisfies("1", StatementNode.isRLT))
            .and(StatementNode.childSatisfies("1,0", StatementNode.isRegularLeftParanthesis));

    public static Pair<Predicate<Node>, Predicate<Node>> endFunctionToBeginFunction = new Pair<>(
            StatementNode.isEndFunctionStatement, StatementNode.isFunctionStatement);

    public static Pair<Predicate<Node>, Predicate<Node>> endSubroutineToBeginSubroutine = new Pair<>(
            StatementNode.isEndSubroutineStatement, StatementNode.isSubroutineStatement);

    public static List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes = List
            .of(StatementNode.endFunctionToBeginFunction, StatementNode.endSubroutineToBeginSubroutine);

    public static String nameOfCalledFunction(final Node functionCallNode) {
        return StatementNode.getSuccessorNode(functionCallNode, "0,0").getTextContent();
    }

    public static String nameOfCalledOperation(final Node operationCallNode) {
        return StatementNode.getSuccessorNode(operationCallNode, "1").getTextContent();
    }

    public static Predicate<Node> hasName(final String name) {
        return node -> name.equals(node.getNodeName());
    }

    public static Predicate<Node> hasTextContent(final String content) {
        return node -> content.equals(node.getTextContent());
    }

    public static Predicate<Node> childSatisfies(final String path, final Predicate<Node> predicate) {
        return node -> predicate.test(StatementNode.getSuccessorNode(node, path));
    }

    public static String nodeType(final short nodeType) {
        return switch (nodeType) {
        case Node.ELEMENT_NODE -> "Element Node";
        case Node.ATTRIBUTE_NODE -> "Attribute Node";
        case Node.TEXT_NODE -> "Text Node";
        case Node.CDATA_SECTION_NODE -> "CDATA Section Node";
        case Node.ENTITY_REFERENCE_NODE -> "Entity Reference Node";
        case Node.ENTITY_NODE -> "Entity Node";
        case Node.PROCESSING_INSTRUCTION_NODE -> "Processing Instruction Node";
        case Node.COMMENT_NODE -> "Comment Node";
        case Node.DOCUMENT_NODE -> "Document Node";
        case Node.DOCUMENT_TYPE_NODE -> "Document Type Node";
        case Node.DOCUMENT_FRAGMENT_NODE -> "Document Fragment Node";
        case Node.NOTATION_NODE -> "Notation Node";
        default -> "Unknown Node Type";
        };
    }

    // Convenience functions

    public static int printNode(final Node node, final int depth) {
        String spaces = "";
        for (int i = 0; i < depth; i++) {
            spaces = spaces + " ";
        }
        int numberOfPrintedNodes = 1;
        System.out.println(spaces + "[node type] " + StatementNode.nodeType(node.getNodeType()));
        System.out.println(spaces + "[node name] " + node.getNodeName());
        System.out.println(spaces + "[node value] " + node.getNodeValue());
        System.out.println(spaces + "[node text content] " + node.getTextContent());
        System.out.println(spaces + "[node #kids] " + node.getChildNodes().getLength());

        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            final Node child = node.getChildNodes().item(i);
            numberOfPrintedNodes += StatementNode.printNode(child, depth + 1);
        }
        return numberOfPrintedNodes;
    }

    // Node Search

    // private static boolean testNodeAndFirstChildPredicateChain(Node t, List<Predicate<Node>>
    // predicates) {
    // if (predicates.isEmpty()) {
    // return true;
    // }
    // if (!predicates.get(0).test(t)) {
    // return false;
    // }
    // if (predicates.size() == 1) {
    // return true;
    // }
    // if (t.getChildNodes().getLength() == 0) {
    // return false;
    // }
    // return testNodeAndFirstChildPredicateChain(t.getFirstChild(), predicates.subList(1,
    // predicates.size()));
    // }

    /*
     * static private Predicate<Node> nodeAndfirstChildPredicateChain(List<Predicate<Node>>
     * predicates) { return node -> testNodeAndFirstChildPredicateChain(node, predicates); }
     */

    // TODO this is not doing the right thing
    public static Node getSuccessorNode(final Node node, final String path) {
        final String firstNumber = StringUtils.substringBefore(path, ",");
        final String nextPath = StringUtils.substringAfter(path, ",");
        final int childIndex = Integer.parseInt(firstNumber);
        final NodeList children = node.getChildNodes();
        if (children.getLength() < childIndex) {
            return null;
        }
        if (nextPath.isEmpty()) {
            return node.getChildNodes().item(childIndex);
        } else {
            return StatementNode.getSuccessorNode(node.getChildNodes().item(childIndex), nextPath);
        }
    }

    /*
     * private static ASTNode getFirstChildChain(Node node, int depth) {
     *
     * Node result = node;
     *
     * for (int i = 0; i < depth; i++) { result = result.getFirstChild(); }
     *
     * return new ASTNode(result); }
     */

    // NOTE: Only terminates if nextNode eventually returns null or a matching element.
    private static Node findFirst(final Node parent, final Function<Node, Node> nextNode,
            final Predicate<Node> condition, final boolean includeSelf) {
        return findFirst(parent, nextNode, condition, includeSelf, null);
    }

    // paranthesistypes: contains pairs of "opening paranthesis" and "closed paranthesis"
    // conditions,
    // where stuff between the paranthesis is ignored.
    //
    // Be careful with the search direction here, if you are searching "backwards", then ")" might
    // be your opening, and "(" your closing paranthesis.
    //
    // use case: when finding the containing operation statement, we need to skip functions that
    // are defined "along the way," i.e., where both the "function declaration" and the
    // "end function declaration" elements appear.

    // NOTE: Only terminates if nextNode eventually returns null or a matching element.
    private static Node findFirst(final Node parent, final Function<Node, Node> nextNode,
            final Predicate<Node> condition, final boolean includeSelf,
            final List<Pair<Predicate<Node>, Predicate<Node>>> paranthesesTypes) {

        final List<Node> result = findAll(parent, nextNode, condition, includeSelf, paranthesesTypes, 1);

        return result.isEmpty() ? null : result.get(0);
    }

    public static List<Node> findAll(final Node parent, final Function<Node, Node> nextNode,
            final Predicate<Node> condition, final boolean includeSelf,
            final List<Pair<Predicate<Node>, Predicate<Node>>> paranthesesTypes, final int maxElementsToFind) {

        final List<Node> result = new ArrayList<>();

        final int numberparanthesesTypes = paranthesesTypes == null ? 0 : paranthesesTypes.size();
        final int[] openParanthesis = new int[numberparanthesesTypes];

        Node current = parent;

        boolean inParanthesisInterval = false;
        // End if we do not have anywhere to search, or we have reached the limit (where "-1" counts
        // as "no limit").
        while (current != null && (result.size() < maxElementsToFind || maxElementsToFind == -1)) {

            if (!inParanthesisInterval && condition.test(current) && (current != parent || includeSelf)) {
                result.add(current);
            }

            inParanthesisInterval = false;
            for (int i = 0; i < numberparanthesesTypes; i++) {
                final Predicate<Node> openingParanthesis = paranthesesTypes.get(i).first;
                final Predicate<Node> closingParanthesis = paranthesesTypes.get(i).second;

                if (openingParanthesis.test(current)) {
                    openParanthesis[i]++;
                }

                if (closingParanthesis.test(current)) {
                    openParanthesis[i]--;
                }

                if (openParanthesis[i] > 0) {
                    inParanthesisInterval = true;
                }
            }

            // Check for nextNode-stationaly points
            final Node nextNodeResult = nextNode.apply(current);
            current = current == nextNodeResult ? null : nextNodeResult;
        }

        return result;
    }

    private static boolean hasConnectedWith(final Node parent, final Function<Node, Node> nextNode,
            final Predicate<Node> condition, final boolean includeSelf) {
        return findFirst(parent, nextNode, condition, includeSelf) != null;
    }

    private static boolean hasLeftSibling(final Node parent, final Predicate<Node> condition,
            final boolean includeSelf) {
        return hasConnectedWith(parent, node -> node.getPreviousSibling(), condition, includeSelf);
    }

    private static Node firstLeftSibling(final Node parent, final Predicate<Node> condition, final boolean includeSelf,
            final List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes) {
        return findFirst(parent, node -> node.getPreviousSibling(), condition, includeSelf, paranthesisTypes);
    }

    private static Node firstAncestor(final Node parent, final Predicate<Node> condition, final boolean includeSelf) {
        return findFirst(parent, node -> node.getParentNode(), condition, includeSelf);
    }

    public static Set<Node> allDescendents(final Node node, final Predicate<Node> condition,
            final boolean includeSelf) {
        return addAllDescendentsTo(node, condition, includeSelf, new HashSet<>());
    }

    private static <T extends Collection<Node>> T addAllDescendentsTo(final Node node, final Predicate<Node> condition,
            final boolean includeSelf, final T addToThese) {

        if (condition.test(node) && includeSelf) {
            addToThese.add(node);
        }

        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            final Node child = node.getChildNodes().item(i);
            addAllDescendentsTo(child, condition, true, addToThese);
        }

        return addToThese;
    }

    public static String getNameOfOperation(final Node operationStatement) {

        if (StatementNode.isSubroutineStatement.test(operationStatement)) {
            return StatementNode.getNameOfOperation(operationStatement, StatementNode.isSubroutineName);
        } else if (StatementNode.isFunctionStatement.test(operationStatement)) {
            return StatementNode.getNameOfOperation(operationStatement, StatementNode.isFunctionName);
        }

        throw new IllegalArgumentException("Node is neither a function nor a subroutine statement.");
    }

    public static String getNameOfOperation(final Node operationStatement, final Predicate<Node> namePredicate) {
        final Set<Node> nameNodes = allDescendents(operationStatement, namePredicate, true);
        return ListTools.getUniqueElement(nameNodes).getTextContent();
    }

    public static <T> Set<T> getDescendentAttributes(final Node node, final Predicate<Node> predicate,
            final Function<Node, T> extractAttribute) throws ParserConfigurationException, SAXException, IOException {
        return allDescendents(node, predicate, true).stream().map(extractAttribute).collect(Collectors.toSet());
    }

    public static List<Pair<String, String>> operationCalls(final Node node, final Predicate<Node> callPredicate,
            final Function<Node, String> calledOperation) {
        final Set<Pair<String, String>> result = new HashSet<>(); // Check for double entries
        final Set<Node> callStatements = allDescendents(node, callPredicate, true);
        for (final Node callStatement : callStatements) {
            final String callee = calledOperation.apply(callStatement);
            final String caller = getNameOfContainingOperation(callStatement);
            result.add(new Pair<>(caller, callee));
        }

        return ListTools.ofM(result, Pair.getComparatorFirstSecond());
    }

    public static List<Pair<String, String>> subroutineCalls(final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        return operationCalls(node, StatementNode.isCallStatement.and(StatementNode.isLocalAccess.negate()),
                subroutineCall -> StatementNode.nameOfCalledOperation(subroutineCall));
    }

    public static List<Pair<String, String>> functionCalls(final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        return operationCalls(node, StatementNode.namedExpressionAccess.and(StatementNode.isLocalAccess.negate()),
                functionCall -> StatementNode.nameOfCalledFunction(functionCall));
    }

    /** ---------------------------------- */

    public static Node findContainingStatement(final Node parent, final Predicate<Node> condition) {
        return findContainingStatement(parent, condition, null);
    }

    public static Node findContainingStatement(final Node parent, final Predicate<Node> condition,
            final List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes) {

        final Predicate<Node> hasSuchANodeAsLeftSibling = node -> hasLeftSibling(node, condition, false);
        final Node siblingOfSuchNode = firstAncestor(parent, hasSuchANodeAsLeftSibling, !condition.test(parent));

        if (siblingOfSuchNode == null) {
            return null;
        }

        return firstLeftSibling(siblingOfSuchNode, condition, true, paranthesisTypes);
    }

    private static String getNameOfContainingOperation(final Node node) {

        final Node containingOperationStatement = findContainingStatement(node, StatementNode.isOperationStatement,
                StatementNode.paranthesisTypes);
        return containingOperationStatement == null ? "<root>"
                : StatementNode.getNameOfOperation(containingOperationStatement);
    }

    // We make some assumptions about the structure of the Fortran files (and the generated XML
    // representations).
    // If we hit a node that does not satisfy these assumptions, throw an exception so that we know
    // we need to
    // handle that case as well.
    public static boolean satisfiesAssumptions(final Node node) {

        if (node == null) {
            return true;
        }

        final short type = node.getNodeType();

        if (type == Node.TEXT_NODE && node.getChildNodes().getLength() > 0) {
            throw new IllegalArgumentException("text node with children");
        }

        if ("call-stmt".equals(node.getNodeName()) && node.getChildNodes().getLength() < 2) {
            StatementNode.printNode(node, 0);
            throw new IllegalArgumentException("call statement with < 2 children");
        }

        if (StatementNode.isNamedExpression.test(node)) {
            // NodeList children = node.getChildNodes();
            final Node firstChild = node.getFirstChild();
            final Node firstGrandChild = firstChild.getFirstChild();
            if (!StatementNode.isBigN.test(firstChild)) {
                throw new IllegalArgumentException("named expression with unexpected type of first child.");
            }

            if (!StatementNode.isSmallN.test(firstGrandChild)) {
                throw new IllegalArgumentException("named expression with unexpected type of first grandchild.");
            }

            if (firstGrandChild.getChildNodes().getLength() > 1) {
                StatementNode.printNode(firstGrandChild, 0);
                throw new IllegalArgumentException(
                        "named expression with unexpected chlildren length list of first grandchild.");
            }
        }

        return true;
    }

    public int getNumberOfDescendants(final Node parent, final boolean countSelf) {
        return allDescendents(parent, node -> true, countSelf).size();
    }

}
