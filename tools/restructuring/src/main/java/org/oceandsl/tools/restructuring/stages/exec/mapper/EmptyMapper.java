package org.oceandsl.tools.restructuring.stages.exec.mapper;

import java.util.Map.Entry;
import java.util.Set;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;

public class EmptyMapper extends AbstractComponentMapper {

	public EmptyMapper(AssemblyModel orig, AssemblyModel goal, String originalModelName, String goalModelName) {
		super(originalModelName, goalModelName);
		this.orig = orig;
		this.goal = goal;
		// System.out.println(this.orig != null);
		// System.out.println(this.goal != null);
		// init mappings
		populateOperationTocomponentG();
		populateOperationToComponentO();
		// populateTraceModel();
		// computeOriginalComponentNames();

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
