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
package org.oceandsl.architecture.model.stages;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.oceandsl.architecture.model.stages.data.StringValueHandler;
import org.oceandsl.architecture.model.stages.data.Table;

import teetime.framework.AbstractProducerStage;

/**
 * @author Reiner Jung
 * @since 1.0
 */
public class CSVReaderStage extends AbstractProducerStage<Table> {

    private final BufferedReader reader;

    public CSVReaderStage(final Path path) throws IOException {
        this.reader = Files.newBufferedReader(path);
    }

    @Override
    protected void execute() throws Exception {
        final Table resultTable = new Table("Component-Map", new StringValueHandler("Component"),
                new StringValueHandler("File"), new StringValueHandler("Function"));
        String line;
        while ((line = this.reader.readLine()) != null) {
            final String[] values = line.split(",");
            if (values.length == 3) {
                resultTable.addRow(values[0].trim(), values[1].trim().toLowerCase(), values[2].trim().toLowerCase());
            } else {
                this.logger.error("Entry incomplete '{}'", line.trim());
            }
        }
        this.reader.close();
        this.outputPort.send(resultTable);
        this.workCompleted();
    }
}
