package org.oceandsl.tools.mvis.stages.metrics;

import lombok.Getter;

public class NumberOfCallsEntry {

    @Getter
    private final String sourceFile;
    @Getter
    private final String sourceFunction;
    @Getter
    private final String targetFile;
    @Getter
    private final String targetFunction;
    @Getter
    private final long calls;

    public NumberOfCallsEntry(final String sourceFile, final String sourceFunction, final String targetFile,
            final String targetFunction, final long calls) {
        this.sourceFile = sourceFile;
        this.sourceFunction = sourceFunction;
        this.targetFile = targetFile;
        this.targetFunction = targetFunction;
        this.calls = calls;
    }

}
