package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.deployment.*;
import kieker.model.analysismodel.execution.*;
import kieker.model.analysismodel.sources.SourceModel;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;


/**
 * Stage to define an execution model according to bachelor thesis ss2022
 *
 * @author Yannick Illmann
 * @since 1.1
 */public class ExecutionModelStage extends AbstractDataflowAssemblerStage<DataTransferObject, DataTransferObject> {

    private final ExecutionModel executionModel;
    private final DeploymentModel deploymentModel;

    public ExecutionModelStage(final ExecutionModel executionModel, final DeploymentModel deploymentModel, final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.executionModel = executionModel;
        this.deploymentModel = deploymentModel;
    }

    @Override
    protected void execute(final DataTransferObject dataTransferObject) throws Exception {

        final DeploymentContext context = this.deploymentModel.getContexts().get(0).getValue();
        final DeployedComponent callerComponent = context.getComponents().get(dataTransferObject.getComponent());
        final DeployedOperation sourceOperation = callerComponent.getOperations().get(dataTransferObject.getSourceIdent());

        //distinguish between o2o and o2c Operation. Use created Storages and Operations in last stages to set relations
        if(dataTransferObject.callsOperation()){
            addOperationDataflow(context, sourceOperation, dataTransferObject);
        } else if(dataTransferObject.callsCommon()){
            addStorageDataflow(context, sourceOperation, dataTransferObject);
        } else {
            logger.error("Failed to setup Dataflow, due to an earlier error.");
        }

        logger.info("finished dataflow step");
    }

    /*
        ADDING
     */

    /**
     * This function adds Dataflow in form of an operation access. It creates a new OperationDataflowObject when a new
     * dataflow is detected or updates
     * @param context where deploymentComponents are stored in.
     * @param sourceOperation of the dataflow step, stored in Deployment model
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     */
    private void addOperationDataflow(final DeploymentContext context,final DeployedOperation sourceOperation,final DataTransferObject dataTransferObject){
        final DeployedComponent targetComponent = context.getComponents().get(dataTransferObject.getTargetComponent());
        if(!targetComponent.getAssemblyComponent().getComponentType().getName().equals(".unknown")){ //filter unknown components

            final DeployedOperation targetOperation = targetComponent.getOperations().get(dataTransferObject.getTargetIdent());

            final Tuple<DeployedOperation, DeployedOperation> key = ExecutionFactory.eINSTANCE.createTuple();
            key.setFirst(sourceOperation);
            key.setSecond(targetOperation);
            this.addObjectToSource(key);

            final OperationDataflow operationDataflow = this.executionModel.getOperationDataflow().get(key);
            if(operationDataflow == null){
                createOperationDataflow(key, sourceOperation, targetOperation, dataTransferObject);
            }
            /*
             * Dataflow can not change from READ to BOTH with two separate iteration steps due to the initialization of functions.
             * OperationDataflow READ only possible when a function has no arguments. This cannot change in future code lines
             */
        }
    }

    /**
     *
     * @param context where deploymentComponents are stored in.
     * @param sourceOperation of the dataflow step, stored in Deployment model
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     */
    private void addStorageDataflow(final DeploymentContext context,final DeployedOperation sourceOperation,final DataTransferObject dataTransferObject){
        final DeployedStorage accessedStorage = this.findStorage(context, dataTransferObject.getTargetIdent());
        final Tuple<DeployedOperation, DeployedStorage> key = ExecutionFactory.eINSTANCE.createTuple();
        key.setFirst(sourceOperation);
        key.setSecond(accessedStorage);
        this.addObjectToSource(key);

        final StorageDataflow storageDataflow = this.executionModel.getStorageDataflow().get(key);
        if (storageDataflow == null) {
            createStorageDataflow(key,sourceOperation,accessedStorage,dataTransferObject);
        } else {
            checkAndChangeDirectionOfStorageDataflow(storageDataflow, dataTransferObject);
        }
    }

    private void checkAndChangeDirectionOfStorageDataflow(final StorageDataflow storageDataflow,final DataTransferObject dataTransferObject) {
        final EDirection directionStorageDataflow = storageDataflow.getDirection();
        final EDirection directionDataTransferObject = this.convertDirection(dataTransferObject.getDirection());

        if(directionStorageDataflow != directionDataTransferObject){
            storageDataflow.setDirection(EDirection.BOTH);
            this.addObjectToSource(storageDataflow);
        }
    }

    /*
        CREATE
     */

    /**
     *
     * @param key the created Access should be stored in.
     * @param sourceOperation of the dataflow step, stored in Deployment model
     * @param targetOperation of the dataflow step, stored in Deployment model
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     */
    private void createOperationDataflow(final Tuple<DeployedOperation, DeployedOperation> key,final DeployedOperation sourceOperation,final DeployedOperation targetOperation,final DataTransferObject dataTransferObject){
        final OperationDataflow operationDataflow = ExecutionFactory.eINSTANCE.createOperationDataflow();
        operationDataflow.setSource(sourceOperation);
        operationDataflow.setTarget(targetOperation);
        operationDataflow.setDirection(this.convertDirection(dataTransferObject.getDirection()));
        if(logger.isInfoEnabled()){
            logger.info("Placing Dataflow Operation: " + dataTransferObject.getSourceIdent() + " to " + dataTransferObject.getTargetIdent());
        }
        this.executionModel.getOperationDataflow().put(key, operationDataflow);
        this.addObjectToSource(operationDataflow);
    }

    /**
     *
     * @param key the created Access should be stored in.
     * @param sourceOperation of the dataflow step, stored in Deployment model
     * @param accessedStorage of the dataflow step, stored in Deployment model
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     */
    private void createStorageDataflow(final Tuple<DeployedOperation, DeployedStorage> key,final DeployedOperation sourceOperation,final DeployedStorage accessedStorage,final DataTransferObject dataTransferObject){
        final StorageDataflow storageDataflow = ExecutionFactory.eINSTANCE.createStorageDataflow();
        storageDataflow.setCode(sourceOperation);
        storageDataflow.setStorage(accessedStorage);
        storageDataflow.setDirection(this.convertDirection(dataTransferObject.getDirection()));
        if(logger.isInfoEnabled()){
            logger.info("Placing Dataflow Common: " + dataTransferObject.getSourceIdent() + " to " + dataTransferObject.getTargetIdent());
        }
        this.executionModel.getStorageDataflow().put(key, storageDataflow);
        this.addObjectToSource(storageDataflow);
    }

    /*
        OTHER
     */

    private DeployedStorage findStorage(final DeploymentContext context, final String name) {
        String commonIdent = "COMMON-Component";
        DeployedComponent deployedComponent = context.getComponents().get(commonIdent);
        DeployedStorage deployedStorage = deployedComponent.getStorages().get(name);
        if(deployedStorage!= null){
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
