package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.assembly.*;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.TypeModel;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

/**
 * Stage to define an assembly model according to bachelor thesis ss2022
 *
 * @author Yannick Illmann
 * @since 1.1
 */
public class AssemblyModelStage extends AbstractDataflowAssemblerStage<DataTransferObject,DataTransferObject> {


    private final TypeModel typeModel;
    private final AssemblyModel assemblyModel;


    public AssemblyModelStage(final TypeModel typeModel, final AssemblyModel assemblyModel,
                                               final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.typeModel = typeModel;
        this.assemblyModel = assemblyModel;
    }

    @SuppressWarnings("unused")
    @Override
    protected void execute(final DataTransferObject dataTransferObject) throws Exception {

        AssemblyComponent assemblyComponent = assemblyComponentSetUp(dataTransferObject);
        final AssemblyOperation assemblyOperation = addOperation(assemblyComponent,dataTransferObject);

        if(dataTransferObject.callsCommon()){
            // store common block independent of component containing functions
            assemblyComponent = createCommonComponent();
            final AssemblyStorage assemblyStorage = addStorage(assemblyComponent,dataTransferObject);
        } else {
            //if no storage is referenced, add target operation to target component
            final AssemblyComponent targetComponent = targetComponentAndOperationSetUp(dataTransferObject);
        }
        this.outputPort.send(dataTransferObject);
    }


    /*
        SETUP
     */
    /**
     * This function retrieves a stored or new created AssemblyComponent. Depending on the given identifier in the transfer-object
     * a subroutine is called to set up a new AssemblyComponent instance.
     *
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return component, stored for the given identifier string
     */
    @SuppressWarnings("unused")
    private AssemblyComponent assemblyComponentSetUp(final DataTransferObject dataTransferObject) {
        AssemblyComponent assemblyComponent = this.assemblyModel.getComponents().get(dataTransferObject.getComponent());
        if (assemblyComponent == null) {
            assemblyComponent = createAssemblyComponent(dataTransferObject);
        }
        AssemblyComponent packageAssemblyComponent = addComponent(assemblyComponent, dataTransferObject);
        return assemblyComponent;
    }

    /**
     * This function is used to create the matching target component of a given dataflow step. It will use the 'createAssemblyComponent' method
     * to store the new component in the assembly model. Therefor it creates a new transfer object only used in this method.
     *
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return component created and stored in the assembly model
     */
    @SuppressWarnings("unused")
    private AssemblyComponent targetComponentAndOperationSetUp(final DataTransferObject dataTransferObject){

        final DataTransferObject tempTargetDataTransferObject = new DataTransferObject();
        tempTargetDataTransferObject.setComponent(dataTransferObject.getTargetComponent());
        tempTargetDataTransferObject.setSourceIdent(dataTransferObject.getTargetIdent());
        tempTargetDataTransferObject.setSourcePackage(dataTransferObject.getTargetPackage());

        final AssemblyComponent targetComponentType = assemblyComponentSetUp(tempTargetDataTransferObject);
        final AssemblyOperation operationType = addOperation(targetComponentType, tempTargetDataTransferObject);
        return  targetComponentType;
    }

    /*
        ADDING
     */

    /**
     * This function adds a component to its referenced package component.
     *
     * @param containedAssemblyComponent component to add to package
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return the package component containing the provided file component
     */
    private AssemblyComponent addComponent(final AssemblyComponent containedAssemblyComponent,final DataTransferObject dataTransferObject){
        AssemblyComponent packageAssemblyComponent = this.assemblyModel.getComponents().get(dataTransferObject.getSourcePackage());
        if(packageAssemblyComponent == null){
            packageAssemblyComponent =  createPackageAssemblyComponent(dataTransferObject);
        }
        packageAssemblyComponent.getContainedComponents().add(containedAssemblyComponent);
        this.addObjectToSource(packageAssemblyComponent);
        return packageAssemblyComponent;
    }

    /**
     * This function adds an AssemblyOperation to a given component.
     *
     * @param assemblyComponent the operation should be stored in
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return the added operation. Useful for DEBUG Reasons
     */
    private AssemblyOperation addOperation(final AssemblyComponent assemblyComponent,final DataTransferObject dataTransferObject){
        AssemblyOperation operationType = assemblyComponent.getOperations().get(dataTransferObject.getSourceIdent());
        if(operationType == null){
            operationType = createOperation(assemblyComponent, dataTransferObject.getSourceIdent());
            assemblyComponent.getOperations().put(dataTransferObject.getSourceIdent(), operationType);
        }
        return operationType;
    }

