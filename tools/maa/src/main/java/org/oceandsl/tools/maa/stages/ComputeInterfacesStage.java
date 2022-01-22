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
package org.oceandsl.tools.maa.stages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;

import kieker.analysis.stage.model.ModelRepository;
import kieker.analysis.util.Tuple;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyProvidedInterface;
import kieker.model.analysismodel.assembly.AssemblyRequiredInterface;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeployedProvidedInterface;
import kieker.model.analysismodel.deployment.DeployedRequiredInterface;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.ProvidedInterfaceType;
import kieker.model.analysismodel.type.RequiredInterfaceType;
import kieker.model.analysismodel.type.TypeFactory;

import teetime.stage.basic.AbstractTransformation;

/**
 * Identify interfaces in the model based on inter-module calls. An interface is defined as all
 * calls that occur from one external component.
 *
 * @author Reiner Jung
 * @since 1.2
 */
public class ComputeInterfacesStage extends AbstractTransformation<ModelRepository, ModelRepository> {

    @Override
    protected void execute(final ModelRepository repository) throws Exception {
        final ExecutionModel executionModel = repository.getModel(ExecutionModel.class);

        final Map<Tuple<DeployedComponent, DeployedComponent>, List<AggregatedInvocation>> interconnections = this
                .collectInterconnections(executionModel.getAggregatedInvocations().values());

        for (final Entry<Tuple<DeployedComponent, DeployedComponent>, List<AggregatedInvocation>> entry : interconnections
                .entrySet()) {
            final DeployedComponent requiringComponent = entry.getKey().getFirst();
            final DeployedComponent providingComponent = entry.getKey().getSecond();

            final DeployedProvidedInterface deployedProvidedInterface = this
                    .findOrCreateDeployedProvidedInterface(providingComponent, requiringComponent, entry.getValue());
            this.linkRequiredInterface(requiringComponent, deployedProvidedInterface);
        }

        this.outputPort.send(repository);
    }

    private DeployedProvidedInterface findOrCreateDeployedProvidedInterface(final DeployedComponent providingComponent,
            final DeployedComponent requiringComponent, final List<AggregatedInvocation> aggregatedInvocations) {
        for (final DeployedRequiredInterface deployedRequiredInterface : requiringComponent.getRequiredInterfaces()) {
            for (final DeployedProvidedInterface deployedProvidedInterface : providingComponent.getProvidedInterfaces()
                    .values()) {
                if (deployedProvidedInterface.equals(deployedRequiredInterface.getRequires())) {
                    return deployedProvidedInterface;
                }
            }
        }

        return this.createDeployedProvidedInterface(providingComponent, aggregatedInvocations);
    }

    private void linkRequiredInterface(final DeployedComponent requiringComponent,
            final DeployedProvidedInterface deployedProvidedInterface) {
        final DeployedRequiredInterface deployedRequiredInterface = DeploymentFactory.eINSTANCE
                .createDeployedRequiredInterface();
        deployedRequiredInterface.setRequiredInterface(this.findOrCreateAssemblyRequiredInterface(
                requiringComponent.getAssemblyComponent(), deployedProvidedInterface.getProvidedInterface()));
        deployedRequiredInterface.setRequires(deployedProvidedInterface);

        requiringComponent.getRequiredInterfaces().add(deployedRequiredInterface);
    }

    private AssemblyRequiredInterface findOrCreateAssemblyRequiredInterface(final AssemblyComponent assemblyComponent,
            final AssemblyProvidedInterface providedInterface) {
        for (final AssemblyRequiredInterface assemblyRequiredInterface : assemblyComponent.getRequiredInterfaces()) {
            if (assemblyRequiredInterface.getRequires().equals(providedInterface)) {
                return assemblyRequiredInterface;
            }
        }

        final AssemblyRequiredInterface assemblyRequiredInterface = AssemblyFactory.eINSTANCE
                .createAssemblyRequiredInterface();
        assemblyRequiredInterface.setRequires(providedInterface);
        assemblyRequiredInterface.setRequiredInterfaceType(this.findOrCreateRequiredInterfaceType(
                assemblyComponent.getComponentType(), providedInterface.getProvidedInterfaceType()));

        assemblyComponent.getRequiredInterfaces().add(assemblyRequiredInterface);

        return assemblyRequiredInterface;
    }

