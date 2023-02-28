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
package cau.agse.hs.staticfortran.model;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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

import cau.agse.hs.tools.DataStructureTools;
import cau.agse.hs.utils.lists.iterators.IndexIterator;
import cau.agse.hs.utils.misc.Pair;
import lombok.experimental.Delegate;

// No own attributes, only accessor functions for nodes. Implemented as class in order to allow
// chaining.

/**
 *
 * @author Henning Schnoor
 * @since 1.3.0
 */
public class StatementNode implements Node {

    @Delegate(types = Node.class)
    private final Node node;

    @Override
    public String getNodeName() {
        return (this.node == null) ? null : this.node.getNodeName();
    }

    public static Predicate<Node> isSubroutineStatement = StatementNode.hasName("subroutine-stmt");
    public static Predicate<Node> isEndSubroutineStatement = StatementNode.hasName("end-subroutine-stmt");
    public static Predicate<Node> isFunctionStatement = StatementNode.hasName("function-stmt");
    public static Predicate<Node> isEndFunctionStatement = StatementNode.hasName("end-function-stmt");
    public static Predicate<Node> isModuleStatement = StatementNode.hasName("module-stmt");
    public static Predicate<Node> isUseStatement = StatementNode.hasName("use-stmt");
    public static Predicate<Node> isCallStatement = StatementNode.hasName("call-stmt");
    public static Predicate<Node> isSubroutineName = StatementNode.hasName("subroutine-N");
    public static Predicate<Node> isFunctionName = StatementNode.hasName("function-N");
    public static Predicate<Node> isNamedExpression = StatementNode.hasName("named-E");
    public static Predicate<Node> isBigN = StatementNode.hasName("N");
    public static Predicate<Node> isSmallN = StatementNode.hasName("n");
    public static Predicate<Node> isElementLT = StatementNode.hasName("element-LT");
    public static Predicate<Node> isRLT = StatementNode.hasName("R-LT");
    public static Predicate<Node> isElement = StatementNode.hasName("element");
    public static Predicate<Node> isParensR = StatementNode.hasName("parens-R");
    public static Predicate<Node> isRegularLeftParanthesis = StatementNode.isParensR
            .and(node -> node.getTextContent().startsWith("("));
    public static Predicate<Node> isOperationStatement = StatementNode.isSubroutineStatement
            .or(StatementNode.isFunctionStatement);

    public static Predicate<Node> namedExpressionFunctionCall = StatementNode.isNamedExpression
            .and(StatementNode.childSatisfies("0", StatementNode.isBigN))
            .and(StatementNode.childSatisfies("0,0", StatementNode.isSmallN))
            .and(StatementNode.childSatisfies("1", StatementNode.isRLT))
            .and(StatementNode.childSatisfies("1,0", StatementNode.isRegularLeftParanthesis));

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

    public StatementNode(final Node node) {
        this.node = node;
        if (!StatementNode.satisfiesAssumptions(node)) {
            throw new IllegalArgumentException("unexpected node");
        }
    }

    public String getNodeTypeName() {
        return StatementNode.nodeType(this.getNodeType());
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

    private Iterable<StatementNode> getChildren() {
        return StatementNode.getChildren(this);
    }

    private static Iterable<StatementNode> getChildren(final Node node) {
        return new Iterable<>() {
            @Override
            public Iterator<StatementNode> iterator() {
                return new IndexIterator<>(() -> node.getChildNodes().getLength(),
                        index -> new StatementNode(node.getChildNodes().item(index)));
            }
        };
    }

    public StatementNode getChild(final int index) {
        return new StatementNode(this.getChildNodes().item(index));
    }

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

        for (final StatementNode child : StatementNode.getChildren(node)) {
            numberOfPrintedNodes += StatementNode.printNode(child, depth + 1);
        }
        return numberOfPrintedNodes;
    }

    // Node Search

