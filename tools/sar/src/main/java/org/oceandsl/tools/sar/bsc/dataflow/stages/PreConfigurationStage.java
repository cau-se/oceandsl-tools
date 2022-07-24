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

        //target component is empty by default
        String targetIdent = dataTransferObject.getTargetIdent();
        dataTransferObject.setTargetComponent(componentLookup.getComponentIdent(targetIdent));
        try{

            if(targetIdent.contains("/")){
                logger.info("Dataflow to Common saved");
                dataTransferObject.setCallsCommon(true);
            } else if(componentLookup.callsOperation(dataTransferObject.getTargetComponent(), targetIdent)){
                logger.info("Dataflow to Operation saved");
                dataTransferObject.setCallsOperation(true);
            } else {
                logger.error("Invalid Dataflow detected. No Valid Connection from "
                        + dataTransferObject.getSourceIdent() + " to Ident " + dataTransferObject.getTargetIdent()
                        + ". Please make sure its either a common block or subroutine and it is mentioned as such in analysis files!");
            }

        }catch(NullPointerException e){
            logger.error("Unknown origin component from Operation: " + targetIdent);
            dataTransferObject.setTargetComponent(".unknown");

            // Common Blocks are referenced as "/(...)/" which is evaluated in first if,
            //therefore all unknown dataflow targets are handled like operations
            dataTransferObject.setCallsOperation(true);
        }

        this.outputPort.send(dataTransferObject);
    }
}
