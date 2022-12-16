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
package org.oceandsl.tools.sar.stages.dataflow;

import java.time.Duration;
import java.util.regex.Pattern;

import org.oceandsl.analysis.code.OperationStorage;
import org.oceandsl.tools.sar.stages.calls.DataflowEvent;

import kieker.analysis.architecture.recovery.events.AbstractElementEvent;
import kieker.analysis.architecture.recovery.events.OperationEvent;
import kieker.analysis.architecture.recovery.events.StorageEvent;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

/**
 * Transform @{link CallerCallee}s to @{link OperationEvent}s and @{CallEvent}s on model level. The
 * stage outputs two @{link OperationEvent}s and one @{link CallEvent}s for each @{CallerCallee}
 * event. It is used to convert static caller-callee data to operation and call data compatible with
 * the dynamic architecture reconstruction.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class ElementAndDataflow4StaticDataStage extends AbstractConsumerStage<OperationStorage> {

    private final OutputPort<OperationEvent> operationOutputPort = this.createOutputPort(OperationEvent.class);
    private final OutputPort<StorageEvent> storageOutputPort = this.createOutputPort(StorageEvent.class);

    private final OutputPort<DataflowEvent> dataflowOutputPort = this.createOutputPort(DataflowEvent.class);

    private final String hostname;

    private final Pattern operationSignaturePattern = Pattern.compile("(\\w*)? (\\w*)\\(.*\\) ?.*");

    public ElementAndDataflow4StaticDataStage(final String hostname) {
        this.hostname = hostname;
    }

    @Override
    protected void execute(final OperationStorage element) throws Exception {
        final AbstractElementEvent source;
        final AbstractElementEvent target;

        if (this.isOperation(element.getSourceSignature())) {
            source = new OperationEvent(this.hostname, element.getSourceModule(), element.getSourceSignature());
            this.operationOutputPort.send((OperationEvent) source);
        } else {
            source = new StorageEvent(this.hostname, element.getSourceModule(), element.getSourceSignature(),
                    this.getStorageType(element.getSourceSignature()));
            this.storageOutputPort.send((StorageEvent) source);
        }

        if (this.isOperation(element.getTargetSignature())) {
            target = new OperationEvent(this.hostname, element.getSourceModule(), element.getSourceSignature());
            this.operationOutputPort.send((OperationEvent) target);
        } else {
            target = new StorageEvent(this.hostname, element.getSourceModule(), element.getSourceSignature(),
                    this.getStorageType(element.getSourceSignature()));
            this.storageOutputPort.send((StorageEvent) target);
        }

        final DataflowEvent dataflowEvent = new DataflowEvent(source, target, element.getDirection(), Duration.ZERO);

        this.dataflowOutputPort.send(dataflowEvent);
    }

    private boolean isOperation(final String signature) {
        return this.operationSignaturePattern.matcher(signature).matches();
    }

    private String getStorageType(final String sourceSignature) {
        final String[] result = sourceSignature.split(":");
        return result[0];
    }

    public OutputPort<StorageEvent> getStorageOutputPort() {
        return this.storageOutputPort;
    }

    public OutputPort<OperationEvent> getOperationOutputPort() {
        return this.operationOutputPort;
    }

    public OutputPort<DataflowEvent> getDataflowOutputPort() {
        return this.dataflowOutputPort;
    }
}
