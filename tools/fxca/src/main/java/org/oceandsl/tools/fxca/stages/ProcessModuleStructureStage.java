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

import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.tools.ListTools;
import org.oceandsl.tools.fxca.tools.NodePredicateUtils;
import org.oceandsl.tools.fxca.tools.NodeProcessingUtils;

/**
 *
 * @author Henning Schnoor -- initial contribution
 * @author Reiner Jung
 *
 */
public class ProcessModuleStructureStage extends AbstractTransformation<Document, FortranProject> {

    private final FortranProject project;

    public ProcessModuleStructureStage() {
        this.project = new FortranProject();
    }

    @Override
    protected void execute(final Document document) throws Exception {
        final Element documentElement = document.getDocumentElement();
        final Node moduleStatement = ListTools.getUniqueElementIfNonEmpty(
                NodeProcessingUtils.allDescendents(documentElement, NodePredicateUtils.isModuleStatement, true), null);

        final boolean namedModule = moduleStatement != null;
        final String moduleName = namedModule ? moduleStatement.getChildNodes().item(1).getTextContent()
                : document.getBaseURI();

        final FortranModule module = new FortranModule(moduleName, namedModule, document);

        this.computeUsedModels(module, documentElement);
        this.computeOperationDeclarations(module, documentElement);

        this.project.getModules().put(module.getModuleName(), module);
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
        this.getDescendentAttributes(documentElement, NodePredicateUtils.isOperationStatement,
                operationNode -> NodeProcessingUtils.getNameOfOperation(operationNode))
                .forEach(operation -> module.getSpecifiedOperations().add(operation.toLowerCase(Locale.getDefault())));
    }

    private <T> Set<T> getDescendentAttributes(final Node node, final Predicate<Node> predicate,
            final Function<Node, T> extractAttribute) throws ParserConfigurationException, SAXException, IOException {
        return NodeProcessingUtils.allDescendents(node, predicate, true).stream().map(extractAttribute)
                .collect(Collectors.toSet());
    }

}
