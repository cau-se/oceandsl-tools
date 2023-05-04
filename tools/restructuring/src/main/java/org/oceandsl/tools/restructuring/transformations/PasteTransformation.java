package org.oceandsl.tools.restructuring.transformations;

import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;
import kieker.model.analysismodel.assembly.impl.AssemblyFactoryImpl;

public class PasteTransformation extends AtomicTransformation{

	private String componentName;
	private String operationName;
	private AssemblyOperation operation;
	AssemblyFactoryImpl fac = new AssemblyFactoryImpl();

	public PasteTransformation(AssemblyModel model) {
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
		model.getComponents().get(this.componentName).getOperations().put(this.operationName, fac.createAssemblyOperation());
		this.model = model;
		//System.out.println("x");
	}

}
