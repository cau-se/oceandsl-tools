package org.oceandsl.tools.restructuring;

import org.oceandsl.tools.restructuring.stages.exec.RestructureStepFinder;
import org.oceandsl.tools.restructuring.stages.exec.mapper.AbstractComponentMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.KuhnMatcherMapper;

import kieker.model.analysismodel.assembly.AssemblyModel;

public class LightTraceRestorator {

	private AssemblyModel original;
	private AssemblyModel goal;

	public LightTraceRestorator(AssemblyModel original, AssemblyModel goal) {
		this.original = original;
		this.goal = goal;
	}

	public int getNumSteps() {
		// TODO find better names
		AbstractComponentMapper mapper = new KuhnMatcherMapper(original, goal, "original", "goal");

		RestructureStepFinder stepfinder = new RestructureStepFinder(mapper);
		stepfinder.findTransformation();

		return stepfinder.getNumberOfSteps();
	}

}
