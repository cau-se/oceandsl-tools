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
package org.oceandsl.analysis.architecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.source.SourceModel;
import kieker.model.analysismodel.source.SourcePackage;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.statistics.StatisticsPackage;
import kieker.model.analysismodel.type.TypeModel;
import kieker.model.analysismodel.type.TypePackage;

/**
 * Test @{link ArichtectureModelManagementUtils}.
 *
 * @author Reiner Jung
 * @since 1.3.0
 *
 */
class ArchitectureModelManagementUtilsTest {

    private static final String EXPERIMENT_NAME = "test-experiment";

    @Test
    public void createModelRepositoryTest() { // NOPMD
        final ModelRepository repository = ArchitectureModelManagementUtils
                .createModelRepository(ArchitectureModelManagementUtilsTest.EXPERIMENT_NAME);
        Assertions.assertEquals(ArchitectureModelManagementUtilsTest.EXPERIMENT_NAME, repository.getName());
        Assertions.assertEquals(TypeModel.class, repository.getModel(TypePackage.Literals.TYPE_MODEL).getClass());
        Assertions.assertEquals(AssemblyModel.class,
                repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL).getClass());
        Assertions.assertEquals(DeploymentModel.class,
                repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL).getClass());
        Assertions.assertEquals(ExecutionModel.class,
                repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL).getClass());
        Assertions.assertEquals(StatisticsModel.class,
                repository.getModel(StatisticsPackage.Literals.STATISTICS_MODEL).getClass());
        Assertions.assertEquals(SourceModel.class, repository.getModel(SourcePackage.Literals.SOURCE_MODEL).getClass());
    }
}
