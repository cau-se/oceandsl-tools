package org.oceandsl.tools.restructuring.stages.exec.mapper;

import java.util.HashMap;

import kieker.model.analysismodel.assembly.AssemblyModel;

public abstract class AbstractComponentMapper {

	private ComponentsMapper compMapper;
	private String originalModelName;
	private String goalModelName;

	protected AssemblyModel orig;
	protected AssemblyModel goal;

	protected HashMap<String, String> operationToComponentO = new HashMap<String, String>();
	protected HashMap<String, String> operationToComponentG = new HashMap<String, String>();

	protected HashMap<String, HashMap<String, Integer>> traceModell = new HashMap<String, HashMap<String, Integer>>();
	protected HashMap<String, String> goalToOriginal = new HashMap<String, String>();
	protected HashMap<String, String> originallToGoal = new HashMap<String, String>();

	public AbstractComponentMapper(String originalModelName, String goalModelName) {
		this.originalModelName = originalModelName;
		this.goalModelName = goalModelName;
	}

	public String getGoalModelName() {
		return goalModelName;
	}

	public String getOriginalModelName() {
		return originalModelName;
	}

	public HashMap<String, String> getOperationToComponentO() {
		return operationToComponentO;
	}

	public void setOperationToComponentO(HashMap<String, String> operationToComponentO) {
		this.operationToComponentO = operationToComponentO;
	}

	public HashMap<String, String> getOperationToComponentG() {
		return operationToComponentG;
	}

	public void setOperationToComponentG(HashMap<String, String> operationToComponentG) {
		this.operationToComponentG = operationToComponentG;
	}

	public HashMap<String, HashMap<String, Integer>> getTraceModell() {
		return traceModell;
	}

	public void setTraceModell(HashMap<String, HashMap<String, Integer>> traceModell) {
		this.traceModell = traceModell;
	}

	public HashMap<String, String> getGoalToOriginal() {
		return goalToOriginal;
	}

	public void setGoalToOriginal(HashMap<String, String> goalToOriginal) {
		this.goalToOriginal = goalToOriginal;
	}

	public HashMap<String, String> getOriginalToGoal() {
		return originallToGoal;
	}

	public void setOriginallToGoal(HashMap<String, String> originallToGoal) {
		this.originallToGoal = originallToGoal;
	}

	public AssemblyModel getOriginal() {
		return this.orig;

	}

	public AssemblyModel getGoal() {
		return this.goal;

	}
}
