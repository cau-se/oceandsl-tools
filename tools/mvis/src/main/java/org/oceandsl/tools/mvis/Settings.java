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
package org.oceandsl.tools.mvis;

import java.nio.file.Path;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.PathConverter;

import org.oceandsl.architecture.model.EOutputGraph;
import org.oceandsl.architecture.model.GraphTypeConverter;
import org.oceandsl.architecture.model.stages.graph.ISelector;

/**
 * All settings including command line parameters for the analysis.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class Settings {

    @Parameter(names = { "-i",
            "--input" }, required = true, converter = PathConverter.class, description = "Input Kieker log directory")
    private Path inputDirectory;

    @Parameter(names = { "-o",
            "--output" }, required = true, converter = PathConverter.class, description = "Output directory to store graphics and statistics")
    private Path outputDirectory;

    @Parameter(names = { "-M",
            "--component-map" }, required = false, converter = PathConverter.class, description = "Component, file and function map file")
    private Path componentMapFile;

    @Parameter(names = { "-s",
            "--selector" }, required = true, converter = SelectorKindConverter.class, description = "Set architecture graph selector")
    private ISelector selector;

    @Parameter(names = { "-g",
            "--graphs" }, required = false, variableArity = true, converter = GraphTypeConverter.class, description = "Specify which output graphs must be generated")
    private List<EOutputGraph> outputGraphs;

    public Path getInputDirectory() {
        return this.inputDirectory;
    }

    public Path getOutputDirectory() {
        return this.outputDirectory;
    }

    public Path getComponentMapFile() {
        return this.componentMapFile;
    }

    public List<EOutputGraph> getOutputGraphs() {
        return this.outputGraphs;
    }

    public ISelector getSelector() {
        return this.selector;
    }
}
