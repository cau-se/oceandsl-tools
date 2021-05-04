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
package org.oceandsl.pp.log;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import teetime.framework.AbstractProducerStage;

/**
 * Read function to file map.
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class CSVFunctionMapperStage extends AbstractProducerStage<Map<String, String>> {

    private final List<Path> paths;

    public CSVFunctionMapperStage(final List<Path> paths) {
        this.paths = paths;
    }

    @Override
    protected void execute() throws Exception {
        final Map<String, String> functionMap = new HashMap<>();
        for (final Path path : this.paths) {
            final BufferedReader reader = Files.newBufferedReader(path);
            reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] values = line.split(",");
                if (values.length == 2) {
                    functionMap.put(values[1].trim(), values[0].trim());
                }
            }
            reader.close();
        }
        this.outputPort.send(functionMap);
        this.workCompleted();
    }

}
