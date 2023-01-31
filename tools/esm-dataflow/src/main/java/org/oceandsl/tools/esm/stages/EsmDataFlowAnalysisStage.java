package org.oceandsl.tools.esm.stages;

import java.io.File;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.tools.ant.types.CharSet;
import org.oceandsl.tools.esm.util.FileContentsStageOutput;
import org.oceandsl.tools.esm.util.XPathParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import kieker.analysis.generic.graph.mtree.utils.Pair;
import teetime.framework.AbstractConsumerStage;

public class EsmDataFlowAnalysisStage extends AbstractConsumerStage<FileContentsStageOutput> {

	private List<String> dataflow;
	@Override
	protected void execute(FileContentsStageOutput element) throws Exception {

		XPath xpath = XPathFactory.newInstance().newXPath();
		
		
		for(File file : element.getFiles()) {
			//Document doc = builder.parse(file);
			
			//Extract xml file 
			
			String xml = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
			InputSource inputXML = new InputSource(new StringReader(xml));
			List<String> fileContent;
			List<List<Node>> subRoutineBodies = XPathParser.getSubroutineContents(xml);
			List<List<Node>> functionBodies = XPathParser.getFuncContents(xml);
			List<Node> main = XPathParser.getMain(xml);
			analyzeSubRoutines(subRoutineBodies);
			analyzeFunctions(functionBodies);
			analyzeMain(main);
			
			//TODO if main analyze it
			
		}
		
	}

	private void analyzeSubRoutines(List<List<Node>> subRoutineBodies) {
		
		List<String> dataflowInSub = new ArrayList();
		for(List<Node> body:subRoutineBodies) {
			String name = "TODO";
			List<String> commonBlocks = XPathParser.getCommonBlocks(name);
			String contentLine = "TODO";
			dataflow.add(contentLine);
			
			String dataFlowLine = "TODO";
			dataflowInSub = analyzeExecutionPart(body,commonBlocks, null, dataFlowLine);
			
			
		}
	}
	
	private void analyzeFunctions(List<List<Node>> funcBodies) {

		List<String> dataflowInFunc = new ArrayList();
		for (List<Node> body : funcBodies) {
			String name = "TODO";
			List<String> commonBlocks = XPathParser.getCommonBlocks(name);
			String contentLine = "TODO";
			dataflow.add(contentLine);

			String dataFlowLine = "TODO";
			dataflowInFunc = analyzeExecutionPart(body, commonBlocks, null, dataFlowLine);

		}
	}
	
	private void analyzeMain(List<Node> mainBodie) {
		List<String>dataflowInMain = new ArrayList();
		String name = "TODO";
		List<String> commonBlocks = XPathParser.getCommonBlocks(name);
		String contentLine = "TODO";
		dataflow.add(contentLine);

		String dataFlowLine = "TODO";
		dataflowInMain = analyzeExecutionPart(mainBodie, commonBlocks, null, dataFlowLine);
	}
    
	private List<String> analyzeExecutionPart(List<Node> body, List<String>commonBlocks, List<String>blacklist, String dataFlowLine) {
		
		List<String> dataflowExecPart = new ArrayList();
		String xml = convertNodeListToXml();
		//Dataflow call stmt
		List<List<Node>> callStmts = XPathParser.getCallStmts(xml);
		List<String> calls = analyzeCallStatements(xml, commonBlocks, blacklist);
		for(String call: calls) {
			dataflowExecPart.add(dataFlowLine+call);
		}
		//Dataflow ifelse
		List<String> ifElseStmts = XPathParser.getIfElseStmts(xml);
		List<String> ifElseReads = analyzeReadsFromStatements(ifElseStmts,commonBlocks, blacklist);
		for(String read: ifElseReads) {
			dataflowExecPart.add(dataFlowLine+read);
		}
		//Dataflow select case
		List<String> selectStmts = XPathParser.getSelectStmts(xml);
		List<String> selectReads = analyzeReadsFromStatements(selectStmts,commonBlocks, blacklist);
		for(String read: ifElseReads) {
			dataflowExecPart.add(dataFlowLine+read);
		}
		//Dataflow do while
		List<String> loopCtrlStmts = XPathParser.getLoopCtrlStmts(xml);
		for(String loopCtrl : loopCtrlStmts) {
			String loopAssignedVar = XPathParser.getLoopControlVar(loopCtrl);
			List<String> isInBlock = isVarFromCommonBlock(loopAssignedVar,commonBlocks);
			if (!isInBlock.isEmpty()) {
				String line = "TODOWRITE";
				dataflowExecPart.add(dataFlowLine+line);
			}
		}
		//Analyze Reads
		List<String> loopReads = analyzeReadsFromStatements(loopCtrlStmts,commonBlocks, blacklist);
		for(String read: loopReads) {
			dataflowExecPart.add(dataFlowLine+read);
		}
		
		//Dataflow assignments
		List<String> assignStmts = XPathParser.getAssignmentStmts(xml);
		List<String>assigns = analyzeAssignmentStatements(assignStmts, commonBlocks,blacklist);
		for(String assign : assigns) {
			dataflowExecPart.add(dataFlowLine+assign);
		}
		return null;
	}

	

