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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oceandsl.architecture.model.data.table.ValueConversionErrorException;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class MapBasedCleanupComponentSignatureStage extends AbstractCleanupComponentSignatureStage {

    private final Map<String, String> componentMap = new HashMap<>();
    private final PrintWriter missingMappingWriter;

    public MapBasedCleanupComponentSignatureStage(final List<Path> componentMapFiles, final Path missingMappingFile,
            final String separator, final boolean caseInsensitive) throws IOException, ValueConversionErrorException {
        super(caseInsensitive);
        if (missingMappingFile != null) {
            this.missingMappingWriter = new PrintWriter(Files.newBufferedWriter(missingMappingFile));
        } else {
            this.missingMappingWriter = null;
        }
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
                            this.convertToLowerCase(values[0].trim().toLowerCase()));
                } else {
                    this.logger.error("Entry incomplete '{}'", line.trim());
                }
            }
            reader.close();
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

    @Override
    protected String processComponentSignature(final String signature) {
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
                if (this.missingMappingWriter != null) {
                    this.missingMappingWriter.println(filename + "; " + signature);
                }
                return "unknown";
            }
        }
    }

    @Override
    protected void onTerminating() {
        if (this.missingMappingWriter != null) {
            this.missingMappingWriter.close();
        }
        super.onTerminating();
    }

}
