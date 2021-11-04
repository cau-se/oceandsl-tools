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

import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.StorageType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;

/**
 * Add data sorages to the model.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class TypeModelDataflowAssemblerStage extends AbstractDataflowAssemblerStage<DataAccess, DataAccess> {

    static final String GLOBAL_PACKAGE = "global"; // NOPMD must be package global

    private static final String UNKOWN_TYPE = "UNKNOWN";

    private final TypeModel typeModel;
    private final Map<String, StorageType> storageTypeMap = new HashMap<>();
    private final Map<StorageType, List<ComponentType>> storageAccessMap = new HashMap<>();

    public TypeModelDataflowAssemblerStage(final TypeModel typeModel, final SourceModel sourceModel,
            final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.typeModel = typeModel;
    }

    @Override
    protected void execute(final DataAccess element) throws Exception {
        final OperationType operation = this.findOperation(element);
        final StorageType type = this.findOrCreateDataStorage(element, operation.getComponentType());

        this.addObjectToSource(type);
        this.outputPort.send(element);
    }

    private StorageType findOrCreateDataStorage(final DataAccess element, final ComponentType componentType) {
        StorageType type = this.storageTypeMap.get(element.getSharedData());
        if (type == null) {
            type = TypeFactory.eINSTANCE.createStorageType();
            type.setName(element.getSharedData());
            type.setType(TypeModelDataflowAssemblerStage.UNKOWN_TYPE);
            componentType.getProvidedStorages().put(type.getName(), type);
            this.storageTypeMap.put(type.getName(), type);

            final List<ComponentType> newList = new ArrayList<>();
            newList.add(componentType);
            this.storageAccessMap.put(type, newList);
            this.addObjectToSource(type);
        } else {
            final List<ComponentType> accessedByList = this.storageAccessMap.get(type);
            if (!accessedByList.contains(componentType)) {
                if (accessedByList.size() == 1) {
                    this.moveStorageAccessToSeparateComponent(type, accessedByList.get(0));
                }
                accessedByList.add(componentType);
                this.storageAccessMap.put(type, accessedByList);
            }
        }

        return type;
    }

    private void moveStorageAccessToSeparateComponent(final StorageType storageType,
            final ComponentType componentType) {
        componentType.getProvidedStorages().removeKey(storageType.getName());

        this.findOrCreateGlobalComponentType().getProvidedStorages().put(storageType.getName(), storageType);
    }

    private ComponentType findOrCreateGlobalComponentType() {
        final ComponentType componentType = this.typeModel.getComponentTypes()
                .get(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE);
        if (componentType != null) {
            return componentType;
        } else {
            final ComponentType newComponentType = TypeFactory.eINSTANCE.createComponentType();
            newComponentType.setName(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE);
            newComponentType.setPackage(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE);
            newComponentType.setSignature(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE);

            this.typeModel.getComponentTypes().put(newComponentType.getSignature(), newComponentType);
            this.addObjectToSource(newComponentType);

            return newComponentType;
        }
    }

    private OperationType findOperation(final DataAccess element) {
        ComponentType componentType = this.typeModel.getComponentTypes().get(element.getModule());
        if (componentType == null) {
            componentType = TypeFactory.eINSTANCE.createComponentType();
            componentType.setName(element.getModule());
            componentType.setSignature(element.getModule());
            componentType.setPackage(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE);
            this.addObjectToSource(componentType);

            this.typeModel.getComponentTypes().put(element.getModule(), componentType);
            final OperationType operationType = this.createOperation(element.getOperation());
            componentType.getProvidedOperations().put(element.getOperation(), operationType);

            return operationType;
        } else {
            for (final OperationType operation : componentType.getProvidedOperations().values()) {
                if (operation.getName().equals(element.getOperation())) {
                    return operation;
                }
            }

            final OperationType operationType = this.createOperation(element.getOperation());
            componentType.getProvidedOperations().put(element.getOperation(), operationType);

            return operationType;
        }
    }

    private OperationType createOperation(final String operation) {
        final OperationType operationType = TypeFactory.eINSTANCE.createOperationType();
        operationType.setName(operation);
        operationType.setReturnType("unknown");
        operationType.setSignature(operation);
        this.addObjectToSource(operationType);

        return operationType;
    }

}