    private RequiredInterfaceType findOrCreateRequiredInterfaceType(final ComponentType componentType,
            final ProvidedInterfaceType providedInterfaceType) {
        for (final RequiredInterfaceType requiredInterfaceType : componentType.getRequiredInterfaceTypes()) {
            if (requiredInterfaceType.getRequires().equals(providedInterfaceType)) {
                return requiredInterfaceType;
            }
        }

        final RequiredInterfaceType requiredInterfaceType = TypeFactory.eINSTANCE.createRequiredInterfaceType();
        requiredInterfaceType.setRequires(providedInterfaceType);

        componentType.getRequiredInterfaceTypes().add(requiredInterfaceType);

        return requiredInterfaceType;
    }

    private DeployedProvidedInterface createDeployedProvidedInterface(final DeployedComponent providingComponent,
            final List<AggregatedInvocation> aggregatedInvocations) {
        final DeployedProvidedInterface deployedProvidedInterface = DeploymentFactory.eINSTANCE
                .createDeployedProvidedInterface();
        deployedProvidedInterface.setProvidedInterface(this.findOrCreateAssemblyProvidedInterface(
                providingComponent.getAssemblyComponent(), aggregatedInvocations));

        for (final AggregatedInvocation aggregatedInvocation : aggregatedInvocations) {
            final DeployedOperation operation = aggregatedInvocation.getTarget();
            deployedProvidedInterface.getProvidedOperations()
                    .put(operation.getAssemblyOperation().getOperationType().getSignature(), operation);
        }

        providingComponent.getProvidedInterfaces().put(
                deployedProvidedInterface.getProvidedInterface().getProvidedInterfaceType().getSignature(),
                deployedProvidedInterface);

        return deployedProvidedInterface;
    }

    private AssemblyProvidedInterface findOrCreateAssemblyProvidedInterface(final AssemblyComponent providingComponent,
            final List<AggregatedInvocation> aggregatedInvocations) {
        final AssemblyProvidedInterface assemblyProvidedInterface = this.findAssemblyProvidedInterface(
                providingComponent.getProvidedInterfaces().values(), aggregatedInvocations);
        if (assemblyProvidedInterface == null) {
            return this.createAssemblyProvidedInterface(providingComponent, aggregatedInvocations);
        } else {
            return assemblyProvidedInterface;
        }
    }

    private AssemblyProvidedInterface createAssemblyProvidedInterface(final AssemblyComponent providingComponent,
            final List<AggregatedInvocation> aggregatedInvocations) {
        final AssemblyProvidedInterface assemblyProvidedInterface = AssemblyFactory.eINSTANCE
                .createAssemblyProvidedInterface();
        assemblyProvidedInterface.setProvidedInterfaceType(
                this.findOrCreateProvidedInterfaceType(providingComponent.getComponentType(), aggregatedInvocations));

        for (final AggregatedInvocation aggregatedInvocation : aggregatedInvocations) {
            final DeployedOperation operation = aggregatedInvocation.getTarget();
            assemblyProvidedInterface.getProvidedOperations().put(
                    operation.getAssemblyOperation().getOperationType().getSignature(),
                    operation.getAssemblyOperation());
        }
        providingComponent.getProvidedInterfaces()
                .put(assemblyProvidedInterface.getProvidedInterfaceType().getSignature(), assemblyProvidedInterface);

        return assemblyProvidedInterface;
    }

    private AssemblyProvidedInterface findAssemblyProvidedInterface(
            final Collection<AssemblyProvidedInterface> assemblyProvidedInterfaces,
            final List<AggregatedInvocation> aggregatedInvocations) {
        for (final AssemblyProvidedInterface assemblyProvidedInterface : assemblyProvidedInterfaces) {
            if (this.assemblyProvidedInterfaceMatchFunctions(assemblyProvidedInterface, aggregatedInvocations)) {
                return assemblyProvidedInterface;
            }
        }
        return null;
    }

