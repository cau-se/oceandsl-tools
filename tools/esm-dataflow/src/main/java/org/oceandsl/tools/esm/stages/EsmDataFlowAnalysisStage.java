package org.oceandsl.tools.esm.stages;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.oceandsl.tools.esm.util.Output;
import org.oceandsl.tools.esm.util.XPathParser;
import org.w3c.dom.Node;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;
import org.apache.commons.io.FilenameUtils;

public class EsmDataFlowAnalysisStage extends AbstractConsumerStage<List<File>> {

	private List<String> dataflow = new ArrayList<String>();
	private List<String> contentFile = new ArrayList<String>();
	private List<String> commonBlocksFile = new ArrayList<String>();
	private HashMap<String, String> proceduresFilemap = new HashMap<String, String>();
	
	protected final OutputPort<Output> outputPort = this.createOutputPort();
	
	
	public OutputPort<Output> getOutputPort(){
		return this.outputPort;
	}
	@Override
	protected void execute(List<File> files) throws Exception {

		//System.out.println("Files:"+ files.size());
		Output out = new Output();
		
		for(File file : files) {
			//Extract xml
		//	System.out.println("File:"+ file.getAbsolutePath());
			String xml = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
			// Get main structures
			List<List<Node>> subRoutineBodies = XPathParser.getSubroutineContents(xml);
			List<List<Node>> functionBodies = XPathParser.getFuncContents(xml);
			List<Node> main = XPathParser.getMain(xml);
			
			// analyze
		    String fileName = FilenameUtils.removeExtension(file.getName());
			//String fileName = FilenameUtils.removeExtension(file.getName());
			List<String> dfInSub =  analyzeSubRoutines(subRoutineBodies,fileName);
			List<String> dfInFunc = analyzeFunctions(functionBodies, fileName);
			List<String> dfInMain = analyzeMain(main, fileName);
			// aggregate
			dataflow.addAll(dfInSub);
			dataflow.addAll(dfInFunc);
			dataflow.addAll(dfInMain);
			
			
			
			
			
			
			
		}
		out.setDataflow(dataflow);
		out.setFileContent(contentFile);
		out.setCommonBlocks(commonBlocksFile);
		System.out.println("Done");
		this.outputPort.send(out);
		
	}

	private List<String> analyzeSubRoutines(List<List<Node>> subRoutineBodies, String fileId) {
		
		List<String> dataflowInSub = new ArrayList<String>();
		List<String> blackList=XPathParser.getArraysDecl(subRoutineBodies);
		for(List<Node> body:subRoutineBodies) {
			String name = XPathParser.getsubroutineId(body);
			List<Node> commonBlocks = XPathParser.getCommonBlocks(body);
			writeCommonBlocksName(commonBlocks, fileId);
			System.out.println("size common "+ commonBlocks.size());
			String contentLine = "{"+fileId+"};{"+name+"};SUBROUTINE";
			this.contentFile.add(contentLine);
			this.proceduresFilemap.put(name, fileId);
			
			String dataFlowLine = "{"+fileId+"};"+"{"+fileId+"};"+"{"+name+"};";
			dataflowInSub = analyzeExecutionPart(body,commonBlocks, blackList, dataFlowLine, fileId);
		}
		return dataflowInSub;
	}
	
	private List<String> analyzeFunctions(List<List<Node>> funcBodies, String fileId) {

		List<String> dataflowInFunc = new ArrayList<String>();
		List<String> blackList=XPathParser.getArraysDecl(funcBodies);
		for (List<Node> body : funcBodies) {
			String name = XPathParser.getFunctionId(body);
			List<Node> commonBlocks = XPathParser.getCommonBlocks(body);
			writeCommonBlocksName(commonBlocks, fileId);
			String contentLine = "{"+fileId+"};{"+name+"};FUNCTION";
			this.contentFile.add(contentLine);
			this.proceduresFilemap.put(name, fileId);

		//	String dataFlowLine = "{"+fileId+"};{"+name+"};";
			String dataFlowLine = "{"+fileId+"};"+"{"+fileId+"};"+"{"+name+"};";
			dataflowInFunc = analyzeExecutionPart(body, commonBlocks, blackList, dataFlowLine,fileId);
		}
		return dataflowInFunc;
	}
	
