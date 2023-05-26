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
package org.oceandsl.analysis.code.stages.data;

import lombok.Getter;

public class DataflowEntry {

    @Getter
    private final String sourcePath;
    @Getter
    private final String sourceModule;
    @Getter
    private final String sourceOperation;
    @Getter
    private final String targetPath;
    @Getter
    private final String targetModule;
    @Getter
    private final String targetOperation;
    @Getter
    private final EDirection direction;

    public DataflowEntry(final String sourcePath, final String sourceModule, final String sourceOperation,
            final String targetPath, final String targetModule, final String targetOperatio,
            final EDirection direction) {
        this.sourcePath = sourcePath;
        this.sourceModule = sourceModule;
        this.sourceOperation = sourceOperation;
        this.targetPath = targetPath;
        this.targetModule = targetModule;
        this.targetOperation = targetOperatio;
        this.direction = direction;
    }

}
