/***************************************************************************
 * Copyright (C) 2022 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.source.SourceModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.StorageType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;

import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransfer;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

/**
 * Stage to define a type model according to bachelor thesis ss2022
 *
 * @author Yannick Illmann
 * @since 1.1
 */
public class TypeModelStage extends AbstractDataflowAssemblerStage<DataTransfer, DataTransfer> {

    private static final String UNKNOWN_TYPE = "UNKNOWN";
    private final TypeModel typeModel;

    public TypeModelStage(final TypeModel typeModel, final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.typeModel = typeModel;
    }

    @SuppressWarnings("unused")
    @Override
    protected void execute(final DataTransfer dataTransferObject) throws Exception {

        ComponentType componentType = this.componentSetUp(dataTransferObject);
        final OperationType operationType = this.addOperation(componentType, dataTransferObject);

        if (dataTransferObject.callsCommon()) {
            // store common block independent of component containing functions
            componentType = this.createCommonComponent();
            dataTransferObject.setTargetComponent(componentType.getName());
            final StorageType storageType = this.addStorage(componentType, dataTransferObject);
        } else {
            // if no storage is referenced, add target operation to target component
            final ComponentType targetComponent = this.targetComponentAndOperationSetUp(dataTransferObject);
        }

        this.outputPort.send(dataTransferObject);
    }

    /*
     * SETUP
     */

    /**
     * This function retrieves a stored or new created ComponentType. Depending on the given
     * identifier in the transfer-object a subroutine is called to set up a new ComponentType
     * instance.
     *
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return component, stored for the given identifier string
     */
    @SuppressWarnings("unused")
    private ComponentType componentSetUp(final DataTransfer dataTransferObject) {
        ComponentType componentType = this.typeModel.getComponentTypes().get(dataTransferObject.getComponent());
        if (componentType == null) {
            componentType = this.createComponentType(dataTransferObject);
        }
        final ComponentType packageComponent = this.addComponent(componentType, dataTransferObject);
        return componentType;
    }

    /**
     * This function is used to create the matching target component of a given dataflow step. It
     * will use the 'createComponentType' method to store the new component in the type model.
     * Therefore, it creates a new transfer object only used in this method.
     *
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return target component created and stored in the type model
     */
    @SuppressWarnings("unused")
    private ComponentType targetComponentAndOperationSetUp(final DataTransfer dataTransferObject) {

        final DataTransfer tempTargetDataTransferObject = new DataTransfer();
        tempTargetDataTransferObject.setComponent(dataTransferObject.getTargetComponent());
        tempTargetDataTransferObject.setSourceIdent(dataTransferObject.getTargetIdent());
        tempTargetDataTransferObject.setSourcePackage(dataTransferObject.getTargetPackage());

        final ComponentType targetComponentType = this.componentSetUp(tempTargetDataTransferObject);
        final OperationType operationType = this.addOperation(targetComponentType, tempTargetDataTransferObject);
        this.addObjectToSource(targetComponentType);
        return targetComponentType;
    }

    /*
     * ADDING
     */
    /**
     * This function adds a component to its referenced package component.
     *
     * @param containedComponentType
     *            component to add to package
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return the package component containing the provided file component
     */
    private ComponentType addComponent(final ComponentType containedComponentType,
            final DataTransfer dataTransferObject) {
        ComponentType packageComponentType = this.typeModel.getComponentTypes()
                .get(dataTransferObject.getSourcePackage());
        if (packageComponentType == null) {
            packageComponentType = this.createPackageComponent(dataTransferObject);
        }
        packageComponentType.getContainedComponents().add(containedComponentType);
        this.addObjectToSource(packageComponentType);
        return packageComponentType;
    }

    /**
     * This function adds an OperationType to a given component.
     *
     * @param componentType
     *            the operation should be stored in
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return the added operation. Useful for DEBUG Reasons
     */
    private OperationType addOperation(final ComponentType componentType, final DataTransfer dataTransferObject) {
        OperationType operationType = componentType.getProvidedOperations().get(dataTransferObject.getSourceIdent());
        if (operationType == null) {
            operationType = this.createOperationType(dataTransferObject.getSourceIdent());
            componentType.getProvidedOperations().put(dataTransferObject.getSourceIdent(), operationType);
        }
        return operationType;
    }

