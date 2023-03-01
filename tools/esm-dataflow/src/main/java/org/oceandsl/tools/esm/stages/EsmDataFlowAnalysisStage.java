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

    private final List<String> dataflow = new ArrayList<>();
    private final List<String> contentFile = new ArrayList<>();
    private List<String> commonBlocksFile = new ArrayList<String>();
    private HashMap<String, String> proceduresFilemap = new HashMap<String, String>();

    protected final OutputPort<Output> outputPort = this.createOutputPort();

    public OutputPort<Output> getOutputPort() {
        return this.outputPort;
    }

    @Override
    protected void execute(final List<File> files) throws Exception {

        // System.out.println("Files:"+ files.size());
        final Output out = new Output();

        for (final File file : files) {
            // Extract xml
            // System.out.println("File:"+ file.getAbsolutePath());
            final String xml = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            // Get main structures
            final List<List<Node>> subRoutineBodies = XPathParser.getSubroutineContents(xml);
            final List<List<Node>> functionBodies = XPathParser.getFuncContents(xml);
            final List<Node> main = XPathParser.getMain(xml);

            // analyze
            final String fileName = FilenameUtils.removeExtension(file.getName());
            //String fileName = FilenameUtils.removeExtension(file.getName());
            final List<String> dfInSub = this.analyzeSubRoutines(subRoutineBodies,fileName);
            final List<String> dfInFunc = this.analyzeFunctions(functionBodies, fileName);
            final List<String> dfInMain = this.analyzeMain(main, fileName);
            // aggregate
            this.dataflow.addAll(dfInSub);
            this.dataflow.addAll(dfInFunc);
            this.dataflow.addAll(dfInMain);
        }

        out.setDataflow(this.dataflow);
        out.setFileContent(this.contentFile);

        out.setCommonBlocks(this.commonBlocksFile);
        System.out.println("Done");

        this.outputPort.send(out);
    }

    private List<String> analyzeSubRoutines(final List<List<Node>> subRoutineBodies, final String fileId) {
        List<String> dataflowInSub = new ArrayList<>();
        final List<String> blackList = XPathParser.getArraysDecl(subRoutineBodies);
        for (final List<Node> body : subRoutineBodies) {
            final String name = XPathParser.getsubroutineId(body);
            final List<Node> commonBlocks = XPathParser.getCommonBlocks(body);

            writeCommonBlocksName(commonBlocks, fileId);
            System.out.println("size common " + commonBlocks.size());
            final String contentLine = "{" + fileId + "};{" + name + "};SUBROUTINE";
            this.contentFile.add(contentLine);
            this.proceduresFilemap.put(name, fileId);

            final String dataFlowLine = "{" + fileId + "};" + "{" + fileId + "};" + "{" + name + "};";
            dataflowInSub = this.analyzeExecutionPart(body, commonBlocks, blackList, dataFlowLine, fileId);
        }
        return dataflowInSub;
    }

    private List<String> analyzeFunctions(final List<List<Node>> funcBodies, final String fileId) {
        List<String> dataflowInFunc = new ArrayList<>();
        final List<String> blackList = XPathParser.getArraysDecl(funcBodies);
        for (final List<Node> body : funcBodies) {
            final String name = XPathParser.getFunctionId(body);
            final List<Node> commonBlocks = XPathParser.getCommonBlocks(body);
            writeCommonBlocksName(commonBlocks, fileId);
            final String contentLine = "{" + fileId + "};{" + name + "};FUNCTION";
            this.contentFile.add(contentLine);
            this.proceduresFilemap.put(name, fileId);

            final String dataFlowLine = "{" + fileId + "};" + "{" + fileId + "};" + "{" + name + "};";
            dataflowInFunc = analyzeExecutionPart(body, commonBlocks, blackList, dataFlowLine, fileId);
        }
        return dataflowInFunc;
    }

    /**
     *
     */
    private List<String> analyzeMain(final List<Node> mainBody, final String fileId) {
        List<String>dataflowInMain = new ArrayList<String>();

        final List<Node> commonBlocks = XPathParser.getCommonBlocks(mainBody);

        this.writeCommonBlocksName(commonBlocks, fileId);

        for (final File file : files) {
            // Extract xml
            // System.out.println("File:"+ file.getAbsolutePath());
            final String xml = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            // Get main structures
            final List<List<Node>> subRoutineBodies = XPathParser.getSubroutineContents(xml);
            final List<List<Node>> functionBodies = XPathParser.getFuncContents(xml);
            final List<Node> main = XPathParser.getMain(xml);

        //String dataFlowLine = "{"+fileId+"}"+";main";
        String dataFlowLine = "{"+fileId+"};"+"{"+fileId+"};"+"main";
        dataflowInMain = analyzeExecutionPart(mainBody, commonBlocks, null, dataFlowLine, fileId);
        return dataflowInMain;
    }

    /**
     *
     */
    private List<String> analyzeExecutionPart(final List<Node> body, final List<Node> commonBlocks,
            final List<String> blacklist, final String dataFlowLine, final String caller) {

        final List<String> dataflowExecPart = new ArrayList<>();

        // Dataflow call stmt
        final List<Node> callStmts = XPathParser.getCallStmts(body);
        System.out.println("Num of call-stmts: " + callStmts.size());
        final List<String> calls = this.analyzeCallStatements(callStmts, commonBlocks, blacklist, caller);
        for (final String call : calls) {
            dataflowExecPart.add(dataFlowLine + call);
        }
        // Dataflow ifelse
        final List<Node> ifElseStmts = XPathParser.getIfElseStmts(body);
        System.out.println("Num of ifelse-stmts: " + ifElseStmts.size());
        final List<String> ifElseReads = this.analyzeReadsFromStatements(ifElseStmts, commonBlocks, blacklist, caller);
        for (final String read : ifElseReads) {
            dataflowExecPart.add(dataFlowLine + read);
        }
        // Dataflow select case
        final List<Node> selectStmts = XPathParser.getSelectStmts(body);
        System.out.println("Num of select-stmts: " + selectStmts.size());
        final List<String> selectReads = this.analyzeReadsFromStatements(selectStmts, commonBlocks, blacklist, caller);
        for (final String read : selectReads) {
            dataflowExecPart.add(dataFlowLine + read);
        }
        // Dataflow do while
        final List<Node> loopCtrlStmts = XPathParser.getLoopCtrlStmts(body);
        System.out.println("Num of loopctrl-stmts: " + loopCtrlStmts.size());
        for (final Node loopCtrl : loopCtrlStmts) {
            final String loopAssignedVar = XPathParser.getLoopControlVar(loopCtrl);
            final List<String> isInBlock = this.isVarFromCommonBlock(loopAssignedVar, commonBlocks);
            if (!isInBlock.isEmpty()) {
                final String line = "WRITE/{" + isInBlock.get(0) + "}/;";
                dataflowExecPart.add(dataFlowLine + line);
            }
        }
        // Analyze Reads
        final List<String> loopReads = this.analyzeReadsFromStatements(loopCtrlStmts, commonBlocks, blacklist, caller);
        for (final String read : loopReads) {
            dataflowExecPart.add(dataFlowLine + read);
        }

        // Dataflow assignments
        final List<Node> assignStmts = XPathParser.getAssignmentStmts(body);
        System.out.println("Num of a-stmts: " + assignStmts.size());
        final List<String> assigns = this.analyzeAssignmentStatements(assignStmts, commonBlocks, blacklist, caller);
        for (final String assign : assigns) {
            dataflowExecPart.add(dataFlowLine + assign);
        }
        return dataflowExecPart;
    }

    /**
     *
     */
    private List<String> analyzeCallStatements(final List<Node> callStatements, final List<Node> commonBlocList,
            final List<String> blacklist, final String caller) {
        final List<String> dataflowLineRest = new ArrayList<String>();

        for (final Node callStmt : callStatements) {
            final List<String> args = XPathParser.callHasArgs(callStmt);
            if (!args.isEmpty()) {
                this.checkArgsWithCommon(args, commonBlocList, dataflowLineRest, caller);
                final String fileId = this.proceduresFilemap.get(XPathParser.getCallStmtId(callStmt));
                final String callee = XPathParser.getCallStmtId(callStmt);
                final String line = fileId + ";" + fileId + ";" + callee + ";" + "WRITE";
                dataflowLineRest.add(line);
            }
        }
        return dataflowLineRest;
    }

    /**
     * bb
     */
    private List<String> analyzeReadsFromStatements(final List<Node> stmts, final List<Node> commonBlocks,
            final List<String> blacklist, final String caller) {
        final List<String> dataflowLineRest = new ArrayList<String>();
        for (final Node stmt : stmts) {
            final List<String> names = XPathParser.getNamesFromStatement(stmt);
            final List<String> blocks = this.checkNamesWithCommon(names, commonBlocks);

            for (final String block : blocks) {
                String line = caller + ";" + caller + ";" + "/{" + block + "}/;" + "READ";
                dataflowLineRest.add(line);
            }
        }

        return dataflowLineRest;
    }

    /**
     * aa
     */
    private List<String> analyzeAssignmentStatements(final List<Node> stmts, final List<Node> commonBlocks,
            final List<String> bl, final String caller) {
        final List<String> dataflowLinesRest = new ArrayList<>();

        for (final Node stmt : stmts) { //

            final Node assignedContent = XPathParser.getAssignedContent(stmt); // left part
            final Node assigningContent = XPathParser.getAssigningContent(stmt); // right part

            final String assignedVar = XPathParser.getAssignTargetIdentifier(stmt); // getName as String
            final List<String> blockIdentifierAssign = this.isVarFromCommonBlock(assignedVar, commonBlocks); // left part is in common Block

            final List<Node> namesRight = XPathParser.getNames(assigningContent); // neither funcs or arrays
            System.out.println("Num of names in a: " + namesRight.size());
            final List<Node> potentialFuncs = XPathParser.getPotentialFuncs(assigningContent); // something with args
            System.out.println("Num of potFuncs in a: " + potentialFuncs.size());
            final List<Node> nonArgsFunc = XPathParser.getNonArgsFunc(assigningContent); // func no args
            System.out.println("Num of numofNonArgsFunc in a: " + nonArgsFunc.size());

            if (potentialFuncs.size() > 0) { // potential function analysis
                final List<String> blockIdentifierList = this.analyzePotentialFuncStmt(potentialFuncs, commonBlocks, bl, dataflowLinesRest);
                final List<String> namesAsString = this.convertToString(namesRight);
                blockIdentifierList.addAll(this.checkNamesWithCommon(namesAsString, commonBlocks));
                // delete duplicates

                if (blockIdentifierAssign.size() == 0) {
                    for (final String blockId : blockIdentifierList) {
                        final String line = caller + ";" + caller + ";" + "/{" + blockId + "}/;" + "READ";
                        dataflowLinesRest.add(line);
                    }
                } else if (blockIdentifierList.size() > 0) {
                    this.writeCommonDataflow(blockIdentifierAssign.get(0), blockIdentifierList, dataflowLinesRest, caller);
                } else {
                    final String blockId = blockIdentifierAssign.get(0);
                    String line = caller+";"+caller+";"+"/{"+ blockId +"}/;" + "WRITE";
                    dataflowLinesRest.add(line);
                }
            } else if (nonArgsFunc.size() > 0) {
                for (final Node cons: nonArgsFunc) {
                    this.checkAssignWithCommon(assignedVar, commonBlocks, dataflowLinesRest, caller);
                    final String functionId = XPathParser.getStructureConstructorIdentifier(cons);
                    final String fileId = this.proceduresFilemap.get(functionId);
                    final String line = fileId+";"+fileId+";"+"{"+ functionId +"};" + "READ";
                    dataflowLinesRest.add(line);
                }
            } else {
                final List<String> namesAsString = this.convertToString(namesRight);
                final List<String> blockIdentifierList = this.checkNamesWithCommon(namesAsString, commonBlocks);

                if (blockIdentifierAssign.size() > 0) {

                    if (blockIdentifierList.size() == 0) {
                        final String blockId = blockIdentifierAssign.get(0);
                        final String line = caller+";"+caller+";"+"/{"+ blockId +"}/;" + "WRITE";
                        dataflowLinesRest.add(line);
                    } else {
                        this.writeCommonDataflow(blockIdentifierAssign.get(0), blockIdentifierList, dataflowLinesRest, caller);
                    }
                } else {
                    for (final String blockId: blockIdentifierList) {
                        String line = caller+";"+caller+";"+"/{"+ blockId +"}/;" + "READ";
                        dataflowLinesRest.add(line);
                    }
                }
            }
        }

        return dataflowLinesRest;
    }

    /**
     * convertToString
     */
    private List<String> convertToString(final List<Node> namesRight) {
        final List<String> result = new ArrayList<>();
        for (final Node node : namesRight) {
            result.add(node.getTextContent());
        }
        return result;
    }

    /**
     * analyzePotentialFuncStmt
     */
    private List<String> analyzePotentialFuncStmt(final List<Node> funcs, final List<Node> commonBlocks,
            final List<String> bl, final List<String> dataflowLinesRest) {
        final List<String> blockIdentifierList = new ArrayList<>();
        for (final Node funcNode : funcs) {
            final String functionIdentifier = XPathParser.getPartRefNodeIdentifier(funcNode);
            final List<String>functionInCommonBlockList = isVarFromCommonBlock(functionIdentifier, commonBlocks);
            
            if (functionInCommonBlockList.size() > 0) {
                blockIdentifierList.addAll(functionInCommonBlockList);
                final List<String> args = XPathParser.getArgumentList(funcNode);
                for(final String arg : args) {
                    dataflowLinesRest.addAll(isVarFromCommonBlock(arg,commonBlocks));
                }

            } else if(!bl.contains(functionIdentifier)) {
                final List<String> args = XPathParser.getArgumentList(funcNode);
                if (args.size() > 0) {
                    for (final String arg : args) {
                        blockIdentifierList.addAll(isVarFromCommonBlock(arg, commonBlocks));
                    }

                    final String fileId = this.proceduresFilemap.get(functionIdentifier);
                    final String line = fileId+";"+fileId+";"+"/{"+ functionIdentifier +"}/;" + "BOTH";
                    //String line = "BOTH;{"+functionIdentifier+"};";
                    
                    dataflowLinesRest.add(line);
                } else {
                    final String fileId = this.proceduresFilemap.get(functionIdentifier);
                    final String line = fileId+";"+fileId+";"+"/{"+ functionIdentifier +"}/;" + "READ";
                    //String line = "READ;{"+functionIdentifier+"};";
                    dataflowLinesRest.add(line);
                }
            }

        final List<String> dataflowInFunc = new ArrayList<String>();
        final List<String> blackList = XPathParser.getArraysDecl(funcBodies);
        for (final List<Node> body : funcBodies) {
            final String name = XPathParser.getFunctionId(body);
            final List<Node> commonBlocks = XPathParser.getCommonBlocks(body);
            final String contentLine = "{" + fileId + "};{" + name + "};FUNCTION";
            this.contentFile.add(contentLine);

            final String dataFlowLine = "{" + fileId + "};{" + name + "};";
            dataflowInFunc = this.analyzeExecutionPart(body, commonBlocks, blackList, dataFlowLine);
        }
        return dataflowInFunc;
    }

    /**
     * writeCommonDataflow
     */
    private void writeCommonDataflow(final String blockIdentifierAssign, final List<String> blockIdentifierList,
            final List<String> dataflowLinesRest, final String caller) {
        
        for (final String blockIdentifier: blockIdentifierList) {
            if (blockIdentifierAssign.equals(blockIdentifier)) {

                String line = caller+";"+caller+";"+"/{"+ blockIdentifier +"}/;" + "BOTH";
                //String line =  "BOTH;/{"+blockIdentifier+"}/;";
                dataflowLinesRest.add(line);
            }else {
                String line1 = caller+";"+caller+";"+"/{"+ blockIdentifier +"}/;" + "READ";
                //String line =  "READ;/{"+blockIdentifier+"}/;";
                dataflowLinesRest.add(line1);                
                
                String line2 = caller+";"+caller+";"+"/{"+ blockIdentifier +"}/;" + "WRITE";
            //    line =  "WRITE;/{"+blockIdentifierAssign+"}/;";
                dataflowLinesRest.add(line2);
            }
        }

    }

    private List<String> isVarFromCommonBlock(final String variable, final List<Node> commonBlocks) {
        final List<String> blockIdentifierList = new ArrayList<>();
        for (finalNode commonBlock : commonBlocks) {
            final String blockIdentifier = XPathParser.getCommonBlockId(commonBlock);// bloc[0]
            final List<String> varList = XPathParser.getCommonVars(commonBlock);// = block[1];
            for (final String var : varList) {
                if (variable.equals(var)) {
                    blockIdentifierList.add(blockIdentifier);
                }
            }
        }
        return blockIdentifierList;
    }

    private void checkAssignWithCommon(final String assignedVar, final List<Node> commonBlocks,
            final List<String> dataflowLinesRest, final String caller) {

        final List<String> blockIdentifierList = this.isVarFromCommonBlock(assignedVar, commonBlocks);
        if (blockIdentifierList.size() > 0) {
            for (final String blockId : blockIdentifierList) {
                 String line = caller+";"+caller+";"+"/{"+ blockId +"}/;" + "WRITE";
                dataflowLinesRest.add(line);
            }
        }

    }

    private void checkArgsWithCommon(final List<String> args, final List<Node> commonBlocList, 
            final List<String> dataflowLineRest, String caller) {

        for (final String arg : args) {
            final List<String> blockIdentifierList = this.isVarFromCommonBlock(arg,commonBlocList);
            if (blockIdentifierList.size() > 0) {
                for (final String bId: blockIdentifierList) {
                    String line = caller+";"+caller+";"+"/{"+ bId +"}/;" + "WRITE";
                    //    String line = "WRITE;/{"+bId+"}/;";
                    dataflowLineRest.add(line);
                }
            }
        }

    }

    private List<String> checkNamesWithCommon(final List<String> names, final List<Node> commonBlocks) {
        final List<String> blockIdentifierList = new ArrayList();
        if (commonBlocks.size() > 0) {
            for (final String name : names) {
                blockIdentifierList.addAll(this.isVarFromCommonBlock(name, commonBlocks));
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