	private String convertNodeListToXml() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<String> analyzeCallStatements(List<String>callStatements, List<String>commonBlocList, List<String>blacklist) {
		List<String> dataflowLineRest = new ArrayList();
		
		for(String callStmt : callStatements) {
			List<String> args = XPathParser.callHasArgs(callStmt);
			if(!args.isEmpty()) {
				checkArgsWithCommon(args, commonBlocList, dataflowLineRest);
				String line = "WRITE";
				dataflowLineRest.add(line);
			}
		}
		return dataflowLineRest;
	}
	
	

	private List<String> analyzeReadsFromStatements(List<String>stmts, List<String>commonBlocks, List<String>blacklist) {
		List<String> dataflowLineRest = new ArrayList();
		for(String stmt: stmts) {
			List<String> names = XPathParser.getNamesFromStatement(stmt);
			List<String> blocks = checkNamesWithCommon(names, commonBlocks);
			for(String block : blocks) {
				dataflowLineRest.add("Read;/{"+ block + "}"  );
			}
		}
		
		return dataflowLineRest;
	}
	


	private List<String> analyzeAssignmentStatements(List<String> stmts, List<String> commonBlocks, List<String> bl) {
		List<String> dataflowLinesRest = new ArrayList();
		
		for(String stmt : stmts) {
			List<String> assignedContent = getAssignedContent();
			List<String> assigningContent = getAssigningContent();
			
			String assignedVar = XPathParser.getAssignTargetIdentifier(stmt);
			List<String> blockIdentifierAssign=isVarFromCommonBlock(assignedVar, commonBlocks);
				
			List<String> partRefNodeList = XPathParser.assignmentStatementHasPartRef(assigningContent);
			List<String> structureConstructorNodes = XPathParser.assignmentStatementHasStructureConstructor(assigningContent);
				
			if(!partRefNodeList.isEmpty()) {
					List<String> blockIdentifierList = analyzePartRefNodesInAssignmentStmt(partRefNodeList, commonBlocks,bl,dataflowLinesRest);
					blockIdentifierList.addAll(checkNamesWithCommon(collectNames(assigningContent), commonBlocks));
					//blockIdentifierList  delete duplicates TODO;
					
					if(blockIdentifierList.size()==0) {
						for(String blockId : blockIdentifierList) {
							String line = "READ;/{" + blockId+"}/;";
							dataflowLinesRest.add(line);
						}
					}else if (blockIdentifierList.size()>0) {
						writeCommonDataflow(blockIdentifierAssign.get(0),blockIdentifierList, dataflowLinesRest);
					}else {
						String line = "WRITE;/{" + blockIdentifierList.get(0)+"}/;";
						dataflowLinesRest.add(line);
					}
					
			}else if(!structureConstructorNodes.isEmpty()) {
					for(String s: structureConstructorNodes) {
						assignedVar = XPathParser.getAssignTargetIdentifier(stmt);
						checkAssignWithCommon(assignedVar, commonBlocks,dataflowLinesRest);
						String functionId = XPathParser.getStructureConstructorIdentifier(s);
						
						String line = "READ;{"+functionId+"};";
						dataflowLinesRest.add(line);
					}
			}else {
				List<String> nameList = collectNames(assigningContent);
				List<String>blockIdentifierList = checkNamesWithCommon(nameList, commonBlocks);
				
				if(blockIdentifierAssign.size()>0) {
					
					if(blockIdentifierList.size()==0) {
						String line = "WRITE;/{"+blockIdentifierAssign.get(0)+"}/;";
						dataflowLinesRest.add(line);
					}else {
						writeCommonDataflow(blockIdentifierAssign.get(0),blockIdentifierList,dataflowLinesRest);
					}
				}else {
					blockIdentifierList = checkNamesWithCommon(nameList,commonBlocks);
					for(String blockId : blockIdentifierList) {
						String line = "READ;/{"+blockId+"}/;";
						dataflowLinesRest.add(line);
					}
				}
			}
			}
			
		
		return dataflowLinesRest;
	}
	
