package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.type.TypeModel;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

public class AssemblyModelStage extends AbstractDataflowAssemblerStage<DataTransferObject,DataTransferObject> {


    private final TypeModel typeModel;
    private final AssemblyModel assemblyModel;
    public AssemblyModelStage(final TypeModel typeModel, final AssemblyModel assemblyModel,
                                               final SourceModel sourceModel, final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.typeModel = typeModel;
        this.assemblyModel = assemblyModel;
    }

    @Override
    protected void execute(DataTransferObject dataTransferObject) throws Exception {
        AssemblyComponent assemblyComponent = assemblyComponentSetUp(dataTransferObject);
        AssemblyOperation assemblyOperation = addOperation(assemblyComponent,dataTransferObject);

        this.outputPort.send(dataTransferObject);
    }

    private AssemblyComponent assemblyComponentSetUp(DataTransferObject dataTransferObject) {
        AssemblyComponent assemblyComponent = this.assemblyModel.getComponents().get(dataTransferObject.getComponent());
        if (assemblyComponent == null) {
            assemblyComponent = createAssemblyComponent(dataTransferObject);
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
}
