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

import java.util.Collection;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import kieker.analysis.stage.model.ModelRepository;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyProvidedInterface;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedProvidedInterface;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.ProvidedInterfaceType;

import teetime.framework.test.StageTester;

/**
 *
 * @author Reiner Jung
 * @since 1.2
 */
public class ComputeInterfacesStageTest {

    @Ignore
    @Test
    public void noProvidedInterface() {
        final ModelRepository modelRepository = TestModelRepositoryUtils.createThreeComponentModel();
        TestModelInvocationUtils.addInvocations(modelRepository);

        final CollectConnectionsStage stage = new CollectConnectionsStage();
        StageTester.test(stage).send(modelRepository).to(stage.getInputPort()).start();
        final DeploymentModel deploymentModel = modelRepository.getModel(DeploymentModel.class);
        for (final DeploymentContext context : deploymentModel.getDeploymentContexts().values()) {
            final DeployedComponent componentA = context.getComponents()
                    .get(TestModelRepositoryUtils.FQN_COMPONENT_A + ":1");
            final DeployedComponent componentB = context.getComponents()
                    .get(TestModelRepositoryUtils.FQN_COMPONENT_B + ":1");
            final DeployedComponent componentC = context.getComponents()
                    .get(TestModelRepositoryUtils.FQN_COMPONENT_C + ":1");

            this.assertInterfaces(TestModelRepositoryUtils.COMPONENT_A, componentA, new String[] {}, 1);
            this.assertInterfaces(TestModelRepositoryUtils.COMPONENT_B, componentB,
                    new String[] { TestModelRepositoryUtils.OP_B_NAME_SIGNATURE }, 1);
            this.assertInterfaces(TestModelRepositoryUtils.COMPONENT_C, componentC,
                    new String[] { TestModelRepositoryUtils.OP_C_NAME_SIGNATURE }, 0);
        }
    }

    private void assertInterfaces(final String label, final DeployedComponent deployedComponent,
            final String[] providedSignatures, final int requiredCount) {
        Assert.assertEquals(label + " provided deployed interface", providedSignatures.length,
                deployedComponent.getProvidedInterfaces().size());
        Assert.assertEquals(label + " required deployed interface", requiredCount,
                deployedComponent.getRequiredInterfaces().size());

        final Collection<DeployedProvidedInterface> providedInterfaces = deployedComponent.getProvidedInterfaces()
                .values();
        if (providedInterfaces.size() == 1) {
            final DeployedProvidedInterface[] deployedProvidedInterface = providedInterfaces
                    .toArray(new DeployedProvidedInterface[1]);
            for (final String signature : providedSignatures) {
                if (!deployedProvidedInterface[0].getProvidedInterface().getProvidedInterfaceType()
                        .getProvidedOperationTypes().containsKey(signature)) {
                    Assert.fail(String.format("%s: deployed operation %s missing from interface %s", label, signature,
                            deployedProvidedInterface[0].getProvidedInterface().getProvidedInterfaceType()
                                    .getSignature()));
                }
            }
        }

        this.assertInterfaces(label, deployedComponent.getAssemblyComponent(), providedSignatures, requiredCount);
    }

    private void assertInterfaces(final String label, final AssemblyComponent assemblyComponent,
            final String[] providedSignatures, final int requiredCount) {
        Assert.assertEquals(label + " provided assembly interface", providedSignatures.length,
                assemblyComponent.getProvidedInterfaces().size());
        Assert.assertEquals(label + " required assembly interface", requiredCount,
                assemblyComponent.getRequiredInterfaces().size());

        final Collection<AssemblyProvidedInterface> assemblyProvidedInterfaces = assemblyComponent
                .getProvidedInterfaces().values();
        if (assemblyProvidedInterfaces.size() == 1) {
            final AssemblyProvidedInterface[] assemblyProvidedInterface = assemblyProvidedInterfaces
                    .toArray(new AssemblyProvidedInterface[1]);
            for (final String signature : providedSignatures) {
                if (!assemblyProvidedInterface[0].getProvidedInterfaceType().getProvidedOperationTypes()
                        .containsKey(signature)) {
                    Assert.fail(String.format("%s: assembly operation %s missing from interface %s", label, signature,
                            assemblyProvidedInterface[0].getProvidedInterfaceType().getSignature()));
                }
            }
        }

        this.assertInterfaces(label, assemblyComponent.getComponentType(), providedSignatures, requiredCount);
    }

    private void assertInterfaces(final String label, final ComponentType componentType,
            final String[] providedSignatures, final int requiredCount) {
        Assert.assertEquals(label + " provided assembly interface", providedSignatures.length,
                componentType.getProvidedInterfaceTypes().size());
        Assert.assertEquals(label + " required assembly interface", requiredCount,
                componentType.getRequiredInterfaceTypes().size());

        if (componentType.getProvidedInterfaceTypes().size() == 1) {
            final ProvidedInterfaceType providedInterfaceType = componentType.getProvidedInterfaceTypes().get(0);

            for (final String signature : providedSignatures) {
                if (!providedInterfaceType.getProvidedOperationTypes().containsKey(signature)) {
                    Assert.fail(String.format("%s: operation type %s missing from interface %s", label, signature,
                            providedInterfaceType.getSignature()));
                }
            }
        }
    }

    @Ignore
    @Test
    public void noRequiredInterface() {
        final ModelRepository modelRepository = TestModelRepositoryUtils.createThreeComponentModel();
        TestModelInvocationUtils.addProvidedInterfaces(modelRepository);
        TestModelInvocationUtils.addInvocations(modelRepository);

        final CollectConnectionsStage stage = new CollectConnectionsStage();
        StageTester.test(stage).send(modelRepository).to(stage.getInputPort()).start();
        final DeploymentModel deploymentModel = modelRepository.getModel(DeploymentModel.class);
        for (final DeploymentContext context : deploymentModel.getDeploymentContexts().values()) {
            final DeployedComponent componentA = context.getComponents()
                    .get(TestModelRepositoryUtils.FQN_COMPONENT_A + ":1");
            final DeployedComponent componentB = context.getComponents()
                    .get(TestModelRepositoryUtils.FQN_COMPONENT_B + ":1");
            final DeployedComponent componentC = context.getComponents()
                    .get(TestModelRepositoryUtils.FQN_COMPONENT_C + ":1");

            this.assertInterfaces(TestModelRepositoryUtils.COMPONENT_A, componentA, new String[] {}, 1);
            this.assertInterfaces(TestModelRepositoryUtils.COMPONENT_B, componentB,
                    new String[] { TestModelRepositoryUtils.OP_B_NAME_SIGNATURE }, 1);
            this.assertInterfaces(TestModelRepositoryUtils.COMPONENT_C, componentC,
                    new String[] { TestModelRepositoryUtils.OP_C_NAME_SIGNATURE }, 0);
        }
    }
}
