package org.oceandsl.analysis.code.stages.data;

import org.csveed.annotations.CsvCell;

public class NotFoundEntry {

    @CsvCell(columnIndex = 1, columnName = "file-path")
    private String fileName;
    @CsvCell(columnIndex = 2, columnName = "module-name")
    private String moduleName;
    @CsvCell(columnIndex = 3, columnName = "operation")
    private String operation;
    @CsvCell(columnIndex = 4, columnName = "call")
    private String call;

    public NotFoundEntry() {
        // default constructor required by csv library
    }

    public NotFoundEntry(final String fileName, final String moduleName, final String operation, final String call) {
        this.fileName = fileName;
        this.moduleName = moduleName;
        this.operation = operation;
        this.call = call;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(final String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(final String operation) {
        this.operation = operation;
    }

    public String getCall() {
        return this.call;
    }

    public void setCall(final String call) {
        this.call = call;
    }
}
