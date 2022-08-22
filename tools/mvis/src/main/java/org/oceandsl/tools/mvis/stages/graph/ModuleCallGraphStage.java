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

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.analysis.generic.graph.GraphFactory;
import kieker.analysis.generic.graph.IGraph;
import kieker.analysis.generic.graph.INode;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.OperationDataflow;
import kieker.model.analysismodel.execution.StorageDataflow;
import org.oceandsl.analysis.graph.EGraphGenerationMode;
import org.oceandsl.analysis.graph.IGraphElementSelector;
import org.oceandsl.tools.mvis.FullyQualifiedNamesFactory;
import teetime.stage.basic.AbstractTransformation;

import java.util.Optional;

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

        final IGraph graph = GraphFactory.createGraph(repository.getName());

        for (final AggregatedInvocation invocation : executionModel.getAggregatedInvocations().values()) {
            final boolean sourceSelected = this.selector.nodeIsSelected(invocation.getSource().getComponent());
            final boolean targetSelected = this.selector.nodeIsSelected(invocation.getTarget().getComponent());
            if (sourceSelected) {
                this.addVertexIfAbsent(graph, invocation.getSource().getComponent());
            }
            if (targetSelected) {
                this.addVertexIfAbsent(graph, invocation.getTarget().getComponent());
            }
            switch (this.graphGeneratioMode) {
            case ONLY_EDGES_FOR_NODES:
                if (sourceSelected && targetSelected && this.selector.edgeIsSelected(invocation)) {
                    this.addEdge(graph, invocation.getSource().getComponent(), invocation.getTarget().getComponent());
                }
                break;
            case ADD_NODES_FOR_EDGES:
                if (this.selector.edgeIsSelected(invocation)) {
                    if (!sourceSelected) {
                        this.addVertexIfAbsent(graph, invocation.getSource().getComponent());
                    }
                    if (!targetSelected) {
                        this.addVertexIfAbsent(graph, invocation.getTarget().getComponent());
                    }
                    this.addEdge(graph, invocation.getSource().getComponent(), invocation.getTarget().getComponent());
                }
                break;
            default:
                throw new InternalError("Illegal graph generation mode " + this.graphGeneratioMode.name());
            }
        }

        for (final OperationDataflow operationDataflow : executionModel.getOperationDataflow().values()) {
            final boolean sourceSelected = this.selector.nodeIsSelected(operationDataflow.getSource().getComponent());
            final boolean targetSelected = this.selector.nodeIsSelected(operationDataflow.getTarget().getComponent());
            if (sourceSelected) {
                this.addVertexIfAbsent(graph, operationDataflow.getSource().getComponent());
            }
            if (targetSelected) {
                this.addVertexIfAbsent(graph, operationDataflow.getTarget().getComponent());
            }
            switch (this.graphGeneratioMode) {
                case ONLY_EDGES_FOR_NODES:
                    if (sourceSelected && targetSelected && this.selector.edgeIsSelected(operationDataflow)) {
                        this.addEdge(graph, operationDataflow.getSource().getComponent(), operationDataflow.getTarget().getComponent());
                    }
                    break;
                case ADD_NODES_FOR_EDGES:
                    if (this.selector.edgeIsSelected(operationDataflow)) {
                        if (!sourceSelected) {
                            this.addVertexIfAbsent(graph, operationDataflow.getSource().getComponent());
                        }
                        if (!targetSelected) {
                            this.addVertexIfAbsent(graph, operationDataflow.getTarget().getComponent());
                        }
                        this.addEdge(graph, operationDataflow.getSource().getComponent(), operationDataflow.getTarget().getComponent());
                    }
                    break;
                default:
                    throw new InternalError("Illegal graph generation mode " + this.graphGeneratioMode.name());
            }
        }

        for (final StorageDataflow storageDataflow : executionModel.getStorageDataflow().values()) {
            final boolean sourceSelected = this.selector.nodeIsSelected(storageDataflow.getCode().getComponent());
            final boolean targetSelected = this.selector.nodeIsSelected(storageDataflow.getStorage().getComponent());
            if (sourceSelected) {
                this.addVertexIfAbsent(graph, storageDataflow.getCode().getComponent());
            }
            if (targetSelected) {
                this.addVertexIfAbsent(graph, storageDataflow.getStorage().getComponent());
            }
            switch (this.graphGeneratioMode) {
                case ONLY_EDGES_FOR_NODES:
                    if (sourceSelected && targetSelected && this.selector.edgeIsSelected(storageDataflow)) {
                        this.addEdge(graph, storageDataflow.getCode().getComponent(), storageDataflow.getStorage().getComponent());
                    }
                    break;
                case ADD_NODES_FOR_EDGES:
                    if (this.selector.edgeIsSelected(storageDataflow)) {
                        if (!sourceSelected) {
                            this.addVertexIfAbsent(graph, storageDataflow.getCode().getComponent());
                        }
                        if (!targetSelected) {
                            this.addVertexIfAbsent(graph, storageDataflow.getStorage().getComponent());
                        }
                        this.addEdge(graph, storageDataflow.getCode().getComponent(), storageDataflow.getStorage().getComponent());
                    }
                    break;
                default:
                    throw new InternalError("Illegal graph generation mode " + this.graphGeneratioMode.name());
            }
        }

        this.outputPort.send(graph);
    }

    private void addEdge(final IGraph graph, final DeployedComponent source, final DeployedComponent target) {
        final Optional<INode> sourceNode = this.findNode(graph, source);
        final Optional<INode> targetNode = this.findNode(graph, target);
        graph.getGraph().addEdge(sourceNode.get(), targetNode.get(), GraphFactory.createEdge(null));
    }

    private Optional<INode> findNode(final IGraph graph, final DeployedComponent component) {
        final String fullyQualifiedName = FullyQualifiedNamesFactory.createFullyQualifiedName(component);
        return graph.findNode(fullyQualifiedName);
    }

    private void addVertexIfAbsent(final IGraph graph, final DeployedComponent component) {
        final Optional<INode> node = this.findNode(graph, component);
        if (!node.isPresent()) {
            final INode newNode = GraphFactory
                    .createNode(FullyQualifiedNamesFactory.createFullyQualifiedName(component));
            graph.getGraph().addNode(newNode);
        }
    }

}
