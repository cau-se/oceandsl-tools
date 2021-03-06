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
package org.oceandsl.analysis.code.stages.data;

/**
 * @author Reiner Jung
 * @since 1.0
 *
 */
public class CallerCallee {

    private String sourcePath;
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

    public void setSourcePath(final String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getTargetPath() {
        return this.targetPath;
    }

    public void setTargetPath(final String targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof CallerCallee) {
            final CallerCallee other = (CallerCallee) object;
            return this.checkString(this.sourcePath, other.getSourcePath())
                    && this.checkString(this.caller, other.getCaller())
                    && this.checkString(this.targetPath, other.getTargetPath())
                    && this.checkString(this.callee, other.getCallee());
        } else {
            return false;
        }
    }

    private boolean checkString(final String left, final String right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            return left.equals(right);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.sourcePath.hashCode() ^ this.caller.hashCode() ^ this.targetPath.hashCode()
                ^ this.callee.hashCode();
    }
}
