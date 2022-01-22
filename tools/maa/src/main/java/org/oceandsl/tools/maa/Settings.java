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
package org.oceandsl.tools.maa;

import java.nio.file.Path;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.PathConverter;

/**
 * @author Reiner Jung
 * @since 1.2
 */
public class Settings {

    @Parameter(names = { "-i",
            "--input" }, required = true, variableArity = false, converter = PathConverter.class, description = "Input architecture model directory")
    private Path inputModelPath;

    @Parameter(names = { "-o",
            "--output" }, required = true, variableArity = false, converter = PathConverter.class, description = "Output architecture model directory")
    private Path outputModelPath;

    @Parameter(names = { "-I",
            "--compute-interfaces" }, required = false, description = "Compute interfaces based on aggregated invocations")
    private boolean computeInterfaces;

    public Path getInputModelPath() {
        return this.inputModelPath;
    }

    public Path getOutputModelPath() {
        return this.outputModelPath;
    }

    public boolean isComputeInterfaces() {
        return this.computeInterfaces;
    }

}
