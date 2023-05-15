package org.oceandsl.tools.delta;

/***************************************************************************
 * Copyright (C) 2023 OceanDSL (https://oceandsl.uni-kiel.de)
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

import java.nio.file.Path;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.PathConverter;

/**
 * All settings including command line parameters for the analysis.
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public class Settings { // NOPMD data class

    @Parameter(names = { "-i",
            "--input" }, required = true, converter = PathConverter.class, description = "Input restructure information")
    private Path inputPath;

    @Parameter(names = { "-o",
            "--output" }, required = true, converter = PathConverter.class, description = "Output restructure information as CSV table")
    private Path outputPath;

    public Path getInputPath() {
        return this.inputPath;
    }

    public Path getOutputPath() {
        return this.outputPath;
    }
}