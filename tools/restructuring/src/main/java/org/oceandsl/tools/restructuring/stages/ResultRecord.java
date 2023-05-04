package org.oceandsl.tools.restructuring.stages;

/**
 *
 * @author Reiner Jung
 * @since 1.3.0
 */
public class ResultRecord {
	String originalModelName;
	String goalModelName;
	int numberOfSteps;

	public ResultRecord(String originalModelName, String goalModelName, int numberOfSteps) {
		this.originalModelName = originalModelName;
		this.goalModelName = goalModelName;
		this.numberOfSteps = numberOfSteps;
	}

	public String getOriginalModelName() {
		return originalModelName;
	}

	public String getGoalModelName() {
		return goalModelName;
	}

	public int getNumberOfSteps() {
		return numberOfSteps;
	}

}
