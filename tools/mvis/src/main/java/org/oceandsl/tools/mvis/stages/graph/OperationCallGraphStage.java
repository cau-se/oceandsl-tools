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

import org.oceandsl.analysis.graph.EGraphGenerationMode;
import org.oceandsl.analysis.graph.IGraphElementSelector;
import org.oceandsl.tools.mvis.FullyQualifiedNamesFactory;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.analysis.generic.graph.GraphFactory;
import kieker.analysis.generic.graph.IEdge;
import kieker.analysis.generic.graph.IGraph;
import kieker.analysis.generic.graph.INode;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.execution.EDirection;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.execution.Invocation;
import kieker.model.analysismodel.execution.OperationDataflow;
import kieker.model.analysismodel.execution.StorageDataflow;
import teetime.stage.basic.AbstractTransformation;

/**
 * Create a graph based on function calls in the architecture model.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class OperationCallGraphStage extends AbstractTransformation<ModelRepository, IGraph<INode, IEdge>> {

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

        final IGraph<INode, IEdge> graph = GraphFactory.createGraph(repository.getName());

        for (final Invocation invocation : executionModel.getInvocations().values()) {
            final boolean sourceSelected = this.selector.nodeIsSelected(invocation.getCallee().getComponent());
            final boolean targetSelected = this.selector.nodeIsSelected(invocation.getCallee());
            if (sourceSelected) {
                this.addOperationVertexIfAbsent(graph, invocation.getCaller());
            }
            if (targetSelected) {
                this.addOperationVertexIfAbsent(graph, invocation.getCallee());
            }
            switch (this.graphGeneratioMode) {
            case ONLY_EDGES_FOR_NODES:
                if (sourceSelected && targetSelected && this.selector.edgeIsSelected(invocation)) {
                    this.addOperationEdge(graph, invocation.getCaller(), invocation.getCallee(), EDirection.READ);
                }
                break;
            case ADD_NODES_FOR_EDGES:
                if (this.selector.edgeIsSelected(invocation)) {
                    if (!sourceSelected) {
                        this.addOperationVertexIfAbsent(graph, invocation.getCaller());
                    }
                    if (!targetSelected) {
                        this.addOperationVertexIfAbsent(graph, invocation.getCallee());
                    }
                    this.addOperationEdge(graph, invocation.getCaller(), invocation.getCallee(), EDirection.READ);
                }
                break;
            default:
                throw new InternalError("Illegal graph generation mode " + this.graphGeneratioMode.name());
            }
        }

        for (final OperationDataflow operationDataflow : executionModel.getOperationDataflows().values()) {
            final boolean sourceSelected = this.selector.nodeIsSelected(operationDataflow.getCallee().getComponent());
            final boolean targetSelected = this.selector.nodeIsSelected(operationDataflow.getCallee());
            if (sourceSelected) {
                this.addOperationVertexIfAbsent(graph, operationDataflow.getCaller());
            }
            if (targetSelected) {
                this.addOperationVertexIfAbsent(graph, operationDataflow.getCallee());
            }
            switch (this.graphGeneratioMode) {
            case ONLY_EDGES_FOR_NODES:
                if (sourceSelected && targetSelected && this.selector.edgeIsSelected(operationDataflow)) {
                    this.addOperationEdge(graph, operationDataflow.getCaller(), operationDataflow.getCallee(),
                            operationDataflow.getDirection());
                }
                break;
            case ADD_NODES_FOR_EDGES:
                if (this.selector.edgeIsSelected(operationDataflow)) {
                    if (!sourceSelected) {
                        this.addOperationVertexIfAbsent(graph, operationDataflow.getCaller());
                    }
                    if (!targetSelected) {
                        this.addOperationVertexIfAbsent(graph, operationDataflow.getCallee());
                    }
                    this.addOperationEdge(graph, operationDataflow.getCaller(), operationDataflow.getCallee(),
                            operationDataflow.getDirection());
                }
                break;
            default:
                throw new InternalError("Illegal graph generation mode " + this.graphGeneratioMode.name());
            }

        }
        for (final StorageDataflow storageDataflow : executionModel.getStorageDataflows().values()) {
            final boolean sourceSelected = this.selector.nodeIsSelected(storageDataflow.getStorage().getComponent());
            final boolean targetSelected = this.selector.nodeIsSelected(storageDataflow.getStorage());
            if (sourceSelected) {
                this.addOperationVertexIfAbsent(graph, storageDataflow.getCode());
            }
            if (targetSelected) {
                this.addStorageVertexIfAbsent(graph, storageDataflow.getStorage());
            }
            switch (this.graphGeneratioMode) {
            case ONLY_EDGES_FOR_NODES:
                if (sourceSelected && targetSelected && this.selector.edgeIsSelected(storageDataflow)) {
                    this.addStorageEdge(graph, storageDataflow.getCode(), storageDataflow.getStorage(),
                            storageDataflow.getDirection());
                }
                break;
            case ADD_NODES_FOR_EDGES:
                if (this.selector.edgeIsSelected(storageDataflow)) {
                    if (!sourceSelected) {
                        this.addOperationVertexIfAbsent(graph, storageDataflow.getCode());
                    }
                    if (!targetSelected) {
                        this.addStorageVertexIfAbsent(graph, storageDataflow.getStorage());
                    }
                    this.addStorageEdge(graph, storageDataflow.getCode(), storageDataflow.getStorage(),
                            storageDataflow.getDirection());
                }
                break;
            default:
                throw new InternalError("Illegal graph generation mode " + this.graphGeneratioMode.name());
            }
        }

        this.outputPort.send(graph);
    }

    private void addOperationEdge(final IGraph<INode, IEdge> graph, final DeployedOperation source,
            final DeployedOperation target, final EDirection direction) {
        final Optional<INode> sourceNode = this.findOperationNode(graph, source);
        final Optional<INode> targetNode = this.findOperationNode(graph, target);
        switch (direction) {
        case WRITE:
            graph.getGraph().addEdge(sourceNode.get(), targetNode.get(), GraphFactory.createEdge(null));
            break;
        case READ:
            graph.getGraph().addEdge(targetNode.get(), sourceNode.get(), GraphFactory.createEdge(null));
            break;
        case BOTH:
            graph.getGraph().addEdge(targetNode.get(), sourceNode.get(), GraphFactory.createEdge(null));
            graph.getGraph().addEdge(sourceNode.get(), targetNode.get(), GraphFactory.createEdge(null));
            break;
        default:
            break;
        }
    }

    private void addStorageEdge(final IGraph<INode, IEdge> graph, final DeployedOperation operation,
            final DeployedStorage storage, final EDirection direction) {
        final Optional<INode> operationNode = this.findOperationNode(graph, operation);
        final Optional<INode> storageNode = this.findStorageNode(graph, storage);
        switch (direction) {
        case WRITE:
            graph.getGraph().addEdge(operationNode.get(), storageNode.get(), GraphFactory.createEdge(null));
            break;
        case READ:
            graph.getGraph().addEdge(storageNode.get(), operationNode.get(), GraphFactory.createEdge(null));
            break;
        case BOTH:
            graph.getGraph().addEdge(storageNode.get(), operationNode.get(), GraphFactory.createEdge(null));
            graph.getGraph().addEdge(operationNode.get(), storageNode.get(), GraphFactory.createEdge(null));
            break;
        default:
            break;
        }
    }

    private Optional<INode> findOperationNode(final IGraph<INode, IEdge> graph, final DeployedOperation operation) {
        final String fullyQualifiedName = FullyQualifiedNamesFactory.createFullyQualifiedName(operation);
        return graph.findNode(fullyQualifiedName);
    }

    private void addEdge(final IGraph<INode, IEdge> graph, final DeployedOperation source,
            final DeployedOperation target) {
        final Optional<INode> sourceNode = this.findOperationNode(graph, source);
        final Optional<INode> targetNode = this.findOperationNode(graph, target);
        graph.getGraph().addEdge(sourceNode.get(), targetNode.get(), GraphFactory.createEdge(null));
    }

    private Optional<INode> findStorageNode(final IGraph<INode, IEdge> graph, final DeployedStorage storage) {
        final String fullyQualifiedName = FullyQualifiedNamesFactory.createFullyQualifiedName(storage);
        return graph.findNode(fullyQualifiedName);
    }

    private void addOperationVertexIfAbsent(final IGraph<INode, IEdge> graph, final DeployedOperation operation) {
        final Optional<INode> node = this.findOperationNode(graph, operation);
        if (!node.isPresent()) {
            final INode newNode = GraphFactory
                    .createNode(FullyQualifiedNamesFactory.createFullyQualifiedName(operation));
            graph.getGraph().addNode(newNode);
        }
    }

    private void addStorageVertexIfAbsent(final IGraph<INode, IEdge> graph, final DeployedStorage storage) {
        final Optional<INode> node = this.findStorageNode(graph, storage);
        if (!node.isPresent()) {
            final INode newNode = GraphFactory.createNode(FullyQualifiedNamesFactory.createFullyQualifiedName(storage));
            graph.getGraph().addNode(newNode);
        }
    }

}
