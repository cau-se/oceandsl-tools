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
import java.util.List;
import java.util.Map.Entry;

import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;

/**
 * @author Reiner Jung
 * @since 1.2
 */
public final class TestModelRepositoryUtils {

    public static final String PACKAGE_NAME = "a.generic.package";

    public static final String COMPONENT_A = "component-A";
    public static final String FQN_COMPONENT_A = TestModelRepositoryUtils.PACKAGE_NAME + "."
            + TestModelRepositoryUtils.COMPONENT_A;
    public static final String COMPONENT_B = "component-B";
    public static final String FQN_COMPONENT_B = TestModelRepositoryUtils.PACKAGE_NAME + "."
            + TestModelRepositoryUtils.COMPONENT_B;
    public static final String COMPONENT_C = "component-C";
    public static final String FQN_COMPONENT_C = TestModelRepositoryUtils.PACKAGE_NAME + "."
            + TestModelRepositoryUtils.COMPONENT_C;

    public static final String OP_A_NAME_MAIN = "main";
    public static final String OP_A_NAME_SECOND = "second";
    public static final String OP_RETURN = "Type";
    public static final String OP_A_NAME_MAIN_SIGNATURE = String.format("%s %s (Type a)",
            TestModelRepositoryUtils.OP_RETURN, TestModelRepositoryUtils.OP_A_NAME_MAIN);
    public static final String OP_A_NAME_SECOND_SIGNATURE = String.format("%s %s (Type a)",
            TestModelRepositoryUtils.OP_RETURN, TestModelRepositoryUtils.OP_A_NAME_SECOND);
    public static final String OP_B_NAME = "doB";
    public static final String OP_B_NAME_SIGNATURE = String.format("%s %s (Type b)", TestModelRepositoryUtils.OP_RETURN,
            TestModelRepositoryUtils.OP_B_NAME);
    public static final String OP_C_NAME = "doC";
    public static final String OP_C_NAME_SIGNATURE = String.format("%s %s (Type c)", TestModelRepositoryUtils.OP_RETURN,
            TestModelRepositoryUtils.OP_C_NAME);
    public static final String CONTEXT_A = "context";

    private TestModelRepositoryUtils() {
        // utility class for models
    }

    public static ModelRepository createThreeComponentModel() {
        final ModelRepository repository = new ModelRepository("test");

        final TypeModel typeModel = TestModelRepositoryUtils.createTypeModel();
        final AssemblyModel assemblyModel = TestModelRepositoryUtils.createAssemblyModel(typeModel);
        final DeploymentModel deploymentModel = TestModelRepositoryUtils.createDeploymentModel(assemblyModel);

        repository.register(TypeModel.class, typeModel);
        repository.register(AssemblyModel.class, assemblyModel);
        repository.register(DeploymentModel.class, deploymentModel);

        return repository;
    }

    private static DeploymentModel createDeploymentModel(final AssemblyModel assemblyModel) {
        final DeploymentModel deploymentModel = DeploymentFactory.eINSTANCE.createDeploymentModel();

        deploymentModel.getContexts().put(TestModelRepositoryUtils.CONTEXT_A,
                TestModelRepositoryUtils.createDeploymentContext(TestModelRepositoryUtils.CONTEXT_A, assemblyModel));

        return deploymentModel;
    }

    private static DeploymentContext createDeploymentContext(final String name, final AssemblyModel assemblyModel) {
        final DeploymentContext deploymentContext = DeploymentFactory.eINSTANCE.createDeploymentContext();
        deploymentContext.setName(name);

        for (final Entry<String, AssemblyComponent> entry : assemblyModel.getComponents().entrySet()) {
            deploymentContext.getComponents().put(entry.getKey(),
                    TestModelRepositoryUtils.createDeploymentComponent(entry.getKey(), entry.getValue()));
        }

        return deploymentContext;
    }

    private static DeployedComponent createDeploymentComponent(final String signature,
            final AssemblyComponent assemblyComponent) {
        final DeployedComponent deployedComponent = DeploymentFactory.eINSTANCE.createDeployedComponent();
        deployedComponent.setAssemblyComponent(assemblyComponent);
        deployedComponent.setSignature(signature);

        for (final Entry<String, AssemblyOperation> entry : assemblyComponent.getOperations().entrySet()) {
            deployedComponent.getOperations().put(entry.getKey(),
                    TestModelRepositoryUtils.createDeployedOperation(entry.getValue()));
        }

        return deployedComponent;
    }

    private static DeployedOperation createDeployedOperation(final AssemblyOperation assemblyOperation) {
        final DeployedOperation deployedOperation = DeploymentFactory.eINSTANCE.createDeployedOperation();
        deployedOperation.setAssemblyOperation(assemblyOperation);
        return deployedOperation;
    }

