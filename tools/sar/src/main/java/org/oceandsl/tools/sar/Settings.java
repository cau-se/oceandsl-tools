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
package org.oceandsl.tools.sar;

import java.io.File;
import java.nio.file.Path;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import com.beust.jcommander.converters.PathConverter;

/**
 * All settings including command line parameters for the analysis.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class Settings {

    @Parameter(names = { "-i",
            "--input" }, required = true, converter = FileConverter.class, description = "Input Kieker log directory or CSV file location")
    private File inputFile;

    @Parameter(names = { "-o",
            "--output" }, required = true, converter = PathConverter.class, description = "Output directory to store graphics and statistics")
    private Path outputDirectory;

    @Parameter(names = { "-M",
            "--component-map" }, required = false, converter = PathConverter.class, description = "Component, file and function map file")
    private Path componentMapFile;

    @Parameter(names = { "-a",
            "--addrline" }, required = false, converter = FileConverter.class, description = "Location of the addrline tool")
    private File addrlineExecutable;

    @Parameter(names = { "-e",
            "--executable" }, required = false, converter = FileConverter.class, description = "Location of the executable")
    private File modelExecutable;

    @Parameter(names = { "-ia",
            "--input-architecture-model" }, required = false, converter = FileConverter.class, description = "Directory for an input architecture model")
    private File inputArchitectureModelDirectory;

    @Parameter(names = { "-oa",
            "--output-architecture-model" }, required = false, converter = FileConverter.class, description = "Directory for an output architecture model")
    private File outputArchitectureModelDirectory;

    @Parameter(names = { "-l", "--source-label" }, required = true, description = "Set source label for the read data")
    private String sourceLabel;

    @Parameter(names = { "-c",
            "--case-insensitive" }, required = false, description = "Handle function names in CSV case insensitive")
    private boolean caseInsensitive;

    @Parameter(names = { "-H",
            "--hostname" }, required = false, description = "Hostname to be used in CSV reconstruction")
    private String hostname;

    @Parameter(names = { "-E", "--experiment-name" }, required = true, description = "Name of the experiment")
    private String experimentName;

    public File getInputFile() {
        return this.inputFile;
    }

    public Path getOutputDirectory() {
        return this.outputDirectory;
    }

    public File getAddrlineExecutable() {
        return this.addrlineExecutable;
    }

    public File getModelExecutable() {
        return this.modelExecutable;
    }

    public File getInputArchitectureModelDirectory() {
        return this.inputArchitectureModelDirectory;
    }

    public File getOutputArchitectureModelDirectory() {
        return this.outputArchitectureModelDirectory;
    }

    public String getSourceLabel() {
        return this.sourceLabel;
    }

    public boolean getCaseInsensitive() {
        return this.caseInsensitive;
    }

    public String getHostname() {
        return this.hostname == null ? "<static>" : this.hostname;
    }

    public String getExperimentName() {
        return this.experimentName;
    }

    public Path getComponentMapFile() {
        return this.componentMapFile;
    }
}
