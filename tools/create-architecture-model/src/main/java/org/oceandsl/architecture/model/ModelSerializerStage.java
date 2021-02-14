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
package org.oceandsl.architecture.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import kieker.analysis.statistics.Properties;
import kieker.analysis.statistics.StatisticsModel;
import kieker.analysis.statistics.Units;
import kieker.analysis.util.ComposedKey;
import kieker.analysis.util.stage.trigger.Trigger;
import kieker.analysisteetime.model.analysismodel.assembly.AssemblyComponent;
import kieker.analysisteetime.model.analysismodel.assembly.AssemblyModel;
import kieker.analysisteetime.model.analysismodel.assembly.AssemblyOperation;
import kieker.analysisteetime.model.analysismodel.deployment.DeployedComponent;
import kieker.analysisteetime.model.analysismodel.deployment.DeployedOperation;
import kieker.analysisteetime.model.analysismodel.deployment.DeploymentContext;
import kieker.analysisteetime.model.analysismodel.deployment.DeploymentModel;
import kieker.analysisteetime.model.analysismodel.execution.AggregatedInvocation;
import kieker.analysisteetime.model.analysismodel.execution.ExecutionModel;
import kieker.analysisteetime.model.analysismodel.type.ComponentType;
import kieker.analysisteetime.model.analysismodel.type.OperationType;
import kieker.analysisteetime.model.analysismodel.type.TypeModel;
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

    public ModelSerializerStage(final TypeModel typeModel, final AssemblyModel assemblyModel,
            final DeploymentModel deploymentModel, final ExecutionModel executionModel,
            final StatisticsModel statisticsModel, final File outputDirectoryPath) throws IOException {
        this.typeModel = typeModel;
        this.assemblyModel = assemblyModel;
        this.deploymentModel = deploymentModel;
        this.executionModel = executionModel;
        this.statisticsModel = statisticsModel;
        this.outputFile = new File(outputDirectoryPath.getAbsolutePath() + File.separator + "model.json");
    }

    @Override
    protected void execute(final Trigger element) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        final HashMap<String, Object> modelMap = new HashMap<>();

        modelMap.put("type-model", this.createTypeModel());
        modelMap.put("assembly-model", this.createAssemblyModel());
        modelMap.put("deployment-model", this.createDeplyomentModel());
        modelMap.put("execution-aggregate-model", this.createExecutionAggregateModel());

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

    private List<Object> createExecutionAggregateModel() {
        final List<Object> executionAggregateModels = new ArrayList<>();

        for (final Entry<ComposedKey<DeployedOperation, DeployedOperation>, AggregatedInvocation> aggregatedInvocationEntry : this.executionModel
                .getAggregatedInvocations()) {
            final AggregatedInvocation aggregatedInvocation = aggregatedInvocationEntry.getValue();
            final long numberOfcalls = this.statisticsModel.get(aggregatedInvocation).getStatistic(Units.RESPONSE_TIME)
                    .getProperty(Properties.COUNT);

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
