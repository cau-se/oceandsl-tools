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

import org.eclipse.emf.common.util.EMap;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.type.TypeModel;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public final class AssemblyModelMerger {

    private AssemblyModelMerger() {
    }

    /* default access */
    static void mergeAssemblyModel(final TypeModel typeModel, final AssemblyModel model, // NOPMD
            final AssemblyModel mergeModel) {
        // add additional component types if necessary
        for (final AssemblyComponent mergeComponent : mergeModel.getAssemblyComponents().values()) {
            if (!model.getAssemblyComponents().containsKey(mergeComponent.getSignature())) {
                model.getAssemblyComponents().put(mergeComponent.getSignature(),
                        AssemblyModelCloneUtils.duplicate(typeModel, mergeComponent));
            }
        }
        // now merge operations
        for (final AssemblyComponent component : model.getAssemblyComponents().values()) {
            final AssemblyComponent mergeComponent = mergeModel.getAssemblyComponents().get(component.getSignature());
            if (mergeComponent != null) {
                AssemblyModelMerger.mergeAssemblyOperations(component, mergeComponent.getAssemblyOperations());
                AssemblyModelMerger.mergeAssemblyStorages(component, mergeComponent.getAssemblyStorages());
            }
        }
    }

    private static void mergeAssemblyOperations(final AssemblyComponent component,
            final EMap<String, AssemblyOperation> assemblyOperations) {
        for (final AssemblyOperation mergeOperation : assemblyOperations.values()) {
            if (!component.getAssemblyOperations().containsKey(mergeOperation.getOperationType().getSignature())) {
                component.getAssemblyOperations().put(mergeOperation.getOperationType().getSignature(),
                        AssemblyModelCloneUtils.duplicate(component.getComponentType(), mergeOperation));
            }
        }
    }

    private static void mergeAssemblyStorages(final AssemblyComponent component,
            final EMap<String, AssemblyStorage> mergeAssemblyStorages) {
        for (final AssemblyStorage mergeStorage : mergeAssemblyStorages.values()) {
            if (!component.getAssemblyStorages().containsKey(mergeStorage.getStorageType().getName())) {
                component.getAssemblyStorages().put(mergeStorage.getStorageType().getName(),
                        AssemblyModelCloneUtils.duplicate(component.getComponentType(), mergeStorage));
            }
        }
    }
}
