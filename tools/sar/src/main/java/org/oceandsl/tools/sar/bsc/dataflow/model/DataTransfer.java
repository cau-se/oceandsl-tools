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
package org.oceandsl.tools.sar.bsc.dataflow.model;

import org.oceandsl.tools.sar.stages.dataflow.EDirection;

public class DataTransfer {

    private String component;
    private String sourceIdent;
    private String sourcePackage;
    private EDirection direction;
    private String targetIdent;
    private String targetComponent;
    private String targetPackage = ".unknown";
    private boolean callsOperationFlag;
    private boolean callsCommonFlag;

    public DataTransfer(final String component, final String sourceIdent, final EDirection direction,
            final String targetIdent) {

        this.component = component;
        this.sourceIdent = sourceIdent;
        this.direction = direction;
        this.targetIdent = targetIdent;
        this.callsOperationFlag = false;
        this.callsCommonFlag = false;
    }

    public DataTransfer() {
        this.callsOperationFlag = false;
        this.callsCommonFlag = false;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(final String component) {
        this.component = component;
    }

    public String getSourceIdent() {
        return sourceIdent;
    }

    public void setSourceIdent(final String sourceIdent) {
        this.sourceIdent = sourceIdent;
    }

    public EDirection getDirection() {
        return direction;
    }

    public String getTargetIdent() {
        return targetIdent;
    }

    public String getTargetComponent() {
        return targetComponent;
    }

    public void setTargetComponent(final String targetComponent) {
        this.targetComponent = targetComponent;
    }

    public boolean callsOperation() {
        return callsOperationFlag;
    }

    public void setCallsOperation(final boolean callsOperation) {
        this.callsOperationFlag = callsOperation;
    }

    public void setCallsCommon(final boolean callsCommon) {
        this.callsCommonFlag = callsCommon;
    }

    public boolean callsCommon() {
        return callsCommonFlag;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(final String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getSourcePackage() {
        return sourcePackage;
    }

    public void setSourcePackage(final String sourcePackage) {
        this.sourcePackage = sourcePackage;
    }
}
