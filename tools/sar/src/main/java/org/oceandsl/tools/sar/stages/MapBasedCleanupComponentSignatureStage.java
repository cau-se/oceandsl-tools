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
package org.oceandsl.tools.sar.stages;

import java.util.List;

import org.oceandsl.analysis.code.stages.data.CallerCallee;
import org.oceandsl.tools.sar.signature.processor.AbstractSignatureProcessor;

import teetime.stage.basic.AbstractFilter;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class MapBasedCleanupComponentSignatureStage extends AbstractFilter<CallerCallee> {

    private static final String UNKNOWN = "<unknown>";
    private final List<AbstractSignatureProcessor> processors;

    public MapBasedCleanupComponentSignatureStage(final List<AbstractSignatureProcessor> processors) {
        this.processors = processors;
    }

    @Override
    protected void execute(final CallerCallee event) throws Exception {
        final Operation caller = this.executeOperation(event.getSourcePath(), event.getSourceModule(),
                event.getCaller());
        final Operation callee = this.executeOperation(event.getTargetPath(), event.getTargetModule(),
                event.getCallee());
        final CallerCallee newEvent = new CallerCallee(event.getSourcePath(), caller.component, caller.operation,
                event.getTargetPath(), callee.component, callee.operation);
        this.outputPort.send(newEvent);
    }

    private Operation executeOperation(final String path, final String componentSignature,
            final String operationSignature) {
        final Operation operation = new Operation();
        operation.component = MapBasedCleanupComponentSignatureStage.UNKNOWN;
        operation.operation = MapBasedCleanupComponentSignatureStage.UNKNOWN;
        for (final AbstractSignatureProcessor processor : this.processors) {
            processor.processSignatures(path, componentSignature, operationSignature);
            if (operation.component.equals(MapBasedCleanupComponentSignatureStage.UNKNOWN)) {
                operation.component = processor.getComponentSignature();
            }
            if (operation.operation.equals(MapBasedCleanupComponentSignatureStage.UNKNOWN)) {
                operation.operation = processor.getOperationSignature();
            }
        }
        return operation;
    }

    @Override
    protected void onTerminating() {
        this.processors.forEach(processor -> processor.close());
        super.onTerminating();
    }

    private class Operation {
        public String component;
        public String operation;
    }

}
