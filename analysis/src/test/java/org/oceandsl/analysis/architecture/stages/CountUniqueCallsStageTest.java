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

import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import kieker.analysis.architecture.recovery.events.DeployedOperationCallEvent;
import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.statistics.StatisticsPackage;

import teetime.framework.test.StageTester;

import org.oceandsl.analysis.architecture.BasicArchitectureModelUtils;

/**
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
class CountUniqueCallsStageTest {

    @Test
    public void test() {
        final ModelRepository repository = BasicArchitectureModelUtils.createMinimalModelRepository();

        final StatisticsModel statisticsModel = repository.getModel(StatisticsPackage.Literals.STATISTICS_MODEL);
        final ExecutionModel executionModel = repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL);
        final ExecutionModel deployedModel = repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL);

        final CountUniqueCallsStage stage = new CountUniqueCallsStage(statisticsModel, executionModel);

        final Duration duration = Duration.of(10, ChronoUnit.SECONDS);
        final Tuple<DeployedOperation, DeployedOperation> tuple = ExecutionFactory.eINSTANCE.createTuple();

        final DeployedOperationCallEvent event = new DeployedOperationCallEvent(tuple, duration);

        StageTester.test(stage).send(event).to(stage.getInputPort()).start();
        assertThat(stage.getOutputPort(), StageTester.produces(event));
    }

}
