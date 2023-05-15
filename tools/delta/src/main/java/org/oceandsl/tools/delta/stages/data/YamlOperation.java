package org.oceandsl.tools.delta.stages.data;

import lombok.Getter;

public class YamlOperation {

    @Getter
    private final String name;

    @Getter
    private final String sourceComponentName;

    public YamlOperation(final String operationName, final String componentName) {
        this.name = operationName;
        this.sourceComponentName = componentName;
    }
}
