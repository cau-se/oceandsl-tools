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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oceandsl.analysis.CallerCallee;

import teetime.stage.basic.AbstractFilter;

/**
 * @author Reiner Jung
 * @since 1.1
 */
public class CSVFixPathStage extends AbstractFilter<CallerCallee> {

    Map<String, String> functionToFileMap = new HashMap<>();

    public CSVFixPathStage(final List<Path> functionMapPaths) throws IOException {
        for (final Path functionMapPath : functionMapPaths) {
            final BufferedReader reader = Files.newBufferedReader(functionMapPath);
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                final String[] values = line.split(",");
                if (values.length >= 2) {
                    this.functionToFileMap.put(values[1].trim(), values[0].trim());
                }
            }
        }
    }

    @Override
    protected void execute(final CallerCallee element) throws Exception {
        if ("".equals(element.getSourcePath())) {
            element.setSourcePath(this.findPath(element.getCaller()));
        }
        if ("".equals(element.getTargetPath())) {
            element.setTargetPath(this.findPath(element.getCallee()));
        }

        this.outputPort.send(element);
    }

    private String findPath(final String functionName) {
        final String path = this.functionToFileMap.get(functionName);
        if (path == null) {
            this.logger.error("Missing function entry for {}", functionName);
            return "";
        } else {
            return path;
        }
    }

}
