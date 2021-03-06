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

import kieker.analysis.generic.graph.IGraph;
import kieker.analysis.generic.graph.INode;
import kieker.common.exception.ConfigurationException;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.type.OperationType;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.LongValueHandler;
import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;

/**
 * Counts the number of incoming and outgoing edges of each node. Nodes represent functions or
 * operations.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class OperationNodeCountCouplingStage extends AbstractTransformation<IGraph, Table> {

    @Override
    protected void execute(final IGraph graph) throws Exception {
        final Table result = new Table(graph.getLabel(), new StringValueHandler("module"),
                new StringValueHandler("operation"), new LongValueHandler("in-edges"),
                new LongValueHandler("out-edges"));

        for (final INode vertex : graph.getGraph().nodes()) {
            result.addRow(this.getFilepath(vertex.getId()), this.getFunction(vertex.getId()),
                    graph.getGraph().inDegree(vertex), graph.getGraph().outDegree(vertex));
        }

        this.outputPort.send(result);
    }

    private String getFilepath(final Object id) throws ConfigurationException {
        return this.getOperationType(id).getComponentType().getSignature();
    }

    private String getFunction(final Object id) throws ConfigurationException {
        return this.getOperationType(id).getSignature();
    }

    private OperationType getOperationType(final Object id) throws ConfigurationException {
        if (id instanceof DeployedOperation) {
            return this.getOperationType(((DeployedOperation) id).getAssemblyOperation());
        } else if (id instanceof AssemblyOperation) {
            return this.getOperationType(((AssemblyOperation) id).getOperationType());
        } else if (id instanceof OperationType) {
            return (OperationType) id;
        } else {
            throw new ConfigurationException("Vertex does not relate to a operation.");
        }
    }

}
