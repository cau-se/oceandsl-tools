package org.oceandsl.tools.restructuring.util;

import java.util.Map.Entry;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyFactory;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;

public class RestructurerTools {

	public static AssemblyModel cloneModel(AssemblyModel model) {
		AssemblyFactory factory = AssemblyFactory.eINSTANCE;
		AssemblyModel result = factory.createAssemblyModel();

		for (Entry<String, AssemblyComponent> e : model.getComponents().entrySet()) {
			AssemblyComponent comp = factory.createAssemblyComponent();
			result.getComponents().put(e.getKey(), comp);
			for (Entry<String, AssemblyOperation> op : e.getValue().getOperations().entrySet()) {
				AssemblyOperation o = factory.createAssemblyOperation();
				result.getComponents().get(e.getKey()).getOperations().put(op.getKey(), o);
			}
		}
		if (!TransformationFactory.areSameModels(model, result))
			throw new Error("Models were not clonned succesfully!");

		return result;
	}

	public static AssemblyModel alterComponentNames(AssemblyModel model) {
		String prefix = "_";
		AssemblyFactory factory = AssemblyFactory.eINSTANCE;
		AssemblyModel result = factory.createAssemblyModel();

		for (Entry<String, AssemblyComponent> e : model.getComponents().entrySet()) {
			AssemblyComponent comp = factory.createAssemblyComponent();
			result.getComponents().put(prefix + e.getKey(), comp);
			for (Entry<String, AssemblyOperation> op : e.getValue().getOperations().entrySet()) {
				AssemblyOperation o = factory.createAssemblyOperation();
				result.getComponents().get(e.getKey()).getOperations().put(op.getKey(), o);
			}
		}
		if (!TransformationFactory.areSameModels(model, result))
			throw new Error("Models were not clonned succesfully!");

		return result;
	}
}
