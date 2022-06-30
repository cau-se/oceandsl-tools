package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.deployment.*;
import kieker.model.analysismodel.sources.SourceModel;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

public class DeploymentModelStage extends AbstractDataflowAssemblerStage<DataTransferObject,DataTransferObject> {

    private final AssemblyModel assemblyModel;
    private final DeploymentModel deploymentModel;

    public DeploymentModelStage(final AssemblyModel assemblyModel,
                                                 final DeploymentModel deploymentModel, final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.assemblyModel = assemblyModel;
        this.deploymentModel = deploymentModel;
    }

    @Override
    protected void execute(DataTransferObject dataTransferObject) throws Exception {
        DeployedComponent deployedComponent = deployedComponentSetUp(dataTransferObject);
        assert deployedComponent != null;
        DeployedOperation deployedOperation = addOperation(deployedComponent, dataTransferObject);


    }

    private DeployedComponent deployedComponentSetUp(DataTransferObject dataTransferObject) {
        final DeploymentContext deployedContext = this.deploymentModel.getContexts().get(0).getValue();
        if (deployedContext == null) {
            this.logger.error("Internal error: Data must contain at least one deployment context.");
            return null;
        } else {
            DeployedComponent deployedComponent = deployedContext.getComponents().get(dataTransferObject.getComponent());
            if (deployedComponent == null) {
                deployedComponent = createDeployedComponent(deployedContext, dataTransferObject);
            }
            return deployedComponent;
        }
    }

    private DeployedComponent createDeployedComponent(DeploymentContext deploymentContext, DataTransferObject dataTransferObject){
        final DeployedComponent newDeployedComponent= DeploymentFactory.eINSTANCE.createDeployedComponent();

        newDeployedComponent.setSignature(dataTransferObject.getComponent());
        newDeployedComponent.setAssemblyComponent(this.assemblyModel.getComponents().get(dataTransferObject.getComponent()));
        deploymentContext.getComponents().put(dataTransferObject.getComponent(), newDeployedComponent);

        logger.info("Placing DeployedComponent with name: " + dataTransferObject.getComponent());
        this.addObjectToSource(newDeployedComponent);
        return newDeployedComponent;
    }
    private DeployedOperation addOperation(DeployedComponent deployedComponent, DataTransferObject dataTransferObject){
        DeployedOperation deployedOperation = deployedComponent.getOperations().get(dataTransferObject.getSourceIdent());
        if(deployedOperation == null){
            deployedOperation = createOperation(deployedComponent, dataTransferObject);
            deployedComponent.getOperations().put(dataTransferObject.getSourceIdent(), deployedOperation);
        }
        return deployedOperation;
    }

    private DeployedOperation createOperation(DeployedComponent deployedComponent, DataTransferObject dataTransferObject) {
        final AssemblyComponent assemblyComponent = this.assemblyModel.getComponents().get(dataTransferObject.getComponent());
        final AssemblyOperation assemblyOperation = assemblyComponent.getOperations().get(assemblyComponent.getOperations().get(dataTransferObject.getSourceIdent()));

        final DeployedOperation deployedOperation = DeploymentFactory.eINSTANCE.createDeployedOperation();
        deployedOperation.setAssemblyOperation(assemblyOperation);
        logger.info("Placing DeployedOperation with name: " + dataTransferObject.getSourceIdent());
        this.addObjectToSource(assemblyOperation);
        return deployedOperation;
    }

}
