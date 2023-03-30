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
package org.oceandsl.tools.fxca.stages;

import java.io.IOException;
import java.util.Locale;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.tools.fxca.model.CommonBlock;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.tools.IUriProcessor;
import org.oceandsl.tools.fxca.tools.ListTools;
import org.oceandsl.tools.fxca.tools.NodePredicateUtils;
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;

/**
 *
 * @author Henning Schnoor -- initial contribution
 * @author Reiner Jung
 *
 * @since 1.3.0
 */
public class ProcessModuleStructureStage extends AbstractTransformation<Document, FortranProject> {

    private final FortranProject project;
    private final IUriProcessor uriProcessor;

    public ProcessModuleStructureStage(final IUriProcessor uriProcessor) {
        this.project = new FortranProject();
        this.uriProcessor = uriProcessor;
    }

    @Override
    protected void execute(final Document document) throws Exception {
        final Element documentElement = document.getDocumentElement();
        final Node moduleStatement = ListTools.getUniqueElementIfNonEmpty(
                NodeProcessingUtils.allDescendents(documentElement, NodePredicateUtils.isModuleStatement, true), null);

        final boolean namedModule = moduleStatement != null;
        final String fileName = this.uriProcessor.process(document.getBaseURI());
        final String moduleName = namedModule ? moduleStatement.getChildNodes().item(1).getTextContent() : fileName;

        final FortranModule module = new FortranModule(moduleName, fileName, namedModule, document);

        this.computeUsedModels(module, documentElement);
        this.computeOperationDeclarations(module, documentElement);
        this.computeCommonBlocks(module, documentElement);
        this.computeInternalVariables(module, documentElement);
        this.computeInternalDimensionVariables(module, documentElement);

        this.project.getModules().put(module.getModuleName(), module);
    }

    private void computeInternalVariables(final FortranModule module, final Element documentElement)
            throws ParserConfigurationException, SAXException, IOException {
        final Set<Node> declarationStatements = this.getDescendentAttributes(documentElement,
                NodePredicateUtils.isTDeclStmt, operationNode -> operationNode);
        declarationStatements.forEach(statement -> {
            final Node declarationElements = statement.getChildNodes().item(2);
            for (int i = 0; i < declarationElements.getChildNodes().getLength(); i++) {
                final Node declarationObject = declarationElements.getChildNodes().item(i);
                if ("EN-decl".equals(declarationObject.getNodeName())) {
                    final String objectName = declarationObject.getFirstChild().getFirstChild().getFirstChild()
                            .getTextContent();
                    module.getVariables().add(objectName.toLowerCase(Locale.getDefault()));
                }
            }
        });
    }

    private void computeInternalDimensionVariables(final FortranModule module, final Element documentElement)
            throws ParserConfigurationException, SAXException, IOException {
        final Set<Node> declarationStatements = this.getDescendentAttributes(documentElement,
                NodePredicateUtils.isDimStmt, operationNode -> operationNode);
        declarationStatements.forEach(statement -> {
            final Node declarationElements = statement.getChildNodes().item(1);
            for (int i = 0; i < declarationElements.getChildNodes().getLength(); i++) {
                final Node declarationObject = declarationElements.getChildNodes().item(i);
                if ("EN-decl".equals(declarationObject.getNodeName())) {
                    final String objectName = declarationObject.getFirstChild().getFirstChild().getFirstChild()
                            .getTextContent();
                    module.getVariables().add(objectName.toLowerCase(Locale.getDefault()));
                }
            }
        });
    }

    private void computeCommonBlocks(final FortranModule module, final Element documentElement)
            throws ParserConfigurationException, SAXException, IOException {
        final Set<Node> commonStatements = this.getDescendentAttributes(documentElement,
                NodePredicateUtils.isCommonStatement, operationNode -> operationNode);
        commonStatements.forEach(statement -> {
            final Node commonBlockNameNode = statement.getChildNodes().item(1);
            final String commonBlockName = commonBlockNameNode.getFirstChild().getTextContent()
                    .toLowerCase(Locale.getDefault());
            CommonBlock commonBlock = module.getCommonBlocks().get(commonBlockName);
            if (commonBlock == null) {
                commonBlock = new CommonBlock(commonBlockName, statement);
            }
            module.getCommonBlocks().put(commonBlockName,
                    this.createCommonBlock(commonBlock, statement, commonBlockName));
        });
    }

    private CommonBlock createCommonBlock(final CommonBlock commonBlock, final Node statement, final String name) {
        final Node commonBlockElements = statement.getChildNodes().item(3);
        for (int i = 0; i < commonBlockElements.getChildNodes().getLength(); i++) {
            final Node commonBlockObject = commonBlockElements.getChildNodes().item(i);
            if ("common-block-obj".equals(commonBlockObject.getNodeName())) {
                final String objectName = commonBlockObject.getFirstChild().getFirstChild().getFirstChild()
                        .getTextContent();
                commonBlock.getElements().add(objectName.toLowerCase(Locale.getDefault()));
            }
        }

        return commonBlock;
    }

