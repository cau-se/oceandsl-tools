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
package org.oceandsl.tools.sar.stages.dataflow;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import teetime.framework.OutputPort;
import teetime.stage.basic.AbstractFilter;

import org.oceandsl.tools.sar.Storage;
import org.oceandsl.tools.sar.signature.processor.AbstractSignatureProcessor;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class CleanupStorageComponentSignatureStage extends AbstractFilter<Storage> {

    private static final String UNKNOWN = "<unknown>";

    private final OutputPort<String> errorMessageOutputPort = this.createOutputPort(String.class);

    private final List<AbstractSignatureProcessor> processors;

    public CleanupStorageComponentSignatureStage(final List<AbstractSignatureProcessor> processors) {
        this.processors = processors;
    }

    @Override
    protected void execute(final Storage event) throws Exception {
        this.outputPort.send(this.executeEntry(event.getFiles(), event.getModules(), event.getName()));
    }

    private Storage executeEntry(final List<String> paths, final List<String> componentSignatures,
            final String storageSignature) {
        final Entry entry = new Entry();
        final Storage storage = new Storage(storageSignature);
        final Set<String> modules = new HashSet<>();

        for (int i = 0; i < paths.size(); i++) {
            entry.component = CleanupStorageComponentSignatureStage.UNKNOWN;
            entry.element = CleanupStorageComponentSignatureStage.UNKNOWN;
            for (final AbstractSignatureProcessor processor : this.processors) {
                if (!processor.processSignatures(paths.get(i), componentSignatures.get(i), storageSignature)) {
                    this.errorMessageOutputPort.send(processor.getErrorMessage());
                }
                if (entry.component.equals(CleanupStorageComponentSignatureStage.UNKNOWN)) {
                    entry.component = processor.getComponentSignature();
                }
                // TODO this might be obsolete
                if (entry.element.equals(CleanupStorageComponentSignatureStage.UNKNOWN)) {
                    entry.element = processor.getElementSignature();
                }
            }
            modules.add(entry.component);
        }
        storage.getFiles().addAll(paths);
        storage.getModules().addAll(modules);

        return storage;
    }

    public OutputPort<String> getErrorMessageOutputPort() {
        return this.errorMessageOutputPort;
    }

    private class Entry {
        private String component;
        private String element;
    }

}
