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
package org.oceandsl.tools.mvis.stages.metrics;

import org.oceandsl.analysis.code.stages.data.LongValueHandler;
import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;

import kieker.analysis.generic.graph.IEdge;
import kieker.analysis.generic.graph.IGraph;
import kieker.analysis.generic.graph.INode;
import kieker.common.exception.ConfigurationException;
import teetime.stage.basic.AbstractTransformation;

/**
 * Counts the number of incoming and outgoing edges of each node. Nodes represent functions or
 * operations.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class OperationNodeCountCouplingStage extends AbstractTransformation<IGraph<INode, IEdge>, Table> {

    @Override
    protected void execute(final IGraph<INode, IEdge> graph) throws Exception {
        final Table result = new Table(graph.getLabel(), new StringValueHandler("module"),
                new StringValueHandler("operation"), new LongValueHandler("in-edges"),
                new LongValueHandler("out-edges"));

        for (final INode vertex : graph.getGraph().nodes()) {
            result.addRow(this.getFilepath(vertex.getId()), this.getFunction(vertex.getId()),
                    graph.getGraph().inDegree(vertex), graph.getGraph().outDegree(vertex));
        }

        this.outputPort.send(result);
    }

    private String getFilepath(final Object id) {
        final String stringId = (String) id;
        String filepath = stringId.split("\\.f")[0];
        filepath = filepath.split("@")[1];
        return filepath.concat(".f");
    }

    private String getFunction(final Object id) throws ConfigurationException {
        final String stringId = (String) id;
        final String[] filepath = stringId.split("\\.");
        return filepath[filepath.length - 1];
    }

}
