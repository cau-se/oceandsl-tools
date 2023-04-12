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
import java.util.List;
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
import org.oceandsl.tools.fxca.model.FortranParameter;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.model.FortranVariable;
import org.oceandsl.tools.fxca.tools.IUriProcessor;
import org.oceandsl.tools.fxca.tools.ListTools;
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;
import org.oceandsl.tools.fxca.tools.Predicates;

/**
 *
 * @author Henning Schnoor -- initial contribution
 * @author Reiner Jung
 *
 * @since 1.3.0
 */
public class ProcessModuleStructureStage extends AbstractTransformation<Document, FortranProject> {

    private static final String RUNTIME = "<runtime>";
    private final FortranProject project;
    private final IUriProcessor uriProcessor;

    public ProcessModuleStructureStage(final IUriProcessor uriProcessor, final List<FortranOperation> operations) {
        this.project = new FortranProject();
        this.project.getModules().put(ProcessModuleStructureStage.RUNTIME, this.createModule(operations));
        this.uriProcessor = uriProcessor;
    }

    private FortranModule createModule(final List<FortranOperation> operations) {
        final FortranModule module = new FortranModule(ProcessModuleStructureStage.RUNTIME, "", true, null);
        operations.forEach(operation -> module.getOperations().put(operation.getName(), operation));
        return module;
    }

    @Override
    protected void execute(final Document document) throws Exception {
        final Element documentElement = document.getDocumentElement();
        final Node moduleStatement = ListTools.getUniqueElementIfNonEmpty(
                NodeProcessingUtils.allDescendents(documentElement, Predicates.isModuleStatement, true), null);

        final boolean namedModule = moduleStatement != null;
        final String fileName = this.uriProcessor.process(document.getBaseURI());
        final String moduleName = namedModule ? moduleStatement.getChildNodes().item(1).getTextContent() : fileName;

        final FortranModule module = new FortranModule(moduleName, fileName, namedModule, document);

        this.computeMainProgram(module, documentElement);

        this.computeUsedModels(module, documentElement);
        this.computeOperationDeclarations(module, documentElement);
        this.computeCommonBlocks(module, documentElement);
        this.computeInternalVariables(module, documentElement);
        this.computeInternalDimensionVariables(module, documentElement);

        this.project.getModules().put(module.getModuleName(), module);
    }

    private void computeMainProgram(final FortranModule module, final Element documentElement) {
        final Node mainProgram = ListTools.getUniqueElementIfNonEmpty(
                NodeProcessingUtils.allDescendents(documentElement, Predicates.isProgramStatement, true), null);
        if (mainProgram != null) {
            module.getOperations().put("main", new FortranOperation("main", mainProgram));
        }
    }

    private void computeInternalVariables(final FortranModule module, final Element documentElement)
            throws ParserConfigurationException, SAXException, IOException {
        final Set<Node> declarationStatements = this.getDescendentAttributes(documentElement, Predicates.isTDeclStmt,
                operationNode -> operationNode);
        declarationStatements.forEach(statement -> {
            final Node declarationElements = statement.getChildNodes().item(2);
            for (int i = 0; i < declarationElements.getChildNodes().getLength(); i++) {
                final Node declarationObject = declarationElements.getChildNodes().item(i);
                if ("EN-decl".equals(declarationObject.getNodeName())) {
                    final String objectName = declarationObject.getFirstChild().getFirstChild().getFirstChild()
                            .getTextContent();
                    final String variableName = objectName.toLowerCase(Locale.getDefault());
                    module.getVariables().put(variableName, this.createVariable(variableName));
                }
            }
        });
    }

    private FortranVariable createVariable(final String variableName) {
        return new FortranVariable(variableName);
    }

    private void computeInternalDimensionVariables(final FortranModule module, final Element documentElement)
            throws ParserConfigurationException, SAXException, IOException {
        final Set<Node> declarationStatements = this.getDescendentAttributes(documentElement, Predicates.isDimStmt,
                operationNode -> operationNode);
        declarationStatements.forEach(statement -> {
            final Node declarationElements = statement.getChildNodes().item(1);
            for (int i = 0; i < declarationElements.getChildNodes().getLength(); i++) {
                final Node declarationObject = declarationElements.getChildNodes().item(i);
                if ("EN-decl".equals(declarationObject.getNodeName())) {
                    final String objectName = declarationObject.getFirstChild().getFirstChild().getFirstChild()
                            .getTextContent();
                    final String variableName = objectName.toLowerCase(Locale.getDefault());
                    module.getVariables().put(variableName, this.createVariable(variableName));
                }
            }
        });
    }

    private void computeCommonBlocks(final FortranModule module, final Element documentElement)
            throws ParserConfigurationException, SAXException, IOException {
        final Set<Node> commonStatements = this.getDescendentAttributes(documentElement, Predicates.isCommonStatement,
                operationNode -> operationNode);
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
                final String variableName = objectName.toLowerCase(Locale.getDefault());
                commonBlock.getVariables().put(variableName, this.createVariable(variableName));
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
        final Set<Node> useStatements = NodeProcessingUtils.allDescendents(rootNode, Predicates.isUseStatement, false);
        for (final Node useStatement : useStatements) {
            final String usedModuleName = useStatement.getChildNodes().item(1).getTextContent();
            this.logger.debug("found use statement: {}, module name: {}", useStatement.getTextContent(),
                    usedModuleName);
            module.getUsedModules().add(usedModuleName);
        }
    }

