package org.oceandsl.analysis.code.stages.data;

import lombok.Getter;

public class FileOperationEntry {

    @Getter
    private final String path;
    @Getter
    private final String name;

    public FileOperationEntry(final String path, final String name) {
        this.path = path;
        this.name = name;
    }

}