	private List<String> analyzeMain(List<Node> mainBody, String fileId) {
		List<String>dataflowInMain = new ArrayList<String>();
		
		List<Node> commonBlocks = XPathParser.getCommonBlocks(mainBody);
		writeCommonBlocksName(commonBlocks, fileId);


		//String dataFlowLine = "{"+fileId+"}"+";main";
		String dataFlowLine = "{"+fileId+"};"+"{"+fileId+"};"+"main";
		dataflowInMain = analyzeExecutionPart(mainBody, commonBlocks, null, dataFlowLine, fileId);
		return dataflowInMain;
	}
    
	private List<String> analyzeExecutionPart(List<Node> body, List<Node>commonBlocks, List<String>blacklist, String dataFlowLine, String caller) {
		
		List<String> dataflowExecPart = new ArrayList<String>();
		
		//Dataflow call stmt
		List<Node> callStmts = XPathParser.getCallStmts(body);
		System.out.println("Num of call-stmts: "+callStmts.size());
		List<String> calls = analyzeCallStatements(callStmts, commonBlocks, blacklist, caller);
		for(String call: calls) {
			dataflowExecPart.add(dataFlowLine+call);
		}
		//Dataflow ifelse
		List<Node> ifElseStmts = XPathParser.getIfElseStmts(body);
		System.out.println("Num of ifelse-stmts: "+ifElseStmts.size());
		List<String> ifElseReads = analyzeReadsFromStatements(ifElseStmts,commonBlocks, blacklist, caller);
		for(String read: ifElseReads) {
			dataflowExecPart.add(dataFlowLine+read);
		}
		//Dataflow select case
		List<Node> selectStmts = XPathParser.getSelectStmts(body);
		System.out.println("Num of select-stmts: "+selectStmts.size());
		List<String> selectReads = analyzeReadsFromStatements(selectStmts,commonBlocks, blacklist, caller);
		for(String read: selectReads) {
			dataflowExecPart.add(dataFlowLine+read);
		}
		//Dataflow do while
		List<Node> loopCtrlStmts = XPathParser.getLoopCtrlStmts(body);
		System.out.println("Num of loopctrl-stmts: "+loopCtrlStmts.size());
		for(Node loopCtrl : loopCtrlStmts) {
			String loopAssignedVar = XPathParser.getLoopControlVar(loopCtrl);
			List<String> isInBlock = isVarFromCommonBlock(loopAssignedVar,commonBlocks);
			if (!isInBlock.isEmpty()) {
				String line = "WRITE/{"+isInBlock.get(0)+"}/;";
				dataflowExecPart.add(dataFlowLine+line);
			}
		}
		//Analyze Reads
		List<String> loopReads = analyzeReadsFromStatements(loopCtrlStmts,commonBlocks, blacklist, caller);
		for(String read: loopReads) {
			dataflowExecPart.add(dataFlowLine+read);
		}
		
		//Dataflow assignments
		List<Node> assignStmts = XPathParser.getAssignmentStmts(body);
		System.out.println("Num of a-stmts: "+assignStmts.size());
		List<String>assigns = analyzeAssignmentStatements(assignStmts, commonBlocks,blacklist,caller);
		for(String assign : assigns) {
			dataflowExecPart.add(dataFlowLine+assign);
		}
		return dataflowExecPart;
	}

	

	private List<String> analyzeCallStatements(List<Node> callStatements, List<Node>commonBlocList, List<String>blacklist, String caller) {
		List<String> dataflowLineRest = new ArrayList<String>();
		
		for(Node callStmt : callStatements) {
			List<String> args = XPathParser.callHasArgs(callStmt);
			if(!args.isEmpty()) {
				checkArgsWithCommon(args, commonBlocList, dataflowLineRest, caller);
				String fileId = this.proceduresFilemap.get(XPathParser.getCallStmtId(callStmt));
				String callee = XPathParser.getCallStmtId(callStmt);
				String line = fileId +";"+fileId+";"+callee+";"+"WRITE";
				//	String line = "WRITE;{"+fileId+";"+callee+"}";
				dataflowLineRest.add(line);
			}
		}
		return dataflowLineRest;
	}
	
	

	private List<String> analyzeReadsFromStatements(List<Node>stmts, List<Node>commonBlocks, List<String>blacklist, String caller) {
		List<String> dataflowLineRest = new ArrayList<String>();
		for(Node stmt: stmts) {
			List<String> names = XPathParser.getNamesFromStatement(stmt);
			List<String> blocks = checkNamesWithCommon(names, commonBlocks);
			for(String block : blocks) {
				//String line = "READ;/{"+ block + "}";
				String line = caller+";"+caller+";"+"/{"+ block +"}/;" + "READ";
				dataflowLineRest.add(line);
			}
		}
		
		return dataflowLineRest;
	}
	


