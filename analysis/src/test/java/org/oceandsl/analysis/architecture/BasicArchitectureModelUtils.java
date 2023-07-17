/***************************************************************************
 * Copyright (C) 2023 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.analysis.architecture;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.execution.Invocation;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;
import kieker.model.analysismodel.type.TypePackage;

/**
 * @author Reiner Jung
 * @since 1.3.0
 *
 */
public final class BasicArchitectureModelUtils {

    public static final String COMPONENT_A = "A";
    public static final String COMPONENT_B = "B";
    public static final String OPERATION_A_A = "a()";
    public static final String OPERATION_B_A = "b()";
    public static final String BASE = "base";
    public static final String CONTEXT = "test-context";

    private BasicArchitectureModelUtils() {
        // do not instantiate
    }

    public static ModelRepository createMinimalModelRepository() {
        final ModelRepository repository = ArchitectureModelRepositoryFactory.createEmptyModelRepository("test");

        final TypeModel typeModel = createTypeModel(repository);
        final AssemblyModel assemblyModel = createAssemblyModel(repository, typeModel);
        final DeploymentModel deploymentModel = createDeploymentModel(repository, assemblyModel);
        createExecutionModel(repository, deploymentModel);

        return repository;
    }

    private static TypeModel createTypeModel(final ModelRepository repository) {
        final TypeModel typeModel = repository.getModel(TypePackage.Literals.TYPE_MODEL);

        final ComponentType componentA = createComponent(COMPONENT_A);
        createOperation(componentA, OPERATION_A_A);
        final ComponentType componentB = createComponent(COMPONENT_B);
        createOperation(componentB, OPERATION_B_A);

        typeModel.getComponentTypes().put(COMPONENT_A, componentA);
        typeModel.getComponentTypes().put(COMPONENT_B, componentB);

        return typeModel;
    }

    private static void createOperation(final ComponentType component, final String operation) {
        final OperationType operationType = TypeFactory.eINSTANCE.createOperationType();
        operationType.setName(operation);
        operationType.setReturnType("int");
        operationType.setSignature("int " + operation);

        component.getProvidedOperations().put(operation, operationType);
    }

    private static ComponentType createComponent(final String component) {
        final ComponentType componentType = TypeFactory.eINSTANCE.createComponentType();
        componentType.setName(component);
        componentType.setPackage(BASE);

        return componentType;
    }

    private static AssemblyModel createAssemblyModel(final ModelRepository repository, final TypeModel typeModel) {
        final AssemblyModel assemblyModel = repository.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL);

        createAssemblyComponent(typeModel, assemblyModel, COMPONENT_A);
        createAssemblyComponent(typeModel, assemblyModel, COMPONENT_B);

        return assemblyModel;
    }

    private static void createAssemblyComponent(final TypeModel typeModel, final AssemblyModel assemblyModel,
            final String name) {
        final ComponentType typeComponent = typeModel.getComponentTypes().get(name);
        final AssemblyComponent component = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        component.setComponentType(typeComponent);
        typeComponent.getProvidedOperations().values().forEach(operationType -> {
            final AssemblyOperation operation = AssemblyFactory.eINSTANCE.createAssemblyOperation();
            operation.setOperationType(operationType);
            component.getOperations().put(operationType.getSignature(), operation);
        });

        assemblyModel.getComponents().put(name, component);
    }

    private static DeploymentModel createDeploymentModel(final ModelRepository repository,
            final AssemblyModel assemblyModel) {
        final DeploymentModel deploymentModel = repository.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL);

        final DeploymentContext context = DeploymentFactory.eINSTANCE.createDeploymentContext();
        context.setName(CONTEXT);

        deploymentModel.getContexts().put(CONTEXT, context);

        createDeploymentComponent(assemblyModel, deploymentModel, COMPONENT_A);
        createDeploymentComponent(assemblyModel, deploymentModel, COMPONENT_B);

        return deploymentModel;
    }

    private static void createDeploymentComponent(final AssemblyModel assemblyModel,
            final DeploymentModel deploymentModel, final String name) {
        final AssemblyComponent assemblyComponent = assemblyModel.getComponents().get(name);
        final DeployedComponent component = DeploymentFactory.eINSTANCE.createDeployedComponent();
        component.setAssemblyComponent(assemblyComponent);
        assemblyComponent.getOperations().forEach(entry -> {
            final DeployedOperation operation = DeploymentFactory.eINSTANCE.createDeployedOperation();
            operation.setAssemblyOperation(entry.getValue());
            component.getOperations().put(entry.getKey(), operation);
        });

        deploymentModel.getContexts().get(CONTEXT).getComponents().put(name, component);
    }

    private static ExecutionModel createExecutionModel(final ModelRepository repository,
            final DeploymentModel deploymentModel) {
        final ExecutionModel executionModel = repository.getModel(ExecutionPackage.Literals.EXECUTION_MODEL);

        createLink(CONTEXT, COMPONENT_A, OPERATION_A_A, COMPONENT_B, OPERATION_B_A, deploymentModel, executionModel);

        return executionModel;
    }

    private static void createLink(final String contextName, final String componentNameA, final String operationNameA,
            final String componentNameB, final String operationNameB, final DeploymentModel deploymentModel,
            final ExecutionModel executionModel) {
        final DeploymentContext context = deploymentModel.getContexts().get(contextName);
        final DeployedComponent componentA = context.getComponents().get(componentNameA);
        final DeployedComponent componentB = context.getComponents().get(componentNameB);
        final DeployedOperation operationA = componentA.getOperations().get(operationNameA);
        final DeployedOperation operationB = componentB.getOperations().get(operationNameB);

        final Invocation invocation = ExecutionFactory.eINSTANCE.createInvocation();
        invocation.setCaller(operationA);
        invocation.setCallee(operationB);
        final Tuple<DeployedOperation, DeployedOperation> tuple = ExecutionFactory.eINSTANCE.createTuple();
        tuple.setFirst(operationA);
        tuple.setSecond(operationB);

        executionModel.getInvocations().put(tuple, invocation);
    }

}
