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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import kieker.analysis.architecture.recovery.events.StorageEvent;
import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.deployment.DeployedComponent;
import kieker.model.analysismodel.deployment.DeployedStorage;
import kieker.model.analysismodel.deployment.DeploymentContext;
import kieker.model.analysismodel.deployment.DeploymentFactory;
import kieker.model.analysismodel.deployment.DeploymentModel;
import kieker.model.analysismodel.source.SourceFactory;
import kieker.model.analysismodel.source.SourceModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;

/**
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
class StorageDeploymentModelAssemblerTest {

    private static final String SOURCE_LABEL = "test";
    private static final String HOSTNAME = "host";
    private static final String COMPONENT_SIGNATUE = "test.component";
    private static final String STORAGE_SIGNATURE = "data";
    private static final String STORAGE_TYPE = "integer";

    @Test
    void test() {
        final TypeModel typeModel = TypeFactory.eINSTANCE.createTypeModel();
        final AssemblyModel assemblyModel = AssemblyFactory.eINSTANCE.createAssemblyModel();
        final DeploymentModel deploymentModel = DeploymentFactory.eINSTANCE.createDeploymentModel();
        final SourceModel sourceModel = SourceFactory.eINSTANCE.createSourceModel();

        final StorageTypeModelAssembler typeAssembler = new StorageTypeModelAssembler(typeModel, sourceModel,
                SOURCE_LABEL, new SimpleComponentSignatureExtractor(), new SimpleStorageSignatureExtractor());
        final StorageAssemblyModelAssembler assemblyAssembler = new StorageAssemblyModelAssembler(typeModel,
                assemblyModel, sourceModel, SOURCE_LABEL);
        final StorageDeploymentModelAssembler deploymentAssembler = new StorageDeploymentModelAssembler(assemblyModel,
                deploymentModel, sourceModel, SOURCE_LABEL);

        final StorageEvent storageEvent = new StorageEvent(HOSTNAME, COMPONENT_SIGNATUE, STORAGE_SIGNATURE,
                STORAGE_TYPE);

        typeAssembler.addStorage(storageEvent);
        assemblyAssembler.addStorage(storageEvent);
        deploymentAssembler.addStorage(storageEvent);

        final ComponentType componentType = typeModel.getComponentTypes().get(COMPONENT_SIGNATUE);
        final AssemblyComponent assemblyComponent = assemblyModel.getComponents().get(COMPONENT_SIGNATUE);
        final DeploymentContext context = deploymentModel.getContexts().get(HOSTNAME);
        final DeployedComponent deployedComponent = context.getComponents().get(COMPONENT_SIGNATUE);

        Assertions.assertNotNull(deployedComponent);
        Assertions.assertEquals(COMPONENT_SIGNATUE, assemblyComponent.getSignature());
        Assertions.assertEquals(componentType, assemblyComponent.getComponentType());

        final DeployedStorage storage = deployedComponent.getStorages().get(STORAGE_SIGNATURE);
        Assertions.assertNotNull(storage);
        Assertions.assertNotNull(storage.getAssemblyStorage());
        Assertions.assertNotNull(storage.getAssemblyStorage().getStorageType());
        Assertions.assertEquals(storage.getAssemblyStorage(), assemblyComponent.getStorages().get(STORAGE_SIGNATURE));
        Assertions.assertEquals(STORAGE_SIGNATURE, storage.getAssemblyStorage().getStorageType().getName());
    }

}
