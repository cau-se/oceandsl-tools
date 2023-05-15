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
package org.oceandsl.tools.fxca.stages.calls;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;
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
public class CreateCallTableStage extends AbstractTransformation<FortranProject, Table> {

    private static final String SOURCE_PATH = "caller-path";
    private static final String SOURCE_MODULE = "caller-module";
    private static final String SOURCE_OPERATION = "caller-operation";
    private static final String TARGET_PATH = "callee-path";
    private static final String TARGET_MODULE = "callee-module";
    private static final String TARGET_OPERATION = "callee-operation";

    @Override
    protected void execute(final FortranProject project) throws Exception {
        final Table callsTable = new Table("calls", new StringValueHandler(CreateCallTableStage.SOURCE_PATH),
                new StringValueHandler(CreateCallTableStage.SOURCE_MODULE),
                new StringValueHandler(CreateCallTableStage.SOURCE_OPERATION),
                new StringValueHandler(CreateCallTableStage.TARGET_PATH),
                new StringValueHandler(CreateCallTableStage.TARGET_MODULE),
                new StringValueHandler(CreateCallTableStage.TARGET_OPERATION));

        project.getCalls().forEach(call -> {
            final Pair<FortranModule, FortranOperation> callerPair = call.getFirst();
            final Pair<FortranModule, FortranOperation> calleePair = call.getSecond();

            if (callerPair != null) {
                final Operation caller = this.composeOperation(callerPair);
                if (calleePair != null) {
                    final Operation callee = this.composeOperation(calleePair);
                    try {
                        callsTable.addRow(caller.path, caller.moduleName, caller.operation, callee.path,
                                callee.moduleName, callee.operation);
                    } catch (final ValueConversionErrorException e) {
                        this.logger.error("Error writing calls to table: {}", e.getLocalizedMessage());
                    }
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
