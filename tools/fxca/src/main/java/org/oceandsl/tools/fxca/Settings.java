/***************************************************************************
 * Copyright 2023 Kieker Project (http://kieker-monitoring.net)
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
package org.oceandsl.tools.fxca;

import java.nio.file.Path;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.PathConverter;

import org.oceandsl.analysis.generic.validators.ParentPathIsDirectoryValidator;
import org.oceandsl.analysis.generic.validators.ParentPathIsWriteableValidator;
import org.oceandsl.analysis.generic.validators.PathIsDirectoryValidator;
import org.oceandsl.analysis.generic.validators.PathIsReadableValidator;

/**
 * @author Reiner Jung
 * @since 1.3.0
 */
public class Settings {

    @Parameter(names = { "-i",
            "--input" }, required = true, variableArity = true, description = "One or more paths to fxtran-generated XML files.", converter = PathConverter.class, validateWith = {
                    PathIsReadableValidator.class, PathIsDirectoryValidator.class })
    private List<Path> inputDirectoryPaths;

    @Parameter(names = { "-o",
            "--output" }, required = true, description = "Path where the output files are placed.", converter = PathConverter.class, validateWith = {
                    ParentPathIsWriteableValidator.class, ParentPathIsDirectoryValidator.class })
    private Path outputDirectoryPath;

    @Parameter(names = { "-d",
            "--default-component" }, required = false, description = "In case callees are identified that do not have an implementation in the code, assign the callee to this operation.")
    private String defaultComponent;

    @Parameter(names = { "-f",
            "--flat" }, required = false, description = "Scan source directories flat, i.e. not in recusrive mode.")
    private boolean flat;

    public List<Path> getInputDirectoryPaths() {
        return this.inputDirectoryPaths;
    }

    public Path getOutputDirectoryPath() {
        return this.outputDirectoryPath;
    }

    public boolean isFlat() {
        return this.flat;
    }

    public String getDefaultComponent() {
        return this.defaultComponent;
    }
}
