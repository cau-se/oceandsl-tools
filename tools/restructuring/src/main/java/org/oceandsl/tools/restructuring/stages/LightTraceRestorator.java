package org.oceandsl.tools.restructuring.stages;


import org.oceandsl.tools.restructuring.stages.exec.RestructureStepFinder;
import org.oceandsl.tools.restructuring.stages.exec.mapper.AbstractMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.Matcher;

import kieker.model.analysismodel.assembly.AssemblyModel;
import teetime.framework.AbstractProducerStage;

public class LightTraceRestorator{

	private AssemblyModel original;
	private AssemblyModel goal;
	
	
	public LightTraceRestorator(AssemblyModel original, AssemblyModel goal) {
		this.original = original;
		this.goal = goal;
	}

	public int getNumSteps(){

				AbstractMapper	 mapper = new Matcher(original,goal);
			
				RestructureStepFinder stepfinder = new RestructureStepFinder(mapper);
				stepfinder.findTransformation();
				return stepfinder.getNumStep();
				
			}
		


}
