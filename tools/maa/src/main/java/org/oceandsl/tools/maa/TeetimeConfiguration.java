/***************************************************************************
 * Copyright (C) 2021 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.maa;

import kieker.analysis.stage.model.ModelRepository;

import teetime.framework.Configuration;
import teetime.framework.OutputPort;
import teetime.stage.basic.distributor.Distributor;

import org.oceandsl.analysis.stages.generic.TableCSVSink;
import org.oceandsl.analysis.stages.model.ModelRepositoryProducerStage;
import org.oceandsl.analysis.stages.model.ModelSink;
import org.oceandsl.tools.maa.stages.ComputeInterfacesStage;
import org.oceandsl.tools.maa.stages.ProvidedInterfaceTableTransformation;

/**
 * @author Reiner Jung
 * @sicne 1.2
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final Settings settings) {
        final ModelRepositoryProducerStage modelReader = new ModelRepositoryProducerStage(settings.getInputModelPath());

        final Distributor<ModelRepository> distributor = new Distributor<>();

        final ModelSink modelSink = new ModelSink(settings.getOutputModelPath());
        final ProvidedInterfaceTableTransformation providedInterfaceTableTransformation = new ProvidedInterfaceTableTransformation();
        final TableCSVSink providedInterfaceSink = new TableCSVSink(settings.getOutputModelPath(),
                "provided-interfaces.csv");

        OutputPort<ModelRepository> outputPort = modelReader.getOutputPort();
        if (settings.isComputeInterfaces()) {
            final ComputeInterfacesStage computeInterfaces = new ComputeInterfacesStage();
            this.connectPorts(outputPort, computeInterfaces.getInputPort());
            outputPort = computeInterfaces.getOutputPort();
        }

        this.connectPorts(outputPort, distributor.getInputPort());
        this.connectPorts(distributor.getNewOutputPort(), modelSink.getInputPort());
        this.connectPorts(distributor.getNewOutputPort(), providedInterfaceTableTransformation.getInputPort());
        this.connectPorts(providedInterfaceTableTransformation.getOutputPort(), providedInterfaceSink.getInputPort());
    }

}
