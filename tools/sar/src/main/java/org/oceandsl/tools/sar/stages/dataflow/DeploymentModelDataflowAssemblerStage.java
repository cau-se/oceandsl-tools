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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.sources.SourceModel;
import teetime.stage.basic.AbstractTransformation;

/**
 * @author Reiner Jung
 * @since 1.1
 *
 */
public class DeploymentModelDataflowAssemblerStage extends AbstractTransformation<DataAccess, DataAccess> {

    private final AssemblyModel assemblyModel;
    private final DeploymentModel deploymentModel;
    private final SourceModel sourceModel;
    private final String sourceLabel;
    private final Map<String, DeployedStorage> deployedStorageMap = new HashMap<>();
    private Map<DeployedStorage, List<DeployedComponent>> deployedStorageAccessMap;

    public DeploymentModelDataflowAssemblerStage(final AssemblyModel assemblyModel,
            final DeploymentModel deploymentModel, final SourceModel sourceModel, final String sourceLabel) {
        this.assemblyModel = assemblyModel;
        this.deploymentModel = deploymentModel;
        this.sourceModel = sourceModel;
        this.sourceLabel = sourceLabel;
    }

    @Override
    protected void execute(final DataAccess element) throws Exception {
        final DeployedOperation operation = this.findOperation(element);
        final DeployedStorage assemblyStorage = this.findOrCreateDataStorage(element, operation.getComponent());

        this.addObjectToSource(assemblyStorage);
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
        final DeploymentContext context = this.deploymentModel.getDeploymentContexts().get(0).getValue();
        for (final DeployedComponent component : context.getComponents().values()) {
            final DeployedOperation deployedOperation = component.getContainedOperations().get(element.getOperation());
            if (deployedOperation != null) {
                return deployedOperation;
            }
        }
        this.logger.error("Internal error. Operation {} cannot be found in model.", element.getOperation());
        return null;
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

    private void addObjectToSource(final EObject object) {
        final EList<String> sources = this.sourceModel.getSources().get(object);
        boolean exists = false;
        for (final String source : sources) {
            if (this.sourceLabel.equals(source)) {
                exists = true;
            }
        }
        if (!exists) {
            sources.add(this.sourceLabel);
            this.sourceModel.getSources().put(object, sources);
        }
    }

}
