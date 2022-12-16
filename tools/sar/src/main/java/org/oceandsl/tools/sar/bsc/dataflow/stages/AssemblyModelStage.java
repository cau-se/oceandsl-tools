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

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.source.SourceModel;
import kieker.model.analysismodel.type.TypeModel;

import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransfer;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

/**
 * Stage to define an assembly model according to bachelor thesis ss2022
 *
 * @author Yannick Illmann
 * @since 1.1
 */
public class AssemblyModelStage extends AbstractDataflowAssemblerStage<DataTransfer, DataTransfer> {

    private final TypeModel typeModel;
    private final AssemblyModel assemblyModel;

    public AssemblyModelStage(final TypeModel typeModel, final AssemblyModel assemblyModel,
            final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.typeModel = typeModel;
        this.assemblyModel = assemblyModel;
    }

    @SuppressWarnings("unused")
    @Override
    protected void execute(final DataTransfer dataTransferObject) throws Exception {

        AssemblyComponent assemblyComponent = this.assemblyComponentSetUp(dataTransferObject);
        final AssemblyOperation assemblyOperation = this.addOperation(assemblyComponent, dataTransferObject);

        if (dataTransferObject.callsCommon()) {
            // store common block independent of component containing functions
            assemblyComponent = this.createCommonComponent();
            final AssemblyStorage assemblyStorage = this.addStorage(assemblyComponent, dataTransferObject);
        } else {
            // if no storage is referenced, add target operation to target component
            final AssemblyComponent targetComponent = this.targetComponentAndOperationSetUp(dataTransferObject);
        }
        this.outputPort.send(dataTransferObject);
    }

    /*
     * SETUP
     */
    /**
     * This function retrieves a stored or new created AssemblyComponent. Depending on the given
     * identifier in the transfer-object a subroutine is called to set up a new AssemblyComponent
     * instance.
     *
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return component, stored for the given identifier string
     */
    @SuppressWarnings("unused")
    private AssemblyComponent assemblyComponentSetUp(final DataTransfer dataTransferObject) {
        AssemblyComponent assemblyComponent = this.assemblyModel.getComponents().get(dataTransferObject.getComponent());
        if (assemblyComponent == null) {
            assemblyComponent = this.createAssemblyComponent(dataTransferObject);
        }
        final AssemblyComponent packageAssemblyComponent = this.addComponent(assemblyComponent, dataTransferObject);
        return assemblyComponent;
    }

    /**
     * This function is used to create the matching target component of a given dataflow step. It
     * will use the 'createAssemblyComponent' method to store the new component in the assembly
     * model. Therefore, it creates a new transfer object only used in this method.
     *
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return component created and stored in the assembly model
     */
    @SuppressWarnings("unused")
    private AssemblyComponent targetComponentAndOperationSetUp(final DataTransfer dataTransferObject) {

        final DataTransfer tempTargetDataTransferObject = new DataTransfer();
        tempTargetDataTransferObject.setComponent(dataTransferObject.getTargetComponent());
        tempTargetDataTransferObject.setSourceIdent(dataTransferObject.getTargetIdent());
        tempTargetDataTransferObject.setSourcePackage(dataTransferObject.getTargetPackage());

        final AssemblyComponent targetComponentType = this.assemblyComponentSetUp(tempTargetDataTransferObject);
        final AssemblyOperation operationType = this.addOperation(targetComponentType, tempTargetDataTransferObject);
        this.addObjectToSource(targetComponentType);
        return targetComponentType;
    }

    /*
     * ADDING
     */

    /**
     * This function adds a component to its referenced package component.
     *
     * @param containedAssemblyComponent
     *            component to add to package
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return the package component containing the provided file component
     */
    private AssemblyComponent addComponent(final AssemblyComponent containedAssemblyComponent,
            final DataTransfer dataTransferObject) {
        AssemblyComponent packageAssemblyComponent = this.assemblyModel.getComponents()
                .get(dataTransferObject.getSourcePackage());
        if (packageAssemblyComponent == null) {
            packageAssemblyComponent = this.createPackageAssemblyComponent(dataTransferObject);
        }
        packageAssemblyComponent.getContainedComponents().add(containedAssemblyComponent);
        this.addObjectToSource(packageAssemblyComponent);
        return packageAssemblyComponent;
    }

    /**
     * This function adds an AssemblyOperation to a given component.
     *
     * @param assemblyComponent
     *            the operation should be stored in
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return the added operation. Useful for DEBUG Reasons
     */
    private AssemblyOperation addOperation(final AssemblyComponent assemblyComponent,
            final DataTransfer dataTransferObject) {
        AssemblyOperation operationType = assemblyComponent.getOperations().get(dataTransferObject.getSourceIdent());
        if (operationType == null) {
            operationType = this.createOperation(assemblyComponent, dataTransferObject.getSourceIdent());
            assemblyComponent.getOperations().put(dataTransferObject.getSourceIdent(), operationType);
        }
        return operationType;
    }