    private static AssemblyModel createAssemblyModel(final TypeModel typeModel) {
        final AssemblyModel assemblyModel = AssemblyFactory.eINSTANCE.createAssemblyModel();

        assemblyModel.getComponents().put(TestModelRepositoryUtils.FQN_COMPONENT_A + ":1", TestModelRepositoryUtils
                .createAssemblyComponent(typeModel, TestModelRepositoryUtils.FQN_COMPONENT_A, ":1"));
        assemblyModel.getComponents().put(TestModelRepositoryUtils.FQN_COMPONENT_B + ":1", TestModelRepositoryUtils
                .createAssemblyComponent(typeModel, TestModelRepositoryUtils.FQN_COMPONENT_B, ":1"));
        assemblyModel.getComponents().put(TestModelRepositoryUtils.FQN_COMPONENT_C + ":1", TestModelRepositoryUtils
                .createAssemblyComponent(typeModel, TestModelRepositoryUtils.FQN_COMPONENT_C, ":1"));

        return assemblyModel;
    }

    private static AssemblyComponent createAssemblyComponent(final TypeModel typeModel, final String typeName,
            final String instanceName) {
        final ComponentType componentType = typeModel.getComponentTypes().get(typeName);
        final AssemblyComponent assemblyComponent = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        assemblyComponent.setComponentType(componentType);
        assemblyComponent.setSignature(typeName + instanceName);

        for (final Entry<String, OperationType> entry : componentType.getProvidedOperations().entrySet()) {
            assemblyComponent.getOperations().put(entry.getKey(),
                    TestModelRepositoryUtils.createAssemblyOperation(entry.getValue()));
        }
        return assemblyComponent;
    }

    private static AssemblyOperation createAssemblyOperation(final OperationType value) {
        final AssemblyOperation assemblyOperation = AssemblyFactory.eINSTANCE.createAssemblyOperation();
        assemblyOperation.setOperationType(value);
        return assemblyOperation;
    }

    private static TypeModel createTypeModel() {
        final TypeModel typeModel = TypeFactory.eINSTANCE.createTypeModel();

        typeModel.getComponentTypes().put(TestModelRepositoryUtils.FQN_COMPONENT_A, TestModelRepositoryUtils
                .createComponent(TestModelRepositoryUtils.COMPONENT_A, TestModelRepositoryUtils.createAOperations()));
        typeModel.getComponentTypes().put(TestModelRepositoryUtils.FQN_COMPONENT_B, TestModelRepositoryUtils
                .createComponent(TestModelRepositoryUtils.COMPONENT_B, TestModelRepositoryUtils.createBOperations()));
        typeModel.getComponentTypes().put(TestModelRepositoryUtils.FQN_COMPONENT_C, TestModelRepositoryUtils
                .createComponent(TestModelRepositoryUtils.COMPONENT_C, TestModelRepositoryUtils.createCOperations()));

        return typeModel;
    }

    private static List<OperationType> createAOperations() {
        final List<OperationType> operationTypes = new ArrayList<>();
        operationTypes.add(TestModelRepositoryUtils.creatOperation(TestModelRepositoryUtils.OP_A_NAME_MAIN,
                TestModelRepositoryUtils.OP_RETURN, TestModelRepositoryUtils.OP_A_NAME_MAIN_SIGNATURE));
        operationTypes.add(TestModelRepositoryUtils.creatOperation(TestModelRepositoryUtils.OP_A_NAME_SECOND,
                TestModelRepositoryUtils.OP_RETURN, TestModelRepositoryUtils.OP_A_NAME_SECOND_SIGNATURE));
        return operationTypes;
    }

    private static List<OperationType> createBOperations() {
        final List<OperationType> operationTypes = new ArrayList<>();
        operationTypes.add(TestModelRepositoryUtils.creatOperation(TestModelRepositoryUtils.OP_B_NAME,
                TestModelRepositoryUtils.OP_RETURN, TestModelRepositoryUtils.OP_B_NAME_SIGNATURE));
        return operationTypes;
    }

    private static List<OperationType> createCOperations() {
        final List<OperationType> operationTypes = new ArrayList<>();
        operationTypes.add(TestModelRepositoryUtils.creatOperation(TestModelRepositoryUtils.OP_C_NAME,
                TestModelRepositoryUtils.OP_RETURN, TestModelRepositoryUtils.OP_C_NAME_SIGNATURE));
        return operationTypes;
    }

    private static OperationType creatOperation(final String name, final String returnType, final String signature) {
        final OperationType operationType = TypeFactory.eINSTANCE.createOperationType();

        operationType.setName(name);
        operationType.setReturnType(returnType);
        operationType.setSignature(signature);

        return operationType;
    }

    private static ComponentType createComponent(final String name, final List<OperationType> operationTypes) {
        final ComponentType componentType = TypeFactory.eINSTANCE.createComponentType();
        componentType.setName(name);
        componentType.setPackage(TestModelRepositoryUtils.PACKAGE_NAME);
        componentType.setSignature(TestModelRepositoryUtils.PACKAGE_NAME + "." + name);

        for (final OperationType operationType : operationTypes) {
            componentType.getProvidedOperations().put(operationType.getSignature(), operationType);
        }

        return componentType;
    }

}
