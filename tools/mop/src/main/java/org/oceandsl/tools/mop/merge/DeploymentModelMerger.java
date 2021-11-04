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

import java.util.Map.Entry;

import org.eclipse.emf.common.util.EMap;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public final class DeploymentModelMerger {

    private DeploymentModelMerger() {
        // Utility class
    }

    /* default */ static void mergeDeploymentModel(final AssemblyModel assemblyModel, final DeploymentModel model, // NOPMD
            final DeploymentModel mergeModel) {
        // add additional contexts if necessary
        for (final DeploymentContext mergeDeploymentContext : mergeModel.getDeploymentContexts().values()) {
            if (!model.getDeploymentContexts().containsKey(mergeDeploymentContext.getName())) {
                model.getDeploymentContexts().put(mergeDeploymentContext.getName(),
                        DeploymentModelCloneUtils.duplicate(assemblyModel, mergeDeploymentContext));
            }
        }
        // now merge operations
        for (final DeploymentContext deploymentContext : model.getDeploymentContexts().values()) {
            final DeploymentContext mergeDeploymentContext = mergeModel.getDeploymentContexts()
                    .get(deploymentContext.getName());
            if (mergeDeploymentContext != null) {
                DeploymentModelMerger.mergeDepolymentComponents(assemblyModel, deploymentContext,
                        mergeDeploymentContext.getComponents());
            }
        }

    }

    private static void mergeDepolymentComponents(final AssemblyModel assemblyModel,
            final DeploymentContext deploymentContext, final EMap<String, DeployedComponent> components) {
        for (final DeployedComponent mergeComponent : components.values()) {
            if (!deploymentContext.getComponents().containsKey(mergeComponent.getSignature())) {
                deploymentContext.getComponents().put(mergeComponent.getSignature(),
                        DeploymentModelCloneUtils.duplicate(assemblyModel, mergeComponent));
            } else {
                final DeployedComponent component = deploymentContext.getComponents()
                        .get(mergeComponent.getSignature());
                DeploymentModelMerger.mergeDeploymentOperations(component.getAssemblyComponent(), component,
                        mergeComponent.getContainedOperations());
                DeploymentModelMerger.mergeDeploymentStorages(component.getAssemblyComponent(), component,
                        mergeComponent.getContainedStorages());
            }
        }
    }

    private static void mergeDeploymentOperations(final AssemblyComponent assemblyComponent,
            final DeployedComponent component, final EMap<String, DeployedOperation> containedOperations) {
        for (final Entry<String, DeployedOperation> mergeOperation : containedOperations) {
            if (!component.getContainedOperations().containsKey(mergeOperation.getKey())) {
                component.getContainedOperations().put(mergeOperation.getKey(),
                        DeploymentModelCloneUtils.duplicate(assemblyComponent, mergeOperation.getValue()));
            }
        }
    }

    private static void mergeDeploymentStorages(final AssemblyComponent assemblyComponent,
            final DeployedComponent component, final EMap<String, DeployedStorage> containedStorages) {
        for (final Entry<String, DeployedStorage> mergeStorage : containedStorages) {
            if (!component.getContainedStorages().containsKey(mergeStorage.getKey())) {
                component.getContainedStorages().put(mergeStorage.getKey(),
                        DeploymentModelCloneUtils.duplicate(assemblyComponent, mergeStorage.getValue()));
            }
        }
    }
}
