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
package org.oceandsl.analysis.metrics.entropy;

import java.util.Map.Entry;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import org.mosim.refactorlizar.architecture.evaluation.graphs.Node;

import kieker.analysis.architecture.recovery.ModelRepository;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.graph.EGraphGenerationMode;
import org.oceandsl.analysis.graph.IGraphElementSelector;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class AllenDeployedArchitectureGraphStage
        extends AbstractTransformation<ModelRepository, Graph<Node<DeployedComponent>>> {

    private final IGraphElementSelector selector;
    private final EGraphGenerationMode graphGeneratioMode;

    public AllenDeployedArchitectureGraphStage(final IGraphElementSelector selector,
            final EGraphGenerationMode graphGeneratioMode) {
        this.selector = selector;
        this.graphGeneratioMode = graphGeneratioMode;
    }

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        // RepositoryUtils.print(repository);

        final DeploymentModel deploymentModel = repository.getModel(DeploymentModel.class);
        final ExecutionModel executionModel = repository.getModel(ExecutionModel.class);
        final MutableGraph<Node<DeployedComponent>> graph = GraphBuilder.undirected().allowsSelfLoops(true).build();

        this.selector.setRepository(repository);

        for (final Entry<String, DeploymentContext> context : deploymentModel.getContexts()) {
            for (final Entry<String, DeployedComponent> component : context.getValue().getComponents()) {
                for (final Entry<String, DeployedOperation> operation : component.getValue().getOperations()) {
                    if (this.selector.nodeIsSelected(operation.getValue())) {
                        final Node<DeployedComponent> node = new KiekerNode<>(operation.getValue());
                        graph.addNode(node);
                    }
                }
            }
        }
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> entry : executionModel
                .getAggregatedInvocations()) {
            if (this.selector.edgeIsSelected(entry.getValue())) {
                final Node<DeployedComponent> source = this.findNode(graph, entry.getValue().getSource());
                final Node<DeployedComponent> target = this.findNode(graph, entry.getValue().getTarget());

                switch (this.graphGeneratioMode) { // NOPMD
                case ADD_NODES_FOR_EDGES:
                    graph.putEdge(this.getOrCreateNode(graph, source, entry.getValue().getSource()),
                            this.getOrCreateNode(graph, target, entry.getValue().getTarget()));
                    break;
                case ONLY_EDGES_FOR_NODES:
                    if (source != null && target != null) {
                        graph.putEdge(source, target);
                    }
                    break;
                default:
                    throw new InternalError("Illegal graph generation mode " + this.graphGeneratioMode.name());
                }
            }
        }
        this.outputPort.send(graph);
    }

    private Node<DeployedComponent> getOrCreateNode(final MutableGraph<Node<DeployedComponent>> graph,
            final Node<DeployedComponent> node, final DeployedOperation operation) {
        if (node == null) {
            final Node<DeployedComponent> newNode = new KiekerNode<>(operation);
            graph.addNode(newNode);
            return newNode;
        } else {
            return node;
        }
    }

    private Node<DeployedComponent> findNode(final Graph<Node<DeployedComponent>> graph,
            final DeployedOperation operation) {
        for (final Node<DeployedComponent> node : graph.nodes()) {
            final KiekerNode<DeployedComponent> kiekerNode = (KiekerNode<DeployedComponent>) node;
            if (kiekerNode.getMember().equals(operation)) {
                return kiekerNode;
            }
        }

        return null;
    }

}
