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
package org.oceandsl.analysis.generic.stages;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import teetime.framework.AbstractConsumerStage;

import org.oceandsl.analysis.code.stages.data.IValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;
import org.oceandsl.analysis.code.stages.data.ValueConversionErrorException;

/**
 * Save a table as a csv file.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class TableCSVSink extends AbstractConsumerStage<Table> {

    private final Path filePath;
    private final String filename;
    private final boolean header;

    public TableCSVSink(final Path filePath, final String filename, final boolean header) {
        this.filePath = filePath;
        this.filename = filename;
        this.header = header;
    }

    public TableCSVSink(final Path filePath, final String filename) {
        this(filePath, filename, false);
    }

    @Override
    protected void execute(final Table table) throws IOException, ValueConversionErrorException {
        try (BufferedWriter outputStream = Files.newBufferedWriter(
                this.filePath.resolve(String.format("%s-%s", table.getName(), this.filename)),
                StandardCharsets.UTF_8)) {
            if (this.header) {
                this.printHeader(outputStream, table);
            }

            this.printRows(outputStream, table);
        }
    }

    private void printHeader(final BufferedWriter outputStream, final Table table) throws IOException {
        final IValueHandler<?>[] valueHandlers = table.getValueHandlers();
        for (int i = 0; i < valueHandlers.length; i++) {
            outputStream.write(valueHandlers[i].getLabel());
            if (i < valueHandlers.length - 1) {
                outputStream.write(";");
            } else {
                outputStream.write("\n");
            }
        }
    }

    private void printRows(final BufferedWriter outputStream, final Table table)
            throws IOException, ValueConversionErrorException {
        for (final Object[] row : table.getRows()) {
            for (int i = 0; i < row.length; i++) {
                outputStream.write(table.getValueHandler(i).convertToString(row[i]));
                if (i < row.length - 1) {
                    outputStream.write(";");
                } else {
                    outputStream.write("\n");
                }
            }
        }
    }
}
