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
import java.util.ArrayList;
import java.util.List;

import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;
import org.csveed.report.CsvException;

import teetime.stage.basic.AbstractTransformation;

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
public class CsvReaderStage<T> extends AbstractTransformation<Path, List<T>> {

    private final char separator;
    private final char quoteSymbol;
    private final char escapeSymbol;
    private final boolean header;
    private final Class<T> clazz;

    /**
     * Read a single CSV file.
     *
     * @param separator
     *            string containing the separator symbol for cells
     * @param quoteSymbol
     *            quote symbol used for cells
     * @param escapeSymbol
     *            escape character
     * @param header
     *            indicate how to interpret the first line in the CSV file, set to true to indicate
     *            that the first line contains the header information
     * @param clazz
     *            bean class
     * @throws IOException
     *             when a stream could not be opened.
     */
    public CsvReaderStage(final char separator, final char quoteSymbol, final char escapeSymbol, final boolean header,
            final Class<T> clazz) {
        this.separator = separator;
        this.quoteSymbol = quoteSymbol;
        this.escapeSymbol = escapeSymbol;
        this.header = header;
        this.clazz = clazz;
    }

    @Override
    protected void execute(final Path path) throws Exception {
        final BufferedReader reader = Files.newBufferedReader(path);
        final CsvClient<T> csvClient = new CsvClientImpl<>(reader, this.clazz);
        csvClient.setQuote(this.quoteSymbol);
        csvClient.setSeparator(this.separator);
        csvClient.setEscape(this.escapeSymbol);
        csvClient.setUseHeader(this.header);
        csvClient.skipEmptyLines(true);

        final List<T> outputList = new ArrayList<>();

        try {
            while (!csvClient.isFinished()) {
                final T bean = csvClient.readBean();
                if (bean != null) {
                    outputList.add(bean);
                } else {
                    break;
                }
            }

            reader.close();
        } catch (final CsvException e) {
            this.logger.error("Error reading csv file in line {} path {}", csvClient.getCurrentLine(), path.toString());
        }
        this.outputPort.send(outputList);
    }

}
