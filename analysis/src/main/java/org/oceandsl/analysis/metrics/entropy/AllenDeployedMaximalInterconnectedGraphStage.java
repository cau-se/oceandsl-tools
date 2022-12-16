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

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.execution.Invocation;
import kieker.model.analysismodel.execution.Tuple;

import org.mosim.refactorlizar.architecture.evaluation.graphs.Node;
import teetime.stage.basic.AbstractTransformation;

import java.util.Map.Entry;

/**
 * @author Reiner Jung
 * @since 1.4
 */
public class AllenDeployedMaximalInterconnectedGraphStage
        extends AbstractTransformation<ModelRepository, Graph<Node<DeployedComponent>>> {

    public AllenDeployedMaximalInterconnectedGraphStage() {
    }

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        final DeploymentModel deploymentModel = repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL);
        final ExecutionModel executionModel = repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL);
        final MutableGraph<Node<DeployedComponent>> graph = GraphBuilder.undirected().allowsSelfLoops(true).build();

        for (final Entry<String, DeploymentContext> context : deploymentModel.getContexts()) {
            for (final Entry<String, DeployedComponent> component : context.getValue().getComponents()) {
                for (final Entry<String, DeployedOperation> operation : component.getValue().getOperations()) {
                    final Node<DeployedComponent> node = new KiekerNode<>(operation.getValue());
                    graph.addNode(node);
                }
            }
        }
        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, Invocation> entry : executionModel
                .getInvocations()) {
            final Node<DeployedComponent> source = this.findNode(graph, entry.getValue().getCaller());
            final Node<DeployedComponent> target = this.findNode(graph, entry.getValue().getCallee());

            if (source != null && target != null) {
                graph.putEdge(source, target);
            }
        }
        this.outputPort.send(graph);
    }

    private Node<DeployedComponent> findNode(final Graph<Node<DeployedComponent>> graph,
            final DeployedOperation operation) {
        for (final Node<DeployedComponent> node : graph.nodes()) {
            final KiekerNode<DeployedComponent,DeployedOperation> kiekerNode = (KiekerNode<DeployedComponent, DeployedOperation>) node;
            if (kiekerNode.getMember().equals(operation)) {
                return kiekerNode;
            }
        }

        return null;
    }

}
