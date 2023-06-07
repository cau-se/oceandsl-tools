package org.oceandsl.tools.maa.stages;

import org.csveed.annotations.CsvCell;

public class ProvidedInterfaceEntry {

    @CsvCell(columnIndex = 1, columnName = "component-type")
    private final String componentType;
    @CsvCell(columnIndex = 2, columnName = "provided-interface")
    private final String providedInterface;
    @CsvCell(columnIndex = 3, columnName = "operation")
    private final String operation;
    @CsvCell(columnIndex = 4, columnName = "caller-component-types")
    private final String callerComponentTypes;

    public ProvidedInterfaceEntry(final String componentType, final String providedInterface, final String operation,
            final String callerComponentTypes) {
        this.componentType = componentType;
        this.providedInterface = providedInterface;
        this.operation = operation;
        this.callerComponentTypes = callerComponentTypes;
    }

    public String getComponentType() {
        return this.componentType;
    }

    public String getProvidedInterface() {
        return this.providedInterface;
    }

    public String getOperation() {
        return this.operation;
    }

    public String getCallerComponentTypes() {
        return this.callerComponentTypes;
    }

}
