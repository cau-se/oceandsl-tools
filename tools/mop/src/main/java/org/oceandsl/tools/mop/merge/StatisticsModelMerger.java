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

import java.util.Map.Entry;

import org.apache.commons.lang3.NotImplementedException;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.AggregatedStorageAccess;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.statistics.EPredefinedUnits;
import kieker.model.analysismodel.statistics.EPropertyType;
import kieker.model.analysismodel.statistics.StatisticRecord;
import kieker.model.analysismodel.statistics.Statistics;
import kieker.model.analysismodel.statistics.StatisticsModel;

/**
 * @author Reiner Jung
 * @since
 */
public final class StatisticsModelMerger {

    private final static Logger LOGGER = LoggerFactory.getLogger(StatisticsModelMerger.class);

    private StatisticsModelMerger() {
        // Utility class
    }

    /* default */ static void mergeStatisticsModel(final ExecutionModel executionModel, // NOPMD
            final StatisticsModel targetModel, final StatisticsModel mergeModel) {
        for (final Entry<EObject, Statistics> mergeStatistic : mergeModel.getStatistics()) {
            /*
             * Unfortunately the map function containsKey does not match the correct keys, as the
             * hash value is computed also over references which belong to another model. Thus, the
             * containsKey always fails.
             */
            final EObject targetStatisticKey = StatisticsModelMerger.findKey(targetModel.getStatistics(),
                    mergeStatistic.getKey());
            if (targetStatisticKey == null) {
                targetModel.getStatistics().put(
                        StatisticsModelMerger.createExecutionModelKey(executionModel, mergeStatistic.getKey()),
                        StatisticsModelCloneUtils.duplicate(mergeStatistic.getValue()));
            } else {
                final Statistics newStatistic = targetModel.getStatistics().get(targetStatisticKey);
                for (final Entry<EPredefinedUnits, StatisticRecord> statisticRecord : mergeStatistic.getValue()
                        .getStatistics()) {
                    if (!newStatistic.getStatistics().containsKey(statisticRecord.getKey())) {
                        newStatistic.getStatistics().put(statisticRecord.getKey(),
                                StatisticsModelCloneUtils.duplicate(statisticRecord.getValue()));
                    } else {
                        final StatisticRecord newRecord = newStatistic.getStatistics().get(statisticRecord.getKey());
                        for (final Entry<EPropertyType, Object> property : statisticRecord.getValue().getProperties()) {
                            if (!newRecord.getProperties().containsKey(property.getKey())) {
                                newRecord.getProperties().put(property.getKey(),
                                        StatisticsModelCloneUtils.duplicateObject(property.getValue()));
                            } else {
                                final Object newValue = StatisticsModelCloneUtils
                                        .compute(newRecord.getProperties().get(property.getKey()), property.getValue());
                                newRecord.getProperties().put(property.getKey(), newValue);
                            }
                        }
                    }
                }
            }
        }
    }

    private static EObject createExecutionModelKey(final ExecutionModel executionModel, final EObject key) {
        if (key instanceof AggregatedInvocation) {
            for (final AggregatedInvocation invocation : executionModel.getAggregatedInvocations().values()) {
                if (ModelUtils.areObjectsEqual(key, invocation)) {
                    return invocation;
                }
            }
            StatisticsModelMerger.LOGGER.error("Missing correpsonding {} in the merged model.", key.getClass());
            throw new InternalError(String.format("Missing correpsonding %s in the merged model.", key.getClass()));
        } else if (key instanceof AggregatedStorageAccess) {
            for (final AggregatedStorageAccess storageAccess : executionModel.getAggregatedStorageAccesses().values()) {
                final AggregatedStorageAccess searchForStorageAccess = (AggregatedStorageAccess) key;
                if (ModelUtils.areObjectsEqual(searchForStorageAccess, storageAccess)) {
                    return storageAccess;
                }
            }
            StatisticsModelMerger.LOGGER.error("Missing correpsonding {} in the merged model.", key.getClass());
            throw new InternalError(String.format("Missing correpsonding %s in the merged model.", key.getClass()));
        } else {
            StatisticsModelMerger.LOGGER.error("Statistics related to {} objects are not yet supported.",
                    key.getClass());
            throw new NotImplementedException(
                    String.format("Statistics related to %s objects are not yet supported.", key.getClass()));
        }
    }

    private static EObject findKey(final EMap<EObject, Statistics> statistics, final EObject key) {
        for (final EObject mapkey : statistics.keySet()) {
            if (ModelUtils.areObjectsEqual(mapkey, key)) {
                return mapkey;
            }
        }
        return null;
    }
}
