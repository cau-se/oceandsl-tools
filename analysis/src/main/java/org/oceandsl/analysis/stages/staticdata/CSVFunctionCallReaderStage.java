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
package org.oceandsl.analysis.stages.staticdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.oceandsl.analysis.stages.staticdata.data.CallerCallee;

import teetime.framework.AbstractProducerStage;

/**
 * Reads an 3 or 4 column CSV file containing calls.
 *
 * @author Reiner Jung
 * @since 1.0
 *
 */
public class CSVFunctionCallReaderStage extends AbstractProducerStage<CallerCallee> {

    private final BufferedReader reader;
	private String splitSymbol;

    /**
     * Read a single CSV file.
     *
     * @param path
     *            file path
     * @param splitSymbol string containing the split symbol
     * @throws IOException
     *             when a stream could not be opened.
     */
    public CSVFunctionCallReaderStage(final Path path, String splitSymbol) throws IOException {
        this.reader = Files.newBufferedReader(path);
        this.splitSymbol = splitSymbol;
    }

    @Override
    protected void execute() throws Exception {
        // ignore the header
        this.reader.readLine();
        String line;
        while ((line = this.reader.readLine()) != null) {
            final String[] values = line.split(splitSymbol);
            if (values.length == 4) {
                this.outputPort
                        .send(new CallerCallee(values[0].trim(), values[1].trim(), values[2].trim(), values[3].trim()));
            } else if (values.length == 3) {
                this.outputPort.send(new CallerCallee(values[0].trim(), values[1].trim(), "", values[2].trim()));
            } else {
                this.logger.error("Line needs at least 3 better 4 values. :{}:", line);
            }
        }
        this.reader.close();
        this.workCompleted();
    }

}
