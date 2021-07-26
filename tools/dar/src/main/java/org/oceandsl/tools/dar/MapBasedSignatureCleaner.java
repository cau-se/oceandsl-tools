/***************************************************************************
 * Copyright 2021 Kieker Project (http://kieker-monitoring.net)
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
package org.oceandsl.tools.dar;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kieker.analysis.signature.AbstractSignatureCleaner;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class MapBasedSignatureCleaner extends AbstractSignatureCleaner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<String, String> componentMap = new HashMap<>();

    public MapBasedSignatureCleaner(final Path componentMapFile, final boolean caseInsensitive) throws IOException {
        super(caseInsensitive);
        this.logger.info("Reading map file {}", componentMapFile.toString());
        final BufferedReader reader = Files.newBufferedReader(componentMapFile);
        String line;
        while ((line = reader.readLine()) != null) {
            final String[] values = line.split(",");
            if (values.length == 3) {
                // 0 = component name
                // 1 = file name
                // 2 = function name
                this.componentMap.put(this.convertToLowerCase(this.removeTrailingUnderscore(values[1].trim())),
                        this.convertToLowerCase(this.removeTrailingUnderscore(values[0].trim())));
            } else {
                this.logger.error("Entry incomplete '{}'", line.trim());
            }
        }
        reader.close();
    }

    @Override
    public String processSignature(final String signature) {
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
