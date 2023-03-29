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
package org.oceandsl.tools.esm.stages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import teetime.stage.basic.AbstractFilter;

import org.oceandsl.tools.esm.util.Output;
import org.oceandsl.tools.esm.util.XPathParser;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.tools.IUriProcessor;

public class ProcessDataFlowAnalysisStage extends AbstractFilter<FortranProject> {

    private final List<String> dataflow = new ArrayList<>();
    private final List<String> contentFile = new ArrayList<>();
    private final List<String> commonBlocksFile = new ArrayList<>();
    private final HashMap<String, String> proceduresFilemap = new HashMap<>();
    private final Output out = new Output();

    private final IUriProcessor uriProcessor;

    public ProcessDataFlowAnalysisStage(final IUriProcessor uriProcessor) {
        this.uriProcessor = uriProcessor;
    }

    @Override
    protected void execute(final FortranProject project) throws Exception {
        project.getModules().values().forEach(module -> {
            final Document document = module.getDocument();

            // Get main structures
            final List<List<Node>> subRoutineBodies = XPathParser.getSubroutineContents(document);
            final List<List<Node>> functionBodies = XPathParser.getFuncContents(document);
            final List<Node> main = XPathParser.getMain(document);

            // analyze
            final String fileName = this.uriProcessor.process(document.getBaseURI());
            final List<String> dfInSub = this.analyzeSubRoutines(subRoutineBodies, fileName);
            final List<String> dfInFunc = this.analyzeFunctions(functionBodies, fileName);
            final List<String> dfInMain = this.analyzeMain(main, fileName);

            // aggregate
            this.dataflow.addAll(dfInSub);
            this.dataflow.addAll(dfInFunc);
            this.dataflow.addAll(dfInMain);
        });
    }

    @Override
    protected void onTerminating() {
        this.out.setDataflow(this.dataflow);
        this.out.setFileContent(this.contentFile);
        this.out.setCommonBlocks(this.commonBlocksFile);

        super.onTerminating();
    }

    private List<String> analyzeSubRoutines(final List<List<Node>> subRoutineBodies, final String fileId) {
        List<String> dataflowInSub = new ArrayList<>();
        final List<String> blackList = XPathParser.getArraysDecl(subRoutineBodies);
        for (final List<Node> body : subRoutineBodies) {
            final String name = XPathParser.getSubroutineId(body);
            final List<Node> commonBlocks = XPathParser.getCommonBlocks(body);
            this.writeCommonBlocksName(commonBlocks, fileId);
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
            this.writeCommonBlocksName(commonBlocks, fileId);
            final String contentLine = "{" + fileId + "};{" + name + "};FUNCTION";
            this.contentFile.add(contentLine);
            this.proceduresFilemap.put(name, fileId);

            final String dataFlowLine = "{" + fileId + "};" + "{" + fileId + "};" + "{" + name + "};";
            dataflowInFunc = this.analyzeExecutionPart(body, commonBlocks, blackList, dataFlowLine, fileId);
        }
        return dataflowInFunc;
    }

    private List<String> analyzeMain(final List<Node> mainBody, final String fileId) {
        List<String> dataflowInMain = new ArrayList<>();

        final List<Node> commonBlocks = XPathParser.getCommonBlocks(mainBody);
        this.writeCommonBlocksName(commonBlocks, fileId);

        // String dataFlowLine = "{"+fileId+"}"+";main";
        final String dataFlowLine = "{" + fileId + "};" + "{" + fileId + "};" + "main";
        dataflowInMain = this.analyzeExecutionPart(mainBody, commonBlocks, null, dataFlowLine, fileId);
        return dataflowInMain;
    }

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

    private List<String> analyzeCallStatements(final List<Node> callStatements, final List<Node> commonBlocList,
            final List<String> blacklist, final String caller) {
        final List<String> dataflowLineRest = new ArrayList<>();

        for (final Node callStmt : callStatements) {
            final List<String> args = XPathParser.callHasArgs(callStmt);
            if (!args.isEmpty()) {
                this.checkArgsWithCommon(args, commonBlocList, dataflowLineRest, caller);
                final String fileId = this.proceduresFilemap.get(XPathParser.getCallStmtId(callStmt));
                final String callee = XPathParser.getCallStmtId(callStmt);
                final String line = fileId + ";" + fileId + ";" + callee + ";" + "WRITE";
                // String line = "WRITE;{"+fileId+";"+callee+"}";
                dataflowLineRest.add(line);
            }
        }
        return dataflowLineRest;
    }

