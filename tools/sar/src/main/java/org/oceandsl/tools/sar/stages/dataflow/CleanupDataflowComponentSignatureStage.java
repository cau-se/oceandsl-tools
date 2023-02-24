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

import java.util.List;

import teetime.framework.OutputPort;
import teetime.stage.basic.AbstractFilter;

import org.oceandsl.analysis.code.OperationStorage;
import org.oceandsl.tools.sar.signature.processor.AbstractSignatureProcessor;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class CleanupDataflowComponentSignatureStage extends AbstractFilter<OperationStorage> {

    private static final String UNKNOWN = "<unknown>";

    private final OutputPort<String> errorMessageOutputPort = this.createOutputPort(String.class);

    private final List<AbstractSignatureProcessor> processors;

    public CleanupDataflowComponentSignatureStage(final List<AbstractSignatureProcessor> processors) {
        this.processors = processors;
    }

    @Override
    protected void execute(final OperationStorage event) throws Exception {
        final Entry caller = this.executeEntry(event.getSourcePath(), event.getSourceModule(),
                event.getSourceSignature());
        final Entry callee = this.executeEntry(event.getTargetPath(), event.getTargetModule(),
                event.getTargetSignature());
        final OperationStorage newEvent = new OperationStorage(event.getSourcePath(), caller.component, caller.element,
                event.getTargetPath(), callee.component, callee.element, event.getDirection());
        this.outputPort.send(newEvent);
    }

    private Entry executeEntry(final String path, final String componentSignature, final String operationSignature) {
        final Entry entry = new Entry();
        entry.component = CleanupDataflowComponentSignatureStage.UNKNOWN;
        entry.element = CleanupDataflowComponentSignatureStage.UNKNOWN;
        for (final AbstractSignatureProcessor processor : this.processors) {
            if (!processor.processSignatures(path, componentSignature, operationSignature)) {
                this.errorMessageOutputPort.send(processor.getErrorMessage());
            }
            if (CleanupDataflowComponentSignatureStage.UNKNOWN.equals(entry.component)) {
                entry.component = processor.getComponentSignature();
            }
            if (CleanupDataflowComponentSignatureStage.UNKNOWN.equals(entry.element)) {
                entry.element = processor.getElementSignature();
            }
        }
        return entry;
    }

    public OutputPort<String> getErrorMessageOutputPort() {
        return this.errorMessageOutputPort;
    }

    private class Entry {
        private String component;
        private String element;
    }

}
