package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.sources.SourceModel;
import org.oceandsl.tools.sar.bsc.dataflow.model.ComponentLookup;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

/**
 * Stage to valid incoming dataflow statements according to bachelor thesis ss2022
 *
 * @author Yannick Illmann
 * @since 1.1
 */public class PreConfigurationStage extends AbstractDataflowAssemblerStage<DataTransferObject, DataTransferObject> {

    private final ComponentLookup componentLookup;
    public PreConfigurationStage(final ComponentLookup componentLookup,final SourceModel sourceModel,final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.componentLookup = componentLookup;
    }

    @Override
    protected void execute(final DataTransferObject dataTransferObject) throws Exception {

        //target component is empty by default
        final String targetIdent = dataTransferObject.getTargetIdent();
        dataTransferObject.setTargetComponent(componentLookup.getComponentIdent(targetIdent));
        try{

            if(targetIdent.contains("/")){
                if(logger.isInfoEnabled()){
                    logger.info("Dataflow to Common saved");
                }
                dataTransferObject.setCallsCommon(true);
            } else if(componentLookup.callsOperation(dataTransferObject.getTargetComponent(), targetIdent)){
                if(logger.isInfoEnabled()){
                    logger.info("Dataflow to Operation saved");
                }
                dataTransferObject.setCallsOperation(true);
            } else {
                if(logger.isErrorEnabled()){
                    logger.error("Invalid Dataflow detected. No Valid Connection from "
                            + dataTransferObject.getSourceIdent() + " to Ident " + dataTransferObject.getTargetIdent()
                            + ". Please make sure its either a common block or subroutine and it is mentioned as such in analysis files!");
                }
            }
        }catch(NullPointerException e){
            if(logger.isErrorEnabled()){
                logger.error("Unknown origin component from Operation: " + targetIdent);
            }
            dataTransferObject.setTargetComponent(".unknown");

            /*
             * Common Blocks are referenced as "/(...)/" which is evaluated in first if,
             * therefore all unknown dataflow targets are handled like operations
             */
            dataTransferObject.setCallsOperation(true);
        }
        final String component = dataTransferObject.getComponent();
        dataTransferObject.setPackageSourceIdent(componentLookup.getPackageToComponent(component));

        this.outputPort.send(dataTransferObject);
    }
}
