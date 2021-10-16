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
package org.oceandsl.tools.mvis.stages.graph;

import kieker.analysis.graph.IGraph;
import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionModel;
import teetime.stage.basic.AbstractTransformation;

/**
 * Compute a graph based on the module structure of the architecture limited to nodes and modules
 * which belong to a specific measurement source.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class ModuleCallGraphStage extends AbstractTransformation<ModelRepository, IGraph> {

    private final IGraphElementSelector selector;
    private final EGraphGenerationMode graphGeneratioMode;

    public ModuleCallGraphStage(final IGraphElementSelector selector, final EGraphGenerationMode graphGeneratioMode) {
        this.selector = selector;
        this.graphGeneratioMode = graphGeneratioMode;
    }

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        final ExecutionModel executionModel = (ExecutionModel) repository.getModels().get(ExecutionModel.class);

        final IGraph graph = IGraph.create();
        graph.setName(repository.getName());

        for (final AggregatedInvocation invocation : executionModel.getAggregatedInvocations().values()) {
            final boolean sourceSelected = this.selector.nodeIsSelected(invocation.getSource().getComponent());
            final boolean targetSelected = this.selector.nodeIsSelected(invocation.getTarget().getComponent());
            if (sourceSelected) {
                graph.addVertexIfAbsent(invocation.getSource().getComponent());
            }
            if (targetSelected) {
                graph.addVertexIfAbsent(invocation.getTarget().getComponent());
            }
            switch (this.graphGeneratioMode) {
            case ONLY_EDGES_FOR_NODES:
                if (sourceSelected && targetSelected && this.selector.edgeIsSelected(invocation)) {
                    graph.addEdge(invocation, graph.getVertex(invocation.getSource().getComponent()),
                            graph.getVertex(invocation.getTarget().getComponent()));
                }
                break;
            case ADD_NODES_FOR_EDGES:
                if (this.selector.edgeIsSelected(invocation)) {
                    if (!sourceSelected) {
                        graph.addVertexIfAbsent(invocation.getSource());
                    }
                    if (!targetSelected) {
                        graph.addVertexIfAbsent(invocation.getTarget());
                    }
                    graph.addEdge(invocation, graph.getVertex(invocation.getSource()),
                            graph.getVertex(invocation.getTarget()));
                }
                break;
            }
        }

        this.outputPort.send(graph);
    }

}
