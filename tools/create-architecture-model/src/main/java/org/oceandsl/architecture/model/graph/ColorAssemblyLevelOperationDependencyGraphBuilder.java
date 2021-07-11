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
package org.oceandsl.architecture.model.graph;

import org.oceandsl.architecture.model.ExtraConstants;

import kieker.analysis.graph.IEdge;
import kieker.analysis.graph.IGraph;
import kieker.analysis.graph.IVertex;
import kieker.analysis.graph.dependency.PropertyConstants;
import kieker.analysis.graph.dependency.vertextypes.VertexType;
import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.deployment.DeployedOperation;

/**
 * Dependency graph builder for <strong>operation</strong> dependency graphs at the <strong>assembly
 * level</strong>.
 *
 * @author Reiner jung
 */
public class ColorAssemblyLevelOperationDependencyGraphBuilder extends AbstractColorDependencyGraphBuilder {

    public ColorAssemblyLevelOperationDependencyGraphBuilder(final ModelRepository repository) {
        super(repository);
    }

    @Override
    public IGraph build() {
        return super.build();
    }

    @Override
    protected IVertex addVertex(final DeployedOperation deployedOperation) {
        final AssemblyOperation operation = deployedOperation.getAssemblyOperation();
        final AssemblyComponent component = operation.getAssemblyComponent();

        final int componentId = this.identifierRegistry.getIdentifier(component);
        final IVertex componentVertex = this.graph.addVertexIfAbsent(componentId);
        componentVertex.setPropertyIfAbsent(PropertyConstants.TYPE, VertexType.ASSEMBLY_COMPONENT);
        componentVertex.setPropertyIfAbsent(PropertyConstants.NAME, component.getComponentType().getName());
        componentVertex.setPropertyIfAbsent(PropertyConstants.PACKAGE_NAME, component.getComponentType().getPackage());
        componentVertex.setPropertyIfAbsent(ExtraConstants.FOREGROUND_COLOR, this.selectForegroundColor(component));
        componentVertex.setPropertyIfAbsent(ExtraConstants.BACKGROUND_COLOR, this.selectBackgroundColor(component));

        final IGraph componentSubgraph = componentVertex.addChildGraphIfAbsent();
        final int operationId = this.identifierRegistry.getIdentifier(operation);
        final IVertex operationVertex = componentSubgraph.addVertexIfAbsent(operationId);
        operationVertex.setPropertyIfAbsent(PropertyConstants.TYPE, VertexType.ASSEMBLY_OPERATION);
        operationVertex.setPropertyIfAbsent(PropertyConstants.NAME, operation.getOperationType().getName());
        operationVertex.setPropertyIfAbsent(PropertyConstants.RETURN_TYPE,
                operation.getOperationType().getReturnType());
        operationVertex.setPropertyIfAbsent(PropertyConstants.MODIFIERS, operation.getOperationType().getModifiers());
        operationVertex.setPropertyIfAbsent(PropertyConstants.PARAMETER_TYPES,
                operation.getOperationType().getParameterTypes());
        operationVertex.setPropertyIfAbsent(ExtraConstants.FOREGROUND_COLOR, this.selectForegroundColor(operation));
        componentVertex.setPropertyIfAbsent(ExtraConstants.BACKGROUND_COLOR, this.selectBackgroundColor(operation));

        this.responseTimeDecorator.decorate(operationVertex, operation);

        return operationVertex;
    }

    @Override
    protected IEdge addEdge(final IVertex source, final IVertex target, final long calls) {
        final IEdge edge = super.addEdge(source, target, calls);
        return edge;
    }

}
