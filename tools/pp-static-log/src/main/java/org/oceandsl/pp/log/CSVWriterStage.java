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
package org.oceandsl.pp.log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.oceandsl.analysis.CallerCallee;

import teetime.framework.AbstractConsumerStage;

/**
 * @author Reiner Jung
 * @since 1.0
 *
 */
public class CSVWriterStage extends AbstractConsumerStage<CallerCallee> {

    final BufferedWriter writer;

    public CSVWriterStage(final Path outputPath) throws IOException {
        this.writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8);
    }

    @Override
    protected void execute(final CallerCallee element) throws Exception {
        final String result = String.format("%s,%s,%s,%s\n", element.getSourcePath(), element.getCaller(),
                element.getTargetPath(), element.getCallee());
        this.writer.write(result);
    }

    @Override
    protected void onTerminating() {
        try {
            this.writer.close();
        } catch (final IOException e) {
            this.logger.error("Could not close writer. Cause: {}", e.getLocalizedMessage());
        }
        super.onTerminating();
    }

}
