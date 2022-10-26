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

import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import kieker.analysis.architecture.repository.ArchitectureModelUtils;
import kieker.analysis.architecture.repository.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.AssemblyPackage;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedOperation;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.deployment.DeploymentPackage;
import kieker.model.analysismodel.execution.ExecutionModel;
import kieker.model.analysismodel.execution.ExecutionPackage;
import kieker.model.analysismodel.source.SourceModel;
import kieker.model.analysismodel.source.SourcePackage;
import kieker.model.analysismodel.statistics.StatisticsModel;
import kieker.model.analysismodel.statistics.StatisticsPackage;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.TypeModel;
import kieker.model.analysismodel.type.TypePackage;

import org.oceandsl.tools.mop.merge.ModelRepositoryMergerUtils;

/**
 * @author Reiner Jung
 *
 */
public class ModelRepositoryMergerTest {

    private static final String NUMBER_OF_OPERATION = "Number of operation";

    /**
     * Test method for
     * {@link org.oceandsl.tools.mop.merge.ModelRepositoryMergerUtils#perform(kieker.analysis.stage.model.ModelRepository, kieker.analysis.stage.model.ModelRepository, org.oceandsl.tools.mop.EStrategy)}.
     */
    @Test
    public void testPerformType() {
        final ModelRepository darRepo = this.createDarModel();
        final ModelRepository sarRepo = this.createSarModel();
        ModelRepositoryMergerUtils.perform(darRepo, sarRepo);

        final TypeModel typeModel = darRepo.getModel(TypePackage.Literals.TYPE_MODEL);

        Assert.assertEquals("Numer of component types", 3, typeModel.getComponentTypes().size());
        for (final Entry<String, ComponentType> type : typeModel.getComponentTypes().entrySet()) {
            if (SarModelFactory.SAR_COMPONENT_SIGNATURE.equals(type.getKey())) {
                Assert.assertEquals(ModelRepositoryMergerTest.NUMBER_OF_OPERATION, 1,
                        type.getValue().getProvidedOperations().size());
            } else if (AbstractModelTestFactory.JOINT_COMPONENT_SIGNATURE.equals(type.getKey())) {
                Assert.assertEquals(ModelRepositoryMergerTest.NUMBER_OF_OPERATION, 2,
                        type.getValue().getProvidedOperations().size());
            } else if (DarModelFactory.DAR_COMPONENT_SIGNATURE.equals(type.getKey())) {
                Assert.assertEquals(ModelRepositoryMergerTest.NUMBER_OF_OPERATION, 1,
                        type.getValue().getProvidedOperations().size());
            } else {
                Assert.fail("Unkown component type " + type.getKey());
            }
        }
    }

    @Test
    public void testPerformAssembly() {
        final ModelRepository darRepo = this.createDarModel();
        final ModelRepository sarRepo = this.createSarModel();
        ModelRepositoryMergerUtils.perform(darRepo, sarRepo);

        final TypeModel typeModel = darRepo.getModel(TypePackage.Literals.TYPE_MODEL);
        final AssemblyModel assemblyModel = darRepo.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL);

