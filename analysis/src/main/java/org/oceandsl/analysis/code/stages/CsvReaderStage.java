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

import teetime.framework.AbstractProducerStage;

import org.oceandsl.analysis.code.stages.data.ICsvRecord;
import org.oceandsl.analysis.code.stages.data.ICsvRecordFactory;

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
public class CsvReaderStage<T extends ICsvRecord> extends AbstractProducerStage<T> {

    private final BufferedReader reader;
    private final String splitSymbol;
    private final boolean header;
    private final ICsvRecordFactory<T> recordFactory;

    /**
     * Read a single CSV file.
     *
     * @param path
     *            file path
     * @param splitSymbol
     *            string containing the split symbol
     * @param header
     *            indicate how to interpret the first line in the CSV file, set to true to indicate
     *            that the first line contains the header information
     * @param recordFactory
     *            factory for record values
     * @throws IOException
     *             when a stream could not be opened.
     */
    public CsvReaderStage(final Path path, final String splitSymbol, final boolean header,
            final ICsvRecordFactory<T> recordFactory) throws IOException {
        this.reader = Files.newBufferedReader(path);
        this.splitSymbol = splitSymbol;
        this.header = header;
        this.recordFactory = recordFactory;
    }

    @Override
    protected void execute() throws Exception {
        final String[] headerLabels;
        if (this.header) {
            headerLabels = this.reader.readLine().split(this.splitSymbol);
            for (int i = 0; i < headerLabels.length; i++) {
                headerLabels[i] = headerLabels[i].trim();
            }
        } else {
            final String[] values = this.reader.readLine().split(this.splitSymbol);
            headerLabels = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                headerLabels[i] = "column " + i;
            }
        }

        String line;
        while ((line = this.reader.readLine()) != null) {
            final String[] values = line.split(this.splitSymbol);
            for (int i = 0; i < values.length; i++) {
                values[i] = values[i].trim();
            }
            if (values.length == headerLabels.length) {
                this.outputPort.send(this.recordFactory.createRecord(headerLabels, values));
            } else {
                this.logger.error("Line needs at least 3 better 4 values. :{}:", line);
            }
        }
        this.reader.close();
        this.workCompleted();
    }

}
