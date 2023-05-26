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
package org.oceandsl.analysis.code.stages;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;

import teetime.framework.AbstractProducerStage;

/**
 * Reader for CSV files.
 *
 * @param <T>
 *            ICsvRecord datatype
 *
 * @author Reiner Jung
 * @since 1.0
 *
 */
public class CsvReaderStage<T> extends AbstractProducerStage<T> {

    private final CsvClient<T> csvClient;
    private final BufferedReader reader;

    /**
     * Read a single CSV file.
     *
     * @param path
     *            file path
     * @param separator
     *            string containing the separator symbol for cells
     * @param quoteSymbol
     *            quote symbol used for cells
     * @param escapeSymbol
     *            escape character
     * @param header
     *            indicate how to interpret the first line in the CSV file, set to true to indicate
     *            that the first line contains the header information
     * @throws IOException
     *             when a stream could not be opened.
     */
    public CsvReaderStage(final Path path, final char separator, final char quoteSymbol, final char escapeSymbol,
            final boolean header) throws IOException {
        this.reader = Files.newBufferedReader(path);
        this.csvClient = new CsvClientImpl<>(this.reader);
        this.csvClient.setQuote(quoteSymbol);
        this.csvClient.setSeparator(separator);
        this.csvClient.setEscape(escapeSymbol);
        this.csvClient.setUseHeader(header);
    }

    @Override
    protected void execute() throws Exception {
        while (!this.csvClient.isFinished()) {
            this.outputPort.send(this.csvClient.readBean());
        }

        this.reader.close();
        this.workCompleted();
    }

}
