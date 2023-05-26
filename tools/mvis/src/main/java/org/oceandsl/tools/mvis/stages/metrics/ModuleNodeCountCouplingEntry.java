package org.oceandsl.tools.mvis.stages.metrics;

import lombok.Getter;

public class ModuleNodeCountCouplingEntry {

    @Getter
    private final String module;
    @Getter
    private final int inEdges;
    @Getter
    private final int outEdges;

    public ModuleNodeCountCouplingEntry(final String module, final int inEdges, final int outEdges) {
        this.module = module;
        this.inEdges = inEdges;
        this.outEdges = outEdges;
    }

}
