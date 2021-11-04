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

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.oceandsl.analysis.stages.staticdata.data.ValueConversionErrorException;
import org.oceandsl.analysis.utils.MapFileReader;
import org.oceandsl.analysis.utils.StringValueConverter;

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
            this.missingMappingWriter = null; // NOPMD null assignment necessary
        }
        for (final Path componentMapFile : componentMapFiles) {
            this.logger.info("Reading map file {}", componentMapFile.toString());
            final MapFileReader<String, String> mapFileReader = new MapFileReader<>(componentMapFile, separator,
                    this.componentMap, new StringValueConverter(caseInsensitive, 1),
                    new StringValueConverter(caseInsensitive, 0));
            mapFileReader.read();
        }
    }

    @Override
    protected String processComponentSignature(final String signature) {
        if ("<<no-file>>".equals(signature)) {
            return signature;
        } else {
            final Path path = Paths.get(signature);
            final String filename = path.getName(path.getNameCount() - 1).toString();
            final String result = this.componentMap
                    .get(this.caseInsensitive ? filename.toLowerCase(Locale.ROOT) : filename);
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
