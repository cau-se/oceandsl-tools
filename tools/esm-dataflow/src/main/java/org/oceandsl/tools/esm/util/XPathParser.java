package org.oceandsl.tools.esm.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
public class XPathParser {
	
	
	/**
	 * Return list of bodies of subroutines
	 * @param xml
	 * @return return list of subroutine bodies
	 */
	public static List<List<Node>> getSubroutineContents(String xml){
		List<List<Node>> subRs = new ArrayList<List<Node>>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dbuilder = dbFactory.newDocumentBuilder();
			Document doc = dbuilder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().normalize();
			NodeList nList =doc.getElementsByTagName("subroutine-stmt");
			
			
			for(int i = 0; i<nList.getLength();i++) {
				Node node = nList.item(i);
				List<Node> currentNList = new ArrayList<Node>();
				currentNList.add(node);
				while(node.getNextSibling()!=null && !node.getNextSibling().getNodeName().equals("end-subroutine-stmt")) {
					currentNList.add(node.getNextSibling());
					node = node.getNextSibling();
				}
				subRs.add(currentNList);
				
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subRs;
		
	}
	

	/**
	 * Return list of bodies of functions
	 * @param xml
	 * @return return list of functions bodies
	 */
	public static List<List<Node>> getFuncContents(String xml){
		List<List<Node>> subRs = new ArrayList();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dbuilder = dbFactory.newDocumentBuilder();
			Document doc = dbuilder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().normalize();
			NodeList nList =doc.getElementsByTagName("function-stmt");
			
			
			for(int i = 0; i<nList.getLength();i++) {
				Node node = nList.item(i);
				List<Node> currentNList = new ArrayList();
				currentNList.add(node);
				while(node.getNextSibling()!=null && node.getNextSibling().getNodeName().equals("end-function-stmt")) {
					currentNList.add(node.getNextSibling());
					node = node.getNextSibling();
				}
				subRs.add(currentNList);
				
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subRs;
	}
	
	
	public static List<Node> getMain(String xml) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<Node> getCallStmts(List<Node> body){
		
		List<Node> result = new ArrayList<Node>();
		for(Node node:body) {
			if(node.getNodeName().equals("call-stmt")) {
				result.add(node);
			}
		}
		return result;
		
	}
	
	
	public static List<Node>getCommonBlocks(List<Node>body){
		List<Node> result = new ArrayList<Node>();
		for(Node node:body) {
			if(node.getNodeName().equals("common-stmt")) {
				result.add(node);
			}
		}
		return result;
		
		
	}


	public static List<Node> getIfElseStmts(List <Node>body) {
		List<Node> result = new ArrayList<Node>();
		for(Node node:body) {
			if(node.getNodeName().equals("if-then-stmt")) {
				result.add(node);
			}
		}
		return result;
	}


	public static List<Node> getSelectStmts(List<Node> body) {
		List<Node> result = new ArrayList<Node>();
		for(Node node:body) {
			if(node.getNodeName().equals("select-case-stmt")) {
				result.add(node);
			}
		}
		return result;
		
	}


	public static List<Node> getLoopCtrlStmts(List <Node> body) {
		List<Node> result = new ArrayList<Node>();
		for(Node node:body) {
			if(node.getNodeName().equals("do-stmt")) {
				result.add(node);
			}
		}
		return result;
		
		}


	public static String getLoopControlVar(Node loopStatement) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<Node> getAssignmentStmts(List <Node> body) {
		List<Node> result = new ArrayList<Node>();
		for(Node node:body) {
			if(node.getNodeName().equals("a-stmt")) {
				result.add(node);
			}
		}
		return result;
		
	
	}
	



	public static List<String> callHasArgs(Node callStmt) {
		List<String>result = new ArrayList <String> ();
		if(callStmt instanceof Element) {
			Element e = (Element)callStmt;
			NodeList args = e.getElementsByTagName("arg");
			
			for(int i = 0; i<args.getLength();i++) {
				Element arg = (Element)args.item(i);
				String name=arg.getElementsByTagName("n").item(0).getNodeValue();
				result.add(name);
			}
		}
		return result;
	}


	public static List<String> getNamesFromStatement(Node stmt) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)stmt;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getNodeValue());	
		}
		
		
		return result;
	}

	
	/**
	 * Get variable name frome left part of an assignment as a string
	 * @param aStmt
	 * @return
	 */
	public static String getAssignTargetIdentifier(Node left) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)left;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getNodeValue());	
		}
		
		
		return result.get(0);
	}






	public static String getStructureConstructorIdentifier(Node cons) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)cons;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getNodeValue());	
		}
		
		
		return result.get(0);
	}


	public static String getPartRefNodeIdentifier(Node partRef) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)partRef;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getNodeValue());	
		}
		
		
		return result.get(0);
	}


	public static List<String> getArgumentList(Node procedure) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)procedure;
		NodeList nameElems = e.getElementsByTagName("element");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getNodeValue());	
		}
		
		
		return result;
	}



	public static String getsubroutineId(List<Node> body) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)body;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getNodeValue());	
		}
		
		
		return result.get(0);
	}

	public static String getFunctionId(List<Node> body) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)body;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getNodeValue());	
		}
		
		
		return result.get(0);
	}


	public static String getCallStmtId(Node callStmt) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)callStmt;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getNodeValue());	
		}
		
		
		return result.get(0);
	}


	public static String getCommonBlockId(Node commonBlock) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)commonBlock;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getNodeValue());	
		}
		
		
		return result.get(0);
	}


	public static boolean isPotentialFunction(String assigningContent) {
		// TODO Auto-generated method stub
		return false;
	}


	public static boolean funcHasArgs(String assigningContent) {
		// TODO Auto-generated method stub
		return false;
	}


	public static List<Node> getPoptentialFuncs(List<Node> assigningContent) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<Node> getNonArgsFunc(List<Node> assigningContent) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<Node> getNames(List<Node> assigningContent) {
		// TODO Auto-generated method stub
		return null;
	}


}
