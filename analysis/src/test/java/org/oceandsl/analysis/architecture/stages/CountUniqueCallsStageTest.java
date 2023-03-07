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
package org.oceandsl.analysis.architecture.stages;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import kieker.analysis.architecture.recovery.events.OperationCallDurationEvent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Invocation;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.TypeModel;

import teetime.framework.test.StageTester;

import org.oceandsl.analysis.architecture.ArchitectureModelUtils;

class CountUniqueCallsStageTest {

    @Test
    public void test() {
        final TypeModel typeModel = ArchitectureModelUtils.createTypeModel();
        final AssemblyModel assemblyModel = ArchitectureModelUtils.createAssemblyModel(typeModel);
        final DeploymentModel deploymentModel = ArchitectureModelUtils.createDeploymentModel(assemblyModel);
        final ExecutionModel executionModel = ArchitectureModelUtils.createExecutionModel(deploymentModel);
        final StatisticsModel statisticsModel = ArchitectureModelUtils.createEmptyStatisticsModel(executionModel);

        final Tuple<DeployedOperation, DeployedOperation> invocationKey = executionModel.getInvocations().keySet()
                .iterator().next();

        final OperationCallDurationEvent sourceCall = new OperationCallDurationEvent(invocationKey,
                Duration.of(5, ChronoUnit.NANOS));

        final Invocation invocation = executionModel.getInvocations().get(invocationKey);

        final CountUniqueCallsStage stage = new CountUniqueCallsStage(statisticsModel, executionModel);

        StageTester.test(stage).and().send(sourceCall).to(stage.getInputPort()).start();
        MatcherAssert.assertThat(stage.getOutputPort(), StageTester.produces(sourceCall));

        Assertions.assertEquals(1, statisticsModel.getStatistics().size(), "one entry should exist");
        Assertions.assertTrue(statisticsModel.getStatistics().containsKey(invocation), "missing entry");
        Assertions.assertEquals(1L, statisticsModel.getStatistics().get(invocation).getProperties().get("calls"),
                "only one call");
    }

}