    /**
     * This function adds an AssemblyStorage to a given component.
     *
     * @param assemblyComponent
     *            the storage should be stored in
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return the added operation. Useful for DEBUG Reasons
     */
    private AssemblyStorage addStorage(final AssemblyComponent assemblyComponent,
            final DataTransfer dataTransferObject) {
        AssemblyStorage assemblyStorage = assemblyComponent.getStorages().get(dataTransferObject.getTargetIdent());
        if (assemblyStorage == null) {
            assemblyStorage = this.createAssemblyStorage(assemblyComponent, dataTransferObject.getTargetIdent());
            assemblyComponent.getStorages().put(dataTransferObject.getTargetIdent(), assemblyStorage);
            this.assemblyModel.getComponents().put(assemblyComponent.getSignature(), assemblyComponent);

            if (this.logger.isInfoEnabled()) {
                this.logger.info("Placing Storage with name: " + assemblyStorage.getStorageType().getName());
            }
            this.addObjectToSource(assemblyStorage);
        }

        return assemblyStorage;
    }

    /*
     * CREATING
     */
    /**
     * This function is used to create a new file AssemblyComponentObject and store it in the given
     * assembly model.
     *
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return file component created and stored in the assembly model
     */
    private AssemblyComponent createAssemblyComponent(final DataTransfer dataTransferObject) {
        final AssemblyComponent newAssemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        newAssemblyComponent
                .setComponentType(this.typeModel.getComponentTypes().get(dataTransferObject.getComponent()));
        newAssemblyComponent.setSignature(dataTransferObject.getComponent());
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Placing AssemblyComponent with name: " + dataTransferObject.getComponent());
        }
        this.assemblyModel.getComponents().put(dataTransferObject.getComponent(), newAssemblyComponent);
        this.addObjectToSource(newAssemblyComponent);
        return newAssemblyComponent;
    }

    /**
     * This function retrieves a stored or new created AssemblyComponent, storing all
     * storages/common blocks.
     *
     * @return component containing possible storages/common blocks.
     */
    private AssemblyComponent createCommonComponent() {
        final String commonIdent = "COMMON-Component";
        AssemblyComponent assemblyComponent = this.assemblyModel.getComponents().get(commonIdent);
        if (assemblyComponent == null) {
            assemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
            assemblyComponent.setComponentType(this.typeModel.getComponentTypes().get(commonIdent));
            assemblyComponent.setSignature(commonIdent);
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Placing AssemblyComponent with name: " + commonIdent);
            }
            this.assemblyModel.getComponents().put(commonIdent, assemblyComponent);
            this.addObjectToSource(assemblyComponent);
        }
        return assemblyComponent;
    }

    /**
     * This function is used to create a new package AssemblyComponentObject and store it in the
     * given assembly model.
     *
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return package component created and stored in the assembly model
     */
    private AssemblyComponent createPackageAssemblyComponent(final DataTransfer dataTransferObject) {
        final AssemblyComponent packageAssemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        packageAssemblyComponent.setSignature(dataTransferObject.getSourcePackage());
        packageAssemblyComponent
                .setComponentType(this.typeModel.getComponentTypes().get(dataTransferObject.getSourcePackage()));

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Placing Package-AssemblyComponent with name: " + packageAssemblyComponent.getSignature());
        }
        this.assemblyModel.getComponents().put(packageAssemblyComponent.getSignature(), packageAssemblyComponent);
        this.addObjectToSource(packageAssemblyComponent);
        return packageAssemblyComponent;
    }

    /**
     * This function creates an AssemblyOperationObject and stores it in the given source
     *
     * @param assemblyComponent
     *            component where the new operation is stored in
     * @param operationIdent
     *            string identifier for operation creation
     * @return created operation
     */
    private AssemblyOperation createOperation(final AssemblyComponent assemblyComponent, final String operationIdent) {
        final AssemblyOperation assemblyOperation = AssemblyFactory.eINSTANCE.createAssemblyOperation();
        assemblyOperation
                .setOperationType(assemblyComponent.getComponentType().getProvidedOperations().get(operationIdent));

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Placing AssemblyOperation with name: " + operationIdent);
        }
        this.addObjectToSource(assemblyOperation);
        return assemblyOperation;
    }

    /**
     * This function creates an AssemblyStorageObject and returns it
     *
     * @param assemblyComponent
     *            component where the new storage is stored in
     * @param storageIdent
     *            string identifier for storage creation
     * @return created storage
     */
    private AssemblyStorage createAssemblyStorage(final AssemblyComponent assemblyComponent,
            final String storageIdent) {
        final AssemblyStorage assemblyStorage = AssemblyFactory.eINSTANCE.createAssemblyStorage();
        assemblyStorage.setStorageType(assemblyComponent.getComponentType().getProvidedStorages().get(storageIdent));
        return assemblyStorage;
    }
}
