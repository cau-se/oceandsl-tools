package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.*;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeModelStage extends AbstractDataflowAssemblerStage<DataTransferObject,DataTransferObject> {

    static final String GLOBAL_PACKAGE = "global"; // NOPMD must be package global
    private static final String UNKOWN_TYPE = "UNKNOWN";

    private final TypeModel typeModel;
    private final Map<String, StorageType> storageTypeMap = new HashMap<>();
    private final Map<StorageType, List<ComponentType>> storageAccessMap = new HashMap<>();

    public TypeModelStage(final TypeModel typeModel, SourceModel sourceModel, String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.typeModel = typeModel;

    }

    @Override
    protected void execute(DataTransferObject dataTransferObject) throws Exception {
        ComponentType componentType = componentSetUp(dataTransferObject);
        if(dataTransferObject.callsCommon()){
            StorageType storageType = addStorage(componentType, dataTransferObject);
        } else if(dataTransferObject.callsOperation()){
            OperationType operationType = addOperation(componentType, dataTransferObject);
        } else {
            logger.error("Failed to setup Dataflow, due to an earlier error.");
        }
        this.outputPort.send(dataTransferObject);
    }

    private StorageType addStorage(ComponentType componentType, DataTransferObject dataTransferObject) {
        StorageType storageType = this.storageTypeMap.get(dataTransferObject.getTargetComponent()); // common Block init via registration of referencing
        if(storageType== null){
            storageType = createStorageType(dataTransferObject);
            componentType.getProvidedStorages().put(storageType.getName(), storageType);
            this.storageTypeMap.put(storageType.getName(), storageType);

            final List<ComponentType> newList = new ArrayList<>();
            newList.add(componentType);
            this.storageAccessMap.put(storageType, newList);
            logger.info("Placing Storage with name: " + storageType.getName());
            this.addObjectToSource(storageType);
        }
        return storageType;
    }

    private StorageType createStorageType(DataTransferObject dataTransferObject){
        StorageType storageType = TypeFactory.eINSTANCE.createStorageType();
        storageType.setName(dataTransferObject.getTargetIdent());
        storageType.setType(TypeModelStage.UNKOWN_TYPE);

        return storageType;
    }

    private ComponentType componentSetUp(DataTransferObject dataTransferObject) {
        ComponentType componentType = this.typeModel.getComponentTypes().get(dataTransferObject.getComponent());
        if (componentType == null) {
            componentType = createComponentType(dataTransferObject);
        }
        return componentType;
    }

    private ComponentType createComponentType(DataTransferObject dataTransferObject){
        final ComponentType newComponentType = TypeFactory.eINSTANCE.createComponentType();
        newComponentType.setName(dataTransferObject.getComponent());
        newComponentType.setSignature(dataTransferObject.getComponent());
        logger.info("Placing Component with name: " + newComponentType.getName());
        this.typeModel.getComponentTypes().put(dataTransferObject.getComponent(), newComponentType);
        this.addObjectToSource(newComponentType);
        return newComponentType;
    }

    private OperationType addOperation(ComponentType componentType, DataTransferObject dataTransferObject){
        OperationType operationType = componentType.getProvidedOperations().get(dataTransferObject.getSourceIdent());
        if(operationType == null){
            operationType = createOperation(dataTransferObject.getSourceIdent());
            componentType.getProvidedOperations().put(dataTransferObject.getSourceIdent(), operationType);
        }
        return operationType;
    }

    private OperationType createOperation(String operationIdent) {
        final OperationType operationType = TypeFactory.eINSTANCE.createOperationType();
        operationType.setName(operationIdent);
        operationType.setReturnType(TypeModelStage.UNKOWN_TYPE);
        operationType.setSignature(operationIdent);
        logger.info("Placing Operation with name: " + operationIdent);
        this.addObjectToSource(operationType);

        return operationType;
    }
}
