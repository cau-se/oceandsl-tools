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
package org.oceandsl.tools.mvis.graph;

import java.time.temporal.ChronoUnit;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import kieker.analysis.architecture.dependency.AbstractDependencyGraphBuilder;
import kieker.analysis.architecture.dependency.ResponseTimeDecorator;
import kieker.analysis.architecture.repository.ModelRepository;
import kieker.analysis.generic.graph.GraphFactory;
import kieker.analysis.generic.graph.IGraph;
import kieker.analysis.generic.graph.INode;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.AggregatedStorageAccess;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.statistics.EPredefinedUnits;
import kieker.model.analysismodel.statistics.EPropertyType;
import kieker.model.analysismodel.statistics.StatisticRecord;
import kieker.model.analysismodel.statistics.Statistics;
import kieker.model.analysismodel.statistics.StatisticsModel;

import org.oceandsl.analysis.graph.IGraphElementSelector;

/**
 * @author Reiner Jung
 * @since 1.0
 *
 */
public abstract class AbstractColorDependencyGraphBuilder extends AbstractDependencyGraphBuilder {

    private SourceModel sourcesModel;
    private final IGraphElementSelector selector;

    public AbstractColorDependencyGraphBuilder(final IGraphElementSelector selector) {
        super();
        this.selector = selector;
    }

    protected String selectForegroundColor(final EObject object) {
        final EList<String> sources = this.sourcesModel.getSources().get(object);
        if (sources != null) {
            if (this.selector.isColorGroup(sources, 0)) {
                return "#000000";
            } else if (this.selector.isColorGroup(sources, 1)) {
                return "#0000ff"; // blue for static
            } else if (this.selector.isColorGroup(sources, 2)) {
                return "#00ff00"; // green on dynamic
            } else {
                return "#fafafa"; // other objects
            }
        } else {
            return "#ff00ff"; // pink on error
        }
    }

    protected String selectBackgroundColor(final EObject object) {
        final EList<String> sources = this.sourcesModel.getSources().get(object);
        if (sources != null) {
            if (this.selector.isColorGroup(sources, 0)) {
                return "#ffffff";
            } else if (this.selector.isColorGroup(sources, 1)) {
                return "#a0a0ff"; // blue for static
            } else if (this.selector.isColorGroup(sources, 2)) {
                return "#90ff90"; // green on dynamic
            } else {
                return "#eaeaea"; // other objects
            }
        } else {
            return "#d000d0"; // pink on error
        }
    }

    @Override
    public IGraph build(final ModelRepository repository) {
        this.graph = GraphFactory.createGraph(repository.getName());

        this.sourcesModel = repository.getModel(SourceModel.class);
        this.executionModel = repository.getModel(ExecutionModel.class);
        this.statisticsModel = repository.getModel(StatisticsModel.class);
        this.responseTimeDecorator = new ResponseTimeDecorator(this.statisticsModel, ChronoUnit.NANOS);
        for (final AggregatedInvocation invocation : this.executionModel.getAggregatedInvocations().values()) {
            this.handleInvocation(invocation);
        }
        for (final AggregatedStorageAccess storageAccess : this.executionModel.getAggregatedStorageAccesses()
                .values()) {
            this.handleStorageAccess(storageAccess);
        }
        return this.graph;
    }

    private void handleInvocation(final AggregatedInvocation invocation) {
        final INode sourceVertex = invocation.getSource() != null ? this.addVertex(invocation.getSource())
                : this.addVertexForEntry(); // NOCS (declarative)
        final INode targetVertex = this.addVertex(invocation.getTarget());

        final EMap<EObject, Statistics> statisticsMap = this.statisticsModel.getStatistics();
        final Statistics statistics = statisticsMap.get(invocation);
        final EMap<EPredefinedUnits, StatisticRecord> recordMap = statistics.getStatistics();
        final StatisticRecord record = recordMap.get(EPredefinedUnits.INVOCATION);
        final EMap<EPropertyType, Object> properties = record.getProperties();

        final long calls = (Long) properties.get(EPropertyType.COUNT);
        this.addEdge(sourceVertex, targetVertex, calls);
    }

    private void handleStorageAccess(final AggregatedStorageAccess storageAccess) {
        final INode sourceVertex = storageAccess.getCode() != null ? this.addVertex(storageAccess.getCode())
                : this.addVertexForEntry(); // NOCS (declarative)
        final INode targetVertex = this.addStorageVertex(storageAccess.getStorage());

        final EMap<EObject, Statistics> statisticsMap = this.statisticsModel.getStatistics();
        final Statistics statistics = statisticsMap.get(storageAccess);
        final EMap<EPredefinedUnits, StatisticRecord> recordMap = statistics.getStatistics();
        final StatisticRecord record = recordMap.get(EPredefinedUnits.INVOCATION);
        final EMap<EPropertyType, Object> properties = record.getProperties();

        final long calls = (Long) properties.get(EPropertyType.COUNT);
        this.addEdge(sourceVertex, targetVertex, calls);
    }

    protected abstract INode addStorageVertex(final DeployedStorage deployedStorage);
}
