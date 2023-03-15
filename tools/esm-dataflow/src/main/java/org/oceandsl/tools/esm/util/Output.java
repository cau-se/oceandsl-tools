package org.oceandsl.tools.esm.util;

import java.util.List;

public class Output {

    private List<String> dataflow;
    private List<String> fileContent;

    public List<String> getDataflow() {
        return this.dataflow;
    }

    public void setDataflow(final List<String> dataflow) {
        this.dataflow = dataflow;
    }

    public List<String> getFileContent() {
        return this.fileContent;
    }

    public void setFileContent(final List<String> fileContent) {
        this.fileContent = fileContent;
    }

}
