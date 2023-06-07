package org.oceandsl.tools.mvis.stages.metrics;

import org.csveed.annotations.CsvCell;

public class ModuleNodeCountCouplingEntry {

    @CsvCell(columnIndex = 1, columnName = "module")
    private String module;
    @CsvCell(columnIndex = 2, columnName = "in-edges")
    private int inEdges;
    @CsvCell(columnIndex = 3, columnName = "out-edges")
    private int outEdges;

    public ModuleNodeCountCouplingEntry(final String module, final int inEdges, final int outEdges) {
        this.module = module;
        this.inEdges = inEdges;
        this.outEdges = outEdges;
    }

    public String getModule() {
        return this.module;
    }

    public void setModule(final String module) {
        this.module = module;
    }

    public int getInEdges() {
        return this.inEdges;
    }

    public void setInEdges(final int inEdges) {
        this.inEdges = inEdges;
    }

    public int getOutEdges() {
        return this.outEdges;
    }

    public void setOutEdges(final int outEdges) {
        this.outEdges = outEdges;
    }

}
