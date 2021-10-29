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

import java.nio.file.Path;

import org.oceandsl.analysis.architecture.model.ArchitectureModelManagementUtils;

import kieker.analysis.stage.model.ModelRepository;
import teetime.framework.AbstractConsumerStage;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class ModelSink extends AbstractConsumerStage<ModelRepository> {

    private final Path outputPath;

    public ModelSink(final Path outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    protected void execute(final ModelRepository element) throws Exception {
        ArchitectureModelManagementUtils.writeModelRepository(this.outputPath, element);
    }

}