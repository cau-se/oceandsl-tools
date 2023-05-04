package org.oceandsl.tools.restructuring.transformations;

import kieker.model.analysismodel.assembly.AssemblyModel;

public class FullTransformation extends CompositeTransformation {

	public FullTransformation(AssemblyModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyTransformation(AssemblyModel model) {
		for(AbstractTransformationStep step : this.steps) {
			step.applyTransformation(model);
		}
		this.model = model;

	}

}
