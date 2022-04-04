/***************************************************************************
 * Copyright (C) 2022 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.aul.stages.graph;

import java.util.HashMap;
import java.util.Map;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import org.mosim.refactorlizar.architecture.evaluation.graphs.Node;

import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentFactory;

import teetime.stage.basic.AbstractTransformation;

/**
 *
 * @author Reiner Jung
 * @since 1.4
 */
public class CreateArchitectureModularGraphStage
        extends AbstractTransformation<Integer, Graph<Node<DeployedComponent>>> {

    private final INetworkCreator networkCreator;

    public CreateArchitectureModularGraphStage(final INetworkCreator networkCreator) {
        this.networkCreator = networkCreator;
    }

    @Override
    protected void execute(final Integer numOfNodes) throws Exception {
        final MutableGraph<Node<DeployedComponent>> graph = GraphBuilder.undirected().allowsSelfLoops(true).build();
        final DeployedComponent component = DeploymentFactory.eINSTANCE.createDeployedComponent();
        final Map<Integer, Node<DeployedComponent>> nodes = new HashMap<>();
        for (int i = 0; i < numOfNodes; i++) {
            final DeployedOperation operation = DeploymentFactory.eINSTANCE.createDeployedOperation();
            component.getOperations().put(String.format("func%d", i), operation);
            final Node<DeployedComponent> node = new KiekerNode<>(operation);
            graph.addNode(node);
            nodes.put(i, node);
        }

        this.networkCreator.createEdges(graph, nodes, numOfNodes);

        this.outputPort.send(graph);
    }
}
