package org.oceandsl.tools.restructuring.transformations;

import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.impl.AssemblyFactoryImpl;

public class DeleteTransformation extends AtomicTransformation{
    
	private String componentName;
	private AssemblyFactoryImpl fac = TransformationUtil.ASSEMBLY_MODEL_FACTORY;
	
	public DeleteTransformation(AssemblyModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
   
	public void setComponentName(String componentName) {
    	this.componentName = componentName;
    }
	
	public String getComponentName() {
		return this.componentName;
	}
    @Override
	public void applyTransformation(AssemblyModel model) {
    	model.getComponents().removeKey(componentName);
		this.model = model;
	}

	
	
}