    private static boolean testNodeAndFirstChildPredicateChain(final Node t, final List<Predicate<Node>> predicates) {
        if (predicates.isEmpty()) {
            return true;
        }
        if (!predicates.get(0).test(t)) {
            return false;
        }
        if (predicates.size() == 1) {
            return true;
        }
        if (t.getChildNodes().getLength() == 0) {
            return false;
        }
        return StatementNode.testNodeAndFirstChildPredicateChain(t.getFirstChild(),
                predicates.subList(1, predicates.size()));
    }

    static private Predicate<Node> nodeAndfirstChildPredicateChain(final List<Predicate<Node>> predicates) {
        return node -> StatementNode.testNodeAndFirstChildPredicateChain(node, predicates);
    }

    static private StatementNode getSuccessorNode(final Node node, final String path) {

        if (path.isEmpty()) {
            return new StatementNode(node);
        }

        final String firstNumber = StringUtils.substringBefore(path, ",");
        final String nextPath = StringUtils.substringAfter(path, ",");
        final int childIndex = Integer.parseInt(firstNumber);
        final NodeList children = node.getChildNodes();
        if (children.getLength() < childIndex) {
            return null;
        }
        return StatementNode.getSuccessorNode(node.getChildNodes().item(childIndex), nextPath);
    }

    static StatementNode getFirstChildChain(final Node node, final int depth) {

        Node result = node;

        for (int i = 0; i < depth; i++) {
            result = result.getFirstChild();
        }

        return new StatementNode(result);
    }

