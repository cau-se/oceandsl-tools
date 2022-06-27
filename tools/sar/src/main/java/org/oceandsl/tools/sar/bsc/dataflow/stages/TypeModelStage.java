package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

public class TypeModelStage extends AbstractDataflowAssemblerStage<DataTransferObject,DataTransferObject> {

    static final String GLOBAL_PACKAGE = "global"; // NOPMD must be package global
    private final TypeModel typeModel;

    public TypeModelStage(final TypeModel typeModel, SourceModel sourceModel, String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.typeModel = typeModel;

    }

    @Override
    protected void execute(DataTransferObject dataTransferObject) throws Exception {
        logger.debug("entering TypeModelStage");
        ComponentType componentType = componentSetUp(dataTransferObject);
        OperationType operationType = addOperation(componentType, dataTransferObject);

    }

    private ComponentType componentSetUp(DataTransferObject dataTransferObject) {
        ComponentType componentType = this.typeModel.getComponentTypes().get(dataTransferObject.getComponent());
        if (componentType == null) {
            componentType = createComponentType();
        }
        return componentType;
    }

    private OperationType addOperation(ComponentType componentType, DataTransferObject dataTransferObject){
        OperationType operationType = componentType.getProvidedOperations().get(dataTransferObject.getSourceIdent());
        if(operationType == null){
            operationType = createOperation(dataTransferObject.getSourceIdent());
            componentType.getProvidedOperations().put(dataTransferObject.getSourceIdent(), operationType);
        }
        return operationType;
    }

    private OperationType createOperation(String operation) {
        final OperationType operationType = TypeFactory.eINSTANCE.createOperationType();
        operationType.setName(operation);
        operationType.setReturnType("unknown");
        operationType.setSignature(operation);
        this.addObjectToSource(operationType);

        return operationType;
    }

    private ComponentType createComponentType(){

            logger.info("Creating component instance... ");
            final ComponentType newComponentType = TypeFactory.eINSTANCE.createComponentType();
            newComponentType.setName(TypeModelStage.GLOBAL_PACKAGE);
            newComponentType.setPackage(TypeModelStage.GLOBAL_PACKAGE);
            newComponentType.setSignature(TypeModelStage.GLOBAL_PACKAGE);

            this.typeModel.getComponentTypes().put(newComponentType.getSignature(), newComponentType);
            this.addObjectToSource(newComponentType);
            return newComponentType;
    }
}
