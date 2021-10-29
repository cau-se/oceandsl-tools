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
package org.oceandsl.tools.sar.stages.dataflow;

import java.util.Map.Entry;
import java.util.function.Function;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.oceandsl.analysis.stages.model.CountUniqueCallsStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kieker.analysis.statistics.StatisticsDecoratorStage;
import kieker.analysis.statistics.calculating.CountCalculator;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.execution.AggregatedStorageAccess;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.statistics.EPredefinedUnits;
import kieker.model.analysismodel.statistics.StatisticsModel;

/**
 * @author Reiner Jung
 *
 */
public class CountUniqueDataflowCallsStage extends StatisticsDecoratorStage<DataAccess> {

    public CountUniqueDataflowCallsStage(final StatisticsModel statisticsModel, final ExecutionModel executionModel) {
        super(statisticsModel, EPredefinedUnits.INVOCATION, new CountCalculator<>(),
                CountUniqueDataflowCallsStage.createForAggregatedStorageAccess(executionModel));
    }

    public static final Function<DataAccess, EObject> createForAggregatedStorageAccess(
            final ExecutionModel executionModel) {
        return dataAccess -> {
            final AggregatedStorageAccess result = CountUniqueDataflowCallsStage.getValue(executionModel,
                    executionModel.getAggregatedStorageAccesses(),
                    CountUniqueDataflowCallsStage.getKeyTuple(dataAccess, executionModel));

            if (result == null) {
                final Logger logger = LoggerFactory.getLogger(CountUniqueCallsStage.class);
                logger.error("Fatal error: call not does not exist {}:{}", dataAccess.getOperation(),
                        dataAccess.getSharedData());
            }

            return result;
        };
    }

    private static Tuple<DeployedOperation, DeployedStorage> getKeyTuple(final DataAccess dataAccess,
            final ExecutionModel executionModel) {
        for (final Entry<Tuple<DeployedOperation, DeployedStorage>, AggregatedStorageAccess> entry : executionModel
                .getAggregatedStorageAccesses().entrySet()) {
            final Tuple<DeployedOperation, DeployedStorage> key = entry.getKey();
            if (key.getFirst().getAssemblyOperation().getOperationType().getName().equals(dataAccess.getOperation())
                    && key.getFirst().getComponent().getAssemblyComponent().getSignature()
                            .equals(dataAccess.getModule())
                    && key.getSecond().getAssemblyStorage().getStorageType().getName()
                            .equals(dataAccess.getSharedData())) {
                return key;
            }
        }
        return null;
    }

    private static AggregatedStorageAccess getValue(final ExecutionModel executionModel,
            final EMap<Tuple<DeployedOperation, DeployedStorage>, AggregatedStorageAccess> aggregatedSorageAccesses,
            final Tuple<DeployedOperation, DeployedStorage> key) {
        for (final Entry<Tuple<DeployedOperation, DeployedStorage>, AggregatedStorageAccess> ag : executionModel
                .getAggregatedStorageAccesses()) {
            if (ag.getKey().hashCode() == key.hashCode()) {
                return ag.getValue();
            }
        }
        return null;
    }
}
