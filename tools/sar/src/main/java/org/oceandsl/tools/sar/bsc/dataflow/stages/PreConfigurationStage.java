/***************************************************************************
 * Copyright (C) 2022 OceanDSL (https://oceandsl.uni-kiel.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package org.oceandsl.tools.sar.bsc.dataflow.stages;

import kieker.model.analysismodel.source.SourceModel;

import org.oceandsl.tools.sar.bsc.dataflow.model.ComponentLookup;
import org.oceandsl.tools.sar.bsc.dataflow.model.DataTransfer;
import org.oceandsl.tools.sar.stages.dataflow.AbstractDataflowAssemblerStage;

/**
 * Stage to valid incoming dataflow statements according to bachelor thesis ss2022
 *
 * @author Yannick Illmann
 * @since 1.1
 */
public class PreConfigurationStage extends AbstractDataflowAssemblerStage<DataTransfer, DataTransfer> {

    private final ComponentLookup componentLookup;

    public PreConfigurationStage(final ComponentLookup componentLookup, final SourceModel sourceModel,
            final String sourceLabel) {
        super(sourceModel, sourceLabel);
        this.componentLookup = componentLookup;
    }

    @Override
    protected void execute(final DataTransfer dataTransferObject) throws Exception {

        // target component is empty by default
        final String targetIdent = dataTransferObject.getTargetIdent();
        dataTransferObject.setTargetComponent(this.componentLookup.getComponentIdent(targetIdent));
        try {

            if (targetIdent.contains("/")) {
                // Only a common block has '/' in its identifier
                if (this.logger.isInfoEnabled()) {
                    this.logger.info("Dataflow to Common saved");
                }
                dataTransferObject.setCallsCommon(true);
                this.sendDTO(dataTransferObject);
            } else if (this.componentLookup.callsOperation(dataTransferObject.getTargetComponent(), targetIdent)) {
                // Lookup class can verify inner model data flow.
                if (this.logger.isInfoEnabled()) {
                    this.logger.info("Dataflow to Operation saved");
                }
                dataTransferObject.setCallsOperation(true);
                final String targetComponent = dataTransferObject.getTargetComponent();
                dataTransferObject.setTargetPackage(this.componentLookup.getPackageToComponent(targetComponent));
                this.sendDTO(dataTransferObject);
            } else {
                if (this.logger.isErrorEnabled()) {
                    this.logger.error("Invalid Dataflow detected. No Valid Connection from "
                            + dataTransferObject.getSourceIdent() + " to Ident " + dataTransferObject.getTargetIdent()
                            + ". Please make sure its either a common block or operation and it is mentioned as such in analysis files!");
                }
            }
        } catch (final NullPointerException e) {
            /*
             * If no target component was set, a NullPointer is thrown, while verifying inner model
             * data flow no target concludes to external data flow.
             */

            if (this.logger.isErrorEnabled()) {
                this.logger.error("Unknown origin component from Operation: " + targetIdent);
            }
            dataTransferObject.setTargetComponent(".unknown");
            dataTransferObject.setTargetPackage(".unknown");

            /*
             * Common Blocks are referenced as "/(...)/" which is evaluated in first if, therefore
             * all unknown dataflow targets are handled like operations
             */
            dataTransferObject.setCallsOperation(true);
            this.sendDTO(dataTransferObject);
        }
    }

    /**
     * A helper method to send configured DTOs to next stage.
     *
     * @param dataTransferObject
     */
    private void sendDTO(final DataTransfer dataTransferObject) {
        final String component = dataTransferObject.getComponent();
        dataTransferObject.setSourcePackage(this.componentLookup.getPackageToComponent(component));

        this.outputPort.send(dataTransferObject);
    }
}
