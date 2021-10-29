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

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import teetime.stage.basic.AbstractTransformation;

/**
 * Cleanup names and make them lower case if requested.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class MapBasedCleanupInDataflowSignatureStage extends AbstractTransformation<DataAccess, DataAccess> {

    private final boolean caseInsensitive;
    private final Map<String, String> componentMap = new HashMap<>();

    public MapBasedCleanupInDataflowSignatureStage(final List<Path> componentMapFiles, final String seperator,
            final boolean caseInsensitive) throws IOException {
        this.caseInsensitive = caseInsensitive;
        this.setupMap(componentMapFiles, seperator);
    }

    private void setupMap(final List<Path> componentMapFiles, final String separator) throws IOException {
        for (final Path componentMapFile : componentMapFiles) {
            this.logger.info("Reading map file {}", componentMapFile.toString());
            final BufferedReader reader = Files.newBufferedReader(componentMapFile);
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] values = line.split(separator);
                if (values.length == 2) {
                    // 0 = component name
                    // 1 = file name
                    this.componentMap.put(this.convertToLowerCase(values[1].trim()),
                            this.convertToLowerCase(values[0].trim().toLowerCase(Locale.ROOT)));
                } else {
                    this.logger.error("Entry incomplete '{}'", line.trim());
                }
            }
            reader.close();
        }
    }

    @Override
    protected void execute(final DataAccess element) throws Exception {
        element.setModule(this.convertToLowerCase(this.processComponentSignature(element.getModule())));
        element.setOperation(this.convertToLowerCase(this.processSignature(element.getOperation())));
        element.setSharedData(this.convertToLowerCase(this.processSignature(element.getSharedData())));

        this.outputPort.send(element);
    }

    private String convertToLowerCase(final String string) {
        final String value;
        if (string.endsWith("_")) {
            value = string.substring(0, string.length() - 1);
        } else {
            value = string;
        }
        return this.caseInsensitive ? value.toLowerCase(Locale.ROOT) : value;
    }

    private String processSignature(final String signature) {
        if ("<<no-file>>".equals(signature)) {
            return signature;
        } else {
            final Path path = Paths.get(signature);
            return this.convertToLowerCase(path.getName(path.getNameCount() - 1).toString());
        }
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
                return "unknown";
            }
        }
    }
}
