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
import java.util.function.Function;

import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;

import teetime.framework.AbstractConsumerStage;

import org.oceandsl.analysis.code.stages.data.Table;

/**
 * Save tables with a specific row type as a csv files based on a path function.
 *
 * @param <T>
 *            row type
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class TableCsvSink<T> extends AbstractConsumerStage<Table<T>> {

    private final Function<String, Path> filePathFunction;
    private final boolean header;

    public TableCsvSink(final Function<String, Path> filePathFunction, final boolean header) {
        this.header = header;
        this.filePathFunction = filePathFunction;
    }

    public TableCsvSink(final Path filePath, final String filename, final boolean header) {
        this.header = header;
        this.filePathFunction = new Function<>() {

            @Override
            public Path apply(final String name) {
                return filePath.resolve(String.format("%s-%s", name, filename));
            }
        };
    }

    public TableCsvSink(final Path filePath, final boolean header) {
        this.header = header;
        this.filePathFunction = new Function<>() {

            @Override
            public Path apply(final String name) {
                return filePath.resolve(String.format("%s.csv", name));
            }
        };
    }

    public TableCsvSink(final Path filePath, final String filename) {
        this(filePath, filename, false);
    }

    @Override
    protected void execute(final Table<T> table) throws IOException {
        try (BufferedWriter outputStream = Files.newBufferedWriter(this.filePathFunction.apply(table.getName()),
                StandardCharsets.UTF_8)) {
            final CsvClient<T> csvClient = new CsvClientImpl<>(outputStream);
            if (this.header) {
                csvClient.writeHeader(table.getHeader());
            }
            csvClient.writeBeans(table.getRows());
            csvClient.isFinished();
        }
    }
}
