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
package org.oceandsl.tools.fxca.stages.dataflow;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.CallerCalleeEntry;
import org.oceandsl.analysis.generic.Table;
import org.oceandsl.tools.fxca.model.FortranModule;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranProject;
import org.oceandsl.tools.fxca.utils.Pair;

/**
 * Create the call table for a fortran project.
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
public class CreateFileContentsTableStage extends AbstractTransformation<FortranProject, Table<CallerCalleeEntry>> {

    // TODO does not create file content table

    private static final String SOURCE_PATH = "callerfilename";
    private static final String SOURCE_MODULE = "callermodule";
    private static final String SOURCE_OPERATION = "callerfunction";
    private static final String TARGET_PATH = "calleefilename";
    private static final String TARGET_MODULE = "calleemodule";
    private static final String TARGET_OPERATION = "calleefunction";

    @Override
    protected void execute(final FortranProject project) throws Exception {
        final Table<CallerCalleeEntry> callsTable = new Table<>("calls", CreateFileContentsTableStage.SOURCE_PATH,
                CreateFileContentsTableStage.SOURCE_MODULE, CreateFileContentsTableStage.SOURCE_OPERATION,
                CreateFileContentsTableStage.TARGET_PATH, CreateFileContentsTableStage.TARGET_MODULE,
                CreateFileContentsTableStage.TARGET_OPERATION);

        project.getCalls().forEach(call -> {
            final Pair<FortranModule, FortranOperation> callerPair = call.getFirst();
            final Pair<FortranModule, FortranOperation> calleePair = call.getSecond();

            if (callerPair != null) {
                final Operation caller = this.composeOperation(callerPair);
                if (calleePair != null) {
                    final Operation callee = this.composeOperation(calleePair);
                    callsTable.getRows().add(new CallerCalleeEntry(caller.path, caller.moduleName, caller.operation,
                            callee.path, callee.moduleName, callee.operation));
                } else {
                    this.logger.warn("Caller {} {} {} has no callee ", caller.path, caller.moduleName,
                            caller.operation);
                }
            } else {
                if (calleePair != null) {
                    final Operation callee = this.composeOperation(calleePair);
                    this.logger.warn("No caller for callee {} {} {}", callee.path, callee.moduleName, callee.operation);
                } else {
                    this.logger.error("Empty call");
                }
            }
        });

        this.outputPort.send(callsTable);
    }

    private Operation composeOperation(final Pair<FortranModule, FortranOperation> operationDescription) {
        final FortranModule module = operationDescription.getFirst();

        final FortranOperation callerOperation = operationDescription.getSecond();

        if (module.isNamedModule()) {
            return new Operation(module.getFileName(), module.getModuleName(), callerOperation.getName());
        } else {
            return new Operation(module.getFileName(), "<no-module>", callerOperation.getName());
        }
    }

    private class Operation {
        private final String path;
        private final String moduleName;
        private final String operation;

        Operation(final String path, final String moduleName, final String operation) {
            this.path = path;
            this.moduleName = moduleName;
            this.operation = operation;
        }
    }

}
