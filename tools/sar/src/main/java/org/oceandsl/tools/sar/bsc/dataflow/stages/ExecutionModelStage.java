package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.deployment.*;
import kieker.model.analysismodel.execution.*;
import kieker.model.analysismodel.sources.SourceModel;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;


public class ExecutionModelStage extends AbstractDataflowAssemblerStage<DataTransferObject, DataTransferObject> {

    private final ExecutionModel executionModel;
    private final DeploymentModel deploymentModel;

    public ExecutionModelStage(final ExecutionModel executionModel, final DeploymentModel deploymentModel, final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.executionModel = executionModel;
        this.deploymentModel = deploymentModel;
    }

    @Override
    protected void execute(DataTransferObject dataTransferObject) throws Exception {

        final DeploymentContext context = this.deploymentModel.getContexts().get(0).getValue();
        final DeployedComponent callerComponent = context.getComponents().get(dataTransferObject.getComponent());
        final DeployedOperation sourceOperation = callerComponent.getOperations().get(dataTransferObject.getSourceIdent());

        //distinguish between o2o and o2c Operation. Use created Storages and Operations in last stages to set relations
        if(dataTransferObject.callsOperation()){
            addOperationDataflow(context, sourceOperation, dataTransferObject);
        } else if(dataTransferObject.callsCommon()){
            addStorageAccess(context, sourceOperation, dataTransferObject);
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
    private void addOperationDataflow(DeploymentContext context, DeployedOperation sourceOperation, DataTransferObject dataTransferObject){
        final DeployedComponent targetComponent = context.getComponents().get(dataTransferObject.getTargetComponent());
        //filter unkown components
        if(!targetComponent.getAssemblyComponent().getComponentType().getName().equals(".unknown")){

            final DeployedOperation targetOperation = targetComponent.getOperations().get(dataTransferObject.getTargetIdent());

            final Tuple<DeployedOperation, DeployedOperation> key = ExecutionFactory.eINSTANCE.createTuple();
            key.setFirst(sourceOperation);
            key.setSecond(targetOperation);
            this.addObjectToSource(key);

            OperationDataflow operationDataflow = this.executionModel.getOperationDataflow().get(key);
            if(operationDataflow == null){
                createOperationDataflow(key, sourceOperation, targetOperation, dataTransferObject);
            } // else fall not necessary, because an operation access from the same source to the same target can not change dataflow types due to function definition.
        }
    }

    /**
     *
     * @param context where deploymentComponents are stored in.
     * @param sourceOperation of the dataflow step, stored in Deployment model
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     */
    private void addStorageAccess(DeploymentContext context, DeployedOperation sourceOperation, DataTransferObject dataTransferObject){
        final DeployedStorage accessedStorage = this.findStorage(context, dataTransferObject.getTargetIdent());
        final Tuple<DeployedOperation, DeployedStorage> key = ExecutionFactory.eINSTANCE.createTuple();
        key.setFirst(sourceOperation);
        key.setSecond(accessedStorage);
        this.addObjectToSource(key);

       StorageDataflow aggregatedStorageAccess = this.executionModel.getStorageDataflow().get(key);
        if (aggregatedStorageAccess == null) {
            createStorageAccess(key,sourceOperation,accessedStorage,dataTransferObject);
        } else {
            checkAndChangeDirectionOfStorage(aggregatedStorageAccess, dataTransferObject);
        }
    }

    private void checkAndChangeDirectionOfStorage(StorageDataflow aggregatedStorageAccess, DataTransferObject dataTransferObject) {
        EDirection directionStorageDataflow = aggregatedStorageAccess.getDirection();
        EDirection directionDataTransferObject = this.convertDirection(dataTransferObject.getRw_action());

        if(directionStorageDataflow != directionDataTransferObject){
            aggregatedStorageAccess.setDirection(EDirection.BOTH);
            this.addObjectToSource(aggregatedStorageAccess);
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
    private void createOperationDataflow(Tuple<DeployedOperation, DeployedOperation> key, DeployedOperation sourceOperation, DeployedOperation targetOperation, DataTransferObject dataTransferObject){
        OperationDataflow operationDataflow = ExecutionFactory.eINSTANCE.createOperationDataflow();
        operationDataflow.setSource(sourceOperation);
        operationDataflow.setTarget(targetOperation);
        operationDataflow.setDirection(this.convertDirection(dataTransferObject.getRw_action()));
        logger.info("Placing Dataflow Operation: " + dataTransferObject.getSourceIdent() + " to " + dataTransferObject.getTargetIdent());
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
    private void createStorageAccess(Tuple<DeployedOperation, DeployedStorage> key, DeployedOperation sourceOperation, DeployedStorage accessedStorage, DataTransferObject dataTransferObject){
        StorageDataflow aggregatedStorageAccess = ExecutionFactory.eINSTANCE.createStorageDataflow();
        aggregatedStorageAccess.setCode(sourceOperation);
        aggregatedStorageAccess.setStorage(accessedStorage);
        aggregatedStorageAccess.setDirection(this.convertDirection(dataTransferObject.getRw_action()));
        logger.info("Placing Dataflow Common: " + dataTransferObject.getSourceIdent() + " to " + dataTransferObject.getTargetIdent());
        this.executionModel.getStorageDataflow().put(key, aggregatedStorageAccess);
        this.addObjectToSource(aggregatedStorageAccess);
    }

    /*
        OTHER
     */

    private DeployedStorage findStorage(final DeploymentContext context, final String name) {
        for (final DeployedComponent component : context.getComponents().values()) {
            final DeployedStorage storage = component.getStorages().get(name);
            if (storage != null) {
                return storage;
            }
        }
        this.logger.error("Internal error. Cannot find storage {}", name);
        return null;
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
