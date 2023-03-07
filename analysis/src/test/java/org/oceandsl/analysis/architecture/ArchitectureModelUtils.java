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

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Invocation;
import kieker.model.analysismodel.execution.OperationDataflow;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.statistics.StatisticRecord;
import kieker.model.analysismodel.statistics.StatisticsFactory;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;

/**
 * @author Reiner Jung
 *
 */
public final class ArchitectureModelUtils {

    public static final String CP_LEFT = "Left";
    public static final String PACKAGE = "test";
    public static final String CP_LEFT_FQN = PACKAGE + "." + CP_LEFT;
    public static final String OP_LEFT = "op1";
    public static final String OP_LEFT_SIGNATURE = "V op1 (T, B)";
    public static final String OP_RETURN_TYPE = "V";
    public static final String OP_RIGHT = "op2";
    public static final String OP_RIGHT_SIGNATURE = "V op2 (T, B)";
    public static final String CP_RIGHT = "Right";
    public static final String CP_RIGHT_FQN = PACKAGE + "." + CP_RIGHT;
    public static final String CONTEXT = "context";
    public static final String DATAFLOW = "dataflow";

    private ArchitectureModelUtils() {
        // private constructor to block instantiation of utility class
    }

    public static TypeModel createTypeModel() {
        final TypeModel model = TypeFactory.eINSTANCE.createTypeModel();

        final ComponentType componentType = createComponentType(CP_LEFT, CP_LEFT_FQN);

        final OperationType operationType = createOperationType(OP_LEFT, OP_LEFT_SIGNATURE, OP_RETURN_TYPE);
        componentType.getProvidedOperations().put(operationType.getSignature(), operationType);

        model.getComponentTypes().put(componentType.getSignature(), componentType);

        final ComponentType componentType2 = createComponentType(CP_RIGHT, CP_RIGHT_FQN);

        final OperationType operationType2 = createOperationType(OP_RIGHT, OP_RIGHT_SIGNATURE, OP_RETURN_TYPE);
        componentType.getProvidedOperations().put(operationType2.getSignature(), operationType2);

        model.getComponentTypes().put(componentType2.getSignature(), componentType2);

        return model;
    }

    private static ComponentType createComponentType(final String name, final String fqnName) {
        final ComponentType componentType = TypeFactory.eINSTANCE.createComponentType();
        componentType.setName(name);
        componentType.setPackage(PACKAGE);
        componentType.setSignature(fqnName);
        return componentType;
    }

    private static OperationType createOperationType(final String name, final String signature,
            final String returnType) {
        final OperationType operationType = TypeFactory.eINSTANCE.createOperationType();
        operationType.setName(name);
        operationType.setSignature(signature);
        operationType.setReturnType(returnType);

        return operationType;
    }

    /*
     * assembly
     */

    public static AssemblyModel createAssemblyModel(final TypeModel typeModel) {
        final AssemblyModel model = AssemblyFactory.eINSTANCE.createAssemblyModel();

        final AssemblyComponent component = createAssemblyComponent(CP_LEFT_FQN, typeModel);
        component.getOperations().put(OP_LEFT_SIGNATURE,
                createAssemblyOperation(OP_LEFT_SIGNATURE, component.getComponentType()));
        model.getComponents().put(CP_LEFT_FQN, component);

        final AssemblyComponent component2 = createAssemblyComponent(CP_RIGHT_FQN, typeModel);
        component2.getOperations().put(OP_RIGHT_SIGNATURE,
                createAssemblyOperation(OP_RIGHT_SIGNATURE, component2.getComponentType()));
        model.getComponents().put(CP_RIGHT_FQN, component2);

        return model;
    }

    private static AssemblyComponent createAssemblyComponent(final String fqnName, final TypeModel typeModel) {
        final AssemblyComponent component = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        final ComponentType componentType = typeModel.getComponentTypes().get(CP_LEFT_FQN);
        component.setComponentType(componentType);
        component.setSignature(fqnName);

        return component;
    }

