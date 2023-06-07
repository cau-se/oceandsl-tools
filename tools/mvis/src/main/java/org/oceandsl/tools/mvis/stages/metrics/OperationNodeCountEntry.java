package org.oceandsl.tools.mvis.stages.metrics;

import org.csveed.annotations.CsvCell;

public class OperationNodeCountEntry {

    @CsvCell(columnIndex = 1, columnName = "module")
    private final String module;
    @CsvCell(columnIndex = 2, columnName = "operation")
    private final String operation;
    @CsvCell(columnIndex = 3, columnName = "in-edges")
    private final int inEdges;
    @CsvCell(columnIndex = 4, columnName = "out-edges")
    private final int outEdges;

    public OperationNodeCountEntry(final String module, final String operation, final int inEdges, final int outEdges) {
        this.module = module;
        this.operation = operation;
        this.inEdges = inEdges;
        this.outEdges = outEdges;
    }

    public String getModule() {
        return this.module;
    }

    public String getOperation() {
        return this.operation;
    }

    public int getInEdges() {
        return this.inEdges;
    }

    public int getOutEdges() {
        return this.outEdges;
    }

}
