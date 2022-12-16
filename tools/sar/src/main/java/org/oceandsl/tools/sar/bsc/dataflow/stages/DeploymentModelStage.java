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

import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.source.SourceModel;

import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransfer;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

/**
 * Stage to define a deployment model according to bachelor thesis ss2022
 *
 * @author Yannick Illmann
 * @since 1.1
 */
public class DeploymentModelStage extends AbstractDataflowAssemblerStage<DataTransfer, DataTransfer> {

    private final AssemblyModel assemblyModel;
    private final DeploymentModel deploymentModel;

    public DeploymentModelStage(final AssemblyModel assemblyModel, final DeploymentModel deploymentModel,
            final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.assemblyModel = assemblyModel;
        this.deploymentModel = deploymentModel;
    }

    @SuppressWarnings("unused")
    @Override
    protected void execute(final DataTransfer dataTransferObject) throws Exception {

        DeployedComponent deployedComponent = this.deployedComponentSetUp(dataTransferObject);
        assert deployedComponent != null;
        final DeployedOperation deployedOperation = this.addOperation(deployedComponent, dataTransferObject);

        if (dataTransferObject.callsCommon()) {
            deployedComponent = this.createCommonComponent(); // store common block independent of
            // dataflow component
            assert deployedComponent != null;
            final DeployedStorage deployedStorage = this.addStorage(deployedComponent, dataTransferObject);
        } else {
            final DeployedComponent targetComponent = this.targetComponentAndOperationSetUp(dataTransferObject);

        }
        this.outputPort.send(dataTransferObject);
    }

    /*
     * SETUP
     */

    /**
     * This function retrieves a stored or new created DeployedComponent. Depending on the given
     * identifier in the transfer-object a subroutine is called to set up a new DeployedComponent
     * instance.
     *
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return component, stored for the given identifier string
     */
    @SuppressWarnings("unused")
    private DeployedComponent deployedComponentSetUp(final DataTransfer dataTransferObject) {
        final DeploymentContext deployedContext = this.deploymentModel.getContexts().get(0).getValue();
        if (deployedContext == null) {
            this.logger.error("Internal error: Data must contain at least one deployment context.");
            return null;
        } else {
            DeployedComponent deployedComponent = deployedContext.getComponents()
                    .get(dataTransferObject.getComponent());
            if (deployedComponent == null) {
                deployedComponent = this.createDeployedComponent(deployedContext, dataTransferObject);
            }
            final DeployedComponent packageDeployedComponent = this.addComponent(deployedContext, deployedComponent,
                    dataTransferObject);
            return deployedComponent;
        }
    }

    /**
     * This function is used to create the matching target component of a given dataflow step. It
     * will use the 'createDeployedComponent' method to store the new component in the deployment
     * model. Therefor it creates a new transfer object only used in this method.
     *
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return component created and stored in the deployment model
     */
    @SuppressWarnings("unused")
    private DeployedComponent targetComponentAndOperationSetUp(final DataTransfer dataTransferObject) {

        final DataTransfer tempTargetDataTransferObject = new DataTransfer();
        tempTargetDataTransferObject.setComponent(dataTransferObject.getTargetComponent());
        tempTargetDataTransferObject.setSourceIdent(dataTransferObject.getTargetIdent());
        tempTargetDataTransferObject.setSourcePackage(dataTransferObject.getTargetPackage());

        final DeployedComponent targetComponentType = this.deployedComponentSetUp(tempTargetDataTransferObject);
        assert targetComponentType != null;
        final DeployedOperation operationType = this.addOperation(targetComponentType, tempTargetDataTransferObject);
        this.addObjectToSource(targetComponentType);
        return targetComponentType;
    }

    /*
     * ADDING
     */

    /**
     * This function adds a component to its referenced package component.
     *
     * @param deploymentContext
     *            context the deploymentComponents are stored in
     * @param containedDeployedComponent
     *            component to add to package
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return the package component containing the provided file component
     */
    private DeployedComponent addComponent(final DeploymentContext deploymentContext,
            final DeployedComponent containedDeployedComponent, final DataTransfer dataTransferObject) {
        DeployedComponent packageDeploymentComponent = deploymentContext.getComponents()
                .get(dataTransferObject.getSourcePackage());
        if (packageDeploymentComponent == null) {
            packageDeploymentComponent = this.createPackageDeploymentComponent(deploymentContext, dataTransferObject);
        }
        packageDeploymentComponent.getContainedComponents().add(containedDeployedComponent);
        this.addObjectToSource(packageDeploymentComponent);
        return packageDeploymentComponent;
    }

    /**
     * This function adds an AssemblyOperation to a given component.
     *
     * @param deployedComponent
     *            the operation should be stored in
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return the added operation. Useful for DEBUG Reasons
     */
    private DeployedOperation addOperation(final DeployedComponent deployedComponent,
            final DataTransfer dataTransferObject) {
        DeployedOperation deployedOperation = deployedComponent.getOperations()
                .get(dataTransferObject.getSourceIdent());
        if (deployedOperation == null) {
            deployedOperation = this.createOperation(deployedComponent, dataTransferObject.getSourceIdent());
            deployedComponent.getOperations().put(dataTransferObject.getSourceIdent(), deployedOperation);
        }
        return deployedOperation;
    }

