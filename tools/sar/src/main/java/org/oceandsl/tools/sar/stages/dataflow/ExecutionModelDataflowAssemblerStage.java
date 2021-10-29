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

import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.AggregatedStorageAccess;
import kieker.model.analysismodel.execution.EDirection;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.sources.SourceModel;

/**
 * @author Reiner Jung
 *
 */
public class ExecutionModelDataflowAssemblerStage extends AbstractDataflowAssemblerStage<DataAccess, DataAccess> {

    private final ExecutionModel executionModel;
    private final DeploymentModel deploymentModel;

    public ExecutionModelDataflowAssemblerStage(final ExecutionModel executionModel,
            final DeploymentModel deploymentModel, final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.executionModel = executionModel;
        this.deploymentModel = deploymentModel;
    }

    @Override
    protected void execute(final DataAccess element) throws Exception {
        final DeploymentContext context = this.deploymentModel.getDeploymentContexts().get(0).getValue();
        final DeployedComponent callerComponent = context.getComponents().get(element.getModule());
        final DeployedOperation callerOperation = callerComponent.getContainedOperations().get(element.getOperation());
        final DeployedStorage accessedStorage = this.findStorage(context, element.getSharedData());

        // TODO this must check whether an access already exists.

        final Tuple<DeployedOperation, DeployedStorage> key = ExecutionFactory.eINSTANCE.createTuple();
        key.setFirst(callerOperation);
        key.setSecond(accessedStorage);
        this.addObjectToSource(key);

        AggregatedStorageAccess aggregatedStorageAccess = this.executionModel.getAggregatedStorageAccesses().get(key);
        if (aggregatedStorageAccess == null) {
            aggregatedStorageAccess = ExecutionFactory.eINSTANCE.createAggregatedStorageAccess();
            aggregatedStorageAccess.setCode(callerOperation);
            aggregatedStorageAccess.setStorage(accessedStorage);
            aggregatedStorageAccess.setDirection(this.convertDirection(element.getDirection()));
            this.executionModel.getAggregatedStorageAccesses().put(key, aggregatedStorageAccess);
            this.addObjectToSource(aggregatedStorageAccess);
        } else {
            final EDirection newDirection = this.convertDirection(element.getDirection());
            if (aggregatedStorageAccess.getDirection() == EDirection.READ
                    && (newDirection == EDirection.WRITE || newDirection == EDirection.BOTH)) {
                aggregatedStorageAccess.setDirection(EDirection.BOTH);
            } else if (aggregatedStorageAccess.getDirection() == EDirection.WRITE
                    && (newDirection == EDirection.READ || newDirection == EDirection.BOTH)) {
                aggregatedStorageAccess.setDirection(EDirection.BOTH);
            }
        }

        this.outputPort.send(element);

    }

    private EDirection convertDirection(final org.oceandsl.tools.sar.stages.dataflow.EDirection direction) {
        switch (direction) {
        case READ:
            return EDirection.READ;
        case WRITE:
            return EDirection.WRITE;
        case BOTH:
            return EDirection.BOTH;
        default:
            throw new InternalError("Unknown direction type found " + direction.name());
        }
    }

    private DeployedStorage findStorage(final DeploymentContext context, final String name) {
        for (final DeployedComponent component : context.getComponents().values()) {
            final DeployedStorage storage = component.getContainedStorages().get(name);
            if (storage != null) {
                return storage;
            }
        }
        this.logger.error("Internal error. Cannot find storage {}", name);
        return null;
    }

}