	private List<String> analyzeAssignmentStatements(List<Node> stmts, List<Node> commonBlocks, List<String> bl, String caller) {
		List<String> dataflowLinesRest = new ArrayList();
		
		for(Node stmt : stmts) {
			Node assignedContent = XPathParser.getAssignedContent(stmt); //left part
			Node assigningContent = XPathParser.getAssigningContent(stmt); //right part
			
			
			String assignedVar = XPathParser.getAssignTargetIdentifier(stmt); //getName as a String
			List<String> blockIdentifierAssign=isVarFromCommonBlock(assignedVar, commonBlocks);//left part is in common Block
			
			List<Node> namesRight = XPathParser.getNames(assigningContent); //neither funcs or arrays
			System.out.println("Num of names in a: "+namesRight.size());
			List<Node> potentialFuncs = XPathParser.getPotentialFuncs(assigningContent); //something with args
			System.out.println("Num of potFuncs in a: "+potentialFuncs.size());
			List<Node> nonArgsFunc =  XPathParser.getNonArgsFunc(assigningContent); //func no args
			System.out.println("Num of numofNonArgsFunc in a: "+nonArgsFunc.size());
			
			
			if(potentialFuncs.size()>0) { //potential function analysis
				List<String> blockIdentifierList = analyzePotentialFuncStmt(potentialFuncs, commonBlocks, bl, dataflowLinesRest);
				List<String> namesAsString =convertToString(namesRight);
				blockIdentifierList.addAll(checkNamesWithCommon(namesAsString,commonBlocks));
			    //delete duplicates
				
			    if(blockIdentifierAssign.size()==0) {
			    	for(String blockId : blockIdentifierList) {
			    		
						String line = caller+";"+caller+";"+"/{"+ blockId +"}/;" + "READ";
			    	//	String line = "READ;/{" + blockId + "}/;";
						dataflowLinesRest.add(line);
			    	}
			    }else if(blockIdentifierList.size()>0) {
			    	writeCommonDataflow(blockIdentifierAssign.get(0),blockIdentifierList, dataflowLinesRest, caller);
			    }else {
			    	String blockId = blockIdentifierAssign.get(0);
			    	//String line = "WRITE;/{" + blockIdentifierAssign.get(0) + "}/;";
			    	String line = caller+";"+caller+";"+"/{"+ blockId +"}/;" + "WRITE";
					dataflowLinesRest.add(line);
			    }
			}else if(nonArgsFunc.size()>0) {
				for(Node cons: nonArgsFunc) {
					checkAssignWithCommon(assignedVar, commonBlocks,dataflowLinesRest, caller);
					String functionId = XPathParser.getStructureConstructorIdentifier(cons);
					String fileId = this.proceduresFilemap.get(functionId);
					String line = fileId+";"+fileId+";"+"{"+ functionId +"};" + "READ";
					//String line = "READ;"+ "{"+functionId+"};";
					dataflowLinesRest.add(line);
				}
			}else {
				List<String> namesAsString =convertToString(namesRight);
				List<String>blockIdentifierList = checkNamesWithCommon(namesAsString, commonBlocks);
				
				if(blockIdentifierAssign.size()>0){
					
					if(blockIdentifierList.size()==0) {
						String blockId = blockIdentifierAssign.get(0);
					//	String line = "WRITE;/{"+blockIdentifierAssign.get(0)+"}/;";
						String line = caller+";"+caller+";"+"/{"+ blockId +"}/;" + "WRITE";
						dataflowLinesRest.add(line);
					}else {
						writeCommonDataflow(blockIdentifierAssign.get(0),blockIdentifierList, dataflowLinesRest, caller);
					}
				}else {
					for(String blockId: blockIdentifierList) {
						//String line = "READ;/{"+blockId+"}/;";
						String line = caller+";"+caller+";"+"/{"+ blockId +"}/;" + "READ";
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
			result.add(node.getTextContent());
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
					dataflowLinesRest.addAll(isVarFromCommonBlock(arg,commonBlocks));
				}
				
			}else if(!bl.contains(functionIdentifier)) {
				List<String> args = XPathParser.getArgumentList(funcNode);
				if(args.size()>0) {
					for(String arg : args) {
						blockIdentifierList.addAll(isVarFromCommonBlock(arg, commonBlocks));
					}
					
					String fileId = this.proceduresFilemap.get(functionIdentifier);
					String line = fileId+";"+fileId+";"+"/{"+ functionIdentifier +"}/;" + "BOTH";
					//String line = "BOTH;{"+functionIdentifier+"};";
					
					dataflowLinesRest.add(line);
				}else {
					String fileId = this.proceduresFilemap.get(functionIdentifier);
					String line = fileId+";"+fileId+";"+"/{"+ functionIdentifier +"}/;" + "READ";
					//String line = "READ;{"+functionIdentifier+"};";
					dataflowLinesRest.add(line);
				}
			}


		}
		
		return blockIdentifierList;
	}



	private void writeCommonDataflow(String blockIdentifierAssign, List<String> blockIdentifierList, List<String> dataflowLinesRest, String caller) {
		
		for(String blockIdentifier: blockIdentifierList) {
			if(blockIdentifierAssign.equals(blockIdentifier)) {
				
				String line = caller+";"+caller+";"+"/{"+ blockIdentifier +"}/;" + "BOTH";
				//String line =  "BOTH;/{"+blockIdentifier+"}/;";
				dataflowLinesRest.add(line);
			}else {
				String line1 = caller+";"+caller+";"+"/{"+ blockIdentifier +"}/;" + "READ";
				//String line =  "READ;/{"+blockIdentifier+"}/;";
				dataflowLinesRest.add(line1);				
				
				String line2 = caller+";"+caller+";"+"/{"+ blockIdentifier +"}/;" + "WRITE";
			//	line =  "WRITE;/{"+blockIdentifierAssign+"}/;";
				dataflowLinesRest.add(line2);
			}
		}
		
	}
	



	private List<String> isVarFromCommonBlock(String variable, List<Node> commonBlocks) {
		List<String> blockIdentifierList = new ArrayList<String>();
		for(Node commonBlock:commonBlocks) {
			String blockIdentifier = XPathParser.getCommonBlockId(commonBlock);//bloc[0]
			List<String> varList = XPathParser.getCommonVars(commonBlock);//= block[1];
			for(String var:varList) {
				if(variable.equals(var)) {
					blockIdentifierList.add(blockIdentifier);
				}
			}
		}
		return blockIdentifierList;
	}

	private void checkAssignWithCommon(String assignedVar, List<Node> commonBlocks, List<String> dataflowLinesRest, String caller) {
		
		List<String> blockIdentifierList = isVarFromCommonBlock(assignedVar,commonBlocks);
		if(blockIdentifierList.size()>0) {
			for(String blockId: blockIdentifierList) {
				 String line = caller+";"+caller+";"+"/{"+ blockId +"}/;" + "WRITE";
				//String line = "WRITE;/{"+ blockId+"}/;";
				dataflowLinesRest.add(line);
			}
		}
		
	}
	
	private void checkArgsWithCommon(List<String> args, List<Node> commonBlocList, List<String> dataflowLineRest, String caller) {
		
		for(String arg :args) {
			List<String> blockIdentifierList = isVarFromCommonBlock(arg,commonBlocList);
			if(blockIdentifierList.size()>0) {
				for(String bId: blockIdentifierList) {
					String line = caller+";"+caller+";"+"/{"+ bId +"}/;" + "WRITE";
					//	String line = "WRITE;/{"+bId+"}/;";
					dataflowLineRest.add(line);
					}
			}
		}
		
	}

	private List<String> checkNamesWithCommon(List<String> names, List<Node> commonBlocks) {
		List<String> blockIdentifierList = new ArrayList();
		if (commonBlocks.size() > 0) {
			for (String name : names) {
				blockIdentifierList.addAll(isVarFromCommonBlock(name, commonBlocks));
			}
			return blockIdentifierList;
		}
		return blockIdentifierList;
	}
	
	private void writeCommonBlocksName(List<Node> commonBlocks, String filename) {
		
		for(Node cb : commonBlocks) {
			StringBuilder sb = new StringBuilder(filename+";");
			String blockIdentifier = XPathParser.getCommonBlockId(cb);//block[0]
			List<String> varList = XPathParser.getCommonVars(cb);//= block[1];
			sb.append(blockIdentifier);
			this.commonBlocksFile.add(sb.toString());
		
			
		}
	}


	

}
