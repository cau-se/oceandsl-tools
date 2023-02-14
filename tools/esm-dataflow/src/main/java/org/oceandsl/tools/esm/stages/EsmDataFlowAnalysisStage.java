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
import org.oceandsl.tools.esm.util.Output;
import org.oceandsl.tools.esm.util.XPathParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import kieker.analysis.generic.graph.mtree.utils.Pair;
import teetime.framework.AbstractConsumerStage;
import teetime.stage.basic.AbstractTransformation;

public class EsmDataFlowAnalysisStage extends AbstractTransformation<List<File>, Output> {

	private List<String> dataflow = new ArrayList<String>();
	private List<String> contentFile = new ArrayList<String>();
	@Override
	protected void execute(List<File> files) throws Exception {

	
		
		
		for(File file : files) {
			//Extract xml
			String xml = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
			// Get main structures
			List<List<Node>> subRoutineBodies = XPathParser.getSubroutineContents(xml);
			List<List<Node>> functionBodies = XPathParser.getFuncContents(xml);
			List<Node> main = XPathParser.getMain(xml);
			
			// analyze
			List<String> dfInSub =  analyzeSubRoutines(subRoutineBodies,file.getName());
			List<String> dfInFunc = analyzeFunctions(functionBodies, file.getName());
			List<String> dfInMain = analyzeMain(main, file.getName());
			// aggregate
			dataflow.addAll(dfInSub);
			dataflow.addAll(dfInFunc);
			dataflow.addAll(dfInMain);
			
			Output out = new Output();
			out.setDataflow(dataflow);
			out.setFileContent(contentFile);
			this.outputPort.send(out);
			
			
			
		}
		
	}

	private List<String> analyzeSubRoutines(List<List<Node>> subRoutineBodies, String fileId) {
		
		List<String> dataflowInSub = new ArrayList<String>();
		List<String> blackList=XPathParser.getArraysDecl(subRoutineBodies);
		for(List<Node> body:subRoutineBodies) {
			String name = XPathParser.getsubroutineId(body);
			List<Node> commonBlocks = XPathParser.getCommonBlocks(body);
			String contentLine = "{"+fileId+"};{"+name+"};SUBROUTINE";
			this.contentFile.add(contentLine);
			
			String dataFlowLine = "{"+fileId+"};"+name+"};";
			dataflowInSub = analyzeExecutionPart(body,commonBlocks, blackList, dataFlowLine);
		}
		return dataflowInSub;
	}
	
	private List<String> analyzeFunctions(List<List<Node>> funcBodies, String fileId) {

		List<String> dataflowInFunc = new ArrayList<String>();
		List<String> blackList=XPathParser.getArraysDecl(funcBodies);
		for (List<Node> body : funcBodies) {
			String name = XPathParser.getFunctionId(body);
			List<Node> commonBlocks = XPathParser.getCommonBlocks(body);
			String contentLine = "{"+fileId+"};{"+name+"};FUNCTION";
			this.contentFile.add(contentLine);

			String dataFlowLine = "{"+fileId+"};"+name+"};";
			dataflowInFunc = analyzeExecutionPart(body, commonBlocks, blackList, dataFlowLine);
		}
		return dataflowInFunc;
	}
	
	private List<String> analyzeMain(List<Node> mainBody, String fileId) {
		List<String>dataflowInMain = new ArrayList();
		
		List<Node> commonBlocks = XPathParser.getCommonBlocks(mainBody);


		String dataFlowLine = "{"+fileId+"}"+";main";
		dataflowInMain = analyzeExecutionPart(mainBody, commonBlocks, null, dataFlowLine);
		return dataflowInMain;
	}
    
