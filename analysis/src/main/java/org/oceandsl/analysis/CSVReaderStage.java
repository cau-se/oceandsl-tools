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
package org.oceandsl.analysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import teetime.framework.AbstractProducerStage;

/**
 * Reads an 3 column CSV file.
 *
 * @author Reiner Jung
 * @since 1.0
 *
 */
public class CSVReaderStage extends AbstractProducerStage<CallerCallee> {

    private final BufferedReader reader;

    /**
     * Read a single CSV file.
     *
     * @param path
     *            file path
     * @throws IOException
     *             when a stream could not be opened.
     */
    public CSVReaderStage(final Path path) throws IOException {
        this.reader = Files.newBufferedReader(path);
    }

    @Override
    protected void execute() throws Exception {
        // ignore the header
        this.reader.readLine();
        String line;
        while ((line = this.reader.readLine()) != null) {
            final String[] values = line.split(",");
            final CallerCallee result = new CallerCallee(values[0].trim(), values[1].trim(), "", values[2].trim());
            this.outputPort.send(result);
        }
        this.reader.close();
        this.workCompleted();
    }

}
