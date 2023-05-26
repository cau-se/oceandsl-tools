package org.oceandsl.tools.fxca.stages.dataflow;

import lombok.Getter;

public class DataflowEntry {

    @Getter
    private final String sourceFileName;
    @Getter
    private final String sourceModuleName;
    @Getter
    private final String sourceOperationName;
    @Getter
    private final String targetFileName;
    @Getter
    private final String targetModuleName;
    @Getter
    private final String targetOperatioName;
    @Getter
    private final String direction;

    public DataflowEntry(final String sourceFileName, final String sourceModuleName, final String sourceOperationName,
            final String targetFileName, final String targetModuleName, final String targetOperatioName,
            final String direction) {
        this.sourceFileName = sourceFileName;
        this.sourceModuleName = sourceModuleName;
        this.sourceOperationName = sourceOperationName;
        this.targetFileName = targetFileName;
        this.targetModuleName = targetModuleName;
        this.targetOperatioName = targetOperatioName;
        this.direction = direction;
    }

}
