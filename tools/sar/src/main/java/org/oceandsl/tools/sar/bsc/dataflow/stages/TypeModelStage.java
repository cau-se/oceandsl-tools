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
        ComponentType componentType = componentSetUp(dataTransferObject);
        OperationType operationType = addOperation(componentType, dataTransferObject);
        this.outputPort.send(dataTransferObject);
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
        logger.info("Placing Component with name: " + dataTransferObject.getComponent());
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
        operationType.setReturnType("unknown");
        operationType.setSignature(operationIdent);
        logger.info("Placing Operation with name: " + operationIdent);
        this.addObjectToSource(operationType);

        return operationType;
    }
}