    /**
     * This function adds an DeployedStorage to a given component.
     *
     * @param deployedComponent
     *            the storage should be stored in
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return the added operation. Useful for DEBUG Reasons
     */
    private DeployedStorage addStorage(final DeployedComponent deployedComponent,
            final DataTransfer dataTransferObject) {
        DeployedStorage deployedStorage = deployedComponent.getStorages().get(dataTransferObject.getTargetIdent());
        if (deployedStorage == null) {
            deployedStorage = this.createStorage(deployedComponent, dataTransferObject.getTargetIdent());
            deployedComponent.getStorages().put(dataTransferObject.getTargetIdent(), deployedStorage);
            this.deploymentModel.getContexts().get(0).getValue().getComponents().put(deployedComponent.getSignature(),
                    deployedComponent);

            if (this.logger.isInfoEnabled()) {
                this.logger.info("Placing Storage with name: "
                        + deployedStorage.getAssemblyStorage().getStorageType().getName());
            }
            this.addObjectToSource(deployedStorage);
        }
        return deployedStorage;
    }

    /*
     * CREATING
     */

    /**
     * This function is used to create a new file DeployedComponentObject and store it in the given
     * deployment model.
     *
     * @param deploymentContext
     *            context the deploymentComponents are stored in
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return file component created and stored in the deployment model
     */
    private DeployedComponent createDeployedComponent(final DeploymentContext deploymentContext,
            final DataTransfer dataTransferObject) {
        final DeployedComponent newDeployedComponent = DeploymentFactory.eINSTANCE.createDeployedComponent();

        newDeployedComponent.setSignature(dataTransferObject.getComponent());
        newDeployedComponent
                .setAssemblyComponent(this.assemblyModel.getComponents().get(dataTransferObject.getComponent()));
        deploymentContext.getComponents().put(dataTransferObject.getComponent(), newDeployedComponent);

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Placing DeployedComponent with name: " + dataTransferObject.getComponent());
        }
        this.addObjectToSource(newDeployedComponent);
        return newDeployedComponent;
    }

    /**
     * This function retrieves a stored or new created DeployedComponent, storing all
     * storages/common blocks.
     *
     * @return component containing possible storages/common blocks
     */
    private DeployedComponent createCommonComponent() {
        final String commonIdent = "COMMON-Component";
        final DeploymentContext deploymentContext = this.deploymentModel.getContexts().get(0).getValue();
        if (deploymentContext == null) {
            this.logger.error("Internal error: Data must contain at least one deployment context.");
            return null;
        } else {
            DeployedComponent deployedComponent = deploymentContext.getComponents().get(commonIdent);
            if (deployedComponent == null) {
                deployedComponent = DeploymentFactory.eINSTANCE.createDeployedComponent();

                deployedComponent.setSignature(commonIdent);
                deployedComponent.setAssemblyComponent(this.assemblyModel.getComponents().get(commonIdent));
                deploymentContext.getComponents().put(commonIdent, deployedComponent);

                if (this.logger.isInfoEnabled()) {
                    this.logger.info("Placing DeployedComponent with name: " + commonIdent);
                }
                this.addObjectToSource(deployedComponent);
            }
            return deployedComponent;
        }
    }

    /**
     * This function is used to create a new package DeployedComponentObject and store it in the
     * given deployment model.
     *
     * @param deploymentContext
     *            context the deploymentComponents are stored in
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     * @return package component created and stored in the deployment model
     */
    private DeployedComponent createPackageDeploymentComponent(final DeploymentContext deploymentContext,
            final DataTransfer dataTransferObject) {

        final DeployedComponent packageDeploymentComponent = DeploymentFactory.eINSTANCE.createDeployedComponent();
        packageDeploymentComponent.setSignature(dataTransferObject.getSourcePackage());
        packageDeploymentComponent
                .setAssemblyComponent(this.assemblyModel.getComponents().get(dataTransferObject.getSourcePackage()));
        if (this.logger.isInfoEnabled()) {
            this.logger
                    .info("Placing Package-AssemblyComponent with name: " + packageDeploymentComponent.getSignature());
        }
        deploymentContext.getComponents().put(packageDeploymentComponent.getSignature(), packageDeploymentComponent);
        this.addObjectToSource(packageDeploymentComponent);
        return packageDeploymentComponent;
    }

    /**
     * This function creates an DeployedOperationObject and stores it in the given source
     *
     * @param deployedComponent
     *            component where the new operation is stored in
     * @param operationIdent
     *            string identifier for operation creation
     * @return created operation
     */
    private DeployedOperation createOperation(final DeployedComponent deployedComponent, final String operationIdent) {
        final DeployedOperation deployedOperation = DeploymentFactory.eINSTANCE.createDeployedOperation();
        deployedOperation
                .setAssemblyOperation(deployedComponent.getAssemblyComponent().getOperations().get(operationIdent));

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Placing DeployedOperation with name: " + operationIdent);
        }
        this.addObjectToSource(deployedOperation);
        return deployedOperation;
    }

    /**
     *
     * @param deployedComponent
     *            component where the new storage is stored in
     * @param storageIdent
     *            string identifier for storage creation
     * @return created storage
     */
    private DeployedStorage createStorage(final DeployedComponent deployedComponent, final String storageIdent) {
        final DeployedStorage deployedStorage = DeploymentFactory.eINSTANCE.createDeployedStorage();
        deployedStorage.setAssemblyStorage(deployedComponent.getAssemblyComponent().getStorages().get(storageIdent));
        return deployedStorage;
    }
}
