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
package org.oceandsl.architecture.model;

import java.io.File;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

/**
 * All settings including command line parameters for the analysis.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class ArchitectureModelSettings {

    @Parameter(names = { "-i",
            "--input" }, required = true, converter = FileConverter.class, description = "Input Kieker log directory")
    private File inputFile;

    @Parameter(names = { "-o",
            "--output" }, required = true, converter = FileConverter.class, description = "Output directory where to put the graphics")
    private File outputFile;

    @Parameter(names = { "-a",
            "--addrline" }, required = true, converter = FileConverter.class, description = "Location of the addrline tool")
    private File addrlineExecutable;

    @Parameter(names = { "-e",
            "--executable" }, required = true, converter = FileConverter.class, description = "Location of the executable")
    private File modelExecutable;

    @Parameter(names = { "-p",
            "--prefix" }, required = false, description = "Path prefix to be removed from filenames in the visualization")
    private String prefix;

    public File getInputFile() {
        return this.inputFile;
    }

    public File getOutputFile() {
        return this.outputFile;
    }

    public File getAddrlineExecutable() {
        return this.addrlineExecutable;
    }

    public File getModelExecutable() {
        return this.modelExecutable;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
}
