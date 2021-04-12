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
    private static final String HOSTNAME = "<static>";

    private long traceId = 0;
    private long time = 0;
    private final String prefix;

    public CSVMapperStage(final String prefix) {
        this.prefix = prefix;
    }

    @Override
    protected void execute(final CallerCallee element) throws Exception {
        this.outputPort.send(new TraceMetadata(++this.traceId, CSVMapperStage.THREAD_ID, CSVMapperStage.SESSION_ID,
                CSVMapperStage.HOSTNAME, -1, -1));
        this.outputPort.send(new BeforeOperationEvent(++this.time, this.traceId, 0, element.getCaller(),
                this.fixSignature(element.getSourcePath())));
        this.outputPort.send(new BeforeOperationEvent(++this.time, this.traceId, 1, element.getCallee(),
                this.fixSignature(element.getTargetPath())));
        this.outputPort.send(new AfterOperationEvent(++this.time, this.traceId, 2, element.getCallee(),
                this.fixSignature(element.getTargetPath())));
        this.outputPort.send(new AfterOperationEvent(++this.time, this.traceId, 3, element.getCaller(),
                this.fixSignature(element.getSourcePath())));
    }

    private String fixSignature2(final String signature) {
        return "node";
    }

    private String fixSignature(final String signature) {
        if (signature.startsWith(this.prefix)) {
            return signature.substring(this.prefix.length());
        } else {
            return signature;
        }
    }

}
