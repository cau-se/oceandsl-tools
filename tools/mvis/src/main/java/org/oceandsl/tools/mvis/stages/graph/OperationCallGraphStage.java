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
 * Create a graph based on function calls in the architecture model.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class OperationCallGraphStage extends AbstractTransformation<ModelRepository, IGraph> {

    private final IGraphElementSelector selector;

    public OperationCallGraphStage(final IGraphElementSelector selector) {
        this.selector = selector;
    }

    @Override
    protected void execute(final ModelRepository element) throws Exception {
        final ExecutionModel executionModel = (ExecutionModel) element.getModels().get(ExecutionModel.class);

        final IGraph graph = IGraph.create();
        graph.setName(element.getName());

        for (final AggregatedInvocation invocation : executionModel.getAggregatedInvocations().values()) {
            final boolean sourceSelected = this.selector.nodeIsSelected(invocation.getTarget().getComponent());
            final boolean targetSelected = this.selector.nodeIsSelected(invocation.getTarget());
            if (sourceSelected) {
                graph.addVertexIfAbsent(invocation.getSource());
            }
            if (targetSelected) {
                graph.addVertexIfAbsent(invocation.getTarget());
            }
            if (sourceSelected && targetSelected && this.selector.edgeIsSelected(invocation)) {
                graph.addEdge(invocation, graph.getVertex(invocation.getSource()),
                        graph.getVertex(invocation.getTarget()));
            }
        }

        this.outputPort.send(graph);
    }

}
