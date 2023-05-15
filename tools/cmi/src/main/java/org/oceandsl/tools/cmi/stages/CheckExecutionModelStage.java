/***************************************************************************
 * Copyright (C) 2023 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.cmi.stages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.execution.Invocation;
import kieker.model.analysismodel.execution.OperationDataflow;
import kieker.model.analysismodel.execution.StorageDataflow;
import kieker.model.analysismodel.execution.Tuple;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.architecture.RepositoryUtils;

public class CheckExecutionModelStage extends AbstractTransformation<ModelRepository, ModelRepository> {

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        this.logger.info("Check execution model");

        final ExecutionModel executionModel = repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL);

        final long missingSignatures = GenericCheckUtils.missingSignature(executionModel.eAllContents(), this.logger);
        this.logger.info("Missing signatures in execution model {}", missingSignatures);
        final long missingReferences = GenericCheckUtils.checkReferences(ExecutionPackage.Literals.EXECUTION_MODEL,
                executionModel.eAllContents());
        this.logger.info("Missing references in execution model {}", missingReferences);

        this.checkExecutionInvocationIntegrity(executionModel);
        this.checkExecutionStorageDataflowIntegrity(executionModel);
        this.checkExecutionOperationDataflowIntegrity(executionModel);
        this.checkForDuplicateInvocations(executionModel);

        this.outputPort.send(repository);
    }

    private void checkExecutionInvocationIntegrity(final ExecutionModel model) {
        long errors = 0;
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, Invocation> entry : model.getInvocations()) {
            final Tuple<DeployedOperation, DeployedOperation> tuple = entry.getKey();
            final Invocation invocation = entry.getValue();
            if (tuple.getFirst() != invocation.getCaller()) {
                this.logger.info("Caller does not match lookup key {} ++ {}", RepositoryUtils.getName(tuple.getFirst()),
                        RepositoryUtils.getName(invocation.getCaller()));

                errors++;
            }
            if (tuple.getSecond() != invocation.getCallee()) {
                this.logger.info("Callee does not match lookup key {} ++ {}",
                        RepositoryUtils.getName(tuple.getSecond()), RepositoryUtils.getName(invocation.getCallee()));

                final DeployedComponent keyComponent = tuple.getSecond().getComponent();
                final DeployedComponent targetComponent = invocation.getCallee().getComponent();
                if (keyComponent != targetComponent) { // NOPMD objects must not be identical
                    this.logger.info("Callee component does not match lookup key component {} ++ {}",
                            RepositoryUtils.getName(keyComponent), RepositoryUtils.getName(targetComponent));
                    final DeploymentContext keyContext = keyComponent.getContext();
                    final DeploymentContext targetContext = targetComponent.getContext();
                    if (keyContext != targetContext) { // NOPMD objects must not be identical
                        this.logger.info("Callee context does not match lookup key context {} ++ {}",
                                RepositoryUtils.getName(keyContext), RepositoryUtils.getName(targetContext));
                        if (keyContext.eContainer() != targetContext.eContainer()) {
                            this.logger.info("Duplicate deployment models: {} ++ {}", keyContext.eResource().getURI(),
                                    targetContext.eResource().getURI());
                        }
                    }
                }

                errors++;
            }
        }
        this.logger.info("Number of errors in execution model invocations {}", errors); // NOPMD
    }

    private void checkExecutionOperationDataflowIntegrity(final ExecutionModel model) {
        long errors = 0;
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, OperationDataflow> entry : model
                .getOperationDataflows()) {
            final Tuple<DeployedOperation, DeployedOperation> tuple = entry.getKey();
            final OperationDataflow operationDataflow = entry.getValue();
            if (tuple.getFirst() != operationDataflow.getCaller()) {
                this.logger.info("Caller does not match {}:{}", // NOPMD
                        RepositoryUtils.getName(operationDataflow.getCaller().getComponent()),
                        RepositoryUtils.getName(operationDataflow.getCaller()));
                errors++;
            }
            if (tuple.getSecond() != operationDataflow.getCallee()) {
                this.logger.info("Storage does not match {}:{}",
                        RepositoryUtils.getName(operationDataflow.getCallee().getComponent()),
                        RepositoryUtils.getName(operationDataflow.getCallee()));
                errors++;
            }
        }
        this.logger.info("Number of errors in execution model operation dataflows {}", errors); // NOPMD
    }

    private void checkExecutionStorageDataflowIntegrity(final ExecutionModel model) {
        long errors = 0;
        for (final Entry<Tuple<DeployedOperation, DeployedStorage>, StorageDataflow> entry : model
                .getStorageDataflows()) {
            final Tuple<DeployedOperation, DeployedStorage> tuple = entry.getKey();
            final StorageDataflow storageDataflow = entry.getValue();
            if (tuple.getFirst() != storageDataflow.getCode()) {
                this.logger.info("Caller does not match {}:{}\n", // NOPMD
                        RepositoryUtils.getName(storageDataflow.getCode().getComponent()),
                        RepositoryUtils.getName(storageDataflow.getCode()));
                errors++;
            }
            if (tuple.getSecond() != storageDataflow.getStorage()) {
                this.logger.info("Storage does not match {}:{}\n",
                        RepositoryUtils.getName(storageDataflow.getStorage().getComponent()),
                        RepositoryUtils.getName(storageDataflow.getStorage()));
                errors++;
            }
        }
        this.logger.info("Number of errors in execution model storage dataflows {}", errors);
    }

    private void checkForDuplicateInvocations(final ExecutionModel model) {
        this.logger.info("Check for duplicate invocations based on DeployedOperation");
        final Map<DeployedOperation, Map<DeployedOperation, Invocation>> map = new HashMap<>();
        for (final Invocation invocation : model.getInvocations().values()) {
            Map<DeployedOperation, Invocation> targetMap = map.get(invocation.getCaller());
            if (targetMap == null) {
                targetMap = new HashMap<>();
                targetMap.put(invocation.getCallee(), invocation);
            } else if (targetMap.get(invocation.getCallee()) != null) {
                this.logger.info("Found duplicate {} -> {}",
                        invocation.getCaller().getAssemblyOperation().getOperationType().getName(),
                        invocation.getCallee().getAssemblyOperation().getOperationType().getName());
            }
        }

        this.logger.info("Check for duplicate invocations based on DeployedOperation names");
        final List<String> l = new ArrayList<>();
        for (final Invocation invocation : model.getInvocations().values()) {
            final String m = String.format("%s:%s:%s -> %s:%s:%s",
                    invocation.getCaller().getComponent().getContext().getName(),
                    invocation.getCaller().getComponent().getAssemblyComponent().getSignature(),
                    invocation.getCaller().getAssemblyOperation().getOperationType().getSignature(),
                    invocation.getCallee().getComponent().getContext().getName(),
                    invocation.getCallee().getComponent().getAssemblyComponent().getSignature(),
                    invocation.getCallee().getAssemblyOperation().getOperationType().getSignature());
            boolean g = false;
            for (final String x : l) {
                if (x.equals(m)) {
                    this.logger.info("Found duplicate {}", m); // NOPMD
                    g = true;
                }
            }
            if (!g) {
                l.add(m);
            }
        }
    }

}
