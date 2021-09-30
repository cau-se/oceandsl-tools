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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import kieker.analysis.graph.IGraph;
import kieker.analysis.graph.IVertex;
import kieker.analysis.graph.dependency.AbstractDependencyGraphBuilder;
import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.AggregatedStorageAccess;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.statistics.EPredefinedUnits;
import kieker.model.analysismodel.statistics.EPropertyType;
import kieker.model.analysismodel.statistics.StatisticRecord;
import kieker.model.analysismodel.statistics.Statistics;

/**
 * @author Reiner Jung
 * @since 1.0
 *
 */
public abstract class AbstractColorDependencyGraphBuilder extends AbstractDependencyGraphBuilder {

    private final SourceModel sourcesModel;
    private final String[] groupA;
    private final String[] groupB;
    private final String[] intersect;

    public AbstractColorDependencyGraphBuilder(final ModelRepository repository, final String[] groupA,
            final String[] groupB) {
        super(repository);
        this.sourcesModel = repository.getModel(SourceModel.class);
        this.groupA = groupA;
        this.groupB = groupB;
        this.intersect = new String[groupA.length + groupB.length];
        for (int i = 0; i < groupA.length; i++) {
            this.intersect[i] = groupA[i];
        }
        for (int i = 0; i < groupB.length; i++) {
            this.intersect[i + groupA.length] = groupB[i];
        }
    }

    protected String selectForegroundColor(final EObject object) {
        final EList<String> sources = this.sourcesModel.getSources().get(object);
        if (sources != null) {
            if (this.contains(sources, this.intersect)) {
                return "#000000";
            } else if (this.contains(sources, this.groupA)) {
                return "#0000ff"; // blue for static
            } else if (this.contains(sources, this.groupB)) {
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
            if (this.contains(sources, this.intersect)) {
                return "#ffffff";
            } else if (this.contains(sources, this.groupA)) {
                return "#a0a0ff"; // blue for static
            } else if (this.contains(sources, this.groupB)) {
                return "#90ff90"; // green on dynamic
            } else {
                return "#eaeaea"; // other objects
            }
        } else {
            return "#f000f0"; // pink on error
        }
    }

    @Override
    public IGraph build() {
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
        final IVertex sourceVertex = invocation.getSource() != null ? this.addVertex(invocation.getSource())
                : this.addVertexForEntry(); // NOCS (declarative)
        final IVertex targetVertex = this.addVertex(invocation.getTarget());

        final EMap<EObject, Statistics> statisticsMap = this.statisticsModel.getStatistics();
        final Statistics statistics = statisticsMap.get(invocation);
        final EMap<EPredefinedUnits, StatisticRecord> recordMap = statistics.getStatistics();
        final StatisticRecord record = recordMap.get(EPredefinedUnits.RESPONSE_TIME);
        final EMap<EPropertyType, Object> properties = record.getProperties();

        final long calls = (Long) properties.get(EPropertyType.COUNT);
        this.addEdge(sourceVertex, targetVertex, calls);
    }

    private void handleStorageAccess(final AggregatedStorageAccess storageAccess) {
        final IVertex sourceVertex = storageAccess.getCode() != null ? this.addVertex(storageAccess.getCode())
                : this.addVertexForEntry(); // NOCS (declarative)
        final IVertex targetVertex = this.addStorageVertex(storageAccess.getStorage());

        final EMap<EObject, Statistics> statisticsMap = this.statisticsModel.getStatistics();
        final Statistics statistics = statisticsMap.get(storageAccess);
        final EMap<EPredefinedUnits, StatisticRecord> recordMap = statistics.getStatistics();
        final StatisticRecord record = recordMap.get(EPredefinedUnits.INVOCATION);
        final EMap<EPropertyType, Object> properties = record.getProperties();

        final long calls = (Long) properties.get(EPropertyType.COUNT);
        this.addEdge(sourceVertex, targetVertex, calls);
    }

    protected abstract IVertex addStorageVertex(final DeployedStorage deployedStorage);

    private boolean contains(final EList<String> operationSource, final String... labels) {
        boolean result = true;
        for (final String label : labels) {
            result &= operationSource.contains(label);
        }
        return result;
    }
}
