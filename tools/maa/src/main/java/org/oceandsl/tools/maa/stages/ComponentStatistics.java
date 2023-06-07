package org.oceandsl.tools.maa.stages;

import org.csveed.annotations.CsvCell;

public class ComponentStatistics {

    @CsvCell(columnIndex = 1, columnName = "component")
    private final String componentName;
    @CsvCell(columnIndex = 2, columnName = "operations")
    private final int operations;
    @CsvCell(columnIndex = 3, columnName = "provided-operations")
    private final long providedOperations;
    @CsvCell(columnIndex = 4, columnName = "requires-operations")
    private final long requiredOperations;

    public ComponentStatistics(final String componentName, final int operations, final long providedOperations,
            final long requiredOperations) {
        this.componentName = componentName;
        this.operations = operations;
        this.providedOperations = providedOperations;
        this.requiredOperations = requiredOperations;
    }

}
