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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.w3c.dom.Node;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.esm.util.Output;
import org.oceandsl.tools.esm.util.XPathParser;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.tools.Pair;

public class ProcessDataFlowAnalysisStage extends AbstractTransformation<FortranProject, Output> {

    private static final String READ = "READ";
    private static final String WRITE = "WRITE";
    private static final String BOTH = "BOTH";

    private final HashMap<String, String> proceduresFilemap = new HashMap<>();
    private final Output out = new Output();

    private final String defaultModuleName;

    public ProcessDataFlowAnalysisStage(final String defaultModuleName) {
        this.defaultModuleName = defaultModuleName != null ? defaultModuleName : "<unknown>";
    }

    @Override
    protected void execute(final FortranProject project) throws Exception {
        project.getModules().values().stream().filter(module -> !module.getModuleName().equals(this.defaultModuleName))
                .forEach(module -> {
                    this.analyzeSubRoutines(module, project);
                    this.analyzeFunctions(module, project);
                    this.analyzeMain(module, project);
                });

        this.outputPort.send(this.out);
    }

    private void analyzeSubRoutines(final FortranModule module, final FortranProject project) {
        final List<List<Node>> subRoutineBodies = XPathParser.getSubroutineContents(module.getDocument());

        final List<String> blackList = XPathParser.getArraysDecl(subRoutineBodies);
        for (final List<Node> body : subRoutineBodies) {
            final String operationName = XPathParser.getSubroutineId(body);
            final List<Node> commonBlocks = XPathParser.getCommonBlocks(body);
            this.writeCommonBlocksName(commonBlocks, module.getFileName());
            try {
                this.out.getFileContent().addRow(module.getFileName(), module.getModuleName(), operationName,
                        "SUBROUTINE");
            } catch (final ValueConversionErrorException e) {
                this.logger.error("File content table error: {}", e.getLocalizedMessage());
            }
            this.proceduresFilemap.put(operationName, module.getFileName());

            this.analyzeExecutionPart(body, commonBlocks, blackList, module, project, operationName);
        }
    }

    private void analyzeFunctions(final FortranModule module, final FortranProject project) {
        final List<List<Node>> functionBodies = XPathParser.getFuncContents(module.getDocument());
        final List<String> blackList = XPathParser.getArraysDecl(functionBodies);
        for (final List<Node> body : functionBodies) {
            final String operationName = XPathParser.getFunctionId(body);
            final List<Node> commonBlocks = XPathParser.getCommonBlocks(body);
            this.writeCommonBlocksName(commonBlocks, module.getFileName());
            try {
                this.out.getFileContent().addRow(module.getFileName(), module.getModuleName(), operationName,
                        "FUNCTION");
            } catch (final ValueConversionErrorException e) {
                this.logger.error("File content table error: {}", e.getLocalizedMessage());
            }
            this.proceduresFilemap.put(operationName, module.getFileName());

            this.analyzeExecutionPart(body, commonBlocks, blackList, module, project, operationName);
        }
    }

    private void analyzeMain(final FortranModule module, final FortranProject project) {
        final List<Node> mainBody = XPathParser.getMain(module.getDocument());

        final List<Node> commonBlocks = XPathParser.getCommonBlocks(mainBody);
        this.writeCommonBlocksName(commonBlocks, module.getFileName());

        this.analyzeExecutionPart(mainBody, commonBlocks, null, module, project, "main");
    }

