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
import java.util.ArrayList;
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
import org.oceandsl.tools.fxca.utils.IUriProcessor;
import org.oceandsl.tools.fxca.utils.ListTools;
import org.oceandsl.tools.fxca.utils.NodeUtils;
import org.oceandsl.tools.fxca.utils.Predicates;

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

    public ProcessModuleStructureStage(final IUriProcessor uriProcessor, final List<FortranModule> modules,
            final String defaultModuleName) {
        this.project = new FortranProject();
        if (modules.size() > 0) {
            this.project.setDefaultModule(modules.get(0));
            this.project.getModules().put(this.project.getDefaultModule().getFileName(),
                    this.project.getDefaultModule());
        } else {
            this.project.setDefaultModule(new FortranModule(defaultModuleName, "", true, null));
            modules.forEach(module -> this.project.getModules().put(module.getFileName(), module));
        }
        this.uriProcessor = uriProcessor;
    }

    @Override
    protected void execute(final Document document) throws Exception {
        final Element documentElement = document.getDocumentElement();
        final Node moduleStatement = ListTools.getUniqueElementIfNonEmpty(
                NodeUtils.allDescendents(documentElement, Predicates.isModuleStatement, true), null);

        final boolean namedModule = moduleStatement != null;
        final String fileName = this.uriProcessor.process(document.getBaseURI());
        final String moduleName = namedModule ? moduleStatement.getChildNodes().item(1).getTextContent() : fileName;

        final FortranModule module = new FortranModule(moduleName, fileName, namedModule, document);
        this.logger.debug("Processing file structure of {}", module.getFileName());

        this.computeModule(module, documentElement);

        this.computeMainProgram(module, documentElement);
        this.computeOperations(module, documentElement);

        this.project.getModules().put(module.getModuleName(), module);
    }

    @Override
    protected void onTerminating() {
        this.outputPort.send(this.project);
        super.onTerminating();
    }

    private void computeMainProgram(final FortranModule module, final Element documentElement)
            throws ParserConfigurationException, SAXException, IOException {
        final Node mainProgramNode = ListTools.getUniqueElementIfNonEmpty(
                NodeUtils.allDescendents(documentElement, Predicates.isProgramStatement, true), null);
        if (mainProgramNode != null) {
            final FortranOperation operation = new FortranOperation("main", mainProgramNode, false);

            this.createFortranOperationCommonBlock(operation, mainProgramNode);
            this.createFortranOperationVariables(operation, mainProgramNode);
            this.createFortranOperationImplicitVariables(operation, mainProgramNode);
            this.createFortranOperationDimensionalVariables(operation, mainProgramNode);

            operation.setModule(module);
            module.getOperations().put("main", operation);
        }
    }

    private void computeModule(final FortranModule module, final Element documentElement) {
        final Node moduleNode = ListTools.getUniqueElementIfNonEmpty(
                NodeUtils.allDescendents(documentElement, Predicates.isModuleStatement, true), null);
        if (moduleNode != null) {
            this.computeUsedModules(module.getUsedModules(), moduleNode);
            // TODO modules can include variables and parameters

            // this.computeCommonBlocks(module, documentElement);
            // this.computeInternalVariables(module, documentElement);
            // this.computeInternalImplicitVariables(module, documentElement);
            // this.computeInternalDimensionVariables(module, documentElement);
        }
    }

    private void computeOperations(final FortranModule module, final Element documentElement)
            throws ParserConfigurationException, SAXException, IOException {
        final List<Node> operationNodes = NodeUtils.findAllSiblingsDescendants(documentElement,
                Predicates.isOperationStatement, o -> false, false);

        operationNodes.forEach(operationNode -> {
            try {
                final FortranOperation operation = this.createFortranOperation(module, operationNode);
                module.getOperations().put(operation.getName(), operation);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        });
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

    private void computeInternalImplicitVariables(final FortranModule module, final Element documentElement)
            throws ParserConfigurationException, SAXException, IOException {
        final Set<Node> assignments = this.getDescendentAttributes(documentElement, Predicates.isAssignmentStatement,
                o -> o);
        assignments.forEach(assignment -> {
            final String name = NodeUtils.getName(NodeUtils.getAssigmentVariable(assignment));
            module.getVariables().put(name, this.createVariable(name));
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
                if (Predicates.isEnDecl.test(declarationObject)) {
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
            module.getCommonBlocks().put(commonBlockName, this.createCommonBlock(commonBlock, statement));
        });
    }

    private CommonBlock createCommonBlock(final CommonBlock commonBlock, final Node statement) {
        final Node commonBlockElements = statement.getChildNodes().item(3);
        for (int i = 0; i < commonBlockElements.getChildNodes().getLength(); i++) {
            final Node commonBlockObject = commonBlockElements.getChildNodes().item(i);
            if (Predicates.isCommonBlockObject.test(commonBlockObject)) {
                final String objectName = commonBlockObject.getFirstChild().getFirstChild().getFirstChild()
                        .getTextContent();
                final String variableName = objectName.toLowerCase(Locale.getDefault());
                commonBlock.getVariables().put(variableName, this.createVariable(variableName));
            }
        }

        return commonBlock;
    }

    private FortranOperation createFortranOperation(final FortranModule module, final Node operationNode)
            throws ParserConfigurationException, SAXException, IOException {
        final FortranOperation operation = new FortranOperation(NodeUtils.getNameOfOperation(operationNode),
                operationNode, Predicates.isFunctionStatement.test(operationNode));
        operation.setModule(module);

        this.createFortranOperationParameters(operation, operationNode);

        this.computeUsedModules(operation.getUsedModules(), operationNode);

        // This is necessary as an entry "inherits" all variable and common block values from the
        // subroutine it belongs to
        Node belongingSubroutine = operationNode;
        if (Predicates.isEntryStatement.test(operationNode)) {
            while (!Predicates.isSubroutineStatement.test(belongingSubroutine)) {
                belongingSubroutine = belongingSubroutine.getPreviousSibling();
            }
        }

        this.createExternalOperation(module, belongingSubroutine);

        this.createFortranOperationCommonBlock(operation, belongingSubroutine);
        this.createFortranOperationVariables(operation, belongingSubroutine);
        this.createFortranOperationImplicitVariables(operation, belongingSubroutine);
        this.createFortranOperationDimensionalVariables(operation, belongingSubroutine);

        return operation;
    }

    private void createExternalOperation(final FortranModule module, final Node operationNode) {
        final List<Node> externals = NodeUtils.findAllSiblingsDescendants(operationNode, Predicates.isExternalStatement,
                Predicates.isEndSubroutineStatement, true);
        externals.forEach(external -> {
            final Node enDeclLt = NodeUtils.findChildFirst(external, Predicates.isENDeclLT);
            final List<Node> declarations = NodeUtils.findAllSiblingsDescendants(enDeclLt, Predicates.isEnDecl,
                    o -> false, true);
            declarations.forEach(declaration -> {
                final String name = NodeUtils.getName(declaration);
                final FortranOperation operation = new FortranOperation(name, null, true, true);
                operation.setModule(module);
                operation.getParameters().put("v0", new FortranParameter("v0", 0));
                module.getOperations().put(name, operation);
            });
        });
    }

    private void computeUsedModules(final Set<String> usedModules, final Node rootNode) {
        final Set<Node> useStatements = NodeUtils.allDescendents(rootNode, Predicates.isUseStatement, false);
        for (final Node useStatement : useStatements) {
            final String usedModuleName = useStatement.getChildNodes().item(1).getTextContent();
            this.logger.debug("found use statement: {}, module name: {}", useStatement.getTextContent(),
                    usedModuleName);
            usedModules.add(usedModuleName);
        }
    }

    private void createFortranOperationImplicitVariables(final FortranOperation operation, final Node operationNode)
            throws ParserConfigurationException, SAXException, IOException {
        final List<Node> assignments = this.findAllSiblingsAndDescendents(operationNode,
                Predicates.isAssignmentStatement, Predicates.isEndOperationStatement);
        assignments.forEach(assignment -> {
            final String name = NodeUtils.getName(NodeUtils.getAssigmentVariable(assignment));
            if (!operation.getParameters().containsKey(name)) {
                operation.getVariables().put(name, this.createVariable(name));
            }
        });
        final List<Node> doStatements = this.findAllSiblingsAndDescendents(operationNode,
                Predicates.isDoLabelStatement.or(Predicates.isDoStatement), Predicates.isEndOperationStatement);
        doStatements.forEach(doStatement -> {
            final Node doV = NodeUtils.findChildFirst(doStatement, Predicates.isDoV);
            if (doV != null) {
                final String elementName = NodeUtils.getName(doV);
                if (!operation.getParameters().containsKey(elementName)) {
                    operation.getVariables().put(elementName, this.createVariable(elementName));
                    // TODO create error when subroutine does not allow implicit declarations
                }
            }
        });
    }

    private void createFortranOperationParameters(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final Node argumentListNode = NodeUtils.findChildFirst(node, Predicates.isDummyArgumentLT);
        if (argumentListNode != null) {
            int i = 0;
            for (Node argument = argumentListNode.getFirstChild(); argument != null; argument = argument
                    .getNextSibling()) {
                if (Predicates.isArgumentName.test(argument)) {
                    final String parameterName = NodeUtils.getName(argument);
                    operation.getParameters().put(parameterName, new FortranParameter(parameterName, i++));
                }
            }
        }
    }

    private void createFortranOperationCommonBlock(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final List<Node> commonStatements = NodeUtils.findAllSiblingsDescendants(node, Predicates.isCommonStatement,
                Predicates.isEndOperationStatement, true);
        commonStatements.forEach(statement -> {
            final Node commonBlockNameNode = statement.getChildNodes().item(1);
            final String name = commonBlockNameNode.getFirstChild().getTextContent().toLowerCase(Locale.getDefault());
            CommonBlock commonBlock = operation.getCommonBlocks().get(name);
            if (commonBlock == null) {
                commonBlock = new CommonBlock(name, statement);
                operation.getCommonBlocks().put(name, commonBlock);
            }
            this.createCommonBlock(commonBlock, statement);
        });
    }

    private void createFortranOperationVariables(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final List<Node> declarationStatements = NodeUtils.findAllSiblingsDescendants(node,
                Predicates.isTDeclStmt.or(Predicates.isParameterStatement), Predicates.isEndOperationStatement, true);
        declarationStatements.forEach(statement -> this.createFortranOperationPartVariables(operation, statement));
    }

    private void createFortranOperationPartVariables(final FortranOperation operation, final Node statement) {
        final Node declarationElements = NodeUtils.findChildFirst(statement, Predicates.isENDeclLT);

        for (int i = 0; i < declarationElements.getChildNodes().getLength(); i++) {
            final Node declarationObject = declarationElements.getChildNodes().item(i);
            if (Predicates.isEnDecl.test(declarationObject)) {
                final String objectName = declarationObject.getFirstChild().getFirstChild().getFirstChild()
                        .getTextContent();
                final String variableName = objectName.toLowerCase(Locale.getDefault());
                if (!operation.getParameters().containsKey(variableName)) {
                    operation.getVariables().put(variableName, this.createVariable(variableName));
                } else {
                    // here you could set the parameter type
                }
            }
        }
    }

    private void createFortranOperationDimensionalVariables(final FortranOperation operation, final Node node)
            throws ParserConfigurationException, SAXException, IOException {
        final List<Node> files = NodeUtils.findAllSiblings(node, Predicates.isFile,
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
        final List<Node> declarationStatements = this.findAllSiblingsAndDescendents(node, Predicates.isDimStmt,
                Predicates.isEndSubroutineStatement);
        declarationStatements.forEach(statement -> {
            final Node declarationElements = statement.getChildNodes().item(1);
            for (int i = 0; i < declarationElements.getChildNodes().getLength(); i++) {
                final Node declarationObject = declarationElements.getChildNodes().item(i);
                if (Predicates.isEnDecl.test(declarationObject)) {
                    final String objectName = declarationObject.getFirstChild().getFirstChild().getFirstChild()
                            .getTextContent();
                    final String variableName = objectName.toLowerCase(Locale.getDefault());
                    if (!operation.getParameters().containsKey(variableName)) {
                        operation.getVariables().put(variableName, this.createVariable(variableName));
                    }
                }
            }
        });
    }

    private List<Node> findAllSiblingsAndDescendents(final Node node, final Predicate<Node> findPredicate,
            final Predicate<Node> endPredicate) {
        final List<Node> result = new ArrayList<>();
        NodeUtils.findAllSiblings(node, o -> true, endPredicate).forEach(sibling -> {
            final Set<Node> values = NodeUtils.allDescendents(sibling, findPredicate, true);
            result.addAll(values);
        });
        return result;
    }

    private <T> Set<T> getDescendentAttributes(final Node node, final Predicate<Node> predicate,
            final Function<Node, T> extractAttribute) throws ParserConfigurationException, SAXException, IOException {
        return NodeUtils.allDescendents(node, predicate, true).stream().map(extractAttribute)
                .collect(Collectors.toSet());
    }

}
