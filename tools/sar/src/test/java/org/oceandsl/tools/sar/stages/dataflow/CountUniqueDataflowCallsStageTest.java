/***************************************************************************
 * Copyright (C) 2022 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.sar.stages.dataflow;

import java.time.Duration;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import kieker.analysis.architecture.recovery.events.OperationEvent;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.EDirection;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.OperationDataflow;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.statistics.StatisticRecord;
import kieker.model.analysismodel.statistics.StatisticsFactory;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;

import teetime.framework.test.StageTester;

class CountUniqueDataflowCallsStageTest {

    private static final String CP_LEFT = "Left";
    private static final String PACKAGE = "test";
    private static final String CP_LEFT_FQN = CountUniqueDataflowCallsStageTest.PACKAGE + "."
            + CountUniqueDataflowCallsStageTest.CP_LEFT;
    private static final String OP_LEFT = "op1";
    private static final String OP_LEFT_SIGNATURE = "V op1 (T, B)";
    private static final String OP_RETURN_TYPE = "V";
    private static final String OP_RIGHT = "op2";
    private static final String OP_RIGHT_SIGNATURE = "V op2 (T, B)";
    private static final String CP_RIGHT = "Right";
    private static final String CP_RIGHT_FQN = CountUniqueDataflowCallsStageTest.PACKAGE + "."
            + CountUniqueDataflowCallsStageTest.CP_RIGHT;
    private static final String CONTEXT = "context";

    @Test
    void test() {
        final TypeModel typeModel = this.createTypeModel();
        final AssemblyModel assemblyModel = this.createAssemblyModel(typeModel);
        final DeploymentModel deploymentModel = this.createDeploymentModel(assemblyModel);
        final ExecutionModel executionModel = this.createExecutionModel(deploymentModel);
        final StatisticsModel statisticsModel = this.createStatisticsModel(executionModel);

        final OperationEvent op1 = new OperationEvent(CountUniqueDataflowCallsStageTest.CONTEXT,
                CountUniqueDataflowCallsStageTest.CP_LEFT_FQN, CountUniqueDataflowCallsStageTest.CP_LEFT_FQN);
        final OperationEvent op2 = new OperationEvent(CountUniqueDataflowCallsStageTest.CONTEXT,
                CountUniqueDataflowCallsStageTest.CP_RIGHT_FQN, CountUniqueDataflowCallsStageTest.CP_RIGHT_FQN);
        final DataflowEvent sourceDataflow = new DataflowEvent(op1, op2, EDirection.BOTH, Duration.ofMillis(5));

        final CountUniqueDataflowCallsStage stage = new CountUniqueDataflowCallsStage(statisticsModel, executionModel);

        StageTester.test(stage).and().send(sourceDataflow).to(stage.getInputPort()).start();
        MatcherAssert.assertThat(stage.getOutputPort(), StageTester.produces(sourceDataflow));
    }

    private StatisticsModel createStatisticsModel(final ExecutionModel executionModel) {
        final StatisticsModel model = StatisticsFactory.eINSTANCE.createStatisticsModel();
        executionModel.getOperationDataflows().values().forEach(dataflow -> {
            final StatisticRecord statisticRecord = StatisticsFactory.eINSTANCE.createStatisticRecord();
            statisticRecord.getProperties().put(CountUniqueDataflowCallsStage.DATAFLOW, 1);
            model.getStatistics().put(dataflow, statisticRecord);
        });
        return model;
    }

    private TypeModel createTypeModel() {
        final TypeModel model = TypeFactory.eINSTANCE.createTypeModel();

        final ComponentType componentType = this.createComponentType(CountUniqueDataflowCallsStageTest.CP_LEFT,
                CountUniqueDataflowCallsStageTest.CP_LEFT_FQN);

        final OperationType operationType = this.createOperationType(CountUniqueDataflowCallsStageTest.OP_LEFT,
                CountUniqueDataflowCallsStageTest.OP_LEFT_SIGNATURE, CountUniqueDataflowCallsStageTest.OP_RETURN_TYPE);
        componentType.getProvidedOperations().put(operationType.getSignature(), operationType);

        model.getComponentTypes().put(componentType.getSignature(), componentType);

        final ComponentType componentType2 = this.createComponentType(CountUniqueDataflowCallsStageTest.CP_RIGHT,
                CountUniqueDataflowCallsStageTest.CP_RIGHT_FQN);

        final OperationType operationType2 = this.createOperationType(CountUniqueDataflowCallsStageTest.OP_RIGHT,
                CountUniqueDataflowCallsStageTest.OP_RIGHT_SIGNATURE, CountUniqueDataflowCallsStageTest.OP_RETURN_TYPE);
        componentType.getProvidedOperations().put(operationType2.getSignature(), operationType2);

        model.getComponentTypes().put(componentType2.getSignature(), componentType2);

        return model;
    }

    private ComponentType createComponentType(final String name, final String fqnName) {
        final ComponentType componentType = TypeFactory.eINSTANCE.createComponentType();
        componentType.setName(name);
        componentType.setPackage(CountUniqueDataflowCallsStageTest.PACKAGE);
        componentType.setSignature(fqnName);
        return componentType;
    }

    private OperationType createOperationType(final String name, final String signature, final String returnType) {
        final OperationType operationType = TypeFactory.eINSTANCE.createOperationType();
        operationType.setName(name);
        operationType.setSignature(signature);
        operationType.setReturnType(returnType);

        return operationType;
    }

    private AssemblyModel createAssemblyModel(final TypeModel typeModel) {
        final AssemblyModel model = AssemblyFactory.eINSTANCE.createAssemblyModel();

        final AssemblyComponent component = this.createAssemblyComponent(CountUniqueDataflowCallsStageTest.CP_LEFT_FQN,
                typeModel);
        component.getOperations().put(CountUniqueDataflowCallsStageTest.OP_LEFT_SIGNATURE, this.createAssemblyOperation(
                CountUniqueDataflowCallsStageTest.OP_LEFT_SIGNATURE, component.getComponentType()));

        final AssemblyComponent component2 = this
                .createAssemblyComponent(CountUniqueDataflowCallsStageTest.CP_RIGHT_FQN, typeModel);
        component2.getOperations().put(CountUniqueDataflowCallsStageTest.OP_RIGHT_SIGNATURE,
                this.createAssemblyOperation(CountUniqueDataflowCallsStageTest.OP_RIGHT_SIGNATURE,
                        component2.getComponentType()));

        return model;
    }

    private AssemblyComponent createAssemblyComponent(final String fqnName, final TypeModel typeModel) {
        final AssemblyComponent component = AssemblyFactory.eINSTANCE.createAssemblyComponent();
        final ComponentType componentType = typeModel.getComponentTypes()
                .get(CountUniqueDataflowCallsStageTest.CP_LEFT_FQN);
        component.setComponentType(componentType);
        component.setSignature(fqnName);

        return component;
    }

    private AssemblyOperation createAssemblyOperation(final String signature, final ComponentType componentType) {
        final AssemblyOperation operation = AssemblyFactory.eINSTANCE.createAssemblyOperation();
        operation.setOperationType(componentType.getProvidedOperations().get(signature));

        return operation;
    }

    private DeploymentModel createDeploymentModel(final AssemblyModel assemblyModel) {
        final DeploymentModel model = DeploymentFactory.eINSTANCE.createDeploymentModel();

        final DeploymentContext context = DeploymentFactory.eINSTANCE.createDeploymentContext();
        context.setName(CountUniqueDataflowCallsStageTest.CONTEXT);

        model.getContexts().put(CountUniqueDataflowCallsStageTest.CONTEXT, context);

        final DeployedComponent component = this.createComponent(CountUniqueDataflowCallsStageTest.CP_LEFT_FQN,
                assemblyModel);
        component.getOperations().put(CountUniqueDataflowCallsStageTest.OP_LEFT_SIGNATURE,
                this.createDeployedOperation(CountUniqueDataflowCallsStageTest.OP_LEFT_SIGNATURE, component));

        context.getComponents().put(CountUniqueDataflowCallsStageTest.CP_LEFT_FQN, component);

        final DeployedComponent component2 = this.createComponent(CountUniqueDataflowCallsStageTest.CP_RIGHT_FQN,
                assemblyModel);
        component2.getOperations().put(CountUniqueDataflowCallsStageTest.OP_RIGHT_SIGNATURE,
                this.createDeployedOperation(CountUniqueDataflowCallsStageTest.OP_RIGHT_SIGNATURE, component2));
        context.getComponents().put(CountUniqueDataflowCallsStageTest.CP_RIGHT_FQN, component2);

        return model;
    }

    private DeployedComponent createComponent(final String fqnName, final AssemblyModel assemblyModel) {
        final DeployedComponent component = DeploymentFactory.eINSTANCE.createDeployedComponent();
        component.setSignature(fqnName);
        component.setAssemblyComponent(assemblyModel.getComponents().get(fqnName));

        return component;
    }

    private DeployedOperation createDeployedOperation(final String signature, final DeployedComponent component) {
        final DeployedOperation operation = DeploymentFactory.eINSTANCE.createDeployedOperation();
        operation.setAssemblyOperation(component.getAssemblyComponent().getOperations().get(signature));

        return operation;
    }

    private ExecutionModel createExecutionModel(final DeploymentModel deploymentModel) {
        final ExecutionModel model = ExecutionFactory.eINSTANCE.createExecutionModel();

        final DeployedOperation caller = deploymentModel.getContexts().get(CountUniqueDataflowCallsStageTest.CONTEXT)
                .getComponents().get(CountUniqueDataflowCallsStageTest.CP_LEFT_FQN).getOperations()
                .get(CountUniqueDataflowCallsStageTest.OP_LEFT_SIGNATURE);
        final DeployedOperation callee = deploymentModel.getContexts().get(CountUniqueDataflowCallsStageTest.CONTEXT)
                .getComponents().get(CountUniqueDataflowCallsStageTest.CP_RIGHT_FQN).getOperations()
                .get(CountUniqueDataflowCallsStageTest.OP_RIGHT_SIGNATURE);

        final Tuple<DeployedOperation, DeployedOperation> tuple = ExecutionFactory.eINSTANCE.createTuple();
        final OperationDataflow dataflow = ExecutionFactory.eINSTANCE.createOperationDataflow();
        dataflow.setCallee(callee);
        dataflow.setCaller(caller);

        model.getOperationDataflows().put(tuple, dataflow);

        return model;
    }

}
