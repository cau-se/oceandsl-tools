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
package org.oceandsl.architecture.model.stages.graph;

import org.eclipse.emf.common.util.EList;

import kieker.analysis.graph.IGraph;
import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.sources.SourceModel;
import teetime.stage.basic.AbstractTransformation;

/**
 * Compute a graph based on the module structure of the architecture limited to nodes and modules
 * which belong to a specific measurement source.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class ModuleCallGraphStage extends AbstractTransformation<ModelRepository, IGraph> {

    private final String measurementSourceName;

    public ModuleCallGraphStage(final String measurementSourceName) {
        this.measurementSourceName = measurementSourceName;
    }

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        final ExecutionModel executionModel = (ExecutionModel) repository.getModels().get(ExecutionModel.class);
        final SourceModel sourcesModel = (SourceModel) repository.getModels().get(SourceModel.class);

        final IGraph graph = IGraph.create();
        graph.setName(repository.getName());

        for (final AggregatedInvocation invocation : executionModel.getAggregatedInvocations().values()) {
            if (this.findInvocationInGraph(sourcesModel, invocation)) {
                graph.addVertexIfAbsent(invocation.getSource().getComponent());
                graph.addVertexIfAbsent(invocation.getTarget().getComponent());
                graph.addEdge(invocation, graph.getVertex(invocation.getSource().getComponent()),
                        graph.getVertex(invocation.getTarget().getComponent()));
            }
        }

        this.outputPort.send(graph);
    }

    private boolean findInvocationInGraph(final SourceModel sourcesModel, final AggregatedInvocation invocation) {
        final EList<String> sources = sourcesModel.getSources().get(invocation.getSource());
        final EList<String> targets = sourcesModel.getSources().get(invocation.getTarget());

        return (sources.contains(this.measurementSourceName) && targets.contains(this.measurementSourceName));
    }

}
