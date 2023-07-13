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
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeploymentModel;
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

    private BasicArchitectureModelUtils() {
        // do not instantiate
    }

    public static ModelRepository createMinimalModelRepository() {
        final ModelRepository repository = ArchitectureModelRepositoryFactory.createEmptyModelRepository("test");

        final TypeModel typeModel = createTypeModel(repository);
        final AssemblyModel assemblyModel = createAssemblyModel(repository, typeModel);
        final DeploymentModel deploymentModel = createDeploymentModel(repository, assemblyModel, typeModel);

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

        final AssemblyComponent componentA = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        componentA.setComponentType(typeModel.getComponentTypes().get(COMPONENT_A));
        assemblyModel.getComponents().put(COMPONENT_A, componentA);
        final AssemblyComponent componentB = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        componentB.setComponentType(typeModel.getComponentTypes().get(COMPONENT_B));
        assemblyModel.getComponents().put(COMPONENT_B, componentB);

        return assemblyModel;
    }

}
