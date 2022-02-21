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

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.statistics.EPredefinedUnits;
import kieker.model.analysismodel.statistics.EPropertyType;
import kieker.model.analysismodel.statistics.StatisticRecord;
import kieker.model.analysismodel.statistics.Statistics;
import kieker.model.analysismodel.statistics.StatisticsModel;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.architecture.model.RepositoryUtils;
import org.oceandsl.analysis.stages.staticdata.data.LongValueHandler;
import org.oceandsl.analysis.stages.staticdata.data.StringValueHandler;
import org.oceandsl.analysis.stages.staticdata.data.Table;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class NumberOfCallsStage extends AbstractTransformation<ModelRepository, Table> {

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        final ExecutionModel executionModel = (ExecutionModel) repository.getModels().get(ExecutionModel.class);
        final StatisticsModel statisticsModel = (StatisticsModel) repository.getModels().get(StatisticsModel.class);

        final Table result = new Table(repository.getName(), new StringValueHandler("source-file"),
                new StringValueHandler("source-function"), new StringValueHandler("target-file"),
                new StringValueHandler("target-function"), new LongValueHandler("calls"));

        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> invocationEntry : executionModel
                .getAggregatedInvocations().entrySet()) {
            final AggregatedInvocation value = invocationEntry.getValue();
            if (value == null) {
                this.logger.error("Broken invocation entry. {} -> {}",
                        RepositoryUtils.getName(invocationEntry.getKey().getFirst()),
                        RepositoryUtils.getName(invocationEntry.getKey().getSecond()));
            }
            final Statistics statistics = this.findStatistics(statisticsModel.getStatistics(), value);
            if (statistics != null) {
                final Long data = (Long) statistics.getStatistics().get(EPredefinedUnits.INVOCATION).getProperties()
                        .get(EPropertyType.COUNT);

                result.addRow(
                        value.getSource().getAssemblyOperation().getAssemblyComponent().getComponentType()
                                .getSignature(),
                        value.getSource().getAssemblyOperation().getOperationType().getSignature(),
                        value.getTarget().getAssemblyOperation().getAssemblyComponent().getComponentType()
                                .getSignature(),
                        value.getTarget().getAssemblyOperation().getOperationType().getSignature(), data);
            } else {
                this.logger.error("Missing statistics for invocation {} -> {}",
                        RepositoryUtils.getName(invocationEntry.getValue().getSource()),
                        RepositoryUtils.getName(invocationEntry.getValue().getTarget()));
            }
        }

        this.outputPort.send(result);
    }

    private Statistics findStatistics(final EMap<EObject, Statistics> statistics,
            final AggregatedInvocation invocation) {
        for (final Entry<EObject, Statistics> entry : statistics.entrySet()) {
            if (entry.getKey() instanceof AggregatedInvocation) {
                final AggregatedInvocation key = (AggregatedInvocation) entry.getKey();
                if (key != null) {
                    if (invocation.getSource().equals(key.getSource())
                            && invocation.getTarget().equals(key.getTarget())) {
                        return entry.getValue();
                    }
                } else {
                    this.logger.error("Found statistics without a key value");
                    for (final Entry<EObject, Statistics> stats : statistics.entrySet()) {
                        final String kex = stats.getKey() != null ? stats.getKey().toString() : "NO-KEY";
                        this.logger.info("  Key {}", kex);
                        for (final Entry<EPredefinedUnits, StatisticRecord> stat : stats.getValue().getStatistics()
                                .entrySet()) {
                            this.logger.info("    {} = {}", kex, stat.getValue().getProperties().toString());
                        }
                    }
                }
            }
        }
        return null;
    }
}
