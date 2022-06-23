package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.sources.SourceModel;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransferObject;
import org.oceandsl.tools.sar.bsc.dataflow.model.FunctionLookup;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;
import org.oceandsl.tools.sar.stages.dataflow.DataAccess;

import javax.security.auth.login.Configuration;

public class PreConfigurationStage extends AbstractDataflowAssemblerStage<DataTransferObject, DataTransferObject> {

    FunctionLookup functionLookup;
    public PreConfigurationStage(FunctionLookup functionLookup, SourceModel sourceModel, String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.functionLookup = functionLookup;
    }

    @Override
    protected void execute(final DataTransferObject dataTransferObject) throws Exception {

        //If source of our target Content is unknown, look in packages which were import.
        String targetIdent = dataTransferObject.getTargetIdent();
        if(targetIdent.contains(",")){
            String[] packages = dataTransferObject.getTargetIdent().split(",");
            targetIdent = packages[0];

            //check own component
            if(functionLookup.isPartOfComponent(dataTransferObject.getComponent(), targetIdent )){
                dataTransferObject.setTargetComponent(dataTransferObject.getComponent());
            } else {
                //Nothing found in own component -> check in imported components
                for (int i = 1; i < packages.length; i++) {
                    if(functionLookup.isPartOfComponent(packages[i], targetIdent )){
                        dataTransferObject.setTargetComponent(packages[i]);
                        break;
                    }
                }
            }
        }
        if(dataTransferObject.getTargetComponent() == null){
            logger.error("Unknown component from content: " + targetIdent);
        }
    }
}
