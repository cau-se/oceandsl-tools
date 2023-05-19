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
package org.oceandsl.tools.sar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kieker.analysis.architecture.recovery.events.AbstractElementEvent;
import kieker.analysis.architecture.recovery.events.OperationEvent;

import teetime.framework.AbstractStage;
import teetime.framework.InputPort;
import teetime.framework.OutputPort;

import org.oceandsl.tools.sar.stages.dataflow.DataflowEvent;

/**
 * This stage holds {@link DataflowEvent}s back until the corresponding {@link OperationEvent}s have
 * been processed.
 *
 * @author Reiner Jung
 * @since 1.3.0
 *
 */
public class DataflowConstraintStage extends AbstractStage {

    private final InputPort<DataflowEvent> inputPort = this.createInputPort(DataflowEvent.class);
    private final InputPort<OperationEvent> controlInputPort = this.createInputPort(OperationEvent.class);
    private final OutputPort<DataflowEvent> outputPort = this.createOutputPort(DataflowEvent.class);

    private final Map<String, Map<String, Set<String>>> operationEventRegister = new HashMap<>();
    private final List<DataflowEvent> storedEvents = new ArrayList<>();

    @Override
    protected void execute() throws Exception {
        this.processOperationEvent();
        this.processDataflowEvent();
    }

    private void processOperationEvent() {
        final OperationEvent operationEvent = this.controlInputPort.receive();

        if (operationEvent != null) {
            if (this.operationEventRegister.containsKey(operationEvent.getHostname())) {
                final Map<String, Set<String>> components = this.operationEventRegister
                        .get(operationEvent.getHostname());
                if (components.containsKey(operationEvent.getComponentSignature())) {
                    final Set<String> operations = components.get(operationEvent.getComponentSignature());
                    if (!operations.contains(operationEvent.getOperationSignature())) {
                        operations.add(operationEvent.getOperationSignature());
                    }
                } else {
                    components.put(operationEvent.getComponentSignature(), new HashSet<String>());
                }
            } else {
                final Map<String, Set<String>> components = new HashMap<>();
                final Set<String> operations = new HashSet<>();
                operations.add(operationEvent.getOperationSignature());
                components.put(operationEvent.getComponentSignature(), operations);
                this.operationEventRegister.put(operationEvent.getHostname(), components);
            }
            for (int i = 0; i < this.storedEvents.size(); i++) {
                final DataflowEvent dataflowEvent = this.storedEvents.get(i);
                if (this.checkEndpoint(dataflowEvent.getSource()) && this.checkEndpoint(dataflowEvent.getTarget())) {
                    this.outputPort.send(dataflowEvent);
                    this.storedEvents.remove(i);
                }
            }
        }
    }

    private void processDataflowEvent() {
        final DataflowEvent dataflowEvent = this.inputPort.receive();
        if (dataflowEvent != null) {
            if (this.checkEndpoint(dataflowEvent.getSource()) && this.checkEndpoint(dataflowEvent.getTarget())) {
                this.outputPort.send(dataflowEvent);
            } else {
                this.storedEvents.add(dataflowEvent);
            }
        }
    }

    private boolean checkEndpoint(final AbstractElementEvent event) {
        if (event instanceof OperationEvent) {
            return this.checkOperationEvent((OperationEvent) event);
        } else {
            return true;
        }
    }

    private boolean checkOperationEvent(final OperationEvent event) {
        final Map<String, Set<String>> components = this.operationEventRegister.get(event.getHostname());
        if (components != null) {
            final Set<String> operations = components.get(event.getComponentSignature());
            if (operations != null) {
                return operations.contains(event.getOperationSignature());
            }
        }
        return false;
    }

    public InputPort<DataflowEvent> getInputPort() {
        return this.inputPort;
    }

    public InputPort<OperationEvent> getControlInputPort() {
        return this.controlInputPort;
    }

    public OutputPort<DataflowEvent> getOutputPort() {
        return this.outputPort;
    }

}