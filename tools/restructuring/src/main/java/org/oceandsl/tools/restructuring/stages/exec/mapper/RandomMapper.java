package org.oceandsl.tools.restructuring.stages.exec.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;

public class RandomMapper extends AbstractMapper {

	/**
	 * Cosntructor is used to
	 *
	 * @param orig
	 * @param goal
	 * @param compMapper
	 */
	public RandomMapper(AssemblyModel orig, AssemblyModel goal, String originalModelName, String goalModelName) {
		super(originalModelName, goalModelName);
		this.orig = orig;
		this.goal = goal;
		System.out.println(this.orig != null);
		System.out.println(this.goal != null);
		// init mappings
		populateOperationTocomponentG();
		populateOperationToComponentO();
		populateTraceModel();
		computeOriginalComponentNames();
	}

	@Override
	public HashMap<String, String> getOperationToComponentO() {
		return operationToComponentO;
	}

	@Override
	public void setOperationToComponentO(HashMap<String, String> operationToComponentO) {
		this.operationToComponentO = operationToComponentO;
	}

	@Override
	public HashMap<String, String> getOperationToComponentG() {
		return operationToComponentG;
	}

	@Override
	public void setOperationToComponentG(HashMap<String, String> operationToComponentG) {
		this.operationToComponentG = operationToComponentG;
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> getTraceModell() {
		return traceModell;
	}

	@Override
	public void setTraceModell(HashMap<String, HashMap<String, Integer>> traceModell) {
		this.traceModell = traceModell;
	}

	@Override
	public HashMap<String, String> getGoalToOriginal() {
		return goalToOriginal;
	}

	@Override
	public void setGoalToOriginal(HashMap<String, String> goalToOriginal) {
		this.goalToOriginal = goalToOriginal;
	}

	@Override
	public HashMap<String, String> getOriginallToGoal() {
		return originallToGoal;
	}

	@Override
	public void setOriginallToGoal(HashMap<String, String> originallToGoal) {
		this.originallToGoal = originallToGoal;
	}

	@Override
	public AssemblyModel getOrig() {
		return this.orig;

	}

	@Override
	public AssemblyModel getGoal() {
		return this.goal;

	}

	private void computeOriginalComponentNames() {
		Set<String> comps = this.goal.getComponents().keySet();
		List<String> compList = new ArrayList(comps);
		List<String> randComps = getRandomComponents(compList);

		int currentRand = 0;
		for (String origC : this.orig.getComponents().keySet()) {
			this.goalToOriginal.put(randComps.get(currentRand), origC);
			this.originallToGoal.put(origC, randComps.get(currentRand));
			currentRand++;
		}

	}

	private List<String> getRandomComponents(List<String> componentNames) {
		Random rand = new Random();
		List<String> result = new ArrayList();
		for (int i = 0; i < this.orig.getComponents().keySet().size(); i++) {
			int randomIndex = rand.nextInt(componentNames.size());
			result.add(componentNames.get(randomIndex));
			componentNames.remove(randomIndex);

		}
		return result;

	}

	private void populateTraceModel() {
		// For each component in the goal model...
		for (Entry<String, AssemblyComponent> entry : this.goal.getComponents().entrySet()) {

			AssemblyComponent comp = entry.getValue();
			String name = entry.getKey();

			// get operations of the current component
			final Set<String> ops = comp.getOperations().keySet();
			// here we store the components where operations actually originate from and
			// count how many operations of each
			// original component are in the "goal component"
			final HashMap<String, Integer> referencedComponents = new HashMap<String, Integer>();

			// For each operation of current component..
			for (String op : ops) {

				// String opName = op.getKey();
				// look up the name of the "original component" for that operation
				String originalComponent = this.operationToComponentO.get(op);

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
		for (Entry<String, AssemblyComponent> e : this.orig.getComponents().entrySet()) {
			Set<String> ops = e.getValue().getOperations().keySet();
			for (String s : ops) {
				this.operationToComponentO.put(s, e.getKey());
			}
		}
	}

	private void populateOperationTocomponentG() {
		for (Entry<String, AssemblyComponent> e : this.goal.getComponents().entrySet()) {
			Set<String> ops = e.getValue().getOperations().keySet();
			for (String s : ops) {
				this.operationToComponentG.put(s, e.getKey());
			}
		}
	}
}
