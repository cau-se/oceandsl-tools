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

import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransfer;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.EDirection;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.OperationDataflow;
import kieker.model.analysismodel.execution.StorageDataflow;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.source.SourceModel;

/**
 * Stage to define an execution model according to bachelor thesis ss2022
 *
 * @author Yannick Illmann
 * @since 1.1
 */
public class ExecutionModelStage extends AbstractDataflowAssemblerStage<DataTransfer, DataTransfer> {

    private final ExecutionModel executionModel;
    private final DeploymentModel deploymentModel;

    public ExecutionModelStage(final ExecutionModel executionModel, final DeploymentModel deploymentModel,
            final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.executionModel = executionModel;
        this.deploymentModel = deploymentModel;
    }

    @Override
    protected void execute(final DataTransfer dataTransferObject) throws Exception {

        final DeploymentContext context = this.deploymentModel.getContexts().get(0).getValue();
        final DeployedComponent callerComponent = context.getComponents().get(dataTransferObject.getComponent());
        final DeployedOperation sourceOperation = callerComponent.getOperations()
                .get(dataTransferObject.getSourceIdent());

        // distinguish between o2o and o2c Operation. Use created Storages and Operations in last
        // stages to set relations
        if (dataTransferObject.callsOperation()) {
            this.addOperationDataflow(context, sourceOperation, dataTransferObject);
        } else if (dataTransferObject.callsCommon()) {
            this.addStorageDataflow(context, sourceOperation, dataTransferObject);
        } else {
            this.logger.error("Failed to setup Dataflow, due to an earlier error.");
        }

        this.logger.info("finished dataflow step");
    }

    /*
     * ADDING
     */

    /**
     * This function adds Dataflow in form of an operation access. It creates a new
     * OperationDataflowObject when a new dataflow is detected or updates
     *
     * @param context
     *            where deploymentComponents are stored in.
     * @param sourceOperation
     *            of the dataflow step, stored in Deployment model
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     */
    private void addOperationDataflow(final DeploymentContext context, final DeployedOperation sourceOperation,
            final DataTransfer dataTransferObject) {
        final DeployedComponent targetComponent = context.getComponents().get(dataTransferObject.getTargetComponent());
        if (!targetComponent.getAssemblyComponent().getComponentType().getName().equals(".unknown")) { // filter
                                                                                                       // unknown
                                                                                                       // components

            final DeployedOperation targetOperation = targetComponent.getOperations()
                    .get(dataTransferObject.getTargetIdent());

            final Tuple<DeployedOperation, DeployedOperation> key = ExecutionFactory.eINSTANCE.createTuple();
            key.setFirst(sourceOperation);
            key.setSecond(targetOperation);
            this.addObjectToSource(key);

            final OperationDataflow operationDataflow = this.executionModel.getOperationDataflows().get(key);
            if (operationDataflow == null) {
                this.createOperationDataflow(key, sourceOperation, targetOperation, dataTransferObject);
            }
            /*
             * Dataflow can not change from READ to BOTH with two separate iteration steps due to
             * the initialization of functions. OperationDataflow READ only possible when a function
             * has no arguments. This cannot change in future code lines
             */
        }
    }

    /**
     *
     * @param context
     *            where deploymentComponents are stored in.
     * @param sourceOperation
     *            of the dataflow step, stored in Deployment model
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     */
    private void addStorageDataflow(final DeploymentContext context, final DeployedOperation sourceOperation,
            final DataTransfer dataTransferObject) {
        final DeployedStorage accessedStorage = this.findStorage(context, dataTransferObject.getTargetIdent());
        final Tuple<DeployedOperation, DeployedStorage> key = ExecutionFactory.eINSTANCE.createTuple();
        key.setFirst(sourceOperation);
        key.setSecond(accessedStorage);
        this.addObjectToSource(key);

        final StorageDataflow storageDataflow = this.executionModel.getStorageDataflows().get(key);
        if (storageDataflow == null) {
            this.createStorageDataflow(key, sourceOperation, accessedStorage, dataTransferObject);
        } else {
            this.checkAndChangeDirectionOfStorageDataflow(storageDataflow, dataTransferObject);
        }
    }

    private void checkAndChangeDirectionOfStorageDataflow(final StorageDataflow storageDataflow,
            final DataTransfer dataTransferObject) {
        final EDirection directionStorageDataflow = storageDataflow.getDirection();
        final EDirection directionDataTransferObject = this.convertDirection(dataTransferObject.getDirection());

        if (directionStorageDataflow != directionDataTransferObject) {
            storageDataflow.setDirection(EDirection.BOTH);
            this.addObjectToSource(storageDataflow);
        }
    }

    /*
     * CREATE
     */

    /**
     *
     * @param key
     *            the created Access should be stored in.
     * @param sourceOperation
     *            of the dataflow step, stored in Deployment model
     * @param targetOperation
     *            of the dataflow step, stored in Deployment model
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     */
    private void createOperationDataflow(final Tuple<DeployedOperation, DeployedOperation> key,
            final DeployedOperation sourceOperation, final DeployedOperation targetOperation,
            final DataTransfer dataTransferObject) {
        final OperationDataflow operationDataflow = ExecutionFactory.eINSTANCE.createOperationDataflow();
        operationDataflow.setCaller(sourceOperation);
        operationDataflow.setCallee(targetOperation);
        operationDataflow.setDirection(this.convertDirection(dataTransferObject.getDirection()));
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Placing Dataflow Operation: " + dataTransferObject.getSourceIdent() + " to "
                    + dataTransferObject.getTargetIdent());
        }
        this.executionModel.getOperationDataflows().put(key, operationDataflow);
        this.addObjectToSource(operationDataflow);
    }

    /**
     *
     * @param key
     *            the created Access should be stored in.
     * @param sourceOperation
     *            of the dataflow step, stored in Deployment model
     * @param accessedStorage
     *            of the dataflow step, stored in Deployment model
     * @param dataTransferObject
     *            TransferObject containing all dataflow information in one step.
     */
    private void createStorageDataflow(final Tuple<DeployedOperation, DeployedStorage> key,
            final DeployedOperation sourceOperation, final DeployedStorage accessedStorage,
            final DataTransfer dataTransferObject) {
        final StorageDataflow storageDataflow = ExecutionFactory.eINSTANCE.createStorageDataflow();
        storageDataflow.setCode(sourceOperation);
        storageDataflow.setStorage(accessedStorage);
        storageDataflow.setDirection(this.convertDirection(dataTransferObject.getDirection()));
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Placing Dataflow Common: " + dataTransferObject.getSourceIdent() + " to "
                    + dataTransferObject.getTargetIdent());
        }
        this.executionModel.getStorageDataflows().put(key, storageDataflow);
        this.addObjectToSource(storageDataflow);
    }

    /*
     * OTHER
     */

    private DeployedStorage findStorage(final DeploymentContext context, final String name) {
        final String commonIdent = "COMMON-Component";
        final DeployedComponent deployedComponent = context.getComponents().get(commonIdent);
        final DeployedStorage deployedStorage = deployedComponent.getStorages().get(name);
        if (deployedStorage != null) {
            return deployedStorage;
        } else {
            this.logger.error("Internal error. Cannot find storage {}", name);
            return null;
        }
    }

    private EDirection convertDirection(final org.oceandsl.tools.sar.stages.dataflow.EDirection direction) {
        switch (direction) {
        case READ:
            return EDirection.READ;
        case WRITE:
            return EDirection.WRITE;
        case BOTH:
            return EDirection.BOTH;
        default:
            throw new InternalError("Unknown direction type found " + direction.name());
        }
    }
}
