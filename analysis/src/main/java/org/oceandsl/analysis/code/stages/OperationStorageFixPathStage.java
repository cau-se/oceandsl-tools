/***************************************************************************
 * Copyright (C) 2022 OceanDSL (https://oceandsl.uni-kiel.de)
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import teetime.framework.OutputPort;
import teetime.stage.basic.AbstractFilter;

import org.oceandsl.analysis.code.OperationStorage;

/**
 * This stage receives an {@link OperationStorage} object and checks whether the file path for
 * source and target entry are specified. In case they are missing, the stage sets them based on its
 * entry to file lookup table. In case the entry is not listed, it collects all entries which do not
 * have a file name.
 *
 * <ul>
 * <li>outputPort sends out {@link OperationStorage} objects with all values set.</li>
 * <li>missingEntryOutputPort sends out each newly found operation which does not have a associated
 * file path.</li>
 * </ul>
 *
 * @author Reiner Jung
 * @since 1.1
 */
public class OperationStorageFixPathStage extends AbstractFilter<OperationStorage> {

    private final Map<String, String> entryToFileMap = new HashMap<>();
    private final OutputPort<String> missingEntryOutputPort = this.createOutputPort(String.class);
    private final List<String> missingEntryNames = new ArrayList<>();

    public OperationStorageFixPathStage(final List<Path> entryMapPaths, final String splitSymbol) throws IOException {
        for (final Path functionMapPath : entryMapPaths) {
            try (BufferedReader reader = Files.newBufferedReader(functionMapPath)) {
                String line = reader.readLine();
                while ((line = reader.readLine()) != null) {
                    final String[] values = line.split(splitSymbol);
                    if (values.length >= 2) {
                        this.entryToFileMap.put(values[1].trim(), values[0].trim());
                    }
                }
            }
        }
    }

    @Override
    protected void execute(final OperationStorage element) throws Exception {
        if ("".equals(element.getSourcePath())) {
            element.setSourcePath(this.findPath(element.getSourceSignature()));
        }
        if ("".equals(element.getTargetPath())) {
            element.setTargetPath(this.findPath(element.getTargetSignature()));
        }

        this.outputPort.send(element);
    }

    private String findPath(final String functionName) {
        final String path = this.entryToFileMap.get(functionName);
        if (path == null) {
            if (!this.missingEntryNames.contains(functionName)) {
                this.logger.warn("Missing file entry for operation: {}", functionName);
                this.missingEntryNames.add(functionName);
                this.missingEntryOutputPort.send(functionName);
            }
            return "";
        } else {
            return path;
        }
    }

    public OutputPort<String> getMissingEntryOutputPort() {
        return this.missingEntryOutputPort;
    }
}