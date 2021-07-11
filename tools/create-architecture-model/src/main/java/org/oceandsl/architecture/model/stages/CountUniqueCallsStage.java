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

import java.util.Map.Entry;
import java.util.function.Function;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kieker.analysis.stage.model.data.OperationCallDurationEvent;
import kieker.analysis.statistics.StatisticsDecoratorStage;
import kieker.analysis.statistics.calculating.CountCalculator;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.statistics.EPredefinedUnits;
import kieker.model.analysismodel.statistics.StatisticsModel;

/**
 * Counts the number of unique operation calls and stores that information in the statistics model.
 * See {@link StatisticsDecoratorStage} for detail.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class CountUniqueCallsStage extends StatisticsDecoratorStage<OperationCallDurationEvent> {

    public CountUniqueCallsStage(final StatisticsModel statisticsModel, final ExecutionModel executionModel) {
        super(statisticsModel, EPredefinedUnits.RESPONSE_TIME, new CountCalculator<>(),
                CountUniqueCallsStage.createForAggregatedInvocation(executionModel));
    }

    public static final Function<OperationCallDurationEvent, EObject> createForAggregatedInvocation(
            final ExecutionModel executionModel) {
        return operationCall -> {
            final AggregatedInvocation result = CountUniqueCallsStage.getValue(executionModel,
                    executionModel.getAggregatedInvocations(), operationCall.getOperationCall());

            if (result == null) {
                final Logger logger = LoggerFactory.getLogger(CountUniqueCallsStage.class);
                logger.error("Fatal error: call not does not exist {}:{}",
                        operationCall.getOperationCall().getFirst().getAssemblyOperation().getOperationType()
                                .getSignature(),
                        operationCall.getOperationCall().getSecond().getAssemblyOperation().getOperationType()
                                .getSignature());
            }

            return result;
        };
    }

    private static AggregatedInvocation getValue(final ExecutionModel executionModel,
            final EMap<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> aggregatedInvocations,
            final Tuple<DeployedOperation, DeployedOperation> key) {
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> ag : executionModel
                .getAggregatedInvocations()) {
            if (ag.getKey().hashCode() == key.hashCode()) {
                return ag.getValue();
            }
        }
        return null;
    }
}
