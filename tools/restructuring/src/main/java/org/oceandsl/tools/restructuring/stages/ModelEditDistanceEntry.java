package org.oceandsl.tools.restructuring.stages;

import org.csveed.annotations.CsvCell;

public class ModelEditDistanceEntry {

    @CsvCell(columnIndex = 1, columnName = "original-model")
    private String originalModelName;
    @CsvCell(columnIndex = 2, columnName = "goal-model")
    private String goalModelName;
    @CsvCell(columnIndex = 3, columnName = "number-of-steps")
    private int numberOfSteps;

    public ModelEditDistanceEntry(final String originalModelName, final String goalModelName, final int numberOfSteps) {
        this.originalModelName = originalModelName;
        this.goalModelName = goalModelName;
        this.numberOfSteps = numberOfSteps;
    }

    public String getOriginalModelName() {
        return this.originalModelName;
    }

    public void setOriginalModelName(final String originalModelName) {
        this.originalModelName = originalModelName;
    }

    public String getGoalModelName() {
        return this.goalModelName;
    }

    public void setGoalModelName(final String goalModelName) {
        this.goalModelName = goalModelName;
    }

    public int getNumberOfSteps() {
        return this.numberOfSteps;
    }

    public void setNumberOfSteps(final int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

}
