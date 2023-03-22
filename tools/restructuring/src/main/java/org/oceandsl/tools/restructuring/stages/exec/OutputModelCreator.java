
package org.oceandsl.tools.restructuring.stages.exec;
import java.util.List;

import org.oceandsl.tools.restructuring.transformations.AbstractTransformationStep;
import org.oceandsl.tools.restructuring.transformations.CreateTransformation;
import org.oceandsl.tools.restructuring.transformations.CutTransformation;
import org.oceandsl.tools.restructuring.transformations.DeleteTransformation;
import org.oceandsl.tools.restructuring.transformations.MergeTransformation;
import org.oceandsl.tools.restructuring.transformations.MoveTransformation;
import org.oceandsl.tools.restructuring.transformations.PasteTransformation;
import org.oceandsl.tools.restructuring.transformations.SplitTransformation;

import kieker.model.analysismodel.assembly.AssemblyFactory;
import restructuremodel.ComponentsTransformation;
import restructuremodel.CreateComponent;
import restructuremodel.CutOperation;
import restructuremodel.DeleteComponent;
import restructuremodel.MergeComponent;
import restructuremodel.MoveOperation;
import restructuremodel.PasteOperation;
import restructuremodel.RestructuremodelFactory;
import restructuremodel.SplitComponent;


public class OutputModelCreator {

	private RestructureStepFinder stepFinder;
	private ComponentsTransformation outputModel;
	RestructuremodelFactory factory = RestructuremodelFactory.eINSTANCE;
	
	public OutputModelCreator(RestructureStepFinder stepFinder) {
		this.stepFinder = stepFinder;
		this.outputModel = factory.createComponentsTransformation();
	}
	
	public ComponentsTransformation getOutputModel() {
		return this.outputModel;
	}
	
	public void createOutputModel() {
		List<AbstractTransformationStep> transformations=stepFinder.getSteps();
		for(AbstractTransformationStep step : transformations) {
			if(step instanceof CreateTransformation) {
				//System.out.println("dadsadre"+outputModel.getTransformations()!=null);
				outputModel.getTransformations().add(createCreateComponent((CreateTransformation)step));
			}else if(step instanceof DeleteTransformation) {
				outputModel.getTransformations().add(createDeleteComponent((DeleteTransformation)step));
			}else if(step instanceof CutTransformation) {
				outputModel.getTransformations().add(createCutOperation((CutTransformation)step));
			}else if (step instanceof PasteTransformation){
				outputModel.getTransformations().add(createPasteOperation((PasteTransformation)step));
			}else if ((step instanceof MoveTransformation)) {
				outputModel.getTransformations().add(createMoveOperation((MoveTransformation)step));
			}else if (step instanceof SplitTransformation) {
				outputModel.getTransformations().add(createSplitComponent((SplitTransformation)step));
			}else if (step instanceof MergeTransformation) {
				outputModel.getTransformations().add(createMergeComponent((MergeTransformation)step));
			}
		}
	}
	
	
	public CreateComponent createCreateComponent(CreateTransformation transformation) {
		CreateComponent result = this.factory.createCreateComponent();
		result.setComponentName(transformation.getComponentName());	
		return result;
		
	}
	
	public DeleteComponent createDeleteComponent(DeleteTransformation transformation) {
		DeleteComponent result = this.factory.createDeleteComponent();
		result.setComponentName(transformation.getComponentName());	
		return result;
		
	}
	
	public CutOperation createCutOperation(CutTransformation transformation) {
		CutOperation result = this.factory.createCutOperation();
		result.setComponentName(transformation.getComponentName());	
		result.setOperationName(transformation.getOperationName());
		return result;
		
	}
	
	public PasteOperation createPasteOperation(PasteTransformation transformation) {
		PasteOperation result = this.factory.createPasteOperation();
		result.setComponentName(transformation.getComponentName());	
		result.setOperationName(transformation.getOperationName());
		return result;
		
	}
	
	public MoveOperation createMoveOperation(MoveTransformation transformation) {
		MoveOperation result = this.factory.createMoveOperation();
		result.setFrom(transformation.getCutTransformation().getComponentName());
		result.setTo(transformation.getPasteTransformation().getComponentName());
		result.setOperationName(transformation.getCutTransformation().getOperationName());
		
		CutOperation cut = createCutOperation(transformation.getCutTransformation());
		PasteOperation paste = createPasteOperation(transformation.getPasteTransformation());
		
		result.setCutOperation(cut);
		result.setPasteOperation(paste);
		
		return result;
	}
	
	public SplitComponent createSplitComponent(SplitTransformation transformation) {
		SplitComponent result = this.factory.createSplitComponent();
		result.setNewComponent(transformation.getCreateTransformation().getComponentName());
		result.setCreateComponent(createCreateComponent(transformation.getCreateTransformation()));
		MoveTransformation move = (MoveTransformation)transformation.getMoveTransformation().get(0); 
		result.setOldComponent(move.getCutTransformation().getComponentName());
		for(AbstractTransformationStep mv : transformation.getMoveTransformation()) {
			MoveTransformation tmp = (MoveTransformation)mv;
			result.getOperationsToMove().add(tmp.getCutTransformation().getOperationName());
			result.getMoveOperations().add(createMoveOperation(tmp));
		}
		
		return result;
		
	}
	
	public MergeComponent createMergeComponent(MergeTransformation transformation) {
		MergeComponent result = this.factory.createMergeComponent();
		result.setComponentName(transformation.getDeleteTransformation().getComponentName());
		result.setDeleteTransformation(createDeleteComponent(transformation.getDeleteTransformation()));
		MoveTransformation move = (MoveTransformation)transformation.getMoveTransformations().get(0); 
		result.setMergeGoalComponent(move.getPasteTransformation().getComponentName());
		for(AbstractTransformationStep mv : transformation.getMoveTransformations()) {
			MoveTransformation tmp = (MoveTransformation)mv;
			result.getOperations().add(tmp.getCutTransformation().getOperationName());
			result.getOperationToMove().add(createMoveOperation(tmp));
		}
		
		return result;
	}
	
}