        Assert.assertEquals("Numer of component types", 3, assemblyModel.getComponents().size());
        for (final Entry<String, AssemblyComponent> entry : assemblyModel.getComponents().entrySet()) {
            final AssemblyComponent component = entry.getValue();

            final ComponentType componentType = typeModel.getComponentTypes()
                    .get(component.getComponentType().getSignature());

            if (SarModelFactory.SAR_ASSEMBLY_SIGNATURE.equals(entry.getKey())) {
                Assert.assertEquals("Number of operation", 1, component.getOperations().size());
                final AssemblyOperation operation = component.getOperations()
                        .get(AbstractModelTestFactory.OP_SIGNATURE);
                Assert.assertTrue("Operation not found " + AbstractModelTestFactory.OP_SIGNATURE, operation != null);
                Assert.assertTrue("Wrong operation type", operation.getOperationType() == componentType
                        .getProvidedOperations().get(AbstractModelTestFactory.OP_SIGNATURE));
            } else if (AbstractModelTestFactory.JOINT_ASSEMBLY_SIGNATURE.equals(entry.getKey())) {
                Assert.assertEquals(ModelRepositoryMergerTest.NUMBER_OF_OPERATION, 2, component.getOperations().size());
            } else if (DarModelFactory.DAR_ASSEMBLY_SIGNATURE.equals(entry.getKey())) {
                Assert.assertEquals(ModelRepositoryMergerTest.NUMBER_OF_OPERATION, 1, component.getOperations().size());
            } else {
                Assert.fail("Unkown assembly component " + entry.getKey());
            }

            Assert.assertEquals("Wrong component type", component.getComponentType(), componentType);
        }
    }

    @Test
    public void testPerformDeployment() {
        final ModelRepository darRepo = this.createDarModel();
        final ModelRepository sarRepo = this.createSarModel();
        ModelRepositoryMergerUtils.perform(darRepo, sarRepo);

        final AssemblyModel assemblyModel = darRepo.getModel(AssemblyPackage.Literals.ASSEMBLY_MODEL);
        final DeploymentModel deploymentModel = darRepo.getModel(DeploymentPackage.Literals.DEPLOYMENT_MODEL);

        Assert.assertEquals("Numer of deployment contexts", 1, deploymentModel.getContexts().size());
        final DeploymentContext context = deploymentModel.getContexts().get(AbstractModelTestFactory.HOSTNAME);
        Assert.assertTrue("No context", context != null);
        for (final Entry<String, DeployedComponent> entry : context.getComponents()) {
            final DeployedComponent deployedComponent = entry.getValue();
            Assert.assertTrue("Deployed component has wrong signature",
                    deployedComponent.getSignature().equals(entry.getKey()));
            final AssemblyComponent assemblyComponent = assemblyModel.getComponents().get(entry.getKey());
            if (SarModelFactory.SAR_ASSEMBLY_SIGNATURE.equals(entry.getKey())) {
                this.checkDeployedOperation(deployedComponent, assemblyComponent,
                        AbstractModelTestFactory.OP_SIGNATURE);
            } else if (DarModelFactory.DAR_ASSEMBLY_SIGNATURE.equals(entry.getKey())) {
                this.checkDeployedOperation(deployedComponent, assemblyComponent,
                        AbstractModelTestFactory.OP_SIGNATURE);
            } else if (AbstractModelTestFactory.JOINT_ASSEMBLY_SIGNATURE.equals(entry.getKey())) {
                this.checkDeployedOperation(deployedComponent, assemblyComponent,
                        AbstractModelTestFactory.OP_COMPILE_SIGNATURE);
                this.checkDeployedOperation(deployedComponent, assemblyComponent,
                        AbstractModelTestFactory.OP_LINK_SIGNATURE);
            }
        }
    }

    @Test
    public void testPerformExecution() {
        final ModelRepository darRepo = this.createDarModel();
        final ModelRepository sarRepo = this.createSarModel();
        ModelRepositoryMergerUtils.perform(darRepo, sarRepo);

        final ExecutionModel executionModel = darRepo.getModel(ExecutionPackage.Literals.EXECUTION_MODEL);

        Assert.assertEquals("Wrong number of invocations", 6, executionModel.getInvocations().size());
    }

    @Test
    public void testPerformStatistics() {
        final ModelRepository darRepo = this.createDarModel();
        final ModelRepository sarRepo = this.createSarModel();
        ModelRepositoryMergerUtils.perform(darRepo, sarRepo);

        final ExecutionModel executionModel = darRepo.getModel(ExecutionPackage.Literals.EXECUTION_MODEL);
        final StatisticsModel statisticsModel = darRepo.getModel(StatisticsPackage.Literals.STATISTICS_MODEL);

        Assert.assertEquals("Wrong number of statistics", executionModel.getInvocations().size(),
                statisticsModel.getStatistics().size());
    }

    @Test
    public void testPerformSource() {
        final ModelRepository darRepo = this.createDarModel();
        final ModelRepository sarRepo = this.createSarModel();
        ModelRepositoryMergerUtils.perform(darRepo, sarRepo);

        final SourceModel sourceModel = darRepo.getModel(SourcePackage.Literals.SOURCE_MODEL);

        /*
         * 7 types (comp 3 +op 4), 7 assembly, 7 deployment + 1 deployment context, 3 sar,2 dar, 1
         * joint calls.
         */
        Assert.assertEquals("Wrong number of elements in total", 7 + 7 + 7 + 1 + 6,
                sourceModel.getSources().entrySet().size());

    }

    private void checkDeployedOperation(final DeployedComponent deployedComponent,
            final AssemblyComponent assemblyComponent, final String operationSignature) {
        final DeployedOperation deployedOperation = deployedComponent.getOperations().get(operationSignature);
        Assert.assertTrue("Wrong assembly operation for " + operationSignature,
                deployedOperation.getAssemblyOperation() == assemblyComponent.getOperations().get(operationSignature));
    }

    private ModelRepository createSarModel() {
        final ModelRepository repository = new ModelRepository("sar");

        final TypeModel typeModel = SarModelFactory.createTypeModel();
        final AssemblyModel assemblyModel = SarModelFactory.createAssemblyModel(typeModel);
        final DeploymentModel deploymentModel = SarModelFactory.createDeploymentModel(assemblyModel);
        final ExecutionModel executionModel = SarModelFactory.createExecutionModel(deploymentModel);
        final StatisticsModel statisticsModel = SarModelFactory.createStatisticsModel(executionModel);

        repository.register(ArchitectureModelUtils.TYPE_MODEL, typeModel);
        repository.register(ArchitectureModelUtils.ASSEMBLY_MODEL, assemblyModel);
        repository.register(ArchitectureModelUtils.DEPLOYMENT_MODEL, deploymentModel);
        repository.register(ArchitectureModelUtils.EXECUTION_MODEL, executionModel);
        repository.register(ArchitectureModelUtils.STATISTICS_MODEL, statisticsModel);
        repository.register(ArchitectureModelUtils.SOURCE_MODEL, AbstractModelTestFactory.createSourceModel(typeModel,
                assemblyModel, deploymentModel, executionModel, "static"));
        return repository;
    }

    private ModelRepository createDarModel() {
        final ModelRepository repository = new ModelRepository("dar");
        final TypeModel typeModel = DarModelFactory.createTypeModel();
        final AssemblyModel assemblyModel = DarModelFactory.createAssemblyModel(typeModel);
        final DeploymentModel deploymentModel = DarModelFactory.createDeploymentModel(assemblyModel);
        final ExecutionModel executionModel = DarModelFactory.createExecutionModel(deploymentModel);
        final StatisticsModel statisticsModel = DarModelFactory.createStatisticsModel(executionModel);

        repository.register(ArchitectureModelUtils.TYPE_MODEL, typeModel);
        repository.register(ArchitectureModelUtils.ASSEMBLY_MODEL, assemblyModel);
        repository.register(ArchitectureModelUtils.DEPLOYMENT_MODEL, deploymentModel);
        repository.register(ArchitectureModelUtils.EXECUTION_MODEL, executionModel);
        repository.register(ArchitectureModelUtils.STATISTICS_MODEL, statisticsModel);
        repository.register(ArchitectureModelUtils.SOURCE_MODEL, AbstractModelTestFactory.createSourceModel(typeModel,
                assemblyModel, deploymentModel, executionModel, "dynamic"));
        return repository;
    }

}
