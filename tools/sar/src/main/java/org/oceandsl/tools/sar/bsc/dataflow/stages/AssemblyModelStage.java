package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.assembly.*;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.StorageType;
import kieker.model.analysismodel.type.TypeModel;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssemblyModelStage extends AbstractDataflowAssemblerStage<DataTransferObject,DataTransferObject> {


    private final TypeModel typeModel;
    private final AssemblyModel assemblyModel;
    private final Map<String, AssemblyStorage> assemblyStorageMap = new HashMap<>();
    private final Map<AssemblyStorage, List<AssemblyComponent>> assemblyStorageAccessMap = new HashMap<>();


    public AssemblyModelStage(final TypeModel typeModel, final AssemblyModel assemblyModel,
                                               final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.typeModel = typeModel;
        this.assemblyModel = assemblyModel;
    }

    @Override
    protected void execute(DataTransferObject dataTransferObject) throws Exception {
        AssemblyComponent assemblyComponent = assemblyComponentSetUp(dataTransferObject);
        if(dataTransferObject.callsOperation()){
            AssemblyOperation assemblyOperation = addOperation(assemblyComponent,dataTransferObject);
        } else if(dataTransferObject.callsCommon()){
            assemblyComponent = commonComponentSetUp(); // store common block independent of dataflow component
            AssemblyStorage assemblyStorage = addStorage(assemblyComponent,dataTransferObject);
        } else {
            AssemblyOperation assemblyOperation = addOperation(assemblyComponent,dataTransferObject);

            logger.warn("Unknown Dataflow detected.");
            DataTransferObject unknownFlowObject = new DataTransferObject();
            unknownFlowObject.setComponent("Unknown");
            unknownFlowObject.setSourceIdent(dataTransferObject.getTargetIdent());
            AssemblyComponent assemblyComponentUnknown = assemblyComponentSetUp(unknownFlowObject);
            AssemblyOperation assemblyOperationUnknown = addOperation(assemblyComponentUnknown,unknownFlowObject);

        }
        this.outputPort.send(dataTransferObject);
    }

    private AssemblyComponent assemblyComponentSetUp(DataTransferObject dataTransferObject) {
        AssemblyComponent assemblyComponent = this.assemblyModel.getComponents().get(dataTransferObject.getComponent());
        if (assemblyComponent == null) {
            assemblyComponent = createAssemblyComponent(dataTransferObject);
        }
        return assemblyComponent;
    }

    private AssemblyComponent commonComponentSetUp() {
        String commonIdent = "COMMON-Component";
        AssemblyComponent assemblyComponent = this.assemblyModel.getComponents().get(commonIdent);
        if (assemblyComponent == null) {
            assemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
            assemblyComponent.setComponentType(this.typeModel.getComponentTypes().get(commonIdent));
            assemblyComponent.setSignature(commonIdent);
            logger.info("Placing AssemblyComponent with name: " + commonIdent);
            this.assemblyModel.getComponents().put(commonIdent, assemblyComponent);
            this.addObjectToSource(assemblyComponent);
        }
        return assemblyComponent;
    }

    private AssemblyComponent createAssemblyComponent(DataTransferObject dataTransferObject){
        final AssemblyComponent newAssemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        newAssemblyComponent.setComponentType(this.typeModel.getComponentTypes().get(dataTransferObject.getComponent()));
        newAssemblyComponent.setSignature(dataTransferObject.getComponent());
        logger.info("Placing AssemblyComponent with name: " + dataTransferObject.getComponent());
        this.assemblyModel.getComponents().put(dataTransferObject.getComponent(), newAssemblyComponent);
        this.addObjectToSource(newAssemblyComponent);
        return newAssemblyComponent;
    }
    private AssemblyOperation addOperation(AssemblyComponent assemblyComponent, DataTransferObject dataTransferObject){
        AssemblyOperation operationType = assemblyComponent.getOperations().get(dataTransferObject.getSourceIdent());
        if(operationType == null){
            operationType = createOperation(assemblyComponent, dataTransferObject.getSourceIdent());
            assemblyComponent.getOperations().put(dataTransferObject.getSourceIdent(), operationType);
        }
        return operationType;
    }

    private AssemblyOperation createOperation(AssemblyComponent assemblyComponent, String operationIdent) {
        final AssemblyOperation assemblyOperation = AssemblyFactory.eINSTANCE.createAssemblyOperation();
        assemblyOperation.setOperationType(assemblyComponent.getComponentType().getProvidedOperations().get(operationIdent));
        logger.info("Placing AssemblyOperation with name: " + operationIdent);
        this.addObjectToSource(assemblyOperation);
        return assemblyOperation;
    }

    private AssemblyStorage addStorage(AssemblyComponent assemblyComponent, final DataTransferObject dataTransferObject){
        AssemblyStorage assemblyStorage = assemblyComponent.getStorages().get(dataTransferObject.getTargetIdent());
        if(assemblyStorage == null){
            assemblyStorage = createAssemblyStorage(dataTransferObject);
            assemblyComponent.getStorages().put(dataTransferObject.getTargetIdent(), assemblyStorage);
            this.assemblyModel.getComponents().put(assemblyComponent.getSignature(),assemblyComponent);
            this.assemblyStorageMap.put(dataTransferObject.getTargetComponent(), assemblyStorage);

            final List<AssemblyComponent> newList = new ArrayList<>();
            newList.add(assemblyComponent);
            logger.info("Placing Storage with name: " + assemblyStorage.getStorageType().getName());
            this.assemblyStorageAccessMap.put(assemblyStorage, newList);
            this.addObjectToSource(assemblyStorage);
        }

        return assemblyStorage;
    }

    private AssemblyStorage createAssemblyStorage(DataTransferObject dataTransferObject) {
        AssemblyStorage assemblyStorage = AssemblyFactory.eINSTANCE.createAssemblyStorage();
        assemblyStorage.setStorageType(this.findStorageType(dataTransferObject.getTargetIdent()));
        return assemblyStorage;
    }

    private StorageType findStorageType(final String sharedData) {
        for (final ComponentType type : this.typeModel.getComponentTypes().values()) {
            final StorageType storageType = type.getProvidedStorages().get(sharedData);
            if (storageType != null) {
                return storageType;
            }
        }
        this.logger.error("Internal error: Missing storage type for given data access element {}", sharedData);
        return null;
    }
}
