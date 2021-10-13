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
package org.oceandsl.tools.mop;

import java.io.IOException;

import org.oceandsl.tools.mop.stages.ModelProcessor;
import org.oceandsl.tools.mop.stages.ModelRepositoryReaderStage;
import org.oceandsl.tools.mop.stages.ModelSink;
import org.oceandsl.tools.mop.stages.ModelSource;
import org.slf4j.Logger;

import kieker.analysis.stage.model.ModelRepository;
import teetime.framework.Configuration;

/**
 * Pipe and Filter configuration for the architecture creation tool.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class TeetimeConfiguration extends Configuration {

    public TeetimeConfiguration(final Logger logger, final Settings parameterConfiguration,
            final ModelRepository repository) throws IOException {

        final ModelSource modelSource = new ModelSource(parameterConfiguration.getInputModelPaths());
        final ModelRepositoryReaderStage modelReader = new ModelRepositoryReaderStage();
        final ModelProcessor modelProcessor = new ModelProcessor(parameterConfiguration.getExperimentName());
        final ModelSink modelSink = new ModelSink(parameterConfiguration.getOutputDirectory());

        this.connectPorts(modelSource.getOutputPort(), modelReader.getInputPort());
        this.connectPorts(modelReader.getOutputPort(), modelProcessor.getInputPort());
        this.connectPorts(modelProcessor.getOutputPort(), modelSink.getInputPort());
    }
}
