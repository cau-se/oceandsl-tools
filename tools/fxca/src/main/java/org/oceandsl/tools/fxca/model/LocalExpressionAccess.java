package org.oceandsl.tools.fxca.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import org.w3c.dom.Node;

public class LocalExpressionAccess {

	// we often need to search for names that are defined at the current node, which are *not* external
	// calls. Examples are 
	// - common block definitions
	// - local variables
	// - parameters to the function we're currently in.
	//
	// This class collects the parameters for looking for these values.

	public record LocalAccessParameters (
			Predicate<Node> blockNodeTypeCheckPredicate,
			Predicate<Node> outerDelimiterPredicate,
			Predicate<Node> innerDelimiterPredicate, 
			Function<Node, String> extractName) {
	};
	
	public enum accessType { COMMON_BLOCK, LOCAL_VARIABLE, OPERATION_PARAMETER, OPERATION_CALL };
	
	static LocalAccessParameters namesInCommonBlocks =
			new LocalAccessParameters(
					StatementNode.isCommonStatement,
					StatementNode.isCommonBlockObjectStatement,
					StatementNode.isSmallN,
					smallNNode -> StatementNode.getSuccessorNode(smallNNode, "0").getTextContent()
					);

	static LocalAccessParameters namesInOperationParameterList =
			new LocalAccessParameters(
					StatementNode.isOperationStatement,
					StatementNode.isArgN,
					StatementNode.isSmallN,
					smallNNode -> StatementNode.getSuccessorNode(smallNNode, "0").getTextContent()
					);

	static LocalAccessParameters namesInLocalVariableList =
			new LocalAccessParameters(
					StatementNode.isTDeclStmt,
					StatementNode.isEnDcl,
					StatementNode.isSmallN,
					smallNNode -> StatementNode.getSuccessorNode(smallNNode, "0").getTextContent()
					);

	static Set<String> localNamesDefinedInApplyingBlocks(Node node,	LocalAccessParameters parameters, boolean verbose) {

		List<StatementNode> applyingBlocks = new ArrayList<>();

		Node current = node;

		while (current != null) {
			List<StatementNode> applyingBlocksOnThisLevel =
					(new StatementNode(current)).findAll(nnode -> nnode.getPreviousSibling(),
							parameters.blockNodeTypeCheckPredicate,
							true,
							StatementNode.paranthesisTypes,
							-1);
			applyingBlocks.addAll(applyingBlocksOnThisLevel);
			current = current.getParentNode();
		}

		return localNamesDefinedInBlocks(applyingBlocks, parameters); // allNamesDefinedInCommonBlocks
	}

	private static Set<String> localNamesDefinedInBlocks(Collection<StatementNode> applyingBlocks, LocalAccessParameters parameters) {
		Set <String> result = new HashSet<>();

		for (StatementNode blockNode : applyingBlocks) {
			result.addAll(localNamesDefinedInBlock(blockNode, parameters));
		}

		return result;
	}

	private static Set<String> localNamesDefinedInBlock(StatementNode blockNode, LocalAccessParameters parameters)
	{

		if (!parameters.blockNodeTypeCheckPredicate.test(blockNode)) {
			throw new IllegalArgumentException("type checking failure.");
		}

		HashSet<String> result = new HashSet<>();
		for (StatementNode element : blockNode.allDescendents(parameters.outerDelimiterPredicate, true)) {
			for (StatementNode smallN : element.allDescendents(parameters.innerDelimiterPredicate, true)) {
				result.add(parameters.extractName.apply(smallN));
			}
		}

		return result;
	}

	public static boolean isNamedExpressionLocalReference(Node node, LocalAccessParameters parameters) {

		if (!StatementNode.namedExpressionAccess.test(node)) {
			return false;
		}

		String nameOfCalledFunction = StatementNode.nameOfCalledFunction(node);

		return LocalExpressionAccess.localNamesDefinedInApplyingBlocks(node, parameters, false).contains(nameOfCalledFunction);
	}

	public static accessType typeOfReferenceAccess(Node referenceNode) {

		if (isNamedExpressionLocalReference(referenceNode, namesInCommonBlocks)) {
			return accessType.COMMON_BLOCK;
		}

		if (isNamedExpressionLocalReference(referenceNode, namesInOperationParameterList)) {
			return accessType.OPERATION_PARAMETER;
		}

		if (isNamedExpressionLocalReference(referenceNode, namesInLocalVariableList)) {
			return accessType.LOCAL_VARIABLE;
		}

		return accessType.OPERATION_CALL;
	}
	
	public static boolean isLocalAccess(Node referenceNode) {
		return StatementNode.isCallStatement.or(StatementNode.namedExpressionAccess).test(referenceNode)
				&& typeOfReferenceAccess(referenceNode) != accessType.OPERATION_CALL;
	}

//	public static String nameOfCalledFunctionOrLocalReference(Node referenceNode) {
//
//		String suffix = switch(typeOfReferenceAccess(referenceNode)) {
//		case COMMON_BLOCK -> "-common-access";
//		case LOCAL_VARIABLE -> "-local-variable";
//    	case OPERATION_PARAMETER -> "-parameter-access";
//		case OPERATION_CALL -> "";
//		};
//
//		return StatementNode.nameOfCalledFunction(referenceNode) + suffix;
//	}
	
	public static String nameOfCalledFunctionOrLocalReference(Node referenceNode) {
		
		// rewritten the switch statement because checkstyle cannot parse it
		
		accessType switchValue = typeOfReferenceAccess(referenceNode);
		String suffix = null;
		
		if (switchValue == accessType.COMMON_BLOCK) {
			suffix = "-common-access";
		}
		
		if (switchValue == accessType.LOCAL_VARIABLE) {
			suffix = "-local-variable";
		}

		if (switchValue == accessType.OPERATION_PARAMETER) {
			suffix = "-parameter-access";
		}
		
		if (suffix == null) {
			throw new IllegalStateException();
		}

		return StatementNode.nameOfCalledFunction(referenceNode) + suffix;
	}

}