    // NOTE: Only terminates if nextNode eventually returns null or a matching element.
    private StatementNode findFirst(final Function<Node, Node> nextNode, final Predicate<Node> condition,
            final boolean includeSelf) {
        return this.findFirst(nextNode, condition, includeSelf, null);
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
    private StatementNode findFirst(final Function<Node, Node> nextNode, final Predicate<Node> condition,
            final boolean includeSelf, final List<Pair<Predicate<Node>, Predicate<Node>>> paranthesesTypes) {

        final int numberparanthesesTypes = (paranthesesTypes == null) ? 0 : paranthesesTypes.size();
        final int[] openParanthesis = new int[numberparanthesesTypes];

        Node current = this; // nextNode.apply(this);

        boolean inParanthesisInterval = false;
        while (current != null) {

            if (!inParanthesisInterval && condition.test(current) && ((current != this) || includeSelf)) {
                return new StatementNode(current);
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

            current = nextNode.apply(current);
        }

        return null;
    }

    private boolean hasConnectedWith(final Function<Node, Node> nextNode, final Predicate<Node> condition,
            final boolean includeSelf) {
        return this.findFirst(nextNode, condition, includeSelf) != null;
    }

    private boolean hasLeftSibling(final Predicate<Node> condition, final boolean includeSelf) {
        return this.hasConnectedWith(node -> node.getPreviousSibling(), condition, includeSelf);
    }

    private StatementNode firstLeftSibling(final Predicate<Node> condition, final boolean includeSelf) {
        return this.firstLeftSibling(condition, includeSelf, null);
    }

    private StatementNode firstLeftSibling(final Predicate<Node> condition, final boolean includeSelf,
            final List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes) {
        return this.findFirst(node -> node.getPreviousSibling(), condition, includeSelf, paranthesisTypes);
    }

    private StatementNode firstAncestor(final Predicate<Node> condition, final boolean includeSelf) {
        return this.findFirst(node -> node.getParentNode(), condition, includeSelf);
    }

    public Set<StatementNode> allDescendents(final Predicate<Node> condition, final boolean includeSelf) {
        return this.addAllDescendentsTo(condition, includeSelf, new HashSet<>());
    }

    private <T extends Collection<StatementNode>> T addAllDescendentsTo(final Predicate<Node> condition,
            final boolean includeSelf, final T addToThese) {

        if (condition.test(this) && includeSelf) {
            addToThese.add(this);
        }

        for (final StatementNode child : this.getChildren()) {
            child.addAllDescendentsTo(condition, true, addToThese);
        }

        return addToThese;
    }

    public static String getNameOfOperation(final StatementNode operationStatement) {

        if (StatementNode.isSubroutineStatement.test(operationStatement)) {
            return StatementNode.getNameOfOperation(operationStatement, StatementNode.isSubroutineName);
        } else if (StatementNode.isFunctionStatement.test(operationStatement)) {
            return StatementNode.getNameOfOperation(operationStatement, StatementNode.isFunctionName);
        }

        throw new IllegalArgumentException("Node is neither a function nor a subroutine statement.");
    }

    public static String getNameOfOperation(final StatementNode operationStatement,
            final Predicate<Node> namePredicate) {
        final Set<StatementNode> nameNodes = operationStatement.allDescendents(namePredicate, true);
        return DataStructureTools.getUniqueElement(nameNodes).getTextContent();
    }

    public StatementNode findContainingStatement(final Predicate<Node> condition) {
        return this.findContainingStatement(condition, null);
    }

    public StatementNode findContainingStatement(final Predicate<Node> condition,
            final List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes) {

        final Predicate<Node> hasSuchANodeAsLeftSibling = node -> (new StatementNode(node).hasLeftSibling(condition,
                false));

        final StatementNode siblingOfSuchNode = this.firstAncestor(hasSuchANodeAsLeftSibling, !condition.test(this));

        if (siblingOfSuchNode == null) {
            return null;
        }

        final StatementNode suchStatement = siblingOfSuchNode.firstLeftSibling(condition, true, paranthesisTypes);
        return suchStatement;
    }

    public String getNameOfContainingOperation() {
        return this.getNameOfContainingOperation(false);
    }

    public String getNameOfContainingOperation(final boolean verbose) {
        final Pair<Predicate<Node>, Predicate<Node>> endFunctionToBeginFunction = new Pair<>(
                StatementNode.isEndFunctionStatement, StatementNode.isFunctionStatement);

        final Pair<Predicate<Node>, Predicate<Node>> endSubroutineToBeginSubroutine = new Pair<>(
                StatementNode.isEndSubroutineStatement, StatementNode.isSubroutineStatement);

        final var paranthesisTypes = List.of(endFunctionToBeginFunction, endSubroutineToBeginSubroutine);

        final StatementNode containingOperationStatement = this
                .findContainingStatement(StatementNode.isOperationStatement, paranthesisTypes);
        if (verbose) {
            System.out.println("looking for containing operation of " + this.getTextContent());
            System.out.println("Containing Operation Statement: " + containingOperationStatement.getTextContent());
        }
        return (containingOperationStatement == null) ? "<root>"
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

        if ((type == Node.TEXT_NODE) && (node.getChildNodes().getLength() > 0)) {
            throw new IllegalArgumentException("text node with children");
        }

        if ((node.getNodeName().equals("call-stmt")) && (node.getChildNodes().getLength() < 2)) {
            StatementNode.printNode(node, 0);
            throw new IllegalArgumentException("call statement with < 2 children");
        }

        if (StatementNode.isNamedExpression.test(node)) {
            // NodeList children = node.getChildNodes();
            final Node firstChild = node.getFirstChild();
            final Node firstGrandChild = firstChild.getFirstChild();
            if (!StatementNode.isBigN.test(firstChild)) {
                throw new IllegalArgumentException("that's not a nice named expression, dude.");
            }

            if (!StatementNode.isSmallN.test(firstGrandChild)) {
                throw new IllegalArgumentException("that's not a nice named expression, dude.");
            }

            if (firstGrandChild.getChildNodes().getLength() > 1) {
                StatementNode.printNode(firstGrandChild, 0);
                throw new IllegalArgumentException("that's not a nice named expression, dude.");
            }
        }

        return true;
    }

    public boolean satisfiesAssumptions() {
        return StatementNode.satisfiesAssumptions(this);
    }

    public int getNumberOfDescendants(final boolean countSelf) {
        return this.allDescendents(node -> true, countSelf).size();
    }

    public <T> Set<T> getDescendentAttributes(final Predicate<Node> predicate,
            final Function<StatementNode, T> extractAttribute)
            throws ParserConfigurationException, SAXException, IOException {
        return this.allDescendents(predicate, true).stream().map(extractAttribute).collect(Collectors.toSet());
    }
}
