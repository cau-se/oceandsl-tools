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

/**
 * @author Henning Schnoor
 *
 */

package org.oceandsl.tools.fxca.model;

import java.io.IOException;
import java.io.PrintStream;
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

//CHECKSTYLE:OFF
import org.oceandsl.tools.fxca.tools.ListTools;
import org.oceandsl.tools.fxca.tools.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
//CHECKSTYLE:ON

import lombok.Getter;

public class FortranModule {

	@Getter private Path xmlFilePath;
	@Getter private Set<String> usedModules;
	// @Getter Set<String> specifiedSubroutines;
	// @Getter Set<String> specifiedFunctions;
	@Getter private Set<String> specifiedOperations;
	@Getter private String moduleName;
	@Getter private boolean namedModule;
	StatementNode documentElement;
	
	public FortranModule(Path xmlFilePath) throws ParserConfigurationException, SAXException, IOException { 
		this.xmlFilePath = xmlFilePath;
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(xmlFilePath.toFile());
		doc.getDocumentElement().normalize();
		documentElement = new StatementNode(doc.getDocumentElement());
		StatementNode moduleStatement = ListTools.getUniqueElementIfNonEmpty(documentElement.allDescendents(StatementNode.isModuleStatement, true), null);
		this.namedModule = (moduleStatement != null);
		this.moduleName = namedModule? moduleStatement.getChild(1).getTextContent() : "<no module>";
		this.usedModules = computeUsedModels();
		// this.specifiedSubroutines = computeSubroutineDeclarations();
		// this.specifiedFunctions = computeFunctionDeclarations();
		this.specifiedOperations = computeOperationDeclarations();
		// this.specifiedOperations.addAll(specifiedFunctions);
		// this.specifiedOperations.addAll(specifiedSubroutines);
		
		printSummary(System.out);
	}
	
	public void printSummary(PrintStream out) {
		out.println("# Summary");
		out.println(" [xmlFilePath]          " + xmlFilePath);
		out.println(" [moduleName]           " + moduleName);
		out.println(" [used modules]         ");
		usedModules.forEach(name -> System.out.println("  * " + name));
		
		out.println(" [operation definitions] ");
		specifiedOperations.forEach(name -> System.out.println("  * " + name));
	}
	
	public void printXML() throws ParserConfigurationException, SAXException, IOException {
		Set<String> nodeTypes = new HashSet<>();
		StatementNode.printNode(documentElement, 0);
	
		nodeTypes.forEach(System.out::println);
		System.out.println("# nodes: " + documentElement.allDescendents(node -> true, true).size());
	}
	
	/*
	@Deprecated
	private Set<String> computeSubroutineDeclarations() throws ParserConfigurationException, SAXException, IOException {
		return documentElement.getDescendentAttributes(StatementNode.isSubroutineStatement, subroutineNode -> StatementNode.getNameOfOperation(subroutineNode));
	}
	*/
	
    /*
	@Deprecated
	private Set<String> computeFunctionDeclarations() throws ParserConfigurationException, SAXException, IOException {
		return documentElement.getDescendentAttributes(StatementNode.isFunctionStatement, functionNode -> StatementNode.getNameOfOperation(functionNode));
	}
	*/
	
	public Set<String> computeOperationDeclarations() throws ParserConfigurationException, SAXException, IOException {
		return documentElement.getDescendentAttributes(StatementNode.isOperationStatement, operationNode -> StatementNode.getNameOfOperation(operationNode));
	}

	
	/* public Set<String> getAllNamesInNamedEChains() throws ParserConfigurationException, SAXException, IOException {
		return documentElement.getDescendentAttributes(ASTNode.namedExpressionFunctionCall, ASTNode.nameOfCalledFunction);
	} // node -> ASTNode.getFirstChildChain(node, 4).getTextContent() */
	
	public List<Pair<String, String>> operationCalls() throws ParserConfigurationException, SAXException, IOException {
		return ListTools.ofM(subroutineCalls(), functionCalls(), Pair.getComparatorFirstSecond());
	}
	
	public List<Pair<String, String>> operationCalls(Predicate<Node> callPredicate, Function<Node, String> calledOperation) {
		Set<Pair<String, String>> result = new HashSet<>(); // Check for double entries
		Set<StatementNode> callStatements = documentElement.allDescendents(callPredicate, true);
		for (StatementNode callStatement : callStatements) {
			String callee = calledOperation.apply(callStatement);
			String caller = callStatement.getNameOfContainingOperation();
			result.add(new Pair<>(caller, callee));
		}
		
		return ListTools.ofM(result, Pair.getComparatorFirstSecond());
	}
	
	public List<Pair<String, String>> subroutineCalls() throws ParserConfigurationException, SAXException, IOException {
		return operationCalls(StatementNode.isCallStatement.and(StatementNode.isLocalAccess.negate()), subroutineCall -> StatementNode.nameOfCalledOperation(subroutineCall));
	}
	
	public List<Pair<String, String>> functionCalls() throws ParserConfigurationException, SAXException, IOException {
		return operationCalls(StatementNode.namedExpressionAccess.and(StatementNode.isLocalAccess.negate()), functionCall -> StatementNode.nameOfCalledFunction(functionCall));
	}
	
	public <T extends Comparable<T>> List<T> computeAllNodeAttributes(Function<StatementNode, T> extractAttribute) throws ParserConfigurationException, SAXException, IOException {
		List<T> resultList = ListTools.ofM(documentElement.getDescendentAttributes(node -> true, extractAttribute));
		Collections.sort(resultList);
		return resultList;
	}
	
	public int getNumberOfNodes() throws ParserConfigurationException, SAXException, IOException {
		return documentElement.getNumberOfDescendants(true);
	}
	
	public Set<String> computeUsedModels() {
		Set<String> result = new HashSet<>();
		StatementNode rootNode = documentElement;
		Set<StatementNode> useStatements = rootNode.allDescendents(StatementNode.isUseStatement, false);
		for (StatementNode useStatement : useStatements) {
			String usedModuleName = useStatement.getChild(1).getTextContent();
			System.out.println("found use statement: " + useStatement.getTextContent() + ", module name: " + usedModuleName);
			result.add(usedModuleName);
		}
		return result;
	}

}
