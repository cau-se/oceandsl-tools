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
package org.oceandsl.architecture.model.stages;

import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.oceandsl.architecture.model.stages.data.OperationCall;

import kieker.analysis.statistics.StatisticsDecoratorStage;
import kieker.analysis.statistics.calculating.CountCalculator;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.statistics.EPredefinedUnits;
import kieker.model.analysismodel.statistics.StatisticsModel;

/**
 * Counts the number of unique operation calles and stores that information in the statistics model.
 * See {@link StatisticsDecoratorStage} for detail.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class CountUniqueCallsStage extends StatisticsDecoratorStage<OperationCall> {

    public CountUniqueCallsStage(final StatisticsModel statisticsModel, final ExecutionModel executionModel) {
        super(statisticsModel, EPredefinedUnits.RESPONSE_TIME, new CountCalculator<>(),
                CountUniqueCallsStage.createForAggregatedInvocation(executionModel));
    }

    public static final Function<OperationCall, EObject> createForAggregatedInvocation(
            final ExecutionModel executionModel) {
        return operationCall -> {
            final Tuple<DeployedOperation, DeployedOperation> key = ExecutionFactory.eINSTANCE.createTuple();
            key.setFirst(operationCall.getSourceOperation());
            key.setSecond(operationCall.getTargetOperation());
            return executionModel.getAggregatedInvocations().get(key);
        };
    }

}
