package org.oceandsl.analysis.code.stages.data;

import org.csveed.annotations.CsvCell;

public class GlobalDataEntry {

    @CsvCell(columnIndex = 1, columnName = "name")
    private String name;

    @CsvCell(columnIndex = 2, columnName = "files")
    private String files;

    @CsvCell(columnIndex = 3, columnName = "modules")
    private String modules;

    @CsvCell(columnIndex = 4, columnName = "variables")
    private String variables;

    public GlobalDataEntry() {
        // default constructor required by csv library
    }

    public GlobalDataEntry(final String name, final String files, final String modules, final String variables) {
        this.name = name;
        this.files = files;
        this.modules = modules;
        this.variables = variables;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getFiles() {
        return this.files;
    }

    public void setFiles(final String files) {
        this.files = files;
    }

    public String getModules() {
        return this.modules;
    }

    public void setModules(final String modules) {
        this.modules = modules;
    }

    public String getVariables() {
        return this.variables;
    }

    public void setVariables(final String variables) {
        this.variables = variables;
    }

}
