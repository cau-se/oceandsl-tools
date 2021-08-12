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
package org.oceandsl.tools.mop.operations;

import java.util.Map.Entry;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.StorageType;
import kieker.model.analysismodel.type.TypeModel;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public final class AssemblyModelCloneUtils {

    public static AssemblyComponent duplicate(final TypeModel typeModel, final AssemblyComponent component) {
        final AssemblyComponent newComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        newComponent.setSignature(component.getSignature());

        // TODO the following fails.
        newComponent.setComponentType(
                AssemblyModelCloneUtils.findComponentType(typeModel, component.getComponentType().getSignature()));

        for (final Entry<String, AssemblyOperation> operation : component.getAssemblyOperations()) {
            newComponent.getAssemblyOperations().put(operation.getKey(),
                    AssemblyModelCloneUtils.duplicate(newComponent.getComponentType(), operation.getValue()));
        }

        for (final Entry<String, AssemblyStorage> storage : component.getAssemblyStorages()) {
            newComponent.getAssemblyStorages().put(storage.getKey(),
                    AssemblyModelCloneUtils.duplicate(newComponent.getComponentType(), storage.getValue()));
        }

        return newComponent;
    }

    public static AssemblyOperation duplicate(final ComponentType type, final AssemblyOperation operation) {
        final AssemblyOperation newOperation = AssemblyFactory.eINSTANCE.createAssemblyOperation();
        newOperation.setOperationType(
                AssemblyModelCloneUtils.findOperationType(type, operation.getOperationType().getSignature()));

        return newOperation;
    }

    public static AssemblyStorage duplicate(final ComponentType type, final AssemblyStorage storage) {
        final AssemblyStorage newStorage = AssemblyFactory.eINSTANCE.createAssemblyStorage();
        newStorage.setStorageType(AssemblyModelCloneUtils.findStorageType(type, storage.getStorageType().getName()));

        return newStorage;
    }

    private static StorageType findStorageType(final ComponentType componentType, final String name) {
        return componentType.getProvidedStorages().get(name);
    }

    private static OperationType findOperationType(final ComponentType componentType, final String signature) {
        return componentType.getProvidedOperations().get(signature);
    }

    private static ComponentType findComponentType(final TypeModel typeModel, final String signature) {
        return typeModel.getComponentTypes().get(signature);
    }
}
