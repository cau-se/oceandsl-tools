package cau.agse.hs.staticfortran.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
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

public class ASTNode implements Node {

	@Delegate(types=Node.class)
	Node node;
	
	public String getNodeName() {
		return (node == null)? null : node.getNodeName();
	}

	public static Predicate<Node> isSubroutineStatement = hasName("subroutine-stmt");
	public static Predicate<Node> isEndSubroutineStatement = hasName("end-subroutine-stmt");
	public static Predicate<Node> isFunctionStatement = hasName("function-stmt");
	public static Predicate<Node> isEndFunctionStatement = hasName("end-function-stmt");
	public static Predicate<Node> isModuleStatement = hasName("module-stmt");
	public static Predicate<Node> isUseStatement = hasName("use-stmt");
	public static Predicate<Node> isCallStatement = hasName("call-stmt");
	public static Predicate<Node> isSubroutineName = hasName("subroutine-N");
	public static Predicate<Node> isFunctionName = hasName("function-N");
	public static Predicate<Node> isNamedExpression = hasName("named-E");
	public static Predicate<Node> isBigN = hasName("N");
	public static Predicate<Node> isSmallN = hasName("n");
	public static Predicate<Node> isElementLT = hasName("element-LT");
	public static Predicate<Node> isRLT = hasName("R-LT");
	public static Predicate<Node> isElement = hasName("element");
	public static Predicate<Node> isParensR = hasName("parens-R");
	public static Predicate<Node> isRegularLeftParanthesis = isParensR.and(node -> node.getTextContent().startsWith("("));
	public static Predicate<Node> isOperationStatement = isSubroutineStatement.or(isFunctionStatement);

	public static Predicate<Node> namedExpressionFunctionCall =
			isNamedExpression
			.and(childSatisfies("0", isBigN))
			.and(childSatisfies("0,0", isSmallN))
			.and(childSatisfies("1", isRLT))
			.and(childSatisfies("1,0", isRegularLeftParanthesis))
			;
	
	public static String nameOfCalledFunction(Node functionCallNode) {
		return getSuccessorNode(functionCallNode, "0,0").getTextContent();
	}
	
	public static String nameOfCalledOperation(Node operationCallNode) {
		return getSuccessorNode(operationCallNode, "1").getTextContent();
	}

	public static Predicate<Node> hasName(String name) {
		return node -> name.equals(node.getNodeName());
	}

	public static Predicate<Node> hasTextContent(String content) {
		return node -> content.equals(node.getTextContent());
	}

	public static Predicate<Node> childSatisfies(String path, Predicate<Node> predicate) {
		return node -> predicate.test(getSuccessorNode(node, path));
	}

	public ASTNode(Node node) {
		this.node = node;
		if (!satisfiesAssumptions(node)) {
			throw new IllegalArgumentException("unexpected node");
		}
	}

	public String getNodeTypeName() {
		return nodeType(this.getNodeType());
	}

