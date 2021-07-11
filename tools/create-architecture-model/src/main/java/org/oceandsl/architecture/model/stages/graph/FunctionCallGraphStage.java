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
 * Create a graph based on function calls in the architecture model.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class FunctionCallGraphStage extends AbstractTransformation<ModelRepository, IGraph> {

    private final String subgraphName;

    public FunctionCallGraphStage(final String subgraphName) {
        this.subgraphName = subgraphName;
    }

    @Override
    protected void execute(final ModelRepository element) throws Exception {
        final ExecutionModel executionModel = (ExecutionModel) element.getModels().get(ExecutionModel.class);
        final SourceModel sourcesModel = (SourceModel) element.getModels().get(SourceModel.class);

        final IGraph graph = IGraph.create();
        graph.setName(element.getName());

        for (final AggregatedInvocation invocation : executionModel.getAggregatedInvocations().values()) {
            if (this.findInvocationInGraph(sourcesModel, invocation)) {
                graph.addVertexIfAbsent(invocation.getSource());
                graph.addVertexIfAbsent(invocation.getTarget());
                graph.addEdge(invocation, graph.getVertex(invocation.getSource()),
                        graph.getVertex(invocation.getTarget()));
            }
        }

        this.outputPort.send(graph);
    }

    private boolean findInvocationInGraph(final SourceModel sourcesModel, final AggregatedInvocation invocation) {
        final EList<String> sources = sourcesModel.getSources().get(invocation.getSource());
        final EList<String> targets = sourcesModel.getSources().get(invocation.getTarget());

        return (sources.contains(this.subgraphName) && targets.contains(this.subgraphName));
    }

}
