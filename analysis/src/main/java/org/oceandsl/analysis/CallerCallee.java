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
package org.oceandsl.analysis;

/**
 * @author Reiner Jung
 * @since 1.0
 *
 */
public class CallerCallee {

    private final String sourcePath;
    private String targetPath;
    private final String caller;
    private final String callee;

    public CallerCallee(final String sourcePath, final String caller, final String targetPath, final String callee) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        this.caller = caller;
        this.callee = callee;
    }

    public String getCallee() {
        return this.callee;
    }

    public String getCaller() {
        return this.caller;
    }

    public String getSourcePath() {
        return this.sourcePath;
    }

    public String getTargetPath() {
        return this.targetPath;
    }

    public void setTargetPath(final String targetPath) {
        this.targetPath = targetPath;
    }
}