    private void computeOperationDeclarations(final FortranModule module, final Element documentElement)
            throws ParserConfigurationException, SAXException, IOException {
        this.getDescendentAttributes(documentElement, Predicates.isOperationStatement, operationNode -> {
            try {
                final FortranOperation operation = this.createFortranOperation(operationNode);
                return module.getOperations().put(operation.getName(), operation);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    private FortranOperation createFortranOperation(final Node operationNode)
            throws ParserConfigurationException, SAXException, IOException {
        final FortranOperation operation = new FortranOperation(NodeProcessingUtils.getNameOfOperation(operationNode),
                operationNode);

        this.createFortranOperationParameters(operation, operationNode);

        // This is necessary as an entry "inherits" all variable and common block values from the
        // subroutine it belongs to
        if (Predicates.isEntryStatement.test(operationNode)) {
            Node belongingSubroutine = operationNode.getPreviousSibling();
            while (!Predicates.isSubroutineStatement.test(belongingSubroutine)) {
                belongingSubroutine = belongingSubroutine.getPreviousSibling();
            }

            this.createFortranOperationCommonBlock(operation, belongingSubroutine);
            this.createFortranOperationVariables(operation, belongingSubroutine);
            this.createFortranOperationDimensionalVariables(operation, belongingSubroutine);
        } else {
            this.createFortranOperationCommonBlock(operation, operationNode);
            this.createFortranOperationVariables(operation, operationNode);
            this.createFortranOperationDimensionalVariables(operation, operationNode);
        }
        return operation;
    }

    private void createFortranOperationParameters(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final Node argumentListNode = NodeProcessingUtils.findChildFirst(node, Predicates.isDummyArgumentLT);
        if (argumentListNode != null) {
            int i = 0;
            for (Node argument = argumentListNode.getFirstChild(); argument != null; argument = argument
                    .getNextSibling()) {
                if (Predicates.isArgumentName.test(argument)) {
                    final String parameterName = NodeProcessingUtils.getName(argument);
                    operation.getParameters().put(parameterName, new FortranParameter(parameterName, i++));
                }
            }
        }
    }

    private void createFortranOperationCommonBlock(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final List<Node> commonStatements = NodeProcessingUtils.findAllSiblings(node, Predicates.isCommonStatement,
                Predicates.isEndSubroutineStatement);
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
        final List<Node> files = NodeProcessingUtils.findAllSiblings(node, Predicates.isFile,
                Predicates.isEndSubroutineStatement);

        this.createFortranOperationPartVariables(operation, node);
        files.forEach(file -> this.createFortranOperationPartVariables(operation, file.getFirstChild()));
    }

    private void createFortranOperationPartVariables(final FortranOperation operation, final Node node) {
        final List<Node> declarationStatements = NodeProcessingUtils.findAllSiblings(node, Predicates.isTDeclStmt,
                Predicates.isEndSubroutineStatement);
        declarationStatements.forEach(statement -> {
            final Node declarationElements = NodeProcessingUtils.findChildFirst(statement, Predicates.isENDeclLT);

            for (int i = 0; i < declarationElements.getChildNodes().getLength(); i++) {
                final Node declarationObject = declarationElements.getChildNodes().item(i);
                if ("EN-decl".equals(declarationObject.getNodeName())) {
                    final String objectName = declarationObject.getFirstChild().getFirstChild().getFirstChild()
                            .getTextContent();
                    final String caseInsensitiveObjectName = objectName.toLowerCase(Locale.getDefault());
                    if (operation.getParameters().get(caseInsensitiveObjectName) == null) {
                        final String variableName = objectName.toLowerCase(Locale.getDefault());
                        operation.getVariables().put(variableName, this.createVariable(variableName));
                    } else {
                        // here you could set the parameter type
                    }
                }
            }
        });
    }

    private void createFortranOperationDimensionalVariables(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final List<Node> files = NodeProcessingUtils.findAllSiblings(node, Predicates.isFile,
                Predicates.isEndSubroutineStatement);

        this.createFortranOperationPartDimensionalVariables(operation, node);
        files.forEach(file -> {
            try {
                this.createFortranOperationPartDimensionalVariables(operation, file.getFirstChild());
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void createFortranOperationPartDimensionalVariables(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final Set<Node> declarationStatements = this.getDescendentAttributes(node, Predicates.isDimStmt,
                operationNode -> operationNode);
        declarationStatements.forEach(statement -> {
            final Node declarationElements = statement.getChildNodes().item(1);
            for (int i = 0; i < declarationElements.getChildNodes().getLength(); i++) {
                final Node declarationObject = declarationElements.getChildNodes().item(i);
                if ("EN-decl".equals(declarationObject.getNodeName())) {
                    final String objectName = declarationObject.getFirstChild().getFirstChild().getFirstChild()
                            .getTextContent();
                    final String variableName = objectName.toLowerCase(Locale.getDefault());
                    operation.getVariables().put(variableName, this.createVariable(variableName));
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
