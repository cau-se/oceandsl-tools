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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
public class XPathParser {
	

	/**
	 * Return number of subroutine definitions in given xml
	 * @param xml - XML string which is analyzed
	 * @return numer of Suboutine definitions
	 */
	public static int getSubroutineNum(String xml) {
		return 0;
	}

	
	/**
	 * Return number of function definitions in given xml
	 * @param xml - XML string which is analyzed
	 * @return numer of function definitions
	 */
	public static int getFuncNum(String xml) {
		return 0;
	}
	public static List<Node> getMain(String xml) {
		return null;
	}
	
	/**
	 * Return list of bodies of subroutines
	 * @param xml
	 * @return return list of subroutine bodies
	 */
	public static List<List<Node>> getSubroutineContents(String xml){
		List<String> result = new ArrayList();
		List<List<Node>> subRs = new ArrayList();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dbuilder = dbFactory.newDocumentBuilder();
			Document doc = dbuilder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().normalize();
			NodeList nList =doc.getElementsByTagName("subroutine-stmt");
			
			
			for(int i = 0; i<nList.getLength();i++) {
				Node node = nList.item(i);
				List<Node> currentNList = new ArrayList();
				while(node.getNextSibling()!=null && node.getNextSibling().getNodeName().equals("end-subroutine-stmt")) {
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
	
	public static List<List<Node>> getCallStmts(String xml){
		//TODO USE HENNING
		return null;
		
	}
	/**
	 * Return list of bodies of functions
	 * @param xml
	 * @return return list of functions bodies
	 */
	public static List<List<Node>> getFuncContents(String xml){
		List<String> result = new ArrayList();
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
	
	public static List<String>getCommonBlocks(String xml){
		//Use Henning
		return null;
	}


	public static List<String> getIfElseStmts(String xml) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<String> getSelectStmts(String xml) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<String> getLoopCtrlStmts(String xml) {
		// TODO Auto-generated method stub
		return null;
	}


	public static String getLoopControlVar(String loopCtrl) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<String> getAssignmentStmts(String xml) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<String> callHasArgs(String callStmt) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<String> getNamesFromStatement(String stmt) {
		// TODO Auto-generated method stub
		return null;
	}


	public static String getAssignTargetIdentifier(String stmt) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<String> assignmentStatementHasPartRef(List<String> assigningContent) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<String> assignmentStatementHasStructureConstructor(List<String> assigningContent) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<String> convertListNodeToString(Object collectNames) {
		// TODO Auto-generated method stub
		return null;
	}


	public static String getStructureConstructorIdentifier(String s) {
		// TODO Auto-generated method stub
		return null;
	}


	public static String getPartRefNodeIdentifier(String s) {
		// TODO Auto-generated method stub
		return null;
	}


	public static List<String> getArgumentList(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
