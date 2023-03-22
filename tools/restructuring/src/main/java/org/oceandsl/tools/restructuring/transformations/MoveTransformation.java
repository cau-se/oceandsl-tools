package org.oceandsl.tools.restructuring.transformations;

import kieker.model.analysismodel.assembly.AssemblyModel;

public class MoveTransformation extends CompositeTransformation{

	public MoveTransformation(AssemblyModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void applyTransformation(AssemblyModel model) {

			//TODO CHEC IF THE LIST FORMAT IS APPROPRIETE
			//1ST OPERATION Split other operation move
			for(AbstractTransformationStep t:this.steps) {
				t.applyTransformation(model);
			}
			this.model = model;
	}
	
	public void add(AbstractTransformationStep transformation) {
		this.steps.add(transformation);
	}
	
	public CutTransformation getCutTransformation() {
		return (CutTransformation)this.steps.get(0);
	}
	
	public PasteTransformation getPasteTransformation() {
		return (PasteTransformation)this.steps.get(1);
	}
}
