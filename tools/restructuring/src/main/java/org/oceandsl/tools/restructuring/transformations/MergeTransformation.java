package org.oceandsl.tools.restructuring.transformations;

import java.util.ArrayList;
import java.util.List;

import kieker.model.analysismodel.assembly.AssemblyModel;

public class MergeTransformation extends CompositeTransformation {

	public MergeTransformation(AssemblyModel model) {
		super(model);
	//	this.steps.add(new DeleteTransformation(model));
		this.steps.addAll(steps);
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
		
	public void add(AbstractTransformationStep step) {
		this.steps.add(step);
	}
	
	public DeleteTransformation getDeleteTransformation() {
		return (DeleteTransformation)this.steps.get(this.steps.size()-1);
	}
	
	public List<AbstractTransformationStep> getMoveTransformations() {
		  List<AbstractTransformationStep> result = new ArrayList<AbstractTransformationStep>();
		  result.addAll(this.steps);
		  result.remove(this.steps.size()-1);	  		  
		  return  result;
	}

}

