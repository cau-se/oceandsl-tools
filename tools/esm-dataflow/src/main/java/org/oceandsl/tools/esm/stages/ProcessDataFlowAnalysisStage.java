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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.w3c.dom.Node;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
import org.oceandsl.tools.esm.util.Output;
import org.oceandsl.tools.esm.util.XPathParser;
import org.oceandsl.tools.fxca.model.CommonBlock;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.tools.Pair;

public class ProcessDataFlowAnalysisStage extends AbstractTransformation<FortranProject, Output> {

    private static final String READ = "READ";
    private static final String WRITE = "WRITE";
    private static final String BOTH = "BOTH";

    private final Output out = new Output();

    private final String defaultModuleName;

    public ProcessDataFlowAnalysisStage(final String defaultModuleName) {
        this.defaultModuleName = defaultModuleName != null ? defaultModuleName : "<unknown>";
    }

    @Override
    protected void execute(final FortranProject project) throws Exception {
        project.getModules().values().stream().filter(module -> !module.getModuleName().equals(this.defaultModuleName))
                .forEach(module -> {
                    this.analyzeOperations(module, project);
                    this.analyzeMain(module, project);
                });

        this.outputPort.send(this.out);
    }

    private void analyzeOperations(final FortranModule module, final FortranProject project) {
        for (final FortranOperation operation : module.getOperations().values()) {
            this.writeCommonBlocksName(operation.getCommonBlocks(), module.getFileName());
            try {
                this.out.getFileContent().addRow(module.getFileName(), module.getModuleName(), operation.getName());
            } catch (final ValueConversionErrorException e) {
                this.logger.error("File content table error: {}", e.getLocalizedMessage());
            }

            this.analyzeExecutionPart(operation.getNode(), module, project, operation.getName());
        }
    }

    private void analyzeMain(final FortranModule module, final FortranProject project) {
        this.writeCommonBlocksName(module.getCommonBlocks(), module.getFileName());
        this.analyzeExecutionPart(module.getDocument(), module, project, "main");
    }

