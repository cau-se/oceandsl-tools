package org.oceandsl.tools.sar.bsc.dataflow.model;

import org.oceandsl.tools.sar.stages.dataflow.EDirection;

public class DataTransferObject {

    private String component;
    private String sourceIdent;
    private EDirection direction;
    private String targetIdent;
    private String targetComponent;
    private boolean callsOperationFlag;
    private boolean callsCommonFlag;

    public DataTransferObject(final String component, final String sourceIdent, final EDirection direction, final String targetIdent){

        this.component = component;
        this.sourceIdent = sourceIdent;
        this.direction = direction;
        this.targetIdent = targetIdent;
        this.callsOperationFlag = false;
        this.callsCommonFlag = false;
    }


    public DataTransferObject() {
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

    public EDirection getDirection() {return direction;}

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
}
