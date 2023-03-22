package org.oceandsl.tools.restructuring.util;

import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import org.oceandsl.tools.restructuring.transformations.CreateTransformation;
import org.oceandsl.tools.restructuring.transformations.CutTransformation;
import org.oceandsl.tools.restructuring.transformations.DeleteTransformation;
import org.oceandsl.tools.restructuring.transformations.MergeTransformation;
import org.oceandsl.tools.restructuring.transformations.MoveTransformation;
import org.oceandsl.tools.restructuring.transformations.PasteTransformation;
import org.oceandsl.tools.restructuring.transformations.SplitTransformation;

import java.util.Set;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;

public class TransformationFactory {

	public static CreateTransformation createCreateTransformation(String componentName, AssemblyModel model) {

		CreateTransformation result = new CreateTransformation(model);
		result.setComponentName(componentName); // For the new component in original

		return result;
	}

	public static DeleteTransformation createDeleteTransformation(String componentName, AssemblyModel model) {
		DeleteTransformation result = new DeleteTransformation(model);
		result.setComponentName(componentName);
		return result;
	}

	public static CutTransformation createCutTransformation(String componentName, String operationName,
			AssemblyModel model) {

		CutTransformation result = new CutTransformation(model);
		result.setComponentName(componentName); // For the new component in original
		result.setOperationName(operationName);

		return result;
	}

	public static PasteTransformation createPasteTransformation(String componentName, String operationName,
			AssemblyModel model) {

		PasteTransformation result = new PasteTransformation(model);
		result.setComponentName(componentName); // For the new component in original
		result.setOperationName(operationName);

		return result;
	}

	public static MoveTransformation createMoveTransformation(String fromComponent, String toComponent,
			String operationName, AssemblyModel model) {
		CutTransformation from = createCutTransformation(fromComponent, operationName, model);
		PasteTransformation to = createPasteTransformation(toComponent, operationName, model);

		MoveTransformation result = new MoveTransformation(model);
		result.add(from);
		result.add(to);

		return result;

	}

	public static SplitTransformation createSplitTransformation(String componentOld, String componentNew, String operationName, AssemblyModel model) {
	
	CreateTransformation newComponent = createCreateTransformation(componentNew, model);
	MoveTransformation to = createMoveTransformation(componentOld, componentNew, operationName, model);
	
	SplitTransformation result = new SplitTransformation(model);
	result.add(newComponent);
	result.add(to);
	return result;
	
	
	
}

	public static MergeTransformation createMergeTransformation(String componentOld, String componentNew, String operationName, AssemblyModel model) {
	
	DeleteTransformation newComponent = createDeleteTransformation(componentNew, model);
	MoveTransformation to = createMoveTransformation(componentOld, componentNew, operationName, model);
	
	MergeTransformation result = new MergeTransformation(model);
	result.add(newComponent);
	result.add(to);
	return result;
	
	
	
}
	public static boolean areSameComponents(AssemblyComponent a, AssemblyComponent b) {
		Set<String> opsA = a.getOperations().keySet();
		Set<String> opsB = b.getOperations().keySet();
		return new HashSet<>(opsA).equals(new HashSet<>(opsB));

	}
	
	public static boolean containsComponent(AssemblyModel model, AssemblyComponent b) {
		
		for(Entry<String, AssemblyComponent> e : model.getComponents().entrySet()) {
			if(areSameComponents(e.getValue(),b)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean areSameModels(AssemblyModel a , AssemblyModel b) {
		if(a.getComponents().size()!=b.getComponents().size()) {
			return false;
		}
		for(Entry<String, AssemblyComponent> e : b.getComponents().entrySet()) {
			if(!containsComponent(a, e.getValue())) {
				return false;
			}
		}
		
		
		return true;
	}
}
