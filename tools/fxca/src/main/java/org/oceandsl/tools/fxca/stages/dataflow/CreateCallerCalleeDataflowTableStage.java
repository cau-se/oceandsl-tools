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

import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;
import org.oceandsl.tools.fxca.stages.dataflow.data.CallerCalleeDataflow;

/**
 * Create the call table for a fortran project.
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
public class CreateCallerCalleeDataflowTableStage extends AbstractTransformation<CallerCalleeDataflow, Table> {

    private static final String SOURCE_PATH = "callerfilename";
    private static final String SOURCE_MODULE = "callermodule";
    private static final String SOURCE_OPERATION = "callerfunction";
    private static final String TARGET_PATH = "calleefilename";
    private static final String TARGET_MODULE = "calleemodule";
    private static final String TARGET_OPERATION = "calleefunction";
    private static final String DIRECTION = "direction";

    private final Table callsTable;

    public CreateCallerCalleeDataflowTableStage() {
        this.callsTable = new Table("dataflow-cc",
                new StringValueHandler(CreateCallerCalleeDataflowTableStage.SOURCE_PATH),
                new StringValueHandler(CreateCallerCalleeDataflowTableStage.SOURCE_MODULE),
                new StringValueHandler(CreateCallerCalleeDataflowTableStage.SOURCE_OPERATION),
                new StringValueHandler(CreateCallerCalleeDataflowTableStage.TARGET_PATH),
                new StringValueHandler(CreateCallerCalleeDataflowTableStage.TARGET_MODULE),
                new StringValueHandler(CreateCallerCalleeDataflowTableStage.TARGET_OPERATION),
                new StringValueHandler(CreateCallerCalleeDataflowTableStage.DIRECTION));
    }

    @Override
    protected void execute(final CallerCalleeDataflow callerCalleeDataflow) throws Exception {
        this.callsTable.addRow(callerCalleeDataflow.getSourceFileName(), callerCalleeDataflow.getSourceModuleName(),
                callerCalleeDataflow.getSourceOperationName(), callerCalleeDataflow.getTargetFileName(),
                callerCalleeDataflow.getTargetModuleName(), callerCalleeDataflow.getTargetOperatioName(),
                callerCalleeDataflow.getDirection().name());
    }

    @Override
    protected void onTerminating() {
        this.outputPort.send(this.callsTable);
        super.onTerminating();
    }

}
