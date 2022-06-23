package org.oceandsl.tools.sar.bsc.dataflow.model;

import org.oceandsl.tools.sar.stages.dataflow.EDirection;

public class DataTransferObject {

    private String component;
    private String sourceIdent;
    private EDirection rw_action;
    private String targetIdent;
    private String targetComponent;
    private boolean callByValue;

    public DataTransferObject(String component, String sourceIdent, EDirection rw_action, String targetIdent, String callByValue){

        this.component = component;
        this.sourceIdent = sourceIdent;
        this.rw_action = rw_action;
        this.targetIdent = targetIdent;
        this.callByValue = callByValue.isEmpty();
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getSourceIdent() {
        return sourceIdent;
    }

    public void setSourceIdent(String sourceIdent) {
        this.sourceIdent = sourceIdent;
    }

    public EDirection getRw_action() {
        return rw_action;
    }

    public void setRw_action(EDirection rw_action) {
        this.rw_action = rw_action;
    }

    public String getTargetIdent() {
        return targetIdent;
    }

    public void setTargetIdent(String targetIdent) {
        this.targetIdent = targetIdent;
    }

    public String getTargetComponent() {
        return targetComponent;
    }

    public void setTargetComponent(String targetComponent) {
        this.targetComponent = targetComponent;
    }

    public boolean isCallByValue() {
        return callByValue;
    }

    public void setCallByValue(boolean callByValue) {
        this.callByValue = callByValue;
    }

}
