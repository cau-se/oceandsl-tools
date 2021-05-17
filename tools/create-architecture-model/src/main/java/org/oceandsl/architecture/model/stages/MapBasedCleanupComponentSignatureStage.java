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

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.oceandsl.architecture.model.stages.data.ValueConversionErrorException;

import kieker.common.record.IMonitoringRecord;
import kieker.common.record.flow.trace.operation.AfterOperationEvent;
import kieker.common.record.flow.trace.operation.BeforeOperationEvent;
import teetime.stage.basic.AbstractTransformation;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class MapBasedCleanupComponentSignatureStage
        extends AbstractTransformation<IMonitoringRecord, IMonitoringRecord> {

    private final Map<String, String> componentMap = new HashMap<>();
    private final boolean caseInsensitive;

    public MapBasedCleanupComponentSignatureStage(final Path componentMapFile, final boolean caseInsensitive)
            throws IOException, ValueConversionErrorException {
        this.logger.info("Reading map file {}", componentMapFile.toString());
        this.caseInsensitive = caseInsensitive;
        final BufferedReader reader = Files.newBufferedReader(componentMapFile);
        String line;
        while ((line = reader.readLine()) != null) {
            final String[] values = line.split(",");
            if (values.length == 3) {
                // 0 = component name
                // 1 = file name
                // 2 = function name
                this.componentMap.put(this.convertToLowerCase(values[1].trim()),
                        this.convertToLowerCase(values[0].trim().toLowerCase()));
            } else {
                this.logger.error("Entry incomplete '{}'", line.trim());
            }
        }
        reader.close();
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
            final String filename = this.convertToLowerCase(path.getName(path.getNameCount() - 1).toString());
            final String result = this.componentMap.get(filename);
            if (result != null) {
                return result;
            } else {
                this.logger.warn("File '{}' has no component mapping. Signature '{}'", filename, signature);
                return "??" + signature.toLowerCase();
            }
        }
    }

}
