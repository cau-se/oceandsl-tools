package cau.agse.hs.staticfortran.model;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import cau.agse.hs.tools.DataStructureTools;
import cau.agse.hs.utils.lists.misc.ListTools;
import cau.agse.hs.utils.misc.Pair;
import lombok.Getter;

public class FortranModuleModel {

	@Getter Path xmlFilePath;
	@Getter Set<String> usedModules;
	@Getter Set<String> specifiedSubroutines;
	@Getter Set<String> specifiedFunctions;
	@Getter Set<String> specifiedOperations;
	@Getter String moduleName;
	ASTNode documentElement;
	
	public FortranModuleModel(Path xmlFilePath) throws ParserConfigurationException, SAXException, IOException { 
		this.xmlFilePath = xmlFilePath;
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(xmlFilePath.toFile());
		doc.getDocumentElement().normalize();
		documentElement = new ASTNode(doc.getDocumentElement());
		ASTNode moduleStatement = DataStructureTools.getUniqueElementIfNonEmpty(documentElement.allDescendents(ASTNode.isModuleStatement, true), null);
		this.moduleName = (moduleStatement == null)? "<no module>" : moduleStatement.getChild(1).getTextContent();

		this.usedModules = computeUsedModels();
		this.specifiedSubroutines = computeSubroutineDeclarations();
		this.specifiedFunctions = computeFunctionDeclarations();
		this.specifiedOperations = new HashSet<>();
		this.specifiedOperations.addAll(specifiedFunctions);
		this.specifiedOperations.addAll(specifiedSubroutines);
		
		printSummary(System.out);
	}
	
	public void printSummary(PrintStream out) {
		out.println("# Summary");
		out.println(" [xmlFilePath]          " + xmlFilePath);
		out.println(" [moduleName]           " + moduleName);
		out.println(" [used modules]         ");
		usedModules.forEach(name -> System.out.println("  * " + name));
		
		out.println(" [subroutine definitions] ");
		specifiedSubroutines.forEach(name -> System.out.println("  * " + name));
		
		out.println(" [function definitions] ");
		specifiedFunctions.forEach(name -> System.out.println("  * " + name));
	}
	
	public void printXML() throws ParserConfigurationException, SAXException, IOException {
		Set<String> nodeTypes = new HashSet<>();
		ASTNode.printNode(documentElement, 0);
	
		nodeTypes.forEach(System.out::println);
		System.out.println("# nodes: " + documentElement.allDescendents(node -> true, true).size());
	}
	
	public Set<String> computeSubroutineDeclarations() throws ParserConfigurationException, SAXException, IOException {
		return documentElement.getDescendentAttributes(ASTNode.isSubroutineStatement, subroutineNode -> ASTNode.getNameOfOperation(subroutineNode));
	}
	
	public Set<String> computeFunctionDeclarations() throws ParserConfigurationException, SAXException, IOException {
		return documentElement.getDescendentAttributes(ASTNode.isFunctionStatement, functionNode -> ASTNode.getNameOfOperation(functionNode));
	}
	
	/* public Set<String> getAllNamesInNamedEChains() throws ParserConfigurationException, SAXException, IOException {
		return documentElement.getDescendentAttributes(ASTNode.namedExpressionFunctionCall, ASTNode.nameOfCalledFunction);
	} // node -> ASTNode.getFirstChildChain(node, 4).getTextContent() */
	
	public List<Pair<String, String>> operationCalls() throws ParserConfigurationException, SAXException, IOException {
		return ListTools.ofM(subroutineCalls(), functionCalls(), Pair.getComparatorFirstSecond());
	}
	
	public List<Pair<String, String>> operationCalls(Predicate<Node> callPredicate, Function<Node, String> calledOperation) {
		Set<Pair<String, String>> result = new HashSet<>(); // Check for double entries
		Set<ASTNode> callStatements = documentElement.allDescendents(callPredicate, true);
		for (ASTNode callStatement : callStatements) {
			String callee = calledOperation.apply(callStatement);
			String caller = callStatement.getNameOfContainingOperation();
			result.add(new Pair<>(caller, callee));
		}
		
		return ListTools.ofM(result, Pair.getComparatorFirstSecond());
	}
	
	public List<Pair<String, String>> subroutineCalls() throws ParserConfigurationException, SAXException, IOException {
		return operationCalls(ASTNode.isCallStatement, subroutineCall -> ASTNode.nameOfCalledOperation(subroutineCall));
		/*
		Set<Pair<String, String>> result = new HashSet<>(); // Check for double entries
		Set<ASTNode> callStatements = documentElement.allDescendents(ASTNode.isCallStatement, true);
		for (ASTNode callStatement : callStatements) {
			// Issue: Here we do not know which function is called, if there are several xms files 
			// (Fortran files) containing the same function. This needs to be resolved on a higher
			// level (i.e., the calling class that has access to the set of operation declarations
			// by all the modules). Therefore, we here only return a simple list of pairs of strings.
			String callee = ASTNode.nameOfCalledOperation(callStatement);
			String caller = callStatement.getNameOfContainingOperation();
			result.add(new Pair<>(caller, callee));
		}
		
		return ListTools.ofM(result, Pair.getComparatorFirstSecond());*/
	}
	
	public List<Pair<String, String>> functionCalls() throws ParserConfigurationException, SAXException, IOException {
		return operationCalls(ASTNode.namedExpressionFunctionCall, functionCall -> ASTNode.nameOfCalledFunction(functionCall));
		
		/*
		Set<Pair<String, String>> result = new HashSet<>(); // Check for double entries
		Set<ASTNode> functionCalls = documentElement.allDescendents(ASTNode.namedExpressionFunctionCall, true);
		for (ASTNode functionCall : functionCalls) { 
			String callee = ASTNode.nameOfCalledFunction(functionCall);
			String caller = functionCall.getNameOfContainingOperation();
			result.add(new Pair<>(caller, callee));
		}
		
		return ListTools.ofM(result, Pair.getComparatorFirstSecond()); */
	}

	
	
	public <T extends Comparable<T>> List<T> computeAllNodeAttributes(Function<ASTNode, T> extractAttribute) throws ParserConfigurationException, SAXException, IOException {
		List<T> resultList = ListTools.ofM(documentElement.getDescendentAttributes(node -> true, extractAttribute));
		Collections.sort(resultList);
		return resultList;
	}
	
	public int getNumberOfNodes() throws ParserConfigurationException, SAXException, IOException {
		return documentElement.getNumberOfDescendants(true);
	}
	
	public Set<String> computeUsedModels() {
		Set<String> result = new HashSet<>();
		ASTNode rootNode = documentElement;
		Set<ASTNode> useStatements = rootNode.allDescendents(ASTNode.isUseStatement, false);
		for (ASTNode useStatement : useStatements) {
			String usedModuleName = useStatement.getChild(1).getTextContent();
			System.out.println("found use statement: " + useStatement.getTextContent() + ", module name: " + usedModuleName);
			result.add(usedModuleName);
		}
		return result;
	}

}
