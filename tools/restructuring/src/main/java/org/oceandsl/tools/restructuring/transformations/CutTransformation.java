package org.oceandsl.tools.restructuring.transformations;

import java.util.Map.Entry;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.impl.AssemblyFactoryImpl;

public class CutTransformation extends AtomicTransformation {
    
	private String componentName;
	private String operationName;
	private AssemblyOperation operation;
	private AssemblyFactoryImpl fac = TransformationUtil.ASSEMBLY_MODEL_FACTORY;
	
	public CutTransformation(AssemblyModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
    
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	
	public AssemblyOperation getOperation() {
		return this.operation;
	}
	 
	public String getComponentName() {
		return this.componentName;
	}
	
	public String getOperationName() {
		return this.operationName;
	}
	
	@Override
	public void applyTransformation(AssemblyModel model) {
		 System.out.println("cutting" +this.componentName);
		 model.getComponents().get(this.componentName).getOperations().removeKey(this.operationName);
		 this.model = model;
		
	}

}
