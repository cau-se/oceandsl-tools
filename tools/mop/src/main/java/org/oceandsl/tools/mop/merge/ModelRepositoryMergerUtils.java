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
package org.oceandsl.tools.mop.merge;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.TypeModel;

/**
 * Merge two different model repositories
 *
 * @author Reiner Jung
 * @since 1.1
 */
public final class ModelRepositoryMergerUtils {

    private ModelRepositoryMergerUtils() {
        // Utility class
    }

    public static void perform(final ModelRepository lastModelRepository, final ModelRepository mergeModelRepository) {
        TypeModelMerger.mergeTypeModel(lastModelRepository.getModel(TypeModel.class),
                mergeModelRepository.getModel(TypeModel.class));
        AssemblyModelMerger.mergeAssemblyModel(lastModelRepository.getModel(TypeModel.class),
                lastModelRepository.getModel(AssemblyModel.class), mergeModelRepository.getModel(AssemblyModel.class));
        DeploymentModelMerger.mergeDeploymentModel(lastModelRepository.getModel(AssemblyModel.class),
                lastModelRepository.getModel(DeploymentModel.class),
                mergeModelRepository.getModel(DeploymentModel.class));
        ExecutionModelMerger.mergeExecutionModel(lastModelRepository.getModel(DeploymentModel.class),
                lastModelRepository.getModel(ExecutionModel.class),
                mergeModelRepository.getModel(ExecutionModel.class));
        StatisticsModelMerger.mergeStatisticsModel(lastModelRepository.getModel(ExecutionModel.class),
                lastModelRepository.getModel(StatisticsModel.class),
                mergeModelRepository.getModel(StatisticsModel.class));
        SourceModelMerger.mergeSourceModel(lastModelRepository.getModel(TypeModel.class),
                lastModelRepository.getModel(AssemblyModel.class), lastModelRepository.getModel(DeploymentModel.class),
                lastModelRepository.getModel(ExecutionModel.class), lastModelRepository.getModel(SourceModel.class),
                mergeModelRepository.getModel(SourceModel.class));
    }

}
