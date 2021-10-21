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

import java.nio.file.Path;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.PathConverter;

/**
 * All settings including command line parameters for the analysis.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class Settings {

    @Parameter(names = { "-i",
            "--call-input" }, required = false, converter = PathConverter.class, description = "Function call CSV file")
    private Path operationCallInputFile;

    @Parameter(names = { "-j",
            "--dataflow-input" }, required = false, converter = PathConverter.class, description = "Dataflow CSV file")
    private Path dataflowInputFile;

    @Parameter(names = { "-cs",
            "--call-separation-char" }, required = false, description = "Separation character for operation call CSV files, default is comma (,)")
    private String callSplitSymbol;

    @Parameter(names = { "-ds",
            "--dataflow-separation-char" }, required = false, description = "Separation character for dataflow CSV files, default is comma (,)")
    private String dataflowSplitSymbol;

    @Parameter(names = { "-ns",
            "--names-separation-char" }, required = false, description = "Separation character for function name lists CSV files, default is comma (,)")
    private String namesSplitSymbol;

    @Parameter(names = { "-f",
            "--function-names" }, required = false, variableArity = true, converter = PathConverter.class, description = "Function file map CSV file")
    private List<Path> functionNameFiles;

    @Parameter(names = { "-m",
            "--missing-functions" }, required = false, converter = PathConverter.class, description = "Output file for the list of function without an associated file")
    private Path missingFunctionsFile;

    @Parameter(names = { "-o",
            "--output" }, required = true, converter = PathConverter.class, description = "Output directory to store graphics and statistics")
    private Path outputDirectory;

    @Parameter(names = { "-M",
            "--component-map" }, required = false, variableArity = true, converter = PathConverter.class, description = "Component, file and function map file")
    private List<Path> componentMapFiles;

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

    @Parameter(names = { "-n",
            "--missing-mappings" }, required = false, converter = PathConverter.class, description = "Output file for files without a mapping in the mapping file.")
    private Path missingMappingFile;

    public Path getOperationCallInputFile() {
        return this.operationCallInputFile;
    }

    public List<Path> getFunctionNameFiles() {
        return this.functionNameFiles;
    }

    public String getCallSplitSymbol() {
        if (this.callSplitSymbol == null) {
            return ",";
        } else {
            return this.callSplitSymbol;
        }
    }

    public String getDataflowSplitSymbol() {
        if (this.dataflowSplitSymbol == null) {
            return ",";
        } else {
            return this.dataflowSplitSymbol;
        }
    }

    public String getNamesSplitSymbol() {
        if (this.namesSplitSymbol == null) {
            return ",";
        } else {
            return this.namesSplitSymbol;
        }
    }

    public Path getOutputDirectory() {
        return this.outputDirectory;
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

    public List<Path> getComponentMapFiles() {
        return this.componentMapFiles;
    }

    public Path getDataflowInputFile() {
        return this.dataflowInputFile;
    }

    public Path getMissingFunctionsFile() {
        return this.missingFunctionsFile;
    }

    public Path getMissingMappingFile() {
        return this.missingMappingFile;
    }
}