	public static String nodeType(short nodeType) {
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

	private Iterable<ASTNode> getChildren() {
		return getChildren(this);
	}

	private static Iterable<ASTNode> getChildren(Node node) {
		return new Iterable<ASTNode>() {
			@Override
			public Iterator<ASTNode> iterator() {
				return new IndexIterator<>(() -> node.getChildNodes().getLength(), index -> new ASTNode(node.getChildNodes().item(index)));
			}};
	}

	public ASTNode getChild(int index) {
		return new ASTNode(getChildNodes().item(index));
	}

	public static int printNode(Node node, int depth) {
		String spaces = "";
		for (int i = 0; i < depth; i++) {
			spaces = spaces + " ";
		}
		int numberOfPrintedNodes = 1;
		System.out.println(spaces + "[node type] " + ASTNode.nodeType(node.getNodeType()));
		System.out.println(spaces + "[node name] " + node.getNodeName());
		System.out.println(spaces + "[node value] " + node.getNodeValue());
		System.out.println(spaces + "[node text content] " + node.getTextContent());
		System.out.println(spaces + "[node #kids] " + node.getChildNodes().getLength());

		for (ASTNode child : ASTNode.getChildren(node)) {
			numberOfPrintedNodes += printNode(child, depth + 1);
		}
		return numberOfPrintedNodes;
	}


	// Node Search

	private static boolean testNodeAndFirstChildPredicateChain(Node t, List<Predicate<Node>> predicates) {
		if (predicates.isEmpty()) return true;
		if (!predicates.get(0).test(t)) return false;
		if (predicates.size() == 1) return true;
		if (t.getChildNodes().getLength() == 0) return false;
		return testNodeAndFirstChildPredicateChain(t.getFirstChild(), predicates.subList(1, predicates.size()));
	}


	static private Predicate<Node> nodeAndfirstChildPredicateChain(List<Predicate<Node>> predicates) {
		return node -> testNodeAndFirstChildPredicateChain(node, predicates);
	}

	static private ASTNode getSuccessorNode(Node node, String path) {

		if (path.isEmpty()) return new ASTNode(node);

		String firstNumber = StringUtils.substringBefore(path, ",");
		String nextPath = StringUtils.substringAfter(path, ",");
		int childIndex = Integer.parseInt(firstNumber);
		NodeList children = node.getChildNodes();
		if (children.getLength() < childIndex) { 
			return null;
		}
		return getSuccessorNode(node.getChildNodes().item(childIndex), nextPath);
	}

	static ASTNode getFirstChildChain(Node node, int depth) {

		Node result = node;

		for (int i = 0; i < depth; i++) {
			result = result.getFirstChild();
		}

		return new ASTNode(result);
	}

	// NOTE: Only terminates if nextNode eventually returns null or a matching element.
	private ASTNode findFirst(Function <Node, Node> nextNode, Predicate<Node> condition, boolean includeSelf) {
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
	private ASTNode findFirst(Function <Node, Node> nextNode, Predicate<Node> condition, boolean includeSelf, List<Pair<Predicate<Node>, Predicate<Node>>> paranthesesTypes) {

		int numberparanthesesTypes = (paranthesesTypes == null)? 0 : paranthesesTypes.size();
		int[] openParanthesis = new int[numberparanthesesTypes];

		Node current = this; // nextNode.apply(this);

		boolean inParanthesisInterval = false;
		while (current != null) {

			if (!inParanthesisInterval && condition.test(current) && ((current != this) || includeSelf)) {
				return new ASTNode(current);
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

			current = nextNode.apply(current);
		}

		return null;
	} 

	private boolean hasConnectedWith(Function<Node, Node> nextNode, Predicate<Node> condition, boolean includeSelf) {
		return findFirst(nextNode, condition, includeSelf) != null;
	}

	private boolean hasLeftSibling(Predicate<Node> condition, boolean includeSelf) {
		return hasConnectedWith(node -> node.getPreviousSibling(), condition, includeSelf);
	}

	private ASTNode firstLeftSibling(Predicate<Node> condition, boolean includeSelf) {
		return firstLeftSibling(condition, includeSelf, null);
	}

	private ASTNode firstLeftSibling(Predicate<Node> condition, boolean includeSelf, List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes) {
		return findFirst(node -> node.getPreviousSibling(), condition, includeSelf, paranthesisTypes);
	}

	private ASTNode firstAncestor(Predicate<Node> condition, boolean includeSelf) {
		return findFirst(node -> node.getParentNode(), condition, includeSelf);
	}

	public Set<ASTNode> allDescendents(Predicate<Node> condition, boolean includeSelf) {
		return addAllDescendentsTo(condition, includeSelf, new HashSet<>());
	}

	private <T extends Collection<ASTNode>> T addAllDescendentsTo(Predicate<Node> condition, boolean includeSelf, T addToThese) {

		if (condition.test(this) && includeSelf) {
			addToThese.add(this);
		}

		for (ASTNode child : this.getChildren()) {
			child.addAllDescendentsTo(condition, true, addToThese);
		}

		return addToThese;
	}

	public static String getNameOfOperation(ASTNode operationStatement) {

		if (isSubroutineStatement.test(operationStatement)) {
			return getNameOfOperation(operationStatement, isSubroutineName);
		}
		else if (isFunctionStatement.test(operationStatement)) {
			return getNameOfOperation(operationStatement, isFunctionName);
		}

		throw new IllegalArgumentException("Node is neither a function nor a subroutine statement.");
	}

	public static String getNameOfOperation(ASTNode operationStatement, Predicate<Node> namePredicate) {
		Set<ASTNode> nameNodes = operationStatement.allDescendents(namePredicate, true);
		return DataStructureTools.getUniqueElement(nameNodes).getTextContent();
	}
	
	public ASTNode findContainingStatement(Predicate<Node> condition) {
		return findContainingStatement(condition, null);
	}

	public ASTNode findContainingStatement(Predicate<Node> condition, List<Pair<Predicate<Node>, Predicate<Node>>> paranthesisTypes) {

		Predicate<Node> hasSuchANodeAsLeftSibling = node -> (new ASTNode(node).hasLeftSibling(condition, false));

		ASTNode siblingOfSuchNode = this.firstAncestor(hasSuchANodeAsLeftSibling,
				!condition.test(this));

		if (siblingOfSuchNode == null) {
			return null;
		}

		ASTNode suchStatement = siblingOfSuchNode.firstLeftSibling(condition, true, paranthesisTypes);
		return suchStatement;
	}

	public String getNameOfContainingOperation() {
		return getNameOfContainingOperation(false);
	}

	public String getNameOfContainingOperation(boolean verbose) {
		Pair<Predicate<Node>, Predicate<Node>>
		  endFunctionToBeginFunction = new Pair<>(isEndFunctionStatement, isFunctionStatement);

		Pair<Predicate<Node>, Predicate<Node>>
		  endSubroutineToBeginSubroutine = new Pair<>(isEndSubroutineStatement, isSubroutineStatement);
		
		var paranthesisTypes = List.of(endFunctionToBeginFunction, endSubroutineToBeginSubroutine);
		
		ASTNode containingOperationStatement = findContainingStatement(isOperationStatement, paranthesisTypes);
		if (verbose) {
			System.out.println("looking for containing operation of " + this.getTextContent());
			System.out.println("Containing Operation Statement: " + containingOperationStatement.getTextContent());
		}
		return (containingOperationStatement == null)? "<root>" : getNameOfOperation(containingOperationStatement);
	}

	// We make some assumptions about the structure of the Fortran files (and the generated XML representations).
	// If we hit a node that does not satisfy these assumptions, throw an exception so that we know we need to
	// handle that case as well.
	public static boolean satisfiesAssumptions(Node node) {
		
		if (node == null) {
			return true;
		}

		short type = node.getNodeType();

		if ((type == Node.TEXT_NODE) && node.getChildNodes().getLength() > 0) throw new IllegalArgumentException("text node with children");

		if ((node.getNodeName().equals("call-stmt")) && node.getChildNodes().getLength() < 2) {
			printNode(node, 0);
			throw new IllegalArgumentException("call statement with < 2 children");
		}

		if (isNamedExpression.test(node)) {
			// NodeList children = node.getChildNodes();
			Node firstChild = node.getFirstChild();
			Node firstGrandChild = firstChild.getFirstChild();
			if (!isBigN.test(firstChild)) {
				throw new IllegalArgumentException("that's not a nice named expression, dude.");
			}

			if(!isSmallN.test(firstGrandChild)) {
				throw new IllegalArgumentException("that's not a nice named expression, dude.");
			}

			if (firstGrandChild.getChildNodes().getLength() > 1) {
				printNode(firstGrandChild, 0);
				throw new IllegalArgumentException("that's not a nice named expression, dude.");
			}
		} 

		return true;
	}

	public boolean satisfiesAssumptions() {
		return satisfiesAssumptions(this);
	}

	public int getNumberOfDescendants(boolean countSelf) {
		return allDescendents(node -> true, countSelf).size();
	}

	public <T> Set<T> getDescendentAttributes(Predicate<Node> predicate, Function<ASTNode, T> extractAttribute) throws ParserConfigurationException, SAXException, IOException {
		return allDescendents(predicate, true)
				.stream()
				.map(extractAttribute)
				.collect(Collectors.toSet());
	}
}
