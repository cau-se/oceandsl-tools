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
import kieker.model.analysismodel.source.SourceFactory;
import kieker.model.analysismodel.source.SourceModel;
import kieker.model.analysismodel.type.ComponentType;
import kieker.model.analysismodel.type.StorageType;
import kieker.model.analysismodel.type.TypeFactory;
import kieker.model.analysismodel.type.TypeModel;

class StorageTypeModelAssemblerTest {

    private static final String SOURCE_LABEL = "test";
    private static final String HOSTNAME = "host";
    private static final String COMPONENT_SIGNATUE = "test.component";
    private static final String STORAGE_SIGNATURE = "data";
    private static final String STORAGE_TYPE = "integer";

    @Test
    public void test() {
        final TypeModel typeModel = TypeFactory.eINSTANCE.createTypeModel();
        final SourceModel sourceModel = SourceFactory.eINSTANCE.createSourceModel();

        final StorageTypeModelAssembler assembler = new StorageTypeModelAssembler(typeModel, sourceModel, SOURCE_LABEL,
                new SimpleComponentSignatureExtractor(), new SimpleStorageSignatureExtractor());

        final StorageEvent storageEvent = new StorageEvent(HOSTNAME, COMPONENT_SIGNATUE, STORAGE_SIGNATURE,
                STORAGE_TYPE);

        assembler.addStorage(storageEvent);

        Assertions.assertEquals(1, typeModel.getComponentTypes().size());
        final ComponentType type = typeModel.getComponentTypes().get(COMPONENT_SIGNATUE);
        Assertions.assertNotNull(type);
        Assertions.assertEquals(COMPONENT_SIGNATUE, type.getSignature());
        Assertions.assertEquals(1, type.getProvidedStorages().size());
        final StorageType storageType = type.getProvidedStorages().get(STORAGE_SIGNATURE);
        Assertions.assertNotNull(storageType);
        Assertions.assertEquals(STORAGE_SIGNATURE, storageType.getName());
    }

}