    private void analyzeExecutionPart(final List<Node> body, final List<Node> commonBlocks,
            final List<String> blacklist, final FortranModule module, final FortranProject project,
            final String caller) {
        // Dataflow call stmt
        final List<Node> callStmts = XPathParser.getCallStmts(body);
        System.out.println("Num of call-stmts: " + callStmts.size());
        this.analyzeCallStatements(callStmts, commonBlocks, blacklist, caller, module, project);

        // Dataflow ifelse
        final List<Node> ifElseStmts = XPathParser.getIfElseStmts(body);
        System.out.println("Num of ifelse-stmts: " + ifElseStmts.size());
        this.analyzeReadsFromStatements(ifElseStmts, commonBlocks, blacklist, caller, module, project);

        // Dataflow select case
        final List<Node> selectStmts = XPathParser.getSelectStmts(body);
        System.out.println("Num of select-stmts: " + selectStmts.size());
        this.analyzeReadsFromStatements(selectStmts, commonBlocks, blacklist, caller, module, project);

        // Dataflow do while
        final List<Node> loopCtrlStmts = XPathParser.getLoopCtrlStmts(body);
        System.out.println("Num of loopctrl-stmts: " + loopCtrlStmts.size());
        for (final Node loopCtrl : loopCtrlStmts) {
            final String loopAssignedVar = XPathParser.getLoopControlVar(loopCtrl);
            final List<String> isInBlock = this.isVarFromCommonBlock(loopAssignedVar, commonBlocks);
            if (!isInBlock.isEmpty()) {
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                            isInBlock.get(0), ProcessDataFlowAnalysisStage.WRITE);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add row to dataflow table {}", e.getLocalizedMessage());
                }
            }
        }
        // Analyze Reads
        this.analyzeReadsFromStatements(loopCtrlStmts, commonBlocks, blacklist, caller, module, project);

        // Dataflow assignments
        final List<Node> assignStmts = XPathParser.getAssignmentStmts(body);
        System.out.println("Num of a-stmts: " + assignStmts.size());
        this.analyzeAssignmentStatements(assignStmts, commonBlocks, blacklist, caller, module, project);
    }

    private void analyzeCallStatements(final List<Node> callStatements, final List<Node> commonBlocList,
            final List<String> blacklist, final String caller, final FortranModule module,
            final FortranProject project) {

        for (final Node callStmt : callStatements) {
            final List<String> args = XPathParser.callHasArgs(callStmt);
            if (!args.isEmpty()) {
                // this.checkArgsWithCommon(args, commonBlocList, dataflowLineRest, caller);
                final String calleeName = XPathParser.getCallStmtId(callStmt);

                final Pair<FortranModule, String> callee = this.findOperation(project.getModules().values(),
                        calleeName);

                if (callee == null) {
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller,
                                this.defaultModuleName, this.defaultModuleName, calleeName,
                                ProcessDataFlowAnalysisStage.WRITE);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Dataflow cannot be added {}", e.getLocalizedMessage());
                    }
                } else {
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller,
                                callee.first.getFileName(), callee.first.getModuleName(), calleeName,
                                ProcessDataFlowAnalysisStage.WRITE);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Dataflow cannot be added {}", e.getLocalizedMessage());
                    }
                }
            }
        }
    }

    private void analyzeReadsFromStatements(final List<Node> stmts, final List<Node> commonBlocks,
            final List<String> blacklist, final String caller, final FortranModule module,
            final FortranProject project) {
        for (final Node stmt : stmts) {
            final List<String> names = XPathParser.getNamesFromStatement(stmt);
            final List<String> blocks = this.checkNamesWithCommon(names, commonBlocks);
            for (final String block : blocks) {
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "BLOCK",
                            "/" + block + "/", "VARIABLES", ProcessDataFlowAnalysisStage.READ);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Dataflow for reading from common block failed {}", e.getLocalizedMessage());
                }
            }
        }

    }

    private void analyzeAssignmentStatements(final List<Node> stmts, final List<Node> commonBlocks,
            final List<String> bl, final String caller, final FortranModule module, final FortranProject project) {

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
                        module, caller);
                final List<String> namesAsString = this.convertToString(namesRight);
                blockIdentifierList.addAll(this.checkNamesWithCommon(namesAsString, commonBlocks));
                // delete duplicates

                if (blockIdentifierAssign.size() == 0) {
                    for (final String blockId : blockIdentifierList) {
                        try {
                            this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "FILE",
                                    "/" + blockId + "/", "VARIABLES", ProcessDataFlowAnalysisStage.READ);
                        } catch (final ValueConversionErrorException e) {
                            this.logger.error("Cannot add read dataflow call->cb {}", e.getLocalizedMessage());
                        }
                    }
                } else if (blockIdentifierList.size() > 0) {
                    this.writeCommonDataflow(blockIdentifierAssign.get(0), blockIdentifierList, caller, module,
                            project);
                } else {
                    final String blockId = blockIdentifierAssign.get(0);
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "FILE",
                                "/" + blockId + "/", "VARIABLES", ProcessDataFlowAnalysisStage.WRITE);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add write dataflow call->cb {}", e.getLocalizedMessage());
                    }
                }
            } else if (nonArgsFunc.size() > 0) {
                for (final Node cons : nonArgsFunc) {
                    this.checkAssignWithCommon(assignedVar, commonBlocks, caller, module, project);
                    final String functionId = XPathParser.getStructureConstructorIdentifier(cons);
                    final String fileId = this.proceduresFilemap.get(functionId);
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, fileId, "X",
                                functionId, ProcessDataFlowAnalysisStage.READ);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add read dataflow call->cb {}", e.getLocalizedMessage());
                    }
                }
            } else {
                final List<String> namesAsString = this.convertToString(namesRight);
                final List<String> blockIdentifierList = this.checkNamesWithCommon(namesAsString, commonBlocks);

                if (blockIdentifierAssign.size() > 0) {

                    if (blockIdentifierList.size() == 0) {
                        final String blockId = blockIdentifierAssign.get(0);
                        try {
                            this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X",
                                    "X", blockId, ProcessDataFlowAnalysisStage.WRITE);
                        } catch (final ValueConversionErrorException e) {
                            this.logger.error("Cannot add write dataflow call->cb {}", e.getLocalizedMessage());
                        }
                    } else {
                        this.writeCommonDataflow(blockIdentifierAssign.get(0), blockIdentifierList, caller, module,
                                project);
                    }
                } else {
                    for (final String blockId : blockIdentifierList) {
                        try {
                            this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X",
                                    "X", blockId, ProcessDataFlowAnalysisStage.READ);
                        } catch (final ValueConversionErrorException e) {
                            this.logger.error("Cannot add read dataflow call->cb {}", e.getLocalizedMessage());
                        }
                    }
                }
            }
        }
    }

    private List<String> convertToString(final List<Node> namesRight) {
        final List<String> result = new ArrayList<>();
        for (final Node node : namesRight) {
            result.add(node.getTextContent());
        }
        return result;
    }

    private List<String> analyzePotentialFuncStmt(final List<Node> funcs, final List<Node> commonBlocks,
            final List<String> bl, final FortranModule module, final String caller) {
        final List<String> blockIdentifierList = new ArrayList<>();
        for (final Node funcNode : funcs) {
            final String functionIdentifier = XPathParser.getPartRefNodeIdentifier(funcNode);
            final List<String> functionInCommonBlockList = this.isVarFromCommonBlock(functionIdentifier, commonBlocks);

            if (functionInCommonBlockList.size() > 0) {
                blockIdentifierList.addAll(functionInCommonBlockList);
                final List<String> args = XPathParser.getArgumentList(funcNode);
                for (final String arg : args) {
                    this.isVarFromCommonBlock(arg, commonBlocks);
                    // dataflowLinesRest.addAll(this.isVarFromCommonBlock(arg, commonBlocks));
                }

            } else if (!bl.contains(functionIdentifier)) {
                final List<String> args = XPathParser.getArgumentList(funcNode);
                if (args.size() > 0) {
                    for (final String arg : args) {
                        blockIdentifierList.addAll(this.isVarFromCommonBlock(arg, commonBlocks));
                    }

                    String fileId = this.proceduresFilemap.get(functionIdentifier);

                    if (fileId == null) {
                        fileId = this.defaultModuleName;
                    }

                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, fileId, "X",
                                functionIdentifier, ProcessDataFlowAnalysisStage.BOTH);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add both dataflow call->f {}", e.getLocalizedMessage());
                    }
                } else {
                    final String fileId = this.proceduresFilemap.get(functionIdentifier);

                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, fileId, "X",
                                functionIdentifier, ProcessDataFlowAnalysisStage.BOTH);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add both dataflow call->f {}", e.getLocalizedMessage());
                    }
                }
            }

        }

        return blockIdentifierList;
    }

    private void writeCommonDataflow(final String blockIdentifierAssign, final List<String> blockIdentifierList,
            final String caller, final FortranModule module, final FortranProject project) {

        for (final String blockIdentifier : blockIdentifierList) {
            if (blockIdentifierAssign.equals(blockIdentifier)) {
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                            blockIdentifier, ProcessDataFlowAnalysisStage.BOTH);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add bidirection dataflow {}", e.getLocalizedMessage());
                }
            } else {
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                            blockIdentifier, ProcessDataFlowAnalysisStage.READ);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add read dataflow {}", e.getLocalizedMessage());
                }

                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                            blockIdentifier, "WRTE");
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add write dataflow {}", e.getLocalizedMessage());
                }
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

    private void checkAssignWithCommon(final String assignedVar, final List<Node> commonBlocks, final String caller,
            final FortranModule module, final FortranProject project) {

        final List<String> blockIdentifierList = this.isVarFromCommonBlock(assignedVar, commonBlocks);
        if (blockIdentifierList.size() > 0) {
            for (final String blockId : blockIdentifierList) {
                try {
                    this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, "X", "X",
                            blockId, ProcessDataFlowAnalysisStage.WRITE);
                } catch (final ValueConversionErrorException e) {
                    this.logger.error("Cannot add write dataflow {}", e.getLocalizedMessage());
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
            final String blockIdentifier = XPathParser.getCommonBlockId(cb);// block[0]
            final List<String> varList = XPathParser.getCommonVars(cb);// = block[1];

            try {
                this.out.getCommonBlocks().addRow(filename, "<no-module>", "<no-operation>", blockIdentifier, varList);
            } catch (final ValueConversionErrorException e) {
                this.logger.error("Common blocks file error {}", e.getLocalizedMessage());
            }
        }
    }

    // TODO duplicate form ProcessOperationCallStage
    private Pair<FortranModule, String> findOperation(final Collection<FortranModule> modules, final String signature) {
        final Optional<FortranModule> moduleOptional = modules.stream().filter(module -> module.getSpecifiedOperations()
                .stream().anyMatch(operation -> operation.getName().equals(signature))).findFirst();
        if (moduleOptional.isPresent()) {
            return new Pair<>(moduleOptional.get(), signature);
        } else {
            return null;
        }
    }

}
