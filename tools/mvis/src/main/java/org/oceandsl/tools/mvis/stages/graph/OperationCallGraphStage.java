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

import java.util.Optional;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.analysis.generic.graph.GraphFactory;
import kieker.analysis.generic.graph.IGraph;
import kieker.analysis.generic.graph.INode;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.execution.Invocation;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.graph.EGraphGenerationMode;
import org.oceandsl.analysis.graph.IGraphElementSelector;
import org.oceandsl.tools.mvis.FullyQualifiedNamesFactory;

/**
 * Create a graph based on function calls in the architecture model.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class OperationCallGraphStage extends AbstractTransformation<ModelRepository, IGraph> {

    private final IGraphElementSelector selector;
    private final EGraphGenerationMode graphGeneratioMode;

    public OperationCallGraphStage(final IGraphElementSelector selector,
            final EGraphGenerationMode graphGeneratioMode) {
        this.selector = selector;
        this.graphGeneratioMode = graphGeneratioMode;
    }

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        final ExecutionModel executionModel = repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL);

        final IGraph graph = GraphFactory.createGraph(repository.getName());

        for (final Invocation invocation : executionModel.getInvocations().values()) {
            final boolean sourceSelected = this.selector.nodeIsSelected(invocation.getCallee().getComponent());
            final boolean targetSelected = this.selector.nodeIsSelected(invocation.getCallee());
            if (sourceSelected) {
                this.addVertexIfAbsent(graph, invocation.getCaller());
            }
            if (targetSelected) {
                this.addVertexIfAbsent(graph, invocation.getCallee());
            }
            switch (this.graphGeneratioMode) {
            case ONLY_EDGES_FOR_NODES:
                if (sourceSelected && targetSelected && this.selector.edgeIsSelected(invocation)) {
                    this.addEdge(graph, invocation.getCaller(), invocation.getCallee());
                }
                break;
            case ADD_NODES_FOR_EDGES:
                if (this.selector.edgeIsSelected(invocation)) {
                    if (!sourceSelected) {
                        this.addVertexIfAbsent(graph, invocation.getCaller());
                    }
                    if (!targetSelected) {
                        this.addVertexIfAbsent(graph, invocation.getCallee());
                    }
                    this.addEdge(graph, invocation.getCaller(), invocation.getCallee());
                }
                break;
            default:
                throw new InternalError("Illegal graph generation mode " + this.graphGeneratioMode.name());
            }
        }

        this.outputPort.send(graph);
    }

    private void addEdge(final IGraph graph, final DeployedOperation source, final DeployedOperation target) {
        final Optional<INode> sourceNode = this.findNode(graph, source);
        final Optional<INode> targetNode = this.findNode(graph, target);
        graph.getGraph().addEdge(sourceNode.get(), targetNode.get(), GraphFactory.createEdge(null));
    }

    private Optional<INode> findNode(final IGraph graph, final DeployedOperation operation) {
        final String fullyQualifiedName = FullyQualifiedNamesFactory.createFullyQualifiedName(operation);
        return graph.findNode(fullyQualifiedName);
    }

    private void addVertexIfAbsent(final IGraph graph, final DeployedOperation operation) {
        final Optional<INode> node = this.findNode(graph, operation);
        if (!node.isPresent()) {
            final INode newNode = GraphFactory
                    .createNode(FullyQualifiedNamesFactory.createFullyQualifiedName(operation));
            graph.getGraph().addNode(newNode);
        }
    }

}
