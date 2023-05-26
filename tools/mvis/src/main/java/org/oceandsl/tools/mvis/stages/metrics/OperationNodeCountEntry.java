package org.oceandsl.tools.mvis.stages.metrics;

import lombok.Getter;

public class OperationNodeCountEntry {

    @Getter
    private final String module;
    @Getter
    private final String operation;
    @Getter
    private final int inEdges;
    @Getter
    private final int outEdges;

    public OperationNodeCountEntry(final String module, final String operation, final int inEdges, final int outEdges) {
        this.module = module;
        this.operation = operation;
        this.inEdges = inEdges;
        this.outEdges = outEdges;
    }

}