    private void analyzeExecutionPart(final Node parent, final FortranModule module, final FortranProject project,
            final String caller) {
        // Dataflow call stmt
        final Set<Node> callStmts = XPathParser.getCallStmts(parent);
        this.analyzeCallStatements(callStmts, caller, module, project);

        // Dataflow ifelse
        final Set<Node> ifElseStmts = XPathParser.getIfElseStmts(parent);
        this.analyzeReadsFromStatements(ifElseStmts, caller, module, project);

        // Dataflow select case
        final Set<Node> selectStmts = XPathParser.getSelectStmts(parent);
        this.analyzeReadsFromStatements(selectStmts, caller, module, project);

        // Dataflow do while
        final Set<Node> loopCtrlStmts = XPathParser.getLoopCtrlStmts(parent);
        for (final Node loopCtrl : loopCtrlStmts) {
            final String loopAssignedVar = XPathParser.getLoopControlVar(loopCtrl);
            final List<String> isInBlock = this.getCommonBlockIdsForGivenVariable(loopAssignedVar,
                    module.getCommonBlocks());
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
        this.analyzeReadsFromStatements(loopCtrlStmts, caller, module, project);

        // Dataflow assignments
        final Set<Node> assignStmts = XPathParser.getAssignmentStmts(parent);
        this.analyzeAssignmentStatements(assignStmts, null, caller, module, project);
    }

    private void analyzeCallStatements(final Set<Node> callStatements, final String caller, final FortranModule module,
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

    private void analyzeReadsFromStatements(final Set<Node> stmts, final String caller, final FortranModule module,
            final FortranProject project) {
        for (final Node stmt : stmts) {
            final List<String> names = XPathParser.getNamesFromStatement(stmt);
            final List<String> blocks = this.checkNamesWithCommon(names, module.getCommonBlocks());
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

    private void analyzeAssignmentStatements(final Set<Node> stmts, final List<String> bl, final String caller,
            final FortranModule module, final FortranProject project) {

        for (final Node stmt : stmts) {
            final Node assignedContent = XPathParser.getAssignedContent(stmt); // left part
            final Node assigningContent = XPathParser.getAssigningContent(stmt); // right part

            // getName as a String
            final String assignedVar = XPathParser.getAssignTargetIdentifier(stmt);
            // left part is in common Block
            final List<String> blockIdentifierAssign = this.getCommonBlockIdsForGivenVariable(assignedVar,
                    module.getCommonBlocks());

            // neither funcs or arrays
            final List<Node> namesRight = XPathParser.getNames(assigningContent);
            // something with args
            final List<Node> potentialFuncs = XPathParser.getPotentialFuncs(assigningContent);
            // func no args
            final List<Node> nonArgsFunc = XPathParser.getNonArgsFunc(assigningContent);

            if (potentialFuncs.size() > 0) { // potential function analysis
                final List<String> blockIdentifierList = this.analyzePotentialFuncStmt(potentialFuncs, bl, module,
                        caller, project);
                final List<String> namesAsString = this.convertToString(namesRight);
                blockIdentifierList.addAll(this.checkNamesWithCommon(namesAsString, module.getCommonBlocks()));
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
                    this.checkAssignWithCommon(assignedVar, caller, module, project);
                    final String functionId = XPathParser.getStructureConstructorIdentifier(cons);
                    final Pair<FortranModule, String> operation = this.findOperation(project.getModules().values(),
                            functionId);
                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller,
                                operation.getFirst().getFileName(), operation.getFirst().getModuleName(), functionId,
                                ProcessDataFlowAnalysisStage.READ);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add read dataflow call->cb {}", e.getLocalizedMessage());
                    }
                }
            } else {
                final List<String> namesAsString = this.convertToString(namesRight);
                final List<String> blockIdentifierList = this.checkNamesWithCommon(namesAsString,
                        module.getCommonBlocks());

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

    private List<String> analyzePotentialFuncStmt(final List<Node> funcs, final List<String> bl,
            final FortranModule module, final String caller, final FortranProject project) {
        final List<String> blockIdentifierList = new ArrayList<>();
        for (final Node funcNode : funcs) {
            final String functionIdentifier = XPathParser.getPartRefNodeIdentifier(funcNode);
            final List<String> functionInCommonBlockList = this.getCommonBlockIdsForGivenVariable(functionIdentifier,
                    module.getCommonBlocks());

            if (functionInCommonBlockList.size() > 0) {
                blockIdentifierList.addAll(functionInCommonBlockList);
                final List<String> args = XPathParser.getArgumentList(funcNode);
                for (final String arg : args) {
                    this.getCommonBlockIdsForGivenVariable(arg, module.getCommonBlocks());
                    // dataflowLinesRest.addAll(this.isVarFromCommonBlock(arg, commonBlocks));
                }

            } else if (!bl.contains(functionIdentifier)) {
                final List<String> args = XPathParser.getArgumentList(funcNode);
                if (args.size() > 0) {
                    for (final String arg : args) {
                        blockIdentifierList
                                .addAll(this.getCommonBlockIdsForGivenVariable(arg, module.getCommonBlocks()));
                    }

                    final Pair<FortranModule, String> operation = this.findOperation(project.getModules().values(),
                            functionIdentifier);

                    final String moduleName;
                    final String fileName;
                    if (operation == null) {
                        moduleName = this.defaultModuleName;
                        fileName = this.defaultModuleName;
                    } else {
                        moduleName = operation.getFirst().getModuleName();
                        fileName = operation.getFirst().getFileName();
                    }

                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, fileName,
                                moduleName, functionIdentifier, ProcessDataFlowAnalysisStage.BOTH);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Cannot add both dataflow call->f {}", e.getLocalizedMessage());
                    }
                } else {
                    final Pair<FortranModule, String> operation = this.findOperation(project.getModules().values(),
                            functionIdentifier);

                    final String moduleName;
                    final String fileName;
                    if (operation == null) {
                        moduleName = this.defaultModuleName;
                        fileName = this.defaultModuleName;
                    } else {
                        moduleName = operation.getFirst().getModuleName();
                        fileName = operation.getFirst().getFileName();
                    }

                    try {
                        this.out.getDataflow().addRow(module.getFileName(), module.getModuleName(), caller, fileName,
                                moduleName, functionIdentifier, ProcessDataFlowAnalysisStage.BOTH);
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

    /**
     * Check whether a given name is a variale reference.
     *
     * @param module
     *            module to check in
     * @param variableName
     *            name of the suspected variable
     * @return returns true in case it is a variable
     */
    private boolean isVariableReference(final FortranModule module, final String variableName) {
        return module.getVariables().contains(variableName.toLowerCase(Locale.getDefault()));
    }

    private List<String> getCommonBlockIdsForGivenVariable(final String variable,
            final Map<String, CommonBlock> commonBlocks) {
        final List<String> blockIdentifierList = new ArrayList<>();
        for (final CommonBlock commonBlock : commonBlocks.values()) {
            final String blockIdentifier = commonBlock.getName();
            final Set<String> varList = commonBlock.getElements();
            for (final String var : varList) {
                if (variable.equals(var)) {
                    blockIdentifierList.add(blockIdentifier);
                }
            }
        }
        return blockIdentifierList;
    }

    private void checkAssignWithCommon(final String assignedVar, final String caller, final FortranModule module,
            final FortranProject project) {

        final List<String> blockIdentifierList = this.getCommonBlockIdsForGivenVariable(assignedVar,
                module.getCommonBlocks());
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

    private List<String> checkNamesWithCommon(final List<String> names, final Map<String, CommonBlock> commonBlocks) {
        final List<String> blockIdentifierList = new ArrayList<>();
        if (commonBlocks.size() > 0) {
            for (final String name : names) {
                blockIdentifierList.addAll(this.getCommonBlockIdsForGivenVariable(name, commonBlocks));
            }
            return blockIdentifierList;
        }
        return blockIdentifierList;
    }

    private void writeCommonBlocksName(final Map<String, CommonBlock> commonBlocks, final String filename) {
        commonBlocks.values().forEach(commonBlock -> {
            try {
                this.out.getCommonBlocks().addRow(filename, "<no-module>", "<no-operation>", commonBlock.getName(),
                        commonBlock.getElements());
            } catch (final ValueConversionErrorException e) {
                this.logger.error("Common blocks file error {}", e.getLocalizedMessage());
            }
        });
    }

    // TODO duplicate form ProcessOperationCallStage
    private Pair<FortranModule, String> findOperation(final Collection<FortranModule> modules, final String signature) {
        final Optional<FortranModule> moduleOptional = modules.stream().filter(module -> module.getOperations().values()
                .stream().anyMatch(operation -> operation.getName().equals(signature))).findFirst();
        if (moduleOptional.isPresent()) {
            return new Pair<>(moduleOptional.get(), signature);
        } else {
            return null;
        }
    }

}
