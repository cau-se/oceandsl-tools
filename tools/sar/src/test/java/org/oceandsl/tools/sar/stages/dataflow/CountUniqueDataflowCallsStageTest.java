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
package org.oceandsl.tools.sar.stages.dataflow;

import java.time.Duration;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import kieker.analysis.architecture.recovery.events.OperationEvent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.EDirection;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.TypeModel;

import teetime.framework.test.StageTester;

import org.oceandsl.analysis.architecture.ArchitectureModelUtils;

class CountUniqueDataflowCallsStageTest {

    @Test
    void test() {
        final TypeModel typeModel = ArchitectureModelUtils.createTypeModel();
        final AssemblyModel assemblyModel = ArchitectureModelUtils.createAssemblyModel(typeModel);
        final DeploymentModel deploymentModel = ArchitectureModelUtils.createDeploymentModel(assemblyModel);
        final ExecutionModel executionModel = ArchitectureModelUtils.createExecutionModel(deploymentModel);
        final StatisticsModel statisticsModel = ArchitectureModelUtils.createStatisticsModel(executionModel);

        final OperationEvent op1 = new OperationEvent(ArchitectureModelUtils.CONTEXT,
                ArchitectureModelUtils.CP_LEFT_FQN, ArchitectureModelUtils.CP_LEFT_FQN);
        final OperationEvent op2 = new OperationEvent(ArchitectureModelUtils.CONTEXT,
                ArchitectureModelUtils.CP_RIGHT_FQN, ArchitectureModelUtils.CP_RIGHT_FQN);
        final DataflowEvent sourceDataflow = new DataflowEvent(op1, op2, EDirection.BOTH, Duration.ofMillis(5));

        final CountUniqueDataflowCallsStage stage = new CountUniqueDataflowCallsStage(statisticsModel, executionModel);

        StageTester.test(stage).and().send(sourceDataflow).to(stage.getInputPort()).start();
        MatcherAssert.assertThat(stage.getOutputPort(), StageTester.produces(sourceDataflow));
    }

}
