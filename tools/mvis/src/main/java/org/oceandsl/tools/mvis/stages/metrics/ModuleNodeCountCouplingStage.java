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
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.type.ComponentType;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.code.stages.data.LongValueHandler;
import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;

/**
 * Counts the incoming and outgoing edges for each node. Where nodes represent modules/components in
 * the architecture.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class ModuleNodeCountCouplingStage extends AbstractTransformation<IGraph, Table> {

    @Override
    protected void execute(final IGraph graph) throws Exception {
        final Table result = new Table(graph.getLabel(), new StringValueHandler("module"),
                new LongValueHandler("in-edges"), new LongValueHandler("out-edges"));

        for (final INode vertex : graph.getGraph().nodes()) {
            result.addRow(this.getFilepath(vertex.getId()), graph.getGraph().inDegree(vertex),
                    graph.getGraph().outDegree(vertex));
        }

        this.outputPort.send(result);
    }

    private String getFilepath(final Object id) throws ConfigurationException {
        return this.getComponentType(id).getSignature();
    }

    private ComponentType getComponentType(final Object id) throws ConfigurationException {
        if (id instanceof DeployedComponent) {
            return this.getComponentType(((DeployedComponent) id).getAssemblyComponent());
        } else if (id instanceof AssemblyComponent) {
            return this.getComponentType(((AssemblyComponent) id).getComponentType());
        } else if (id instanceof ComponentType) {
            return (ComponentType) id;
        } else {
            throw new ConfigurationException("Vertex does not relate to an operation.");
        }
    }
}
