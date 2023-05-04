package org.oceandsl.tools.restructuring.transformations;

import kieker.model.analysismodel.assembly.AssemblyModel;

public abstract class AbstractTransformationStep {
	protected AssemblyModel model;

	public AbstractTransformationStep(AssemblyModel model) {
		this.model = model;
	}
	public AssemblyModel getModel() {
		return this.model;
	}

	public abstract void applyTransformation(AssemblyModel model);
}