	private List<String> analyzeExecutionPart(List<Node> body, List<Node>commonBlocks, List<String>blacklist, String dataFlowLine) {
		
		List<String> dataflowExecPart = new ArrayList<String>();
		
		//Dataflow call stmt
		List<Node> callStmts = XPathParser.getCallStmts(body);
		List<String> calls = analyzeCallStatements(callStmts, commonBlocks, blacklist);
		for(String call: calls) {
			dataflowExecPart.add(dataFlowLine+call);
		}
		//Dataflow ifelse
		List<Node> ifElseStmts = XPathParser.getIfElseStmts(body);
		List<String> ifElseReads = analyzeReadsFromStatements(ifElseStmts,commonBlocks, blacklist);
		for(String read: ifElseReads) {
			dataflowExecPart.add(dataFlowLine+read);
		}
		//Dataflow select case
		List<Node> selectStmts = XPathParser.getSelectStmts(body);
		List<String> selectReads = analyzeReadsFromStatements(selectStmts,commonBlocks, blacklist);
		for(String read: selectReads) {
			dataflowExecPart.add(dataFlowLine+read);
		}
		//Dataflow do while
		List<Node> loopCtrlStmts = XPathParser.getLoopCtrlStmts(body);
		for(Node loopCtrl : loopCtrlStmts) {
			String loopAssignedVar = XPathParser.getLoopControlVar(loopCtrl);
			List<String> isInBlock = isVarFromCommonBlock(loopAssignedVar,commonBlocks);
			if (!isInBlock.isEmpty()) {
				String line = "WRITE/{"+isInBlock.get(0)+"}/;";
				dataflowExecPart.add(dataFlowLine+line);
			}
		}
		//Analyze Reads
		List<String> loopReads = analyzeReadsFromStatements(loopCtrlStmts,commonBlocks, blacklist);
		for(String read: loopReads) {
			dataflowExecPart.add(dataFlowLine+read);
		}
		
		//Dataflow assignments
		List<Node> assignStmts = XPathParser.getAssignmentStmts(body);
		List<String>assigns = analyzeAssignmentStatements(assignStmts, commonBlocks,blacklist);
		for(String assign : assigns) {
			dataflowExecPart.add(dataFlowLine+assign);
		}
		return dataflowExecPart;
	}

	

	private List<String> analyzeCallStatements(List<Node> callStatements, List<Node>commonBlocList, List<String>blacklist) {
		List<String> dataflowLineRest = new ArrayList<String>();
		
		for(Node callStmt : callStatements) {
			List<String> args = XPathParser.callHasArgs(callStmt);
			if(!args.isEmpty()) {
				checkArgsWithCommon(args, commonBlocList, dataflowLineRest);
				String line = "WRITE;{"+XPathParser.getCallStmtId(callStmt);
				dataflowLineRest.add(line);
			}
		}
		return dataflowLineRest;
	}
	
	

	private List<String> analyzeReadsFromStatements(List<Node>stmts, List<Node>commonBlocks, List<String>blacklist) {
		List<String> dataflowLineRest = new ArrayList<String>();
		for(Node stmt: stmts) {
			List<String> names = XPathParser.getNamesFromStatement(stmt);
			List<String> blocks = checkNamesWithCommon(names, commonBlocks);
			for(String block : blocks) {
				dataflowLineRest.add("READ;/{"+ block + "}"  );
			}
		}
		
		return dataflowLineRest;
	}
	


	private List<String> analyzeAssignmentStatements(List<Node> stmts, List<Node> commonBlocks, List<String> bl) {
		List<String> dataflowLinesRest = new ArrayList();
		
		for(Node stmt : stmts) {
			Node assignedContent = XPathParser.getAssignedContent(stmt); //left part
			Node assigningContent = XPathParser.getAssigningContent(stmt); //right part
			
			
			String assignedVar = XPathParser.getAssignTargetIdentifier(stmt); //getName as Strin
			List<String> blockIdentifierAssign=isVarFromCommonBlock(assignedVar, commonBlocks);//left part is in common Block
			
			List<Node> namesRight = XPathParser.getNames(assigningContent); //neither funcs or arrays
			List<Node> potentialFuncs = XPathParser.getPotentialFuncs(assigningContent); //something with args
			List<Node> nonArgsFunc =  XPathParser.getNonArgsFunc(assigningContent); //func no args
			
			
			if(potentialFuncs.size()>0) { //potential function analysis
				List<String> blockIdentifierList = analyzePotentialFuncStmt(potentialFuncs, commonBlocks, bl, dataflowLinesRest);
			    blockIdentifierList.addAll(checkNamesWithCommon(blockIdentifierList, namesRight));
			    //delete duplicates
				
			    if(blockIdentifierAssign.size()==0) {
			    	for(String blockId : blockIdentifierList) {
			    		String line = "READ;/{" + blockId + "}/;";
						dataflowLinesRest.add(line);
			    	}
			    }else if(blockIdentifierList.size()>0) {
			    	writeCommonDataflow(blockIdentifierAssign.get(0),blockIdentifierList, dataflowLinesRest);
			    }else {
			    	String line = "WRITE;/{" + blockIdentifierList.get(0) + "}/;";
					dataflowLinesRest.add(line);
			    }
			}else if(nonArgsFunc.size()>0) {
				for(Node cons: nonArgsFunc) {
					checkAssignWithCommon(assignedVar, commonBlocks,dataflowLinesRest);
					String functionId = XPathParser.getStructureConstructorIdentifier(cons);
					String line = "READ;{"+functionId+"};";
					dataflowLinesRest.add(line);
				}
			}else {
				List<String> namesAsString =convertToString(namesRight);
				List<String>blockIdentifierList = checkNamesWithCommon(namesAsString, commonBlocks);
				
				if(blockIdentifierAssign.size()>0){
					
					if(blockIdentifierList.size()==0) {
						String line = "WRITE;/{"+blockIdentifierAssign.get(0)+"}/;";
						dataflowLinesRest.add(line);
					}else {
						writeCommonDataflow(blockIdentifierAssign.get(0),blockIdentifierList, dataflowLinesRest);
					}
				}else {
					for(String blockId: blockIdentifierList) {
						String line = "READ;/{"+blockId+"}/;";
						dataflowLinesRest.add(line);
					}
				}
			}
		}
						
		return dataflowLinesRest;
		}
	
