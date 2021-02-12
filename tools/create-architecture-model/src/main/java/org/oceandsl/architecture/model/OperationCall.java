/**
 *
 */
package org.oceandsl.architecture.model;

import kieker.analysisteetime.model.analysismodel.deployment.DeployedOperation;

/**
 * @author reiner
 *
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
            return (otherCall.getSourceOperation().equals(this.getSourceOperation())
                    && otherCall.getTargetOperation().equals(this.getTargetOperation()));
        } else {
            return false;
        }
    }

}
