package org.oceandsl.tools.esm.util;

import java.io.File;
import java.util.List;

public class FileContentsStageOutput {

    private final FileContents fileContents;
    private final List<File> files;

    public FileContentsStageOutput(final FileContents fileContents, final List<File> files) {
        this.fileContents = fileContents;
        this.files = files;
    }

    public FileContents getFileContents() {
        return this.fileContents;
    }

    public List<File> getFiles() {
        return this.files;
    }

}
