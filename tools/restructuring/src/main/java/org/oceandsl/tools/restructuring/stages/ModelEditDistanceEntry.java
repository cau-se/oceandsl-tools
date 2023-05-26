package org.oceandsl.tools.restructuring.stages;

import lombok.Getter;

public class ModelEditDistanceEntry {

    @Getter
    private final String originalModelName;
    @Getter
    private final String goalModelName;
    @Getter
    private final int numberOfSteps;

    public ModelEditDistanceEntry(final String originalModelName, final String goalModelName, final int numberOfSteps) {
        this.originalModelName = originalModelName;
        this.goalModelName = goalModelName;
        this.numberOfSteps = numberOfSteps;
    }

}
