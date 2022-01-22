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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.execution.AggregatedInvocation;
import kieker.model.analysismodel.execution.ExecutionFactory;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.Tuple;
import kieker.model.analysismodel.sources.SourceModel;
import kieker.model.analysismodel.sources.SourcesFactory;
import kieker.model.analysismodel.statistics.EPredefinedUnits;
import kieker.model.analysismodel.statistics.EPropertyType;
import kieker.model.analysismodel.statistics.StatisticRecord;
import kieker.model.analysismodel.statistics.Statistics;
import kieker.model.analysismodel.statistics.StatisticsFactory;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.OperationType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;

/**
 * @author Reiner Jung
 *
 */
public class ModelTestFactory {

    static final String HOSTNAME = "test";

    static final String PACKAGE = "application";
    static final String JOINT_COMPONENT = "joint-component";
    static final String JOINT_COMPONENT_SIGNATURE = ModelTestFactory.PACKAGE + "." + ModelTestFactory.JOINT_COMPONENT;

    static final String OP_SIGNATURE = "void operation()";
    static final String OP_COMPILE_SIGNATURE = "Model compile(Model in)";
    static final String OP_LINK_SIGNATURE = "Model link(Model in)";

    static final String JOINT_ASSEMBLY_SIGNATURE = ModelTestFactory.PACKAGE + "." + ModelTestFactory.JOINT_COMPONENT;

    protected static OperationType createOperationType(final String name, final String returnType,
            final String signature) {
        final OperationType operationType = TypeFactory.eINSTANCE.createOperationType();
        operationType.setName(name);
        operationType.setSignature(signature);
        operationType.setReturnType(returnType);

        return operationType;
    }

    protected static ComponentType findType(final TypeModel typeModel, final String componentTypeSignature) {
        return typeModel.getComponentTypes().get(componentTypeSignature);
    }

    protected static OperationType findOperationType(final ComponentType type, final String operationSignature) {
        return type.getProvidedOperations().get(operationSignature);
    }

    protected static AssemblyComponent createAssemblyComponent(final String assemblySignature,
            final ComponentType type) {
        assert type != null;
        final AssemblyComponent component = AssemblyFactory.eINSTANCE.createAssemblyComponent();

        component.setSignature(assemblySignature);
        component.setComponentType(type);

        return component;
    }

    protected static AssemblyOperation createAssemblyOperation(final OperationType operationType) {
        assert operationType != null;
        final AssemblyOperation operation = AssemblyFactory.eINSTANCE.createAssemblyOperation();

        operation.setOperationType(operationType);

        return operation;
    }

    protected static DeploymentContext createDeploymentContext(final String hostname) {
        final DeploymentContext context = DeploymentFactory.eINSTANCE.createDeploymentContext();
        context.setName(hostname);

        return context;
    }

    public static AssemblyComponent findComponent(final AssemblyModel assemblyModel, final String assemblySignature) {
        return assemblyModel.getAssemblyComponents().get(assemblySignature);
    }

    public static DeployedComponent createDeploymentComponent(final String assemblySignature,
            final AssemblyComponent assemblyComponent, final DeployedOperation... operations) {
        final DeployedComponent deployedComponent = DeploymentFactory.eINSTANCE.createDeployedComponent();
        deployedComponent.setAssemblyComponent(assemblyComponent);
        deployedComponent.setSignature(assemblySignature);

        for (final DeployedOperation operation : operations) {
            deployedComponent.getOperations().put(operation.getAssemblyOperation().getOperationType().getSignature(),
                    operation);
        }

        return deployedComponent;
    }

    public static DeployedOperation createDeployedOperation(final String operationSignature,
            final AssemblyComponent assemblyComponent) {
        final AssemblyOperation assemblyOperation = ModelTestFactory.findOperation(assemblyComponent,
                operationSignature);

        final DeployedOperation operation = DeploymentFactory.eINSTANCE.createDeployedOperation();
        operation.setAssemblyOperation(assemblyOperation);

        return operation;
    }

    private static AssemblyOperation findOperation(final AssemblyComponent assemblyComponent,
            final String operationSignature) {
        return assemblyComponent.getOperations().get(operationSignature);
    }

    public static DeployedComponent findDeployedComponent(final DeploymentModel deploymentModel,
            final String contextName, final String componentSignature) {
        final DeploymentContext context = deploymentModel.getDeploymentContexts().get(contextName);
        assert context != null;
        final DeployedComponent component = context.getComponents().get(componentSignature);
        assert component != null;
        return component;
    }

    public static DeployedOperation findOperation(final DeploymentModel deploymentModel, final String context,
            final String componentName, final String operationName) {
        final DeployedComponent darComponent = ModelTestFactory.findDeployedComponent(deploymentModel, context,
                componentName);
        return darComponent.getOperations().get(operationName);
    }

    public static void createAggregatedInvocation(
            final EMap<Tuple<DeployedOperation, DeployedOperation>, AggregatedInvocation> aggregatedInvocations,
            final DeployedOperation caller, final DeployedOperation callee) {
        final Tuple<DeployedOperation, DeployedOperation> key = ExecutionFactory.eINSTANCE.createTuple();
        key.setFirst(caller);
        key.setSecond(callee);

        final AggregatedInvocation invocation = ExecutionFactory.eINSTANCE.createAggregatedInvocation();
        invocation.setSource(caller);
        invocation.setTarget(callee);

        aggregatedInvocations.put(key, invocation);
    }

    public static Statistics createStatistics() {
        final Statistics statistics = StatisticsFactory.eINSTANCE.createStatistics();

        final StatisticRecord record = StatisticsFactory.eINSTANCE.createStatisticRecord();
        record.getProperties().put(EPropertyType.COUNT, 1L);

        statistics.getStatistics().put(EPredefinedUnits.INVOCATION, record);

        return statistics;
    }

    protected static SourceModel createSourceModel(final TypeModel typeModel, final AssemblyModel assemblyModel,
            final DeploymentModel deploymentModel, final ExecutionModel executionModel, final String source) {
        final SourcesFactory factory = SourcesFactory.eINSTANCE;

        final SourceModel result = factory.createSourceModel();

        /** type */
        typeModel.eAllContents().forEachRemaining(item -> {
            if (item instanceof ComponentType || item instanceof OperationType) {
                result.getSources().put(item, ModelTestFactory.createList(source));
            }
        });

        /** assembly */
        assemblyModel.eAllContents().forEachRemaining(item -> {
            if (item instanceof AssemblyComponent || item instanceof AssemblyOperation) {
                result.getSources().put(item, ModelTestFactory.createList(source));
            }
        });

        /** deployment */
        deploymentModel.eAllContents().forEachRemaining(item -> {
            if (item instanceof DeploymentContext || item instanceof DeployedComponent
                    || item instanceof DeployedOperation) {
                result.getSources().put(item, ModelTestFactory.createList(source));
            }
        });

        /** execution */
        executionModel.eAllContents().forEachRemaining(item -> {
            if (item instanceof AggregatedInvocation) {
                result.getSources().put(item, ModelTestFactory.createList(source));
            }
        });

        return result;
    }

    protected static EList<String> createList(final String value) {
        final EList<String> list = new BasicEList<>();
        list.add(value);

        return list;
    }

}
