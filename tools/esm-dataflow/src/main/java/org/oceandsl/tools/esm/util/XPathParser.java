package org.oceandsl.tools.esm.util;

import java.util.List;

import javax.xml.xpath.*;
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
	public static String getMain(String xml) {
		return null;
	}
	
	/**
	 * Return list of bodies of subroutines
	 * @param xml
	 * @return return list of subroutine bodies
	 */
	public static List<String> getSubroutineContents(String xml){
		return null;
	}
	
	public static List<String>getCallStmts(String xml){
		return null;
	}
	/**
	 * Return list of bodies of functions
	 * @param xml
	 * @return return list of functions bodies
	 */
	public static List<String> getFuncContents(String xml){
		return null;
	}
	
	public static List<String>getCommonBlocks(String xml){
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
