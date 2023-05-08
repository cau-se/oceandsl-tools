package org.oceandsl.tools.restructuring.stages.exec.mapper;

import java.util.HashMap;

import kieker.model.analysismodel.assembly.AssemblyModel;

/**
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public abstract class AbstractComponentMapper {

    private final String originalModelName;
    private final String goalModelName;

    protected AssemblyModel original;
    protected AssemblyModel goal;

    protected HashMap<String, String> operationToComponentO = new HashMap<>();
    protected HashMap<String, String> operationToComponentG = new HashMap<>();

    protected HashMap<String, HashMap<String, Integer>> traceModell = new HashMap<>();
    protected HashMap<String, String> goalToOriginal = new HashMap<>();
    protected HashMap<String, String> originallToGoal = new HashMap<>();

    public AbstractComponentMapper(final String originalModelName, final String goalModelName) {
        this.originalModelName = originalModelName;
        this.goalModelName = goalModelName;
    }

    public String getGoalModelName() {
        return this.goalModelName;
    }

    public String getOriginalModelName() {
        return this.originalModelName;
    }

    public HashMap<String, String> getOperationToComponentO() {
        return this.operationToComponentO;
    }

    public void setOperationToComponentO(final HashMap<String, String> operationToComponentO) {
        this.operationToComponentO = operationToComponentO;
    }

    public HashMap<String, String> getOperationToComponentG() {
        return this.operationToComponentG;
    }

    public void setOperationToComponentG(final HashMap<String, String> operationToComponentG) {
        this.operationToComponentG = operationToComponentG;
    }

    public HashMap<String, HashMap<String, Integer>> getTraceModell() {
        return this.traceModell;
    }

    public void setTraceModell(final HashMap<String, HashMap<String, Integer>> traceModell) {
        this.traceModell = traceModell;
    }

    public HashMap<String, String> getGoalToOriginal() {
        return this.goalToOriginal;
    }

    public void setGoalToOriginal(final HashMap<String, String> goalToOriginal) {
        this.goalToOriginal = goalToOriginal;
    }

    public HashMap<String, String> getOriginalToGoal() {
        return this.originallToGoal;
    }

    public void setOriginalToGoal(final HashMap<String, String> originallToGoal) {
        this.originallToGoal = originallToGoal;
    }

    public AssemblyModel getOriginal() {
        return this.original;

    }

    public AssemblyModel getGoal() {
        return this.goal;

    }
}
