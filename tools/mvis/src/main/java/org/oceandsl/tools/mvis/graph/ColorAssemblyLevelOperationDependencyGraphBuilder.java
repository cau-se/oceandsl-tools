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
package org.oceandsl.tools.mvis.graph;

import kieker.analysis.architecture.dependency.PropertyConstants;
import kieker.analysis.architecture.dependency.VertexType;
import kieker.analysis.generic.graph.GraphFactory;
import kieker.analysis.generic.graph.IGraph;
import kieker.analysis.generic.graph.INode;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyStorage;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedStorage;
import org.oceandsl.analysis.graph.IGraphElementSelector;
import org.oceandsl.tools.mvis.FullyQualifiedNamesFactory;

import java.util.Optional;

/**
 * Dependency graph builder for <strong>operation</strong> dependency graphs at the <strong>assembly
 * level</strong>.
 *
 * @author Reiner jung
 */
public class ColorAssemblyLevelOperationDependencyGraphBuilder extends AbstractColorDependencyGraphBuilder {

    public ColorAssemblyLevelOperationDependencyGraphBuilder(final IGraphElementSelector selector) {
        super(selector);
    }

    @Override
    protected INode addVertex(final DeployedOperation deployedOperation) {
        final AssemblyOperation operation = deployedOperation.getAssemblyOperation();
        final AssemblyComponent component = operation.getComponent();

        final INode componentVertex = this.addVertexIfAbsent(this.graph, component);
        componentVertex.setPropertyIfAbsent(PropertyConstants.TYPE, VertexType.ASSEMBLY_COMPONENT);
        componentVertex.setPropertyIfAbsent(PropertyConstants.NAME, component.getComponentType().getName());
        componentVertex.setPropertyIfAbsent(PropertyConstants.PACKAGE_NAME, component.getComponentType().getPackage());
        componentVertex.setPropertyIfAbsent(ExtraConstantsUtils.FOREGROUND_COLOR,
                this.selectForegroundColor(component));
        componentVertex.setPropertyIfAbsent(ExtraConstantsUtils.BACKGROUND_COLOR,
                this.selectBackgroundColor(component));

        final IGraph componentSubgraph = this.addChildGraphIfAbsent(componentVertex);
        final INode operationVertex = this.addVertexIfAbsent(componentSubgraph, operation);
        operationVertex.setPropertyIfAbsent(PropertyConstants.TYPE, VertexType.ASSEMBLY_OPERATION);
        operationVertex.setPropertyIfAbsent(PropertyConstants.NAME, operation.getOperationType().getName());
        operationVertex.setPropertyIfAbsent(PropertyConstants.RETURN_TYPE,
                operation.getOperationType().getReturnType());
        operationVertex.setPropertyIfAbsent(PropertyConstants.MODIFIERS, operation.getOperationType().getModifiers());
        operationVertex.setPropertyIfAbsent(PropertyConstants.PARAMETER_TYPES,
                operation.getOperationType().getParameterTypes());
        operationVertex.setPropertyIfAbsent(ExtraConstantsUtils.FOREGROUND_COLOR,
                this.selectForegroundColor(operation));
        operationVertex.setPropertyIfAbsent(ExtraConstantsUtils.BACKGROUND_COLOR,
                this.selectBackgroundColor(operation));

        this.responseTimeDecorator.decorate(operationVertex, operation);

        return operationVertex;
    }

    @Override
    protected INode addStorageVertex(final DeployedStorage deployedStorage) {
        final AssemblyStorage storage = deployedStorage.getAssemblyStorage();
        final AssemblyComponent component = storage.getComponent();

        final INode componentVertex = this.addVertexIfAbsent(this.graph, component);
        componentVertex.setPropertyIfAbsent(PropertyConstants.TYPE, VertexType.ASSEMBLY_COMPONENT);
        componentVertex.setPropertyIfAbsent(PropertyConstants.NAME, component.getComponentType().getName());
        componentVertex.setPropertyIfAbsent(PropertyConstants.PACKAGE_NAME, component.getComponentType().getPackage());
        componentVertex.setPropertyIfAbsent(ExtraConstantsUtils.FOREGROUND_COLOR,
                this.selectForegroundColor(component));
        componentVertex.setPropertyIfAbsent(ExtraConstantsUtils.BACKGROUND_COLOR,
                this.selectBackgroundColor(component));

        final IGraph componentSubgraph = this.addChildGraphIfAbsent(componentVertex);
        final INode accessVertex = this.addVertexIfAbsent(componentSubgraph, storage);
        accessVertex.setPropertyIfAbsent(PropertyConstants.TYPE, VertexType.ASSEMBLY_STORAGE);
        accessVertex.setPropertyIfAbsent(PropertyConstants.NAME, storage.getStorageType().getName());
        accessVertex.setPropertyIfAbsent(PropertyConstants.RETURN_TYPE, storage.getStorageType().getType());
        accessVertex.setPropertyIfAbsent(ExtraConstantsUtils.FOREGROUND_COLOR, this.selectForegroundColor(storage));
        componentVertex.setPropertyIfAbsent(ExtraConstantsUtils.BACKGROUND_COLOR, this.selectBackgroundColor(storage));

        this.responseTimeDecorator.decorate(accessVertex, storage);

        return accessVertex;
    }

    protected INode addVertexIfAbsent(final IGraph localGraph, final AssemblyComponent component) {
        final String name = FullyQualifiedNamesFactory.createFullyQualifiedName(component);
        final Optional<INode> nodeOptional = localGraph.getGraph().nodes().stream()
                .filter(node -> name.equals(node.getId())).findFirst();
        if (nodeOptional.isEmpty()) {
            final INode node = GraphFactory.createNode(name);
            localGraph.getGraph().addNode(node);
            return node;
        }
        return nodeOptional.get();
    }

    protected INode addVertexIfAbsent(final IGraph localGraph, final AssemblyOperation operation) {
        final String name = FullyQualifiedNamesFactory.createFullyQualifiedName(operation);
        final Optional<INode> nodeOptional = localGraph.getGraph().nodes().stream()
                .filter(node -> name.equals(node.getId())).findFirst();
        if (nodeOptional.isEmpty()) {
            final INode node = GraphFactory.createNode(name);
            localGraph.getGraph().addNode(node);
            return node;
        }
        return nodeOptional.get();
    }

    protected INode addVertexIfAbsent(final IGraph localGraph, final AssemblyStorage storage) {
        final String name = FullyQualifiedNamesFactory.createFullyQualifiedName(storage);
        final Optional<INode> nodeOptional = localGraph.findNode(name);
        if (nodeOptional.isEmpty()) {
            final INode node = GraphFactory.createNode(name);
            localGraph.getGraph().addNode(node);
            return node;
        }
        return nodeOptional.get();
    }
}
