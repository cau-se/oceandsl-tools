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

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;

/**
 * Create the call table for a fortran project.
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
public class CreateDataflowTableStage extends AbstractTransformation<IDataflowEntry, Table> {

    // TODO does not create dataflow table

    private static final String SOURCE_PATH = "callerfilename";
    private static final String SOURCE_MODULE = "callermodule";
    private static final String SOURCE_OPERATION = "callerfunction";
    private static final String TARGET_PATH = "calleefilename";
    private static final String TARGET_MODULE = "calleemodule";
    private static final String TARGET_OPERATION = "calleefunction";
    private static final String DIRECTION = "direction";

    private final Table callsTable;

    public CreateDataflowTableStage() {
        this.callsTable = new Table("calls", new StringValueHandler(CreateDataflowTableStage.SOURCE_PATH),
                new StringValueHandler(CreateDataflowTableStage.SOURCE_MODULE),
                new StringValueHandler(CreateDataflowTableStage.SOURCE_OPERATION),
                new StringValueHandler(CreateDataflowTableStage.TARGET_PATH),
                new StringValueHandler(CreateDataflowTableStage.TARGET_MODULE),
                new StringValueHandler(CreateDataflowTableStage.TARGET_OPERATION),
                new StringValueHandler(CreateDataflowTableStage.DIRECTION));
    }

    @Override
    protected void execute(final IDataflowEntry entry) throws Exception {
        if (entry instanceof CallerCalleeDataflow) {
            final CallerCalleeDataflow ccd = (CallerCalleeDataflow) entry;
            this.callsTable.addRow(ccd.getSourceFileName(), ccd.getSourceModuleName(), ccd.getSourceOperationName(),
                    ccd.getTargetFileName(), ccd.getTargetModuleName(), ccd.getTargetOperatioName(),
                    ccd.getDirection().name());
        }
    }

    @Override
    protected void onTerminating() {
        this.outputPort.send(this.callsTable);
        super.onTerminating();
    }

}
