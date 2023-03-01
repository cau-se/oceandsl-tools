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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import org.oceandsl.tools.fxca.tools.ListTools;
import org.oceandsl.tools.fxca.tools.Pair;

import lombok.Getter;

/**
 * @author Henning Schnoor
 *
 */
public class FortranModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(FortranModule.class);

    @Getter
    private final Set<String> usedModules;
    @Getter
    private final Set<String> specifiedOperations;
    @Getter
    private final String moduleName;
    @Getter
    private final boolean namedModule;
    StatementNode documentElement;

    public FortranModule(final Document doc) throws ParserConfigurationException, SAXException, IOException {
        this.documentElement = new StatementNode(doc.getDocumentElement());
        final StatementNode moduleStatement = ListTools.getUniqueElementIfNonEmpty(
                this.documentElement.allDescendents(StatementNode.isModuleStatement, true), null);
        this.namedModule = (moduleStatement != null);
        this.moduleName = this.namedModule ? moduleStatement.getChild(1).getTextContent() : "<no module>";
        this.usedModules = this.computeUsedModels();
        this.specifiedOperations = this.computeOperationDeclarations();

        this.printSummary();
    }

    public void printSummary() {
        FortranModule.LOGGER.debug("# Summary");
        FortranModule.LOGGER.debug(" [moduleName]           {}", this.moduleName);
        FortranModule.LOGGER.debug(" [used modules]         ");
        this.usedModules.forEach(name -> FortranModule.LOGGER.debug("  * {}", name));

        FortranModule.LOGGER.debug(" [operation definitions] ");
        this.specifiedOperations.forEach(name -> FortranModule.LOGGER.debug("  * {}", name));
    }

    public Set<String> computeOperationDeclarations() throws ParserConfigurationException, SAXException, IOException {
        return this.documentElement.getDescendentAttributes(StatementNode.isOperationStatement,
                operationNode -> StatementNode.getNameOfOperation(operationNode));
    }

    public List<Pair<String, String>> operationCalls() throws ParserConfigurationException, SAXException, IOException {
        return ListTools.ofM(this.subroutineCalls(), this.functionCalls(), Pair.getComparatorFirstSecond());
    }

    public List<Pair<String, String>> operationCalls(final Predicate<Node> callPredicate,
            final Function<Node, String> calledOperation) {
        final Set<Pair<String, String>> result = new HashSet<>(); // Check for double entries
        final Set<StatementNode> callStatements = this.documentElement.allDescendents(callPredicate, true);
        for (final StatementNode callStatement : callStatements) {
            final String callee = calledOperation.apply(callStatement);
            final String caller = callStatement.getNameOfContainingOperation();
            result.add(new Pair<>(caller, callee));
        }

        return ListTools.ofM(result, Pair.getComparatorFirstSecond());
    }

    public List<Pair<String, String>> subroutineCalls() throws ParserConfigurationException, SAXException, IOException {
        return this.operationCalls(StatementNode.isCallStatement.and(StatementNode.isLocalAccess.negate()),
                subroutineCall -> StatementNode.nameOfCalledOperation(subroutineCall));
    }

    public List<Pair<String, String>> functionCalls() throws ParserConfigurationException, SAXException, IOException {
        return this.operationCalls(StatementNode.namedExpressionAccess.and(StatementNode.isLocalAccess.negate()),
                functionCall -> StatementNode.nameOfCalledFunction(functionCall));
    }

    public <T extends Comparable<T>> List<T> computeAllNodeAttributes(final Function<StatementNode, T> extractAttribute)
            throws ParserConfigurationException, SAXException, IOException {
        final List<T> resultList = ListTools
                .ofM(this.documentElement.getDescendentAttributes(node -> true, extractAttribute));
        Collections.sort(resultList);
        return resultList;
    }

    public int getNumberOfNodes() throws ParserConfigurationException, SAXException, IOException {
        return this.documentElement.getNumberOfDescendants(true);
    }

    public Set<String> computeUsedModels() {
        final Set<String> result = new HashSet<>();
        final StatementNode rootNode = this.documentElement;
        final Set<StatementNode> useStatements = rootNode.allDescendents(StatementNode.isUseStatement, false);
        for (final StatementNode useStatement : useStatements) {
            final String usedModuleName = useStatement.getChild(1).getTextContent();
            FortranModule.LOGGER.debug("found use statement: {}, module name: {}", useStatement.getTextContent(),
                    usedModuleName);
            result.add(usedModuleName);
        }
        return result;
    }

}
