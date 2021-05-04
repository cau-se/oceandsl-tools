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

import java.util.Map.Entry;

import org.eclipse.emf.common.util.EMap;
import org.oceandsl.architecture.model.stages.data.OperationCall;

import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.impl.EStringToDeployedOperationMapEntryImpl;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

/**
 * Stage to generate entries for the execution model based on @{link OperationCall}s.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class ExecutionModelGenerationStage extends AbstractConsumerStage<OperationCall> {

    private final ExecutionFactory factory = ExecutionFactory.eINSTANCE;

    private final ExecutionModel executionModel;

    private final OutputPort<OperationCall> outputPort = this.createOutputPort(OperationCall.class);

    public ExecutionModelGenerationStage(final ExecutionModel executionModel) {
        this.executionModel = executionModel;
    }

    @Override
    protected void execute(final OperationCall call) throws Exception {
        this.addExecution(call.getSourceOperation(), call.getTargetOperation());
        this.outputPort.send(call);
    }

    protected void addExecution(final DeployedOperation source, final DeployedOperation target) {
        final Tuple<DeployedOperation, DeployedOperation> key = this.factory.createTuple();
        key.setFirst(source);
        key.setSecond(target);

        // FIXME the containsKey method does not match two tuples, even if they have the same
        // hashCode
        // if (!this.executionModel.getAggregatedInvocations().containsKey(key)) {
        if (!this.containsKey(this.executionModel.getAggregatedInvocations(), key)) {
            final String keyString = this.printKey(key);
            for (final Entry<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> ag : this.executionModel
                    .getAggregatedInvocations()) {
                final String valueString = this.printKey(ag.getKey());
                if (valueString.equals(keyString)) {
                    System.out.println(key.hashCode() + " " + ag.getKey().hashCode());
                    System.out.println(">> " + valueString);
                }
            }
            final AggregatedInvocation invocation = this.factory.createAggregatedInvocation();
            invocation.setSource(source);
            invocation.setTarget(target);

            this.executionModel.getAggregatedInvocations().put(key, invocation);
        }
    }

    private boolean containsKey(
            final EMap<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> aggregatedInvocations,
            final Tuple<DeployedOperation, DeployedOperation> key) {
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> ag : this.executionModel
                .getAggregatedInvocations()) {
            if (ag.getKey().hashCode() == key.hashCode()) {
                return true;
            }
        }
        return false;
    }

    private String printKey(final Tuple<DeployedOperation, DeployedOperation> key) {
        final DeployedOperation dos = key.getFirst();
        final DeployedOperation dot = key.getSecond();
        final DeployedComponent dcs = (DeployedComponent) ((EStringToDeployedOperationMapEntryImpl) dos.eContainer())
                .eContainer();
        final DeployedComponent dct = (DeployedComponent) ((EStringToDeployedOperationMapEntryImpl) dot.eContainer())
                .eContainer();

        return String.format("%s::%s.%s --> %s:%s.%s", dcs.getDeploymentContext().getName(),
                dcs.getAssemblyComponent().getComponentType().getName(),
                dos.getAssemblyOperation().getOperationType().getSignature(), dct.getDeploymentContext().getName(),
                dct.getAssemblyComponent().getComponentType().getName(),
                dot.getAssemblyOperation().getOperationType().getSignature());
    }

    public OutputPort<OperationCall> getOutputPort() {
        return this.outputPort;
    }

}
