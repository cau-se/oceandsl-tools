package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.*;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

public class TypeModelStage extends AbstractDataflowAssemblerStage<DataTransferObject,DataTransferObject> {

    private static final String UNKNOWN_TYPE = "UNKNOWN";

    private final TypeModel typeModel;

    public TypeModelStage(final TypeModel typeModel, SourceModel sourceModel, String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.typeModel = typeModel;

    }

    @SuppressWarnings("unused")
    @Override
    protected void execute(DataTransferObject dataTransferObject) throws Exception {

        ComponentType componentType = componentSetUp(dataTransferObject);
        OperationType operationType = addOperation(componentType, dataTransferObject);

        if(dataTransferObject.callsCommon()){
            // store common block independent of component containing functions
            componentType = commonComponentSetUp();
            dataTransferObject.setTargetComponent(componentType.getName());
            StorageType storageType = addStorage(componentType, dataTransferObject);
        } else {
            //if no storage is referenced, add target operation to target component
            ComponentType targetComponent = createTargetComponentAndOperation(dataTransferObject);
        }

        this.outputPort.send(dataTransferObject);
    }

    /*
        SETUP
     */

    /**
     * This function retrieves a stored or new created ComponentType. Depending on the given identifier in the transfer-object
     * a subroutine is called to set up a new ComponentType instance.
     *
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return component, stored for the given identifier string
     */
    private ComponentType componentSetUp(DataTransferObject dataTransferObject) {
        ComponentType componentType = this.typeModel.getComponentTypes().get(dataTransferObject.getComponent());
        if (componentType == null) {
            componentType = createComponentType(dataTransferObject);
        }
        return componentType;
    }

    /**
     * This function retrieves a stored or new created ComponentType, storing all storages/common blocks.
     *
     * @return component containing possible storages/common blocks.
     */
    private ComponentType commonComponentSetUp() {
        String commonIdent = "COMMON-Component";
        ComponentType componentType = this.typeModel.getComponentTypes().get(commonIdent);
        if (componentType == null) {
            componentType = TypeFactory.eINSTANCE.createComponentType();
            componentType.setName(commonIdent);
            componentType.setSignature(commonIdent);

            logger.info("Placing Component with name: " + componentType.getName());
            this.typeModel.getComponentTypes().put(componentType.getName(), componentType);
            this.addObjectToSource(componentType);
            return componentType;
        }
        return componentType;
    }

    /*
        ADDING
     */

    /**
     * This function adds an OperationType to a given component.
     *
     * @param componentType the operation should be stored in
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return the added operation. Useful for DEBUG Reasons
     */
    private OperationType addOperation(ComponentType componentType, DataTransferObject dataTransferObject){
        OperationType operationType = componentType.getProvidedOperations().get(dataTransferObject.getSourceIdent());
        if(operationType == null){
            operationType = createOperation(dataTransferObject.getSourceIdent());
            componentType.getProvidedOperations().put(dataTransferObject.getSourceIdent(), operationType);
        }
        return operationType;
    }

    /**
     * This function adds an StorageType to a given component.
     *
     * @param componentType the storage should be stored in
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return the added storage. Useful for DEBUG Reasons
     */
    private StorageType addStorage(ComponentType componentType, DataTransferObject dataTransferObject) {
        StorageType storageType = componentType.getProvidedStorages().get(dataTransferObject.getTargetIdent()); // common Block init via registration of referencing
        if(storageType== null){
            storageType = createStorageType(dataTransferObject);
            componentType.getProvidedStorages().put(storageType.getName(), storageType);
            this.typeModel.getComponentTypes().put(componentType.getName(), componentType);

            logger.info("Placing Storage with name: " + storageType.getName());
            this.addObjectToSource(storageType);
        }
        return storageType;
    }

    /*
        CREATING
     */

    /**
     * This function is used to create a new ComponentTypeObject and store it in the given type model.
     *
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return component created and stored in the type model
     */
    private ComponentType createComponentType(DataTransferObject dataTransferObject){
        final ComponentType newComponentType = TypeFactory.eINSTANCE.createComponentType();
        newComponentType.setName(dataTransferObject.getComponent());
        newComponentType.setPackage("GLOBAL");
        newComponentType.setSignature(dataTransferObject.getComponent());

        logger.info("Placing Component with name: " + newComponentType.getName());
        this.typeModel.getComponentTypes().put(dataTransferObject.getComponent(), newComponentType);
        this.addObjectToSource(newComponentType);
        return newComponentType;
    }

    /**
     * This function is used to create the matching target component of a given dataflow step. It will use the 'createComponentType' method
     * to store the new component in the type model. Therefor it creates a new transfer object only used in this method.
     *
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return target component created and stored in the type model
     */
    @SuppressWarnings("unused")
    private ComponentType createTargetComponentAndOperation(DataTransferObject dataTransferObject){

        DataTransferObject tempTargetDataTransferObject = new DataTransferObject();
        tempTargetDataTransferObject.setComponent(dataTransferObject.getTargetComponent());
        tempTargetDataTransferObject.setSourceIdent(dataTransferObject.getTargetIdent());

        ComponentType targetComponentType = componentSetUp(tempTargetDataTransferObject);
        OperationType operationType = addOperation(targetComponentType, tempTargetDataTransferObject);
        return  targetComponentType;
    }

    /**
     * This function creates an OperationTypeObject and stores it in the given source
     *
     * @param operationIdent name of the OperationTypeObject
     * @return created operation
     */
    private OperationType createOperation(String operationIdent) {
        final OperationType operationType = TypeFactory.eINSTANCE.createOperationType();
        operationType.setName(operationIdent);
        operationType.setReturnType(TypeModelStage.UNKNOWN_TYPE);
        operationType.setSignature(operationIdent);

        logger.info("Placing Operation with name: " + operationIdent);
        this.addObjectToSource(operationType);

        return operationType;
    }

    /**
     * This function creates an StorageTypeObject and returns it
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return created storage
     */
    private StorageType createStorageType(DataTransferObject dataTransferObject){
        StorageType storageType = TypeFactory.eINSTANCE.createStorageType();
        storageType.setName(dataTransferObject.getTargetIdent());
        storageType.setType(TypeModelStage.UNKNOWN_TYPE);

        return storageType;
    }
}
