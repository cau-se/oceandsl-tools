package org.oceandsl.tools.restructuring.transformations;

import java.util.ArrayList;
import java.util.List;

import kieker.model.analysismodel.assembly.AssemblyModel;

public abstract class CompositeTransformation extends AbstractTransformationStep {
    
	protected List<AbstractTransformationStep> steps;
	
	public CompositeTransformation(AssemblyModel model) {
		super(model);
		this.steps = new ArrayList<AbstractTransformationStep>();
		// TODO Auto-generated constructor stub
	}
	public List<AbstractTransformationStep> getSteps(){
		return this.steps;
	}
	
	
   
	

}
