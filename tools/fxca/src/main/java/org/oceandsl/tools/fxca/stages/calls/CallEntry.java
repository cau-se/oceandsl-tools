package org.oceandsl.tools.fxca.stages.calls;

import lombok.Getter;

public class CallEntry {

    @Getter
    private final String sourcePath;
    @Getter
    private final String sourceModule;
    @Getter
    private final String sourceOperation;
    @Getter
    private final String targetPath;
    @Getter
    private final String targetModule;
    @Getter
    private final String targetOperation;

    public CallEntry(final String sourcePath, final String sourceModule, final String sourceOperation,
            final String targetPath, final String targetModule, final String targetOperation) {
        this.sourcePath = sourcePath;
        this.sourceModule = sourceModule;
        this.sourceOperation = sourceOperation;
        this.targetPath = targetPath;
        this.targetModule = targetModule;
        this.targetOperation = targetOperation;
    }

}
