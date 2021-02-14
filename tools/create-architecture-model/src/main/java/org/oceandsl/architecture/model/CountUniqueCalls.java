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
package org.oceandsl.architecture.model;

import java.util.function.Function;

import kieker.analysis.statistics.StatisticsDecoratorStage;
import kieker.analysis.statistics.StatisticsModel;
import kieker.analysis.statistics.Units;
import kieker.analysis.statistics.calculating.CountCalculator;
import kieker.analysis.util.ComposedKey;
import kieker.analysisteetime.model.analysismodel.deployment.DeployedOperation;
import kieker.analysisteetime.model.analysismodel.execution.ExecutionModel;

/**
 * Counts the number of unique operation calles and stores that information in the statistics model.
 * See {@link StatisticsDecoratorStage} for detail.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class CountUniqueCalls extends StatisticsDecoratorStage<OperationCall> {

    public CountUniqueCalls(final StatisticsModel statisticsModel, final ExecutionModel executionModel) {
        super(statisticsModel, Units.RESPONSE_TIME, new CountCalculator<>(),
                CountUniqueCalls.createForAggregatedInvocation(executionModel));
    }

    public static final Function<OperationCall, Object> createForAggregatedInvocation(
            final ExecutionModel executionModel) {
        return operationCall -> {
            final ComposedKey<DeployedOperation, DeployedOperation> key = ComposedKey
                    .of(operationCall.getSourceOperation(), operationCall.getTargetOperation());
            return executionModel.getAggregatedInvocations().get(key);
        };
    }

}
