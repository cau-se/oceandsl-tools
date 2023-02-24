package org.oceandsl.tools.fxca.model;

import java.io.IOException;
import java.util.ArrayList;
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
import org.oceandsl.tools.fxca.tools.IndexIterator;
import org.oceandsl.tools.fxca.tools.ListTools;
import org.oceandsl.tools.fxca.tools.Pair;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lombok.experimental.Delegate;

// No own attributes, only accessor functions for nodes. Implemented as class in order to allow
// chaining.

public class StatementNode implements Node {

	@Delegate(types = Node.class)
	final private Node node;

	public static Predicate<Node> isSubroutineStatement = hasName("subroutine-stmt");
	public static Predicate<Node> isEndSubroutineStatement = hasName("end-subroutine-stmt");
	public static Predicate<Node> isFunctionStatement = hasName("function-stmt");
	public static Predicate<Node> isOperationStatement = isSubroutineStatement.or(isFunctionStatement);
	public static Predicate<Node> isEndFunctionStatement = hasName("end-function-stmt");
	public static Predicate<Node> isModuleStatement = hasName("module-stmt");
	public static Predicate<Node> isUseStatement = hasName("use-stmt");
	public static Predicate<Node> isCallStatement = hasName("call-stmt");
	public static Predicate<Node> isSubroutineName = hasName("subroutine-N");
	public static Predicate<Node> isFunctionName = hasName("function-N");
	public static Predicate<Node> isNamedExpression = hasName("named-E");
	public static Predicate<Node> isBigN = hasName("N");
	public static Predicate<Node> isArgN = hasName("arg-N");
	public static Predicate<Node> isTDeclStmt = hasName("T-decl-stmt");
	public static Predicate<Node> isEnDcl = hasName("EN-decl");
	public static Predicate<Node> isSmallN = hasName("n");
	public static Predicate<Node> isElementLT = hasName("element-LT");
	public static Predicate<Node> isRLT = hasName("R-LT");
	public static Predicate<Node> isElement = hasName("element");
	public static Predicate<Node> isParensR = hasName("parens-R");
	public static Predicate<Node> isRegularLeftParanthesis = isParensR.and(node -> node.getTextContent().startsWith("("));
	public static Predicate<Node> isCommonStatement = hasName("common-stmt");
	public static Predicate<Node> isCommonBlockObjectStatement = hasName("common-block-obj-N");
	public static Predicate<Node> isLocalAccess = node -> LocalExpressionAccess.isLocalAccess(node);
	
	public static Predicate<Node> namedExpressionAccess =
			isNamedExpression
			.and(childSatisfies("0", isBigN))
			.and(childSatisfies("0,0", isSmallN))
			.and(childSatisfies("1", isRLT))
			.and(childSatisfies("1,0", isRegularLeftParanthesis))
			;

	public static Pair<Predicate<Node>, Predicate<Node>>
	endFunctionToBeginFunction = new Pair<>(isEndFunctionStatement, isFunctionStatement);

	public static Pair<Predicate<Node>, Predicate<Node>>
	endSubroutineToBeginSubroutine = new Pair<>(isEndSubroutineStatement, isSubroutineStatement);

	public static List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes = List.of(endFunctionToBeginFunction, endSubroutineToBeginSubroutine);
	
	public String getNodeName() {
		return (node == null)? null : node.getNodeName();
	}

	public static String nameOfCalledFunction(final Node functionCallNode) {
		return getSuccessorNode(functionCallNode, "0,0").getTextContent();
	}

	public static String nameOfCalledOperation(final Node operationCallNode) {
		return getSuccessorNode(operationCallNode, "1").getTextContent();
	}

	public static Predicate<Node> hasName(final String name) {
		return node -> name.equals(node.getNodeName());
	}

	public static Predicate<Node> hasTextContent(final String content) {
		return node -> content.equals(node.getTextContent());
	}

	public static Predicate<Node> childSatisfies(final String path, final Predicate<Node> predicate) {
		return node -> predicate.test(getSuccessorNode(node, path));
	}

	public StatementNode(Node node) {
		this.node = node;
		if (!satisfiesAssumptions(node)) {
			throw new IllegalArgumentException("unexpected node");
		}
	}

	public String getNodeTypeName() {
		return nodeType(this.getNodeType());
	}

