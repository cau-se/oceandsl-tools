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
package org.oceandsl.architecture.model.stages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import kieker.analysis.stage.model.ModelRepository;
import kieker.analysis.util.stage.trigger.Trigger;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.statistics.EPredefinedUnits;
import kieker.model.analysismodel.statistics.EPropertyType;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.TypeModel;
import teetime.framework.AbstractConsumerStage;

/**
 * Store the in memory model in a slightly simplified model on disc.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class ModelSerializerStage extends AbstractConsumerStage<Trigger> {

    private final File outputFile;
    private final TypeModel typeModel;
    private final AssemblyModel assemblyModel;
    private final DeploymentModel deploymentModel;
    private final ExecutionModel executionModel;
    private final StatisticsModel statisticsModel;
    private final SourceModel sourceModel;

    public ModelSerializerStage(final ModelRepository repository, final Path outputDirectoryPath) throws IOException {
        this.typeModel = repository.getModel(TypeModel.class);
        this.assemblyModel = repository.getModel(AssemblyModel.class);
        this.deploymentModel = repository.getModel(DeploymentModel.class);
        this.executionModel = repository.getModel(ExecutionModel.class);
        this.statisticsModel = repository.getModel(StatisticsModel.class);
        this.sourceModel = repository.getModel(SourceModel.class);
        this.outputFile = outputDirectoryPath.resolve(String.format("%s-model.json", repository.getName())).toFile();
    }

    @Override
    protected void execute(final Trigger element) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        final HashMap<String, Object> modelMap = new HashMap<>();

        modelMap.put("type-model", this.createTypeModel());
        modelMap.put("assembly-model", this.createAssemblyModel());
        modelMap.put("deployment-model", this.createDeplyomentModel());
        modelMap.put("execution-aggregate-model", this.createExecutionAggregateModel());
        modelMap.put("source-model", this.createSourceModel());

        objectMapper.writeValue(this.outputFile, modelMap);
    }

    private Map<String, Object> createTypeModel() {
        final Map<String, Object> typeModelMap = new HashMap<>();
        for (final Entry<String, ComponentType> componentTypeEntry : this.typeModel.getComponentTypes()) {
            final ComponentType componentType = componentTypeEntry.getValue();
            final Map<String, Object> componentMap = new HashMap<>();

            componentMap.put("name", componentType.getName());
            componentMap.put("package", componentType.getPackage());
            componentMap.put("signature", componentType.getSignature());

            final Map<String, Object> operationsMap = new HashMap<>();
            for (final Entry<String, OperationType> operationEntry : componentType.getProvidedOperations()) {
                final OperationType operation = operationEntry.getValue();
                final Map<String, Object> operationMap = new HashMap<>();
                operationMap.put("name", operation.getName());
                operationMap.put("signature", operation.getSignature());
                operationMap.put("return-type", operation.getReturnType());
                operationMap.put("modifiers", operation.getModifiers());
                operationMap.put("parameters", operation.getParameterTypes());
                operationsMap.put(operation.getName(), operationMap);
            }
            componentMap.put("operations", operationsMap);
            typeModelMap.put(componentTypeEntry.getKey(), componentMap);
        }

        return typeModelMap;
    }

    private Map<String, Object> createAssemblyModel() {
        final Map<String, Object> assemblyModelMap = new HashMap<>();

        for (final Entry<String, AssemblyComponent> assemblyComponentEntry : this.assemblyModel
                .getAssemblyComponents()) {
            final AssemblyComponent assemblyComponent = assemblyComponentEntry.getValue();
            final Map<String, Object> assemblyComponentMap = new HashMap<>();

            assemblyComponentMap.put("component-type", assemblyComponent.getComponentType().getSignature());

            final Map<String, Object> assemblyOperationsMap = new HashMap<>();
            for (final Entry<String, AssemblyOperation> assemblyOperationEntry : assemblyComponent
                    .getAssemblyOperations()) {
                final AssemblyOperation assemblyOperation = assemblyOperationEntry.getValue();
                final Map<String, Object> assemblyOperationMap = new HashMap<>();

                assemblyOperationMap.put("operation-type", assemblyOperation.getOperationType().getSignature());
                assemblyOperationsMap.put(assemblyOperationEntry.getKey(), assemblyOperationMap);
            }
            assemblyComponentMap.put("assembly-operations", assemblyOperationsMap);

            assemblyModelMap.put(assemblyComponentEntry.getKey(), assemblyComponentMap);
        }

        return assemblyModelMap;
    }

    private Map<String, Object> createDeplyomentModel() {
        final Map<String, Object> deploymentModelMap = new HashMap<>();

        for (final Entry<String, DeploymentContext> deploymentContextEntry : this.deploymentModel
                .getDeploymentContexts()) {
            final DeploymentContext deploymentContext = deploymentContextEntry.getValue();
            final Map<String, Object> deploymentContextMap = new HashMap<>();

            deploymentContextMap.put("deployment-context", deploymentContext.getName());
            final Map<String, Object> deployedComponentsMap = new HashMap<>();
            for (final Entry<String, DeployedComponent> deployedComponentEntry : deploymentContext.getComponents()) {
                final DeployedComponent deployedComponent = deployedComponentEntry.getValue();
                final Map<String, Object> deployedComponentMap = new HashMap<>();

                deployedComponentMap.put("assembly-component",
                        deployedComponent.getAssemblyComponent().getComponentType().getSignature());
                final Map<String, Object> containedOperationsMap = new HashMap<>();
                for (final Entry<String, DeployedOperation> containedOperationEntry : deployedComponent
                        .getContainedOperations()) {
                    final DeployedOperation containedOperation = containedOperationEntry.getValue();
                    final Map<String, Object> containedOperationMap = new HashMap<>();

                    containedOperationMap.put("contained-operation",
                            containedOperation.getAssemblyOperation().getOperationType().getSignature());
                    containedOperationsMap.put(containedOperationEntry.getKey(), containedOperationMap);
                }
                deployedComponentMap.put("contained-operations", containedOperationsMap);
                deployedComponentsMap.put(deployedComponentEntry.getKey(), deployedComponentMap);
            }
            deploymentContextMap.put("deployed-components", deployedComponentsMap);
            deploymentModelMap.put(deploymentContextEntry.getKey(), deploymentContextMap);
        }

        return deploymentModelMap;
    }

    private List<Object> createSourceModel() {
        final List<Object> sourceElementModels = new ArrayList<>();

        for (final Entry<EObject, EList<String>> entry : this.sourceModel.getSources().entrySet()) {
            final Map<String, Object> entryMap = new HashMap<>();
            entryMap.put("reference", this.createReference(entry.getKey()));
            entryMap.put("sources", this.createSourcesList(entry.getValue()));
        }

        return sourceElementModels;
    }

    private String createReference(final EObject key) {
        if (key instanceof DeployedComponent) {
            final DeployedComponent component = (DeployedComponent) key;
            return String.format("%s::%s", component.getDeploymentContext().getName(),
                    this.createReference(component.getAssemblyComponent()));
        } else if (key instanceof DeployedOperation) {
            final DeployedOperation operation = (DeployedOperation) key;
            return String.format("%s/%s", this.createReference(operation.getComponent()),
                    this.createReference(operation.getAssemblyOperation()));
        } else if (key instanceof AssemblyComponent) {
            final AssemblyComponent component = (AssemblyComponent) key;
            return String.format("assembly:%s", this.createReference(component.getComponentType()));
        } else if (key instanceof AssemblyOperation) {
            final AssemblyOperation operation = (AssemblyOperation) key;
            return String.format("assembly:%s", this.createReference(operation.getOperationType()));
        } else if (key instanceof ComponentType) {
            return ((ComponentType) key).getSignature();
        } else if (key instanceof OperationType) {
            return ((OperationType) key).getSignature();
        } else {
            return String.format("-- error -- %s not supported", key.getClass().getCanonicalName());
        }
    }

    private List<String> createSourcesList(final EList<String> list) {
        final List<String> result = new ArrayList<>();
        for (final String element : list) {
            result.add(element);
        }
        return result;
    }

    private List<Object> createExecutionAggregateModel() {
        final List<Object> executionAggregateModels = new ArrayList<>();

        for (final Entry<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> aggregatedInvocationEntry : this.executionModel
                .getAggregatedInvocations()) {
            final AggregatedInvocation aggregatedInvocation = aggregatedInvocationEntry.getValue();
            final long numberOfcalls = (Long) this.statisticsModel.getStatistics().get(aggregatedInvocation)
                    .getStatistics().get(EPredefinedUnits.RESPONSE_TIME).getProperties().get(EPropertyType.COUNT);

            final Map<String, Object> aggregatedLinkMap = new HashMap<>();

            aggregatedLinkMap.put("source",
                    aggregatedInvocation.getSource().getAssemblyOperation().getOperationType().getSignature());
            aggregatedLinkMap.put("target",
                    aggregatedInvocation.getTarget().getAssemblyOperation().getOperationType().getSignature());
            aggregatedLinkMap.put("number-of-calls", numberOfcalls);
            executionAggregateModels.add(aggregatedLinkMap);
        }

        return executionAggregateModels;
    }
}
