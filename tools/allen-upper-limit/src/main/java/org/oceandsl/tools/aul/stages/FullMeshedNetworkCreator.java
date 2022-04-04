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
package org.oceandsl.tools.aul.stages;

import java.util.Map;

import com.google.common.graph.MutableGraph;

import org.mosim.refactorlizar.architecture.evaluation.graphs.Node;

import kieker.model.analysismodel.deployment.DeployedComponent;

/**
 * @author Reiner Jung
 * @since 1.4
 */
public class FullMeshedNetworkCreator implements INetworkCreator {

    @Override
    public void createEdges(final MutableGraph<Node<DeployedComponent>> graph,
            final Map<Integer, Node<DeployedComponent>> nodes, final Integer numOfNodes) {
        for (int i = 0; i < numOfNodes; i++) {
            for (int j = i + 1; j < numOfNodes; j++) {
                final Node<DeployedComponent> source = nodes.get(i);
                final Node<DeployedComponent> target = nodes.get(j);
                graph.putEdge(source, target);
            }
        }
    }

}
