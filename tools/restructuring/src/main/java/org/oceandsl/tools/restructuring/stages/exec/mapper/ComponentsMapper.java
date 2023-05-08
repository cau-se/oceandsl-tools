package org.oceandsl.tools.restructuring.stages.exec.mapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;

/**
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public class ComponentsMapper extends AbstractComponentMapper {

    private HashMap<String, String> operationToComponentO = new HashMap<>();
    private HashMap<String, String> operationToComponentG = new HashMap<>();

    private HashMap<String, HashMap<String, Integer>> traceModell = new HashMap<>();
    private HashMap<String, String> goalToOriginal = new HashMap<>();
    private HashMap<String, String> originallToGoal = new HashMap<>();

    /**
     * Cosntructor is used to
     *
     * @param original
     * @param goal
     * @param compMapper
     */
    public ComponentsMapper(final AssemblyModel original, final AssemblyModel goal, final String originalModelName,
            final String goalModelName) {
        super(originalModelName, goalModelName);

        this.original = original;
        this.goal = goal;

        // init mappings
        this.populateOperationTocomponentG();
        this.populateOperationToComponentO();
        this.populateTraceModel();
        this.computeOriginalComponentNames();

    }

    @Override
    public HashMap<String, String> getOperationToComponentO() {
        return this.operationToComponentO;
    }

    @Override
    public void setOperationToComponentO(final HashMap<String, String> operationToComponentO) {
        this.operationToComponentO = operationToComponentO;
    }

    @Override
    public HashMap<String, String> getOperationToComponentG() {
        return this.operationToComponentG;
    }

    @Override
    public void setOperationToComponentG(final HashMap<String, String> operationToComponentG) {
        this.operationToComponentG = operationToComponentG;
    }

    @Override
    public HashMap<String, HashMap<String, Integer>> getTraceModell() {
        return this.traceModell;
    }

    @Override
    public void setTraceModell(final HashMap<String, HashMap<String, Integer>> traceModell) {
        this.traceModell = traceModell;
    }

    @Override
    public HashMap<String, String> getGoalToOriginal() {
        return this.goalToOriginal;
    }

    @Override
    public void setGoalToOriginal(final HashMap<String, String> goalToOriginal) {
        this.goalToOriginal = goalToOriginal;
    }

    @Override
    public HashMap<String, String> getOriginalToGoal() {
        return this.originallToGoal;
    }

    @Override
    public void setOriginalToGoal(final HashMap<String, String> originallToGoal) {
        this.originallToGoal = originallToGoal;
    }

    @Override
    public AssemblyModel getOriginal() {
        return this.original;

    }

    @Override
    public AssemblyModel getGoal() {
        return this.goal;

    }

    private void computeOriginalComponentNames() {
        final Set<String> assignedComponentsG = new HashSet<>();
        final Set<String> assignedComponentsO = new HashSet<>();
        for (final Entry<String, HashMap<String, Integer>> goalComponent : this.traceModell.entrySet()) {
            final ArrayList<Entry<String, Integer>> componentTraces = new ArrayList<>(
                    goalComponent.getValue().entrySet());
            componentTraces.sort(Comparator.comparing(Entry::getValue));

            for (final Entry<String, Integer> e : componentTraces) {
                if (assignedComponentsO.contains(e.getKey())) {
                    continue;
                }
                assignedComponentsO.add(e.getKey());
                assignedComponentsG.add(goalComponent.getKey());
                this.goalToOriginal.put(goalComponent.getKey(), e.getKey());
                this.originallToGoal.put(e.getKey(), goalComponent.getKey());
                break;
            }
        }

    }

    private void populateTraceModel() {
        // For each component in the goal model...
        for (final Entry<String, AssemblyComponent> entry : this.goal.getComponents().entrySet()) {

            final AssemblyComponent comp = entry.getValue();
            final String name = entry.getKey();

            // get operations of the current component
            final Set<String> ops = comp.getOperations().keySet();
            // here we store the components where operations actually originate from and
            // count how many operations of each
            // original component are in the "goal component"
            final HashMap<String, Integer> referencedComponents = new HashMap<>();

            // For each operation of current component..
            for (final String op : ops) {

                // String opName = op.getKey();
                // look up the name of the "original component" for that operation
                final String originalComponent = this.operationToComponentO.get(op);

                // Already found or not?
                if (referencedComponents.containsKey(originalComponent)) {
                    referencedComponents.put(originalComponent, referencedComponents.get(originalComponent) + 1);

                } else {
                    // first occurence
                    referencedComponents.put(originalComponent, 1);
                }

            }
            this.traceModell.put(name, referencedComponents);

        }
    }

    private void populateOperationToComponentO() {
        for (final Entry<String, AssemblyComponent> e : this.original.getComponents().entrySet()) {
            final Set<String> ops = e.getValue().getOperations().keySet();
            for (final String s : ops) {
                this.operationToComponentO.put(s, e.getKey());
            }
        }
    }

    private void populateOperationTocomponentG() {
        for (final Entry<String, AssemblyComponent> e : this.goal.getComponents().entrySet()) {
            final Set<String> ops = e.getValue().getOperations().keySet();
            for (final String s : ops) {
                this.operationToComponentG.put(s, e.getKey());
            }
        }
    }
}
