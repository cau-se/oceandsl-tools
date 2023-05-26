package org.oceandsl.tools.maa.stages;

import lombok.Getter;

public class ComponentStatistics {

    @Getter
    private final String componentName;
    @Getter
    private final int operations;
    @Getter
    private final long providedOperations;
    @Getter
    private final long requiredOperations;

    public ComponentStatistics(final String componentName, final int operations, final long providedOperations,
            final long requiredOperations) {
        this.componentName = componentName;
        this.operations = operations;
        this.providedOperations = providedOperations;
        this.requiredOperations = requiredOperations;
    }

}
