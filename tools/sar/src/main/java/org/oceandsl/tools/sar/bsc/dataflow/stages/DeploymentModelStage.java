package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.deployment.*;
import kieker.model.analysismodel.sources.SourceModel;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeploymentModelStage extends AbstractDataflowAssemblerStage<DataTransferObject,DataTransferObject> {

    private final AssemblyModel assemblyModel;
    private final DeploymentModel deploymentModel;
    private final Map<String, DeployedStorage> deployedStorageMap = new HashMap<>();
    private final Map<DeployedStorage, List<DeployedComponent>> deployedStorageAccessMap = new HashMap<>();

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
        if(dataTransferObject.callsOperation()){
            DeployedOperation deployedOperation = addOperation(deployedComponent, dataTransferObject);
        } else if(dataTransferObject.callsCommon()){
            DeployedStorage deployedStorage = addStorage(deployedComponent, dataTransferObject);
        } else {
            logger.error("Failed to setup Dataflow, due to an earlier error.");
        }
        this.outputPort.send(dataTransferObject);
    }

    private DeployedStorage addStorage(DeployedComponent deployedComponent, DataTransferObject dataTransferObject) {
        DeployedStorage deployedStorage = this.deployedStorageMap.get(dataTransferObject.getTargetIdent());
        if (deployedStorage == null) {
            deployedStorage = createStorage(dataTransferObject);
            deployedComponent.getStorages().put(dataTransferObject.getTargetIdent(), deployedStorage);
            this.deployedStorageMap.put(dataTransferObject.getTargetIdent(), deployedStorage);

            final List<DeployedComponent> newList = new ArrayList<>();
            newList.add(deployedComponent);
            logger.info("Placing Storage with name: " + deployedStorage.getAssemblyStorage().getStorageType().getName());
            this.deployedStorageAccessMap.put(deployedStorage, newList);
            this.addObjectToSource(deployedStorage);
        }
        return deployedStorage;

    }

    private DeployedStorage createStorage(DataTransferObject dataTransferObject) {
        DeployedStorage deployedStorage = DeploymentFactory.eINSTANCE.createDeployedStorage();
        deployedStorage.setAssemblyStorage(this.findAssemblyStorage(dataTransferObject.getTargetIdent()));
        return deployedStorage;
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

    private AssemblyStorage findAssemblyStorage(final String sharedData) {
        for (final AssemblyComponent assemblyComponent : this.assemblyModel.getComponents().values()) {
            final AssemblyStorage assemblyStorage = assemblyComponent.getStorages().get(sharedData);
            if (assemblyStorage != null) {
                return assemblyStorage;
            }
        }
        this.logger.error("Internal error. Could not find previously defined assembly storage {}", sharedData);
        return null;
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
