/***************************************************************************
 * Copyright (C) 2021 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.architecture.model.stages;

import org.oceandsl.analysis.CallerCallee;

import kieker.common.record.flow.IFlowRecord;
import kieker.common.record.flow.trace.TraceMetadata;
import kieker.common.record.flow.trace.operation.AfterOperationEvent;
import kieker.common.record.flow.trace.operation.BeforeOperationEvent;
import teetime.stage.basic.AbstractTransformation;

/**
 * @author Reiner Jung
 * @since 1.0
 *
 */
public class CSVMapperStage extends AbstractTransformation<CallerCallee, IFlowRecord> {

    private static final long THREAD_ID = 0;
    private static final String SESSION_ID = "<no session>";

    private long traceId = 0;
    private long time = 0;
    private final boolean caseInsensitive;
    private final String hostname;

    public CSVMapperStage(final String hostname, final boolean caseInsensitive) {
        this.hostname = hostname;
        this.caseInsensitive = caseInsensitive;
    }

    @Override
    protected void execute(final CallerCallee element) throws Exception {
        this.outputPort.send(new TraceMetadata(++this.traceId, CSVMapperStage.THREAD_ID, CSVMapperStage.SESSION_ID,
                this.hostname, -1, -1));
        this.outputPort.send(new BeforeOperationEvent(++this.time, this.traceId, 0,
                this.convertToLowerCase(element.getCaller()), this.convertToLowerCase(element.getSourcePath())));
        this.outputPort.send(new BeforeOperationEvent(++this.time, this.traceId, 1,
                this.convertToLowerCase(element.getCallee()), this.convertToLowerCase(element.getTargetPath())));
        this.outputPort.send(new AfterOperationEvent(++this.time, this.traceId, 2,
                this.convertToLowerCase(element.getCallee()), this.convertToLowerCase(element.getTargetPath())));
        this.outputPort.send(new AfterOperationEvent(++this.time, this.traceId, 3,
                this.convertToLowerCase(element.getCaller()), this.convertToLowerCase(element.getSourcePath())));
    }

    private String convertToLowerCase(final String string) {
        return this.caseInsensitive ? string.toLowerCase() + "_" : string;
    }

}