	public static String nodeType(final short nodeType) {
		return switch(nodeType) {
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
		return getChildren(this);
	}

	private static Iterable<StatementNode> getChildren(final Node node) {
		return new Iterable<StatementNode>() {
			@Override
			public Iterator<StatementNode> iterator() {
				return new IndexIterator<>(() -> node.getChildNodes().getLength(), index -> new StatementNode(node.getChildNodes().item(index)));
			}};
	}

	public StatementNode getChild(final int index) {
		return new StatementNode(getChildNodes().item(index));
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

		for (StatementNode child : StatementNode.getChildren(node)) {
			numberOfPrintedNodes += printNode(child, depth + 1);
		}
		return numberOfPrintedNodes;
	}


	// Node Search

//	private static boolean testNodeAndFirstChildPredicateChain(Node t, List<Predicate<Node>> predicates) {
//		if (predicates.isEmpty()) { 
//			return true;
//		}
//		if (!predicates.get(0).test(t)) {
//			return false;
//		}
//		if (predicates.size() == 1) {
//			return true;
//		}
//		if (t.getChildNodes().getLength() == 0) {
//			return false;
//		}
//		return testNodeAndFirstChildPredicateChain(t.getFirstChild(), predicates.subList(1, predicates.size()));
//	}


	/* static private Predicate<Node> nodeAndfirstChildPredicateChain(List<Predicate<Node>> predicates) {
		return node -> testNodeAndFirstChildPredicateChain(node, predicates);
	}*/

	public static StatementNode getSuccessorNode(final Node node, final String path) {

		if (path.isEmpty()) {
			return new StatementNode(node);
		}

		String firstNumber = StringUtils.substringBefore(path, ",");
		String nextPath = StringUtils.substringAfter(path, ",");
		int childIndex = Integer.parseInt(firstNumber);
		NodeList children = node.getChildNodes();
		if (children.getLength() < childIndex) { 
			return null;
		}
		return getSuccessorNode(node.getChildNodes().item(childIndex), nextPath);
	}

	/* private static ASTNode getFirstChildChain(Node node, int depth) {

		Node result = node;

		for (int i = 0; i < depth; i++) {
			result = result.getFirstChild();
		}

		return new ASTNode(result);
	} */

	// NOTE: Only terminates if nextNode eventually returns null or a matching element.
	private StatementNode findFirst(final Function <Node, Node> nextNode, final Predicate<Node> condition, final boolean includeSelf) {
		return findFirst(nextNode, condition, includeSelf, null);
	}

	// paranthesistypes: contains pairs of "opening paranthesis" and "closed paranthesis" conditions,
	// where stuff between the paranthesis is ignored.
	//
	// Be careful with the search direction here, if you are searching "backwards", then ")" might
	// be your opening, and "(" your closing paranthesis.
	//
	// use case: when finding the containing operation statement, we need to skip functions that
	// are defined "along the way," i.e., where both the "function declaration" and the 
	// "end function declaration" elements appear.

	// NOTE: Only terminates if nextNode eventually returns null or a matching element.
	private StatementNode findFirst(final Function <Node, Node> nextNode, final Predicate<Node> condition, final boolean includeSelf, List<Pair<Predicate<Node>, Predicate<Node>>> paranthesesTypes) {

		List<StatementNode> result = findAll(nextNode, condition, includeSelf, paranthesesTypes, 1);

		return result.isEmpty()? null : result.get(0);
	}

	List<StatementNode> findAll(final Function <Node, Node> nextNode, final Predicate<Node> condition, final boolean includeSelf, final List<Pair<Predicate<Node>, Predicate<Node>>> paranthesesTypes, final int maxElementsToFind) {

		List<StatementNode> result = new ArrayList<>();

		int numberparanthesesTypes = (paranthesesTypes == null)? 0 : paranthesesTypes.size();
		int[] openParanthesis = new int[numberparanthesesTypes];

		Node current = this;

		boolean inParanthesisInterval = false;
		// End if we do not have anywhere to search, or we have reached the limit (where "-1" counts as "no limit"). 
		while (current != null && (result.size() < maxElementsToFind || maxElementsToFind == -1)) {

			if (!inParanthesisInterval && condition.test(current) && (current != this || includeSelf)) {
				result.add(new StatementNode(current));
			}

			inParanthesisInterval = false;
			for (int i = 0; i < numberparanthesesTypes; i++) {
				Predicate<Node> openingParanthesis = paranthesesTypes.get(i).first;				
				Predicate<Node> closingParanthesis = paranthesesTypes.get(i).second;

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
			Node nextNodeResult = nextNode.apply(current);
			current = (current == nextNodeResult)? null : nextNodeResult;
		}

		return result;
	} 


	private boolean hasConnectedWith(final Function<Node, Node> nextNode, final Predicate<Node> condition, final boolean includeSelf) {
		return findFirst(nextNode, condition, includeSelf) != null;
	}

	private boolean hasLeftSibling(final Predicate<Node> condition, final boolean includeSelf) {
		return hasConnectedWith(node -> node.getPreviousSibling(), condition, includeSelf);
	}

	private StatementNode firstLeftSibling(final Predicate<Node> condition, final boolean includeSelf, final List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes) {
		return findFirst(node -> node.getPreviousSibling(), condition, includeSelf, paranthesisTypes);
	}

	private StatementNode firstAncestor(final Predicate<Node> condition, final boolean includeSelf) {
		return findFirst(node -> node.getParentNode(), condition, includeSelf);
	}

	public Set<StatementNode> allDescendents(final Predicate<Node> condition, final boolean includeSelf) {
		return addAllDescendentsTo(condition, includeSelf, new HashSet<>());
	}


	private <T extends Collection<StatementNode>> T addAllDescendentsTo(final Predicate<Node> condition, final boolean includeSelf, final T addToThese) {

		if (condition.test(this) && includeSelf) {
			addToThese.add(this);
		}

		for (StatementNode child : this.getChildren()) {
			child.addAllDescendentsTo(condition, true, addToThese);
		}

		return addToThese;
	}

	public static String getNameOfOperation(final StatementNode operationStatement) {

		if (isSubroutineStatement.test(operationStatement)) {
			return getNameOfOperation(operationStatement, isSubroutineName);
		}
		else if (isFunctionStatement.test(operationStatement)) {
			return getNameOfOperation(operationStatement, isFunctionName);
		}

		throw new IllegalArgumentException("Node is neither a function nor a subroutine statement.");
	}

	public static String getNameOfOperation(final StatementNode operationStatement, final Predicate<Node> namePredicate) {
		final Set<StatementNode> nameNodes = operationStatement.allDescendents(namePredicate, true);
		return ListTools.getUniqueElement(nameNodes).getTextContent();
	}

	public StatementNode findContainingStatement(final Predicate<Node> condition) {
		return findContainingStatement(condition, null);
	}

	public StatementNode findContainingStatement(final Predicate<Node> condition, final List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes) {

		final Predicate<Node> hasSuchANodeAsLeftSibling = node -> new StatementNode(node).hasLeftSibling(condition, false);
		final StatementNode siblingOfSuchNode = this.firstAncestor(hasSuchANodeAsLeftSibling, !condition.test(this));

		if (siblingOfSuchNode == null) {
			return null;
		}

		StatementNode suchStatement = siblingOfSuchNode.firstLeftSibling(condition, true, paranthesisTypes);
		return suchStatement;
	}

	public String getNameOfContainingOperation() {

		StatementNode containingOperationStatement = findContainingStatement(isOperationStatement, paranthesisTypes);
		return (containingOperationStatement == null) ? "<root>" : getNameOfOperation(containingOperationStatement);
	}

	// We make some assumptions about the structure of the Fortran files (and the generated XML representations).
	// If we hit a node that does not satisfy these assumptions, throw an exception so that we know we need to
	// handle that case as well.
	public static boolean satisfiesAssumptions(final Node node) {

		if (node == null) {
			return true;
		}

		final short type = node.getNodeType();

		if ((type == Node.TEXT_NODE) && node.getChildNodes().getLength() > 0) { 
			throw new IllegalArgumentException("text node with children");
		}

		if (("call-stmt").equals(node.getNodeName()) && node.getChildNodes().getLength() < 2) {
			printNode(node, 0);
			throw new IllegalArgumentException("call statement with < 2 children");
		}

		if (isNamedExpression.test(node)) {
			// NodeList children = node.getChildNodes();
			final Node firstChild = node.getFirstChild();
			final Node firstGrandChild = firstChild.getFirstChild();
			if (!isBigN.test(firstChild)) {
				throw new IllegalArgumentException("named expression with unexpected type of first child.");
			}

			if (!isSmallN.test(firstGrandChild)) {
				throw new IllegalArgumentException("named expression with unexpected type of first grandchild.");
			}

			if (firstGrandChild.getChildNodes().getLength() > 1) {
				printNode(firstGrandChild, 0);
				throw new IllegalArgumentException("named expression with unexpected chlildren length list of first grandchild.");
			}
		} 

		return true;
	}

	public boolean satisfiesAssumptions() {
		return satisfiesAssumptions(this);
	}

	public int getNumberOfDescendants(final boolean countSelf) {
		return allDescendents(node -> true, countSelf).size();
	}

	public <T> Set<T> getDescendentAttributes(final Predicate<Node> predicate, final Function<StatementNode, T> extractAttribute) throws ParserConfigurationException, SAXException, IOException {
		return allDescendents(predicate, true)
				.stream()
				.map(extractAttribute)
				.collect(Collectors.toSet());
	}
}
