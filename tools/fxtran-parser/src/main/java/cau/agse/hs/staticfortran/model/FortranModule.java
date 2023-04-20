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
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import cau.agse.hs.tools.DataStructureTools;
import cau.agse.hs.utils.lists.misc.ListTools;
import cau.agse.hs.utils.misc.Pair;
import lombok.Getter;

/**
 * @author Henning Schnoor
 *
 */
public class FortranModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(FortranModule.class);

    @Getter
    private final Path xmlFilePath;
    @Getter
    private final Set<String> usedModules;
    @Getter
    private final Set<String> specifiedSubroutines;
    @Getter
    private final Set<String> specifiedFunctions;
    @Getter
    private final Set<String> specifiedOperations;
    @Getter
    private final String moduleName;
    StatementNode documentElement;

    public FortranModule(final Path xmlFilePath) throws ParserConfigurationException, SAXException, IOException {
        this.xmlFilePath = xmlFilePath;
        final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        final Document doc = builder.parse(xmlFilePath.toFile());
        doc.getDocumentElement().normalize();
        this.documentElement = new StatementNode(doc.getDocumentElement());
        final StatementNode moduleStatement = DataStructureTools.getUniqueElementIfNonEmpty(
                this.documentElement.allDescendents(StatementNode.isModuleStatement, true), null);
        this.moduleName = (moduleStatement == null) ? "<no module>" : moduleStatement.getChild(1).getTextContent();

        this.usedModules = this.computeUsedModels();
        this.specifiedSubroutines = this.computeSubroutineDeclarations();
        this.specifiedFunctions = this.computeFunctionDeclarations();
        this.specifiedOperations = new HashSet<>();
        this.specifiedOperations.addAll(this.specifiedFunctions);
        this.specifiedOperations.addAll(this.specifiedSubroutines);

        this.printSummary();
    }

    public void printSummary() {
        FortranModule.LOGGER.debug("# Summary");
        FortranModule.LOGGER.debug(" [xmlFilePath]          {}", this.xmlFilePath);
        FortranModule.LOGGER.debug(" [moduleName]           {}", this.moduleName);
        FortranModule.LOGGER.debug(" [used modules]         ");
        this.usedModules.forEach(name -> FortranModule.LOGGER.debug("  * {}", name));

        FortranModule.LOGGER.debug(" [subroutine definitions] ");
        this.specifiedSubroutines.forEach(name -> FortranModule.LOGGER.debug("  * {}", name));

        FortranModule.LOGGER.debug(" [function definitions] ");
        this.specifiedFunctions.forEach(name -> FortranModule.LOGGER.debug("  * {}", name));
    }

    public void printXML() throws ParserConfigurationException, SAXException, IOException {
        final Set<String> nodeTypes = new HashSet<>();
        StatementNode.printNode(this.documentElement, 0);

        nodeTypes.forEach(System.out::println);
        System.out.println("# nodes: " + this.documentElement.allDescendents(node -> true, true).size());
    }

    public Set<String> computeSubroutineDeclarations() throws ParserConfigurationException, SAXException, IOException {
        return this.documentElement.getDescendentAttributes(StatementNode.isSubroutineStatement,
                subroutineNode -> StatementNode.getNameOfOperation(subroutineNode));
    }

    public Set<String> computeFunctionDeclarations() throws ParserConfigurationException, SAXException, IOException {
        return this.documentElement.getDescendentAttributes(StatementNode.isFunctionStatement,
                functionNode -> StatementNode.getNameOfOperation(functionNode));
    }

    /*
     * public Set<String> getAllNamesInNamedEChains() throws ParserConfigurationException,
     * SAXException, IOException { return
     * documentElement.getDescendentAttributes(ASTNode.namedExpressionFunctionCall,
     * ASTNode.nameOfCalledFunction); } // node -> ASTNode.getFirstChildChain(node,
     * 4).getTextContent()
     */

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
        return this.operationCalls(StatementNode.isCallStatement,
                subroutineCall -> StatementNode.nameOfCalledOperation(subroutineCall));
        /*
         * Set<Pair<String, String>> result = new HashSet<>(); // Check for double entries
         * Set<ASTNode> callStatements = documentElement.allDescendents(ASTNode.isCallStatement,
         * true); for (ASTNode callStatement : callStatements) { // Issue: Here we do not know which
         * function is called, if there are several xms files // (Fortran files) containing the same
         * function. This needs to be resolved on a higher // level (i.e., the calling class that
         * has access to the set of operation declarations // by all the modules). Therefore, we
         * here only return a simple list of pairs of strings. String callee =
         * ASTNode.nameOfCalledOperation(callStatement); String caller =
         * callStatement.getNameOfContainingOperation(); result.add(new Pair<>(caller, callee)); }
         *
         * return ListTools.ofM(result, Pair.getComparatorFirstSecond());
         */
    }

    public List<Pair<String, String>> functionCalls() throws ParserConfigurationException, SAXException, IOException {
        return this.operationCalls(StatementNode.namedExpressionFunctionCall,
                functionCall -> StatementNode.nameOfCalledFunction(functionCall));

        /*
         * Set<Pair<String, String>> result = new HashSet<>(); // Check for double entries
         * Set<ASTNode> functionCalls =
         * documentElement.allDescendents(ASTNode.namedExpressionFunctionCall, true); for (ASTNode
         * functionCall : functionCalls) { String callee =
         * ASTNode.nameOfCalledFunction(functionCall); String caller =
         * functionCall.getNameOfContainingOperation(); result.add(new Pair<>(caller, callee)); }
         *
         * return ListTools.ofM(result, Pair.getComparatorFirstSecond());
         */
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
