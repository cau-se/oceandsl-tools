package org.oceandsl.tools.maa.stages;

import lombok.Getter;

public class ProvidedInterfaceEntry {

    @Getter
    private final String componentType;
    @Getter
    private final String providedInterface;
    @Getter
    private final String operation;
    @Getter
    private final String callerComponentTypes;

    public ProvidedInterfaceEntry(final String componentType, final String providedInterface, final String operation,
            final String callerComponentTypes) {
        this.componentType = componentType;
        this.providedInterface = providedInterface;
        this.operation = operation;
        this.callerComponentTypes = callerComponentTypes;
    }

}
