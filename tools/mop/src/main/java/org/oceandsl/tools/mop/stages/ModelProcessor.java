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
package org.oceandsl.tools.mop.stages;

import org.oceandsl.architecture.model.ArchitectureModelManagementFactory;
import org.oceandsl.tools.mop.merge.ModelRepositoryMerger;

import kieker.analysis.stage.model.ModelRepository;
import teetime.framework.AbstractConsumerStage;
import teetime.framework.OutputPort;

/**
 * @author Reiner Jung
 *
 */
public class ModelProcessor extends AbstractConsumerStage<ModelRepository> {

    private final OutputPort<ModelRepository> outputPort = this.createOutputPort(ModelRepository.class);

    private final ModelRepository lastModel;

    int task = 0;

    public ModelProcessor(final String repositoryName) {
        this.lastModel = ArchitectureModelManagementFactory.createModelRepository(repositoryName);
    }

    @Override
    protected void execute(final ModelRepository element) throws Exception {
        this.logger.info("Merging models {}", element.getName());
        ModelRepositoryMerger.perform(this.lastModel, element);
        this.task++;
    }

    public OutputPort<ModelRepository> getOutputPort() {
        return this.outputPort;
    }

    @Override
    protected void onTerminating() {
        this.outputPort.send(this.lastModel);
        super.onTerminating();
    }

}
