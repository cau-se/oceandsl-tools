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
package org.oceandsl.architecture.model.stages;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.oceandsl.architecture.model.stages.data.ValueConversionErrorException;

import kieker.common.record.IMonitoringRecord;
import kieker.common.record.flow.trace.operation.AfterOperationEvent;
import kieker.common.record.flow.trace.operation.BeforeOperationEvent;
import teetime.stage.basic.AbstractTransformation;

/**
 * Remove the directory name portion of class signatures.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class FileBasedCleanupComponentSignatureStage
        extends AbstractTransformation<IMonitoringRecord, IMonitoringRecord> {

    private final boolean caseInsensitive;

    public FileBasedCleanupComponentSignatureStage(final boolean caseInsensitive)
            throws IOException, ValueConversionErrorException {
        this.caseInsensitive = caseInsensitive;
    }

    @Override
    protected void execute(final IMonitoringRecord event) throws Exception {
        if (event instanceof BeforeOperationEvent) {
            final BeforeOperationEvent before = (BeforeOperationEvent) event;
            this.outputPort.send(new BeforeOperationEvent(before.getTimestamp(), before.getTraceId(),
                    before.getOrderIndex(), before.getOperationSignature(),
                    this.processComponentSignature(before.getClassSignature())));
        } else if (event instanceof AfterOperationEvent) {
            final AfterOperationEvent after = (AfterOperationEvent) event;
            this.outputPort
                    .send(new AfterOperationEvent(after.getTimestamp(), after.getTraceId(), after.getOrderIndex(),
                            after.getOperationSignature(), this.processComponentSignature(after.getClassSignature())));
        } else {
            this.outputPort.send(event);
        }
    }

    private String convertToLowerCase(final String string) {
        String value;
        if (string.endsWith("_")) {
            value = string.substring(0, string.length() - 1);
        } else {
            value = string;
        }
        return this.caseInsensitive ? value.toLowerCase() : value;
    }

    private String processComponentSignature(final String signature) {
        if ("<<no-file>>".equals(signature)) {
            return signature;
        } else {
            final Path path = Paths.get(signature);
            return this.convertToLowerCase(path.getName(path.getNameCount() - 1).toString());
        }
    }

}
