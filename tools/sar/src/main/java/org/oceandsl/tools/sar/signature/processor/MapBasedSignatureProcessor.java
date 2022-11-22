/***************************************************************************
 * Copyright 2021 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.sar.signature.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.oceandsl.analysis.utils.MapFileReader;
import org.oceandsl.analysis.utils.StringValueConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class MapBasedSignatureProcessor extends AbstractSignatureProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<String, String> componentMap = new HashMap<>();
    private final PrintWriter missingMappingWriter;

    public MapBasedSignatureProcessor(final List<Path> componentMapFiles, final Path missingMappingFile,
            final boolean caseInsensitive, final String separator) throws IOException {
        super(caseInsensitive);
        for (final Path componentMapFile : componentMapFiles) {
            this.logger.info("Reading map file {}", componentMapFile.toString());

            final MapFileReader<String, String> mapFileReader = new MapFileReader<>(componentMapFile, separator,
                    this.componentMap, new StringValueConverter(caseInsensitive, 1),
                    new StringValueConverter(caseInsensitive, 0));
            mapFileReader.read();
        }
        if (missingMappingFile != null) {
            this.missingMappingWriter = new PrintWriter(Files.newBufferedWriter(missingMappingFile));
        } else {
            this.missingMappingWriter = null; // NOPMD null assignment necessary
        }
    }

    @Override
    public void processSignatures(final String pathString, final String componentSignature,
            final String operationSignature) {
        this.operationSignature = this.convertToLowerCase(operationSignature);

        if ("<<no-file>>".equals(pathString)) {
            this.componentSignature = "<unknown>";
        } else {
            final Path path = Paths.get(pathString);
            final String filename = this.convertToLowerCase(path.getName(path.getNameCount() - 1).toString());
            final String result = this.componentMap.get(filename);
            if (result != null) {
                this.componentSignature = result;
            } else {
                this.logger.warn("File '{}' has no component mapping. Signature '{}'", filename, operationSignature);
                this.componentSignature = "??" + pathString.toLowerCase(Locale.ROOT);
                if (this.missingMappingWriter != null) {
                    this.missingMappingWriter.printf("%s;%s;%s\b", filename, componentSignature, operationSignature);
                }
            }
        }
    }

    @Override
    public void close() {
        this.missingMappingWriter.close();
    }

}
