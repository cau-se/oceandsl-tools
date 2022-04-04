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
package org.oceandsl.analysis.architecture;

import kieker.model.analysismodel.deployment.DeployedOperation;

/**
 * A Single operation call. Data class.
 *
 * @author Reiner Jung
 * @since 1.0
 */
public class OperationCall {

    private final DeployedOperation sourceOperation;
    private final DeployedOperation targetOperation;

    public OperationCall(final DeployedOperation sourceOperation, final DeployedOperation targetOperation) {
        this.sourceOperation = sourceOperation;
        this.targetOperation = targetOperation;
    }

    public DeployedOperation getSourceOperation() {
        return this.sourceOperation;
    }

    public DeployedOperation getTargetOperation() {
        return this.targetOperation;
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof OperationCall) {
            final OperationCall otherCall = (OperationCall) object;
            return otherCall.getSourceOperation().equals(this.getSourceOperation())
                    && otherCall.getTargetOperation().equals(this.getTargetOperation());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.sourceOperation.hashCode() ^ this.targetOperation.hashCode();
    }

}
