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
package org.oceandsl.architecture.model.stages.sinks;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.oceandsl.architecture.model.data.table.Table;

import teetime.framework.AbstractConsumerStage;

/**
 * Save a table as a csv file.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class TableCSVSink extends AbstractConsumerStage<Table> {

    private final Path filePath;
    private final String filename;

    public TableCSVSink(final Path filePath, final String filename) {
        this.filePath = filePath;
        this.filename = filename;
    }

    @Override
    protected void execute(final Table table) throws Exception {
        final BufferedWriter outputStream = Files.newBufferedWriter(
                this.filePath.resolve(String.format("%s-%s", table.getName(), this.filename)), StandardCharsets.UTF_8);
        for (final Object[] row : table.getRows()) {
            for (int columnCount = 0; columnCount < row.length; columnCount++) {
                outputStream.write(table.getValueHandler(columnCount).convertToString(row[columnCount]));
                if (columnCount < (row.length - 1)) {
                    outputStream.write(";");
                } else {
                    outputStream.write("\n");
                }
            }
        }
        outputStream.close();
    }

}
