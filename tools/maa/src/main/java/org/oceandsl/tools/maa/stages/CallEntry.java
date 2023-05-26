package org.oceandsl.tools.maa.stages;

import lombok.Getter;

public class CallEntry {

    @Getter
    private final String callerComponent;
    @Getter
    private final String caller;
    @Getter
    private final String calleeComponent;
    @Getter
    private final String callee;
    @Getter
    private final int numOfCalls;

    public CallEntry(final String callerComponent, final String caller, final String calleeComponent,
            final String callee, final int numOfCalls) {
        this.callerComponent = callerComponent;
        this.caller = caller;
        this.calleeComponent = calleeComponent;
        this.callee = callee;
        this.numOfCalls = numOfCalls;
    }

}
