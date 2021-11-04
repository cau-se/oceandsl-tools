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
package org.oceandsl.tools.mop.merge;

import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.deployment.impl.EStringToDeployedComponentMapEntryImpl;
import kieker.model.analysismodel.deployment.impl.EStringToDeployedOperationMapEntryImpl;
import kieker.model.analysismodel.deployment.impl.EStringToDeployedStorageMapEntryImpl;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.AggregatedStorageAccess;
import kieker.model.analysismodel.execution.ExecutionFactory;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public final class ExecutionModelCloneUtils {

    private ExecutionModelCloneUtils() {
        // Utility class
    }

    public static AggregatedInvocation duplicate(final DeploymentModel deploymentModel,
            final AggregatedInvocation invocation) {
        final AggregatedInvocation newInvocation = ExecutionFactory.eINSTANCE.createAggregatedInvocation();
        newInvocation
                .setSource(ExecutionModelCloneUtils.findDeployedOperation(deploymentModel, invocation.getSource()));
        newInvocation
                .setTarget(ExecutionModelCloneUtils.findDeployedOperation(deploymentModel, invocation.getTarget()));

        return newInvocation;
    }

    public static AggregatedStorageAccess duplicate(final DeploymentModel deploymentModel,
            final AggregatedStorageAccess access) {
        final AggregatedStorageAccess newAccess = ExecutionFactory.eINSTANCE.createAggregatedStorageAccess();
        newAccess.setDirection(access.getDirection());

        newAccess.setCode(ExecutionModelCloneUtils.findDeployedOperation(deploymentModel, access.getCode()));
        newAccess.setStorage(ExecutionModelCloneUtils.findDeployedStorage(deploymentModel, access.getStorage()));

        return newAccess;
    }

    private static DeployedOperation findDeployedOperation(final DeploymentModel targetModel,
            final DeployedOperation operation) {
        final EStringToDeployedOperationMapEntryImpl mapOperationEntry = (EStringToDeployedOperationMapEntryImpl) operation
                .eContainer();
        final DeployedComponent component = (DeployedComponent) mapOperationEntry.eContainer();

        final EStringToDeployedComponentMapEntryImpl mapComponentEntry = (EStringToDeployedComponentMapEntryImpl) component
                .eContainer();
        final DeploymentContext context = (DeploymentContext) mapComponentEntry.eContainer();

        final DeploymentContext newContext = targetModel.getDeploymentContexts().get(context.getName());
        final DeployedComponent newComponent = newContext.getComponents().get(component.getSignature());

        return newComponent.getContainedOperations()
                .get(operation.getAssemblyOperation().getOperationType().getSignature());
    }

    private static DeployedStorage findDeployedStorage(final DeploymentModel targetModel,
            final DeployedStorage storage) {
        final EStringToDeployedStorageMapEntryImpl mapStorageEntry = (EStringToDeployedStorageMapEntryImpl) storage
                .eContainer();
        final DeployedComponent component = (DeployedComponent) mapStorageEntry.eContainer();

        final EStringToDeployedComponentMapEntryImpl mapComponentEntry = (EStringToDeployedComponentMapEntryImpl) component
                .eContainer();
        final DeploymentContext context = (DeploymentContext) mapComponentEntry.eContainer();

        final DeploymentContext newContext = targetModel.getDeploymentContexts().get(context.getName());
        final DeployedComponent newComponent = newContext.getComponents().get(component.getSignature());
        return newComponent.getContainedStorages().get(storage.getAssemblyStorage().getStorageType().getName());
    }

}
