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
package org.oceandsl.tools.mktable.stages;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;
import org.csveed.report.CsvException;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.analysis.generic.data.MoveOperationEntry;
import org.oceandsl.tools.mktable.Optimization;

/**
 * Reader for CSV files. Modified version. Should be merged
 *
 *
 * @author Reiner Jung
 * @since 1.0
 *
 */
public class CsvReaderStage extends AbstractTransformation<Path, Optimization> {

    private final char separator;
    private final char quoteSymbol;
    private final char escapeSymbol;
    private final boolean header;

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
     * @throws IOException
     *             when a stream could not be opened.
     */
    public CsvReaderStage(final char separator, final char quoteSymbol, final char escapeSymbol, final boolean header) {
        this.separator = separator;
        this.quoteSymbol = quoteSymbol;
        this.escapeSymbol = escapeSymbol;
        this.header = header;
    }

    @Override
    protected void execute(final Path path) throws Exception {
        final Optimization optimization = new Optimization(path.getFileName().toString());
        final BufferedReader reader = Files.newBufferedReader(path);
        final CsvClient<MoveOperationEntry> csvClient = new CsvClientImpl<>(reader, MoveOperationEntry.class);
        csvClient.setQuote(this.quoteSymbol);
        csvClient.setSeparator(this.separator);
        csvClient.setEscape(this.escapeSymbol);
        csvClient.setUseHeader(this.header);
        csvClient.skipEmptyLines(true);

        try {
            while (!csvClient.isFinished()) {
                final MoveOperationEntry bean = csvClient.readBean();
                if (bean != null) {
                    optimization.getList().add(bean);
                } else {
                    break;
                }
            }

            reader.close();
        } catch (final CsvException e) {
            this.logger.error("Error reading csv file in line {} path {}", csvClient.getCurrentLine(), path.toString());
        }
        this.outputPort.send(optimization);
    }

}