	private List<String> analyzePartRefNodesInAssignmentStmt(List<String> stmts, List<String> commonBlocks, List<String> bl, List<String> dfLinesRest) {
		List<String> blockIdentifierList = new ArrayList();
		for(String s : stmts) {
			String functionIdentifier = XPathParser.getPartRefNodeIdentifier(s);
			List<String>functionInCommonBlockList = isVarFromCommonBlock(functionIdentifier, commonBlocks);
			
			if(functionInCommonBlockList.size()>0) {
				blockIdentifierList.addAll(functionInCommonBlockList);
				List<String>args = XPathParser.getArgumentList(s);
				for(String arg : args) {
					blockIdentifierList.addAll(isVarFromCommonBlock(arg,commonBlocks));
				}
				
			}else if(!bl.contains(functionIdentifier)) {
				List<String> args = XPathParser.getArgumentList(s);
				if(args.size()>0) {
					for(String arg : args) {
						blockIdentifierList.addAll(isVarFromCommonBlock(arg, commonBlocks));
					}
					String line = "BOTH;{"+functionIdentifier+"};";
					blockIdentifierList.add(line);
				}else {
					String line = "READ;{"+functionIdentifier+"};";
					blockIdentifierList.add(line);
				}
			}


		}
		
		return blockIdentifierList;
	}

	private void writeCommonDataflow(Object blockIdentifierAssign, List<String> blockIdentifierList, List<String> dataflowLinesRest) {
		
		for(String blockIdentifier: blockIdentifierList) {
			/*if(blockIdentifierAssign[0].equals(blocIdentifier)){
			 * String line = "BOTH/{"+blockIdentifier}/;";
			 * dataflowLIneRest.add(line);
			 * }else{
			 * String line = "READ/{"+blockIdentifier}/;";
			 * dataflowLIneRest.add(line);
			 * line = "WRITE/{"+blockIdentifier}/;";
			 * dataflowLIneRest.add(line);
			 * }
			 * 
			 */
		}
		
	}
	
	private Object getCommonBlocks(String xml) {
		if(xml.isEmpty()) {
			return (Object)new ArrayList();
		}
		List<String>commonStmt = XPathParser.getCommonBlocks(xml);
		return commonStmt;
		
	}
	
	

	/**
	 * 
	 * @param assigningContentList
	 * @return
	 */

	private List<String> collectNames(List<String> assigningContentList) {
		List<String>nameList = new ArrayList();
		for(String assigningContent : assigningContentList) {
			
		}
		return nameList;
	}

	private List<String> isVarFromCommonBlock(String variable, List<String> commonBlocks) {
		List<String> blockIdentifierList = new ArrayList();
		for(String s:commonBlocks) {
			String blockIdentifier = s;//bloc[0]
			List<String> varList = new ArrayList();//= block[1];
			for(String var:varList) {
				if(variable.equals(var)) {
					blockIdentifierList.add(blockIdentifier);
				}
			}
		}
		return blockIdentifierList;
	}

	private void checkAssignWithCommon(String assignedVar, List<String> commonBlocks, List<String> dataflowLinesRest) {
		
		List<String> blockIdentifierList = isVarFromCommonBlock(assignedVar,commonBlocks);
		if(blockIdentifierList.size()>0) {
			for(String blockId: blockIdentifierList) {
				String line = "WRITE;/{"+ blockId+"}/;";
				dataflowLinesRest.add(line);
			}
		}
		
	}
	
	private void checkArgsWithCommon(List<String> args, List<String> commonBlockList, List<String> dataflowLineRest) {
		
		for(String arg :args) {
			List<String> blockIdentifierList = isVarFromCommonBlock(arg,commonBlockList);
			if(blockIdentifierList.size()>0) {
				for(String bId: blockIdentifierList) {
					String line = "WRITE;/{"+bId+"}/;";
					dataflowLineRest.add(line);
					}
			}
		}
		
	}

	private List<String> checkNamesWithCommon(List<String> names, List<String> commonBlocks) {
		List<String> blockIdentifierList = new ArrayList();
		for(String name: names) {
			blockIdentifierList.addAll(isVarFromCommonBlock(name, commonBlocks));
		}
		return blockIdentifierList;
	}
	


	//-----------------------
	private List<String> getAssigningContent() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<String> getAssignedContent() {
		// TODO Auto-generated method stub
		return null;
	}
	//------------------------------------

}
