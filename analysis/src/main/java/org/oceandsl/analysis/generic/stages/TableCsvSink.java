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

import org.oceandsl.analysis.generic.Table;

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

    public static final char[] LF = { 10 };
    public static final char[] CRLF = { 13, 10 };
    public static final char[] CR = { 13 };

    private final Function<String, Path> filePathFunction;
    private final boolean header;
    private Class<T> clazz;
    private char[] newline;

    public TableCsvSink(final Function<String, Path> filePathFunction, final Class<T> clazz, final boolean header,
            final char[] newline) {
        this.header = header;
        this.filePathFunction = filePathFunction;
        this.clazz = clazz;
        this.newline = newline;
    }

    public TableCsvSink(final Path filePath, final String filename, final Class<T> clazz, final boolean header,
            final char[] newline) {
        this(new Function<>() {

            @Override
            public Path apply(final String name) {
                return filePath.resolve(String.format("%s-%s", name, filename));
            }
        }, clazz, header, newline);
    }

    public TableCsvSink(final Path filePath, final Class<T> clazz, final boolean header, final char[] newline) {
        this(new Function<>() {

            @Override
            public Path apply(final String name) {
                return filePath.resolve(String.format("%s.csv", name));
            }
        }, clazz, header, newline);
    }

    public TableCsvSink(final Function<String, Path> filePathFunction, final Class<T> clazz, final boolean header) {
        this(filePathFunction, clazz, header, LF);
    }

    public TableCsvSink(final Path filePath, final Class<T> clazz, final boolean header) {
        this(filePath, clazz, header, LF);
    }

    public TableCsvSink(final Path filePath, final String filename, final Class<T> clazz) {
        this(filePath, filename, clazz, false, LF);
    }

    public TableCsvSink(final Path filePath, final String filename, final Class<T> clazz, final boolean header) {
        this(filePath, filename, clazz, header, LF);
    }

    @Override
    protected void execute(final Table<T> table) throws IOException {
        try (final BufferedWriter outputStream = Files.newBufferedWriter(this.filePathFunction.apply(table.getName()),
                StandardCharsets.UTF_8)) {
            final CsvClient<T> csvClient = new CsvClientImpl<>(outputStream, this.clazz);
            csvClient.setEndOfLine(this.newline);
            csvClient.setUseHeader(this.header);
            csvClient.writeBeans(table.getRows());
            outputStream.close();
        }
    }
}
