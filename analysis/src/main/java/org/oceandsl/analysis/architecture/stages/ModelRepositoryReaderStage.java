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
package org.oceandsl.analysis.architecture.stages;

import java.nio.file.Path;

import org.oceandsl.analysis.architecture.ArchitectureModelManagementUtils;

import kieker.analysis.architecture.repository.ModelRepository;
import teetime.stage.basic.AbstractTransformation;

/**
 * Loads model repositories based on the paths it receives. Each loaded model is then passed
 * as @{link ModelRepository} to the next stage.
 *
 * @author Reiner Jung
 *
 */
public class ModelRepositoryReaderStage extends AbstractTransformation<Path, ModelRepository> {

    @Override
    protected void execute(final Path element) throws Exception {
        this.outputPort.send(ArchitectureModelManagementUtils.loadModelRepository(element));
    }

}
