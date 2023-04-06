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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import org.w3c.dom.Node;

/**
 *
 * @author Henning Schnoor
 *
 * @since 1.3.0
 */
public class LocalExpressionAccess {

    // we often need to search for names that are defined at the current node, which are *not*
    // external
    // calls. Examples are
    // - common block definitions
    // - local variables
    // - parameters to the function we're currently in.
    //
    // This class collects the parameters for looking for these values.

    public enum accessType {
        COMMON_BLOCK, LOCAL_VARIABLE, OPERATION_PARAMETER, OPERATION_CALL
    }

    static LocalAccessParameters namesInCommonBlocks = new LocalAccessParameters(NodePredicateUtils.isCommonStatement,
            NodePredicateUtils.isCommonBlockObjectStatement, NodePredicateUtils.isSmallN,
            smallNNode -> NodeProcessingUtils.getSuccessorNode(smallNNode, "0").getTextContent());

    static LocalAccessParameters namesInOperationParameterList = new LocalAccessParameters(
            NodePredicateUtils.isOperationStatement, NodePredicateUtils.isArgumentName, NodePredicateUtils.isSmallN,
            smallNNode -> NodeProcessingUtils.getSuccessorNode(smallNNode, "0").getTextContent());

    static LocalAccessParameters namesInLocalVariableList = new LocalAccessParameters(NodePredicateUtils.isTDeclStmt,
            NodePredicateUtils.isEnDcl, NodePredicateUtils.isSmallN,
            smallNNode -> NodeProcessingUtils.getSuccessorNode(smallNNode, "0").getTextContent());

    static Set<String> localNamesDefinedInApplyingBlocks(final Node node, final LocalAccessParameters parameters,
            final boolean verbose) {

        final List<Node> applyingBlocks = new ArrayList<>();

        Node current = node;

        while (current != null) {
            final List<Node> applyingBlocksOnThisLevel = NodeProcessingUtils.findAll(current,
                    nnode -> nnode.getPreviousSibling(), parameters.blockNodeTypeCheckPredicate, true,
                    NodePredicateUtils.paranthesisTypes, -1);
            applyingBlocks.addAll(applyingBlocksOnThisLevel);
            current = current.getParentNode();
        }

        return LocalExpressionAccess.localNamesDefinedInBlocks(applyingBlocks, parameters); // allNamesDefinedInCommonBlocks
    }

    private static Set<String> localNamesDefinedInBlocks(final Collection<Node> applyingBlocks,
            final LocalAccessParameters parameters) {
        final Set<String> result = new HashSet<>();

        for (final Node blockNode : applyingBlocks) {
            result.addAll(LocalExpressionAccess.localNamesDefinedInBlock(blockNode, parameters));
        }

        return result;
    }

    private static Set<String> localNamesDefinedInBlock(final Node blockNode, final LocalAccessParameters parameters) {

        if (!parameters.blockNodeTypeCheckPredicate.test(blockNode)) {
            throw new IllegalArgumentException("type checking failure.");
        }

        final HashSet<String> result = new HashSet<>();
        for (final Node element : NodeProcessingUtils.allDescendents(blockNode, parameters.outerDelimiterPredicate,
                true)) {
            for (final Node smallN : NodeProcessingUtils.allDescendents(element, parameters.innerDelimiterPredicate,
                    true)) {
                result.add(parameters.extractName.apply(smallN));
            }
        }

        return result;
    }

    public static boolean isNamedExpressionLocalReference(final Node node, final LocalAccessParameters parameters) {

        if (!NodePredicateUtils.isNamedExpressionAccess.test(node)) {
            return false;
        }

        final String nameOfCalledFunction = NodeProcessingUtils.nameOfCalledFunction(node);

        return LocalExpressionAccess.localNamesDefinedInApplyingBlocks(node, parameters, false)
                .contains(nameOfCalledFunction);
    }

    public static accessType typeOfReferenceAccess(final Node referenceNode) {

        if (LocalExpressionAccess.isNamedExpressionLocalReference(referenceNode,
                LocalExpressionAccess.namesInCommonBlocks)) {
            return accessType.COMMON_BLOCK;
        }

        if (LocalExpressionAccess.isNamedExpressionLocalReference(referenceNode,
                LocalExpressionAccess.namesInOperationParameterList)) {
            return accessType.OPERATION_PARAMETER;
        }

        if (LocalExpressionAccess.isNamedExpressionLocalReference(referenceNode,
                LocalExpressionAccess.namesInLocalVariableList)) {
            return accessType.LOCAL_VARIABLE;
        }

        return accessType.OPERATION_CALL;
    }

    public static boolean isLocalAccess(final Node referenceNode) {
        return NodePredicateUtils.isCallStatement.or(NodePredicateUtils.isNamedExpressionAccess).test(referenceNode)
                && (LocalExpressionAccess.typeOfReferenceAccess(referenceNode) != accessType.OPERATION_CALL);
    }

    public static String nameOfCalledFunctionOrLocalReference(final Node referenceNode) {

        // rewritten the switch statement because checkstyle cannot parse it

        final accessType switchValue = LocalExpressionAccess.typeOfReferenceAccess(referenceNode);
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

        return NodeProcessingUtils.nameOfCalledFunction(referenceNode) + suffix;
    }

    public static class LocalAccessParameters {
        private final Predicate<Node> blockNodeTypeCheckPredicate;
        private final Predicate<Node> outerDelimiterPredicate;
        private final Predicate<Node> innerDelimiterPredicate;
        private final Function<Node, String> extractName;

        LocalAccessParameters(final Predicate<Node> blockNodeTypeCheckPredicate,
                final Predicate<Node> outerDelimiterPredicate, final Predicate<Node> innerDelimiterPredicate,
                final Function<Node, String> extractName) {
            this.blockNodeTypeCheckPredicate = blockNodeTypeCheckPredicate;
            this.outerDelimiterPredicate = outerDelimiterPredicate;
            this.innerDelimiterPredicate = innerDelimiterPredicate;
            this.extractName = extractName;
        }
    }

}
