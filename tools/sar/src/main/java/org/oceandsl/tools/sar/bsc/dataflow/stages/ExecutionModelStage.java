package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.ExecutionModel;
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
        this.executionModel.getOperationAccess();
    }
}
