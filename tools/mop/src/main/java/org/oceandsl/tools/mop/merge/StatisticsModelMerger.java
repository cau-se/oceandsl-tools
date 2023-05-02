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
import java.util.Optional;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.execution.EDirection;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Invocation;
import kieker.model.analysismodel.execution.OperationDataflow;
import kieker.model.analysismodel.execution.StorageDataflow;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.statistics.StatisticRecord;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.StorageType;

/**
 * Merge two statistics models.
 *
 * @author Reiner Jung
 * @since 1.3
 */
public final class StatisticsModelMerger {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsModelMerger.class);

    private StatisticsModelMerger() {
        // Utility class
    }

    /* default */ static void mergeStatisticsModel(final ExecutionModel executionModel, // NOPMD
            final StatisticsModel targetModel, final StatisticsModel mergeModel) {
        for (final Entry<EObject, StatisticRecord> mergeStatisticEntry : mergeModel.getStatistics()) {
            final StatisticRecord targetStatisticRecord = StatisticsModelMerger.findMatchingStatisticRecord(
                    executionModel, targetModel.getStatistics(), mergeStatisticEntry.getKey());

            if (targetStatisticRecord == null) {
                // add additional statistics if they are missing from the target model
                final StatisticRecord duplicateStatisticRecord = StatisticsModelCloneUtils
                        .duplicate(mergeStatisticEntry.getValue());
                final Optional<? extends EObject> edgeOptional = StatisticsModelMerger.findMatchingEdge(executionModel,
                        mergeStatisticEntry.getKey());
                if (edgeOptional.isPresent()) {
                    targetModel.getStatistics().put(edgeOptional.get(), duplicateStatisticRecord);
                }
            } else {
                // merge statistics
                StatisticsModelMerger.mergeStatisticRecord(targetStatisticRecord, mergeStatisticEntry.getValue());
            }
        }
    }

    private static void mergeStatisticRecord(final StatisticRecord targetStatisticRecord,
            final StatisticRecord mergeStatisticRecord) {
        mergeStatisticRecord.getProperties().entrySet().forEach(entry -> {
            final Object targetPropertyValue = targetStatisticRecord.getProperties().get(entry.getKey());
            if (targetPropertyValue != null) {
                targetStatisticRecord.getProperties().put(entry.getKey(),
                        StatisticsModelMerger.mergeValues(targetPropertyValue, entry.getValue()));
            }
        });
    }

    private static Object mergeValues(final Object targetValue, final Object value) {
        if (targetValue instanceof Boolean) {
            return (Boolean) targetValue || (Boolean) value;
        } else if (targetValue instanceof Byte) {
            return (Byte) targetValue + (Byte) value;
        } else if (targetValue instanceof Short) {
            return (Short) targetValue + (Short) value;
        } else if (targetValue instanceof Character) {
            return (Character) targetValue + (Character) value;
        } else if (targetValue instanceof Integer) {
            return (Integer) targetValue + (Integer) value;
        } else if (targetValue instanceof Long) {
            return (Long) targetValue + (Long) value;
        } else if (targetValue instanceof Float) {
            return (Float) targetValue + (Float) value;
        } else if (targetValue instanceof Double) {
            return (Double) targetValue + (Double) value;
        } else if (targetValue instanceof String) {
            return (String) targetValue + (String) value;
        } else {
            StatisticsModelMerger.LOGGER.warn("Statistic values of type {} cannot be merged.",
                    targetValue.getClass().getCanonicalName());
            return null;
        }
    }

    private static StatisticRecord findMatchingStatisticRecord(final ExecutionModel executionModel,
            final EMap<EObject, StatisticRecord> targetStatistics, final EObject key) {
        final Optional<? extends EObject> edgeOptional = StatisticsModelMerger.findMatchingEdge(executionModel, key);
        if (edgeOptional.isPresent()) {
            return targetStatistics.get(edgeOptional.get());
        } else {
            return null;
        }
    }

    private static Optional<? extends EObject> findMatchingEdge(final ExecutionModel executionModel,
            final EObject object) {
        if (object instanceof Invocation) {
            return StatisticsModelMerger.findMatchingInvocation(executionModel.getInvocations(), (Invocation) object);
        } else if (object instanceof OperationDataflow) {
            return StatisticsModelMerger.findMatchingOperationDataflow(executionModel.getOperationDataflows(),
                    (OperationDataflow) object);
        } else if (object instanceof StorageDataflow) {
            return StatisticsModelMerger.findMatchingStorageDataflow(executionModel.getStorageDataflows(),
                    (StorageDataflow) object);
        } else {
            StatisticsModelMerger.LOGGER.warn("Edge type {} not supported by statistics merger.",
                    object.getClass().getCanonicalName());
            return Optional.empty();
        }
    }

    private static Optional<Invocation> findMatchingInvocation(
            final EMap<Tuple<DeployedOperation, DeployedOperation>, Invocation> invocations,
            final Invocation invocation) {
        return invocations.values().stream()
                .filter(targetInvocation -> StatisticsModelMerger.isIdenticalInvocation(targetInvocation, invocation))
                .findFirst();
    }

    private static boolean isIdenticalInvocation(final Invocation left, final Invocation right) {
        return StatisticsModelMerger.isIdenticalOperation(left.getCaller(), right.getCaller())
                && StatisticsModelMerger.isIdenticalOperation(left.getCallee(), right.getCallee());
    }

    private static Optional<OperationDataflow> findMatchingOperationDataflow(
            final EMap<Tuple<DeployedOperation, DeployedOperation>, OperationDataflow> operationDataflows,
            final OperationDataflow dataflow) {
        return operationDataflows.values().stream()
                .filter(targetDataflow -> StatisticsModelMerger.isIdenticalOperationDataflow(targetDataflow, dataflow))
                .findFirst();
    }

    private static boolean isIdenticalOperationDataflow(final OperationDataflow targetDataflow,
            final OperationDataflow dataflow) {
        return StatisticsModelMerger.isIdenticalOperation(targetDataflow.getCaller(), dataflow.getCaller())
                && StatisticsModelMerger.isIdenticalOperation(targetDataflow.getCallee(), dataflow.getCallee())
                && StatisticsModelMerger.isIdenticalDirection(targetDataflow.getDirection(), dataflow.getDirection());
    }

    private static Optional<StorageDataflow> findMatchingStorageDataflow(
            final EMap<Tuple<DeployedOperation, DeployedStorage>, StorageDataflow> storageDataflows,
            final StorageDataflow dataflow) {
        return storageDataflows.values().stream()
                .filter(targetDataflow -> StatisticsModelMerger.isIdenticalStorageDataflow(targetDataflow, dataflow))
                .findFirst();
    }

    private static boolean isIdenticalStorageDataflow(final StorageDataflow targetDataflow,
            final StorageDataflow dataflow) {
        return StatisticsModelMerger.isIdenticalOperation(targetDataflow.getCode(), dataflow.getCode())
                && StatisticsModelMerger.isIdenticalStorage(targetDataflow.getStorage(), dataflow.getStorage())
                && StatisticsModelMerger.isIdenticalDirection(targetDataflow.getDirection(), dataflow.getDirection());
    }

    private static boolean isIdenticalOperation(final DeployedOperation left, final DeployedOperation right) {
        if (left.getAssemblyOperation().getOperationType().getSignature()
                .equals(right.getAssemblyOperation().getOperationType().getSignature())) {
            return left.getComponent().getAssemblyComponent().getSignature()
                    .equals(right.getComponent().getAssemblyComponent().getSignature());
        } else {
            return false;
        }
    }

    private static boolean isIdenticalStorage(final DeployedStorage left, final DeployedStorage right) {
        final StorageType leftStorage = left.getAssemblyStorage().getStorageType();
        final StorageType rightStorage = right.getAssemblyStorage().getStorageType();

        if (leftStorage == null) {
            LOGGER.error("Left storage: Missing reference to storage type.", left);
        }
        if (leftStorage.getName() == null) {
            LOGGER.error("Left storage type has no name.", left);
        }
        if (leftStorage.getType() == null) {
            LOGGER.error("Left storage type {} has no type.", leftStorage.getName());
        }

        if (rightStorage == null) {
            LOGGER.error("Right storage: Missing reference to storage type.", right);
        }
        if (rightStorage.getName() == null) {
            LOGGER.error("Right storage type has no name.", right);
        }
        if (rightStorage.getType() == null) {
            LOGGER.error("Right storage type {} has no type.", rightStorage.getName());
        }

        if (leftStorage.getName().equals(rightStorage.getName())
                && leftStorage.getType().equals(rightStorage.getType())) {
            return left.getComponent().getAssemblyComponent().getSignature()
                    .equals(right.getComponent().getAssemblyComponent().getSignature());
        } else {
            return false;
        }
    }

    private static boolean isIdenticalDirection(final EDirection left, final EDirection right) {
        if (left.equals(right)) {
            return true;
        } else {
            return left == EDirection.BOTH;
        }
    }

}