    private static AssemblyOperation createAssemblyOperation(final String signature,
            final ComponentType componentType) {
        final AssemblyOperation operation = AssemblyFactory.eINSTANCE.createAssemblyOperation();
        operation.setOperationType(componentType.getProvidedOperations().get(signature));

        return operation;
    }

    /*
     * deployment
     */

    public static DeploymentModel createDeploymentModel(final AssemblyModel assemblyModel) {
        final DeploymentModel model = DeploymentFactory.eINSTANCE.createDeploymentModel();

        final DeploymentContext context = DeploymentFactory.eINSTANCE.createDeploymentContext();
        context.setName(CONTEXT);

        model.getContexts().put(CONTEXT, context);

        final DeployedComponent component = createComponent(CP_LEFT_FQN, assemblyModel);
        component.getOperations().put(OP_LEFT_SIGNATURE, createDeployedOperation(OP_LEFT_SIGNATURE, component));

        context.getComponents().put(CP_LEFT_FQN, component);

        final DeployedComponent component2 = createComponent(CP_RIGHT_FQN, assemblyModel);
        component2.getOperations().put(OP_RIGHT_SIGNATURE, createDeployedOperation(OP_RIGHT_SIGNATURE, component2));
        context.getComponents().put(CP_RIGHT_FQN, component2);

        return model;
    }

    private static DeployedComponent createComponent(final String fqnName, final AssemblyModel assemblyModel) {
        final DeployedComponent component = DeploymentFactory.eINSTANCE.createDeployedComponent();
        component.setSignature(fqnName);
        component.setAssemblyComponent(assemblyModel.getComponents().get(fqnName));

        return component;
    }

    private static DeployedOperation createDeployedOperation(final String signature,
            final DeployedComponent component) {
        final DeployedOperation operation = DeploymentFactory.eINSTANCE.createDeployedOperation();
        operation.setAssemblyOperation(component.getAssemblyComponent().getOperations().get(signature));

        return operation;
    }

    /*
     * execution
     */

    public static ExecutionModel createExecutionModel(final DeploymentModel deploymentModel) {
        final ExecutionModel model = ExecutionFactory.eINSTANCE.createExecutionModel();

        final DeployedOperation caller = deploymentModel.getContexts().get(CONTEXT).getComponents().get(CP_LEFT_FQN)
                .getOperations().get(OP_LEFT_SIGNATURE);
        final DeployedOperation callee = deploymentModel.getContexts().get(CONTEXT).getComponents().get(CP_RIGHT_FQN)
                .getOperations().get(OP_RIGHT_SIGNATURE);

        final Tuple<DeployedOperation, DeployedOperation> tuple = ExecutionFactory.eINSTANCE.createTuple();
        final OperationDataflow dataflow = ExecutionFactory.eINSTANCE.createOperationDataflow();
        dataflow.setCallee(callee);
        dataflow.setCaller(caller);

        final Invocation invocation = ExecutionFactory.eINSTANCE.createInvocation();
        invocation.setCaller(caller);
        invocation.setCallee(callee);

        model.getOperationDataflows().put(tuple, dataflow);
        model.getInvocations().put(tuple, invocation);

        return model;
    }

    /*
     * statistics
     */

    public static StatisticsModel createEmptyStatisticsModel(final ExecutionModel executionModel) {
        final StatisticsModel model = StatisticsFactory.eINSTANCE.createStatisticsModel();
        return model;
    }

    public static StatisticsModel createStatisticsModel(final ExecutionModel executionModel) {
        final StatisticsModel model = StatisticsFactory.eINSTANCE.createStatisticsModel();
        executionModel.getOperationDataflows().values().forEach(dataflow -> {
            final StatisticRecord statisticRecord = StatisticsFactory.eINSTANCE.createStatisticRecord();
            statisticRecord.getProperties().put(DATAFLOW, 1);
            model.getStatistics().put(dataflow, statisticRecord);
        });
        return model;
    }
}
