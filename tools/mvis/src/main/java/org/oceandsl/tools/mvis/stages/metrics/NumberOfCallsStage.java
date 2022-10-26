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
package org.oceandsl.tools.mvis.stages.metrics;

import java.util.Map.Entry;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import kieker.analysis.architecture.dependency.PropertyConstants;
import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.execution.Invocation;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.statistics.StatisticRecord;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.statistics.StatisticsPackage;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.architecture.RepositoryUtils;
import org.oceandsl.analysis.code.stages.data.LongValueHandler;
import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class NumberOfCallsStage extends AbstractTransformation<ModelRepository, Table> {

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        final ExecutionModel executionModel = repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL);
        final StatisticsModel statisticsModel = repository.getModel(StatisticsPackage.Literals.STATISTICS_MODEL);

        final Table result = new Table(repository.getName(), new StringValueHandler("source-file"),
                new StringValueHandler("source-function"), new StringValueHandler("target-file"),
                new StringValueHandler("target-function"), new LongValueHandler("calls"));

        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, Invocation> invocationEntry : executionModel
                .getInvocations().entrySet()) {
            final Invocation value = invocationEntry.getValue();
            if (value == null) {
                this.logger.error("Broken invocation entry. {} -> {}",
                        RepositoryUtils.getName(invocationEntry.getKey().getFirst()),
                        RepositoryUtils.getName(invocationEntry.getKey().getSecond()));
            }
            final StatisticRecord statistics = this.findStatistics(statisticsModel.getStatistics(), value);
            if (statistics != null) {
                final Long data = (Long) statistics.getProperties().get(PropertyConstants.CALLS);

                result.addRow(value.getCaller().getAssemblyOperation().getComponent().getComponentType().getSignature(),
                        value.getCaller().getAssemblyOperation().getOperationType().getSignature(),
                        value.getCallee().getAssemblyOperation().getComponent().getComponentType().getSignature(),
                        value.getCallee().getAssemblyOperation().getOperationType().getSignature(), data);
            } else {
                this.logger.error("Missing statistics for invocation {} -> {}",
                        RepositoryUtils.getName(invocationEntry.getValue().getCaller()),
                        RepositoryUtils.getName(invocationEntry.getValue().getCallee()));
            }
        }

        this.outputPort.send(result);
    }

    private StatisticRecord findStatistics(final EMap<EObject, StatisticRecord> statistics,
            final Invocation invocation) {
        for (final Entry<EObject, StatisticRecord> entry : statistics.entrySet()) {
            if (entry.getKey() instanceof Invocation) {
                final Invocation key = (Invocation) entry.getKey();
                if (key != null) {
                    if (invocation.getCaller().equals(key.getCaller())
                            && invocation.getCallee().equals(key.getCallee())) {
                        return entry.getValue();
                    }
                } else {
                    this.logger.error("Found statistics without a key value");
                    for (final Entry<String, Object> recordEntry : entry.getValue().getProperties().entrySet()) {
                        this.logger.error("property {} = {}", recordEntry.getKey(), recordEntry.getValue());
                    }
                }
            }
        }
        return null;
    }
}
