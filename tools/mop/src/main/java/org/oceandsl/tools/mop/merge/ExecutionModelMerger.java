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

import org.eclipse.emf.common.util.EMap;

import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.AggregatedStorageAccess;
import kieker.model.analysismodel.execution.EDirection;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;

/**
 * @author Reiner Jung
 * @since
 */
public final class ExecutionModelMerger {

    private ExecutionModelMerger() {
        // Utility class
    }

    /* default */ static void mergeExecutionModel(final DeploymentModel deploymentModel, // NOPMD
            final ExecutionModel targetModel, final ExecutionModel mergeModel) {
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> entry : mergeModel
                .getAggregatedInvocations()) {
            if (!ExecutionModelMerger.compareTupleOperationKeys(targetModel.getAggregatedInvocations(),
                    entry.getKey())) {
                final AggregatedInvocation value = ExecutionModelCloneUtils.duplicate(deploymentModel,
                        entry.getValue());
                final Tuple<DeployedOperation, DeployedOperation> key = ExecutionFactory.eINSTANCE.createTuple();
                key.setFirst(value.getSource());
                key.setSecond(value.getTarget());
                targetModel.getAggregatedInvocations().put(key, value);
            }
        }
        for (final Entry<Tuple<DeployedOperation, DeployedStorage>, AggregatedStorageAccess> entry : mergeModel
                .getAggregatedStorageAccesses()) {
            final Tuple<DeployedOperation, DeployedStorage> targetModelKey = ExecutionModelMerger
                    .findTupleStorageKeys(targetModel.getAggregatedStorageAccesses(), entry.getKey());
            if (targetModelKey == null) {
                final AggregatedStorageAccess value = ExecutionModelCloneUtils.duplicate(deploymentModel,
                        entry.getValue());
                final Tuple<DeployedOperation, DeployedStorage> key = ExecutionFactory.eINSTANCE.createTuple();
                key.setFirst(value.getCode());
                key.setSecond(value.getStorage());
                targetModel.getAggregatedStorageAccesses().put(key, value);
            } else {
                final AggregatedStorageAccess targetStorageAccess = targetModel.getAggregatedStorageAccesses()
                        .get(targetModelKey);
                final AggregatedStorageAccess sourceStorageAccess = entry.getValue();
                switch (sourceStorageAccess.getDirection()) {
                case READ:
                    if (targetStorageAccess.getDirection() == EDirection.WRITE) {
                        targetStorageAccess.setDirection(EDirection.BOTH);
                    }
                    break;
                case WRITE:
                    if (targetStorageAccess.getDirection() == EDirection.READ) {
                        targetStorageAccess.setDirection(EDirection.BOTH);
                    }
                    break;
                case BOTH:
                    targetStorageAccess.setDirection(EDirection.BOTH);
                    break;
                default:
                    throw new InternalError(
                            "Found unsupported direction type " + sourceStorageAccess.getDirection().getName());
                }
                targetModel.getAggregatedStorageAccesses().put(targetModelKey, targetStorageAccess);
            }
        }

    }

    private static boolean compareTupleOperationKeys(
            final EMap<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> aggregatedInvocations,
            final Tuple<DeployedOperation, DeployedOperation> key) {
        for (final Tuple<DeployedOperation, DeployedOperation> invocationKey : aggregatedInvocations.keySet()) {
            if (ModelUtils.isEqual(invocationKey.getFirst(), key.getFirst())
                    && ModelUtils.isEqual(invocationKey.getSecond(), key.getSecond())) {
                return true;
            }
        }
        return false;
    }

    private static Tuple<DeployedOperation, DeployedStorage> findTupleStorageKeys(
            final EMap<Tuple<DeployedOperation, DeployedStorage>, AggregatedStorageAccess> aggregatedStorageAccesses,
            final Tuple<DeployedOperation, DeployedStorage> key) {
        for (final Tuple<DeployedOperation, DeployedStorage> invocationKey : aggregatedStorageAccesses.keySet()) {
            if (ModelUtils.isEqual(invocationKey.getFirst(), key.getFirst())
                    && ModelUtils.isEqual(invocationKey.getSecond(), key.getSecond())) {
                return invocationKey;
            }
        }
        return null;
    }
}