    @Override
    protected void onTerminating() {
        this.outputPort.send(this.project);
        super.onTerminating();
    }

    private void computeUsedModels(final FortranModule module, final Element rootNode) {
        final Set<Node> useStatements = NodeProcessingUtils.allDescendents(rootNode, NodePredicateUtils.isUseStatement,
                false);
        for (final Node useStatement : useStatements) {
            final String usedModuleName = useStatement.getChildNodes().item(1).getTextContent();
            this.logger.debug("found use statement: {}, module name: {}", useStatement.getTextContent(),
                    usedModuleName);
            module.getUsedModules().add(usedModuleName);
        }
    }

    private void computeOperationDeclarations(final FortranModule module, final Element documentElement)
            throws ParserConfigurationException, SAXException, IOException {
        this.getDescendentAttributes(documentElement, NodePredicateUtils.isOperationStatement, operationNode -> {
            try {
                final FortranOperation operation = this.createFortranOperation(operationNode);
                return module.getOperations().put(operation.getName(), operation);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    private FortranOperation createFortranOperation(final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final FortranOperation operation = new FortranOperation(NodeProcessingUtils.getNameOfOperation(node), node);

        this.createFortranOperationParameters(operation, node);

        this.createFortranOperationCommonBlock(operation, node);
        this.createFortranOperationVariables(operation, node);
        this.createFortranOperationDimensionalVariables(operation, node);

        return operation;
    }

    private void createFortranOperationParameters(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final Node argumentListNode = NodeProcessingUtils.findFirstChild(node, NodePredicateUtils.isDummyArgumentLT);
        if (argumentListNode != null) {
            for (Node argument = argumentListNode.getFirstChild(); argument != null; argument = argument
                    .getNextSibling()) {
                if (NodePredicateUtils.isArgumentName.test(argument)) {
                    final String parameterName = NodeProcessingUtils.getName(argument);
                    operation.getParameters().add(parameterName);
                }
            }
        }
    }

    private void createFortranOperationCommonBlock(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final Set<Node> commonStatements = NodeProcessingUtils.findAllSiblings(node,
                NodePredicateUtils.isCommonStatement, NodePredicateUtils.isEndSubroutineStatement);
        commonStatements.forEach(statement -> {
            final Node commonBlockNameNode = statement.getChildNodes().item(1);
            final String name = commonBlockNameNode.getFirstChild().getTextContent().toLowerCase(Locale.getDefault());
            final CommonBlock commonBlock = operation.getCommonBlocks().get(name);
            if (commonBlock == null) {
                operation.getCommonBlocks().put(name, new CommonBlock(name, statement));
            }
        });
    }

    private void createFortranOperationVariables(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final Set<Node> declarationStatements = NodeProcessingUtils.findAllSiblings(node,
                NodePredicateUtils.isTDeclStmt, NodePredicateUtils.isEndSubroutineStatement);
        declarationStatements.forEach(statement -> {
            final Node declarationElements = statement.getChildNodes().item(2);
            for (int i = 0; i < declarationElements.getChildNodes().getLength(); i++) {
                final Node declarationObject = declarationElements.getChildNodes().item(i);
                if ("EN-decl".equals(declarationObject.getNodeName())) {
                    final String objectName = declarationObject.getFirstChild().getFirstChild().getFirstChild()
                            .getTextContent();
                    final String caseInsensitiveObjectName = objectName.toLowerCase(Locale.getDefault());
                    if (!operation.getParameters().contains(caseInsensitiveObjectName)) {
                        operation.getVariables().add(caseInsensitiveObjectName);
                    }
                }
            }
        });
    }

    private void createFortranOperationDimensionalVariables(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final Set<Node> declarationStatements = this.getDescendentAttributes(node, NodePredicateUtils.isDimStmt,
                operationNode -> operationNode);
        declarationStatements.forEach(statement -> {
            final Node declarationElements = statement.getChildNodes().item(1);
            for (int i = 0; i < declarationElements.getChildNodes().getLength(); i++) {
                final Node declarationObject = declarationElements.getChildNodes().item(i);
                if ("EN-decl".equals(declarationObject.getNodeName())) {
                    final String objectName = declarationObject.getFirstChild().getFirstChild().getFirstChild()
                            .getTextContent();
                    operation.getVariables().add(objectName.toLowerCase(Locale.getDefault()));
                }
            }
        });
    }

    private <T> Set<T> getDescendentAttributes(final Node node, final Predicate<Node> predicate,
            final Function<Node, T> extractAttribute) throws ParserConfigurationException, SAXException, IOException {
        return NodeProcessingUtils.allDescendents(node, predicate, true).stream().map(extractAttribute)
                .collect(Collectors.toSet());
    }

}
