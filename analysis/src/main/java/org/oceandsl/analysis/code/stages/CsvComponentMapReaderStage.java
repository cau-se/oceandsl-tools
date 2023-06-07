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
import java.util.Locale;

import teetime.framework.AbstractProducerStage;

import org.oceandsl.analysis.generic.Table;

/**
 * Read a CSV file containing a component map.
 *
 * Note: Should use a generic CSV reader parent class.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class CsvComponentMapReaderStage extends AbstractProducerStage<Table<ComponentMapEntry>> {

    private final BufferedReader reader;
    private final String seperator;

    /**
     * Create reader stage.
     *
     * @param path
     *            path to file
     * @param separator
     *            separator character
     *
     * @throws IOException
     *             on io exceptions
     */
    public CsvComponentMapReaderStage(final Path path, final String separator) throws IOException {
        this.reader = Files.newBufferedReader(path);
        this.seperator = separator;
    }

    @Override
    protected void execute() throws Exception {
        final Table<ComponentMapEntry> resultTable = new Table<>("Component-Map");
        String line;
        while ((line = this.reader.readLine()) != null) {
            final String[] values = line.split(this.seperator);
            if (values.length == 3) {
                resultTable.getRows().add(new ComponentMapEntry(values[0].trim(),
                        values[1].trim().toLowerCase(Locale.ROOT), values[2].trim().toLowerCase(Locale.ROOT)));
            } else {
                this.logger.error("Entry incomplete '{}'", line.trim());
            }
        }
        this.reader.close();
        this.outputPort.send(resultTable);
        this.workCompleted();
    }
}
