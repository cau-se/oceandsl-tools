package org.oceandsl.analysis.code.stages.data;

import lombok.Getter;

public class NotFoundEntry {

    @Getter
    private final String fileName;
    @Getter
    private final String moduleName;
    @Getter
    private final String operation;
    @Getter
    private final String call;

    public NotFoundEntry(final String fileName, final String moduleName, final String operation, final String call) {
        this.fileName = fileName;
        this.moduleName = moduleName;
        this.operation = operation;
        this.call = call;
    }

}
