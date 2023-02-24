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
package org.oceandsl.analysis.code;

import kieker.model.analysismodel.execution.EDirection;

import org.oceandsl.analysis.code.stages.data.ICsvRecord;

/**
 * Dataflow analysis record describing a CSV file with 7 columns.
 *
 * @author Reiner Jung
 * @author Yannick Illmann (initial contribution)
 */
public class OperationStorage implements ICsvRecord {

    private String sourcePath;
    private String sourceModule;
    private String sourceSignature;

    private String targetPath;
    private String targetModule;
    private String targetSignature;

    private final EDirection direction;

    public OperationStorage(final String sourcePath, final String sourceModule, final String sourceSignature,
            final String targetPath, final String targetModule, final String targetSignature,
            final EDirection direction) {
        this.sourcePath = sourcePath;
        this.sourceModule = sourceModule;
        this.sourceSignature = sourceSignature;

        this.targetPath = targetPath;
        this.targetModule = targetModule;
        this.targetSignature = targetSignature;

        this.direction = direction;
    }

    public String getSourcePath() {
        return this.sourcePath;
    }

    public void setSourcePath(final String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getSourceModule() {
        return this.sourceModule;
    }

    public void setSourceModule(final String sourceModule) {
        this.sourceModule = sourceModule;
    }

    public String getSourceSignature() {
        return this.sourceSignature;
    }

    public void setSourceSignature(final String sourceSignature) {
        this.sourceSignature = sourceSignature;
    }

    public String getTargetPath() {
        return this.targetPath;
    }

    public void setTargetPath(final String targetPath) {
        this.targetPath = targetPath;
    }

    public String getTargetModule() {
        return this.targetModule;
    }

    public void setTargetModule(final String targetModule) {
        this.targetModule = targetModule;
    }

    public String getTargetSignature() {
        return this.targetSignature;
    }

    public void setTargetSignature(final String targetSignature) {
        this.targetSignature = targetSignature;
    }

    public EDirection getDirection() {
        return this.direction;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof OperationStorage) {
            final OperationStorage other = (OperationStorage) obj;
            return other.getDirection() == this.getDirection()
                    && this.stringCompare(other.getSourceModule(), this.sourceModule)
                    && this.stringCompare(other.getSourcePath(), this.sourcePath)
                    && this.stringCompare(other.getSourceSignature(), this.sourceSignature)
                    && this.stringCompare(other.getTargetModule(), this.targetModule)
                    && this.stringCompare(other.getTargetPath(), this.targetPath)
                    && this.stringCompare(other.getTargetSignature(), this.targetSignature);
        } else {
            return false;
        }
    }

    private boolean stringCompare(final String left, final String right) {
        if (left != null) {
            return left.equals(right);
        } else {
            return right == null;
        }
    }

}
