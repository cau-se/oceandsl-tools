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
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.StorageType;
import kieker.model.analysismodel.type.TypeModel;
import teetime.stage.basic.AbstractTransformation;

/**
 * @author Reiner Jung
 *
 */
public class AssemblyModelDataflowAssemblerStage extends AbstractTransformation<DataAccess, DataAccess> {

    private final TypeModel typeModel;
    private final AssemblyModel assemblyModel;
    private final SourceModel sourceModel;
    private final String sourceLabel;
    private final Map<String, AssemblyStorage> assemblyStorageMap = new HashMap<>();
    private Map<AssemblyStorage, List<AssemblyComponent>> assemblyStorageAccessMap;

    public AssemblyModelDataflowAssemblerStage(final TypeModel typeModel, final AssemblyModel assemblyModel,
            final SourceModel sourceModel, final String sourceLabel) {
        this.typeModel = typeModel;
        this.assemblyModel = assemblyModel;
        this.sourceModel = sourceModel;
        this.sourceLabel = sourceLabel;
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

        final AssemblyComponent newAssemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        newAssemblyComponent
                .setComponentType(this.findComponentType(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE + "." + name));
        newAssemblyComponent.setSignature(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE + "." + name);
        newAssemblyComponent.getAssemblyStorages().put(name, assemblyStorage);

        this.addObjectToSource(newAssemblyComponent);
        this.assemblyModel.getAssemblyComponents().put(newAssemblyComponent.getSignature(), newAssemblyComponent);
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
        final AssemblyComponent assemblyComponent = this.assemblyModel.getAssemblyComponents().get(element.getModule());
        return assemblyComponent.getAssemblyOperations().get(element.getOperation());
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
