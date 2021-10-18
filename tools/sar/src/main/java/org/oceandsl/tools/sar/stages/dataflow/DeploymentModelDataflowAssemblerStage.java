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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.sources.SourceModel;

/**
 * @author Reiner Jung
 * @since 1.1
 *
 */
public class DeploymentModelDataflowAssemblerStage extends AbstractDataflowAssemblerStage<DataAccess, DataAccess> {

    private final AssemblyModel assemblyModel;
    private final DeploymentModel deploymentModel;
    private final Map<String, DeployedStorage> deployedStorageMap = new HashMap<>();
    private final Map<DeployedStorage, List<DeployedComponent>> deployedStorageAccessMap = new HashMap<>();

    public DeploymentModelDataflowAssemblerStage(final AssemblyModel assemblyModel,
            final DeploymentModel deploymentModel, final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.assemblyModel = assemblyModel;
        this.deploymentModel = deploymentModel;
    }

    @Override
    protected void execute(final DataAccess element) throws Exception {
        final DeployedOperation deployedOperation = this.findOperation(element);
        final DeployedStorage deployedStorage = this.findOrCreateDataStorage(element, deployedOperation.getComponent());

        this.addObjectToSource(deployedStorage);
        this.outputPort.send(element);
    }

    private DeployedStorage findOrCreateDataStorage(final DataAccess element,
            final DeployedComponent deployedComponent) {
        DeployedStorage deployedStorage = this.deployedStorageMap.get(element.getSharedData());
        if (deployedStorage == null) {
            deployedStorage = DeploymentFactory.eINSTANCE.createDeployedStorage();
            deployedStorage.setAssemblyStorage(this.findAssemblyStorage(element.getSharedData()));
            deployedComponent.getContainedStorages().put(element.getSharedData(), deployedStorage);
            this.deployedStorageMap.put(element.getSharedData(), deployedStorage);

            final List<DeployedComponent> newList = new ArrayList<>();
            newList.add(deployedComponent);
            this.deployedStorageAccessMap.put(deployedStorage, newList);
        } else {
            final List<DeployedComponent> accessedByList = this.deployedStorageAccessMap.get(deployedStorage);
            if (!accessedByList.contains(deployedComponent)) {
                if (accessedByList.size() == 1) {
                    this.moveStorageAccessToSeparateComponent(deployedStorage, accessedByList.get(0));
                }
                accessedByList.add(deployedComponent);
                this.deployedStorageAccessMap.put(deployedStorage, accessedByList);
            }
        }

        return deployedStorage;
    }

    private AssemblyStorage findAssemblyStorage(final String sharedData) {
        for (final AssemblyComponent assemblyComponent : this.assemblyModel.getAssemblyComponents().values()) {
            final AssemblyStorage assemblyStorage = assemblyComponent.getAssemblyStorages().get(sharedData);
            if (assemblyStorage != null) {
                return assemblyStorage;
            }
        }
        this.logger.error("Internal error. Could not find previously defined assembly storage {}", sharedData);
        return null;
    }

    private DeployedOperation findOperation(final DataAccess element) {
        final DeploymentContext deployedContext = this.deploymentModel.getDeploymentContexts().get(0).getValue();
        if (deployedContext == null) {
            this.logger.error("Internal error: Data must contain at least one deployment context.");
            return null;
        } else {
            DeployedComponent deployedComponent = deployedContext.getComponents().get(element.getModule());
            if (deployedComponent == null) {
                deployedComponent = DeploymentFactory.eINSTANCE.createDeployedComponent();
                deployedComponent.setSignature(element.getModule());
                deployedComponent.setAssemblyComponent(this.findAssemblyComponent(element.getModule()));
                deployedContext.getComponents().put(element.getModule(), deployedComponent);
                this.addObjectToSource(deployedComponent);
            }
            DeployedOperation deployedOperation = deployedComponent.getContainedOperations()
                    .get(element.getOperation());
            if (deployedOperation == null) {
                final AssemblyComponent assemblyComponent = this.assemblyModel.getAssemblyComponents()
                        .get(element.getModule());
                final AssemblyOperation assemblyOperation = assemblyComponent.getAssemblyOperations()
                        .get(element.getOperation());
                deployedOperation = DeploymentFactory.eINSTANCE.createDeployedOperation();
                deployedOperation.setAssemblyOperation(assemblyOperation);
                deployedComponent.getContainedOperations().put(element.getOperation(), deployedOperation);
                this.addObjectToSource(deployedOperation);
            }
            return deployedOperation;
        }
    }

    private void moveStorageAccessToSeparateComponent(final DeployedStorage deployedStorage,
            final DeployedComponent deployedComponent) {
        final String name = deployedStorage.getAssemblyStorage().getStorageType().getName();
        deployedComponent.getContainedStorages().removeKey(name);

        final DeployedComponent newDeployedComponent = DeploymentFactory.eINSTANCE.createDeployedComponent();
        newDeployedComponent.setAssemblyComponent(
                this.findAssemblyComponent(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE + "." + name));
        newDeployedComponent.setSignature(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE + "." + name);
        newDeployedComponent.getContainedStorages().put(name, deployedStorage);

        this.addObjectToSource(newDeployedComponent);
        this.deploymentModel.getDeploymentContexts().get(0).getValue().getComponents()
                .put(newDeployedComponent.getSignature(), newDeployedComponent);
    }

    private AssemblyComponent findAssemblyComponent(final String signature) {
        return this.assemblyModel.getAssemblyComponents().get(signature);
    }

}
