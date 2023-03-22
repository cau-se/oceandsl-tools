package org.oceandsl.tools.restructuring.transformations;

import java.util.ArrayList;
import java.util.List;

import kieker.model.analysismodel.assembly.AssemblyModel;


public class SplitTransformation extends CompositeTransformation {

	
	
	public SplitTransformation(AssemblyModel model) {
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
		this.model= model;
	}
	
	public void add(AbstractTransformationStep step) {
		this.steps.add(step);
	}
	
	public CreateTransformation getCreateTransformation() {
		return (CreateTransformation)this.steps.get(0);
	}
	
	public List<AbstractTransformationStep> getMoveTransformation(){
		  List<AbstractTransformationStep> result = new ArrayList<AbstractTransformationStep>();
		  result.addAll(this.steps);
		  result.remove(0);
		  return  result;
		  
	}

}
