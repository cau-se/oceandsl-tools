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

import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.StorageType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;
import teetime.stage.basic.AbstractTransformation;

/**
 * Add data sorages to the model.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class TypeModelDataflowAssemblerStage extends AbstractTransformation<DataAccess, DataAccess> {

    static final String GLOBAL_PACKAGE = "global";

    private static final String UNKOWN_TYPE = "UNKNOWN";

    private final TypeModel typeModel;
    private final SourceModel sourceModel;
    private final String sourceLabel;
    private final Map<String, StorageType> storageTypeMap = new HashMap<>();
    private Map<StorageType, List<ComponentType>> storageAccessMap;

    public TypeModelDataflowAssemblerStage(final TypeModel typeModel, final SourceModel sourceModel,
            final String sourceLabel) {
        this.typeModel = typeModel;
        this.sourceModel = sourceModel;
        this.sourceLabel = sourceLabel;
    }

    @Override
    protected void execute(final DataAccess element) throws Exception {
        final OperationType operation = this.findOperation(element);
        final StorageType type = this.findOrCreateDataStorage(element, operation.getComponentType());

        this.addObjectToSource(type);
        this.outputPort.send(element);
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

        final ComponentType newComponentType = TypeFactory.eINSTANCE.createComponentType();
        newComponentType.setName(storageType.getName());
        newComponentType.setPackage(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE);
        newComponentType.setSignature(TypeModelDataflowAssemblerStage.GLOBAL_PACKAGE + "." + storageType.getName());
        newComponentType.getProvidedStorages().put(storageType.getName(), storageType);

        this.addObjectToSource(newComponentType);
        this.typeModel.getComponentTypes().put(newComponentType.getSignature(), newComponentType);
    }

    private OperationType findOperation(final DataAccess element) {
        for (final ComponentType componentType : this.typeModel.getComponentTypes().values()) {
            if (componentType.getName().equals(element.getModule())) {
                for (final OperationType operation : componentType.getProvidedOperations().values()) {
                    if (operation.getName().equals(element.getOperation())) {
                        return operation;
                    }
                }
            }
        }

        return null;
    }

}
