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
package org.oceandsl.tools.mop.operations;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.statistics.StatisticsFactory;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;

/**
 * @author Reiner Jung
 *
 */
public class DarModelFactory extends ModelTestFactory {

    private static final String DAR_COMPONENT = "dar-component";

    public static final String DAR_COMPONENT_SIGNATURE = DarModelFactory.PACKAGE + "." + DarModelFactory.DAR_COMPONENT;

    private static final String DAR_ASSEMBLY_NAME = "dar-assembly";
    public static final String DAR_ASSEMBLY_SIGNATURE = DarModelFactory.PACKAGE + "."
            + DarModelFactory.DAR_ASSEMBLY_NAME;

    public static TypeModel createTypeModel() {
        final TypeFactory factory = TypeFactory.eINSTANCE;

        final TypeModel result = factory.createTypeModel();

        final ComponentType component = factory.createComponentType();
        component.setName(DarModelFactory.DAR_COMPONENT);
        component.setPackage(ModelTestFactory.PACKAGE);
        component.setSignature(DarModelFactory.DAR_COMPONENT_SIGNATURE);
        component.getProvidedOperations().put(ModelTestFactory.OP_SIGNATURE,
                ModelTestFactory.createOperationType("operation", "void", ModelTestFactory.OP_SIGNATURE));
        result.getComponentTypes().put(DarModelFactory.DAR_COMPONENT_SIGNATURE, component);

        final ComponentType component2 = factory.createComponentType();
        component2.setName(ModelTestFactory.JOINT_COMPONENT);
        component2.setPackage(ModelTestFactory.PACKAGE);
        component2.setSignature(ModelTestFactory.JOINT_COMPONENT_SIGNATURE);
        component2.getProvidedOperations().put(ModelTestFactory.OP_COMPILE_SIGNATURE,
                ModelTestFactory.createOperationType("compile", "Model", ModelTestFactory.OP_COMPILE_SIGNATURE));
        result.getComponentTypes().put(ModelTestFactory.JOINT_COMPONENT_SIGNATURE, component2);

        return result;
    }

    public static AssemblyModel createAssemblyModel(final TypeModel typeModel) {
        final AssemblyFactory factory = AssemblyFactory.eINSTANCE;

        final AssemblyModel result = factory.createAssemblyModel();

        {
            final ComponentType type = ModelTestFactory.findType(typeModel, DarModelFactory.DAR_COMPONENT_SIGNATURE);
            final AssemblyComponent component = ModelTestFactory
                    .createAssemblyComponent(DarModelFactory.DAR_ASSEMBLY_SIGNATURE, type);
            component.getOperations().put(ModelTestFactory.OP_SIGNATURE, ModelTestFactory
                    .createAssemblyOperation(ModelTestFactory.findOperationType(type, ModelTestFactory.OP_SIGNATURE)));

            result.getAssemblyComponents().put(DarModelFactory.DAR_ASSEMBLY_SIGNATURE, component);
        }

        {
            final ComponentType type = ModelTestFactory.findType(typeModel, ModelTestFactory.JOINT_COMPONENT_SIGNATURE);
            final AssemblyComponent component = ModelTestFactory
                    .createAssemblyComponent(ModelTestFactory.JOINT_ASSEMBLY_SIGNATURE, type);
            component.getOperations().put(ModelTestFactory.OP_COMPILE_SIGNATURE,
                    ModelTestFactory.createAssemblyOperation(
                            ModelTestFactory.findOperationType(type, ModelTestFactory.OP_COMPILE_SIGNATURE)));

            result.getAssemblyComponents().put(ModelTestFactory.JOINT_ASSEMBLY_SIGNATURE, component);
        }

        return result;
    }

    public static DeploymentModel createDeploymentModel(final AssemblyModel assemblyModel) {
        final DeploymentFactory factory = DeploymentFactory.eINSTANCE;

        final DeploymentModel result = factory.createDeploymentModel();

        final DeploymentContext context = ModelTestFactory.createDeploymentContext(ModelTestFactory.HOSTNAME);

        {
            final AssemblyComponent assemblyComponent = ModelTestFactory.findComponent(assemblyModel,
                    DarModelFactory.DAR_ASSEMBLY_SIGNATURE);
            context.getComponents().put(DarModelFactory.DAR_ASSEMBLY_SIGNATURE,
                    ModelTestFactory.createDeploymentComponent(DarModelFactory.DAR_ASSEMBLY_SIGNATURE,
                            assemblyComponent, ModelTestFactory.createDeployedOperation(ModelTestFactory.OP_SIGNATURE,
                                    assemblyComponent)));
        }

        {
            final AssemblyComponent assemblyComponent = ModelTestFactory.findComponent(assemblyModel,
                    ModelTestFactory.JOINT_ASSEMBLY_SIGNATURE);
            context.getComponents().put(ModelTestFactory.JOINT_ASSEMBLY_SIGNATURE,
                    ModelTestFactory.createDeploymentComponent(ModelTestFactory.JOINT_ASSEMBLY_SIGNATURE,
                            assemblyComponent, ModelTestFactory.createDeployedOperation(
                                    ModelTestFactory.OP_COMPILE_SIGNATURE, assemblyComponent)));
        }

        result.getDeploymentContexts().put(ModelTestFactory.HOSTNAME, context);

        return result;
    }

    public static ExecutionModel createExecutionModel(final DeploymentModel deploymentModel) {
        final ExecutionFactory factory = ExecutionFactory.eINSTANCE;

        final ExecutionModel result = factory.createExecutionModel();

        final DeployedOperation operation = ModelTestFactory.findOperation(deploymentModel, ModelTestFactory.HOSTNAME,
                DarModelFactory.DAR_ASSEMBLY_SIGNATURE, ModelTestFactory.OP_SIGNATURE);
        final DeployedOperation compile = ModelTestFactory.findOperation(deploymentModel, ModelTestFactory.HOSTNAME,
                ModelTestFactory.JOINT_ASSEMBLY_SIGNATURE, ModelTestFactory.OP_COMPILE_SIGNATURE);

        // operation -> operation
        ModelTestFactory.createAggregatedInvocation(result.getAggregatedInvocations(), operation, operation);
        // operation -> compile
        ModelTestFactory.createAggregatedInvocation(result.getAggregatedInvocations(), operation, compile);
        ModelTestFactory.createAggregatedInvocation(result.getAggregatedInvocations(), compile, compile);

        return result;
    }

    public static StatisticsModel createStatisticsModel(final ExecutionModel executionModel) {
        final StatisticsFactory factory = StatisticsFactory.eINSTANCE;

        final StatisticsModel result = factory.createStatisticsModel();

        for (final AggregatedInvocation key : executionModel.getAggregatedInvocations().values()) {
            result.getStatistics().put(key, ModelTestFactory.createStatistics());
        }

        return result;
    }

}
