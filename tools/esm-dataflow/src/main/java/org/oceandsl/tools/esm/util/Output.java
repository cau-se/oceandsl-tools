package org.oceandsl.tools.esm.util;

import org.oceandsl.analysis.code.stages.data.StringValueHandler;
import org.oceandsl.analysis.code.stages.data.Table;

public class Output {

    private static final String DATAFLOW = "dataflow";
    private static final String FILE_CONTENT = "file-content";

    private final Table dataflow = new Table(Output.DATAFLOW, new StringValueHandler("source-file"),
            new StringValueHandler("source-module"), new StringValueHandler("source-operation"),
            new StringValueHandler("target-file"), new StringValueHandler("target-module"),
            new StringValueHandler("target-operation"), new StringValueHandler("direction"));
    private final Table fileContent = new Table(Output.FILE_CONTENT, new StringValueHandler("file"),
            new StringValueHandler("module"), new StringValueHandler("operation"));

    public Table getDataflow() {
        return this.dataflow;
    }

    public Table getFileContent() {
        return this.fileContent;
    }
}
