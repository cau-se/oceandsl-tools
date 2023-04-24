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
package org.oceandsl.tools.sar.stages.dataflow;

import kieker.analysis.architecture.recovery.events.StorageEvent;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.tools.sar.Storage;

/**
 * @author reiner
 *
 */
public class StorageToStorageEventStage extends AbstractTransformation<Storage, StorageEvent> {

    private final String hostname;

    public StorageToStorageEventStage(final String hostname) {
        this.hostname = hostname;
    }

    @Override
    protected void execute(final Storage storage) throws Exception {
        final String componentSignature;
        if (storage.getModules().size() > 1) {
            componentSignature = storage.getName();
        } else {
            componentSignature = storage.getModules().get(0);
        }
        final String storageSignature = storage.getName();

        this.outputPort.send(new StorageEvent(this.hostname, componentSignature, storageSignature, "unknown"));
    }

}