	private List<String> convertToString(List<Node> namesRight) {
		List<String> result = new ArrayList<String>();
		for(Node node: namesRight) {
			result.add(node.getNodeValue());
		}
		return result;
	}

	private List<String> analyzePotentialFuncStmt(List<Node> funcs, List<Node> commonBlocks, List<String> bl,
			List<String> dataflowLinesRest) {
		List<String> blockIdentifierList = new ArrayList<String>();
		for (Node funcNode : funcs) {
			String functionIdentifier = XPathParser.getPartRefNodeIdentifier(funcNode);
			List<String>functionInCommonBlockList = isVarFromCommonBlock(functionIdentifier, commonBlocks);
			
			if(functionInCommonBlockList.size()>0) {
				blockIdentifierList.addAll(functionInCommonBlockList);
				List<String>args = XPathParser.getArgumentList(funcNode);
				for(String arg : args) {
					blockIdentifierList.addAll(isVarFromCommonBlock(arg,commonBlocks));
				}
				
			}else if(!bl.contains(functionIdentifier)) {
				List<String> args = XPathParser.getArgumentList(funcNode);
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



	private void writeCommonDataflow(String blockIdentifierAssign, List<String> blockIdentifierList, List<String> dataflowLinesRest) {
		
		for(String blockIdentifier: blockIdentifierList) {
			if(blockIdentifierAssign.equals(blockIdentifier)) {
				String line =  "BOTH;/{"+blockIdentifier+"}/;";
				dataflowLinesRest.add(line);
			}else {
				String line =  "READ;/{"+blockIdentifier+"}/;";
				dataflowLinesRest.add(line);				
				
				line =  "WRITE;/{"+blockIdentifierAssign+"}/;";
				dataflowLinesRest.add(line);
			}
		}
		
	}
	



	private List<String> isVarFromCommonBlock(String variable, List<Node> commonBlocks) {
		List<String> blockIdentifierList = new ArrayList();
		for(Node commonBlock:commonBlocks) {
			String blockIdentifier = XPathParser.getCommonBlockId(commonBlock);//bloc[0]
			List<String> varList = new ArrayList();//= block[1];
			for(String var:varList) {
				if(variable.equals(var)) {
					blockIdentifierList.add(blockIdentifier);
				}
			}
		}
		return blockIdentifierList;
	}

	private void checkAssignWithCommon(String assignedVar, List<Node> commonBlocks, List<String> dataflowLinesRest) {
		
		List<String> blockIdentifierList = isVarFromCommonBlock(assignedVar,commonBlocks);
		if(blockIdentifierList.size()>0) {
			for(String blockId: blockIdentifierList) {
				String line = "WRITE;/{"+ blockId+"}/;";
				dataflowLinesRest.add(line);
			}
		}
		
	}
	
	private void checkArgsWithCommon(List<String> args, List<Node> commonBlocList, List<String> dataflowLineRest) {
		
		for(String arg :args) {
			List<String> blockIdentifierList = isVarFromCommonBlock(arg,commonBlocList);
			if(blockIdentifierList.size()>0) {
				for(String bId: blockIdentifierList) {
					String line = "WRITE;/{"+bId+"}/;";
					dataflowLineRest.add(line);
					}
			}
		}
		
	}

	private List<String> checkNamesWithCommon(List<String> names, List<Node> commonBlocks) {
		List<String> blockIdentifierList = new ArrayList();
		for(String name: names) {
			blockIdentifierList.addAll(isVarFromCommonBlock(name, commonBlocks));
		}
		return blockIdentifierList;
	}
	


	

}
