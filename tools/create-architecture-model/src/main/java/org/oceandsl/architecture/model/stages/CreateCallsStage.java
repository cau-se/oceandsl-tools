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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.oceandsl.architecture.model.stages.data.OperationCall;

import kieker.common.record.flow.IFlowRecord;
import kieker.common.record.flow.trace.TraceMetadata;
import kieker.common.record.flow.trace.operation.AfterOperationEvent;
import kieker.common.record.flow.trace.operation.BeforeOperationEvent;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

/**
 * @author Reiner Jung
 * @since 1.0
 */
public class CreateCallsStage extends AbstractConsumerStage<IFlowRecord> {

    private final Map<Long, Stack<BeforeOperationEvent>> cachedTraces = new HashMap<>();
    private final List<TraceMetadata> traces = new ArrayList<>();
    private final OutputPort<OperationCall> outputPort = this.createOutputPort(OperationCall.class);
    private final DeploymentModel deploymentModel;

    public CreateCallsStage(final DeploymentModel deploymentModel) {
        this.deploymentModel = deploymentModel;
    }

    @Override
    protected void execute(final IFlowRecord element) throws Exception {
        if (element instanceof TraceMetadata) {
            final TraceMetadata metadata = (TraceMetadata) element;
            this.traces.add(metadata);
            this.cachedTraces.put(metadata.getTraceId(), new Stack<BeforeOperationEvent>());
        } else if (element instanceof BeforeOperationEvent) {
            final BeforeOperationEvent currentEvent = (BeforeOperationEvent) element;
            final Stack<BeforeOperationEvent> eventStack = this.cachedTraces.get(currentEvent.getTraceId());
            if (eventStack.size() > 0) {
                final BeforeOperationEvent previousEvent = eventStack.peek();
                final DeploymentContext deploymentContext = this.deploymentModel.getDeploymentContexts()
                        .get(this.findDeploymentContext(currentEvent));
                final DeployedComponent sourceComponent = deploymentContext.getComponents()
                        .get(previousEvent.getClassSignature());
                final DeployedComponent targetComponent = deploymentContext.getComponents()
                        .get(currentEvent.getClassSignature());

                final DeployedOperation sourceOperation = sourceComponent.getContainedOperations()
                        .get(previousEvent.getOperationSignature());
                final DeployedOperation targetOperation = targetComponent.getContainedOperations()
                        .get(currentEvent.getOperationSignature());

                this.outputPort.send(new OperationCall(sourceOperation, targetOperation));
            }
            eventStack.push(currentEvent);
        } else if (element instanceof AfterOperationEvent) {
            final AfterOperationEvent currentEvent = (AfterOperationEvent) element;
            final Stack<BeforeOperationEvent> eventStack = this.cachedTraces.get(currentEvent.getTraceId());
            final BeforeOperationEvent beforeOperationEvent = eventStack.pop();
            if (!beforeOperationEvent.getOperationSignature().equals(currentEvent.getOperationSignature())) {
                // broken trace
                this.logger.error("Trace is broken. Found {}, expected {}", currentEvent.getOperationSignature(),
                        beforeOperationEvent.getOperationSignature());
                eventStack.push(beforeOperationEvent);
            }
        }
    }

    private String findDeploymentContext(final BeforeOperationEvent currentEvent) {
        for (final TraceMetadata trace : this.traces) {
            if (trace.getTraceId() == currentEvent.getTraceId()) {
                return trace.getHostname();
            }
        }
        return "";
    }

    public OutputPort<OperationCall> getOutputPort() {
        return this.outputPort;
    }

}