    private List<String> analyzeReadsFromStatements(final List<Node> stmts, final List<Node> commonBlocks,
            final List<String> blacklist, final String caller) {
        final List<String> dataflowLineRest = new ArrayList<>();
        for (final Node stmt : stmts) {
            final List<String> names = XPathParser.getNamesFromStatement(stmt);
            final List<String> blocks = this.checkNamesWithCommon(names, commonBlocks);
            for (final String block : blocks) {
                // String line = "READ;/{"+ block + "}";
                final String line = caller + ";" + caller + ";" + "/{" + block + "}/;" + "READ";
                dataflowLineRest.add(line);
            }
        }

        return dataflowLineRest;
    }

    private List<String> analyzeAssignmentStatements(final List<Node> stmts, final List<Node> commonBlocks,
            final List<String> bl, final String caller) {
        final List<String> dataflowLinesRest = new ArrayList<>();

        for (final Node stmt : stmts) {
            final Node assignedContent = XPathParser.getAssignedContent(stmt); // left part
            final Node assigningContent = XPathParser.getAssigningContent(stmt); // right part

            // getName as a String
            final String assignedVar = XPathParser.getAssignTargetIdentifier(stmt);
            // left part is in common Block
            final List<String> blockIdentifierAssign = this.isVarFromCommonBlock(assignedVar, commonBlocks);

            // neither funcs or arrays
            final List<Node> namesRight = XPathParser.getNames(assigningContent);
            System.out.println("Num of names in a: " + namesRight.size());
            // something with args
            final List<Node> potentialFuncs = XPathParser.getPotentialFuncs(assigningContent);
            System.out.println("Num of potFuncs in a: " + potentialFuncs.size());
            // func no args
            final List<Node> nonArgsFunc = XPathParser.getNonArgsFunc(assigningContent);
            System.out.println("Num of numofNonArgsFunc in a: " + nonArgsFunc.size());

            if (potentialFuncs.size() > 0) { // potential function analysis
                final List<String> blockIdentifierList = this.analyzePotentialFuncStmt(potentialFuncs, commonBlocks, bl,
                        dataflowLinesRest);
                final List<String> namesAsString = this.convertToString(namesRight);
                blockIdentifierList.addAll(this.checkNamesWithCommon(namesAsString, commonBlocks));
                // delete duplicates

                if (blockIdentifierAssign.size() == 0) {
                    for (final String blockId : blockIdentifierList) {

                        final String line = caller + ";" + caller + ";" + "/{" + blockId + "}/;" + "READ";
                        // String line = "READ;/{" + blockId + "}/;";
                        dataflowLinesRest.add(line);
                    }
                } else if (blockIdentifierList.size() > 0) {
                    this.writeCommonDataflow(blockIdentifierAssign.get(0), blockIdentifierList, dataflowLinesRest,
                            caller);
                } else {
                    final String blockId = blockIdentifierAssign.get(0);
                    // String line = "WRITE;/{" + blockIdentifierAssign.get(0) + "}/;";
                    final String line = caller + ";" + caller + ";" + "/{" + blockId + "}/;" + "WRITE";
                    dataflowLinesRest.add(line);
                }
            } else if (nonArgsFunc.size() > 0) {
                for (final Node cons : nonArgsFunc) {
                    this.checkAssignWithCommon(assignedVar, commonBlocks, dataflowLinesRest, caller);
                    final String functionId = XPathParser.getStructureConstructorIdentifier(cons);
                    final String fileId = this.proceduresFilemap.get(functionId);
                    final String line = fileId + ";" + fileId + ";" + "{" + functionId + "};" + "READ";
                    // String line = "READ;"+ "{"+functionId+"};";
                    dataflowLinesRest.add(line);
                }
            } else {
                final List<String> namesAsString = this.convertToString(namesRight);
                final List<String> blockIdentifierList = this.checkNamesWithCommon(namesAsString, commonBlocks);

                if (blockIdentifierAssign.size() > 0) {

                    if (blockIdentifierList.size() == 0) {
                        final String blockId = blockIdentifierAssign.get(0);
                        // String line = "WRITE;/{"+blockIdentifierAssign.get(0)+"}/;";
                        final String line = caller + ";" + caller + ";" + "/{" + blockId + "}/;" + "WRITE";
                        dataflowLinesRest.add(line);
                    } else {
                        this.writeCommonDataflow(blockIdentifierAssign.get(0), blockIdentifierList, dataflowLinesRest,
                                caller);
                    }
                } else {
                    for (final String blockId : blockIdentifierList) {
                        // String line = "READ;/{"+blockId+"}/;";
                        final String line = caller + ";" + caller + ";" + "/{" + blockId + "}/;" + "READ";
                        dataflowLinesRest.add(line);
                    }
                }
            }
        }

        return dataflowLinesRest;
    }

    private List<String> convertToString(final List<Node> namesRight) {
        final List<String> result = new ArrayList<>();
        for (final Node node : namesRight) {
            result.add(node.getTextContent());
        }
        return result;
    }

