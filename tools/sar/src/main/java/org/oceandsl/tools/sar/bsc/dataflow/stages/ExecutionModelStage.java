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
        if(dataTransferObject.callsOperation()){
            final DeployedOperation targetOperation = callerComponent.getOperations().get(dataTransferObject.getTargetIdent());
            final Tuple<DeployedOperation, DeployedOperation> key = ExecutionFactory.eINSTANCE.createTuple();
            key.setFirst(sourceOperation);
            key.setSecond(targetOperation);
            this.addObjectToSource(key);

            OperationAccess operationAccess = this.executionModel.getOperationAccess().get(key);
            if(operationAccess == null){
                operationAccess = ExecutionFactory.eINSTANCE.createOperationAccess();
                operationAccess.setSource(sourceOperation);
                operationAccess.setTarget(targetOperation);
                operationAccess.setRWAccess(this.convertDirection(dataTransferObject.getRw_action()));
                logger.info("Placing Dataflow Operation: " + dataTransferObject.getSourceIdent() + " to " + dataTransferObject.getTargetIdent());
                this.executionModel.getOperationAccess().put(key, operationAccess);
                this.addObjectToSource(operationAccess);
            } else {
                final EDirection newDirection = this.convertDirection(dataTransferObject.getRw_action());
                if (operationAccess.getRWAccess() == EDirection.READ
                        && (newDirection == EDirection.WRITE || newDirection == EDirection.BOTH)) {
                    operationAccess.setRWAccess(EDirection.BOTH);
                } else if (operationAccess.getRWAccess() == EDirection.WRITE
                        && (newDirection == EDirection.READ || newDirection == EDirection.BOTH)) {
                    operationAccess.setRWAccess(EDirection.BOTH);
                }
            }
        } else if(dataTransferObject.callsCommon()){
            final DeployedStorage accessedStorage = this.findStorage(context, dataTransferObject.getTargetIdent());
            final Tuple<DeployedOperation, DeployedStorage> key = ExecutionFactory.eINSTANCE.createTuple();
            key.setFirst(sourceOperation);
            key.setSecond(accessedStorage);
            this.addObjectToSource(key);

            AggregatedStorageAccess aggregatedStorageAccess = this.executionModel.getAggregatedStorageAccesses().get(key);
            if (aggregatedStorageAccess == null) {
                aggregatedStorageAccess = ExecutionFactory.eINSTANCE.createAggregatedStorageAccess();
                aggregatedStorageAccess.setCode(sourceOperation);
                aggregatedStorageAccess.setStorage(accessedStorage);
                aggregatedStorageAccess.setDirection(this.convertDirection(dataTransferObject.getRw_action()));
                logger.info("Placing Dataflow Common: " + dataTransferObject.getSourceIdent() + " to " + dataTransferObject.getTargetIdent());
                this.executionModel.getAggregatedStorageAccesses().put(key, aggregatedStorageAccess);
                this.addObjectToSource(aggregatedStorageAccess);
            } else {
                final EDirection newDirection = this.convertDirection(dataTransferObject.getRw_action());
                if (aggregatedStorageAccess.getDirection() == EDirection.READ
                        && (newDirection == EDirection.WRITE || newDirection == EDirection.BOTH)) {
                    aggregatedStorageAccess.setDirection(EDirection.BOTH);
                } else if (aggregatedStorageAccess.getDirection() == EDirection.WRITE
                        && (newDirection == EDirection.READ || newDirection == EDirection.BOTH)) {
                    aggregatedStorageAccess.setDirection(EDirection.BOTH);
                }
            }
        } else {
            logger.error("Failed to setup Dataflow, due to an earlier error.");
        }

        //distinguish between o2o and o2c Operation. Use created Storages and Operations in last stages to set relations
        this.executionModel.getOperationAccess();
    }

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
