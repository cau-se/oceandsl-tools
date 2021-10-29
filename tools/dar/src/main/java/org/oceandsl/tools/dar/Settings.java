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
package org.oceandsl.tools.dar;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import com.beust.jcommander.converters.PathConverter;

/**
 * All settings including command line parameters for the analysis.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class Settings { // NOPMD data class

    @Parameter(names = { "-i",
            "--input" }, required = true, converter = PathConverter.class, description = "Input Kieker log directory location")
    private Path inputFile;

    @Parameter(names = { "-o",
            "--output" }, required = true, converter = PathConverter.class, description = "Output directory to store the recovered models")
    private Path outputDirectory;

    @Parameter(names = { "-M",
            "--component-map" }, required = false, variableArity = true, converter = PathConverter.class, description = "Component, file and function map file")
    private List<Path> componentMapFiles;

    @Parameter(names = { "-a",
            "--addrline" }, required = false, converter = FileConverter.class, description = "Location of the addrline tool")
    private File addrlineExecutable;

    @Parameter(names = { "-e",
            "--executable" }, required = false, converter = FileConverter.class, description = "Location of the executable")
    private File modelExecutable;

    @Parameter(names = { "-l", "--source-label" }, required = true, description = "Set source label for the read data")
    private String sourceLabel;

    @Parameter(names = { "-c",
            "--case-insensitive" }, required = false, description = "Handle function names case insensitive")
    private boolean caseInsensitive;

    @Parameter(names = { "-E", "--experiment-name" }, required = true, description = "Name of the experiment")
    private String experimentName;

    public Path getInputFile() {
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

    public String getSourceLabel() {
        return this.sourceLabel;
    }

    public boolean isCaseInsensitive() {
        return this.caseInsensitive;
    }

    public List<Path> getComponentMapFiles() {
        return this.componentMapFiles;
    }

    public String getExperimentName() {
        return this.experimentName;
    }
}
