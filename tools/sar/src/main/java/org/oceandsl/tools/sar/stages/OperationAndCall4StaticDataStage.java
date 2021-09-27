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
package org.oceandsl.tools.sar.stages;

import java.time.Duration;

import org.oceandsl.analysis.CallerCallee;

import kieker.analysis.stage.model.data.CallEvent;
import kieker.analysis.stage.model.data.OperationEvent;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class OperationAndCall4StaticDataStage extends AbstractConsumerStage<CallerCallee> {

    private final OutputPort<OperationEvent> operationOutputPort = this.createOutputPort(OperationEvent.class);
    private final OutputPort<CallEvent> callOutputPort = this.createOutputPort(CallEvent.class);

    private final String hostname;

    public OperationAndCall4StaticDataStage(final String hostname) {
        this.hostname = hostname;
    }

    @Override
    protected void execute(final CallerCallee element) throws Exception {
        final OperationEvent caller = new OperationEvent(this.hostname, element.getSourcePath(), element.getCaller());
        final OperationEvent callee = new OperationEvent(this.hostname, element.getTargetPath(), element.getCallee());

        final CallEvent callEvent = new CallEvent(caller, callee, Duration.ZERO);

        this.operationOutputPort.send(caller);
        this.operationOutputPort.send(callee);
        this.callOutputPort.send(callEvent);
    }

    public OutputPort<CallEvent> getCallOutputPort() {
        return this.callOutputPort;
    }

    public OutputPort<OperationEvent> getOperationOutputPort() {
        return this.operationOutputPort;
    }
}
