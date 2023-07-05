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
import kieker.model.analysismodel.execution.EDirection;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Invocation;
import kieker.model.analysismodel.execution.OperationDataflow;
import kieker.model.analysismodel.execution.StorageDataflow;
import kieker.model.analysismodel.execution.Tuple;

/**
 * @author Reiner Jung
 * @since 1.1.0
 */
public final class ExecutionModelMerger {

    private ExecutionModelMerger() {
        // Utility class
    }

    /* default */ static void mergeExecutionModel(final DeploymentModel deploymentModel, // NOPMD
            final ExecutionModel targetModel, final ExecutionModel mergeModel) {
        ExecutionModelMerger.mergeInvocations(deploymentModel, targetModel, mergeModel);
        ExecutionModelMerger.mergeStorageDataflows(deploymentModel, targetModel, mergeModel);
        ExecutionModelMerger.mergeOperationDataflows(deploymentModel, targetModel, mergeModel);
    }

    private static void mergeInvocations(final DeploymentModel deploymentModel, final ExecutionModel targetModel,
            final ExecutionModel mergeModel) {
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, Invocation> entry : mergeModel.getInvocations()) {
            if (!ExecutionModelMerger.compareTupleOperationKeys(targetModel.getInvocations(), entry.getKey())) {
                final Invocation value = ExecutionModelCloneUtils.duplicate(deploymentModel, entry.getValue());
                final Tuple<DeployedOperation, DeployedOperation> key = ExecutionFactory.eINSTANCE.createTuple();
                key.setFirst(value.getCaller());
                key.setSecond(value.getCallee());
                targetModel.getInvocations().put(key, value);
            }
        }
    }

    private static boolean compareTupleOperationKeys(
            final EMap<Tuple<DeployedOperation, DeployedOperation>, Invocation> invocations,
            final Tuple<DeployedOperation, DeployedOperation> key) {
        for (final Tuple<DeployedOperation, DeployedOperation> invocationKey : invocations.keySet()) {
            if (ModelUtils.isEqual(invocationKey.getFirst(), key.getFirst())
                    && ModelUtils.isEqual(invocationKey.getSecond(), key.getSecond())) {
                return true;
            }
        }
        return false;
    }

    private static void mergeStorageDataflows(final DeploymentModel deploymentModel, final ExecutionModel targetModel,
            final ExecutionModel mergeModel) {
        for (final Entry<Tuple<DeployedOperation, DeployedStorage>, StorageDataflow> entry : mergeModel
                .getStorageDataflows()) {
            final Tuple<DeployedOperation, DeployedStorage> targetModelKey = ExecutionModelMerger
                    .findTupleStorageKeys(targetModel.getStorageDataflows(), entry.getKey());
            if (targetModelKey == null) {
                final StorageDataflow value = ExecutionModelCloneUtils.duplicate(deploymentModel, entry.getValue());
                final Tuple<DeployedOperation, DeployedStorage> key = ExecutionFactory.eINSTANCE.createTuple();
                key.setFirst(value.getCode());
                key.setSecond(value.getStorage());
                targetModel.getStorageDataflows().put(key, value);
            } else {
                final StorageDataflow targetStorageDataflow = targetModel.getStorageDataflows().get(targetModelKey);
                final StorageDataflow sourceStorageDataflow = entry.getValue();
                switch (sourceStorageDataflow.getDirection()) {
                case READ:
                    if (targetStorageDataflow.getDirection() == EDirection.WRITE) {
                        targetStorageDataflow.setDirection(EDirection.BOTH);
                    }
                    break;
                case WRITE:
                    if (targetStorageDataflow.getDirection() == EDirection.READ) {
                        targetStorageDataflow.setDirection(EDirection.BOTH);
                    }
                    break;
                case BOTH:
                    targetStorageDataflow.setDirection(EDirection.BOTH);
                    break;
                default:
                    throw new InternalError(
                            "Found unsupported direction type " + sourceStorageDataflow.getDirection().getName());
                }
                targetModel.getStorageDataflows().put(targetModelKey, targetStorageDataflow);
            }
        }
    }

    private static Tuple<DeployedOperation, DeployedStorage> findTupleStorageKeys(
            final EMap<Tuple<DeployedOperation, DeployedStorage>, StorageDataflow> storageDataflows,
            final Tuple<DeployedOperation, DeployedStorage> key) {
        for (final Tuple<DeployedOperation, DeployedStorage> invocationKey : storageDataflows.keySet()) {
            if (ModelUtils.isEqual(invocationKey.getFirst(), key.getFirst())
                    && ModelUtils.isEqual(invocationKey.getSecond(), key.getSecond())) {
                return invocationKey;
            }
        }
        return null;
    }

    private static void mergeOperationDataflows(final DeploymentModel deploymentModel, final ExecutionModel targetModel,
            final ExecutionModel mergeModel) {
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, OperationDataflow> entry : mergeModel
                .getOperationDataflows()) {
            final Tuple<DeployedOperation, DeployedOperation> targetModelKey = ExecutionModelMerger
                    .findTupleOperationKeys(targetModel.getOperationDataflows(), entry.getKey());
            if (targetModelKey == null) {
                final OperationDataflow value = ExecutionModelCloneUtils.duplicate(deploymentModel, entry.getValue());
                final Tuple<DeployedOperation, DeployedOperation> key = ExecutionFactory.eINSTANCE.createTuple();
                key.setFirst(value.getCaller());
                key.setSecond(value.getCallee());
                targetModel.getOperationDataflows().put(key, value);
            } else {
                final OperationDataflow targetOperationDataflow = targetModel.getOperationDataflows()
                        .get(targetModelKey);
                final OperationDataflow sourceOperationDataflow = entry.getValue();
                switch (sourceOperationDataflow.getDirection()) {
                case READ:
                    if (targetOperationDataflow.getDirection() == EDirection.WRITE) {
                        targetOperationDataflow.setDirection(EDirection.BOTH);
                    }
                    break;
                case WRITE:
                    if (targetOperationDataflow.getDirection() == EDirection.READ) {
                        targetOperationDataflow.setDirection(EDirection.BOTH);
                    }
                    break;
                case BOTH:
                    targetOperationDataflow.setDirection(EDirection.BOTH);
                    break;
                default:
                    throw new InternalError(
                            "Found unsupported direction type " + sourceOperationDataflow.getDirection().getName());
                }
                targetModel.getOperationDataflows().put(targetModelKey, targetOperationDataflow);
            }
        }
    }

    private static Tuple<DeployedOperation, DeployedOperation> findTupleOperationKeys(
            final EMap<Tuple<DeployedOperation, DeployedOperation>, OperationDataflow> operationDataflows,
            final Tuple<DeployedOperation, DeployedOperation> tuple) {
        for (final Tuple<DeployedOperation, DeployedOperation> invocationKey : operationDataflows.keySet()) {
            if (ModelUtils.isEqual(invocationKey.getFirst(), tuple.getFirst())
                    && ModelUtils.isEqual(invocationKey.getSecond(), tuple.getSecond())) {
                return invocationKey;
            }
        }
        return null;
    }
}
