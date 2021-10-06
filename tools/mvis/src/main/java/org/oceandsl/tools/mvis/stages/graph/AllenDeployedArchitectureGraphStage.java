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

import java.util.Map.Entry;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import edu.kit.kastel.sdq.case4lang.refactorlizar.architecture_evaluation.graphs.Node;
import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import teetime.stage.basic.AbstractTransformation;

/**
 * @author Reiner Jung
 *
 */
public class AllenDeployedArchitectureGraphStage
        extends AbstractTransformation<ModelRepository, Graph<Node<DeployedComponent>>> {

    IGraphElementSelector selector;

    public AllenDeployedArchitectureGraphStage(final IGraphElementSelector selector) {
        this.selector = selector;
    }

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        // RepositoryUtils.print(repository);

        final DeploymentModel deploymentModel = repository.getModel(DeploymentModel.class);
        final ExecutionModel executionModel = repository.getModel(ExecutionModel.class);
        final MutableGraph<Node<DeployedComponent>> graph = GraphBuilder.undirected().allowsSelfLoops(true).build();

        this.selector.setRepository(repository);

        for (final Entry<String, DeploymentContext> context : deploymentModel.getDeploymentContexts()) {
            for (final Entry<String, DeployedComponent> component : context.getValue().getComponents()) {
                for (final Entry<String, DeployedOperation> operation : component.getValue().getContainedOperations()) {
                    if (this.selector.nodeIsSelected(operation.getValue())) {
                        final Node<DeployedComponent> node = new KiekerNode<>(operation.getValue());
                        graph.addNode(node);
                    }
                }
            }
        }
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> invocation : executionModel
                .getAggregatedInvocations()) {
            if (this.selector.edgeIsSelected(invocation.getValue())) {
                graph.putEdge(this.findNode(graph, invocation.getKey().getFirst()),
                        this.findNode(graph, invocation.getKey().getSecond()));
            }
        }
        this.outputPort.send(graph);
    }

    private Node<DeployedComponent> findNode(final Graph<Node<DeployedComponent>> graph,
            final DeployedOperation operation) {
        for (final Node<DeployedComponent> node : graph.nodes()) {
            @SuppressWarnings("unchecked")
            final KiekerNode<DeployedComponent, DeployedOperation> kiekerNode = (KiekerNode<DeployedComponent, DeployedOperation>) node;
            if (kiekerNode.getMember().equals(operation)) {
                return kiekerNode;
            }
        }

        this.logger.error("Internal error: Looked for node of an edge that does not exist: {}",
                operation.getAssemblyOperation().getOperationType().getSignature());
        return null;
    }

}
