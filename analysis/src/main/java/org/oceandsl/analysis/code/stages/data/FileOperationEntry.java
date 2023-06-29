package org.oceandsl.analysis.code.stages.data;

import org.csveed.annotations.CsvCell;

public class FileOperationEntry {

    @CsvCell(columnIndex = 1, columnName = "path")
    private String path;
    @CsvCell(columnIndex = 2, columnName = "operation")
    private String name;

    public FileOperationEntry() {
        // default constructor required by csv library
    }

    public FileOperationEntry(final String path, final String name) {
        this.path = path;
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
