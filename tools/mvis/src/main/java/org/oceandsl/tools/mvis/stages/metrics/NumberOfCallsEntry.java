package org.oceandsl.tools.mvis.stages.metrics;

import org.csveed.annotations.CsvCell;

public class NumberOfCallsEntry {

    @CsvCell(columnIndex = 1, columnName = "source-path")
    private final String sourceFile;
    @CsvCell(columnIndex = 2, columnName = "source-operation")
    private final String sourceFunction;
    @CsvCell(columnIndex = 3, columnName = "target-path")
    private final String targetFile;
    @CsvCell(columnIndex = 4, columnName = "target-operation")
    private final String targetFunction;
    @CsvCell(columnIndex = 5, columnName = "calls")
    private final long calls;

    public NumberOfCallsEntry(final String sourceFile, final String sourceFunction, final String targetFile,
            final String targetFunction, final long calls) {
        this.sourceFile = sourceFile;
        this.sourceFunction = sourceFunction;
        this.targetFile = targetFile;
        this.targetFunction = targetFunction;
        this.calls = calls;
    }

    public String getSourceFile() {
        return this.sourceFile;
    }

    public String getSourceFunction() {
        return this.sourceFunction;
    }

    public String getTargetFile() {
        return this.targetFile;
    }

    public String getTargetFunction() {
        return this.targetFunction;
    }

    public long getCalls() {
        return this.calls;
    }

}
