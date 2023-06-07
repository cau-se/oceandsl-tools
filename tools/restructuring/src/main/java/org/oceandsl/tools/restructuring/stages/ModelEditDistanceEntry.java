package org.oceandsl.tools.restructuring.stages;

import org.csveed.annotations.CsvCell;

public class ModelEditDistanceEntry {

    @CsvCell(columnIndex = 1, columnName = "original-model")
    private final String originalModelName;
    @CsvCell(columnIndex = 2, columnName = "goal-model")
    private final String goalModelName;
    @CsvCell(columnIndex = 3, columnName = "number-of-steps")
    private final int numberOfSteps;

    public ModelEditDistanceEntry(final String originalModelName, final String goalModelName, final int numberOfSteps) {
        this.originalModelName = originalModelName;
        this.goalModelName = goalModelName;
        this.numberOfSteps = numberOfSteps;
    }

    public String getOriginalModelName() {
        return this.originalModelName;
    }

    public String getGoalModelName() {
        return this.goalModelName;
    }

    public int getNumberOfSteps() {
        return this.numberOfSteps;
    }

}