    private boolean assemblyProvidedInterfaceMatchFunctions(final AssemblyProvidedInterface assemblyProvidedInterface,
            final List<AggregatedInvocation> aggregatedInvocations) {
        for (final AggregatedInvocation aggregatedInvocation : aggregatedInvocations) {
            final String signature = aggregatedInvocation.getTarget().getAssemblyOperation().getOperationType()
                    .getSignature();
            if (!assemblyProvidedInterface.getProvidedOperations().containsKey(signature)) {
                return false;
            }
        }
        return true;
    }

    private ProvidedInterfaceType findOrCreateProvidedInterfaceType(final ComponentType componentType,
            final List<AggregatedInvocation> aggregatedInvocations) {
        final ProvidedInterfaceType providedInterfaceType = this
                .findProvidedInterfaceType(componentType.getProvidedInterfaceTypes(), aggregatedInvocations);
        if (providedInterfaceType == null) {
            return this.createProvidedInterfaceType(componentType, aggregatedInvocations);
        } else {
            return providedInterfaceType;
        }
    }

    private ProvidedInterfaceType createProvidedInterfaceType(final ComponentType componentType,
            final List<AggregatedInvocation> aggregatedInvocations) {
        final ProvidedInterfaceType providedInterfaceType = TypeFactory.eINSTANCE.createProvidedInterfaceType();
        providedInterfaceType.setName(componentType.getName() + componentType.getProvidedInterfaceTypes().size());
        providedInterfaceType.setPackage(componentType.getPackage());
        providedInterfaceType.setSignature(providedInterfaceType.getPackage() + "." + providedInterfaceType.getName());

        for (final AggregatedInvocation aggregatedInvocation : aggregatedInvocations) {
            final DeployedOperation operation = aggregatedInvocation.getTarget();
            providedInterfaceType.getProvidedOperationTypes().put(
                    operation.getAssemblyOperation().getOperationType().getSignature(),
                    operation.getAssemblyOperation().getOperationType());
        }

        componentType.getProvidedInterfaceTypes().add(providedInterfaceType);

        return providedInterfaceType;
    }

    private ProvidedInterfaceType findProvidedInterfaceType(final EList<ProvidedInterfaceType> providedInterfaceTypes,
            final List<AggregatedInvocation> aggregatedInvocations) {
        for (final ProvidedInterfaceType providedInterfaceType : providedInterfaceTypes) {
            if (this.providedInterfaceTypeMatchFunctions(providedInterfaceType, aggregatedInvocations)) {
                return providedInterfaceType;
            }
        }
        return null;
    }

    private boolean providedInterfaceTypeMatchFunctions(final ProvidedInterfaceType providedInterfaceType,
            final List<AggregatedInvocation> aggregatedInvocations) {
        for (final AggregatedInvocation aggregatedInvocation : aggregatedInvocations) {
            final String signature = aggregatedInvocation.getTarget().getAssemblyOperation().getOperationType()
                    .getSignature();
            if (!providedInterfaceType.getProvidedOperationTypes().containsKey(signature)) {
                return false;
            }
        }
        return true;
    }

    private Map<Tuple<DeployedComponent, DeployedComponent>, List<AggregatedInvocation>> collectInterconnections(
            final Collection<AggregatedInvocation> aggregatedInvocations) {
        final Map<Tuple<DeployedComponent, DeployedComponent>, List<AggregatedInvocation>> interconnections = new HashMap<>();

        for (final AggregatedInvocation aggregatedInvocation : aggregatedInvocations) {
            if (!aggregatedInvocation.getSource().getComponent()
                    .equals(aggregatedInvocation.getTarget().getComponent())) {
                final Tuple<DeployedComponent, DeployedComponent> key = new Tuple<>(
                        aggregatedInvocation.getSource().getComponent(),
                        aggregatedInvocation.getTarget().getComponent());
                final List<AggregatedInvocation> connections = interconnections.get(key);
                if (connections == null) {
                    final List<AggregatedInvocation> newConnections = new ArrayList<>();
                    newConnections.add(aggregatedInvocation);
                    interconnections.put(key, newConnections);
                } else {
                    connections.add(aggregatedInvocation);
                }
            }
        }

        return interconnections;
    }

}
