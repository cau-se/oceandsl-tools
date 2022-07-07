package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.sources.SourceModel;
import org.oceandsl.tools.sar.bsc.dataflow.model.ComponentLookup;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

public class PreConfigurationStage extends AbstractDataflowAssemblerStage<DataTransferObject, DataTransferObject> {

    ComponentLookup componentLookup;
    public PreConfigurationStage(ComponentLookup componentLookup, SourceModel sourceModel, String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.componentLookup = componentLookup;
    }

    @Override
    protected void execute(final DataTransferObject dataTransferObject) throws Exception {

        //If source of our target Content is unknown, look in packages which were import.
        String targetIdent = dataTransferObject.getTargetIdent();
        dataTransferObject.setTargetComponent(componentLookup.getComponentIdent(targetIdent));
        try{

            if(componentLookup.callsCommon(dataTransferObject.getTargetComponent(), targetIdent)){
                logger.info("Dataflow to Common saved");
                dataTransferObject.setCallsCommon(true);
            } else if(componentLookup.callsOperation(dataTransferObject.getTargetComponent(), targetIdent)){
                logger.info("Dataflow to Operation saved");
                dataTransferObject.setCallsOperation(true);
            } else {
                logger.error("Invalid Dataflow detected. No Valid Connection from " + dataTransferObject.getSourceIdent() + " to Ident " + dataTransferObject.getTargetIdent() + ". Please make sure its either a common block or subroutine and it is mentioned as such in analysis files!");
            }
        }catch(NullPointerException e){
            logger.error("Unknown origin component from content: " + targetIdent);
            logger.info("datatransferobject: component=" + dataTransferObject.getComponent() +
                                            " sourceIdent="+ dataTransferObject.getSourceIdent() +
                                            " targetIdent="+ dataTransferObject.getTargetIdent());

        }

        if(dataTransferObject.getTargetComponent() == null){
            logger.error("Unknown component from content: " + targetIdent);
        }
        this.outputPort.send(dataTransferObject);
    }
}
