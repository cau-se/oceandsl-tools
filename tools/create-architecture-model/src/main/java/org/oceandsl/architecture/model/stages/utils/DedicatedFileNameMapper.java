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
package org.oceandsl.architecture.model.stages.utils;

import java.nio.file.Path;
import java.util.function.Function;

import kieker.analysis.graph.IGraph;
import kieker.analysis.graph.util.FileExtension;

/**
 * @author Reiner Jung
 * @since 1.0
 */
public class DedicatedFileNameMapper implements Function<IGraph, Path> {

    private final Path outputDirectory;
    private final FileExtension fileExtension;
    private final String outputFilename;

    /**
     * Create a simple file mapper.
     *
     * @param outputDirectory
     *            output directory path
     * @param outputFilename
     *            filename
     * @param fileExtension
     *            file extension for the graph
     */
    public DedicatedFileNameMapper(final Path outputDirectory, final String outputFilename,
            final FileExtension fileExtension) {
        this.outputDirectory = outputDirectory;
        this.outputFilename = outputFilename;
        this.fileExtension = fileExtension;
    }

    @Override
    public Path apply(final IGraph graph) {
        return this.outputDirectory
                .resolve(String.format("%s-%s.%s", graph.getName(), this.outputFilename, this.fileExtension));
    }
}