    private List<String> analyzePotentialFuncStmt(final List<Node> funcs, final List<Node> commonBlocks,
            final List<String> bl, final List<String> dataflowLinesRest) {
        final List<String> blockIdentifierList = new ArrayList<>();
        for (final Node funcNode : funcs) {
            final String functionIdentifier = XPathParser.getPartRefNodeIdentifier(funcNode);
            final List<String> functionInCommonBlockList = this.isVarFromCommonBlock(functionIdentifier, commonBlocks);

            if (functionInCommonBlockList.size() > 0) {
                blockIdentifierList.addAll(functionInCommonBlockList);
                final List<String> args = XPathParser.getArgumentList(funcNode);
                for (final String arg : args) {
                    dataflowLinesRest.addAll(this.isVarFromCommonBlock(arg, commonBlocks));
                }

            } else if (!bl.contains(functionIdentifier)) {
                final List<String> args = XPathParser.getArgumentList(funcNode);
                if (args.size() > 0) {
                    for (final String arg : args) {
                        blockIdentifierList.addAll(this.isVarFromCommonBlock(arg, commonBlocks));
                    }

                    final String fileId = this.proceduresFilemap.get(functionIdentifier);
                    final String line = fileId + ";" + fileId + ";" + "/{" + functionIdentifier + "}/;" + "BOTH";
                    // String line = "BOTH;{"+functionIdentifier+"};";

                    dataflowLinesRest.add(line);
                } else {
                    final String fileId = this.proceduresFilemap.get(functionIdentifier);
                    final String line = fileId + ";" + fileId + ";" + "/{" + functionIdentifier + "}/;" + "READ";
                    // String line = "READ;{"+functionIdentifier+"};";
                    dataflowLinesRest.add(line);
                }
            }

        }

        return blockIdentifierList;
    }

    private void writeCommonDataflow(final String blockIdentifierAssign, final List<String> blockIdentifierList,
            final List<String> dataflowLinesRest, final String caller) {

        for (final String blockIdentifier : blockIdentifierList) {
            if (blockIdentifierAssign.equals(blockIdentifier)) {

                final String line = caller + ";" + caller + ";" + "/{" + blockIdentifier + "}/;" + "BOTH";
                // String line = "BOTH;/{"+blockIdentifier+"}/;";
                dataflowLinesRest.add(line);
            } else {
                final String line1 = caller + ";" + caller + ";" + "/{" + blockIdentifier + "}/;" + "READ";
                // String line = "READ;/{"+blockIdentifier+"}/;";
                dataflowLinesRest.add(line1);

                final String line2 = caller + ";" + caller + ";" + "/{" + blockIdentifier + "}/;" + "WRITE";
                // line = "WRITE;/{"+blockIdentifierAssign+"}/;";
                dataflowLinesRest.add(line2);
            }
        }

    }

    private List<String> isVarFromCommonBlock(final String variable, final List<Node> commonBlocks) {
        final List<String> blockIdentifierList = new ArrayList<>();
        for (final Node commonBlock : commonBlocks) {
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
                final String line = caller + ";" + caller + ";" + "/{" + blockId + "}/;" + "WRITE";
                // String line = "WRITE;/{"+ blockId+"}/;";
                dataflowLinesRest.add(line);
            }
        }

    }

    private void checkArgsWithCommon(final List<String> args, final List<Node> commonBlocList,
            final List<String> dataflowLineRest, final String caller) {

        for (final String arg : args) {
            final List<String> blockIdentifierList = this.isVarFromCommonBlock(arg, commonBlocList);
            if (blockIdentifierList.size() > 0) {
                for (final String bId : blockIdentifierList) {
                    final String line = caller + ";" + caller + ";" + "/{" + bId + "}/;" + "WRITE";
                    // String line = "WRITE;/{"+bId+"}/;";
                    dataflowLineRest.add(line);
                }
            }
        }

    }

    private List<String> checkNamesWithCommon(final List<String> names, final List<Node> commonBlocks) {
        final List<String> blockIdentifierList = new ArrayList<>();
        if (commonBlocks.size() > 0) {
            for (final String name : names) {
                blockIdentifierList.addAll(this.isVarFromCommonBlock(name, commonBlocks));
            }
            return blockIdentifierList;
        }
        return blockIdentifierList;
    }

    private void writeCommonBlocksName(final List<Node> commonBlocks, final String filename) {

        for (final Node cb : commonBlocks) {
            final StringBuilder sb = new StringBuilder(filename + ";");
            final String blockIdentifier = XPathParser.getCommonBlockId(cb);// block[0]
            final List<String> varList = XPathParser.getCommonVars(cb);// = block[1];
            sb.append(blockIdentifier);
            this.commonBlocksFile.add(sb.toString());

        }
    }

}
