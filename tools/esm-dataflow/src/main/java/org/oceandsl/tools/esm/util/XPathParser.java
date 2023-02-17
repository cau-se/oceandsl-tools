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
		//List<List<Node>> subRs = new ArrayList();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		List<Node> currentNList = new ArrayList();
		try {
			DocumentBuilder dbuilder = dbFactory.newDocumentBuilder();
			Document doc = dbuilder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().normalize();
			NodeList nList =doc.getElementsByTagName("program-stmt");
			
			
			for(int i = 0; i<nList.getLength();i++) {
				Node node = nList.item(i);
				
				currentNList.add(node);
				while(node.getNextSibling()!=null && node.getNextSibling().getNodeName().equals("end-program-stmt")) {
					currentNList.add(node.getNextSibling());
					node = node.getNextSibling();
				}
				//subRs.add(currentNList);
				
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
		return currentNList;
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
		String result=null;
		Element e = (Element) loopStatement;
		NodeList ctrlVar = e.getElementsByTagName("do-V");
		if(ctrlVar.getLength()>0) {
			Element nE= (Element) ctrlVar.item(0);
			result=nE.getElementsByTagName("n").item(0).getTextContent();
		}

		return result;
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
			//	System.out.println(arg.getElementsByTagName("n").getLength());
				NodeList ns =arg.getElementsByTagName("n");
				for(int j = 0;j<ns.getLength();j++) {
					Element n = (Element)ns.item(j);
				//	System.out.println(n.getTextContent());
					result.add(n.getTextContent());
				}
			}
		}
		return result;
	}


	public static List<String> getNamesFromStatement(Node stmt) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)stmt;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getTextContent());	
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
		    result.add(nameElems.item(i).getTextContent());	
		}
		
		
		return result.get(0);
	}






	public static String getStructureConstructorIdentifier(Node cons) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)cons;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getTextContent());	
		}
		
		
		return result.get(0);
	}


	public static String getPartRefNodeIdentifier(Node partRef) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)partRef;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getTextContent());	
		}
		
		
		return result.get(0);
	}


	public static List<String> getArgumentList(Node procedure) {
		List<String> result = new ArrayList<String>();
		Element e = (Element)procedure;
		NodeList nameElems = e.getElementsByTagName("element");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i).getTextContent());	
		}
		
		
		return result;
	}



	public static String getsubroutineId(List<Node> body) {
		//List<String> result = new ArrayList<String>();
		Element e = (Element)body.get(0);
	//	System.out.println("name" +e.getNodeName().equals("subroutine-stmt"));
		NodeList nameElems = e.getElementsByTagName("n");
	//	System.out.println("size: "+ nameElems.getLength());
		//for(int i=0;i<nameElems.getLength();i++) {
			
		  //  result.add(nameElems.item(0).getTextContent());	
		//}
		
		return nameElems.item(0).getTextContent();
		//return result.get(0);
	}

	public static String getFunctionId(List<Node> body) {
		//List<String> result = new ArrayList<String>();
		Element e = (Element)body.get(0);
		NodeList nameElems = e.getElementsByTagName("n");
		
		//for(int i=0;i<nameElems.getLength();i++) {
		   // result.add(nameElems.item(i).getNodeValue());	
		//}
		
		return nameElems.item(0).getTextContent();
		//return result.get(0);
	}


	public static String getCallStmtId(Node callStmt) {
		//List<String> result = new ArrayList<String>();
		Element e = (Element)callStmt;
		NodeList nameElems = e.getElementsByTagName("n");
		
	//	for(int i=0;i<nameElems.getLength();i++) {
		//**/    result.add(nameElems.item(i).getNodeValue());	
	//	}
		
		
		return nameElems.item(0).getTextContent();
	}


	public static String getCommonBlockId(Node commonBlock) {
		//List<String> result = new ArrayList<String>();
		Element e = (Element)commonBlock;
		System.out.println(e.getNodeName().equals("common-stmt"));
		NodeList nameElems = e.getElementsByTagName("n");
		
	//	for(int i=0;i<nameElems.getLength();i++) {
	//	    result.add(nameElems.item(i).getNodeValue());	
	//	}
		
		
		//return result.get(0);
		return nameElems.item(0).getTextContent();
	}




	public static List<Node> getPotentialFuncs(Node assigningContent) {
		
		List<Node> result = new ArrayList<Node>();
		Element e = (Element)assigningContent;
		//check if named expressions exist
		NodeList nameExpr = e.getElementsByTagName("named-E");
		
		if(nameExpr.getLength()>0) {
			for(int i=0;i<nameExpr.getLength();i++) {
				//check if parenthesis exist
				  Element currentNode = (Element) nameExpr.item(i);
				  NodeList parensElems =currentNode.getElementsByTagName("parens-R");
				  if(parensElems.getLength()>0) {
					  //check if arguments exists
					  for(int j=0; j<parensElems.getLength();j++) {
						  parensElems.item(j);
						  NodeList elemElems =currentNode.getElementsByTagName("element");
						  if(elemElems.getLength()>0) {
							  //all checls passed! Add the named expression to the list!
							  result.add(currentNode);
						  }
					  }
				  }
				}
		}
		
		return result;
	}


	public static List<Node> getNonArgsFunc(Node assigningContent) {
		List<Node> result = new ArrayList<Node>();
		Element e = (Element)assigningContent;
		//check if named expressions exist
		NodeList nameExpr = e.getElementsByTagName("named-E");
		
		if(nameExpr.getLength()>0) {
			for(int i=0;i<nameExpr.getLength();i++) {
				//check if parenthesis exist
				  Element currentNode = (Element) nameExpr.item(i);
				  NodeList parensElems =currentNode.getElementsByTagName("parens-R");
				  if(parensElems.getLength()>0) {
					  //check if arguments exists
					  for(int j=0; j<parensElems.getLength();j++) {
						  parensElems.item(j);
						  NodeList elemElems =currentNode.getElementsByTagName("element");
						  if(elemElems.getLength()==0) {
							  //parenthesis but no args. Add
							  //all checls passed! Add the named expression to the list!
							  result.add(currentNode);
						  }
					  }
				  }
				}
		}
		
		return result;
	}


	public static List<Node> getNames(Node assigningContent) {
		List<Node> result = new ArrayList<Node>();
		Element e = (Element)assigningContent;
		NodeList nameElems = e.getElementsByTagName("n");
		
		for(int i=0;i<nameElems.getLength();i++) {
		    result.add(nameElems.item(i));	
		}
		
		
		return result;
	}
	
	
	

	
	public static List<String> getArraysDecl(List<List<Node>> bodies){
		List<String> result = new ArrayList<String>();
			
		for(List<Node> body : bodies) { //iterate throgh bodiees
				for(Node node : body) { //get a body
					if(node.getNodeName().equals("T-decl-stmt")) {//find decl stmt
						if(node.getNodeType()==Node.ELEMENT_NODE) {
							Element elem = (Element) node;
							NodeList elems =  elem.getElementsByTagName("EN-decl"); //get entity decl
							for(int i = 0;i<elems.getLength();i++) {//iterate
								Element endecl =(Element)elems.item(i);
								NodeList a_specs = endecl.getElementsByTagName("array-spec"); // is array?
								if(a_specs.getLength()>0) {
									result.add(endecl.getElementsByTagName("n").item(0).getTextContent()); //blacklist!!
								}
							}
						}
						
					}
				}
			}
			return result;
		}
	
	//-----------------------
		public static Node getAssigningContent(Node stmt) { //right
			Element e = (Element)stmt;
			NodeList elems = e.getElementsByTagName("E-2");
			return  elems.item(0);
		}

		public static Node getAssignedContent(Node stmt) { //left
			Element e = (Element)stmt;
			NodeList elems = e.getElementsByTagName("E-1");
			return  elems.item(0);
		}
		//------------------------------------


}
