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
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.StorageType;
import kieker.model.analysismodel.type.TypeModel;

/**
 * @author Reiner Jung
 *
 */
public class AssemblyModelDataflowAssemblerStage extends AbstractDataflowAssemblerStage<DataAccess, DataAccess> {

    private final TypeModel typeModel;
    private final AssemblyModel assemblyModel;
    private final Map<String, AssemblyStorage> assemblyStorageMap = new HashMap<>();
    private final Map<AssemblyStorage, List<AssemblyComponent>> assemblyStorageAccessMap = new HashMap<>();

    public AssemblyModelDataflowAssemblerStage(final TypeModel typeModel, final AssemblyModel assemblyModel,
            final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.typeModel = typeModel;
        this.assemblyModel = assemblyModel;
    }

    @Override
    protected void execute(final DataAccess element) throws Exception {
        final AssemblyOperation operation = this.findOperation(element);
        final AssemblyStorage assemblyStorage = this.findOrCreateDataStorage(element, operation.getAssemblyComponent());

        this.addObjectToSource(assemblyStorage);
        this.outputPort.send(element);
    }

    private AssemblyStorage findOrCreateDataStorage(final DataAccess element,
            final AssemblyComponent assemblyComponent) {
        AssemblyStorage assemblyStorage = this.assemblyStorageMap.get(element.getSharedData());
        if (assemblyStorage == null) {
            assemblyStorage = AssemblyFactory.eINSTANCE.createAssemblyStorage();
            assemblyStorage.setStorageType(this.findStorageType(element.getSharedData()));
            assemblyComponent.getAssemblyStorages().put(element.getSharedData(), assemblyStorage);
            this.assemblyStorageMap.put(element.getSharedData(), assemblyStorage);

            final List<AssemblyComponent> newList = new ArrayList<>();
            newList.add(assemblyComponent);
            this.assemblyStorageAccessMap.put(assemblyStorage, newList);
            this.addObjectToSource(assemblyStorage);
        } else {
            final List<AssemblyComponent> accessedByList = this.assemblyStorageAccessMap.get(assemblyStorage);
            if (!accessedByList.contains(assemblyComponent)) {
                if (accessedByList.size() == 1) {
                    this.moveStorageAccessToSeparateComponent(assemblyStorage, accessedByList.get(0));
                }
                accessedByList.add(assemblyComponent);
                this.assemblyStorageAccessMap.put(assemblyStorage, accessedByList);
            }
        }

        return assemblyStorage;
    }

    private void moveStorageAccessToSeparateComponent(final AssemblyStorage assemblyStorage,
            final AssemblyComponent assemblyComponent) {
        final String name = assemblyStorage.getStorageType().getName();
        assemblyComponent.getAssemblyStorages().removeKey(name);

        this.findOrCreateAssemblyComponent().getAssemblyStorages().put(name, assemblyStorage);
    }

    private AssemblyComponent findOrCreateAssemblyComponent() {
        final AssemblyComponent existingAssemblyComponent = this.assemblyModel.getAssemblyComponents()
                .get(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE);
        if (existingAssemblyComponent == null) {
            final AssemblyComponent newAssemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
            newAssemblyComponent
                    .setComponentType(this.findComponentType(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE));
            newAssemblyComponent.setSignature(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE);
            this.addObjectToSource(newAssemblyComponent);
            this.assemblyModel.getAssemblyComponents().put(newAssemblyComponent.getSignature(), newAssemblyComponent);
            return newAssemblyComponent;
        } else {
            return existingAssemblyComponent;
        }
    }

    private ComponentType findComponentType(final String componentSignature) {
        return this.typeModel.getComponentTypes().get(componentSignature);
    }

    private StorageType findStorageType(final String sharedData) {
        for (final ComponentType type : this.typeModel.getComponentTypes().values()) {
            final StorageType storageType = type.getProvidedStorages().get(sharedData);
            if (storageType != null) {
                return storageType;
            }
        }
        this.logger.error("Intternal error: Missing storage type for given data access element {}", sharedData);
        return null;
    }

    private AssemblyOperation findOperation(final DataAccess element) {
        AssemblyComponent assemblyComponent = this.assemblyModel.getAssemblyComponents().get(element.getModule());
        if (assemblyComponent == null) {
            assemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
            assemblyComponent.setComponentType(this.findComponentType(element.getModule()));
            assemblyComponent.setSignature(element.getModule());
            this.assemblyModel.getAssemblyComponents().put(element.getModule(), assemblyComponent);
            this.addObjectToSource(assemblyComponent);
        }
        AssemblyOperation operation = assemblyComponent.getAssemblyOperations().get(element.getOperation());
        if (operation == null) {
            operation = AssemblyFactory.eINSTANCE.createAssemblyOperation();
            operation.setOperationType(
                    assemblyComponent.getComponentType().getProvidedOperations().get(element.getOperation()));
            assemblyComponent.getAssemblyOperations().put(element.getOperation(), operation);
            this.addObjectToSource(operation);
        }
        return operation;
    }
}