    /**
     * This function adds an AssemblyStorage to a given component.
     *
     * @param assemblyComponent the storage should be stored in
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return the added operation. Useful for DEBUG Reasons
     */
    private AssemblyStorage addStorage(final AssemblyComponent assemblyComponent, final DataTransferObject dataTransferObject){
        AssemblyStorage assemblyStorage = assemblyComponent.getStorages().get(dataTransferObject.getTargetIdent());
        if(assemblyStorage == null){
            assemblyStorage = createAssemblyStorage(assemblyComponent, dataTransferObject.getTargetIdent());
            assemblyComponent.getStorages().put(dataTransferObject.getTargetIdent(), assemblyStorage);
            this.assemblyModel.getComponents().put(assemblyComponent.getSignature(),assemblyComponent);

            if(logger.isInfoEnabled()){
                logger.info("Placing Storage with name: " + assemblyStorage.getStorageType().getName());
            }
            this.addObjectToSource(assemblyStorage);
        }

        return assemblyStorage;
    }

    /*
        CREATING
     */
    /**
     * This function is used to create a new file AssemblyComponentObject and store it in the given assembly model.
     *
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return file component created and stored in the assembly model
     */
    private AssemblyComponent createAssemblyComponent(final DataTransferObject dataTransferObject){
        final AssemblyComponent newAssemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        newAssemblyComponent.setComponentType(this.typeModel.getComponentTypes().get(dataTransferObject.getComponent()));
        newAssemblyComponent.setSignature(dataTransferObject.getComponent());
        if(logger.isInfoEnabled()){
            logger.info("Placing AssemblyComponent with name: " + dataTransferObject.getComponent());
        }
        this.assemblyModel.getComponents().put(dataTransferObject.getComponent(), newAssemblyComponent);
        this.addObjectToSource(newAssemblyComponent);
        return newAssemblyComponent;
    }

    /**
     * This function retrieves a stored or new created AssemblyComponent, storing all storages/common blocks.
     *
     * @return component containing possible storages/common blocks.
     */
    private AssemblyComponent createCommonComponent() {
        final String commonIdent = "COMMON-Component";
        AssemblyComponent assemblyComponent = this.assemblyModel.getComponents().get(commonIdent);
        if (assemblyComponent == null) {
            assemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
            assemblyComponent.setComponentType(this.typeModel.getComponentTypes().get(commonIdent));
            assemblyComponent.setSignature(commonIdent);
            if(logger.isInfoEnabled()){
                logger.info("Placing AssemblyComponent with name: " + commonIdent);
            }
            this.assemblyModel.getComponents().put(commonIdent, assemblyComponent);
            this.addObjectToSource(assemblyComponent);
        }
        return assemblyComponent;
    }

    /**
     * This function is used to create a new package AssemblyComponentObject and store it in the given assembly model.
     *
     * @param dataTransferObject TransferObject containing all dataflow information in one step.
     * @return package component created and stored in the assembly model
     */
    private AssemblyComponent createPackageAssemblyComponent(final DataTransferObject dataTransferObject) {
        AssemblyComponent packageAssemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        packageAssemblyComponent.setSignature(dataTransferObject.getSourcePackage());
        packageAssemblyComponent.setComponentType(this.typeModel.getComponentTypes().get(dataTransferObject.getSourcePackage()));

        if(logger.isInfoEnabled()){
            logger.info("Placing Package-AssemblyComponent with name: " + packageAssemblyComponent.getSignature());
        }
        this.assemblyModel.getComponents().put(packageAssemblyComponent.getSignature(), packageAssemblyComponent);
        this.addObjectToSource(packageAssemblyComponent);
        return packageAssemblyComponent;
    }

    /**
     * This function creates an AssemblyOperationObject and stores it in the given source
     *
     * @param assemblyComponent component where the new operation is stored in
     * @param operationIdent string identifier for operation creation
     * @return created operation
     */
    private AssemblyOperation createOperation(final AssemblyComponent assemblyComponent,final String operationIdent) {
        final AssemblyOperation assemblyOperation = AssemblyFactory.eINSTANCE.createAssemblyOperation();
        assemblyOperation.setOperationType(assemblyComponent.getComponentType().getProvidedOperations().get(operationIdent));

        if(logger.isInfoEnabled()){
            logger.info("Placing AssemblyOperation with name: " + operationIdent);
        }
        this.addObjectToSource(assemblyOperation);
        return assemblyOperation;
    }

    /**
     * This function creates an AssemblyStorageObject and returns it
     *
     * @param assemblyComponent component where the new storage is stored in
     * @param storageIdent string identifier for storage creation
     * @return created storage
     */
    private AssemblyStorage createAssemblyStorage(final AssemblyComponent assemblyComponent,final String storageIdent) {
        final AssemblyStorage assemblyStorage = AssemblyFactory.eINSTANCE.createAssemblyStorage();
        assemblyStorage.setStorageType(assemblyComponent.getComponentType().getProvidedStorages().get(storageIdent));
        return assemblyStorage;
    }
}