    /**
     * This function adds an StorageType to a given component.
     *
     * @param componentType
     *            the storage should be stored in
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return the added storage. Useful for DEBUG Reasons
     */
    private StorageType addStorage(final ComponentType componentType, final DataTransfer dataTransferObject) {
        StorageType storageType = componentType.getProvidedStorages().get(dataTransferObject.getTargetIdent()); // common
                                                                                                                // Block
                                                                                                                // init
                                                                                                                // via
                                                                                                                // registration
                                                                                                                // of
                                                                                                                // referencing
        if (storageType == null) {
            storageType = this.createStorageType(dataTransferObject.getTargetIdent());
            componentType.getProvidedStorages().put(storageType.getName(), storageType);
            this.typeModel.getComponentTypes().put(componentType.getName(), componentType);

            if (this.logger.isInfoEnabled()) {
                this.logger.info("Placing Storage with name: " + storageType.getName());
            }
            this.addObjectToSource(storageType);
        }
        return storageType;
    }

    /*
     * CREATING
     */
    /**
     * This function retrieves a stored or new created ComponentType, storing all storages/common
     * blocks.
     *
     * @return component containing possible storages/common blocks.
     */
    private ComponentType createCommonComponent() {
        final String commonIdent = "COMMON-Component";
        ComponentType componentType = this.typeModel.getComponentTypes().get(commonIdent);
        if (componentType == null) {
            componentType = TypeFactory.eINSTANCE.createComponentType();
            componentType.setName(commonIdent);
            componentType.setSignature(commonIdent);
            componentType.setPackage("COMMON");

            if (this.logger.isInfoEnabled()) {
                this.logger.info("Placing Component with name: " + componentType.getName());
            }
            this.typeModel.getComponentTypes().put(componentType.getName(), componentType);
            this.addObjectToSource(componentType);
            return componentType;
        }
        return componentType;
    }

    /**
     * This function is used to create a new file ComponentTypeObject and store it in the given type
     * model.
     *
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return component created and stored in the type model
     */
    private ComponentType createComponentType(final DataTransfer dataTransferObject) {
        final ComponentType newComponentType = TypeFactory.eINSTANCE.createComponentType();
        newComponentType.setName(dataTransferObject.getComponent());
        newComponentType.setPackage(dataTransferObject.getSourcePackage());
        newComponentType.setSignature(dataTransferObject.getComponent());

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Placing Component with name: " + newComponentType.getName());
        }
        this.typeModel.getComponentTypes().put(dataTransferObject.getComponent(), newComponentType);
        this.addObjectToSource(newComponentType);
        return newComponentType;
    }

    /**
     * This function is used to create a new package ComponentTypeObject and store it in the given
     * type model.
     *
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return component created and stored in the type model
     */
    private ComponentType createPackageComponent(final DataTransfer dataTransferObject) {
        final ComponentType packageComponentType = TypeFactory.eINSTANCE.createComponentType();
        packageComponentType.setName(dataTransferObject.getSourcePackage());
        packageComponentType.setPackage(dataTransferObject.getSourcePackage());
        packageComponentType.setSignature(dataTransferObject.getSourcePackage());
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Placing Package-Component with name: " + packageComponentType.getName());
        }
        this.typeModel.getComponentTypes().put(packageComponentType.getName(), packageComponentType);
        this.addObjectToSource(packageComponentType);

        return packageComponentType;
    }

    /**
     * This function creates an OperationTypeObject and stores it in the given source
     *
     * @param operationIdent
     *            name of the OperationTypeObject
     * @return created operation
     */
    private OperationType createOperationType(final String operationIdent) {
        final OperationType operationType = TypeFactory.eINSTANCE.createOperationType();
        operationType.setName(operationIdent);
        operationType.setReturnType(TypeModelStage.UNKNOWN_TYPE);
        operationType.setSignature(operationIdent);

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Placing Operation with name: " + operationIdent);
        }
        this.addObjectToSource(operationType);

        return operationType;
    }

    /**
     * This function creates an StorageTypeObject and returns it
     *
     * @param storageIdent
     *            name of the StorageTypeObject.
     * @return created storage
     */
    private StorageType createStorageType(final String storageIdent) {
        final StorageType storageType = TypeFactory.eINSTANCE.createStorageType();
        storageType.setName(storageIdent);
        storageType.setType(TypeModelStage.UNKNOWN_TYPE);

        return storageType;
    }
}
