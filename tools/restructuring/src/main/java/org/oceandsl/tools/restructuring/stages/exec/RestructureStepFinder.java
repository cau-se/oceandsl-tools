package org.oceandsl.tools.restructuring.stages.exec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.oceandsl.tools.restructuring.stages.exec.mapper.AbstractMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.ComponentsMapper;
import org.oceandsl.tools.restructuring.transformations.AbstractTransformationStep;
import org.oceandsl.tools.restructuring.transformations.CreateTransformation;
import org.oceandsl.tools.restructuring.transformations.CutTransformation;
import org.oceandsl.tools.restructuring.transformations.DeleteTransformation;
import org.oceandsl.tools.restructuring.transformations.MergeTransformation;
import org.oceandsl.tools.restructuring.transformations.MoveTransformation;
import org.oceandsl.tools.restructuring.transformations.PasteTransformation;
import org.oceandsl.tools.restructuring.transformations.SplitTransformation;
import org.oceandsl.tools.restructuring.util.TransformationFactory;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;

public class RestructureStepFinder {

	private AbstractMapper compMapper;
	private AssemblyModel orig;
	private AssemblyModel goal;
	private int numSteps =  0;

	private List<AbstractTransformationStep> transformations = new ArrayList<AbstractTransformationStep>();

	/**
	 * Cosntructor is used to
	 * 
	 * @param orig
	 * @param goal
	 * @param compMapper
	 */
	public RestructureStepFinder(AssemblyModel orig, AssemblyModel goal) {
		this.orig = orig;
		this.goal = goal;
		this.compMapper = new ComponentsMapper(this.orig, this.goal);
	}

	public RestructureStepFinder(AssemblyModel orig, AssemblyModel goal, ComponentsMapper compMapper) {
		this.compMapper = compMapper;
		this.orig = orig;
		this.goal = goal;
	}

	public RestructureStepFinder(AbstractMapper compMapper) {
		this.compMapper = compMapper;
		this.orig = compMapper.getOrig();
		this.goal = compMapper.getGoal();
	}

	public List<AbstractTransformationStep> getSteps() {
		return this.transformations;
	}

	public AssemblyModel getOrig() {
		return this.orig;
	}
	
	public AssemblyModel getGoal() {
		return this.goal;
	}
	public int getNumStep() {
		return this.numSteps;
	}
	public void findTransformation() {
		// goalComponents.
		//System.out.println("Num of gto in step finder:"+this.compMapper.getGoalToOriginal().size());
		//System.out.println("Num of tgo in stepfinder:"+this.compMapper.getOriginallToGoal().size());
		EMap<String, AssemblyComponent> originalComponents = (EcoreEMap<String, AssemblyComponent>) this.orig
				.getComponents();
		while(!TransformationFactory.areSameModels(goal, orig)) {
		for (int i = 0; i < originalComponents.size(); i++) {

			Entry<String, AssemblyComponent> entry = originalComponents.get(i);
			String nameOfOriginalC = entry.getKey();
			String nameOfGoalC = this.compMapper.getOriginallToGoal().get(nameOfOriginalC);

			// We could find a mapping, yeah!
			if (nameOfGoalC != null) {
				//System.out.println("Get nameOfGoalC "+ nameOfGoalC);
				AssemblyComponent goalAssemblyComponentObject = this.goal.getComponents().get(nameOfGoalC);
				EMap<String, AssemblyOperation> operationInGoalcomponent = goalAssemblyComponentObject.getOperations();

				// If the original and corresponding goal component equal, nothing to do
				// if (entry.getValue().equals(goalAssemblyComponentObject)) // the mapping
				// matches look for next
				if (TransformationFactory.areSameComponents(entry.getValue(), goalAssemblyComponentObject))
					continue;

				// Operations that are in original component but not in goal component
				List<String> out = getOperationsToMove(entry.getValue(), goalAssemblyComponentObject);
				// Operation that are in goal component but not in the original component
				List<String> in = getOperationToAdd(entry.getValue(), goalAssemblyComponentObject);
				transformModel(out, in, entry.getKey(), this.goal);

			} else {
				// Mapping could not be found
				nonMappedComponentTransformation(entry.getValue(), this.orig, this.goal);

			}

		}
		}
	}
	// }

	private void nonMappedComponentTransformation(AssemblyComponent component, AssemblyModel original2,
			AssemblyModel goal2) {

		for (int i = 0; i < component.getOperations().size(); i++) {
			Entry<String, AssemblyOperation> operation = component.getOperations().get(i);

			String operationName = operation.getKey();
			// Where should the operation lie after restructuring?
			String goalComponent = this.compMapper.getOperationToComponentG().get(operationName);

			// Where does it lie currently?
//			String originalComponent = operation.getValue().getComponent().getSignature();
			
			//MP 2023
			String originalComponent = this.compMapper.getOperationToComponentO().get(operation.getKey());
			// check if the goal location of the current operation is mapped to some
			// component in original?
			//
			if (!this.compMapper.getGoalToOriginal().containsKey(goalComponent)) {
				// No mapping exist (This case is rare. Only we compMapper failed!)
				// Thus, we can safely pair the original and goal components
				// Proceed after that normally
				this.compMapper.getGoalToOriginal().put(goalComponent,
						this.compMapper.getOperationToComponentO().get(operationName));
				this.compMapper.getOriginallToGoal().put(this.compMapper.getOperationToComponentO().get(operationName),
						goalComponent);

				// Collect normally which operation must be moved away and which not
				AssemblyComponent before = this.orig.getComponents()
						.get(this.compMapper.getOperationToComponentO().get(operationName));
				AssemblyComponent after = this.goal.getComponents().get(goalComponent);
				List<String> out = getOperationsToMove(before, after);
				List<String> in = getOperationToAdd(before, after);

				// transform normally
				transformModel(out, in, this.compMapper.getOperationToComponentO().get(operationName), this.goal);

				// Since we sorted all operations, there is nothing else to do
				break;
			} else {
				// This case means we have a goal component which is mapped to some original
				// component.
				// We move the operation over there!
				// Where should we move?
				String to = this.compMapper.getGoalToOriginal().get(goalComponent);

				CutTransformation cut = new CutTransformation(null);
				cut.setComponentName(originalComponent);
				cut.setOperationName(operationName);
				this.numSteps++;
				//this.transformations.add(cut);
				//cut.applyTransformation(this.orig);

				PasteTransformation paste = new PasteTransformation(null);
				paste.setComponentName(to);
				paste.setOperationName(operationName);
				this.numSteps++;
				//this.transformations.add(paste);
				//paste.applyTransformation(this.orig);
				
				MoveTransformation move = new MoveTransformation(null);
				move.getSteps().add(cut);
				move.getSteps().add(paste);
				move.applyTransformation(this.orig);
				this.transformations.add(move);

				if (this.orig.getComponents().get(originalComponent).getOperations().size() == 0) {
					DeleteTransformation delete = new DeleteTransformation(null);
					delete.setComponentName(originalComponent);
					delete.applyTransformation(this.orig);
					this.numSteps++;
					//this.transformations.add(delete);
					
					MergeTransformation merge = new MergeTransformation(null);
					merge.add(move);
					merge.add(delete);
					this.transformations.remove(this.transformations.size()-1);
					this.transformations.add(delete);
					
				}

			}

		}
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param out   operations that are not contained in the goal model and must be
	 *              removed from original
	 * @param in    operation that are contained in the goal model and must be added
	 *              to original
	 * @param origC nameOfOriginalComponent
	 * @param goal  goal model
	 */
	private void transformModel(List<String> out, List<String> in, String origC, AssemblyModel goal) {

		// move out
		for (String op : out) {
			// get name of component in goal assembly where the operation lies
			String compName = this.compMapper.getOperationToComponentG().get(op);
			// check if goal component is mapped to some component in the original
			boolean isMapped = this.compMapper.getGoalToOriginal().containsKey(compName);

			if (isMapped) { // a correspondence exists
				// get the mapped component and move the operation over there
				String originalComponentFromGoal = this.compMapper.getGoalToOriginal().get(compName);

				// Cut operation from original Component
				CutTransformation cut = new CutTransformation(null);
				cut.setComponentName(origC);
				cut.setOperationName(op);
				this.numSteps++;
				//this.transformations.add(cut);
				//cut.applyTransformation(this.orig);

				// Paste into another component
				PasteTransformation paste = new PasteTransformation(null);
				paste.setComponentName(originalComponentFromGoal);
				paste.setOperationName(op);
				this.numSteps++;
				//paste.applyTransformation(this.orig);
				//this.transformations.add(paste);
				
				MoveTransformation move = new MoveTransformation(null);
				move.getSteps().add(cut);
				move.getSteps().add(paste);
				move.applyTransformation(this.orig);
				this.transformations.add(move);

			} else { // no mapping exists. We must split!

				CreateTransformation create = new CreateTransformation(null);
				create.setComponentName(compName); // For the new component in original
				create.applyTransformation(this.orig);
				this.numSteps++;
				// we use the name as it is specified in goal model

				//this.transformations.add(create);
				//create.applyTransformation(this.orig);
				this.compMapper.getOriginallToGoal().put(compName, compName);
				this.compMapper.getGoalToOriginal().put(compName, compName);

				// TODO update mapping

				CutTransformation cut = new CutTransformation(null);
				cut.setComponentName(origC);
				cut.setOperationName(op);
				this.numSteps++;
			//	this.transformations.add(cut);
			//	cut.applyTransformation(this.orig);

				PasteTransformation paste = new PasteTransformation(null);
				paste.setComponentName(compName);
				paste.setOperationName(op);
				this.numSteps++;
			//	this.transformations.add(paste);
			//	paste.applyTransformation(this.orig);
				
				MoveTransformation move = new MoveTransformation(null);
				move.getSteps().add(cut);
				move.getSteps().add(paste);
			//	move.applyTransformation(this.orig);
				
				SplitTransformation split = new SplitTransformation(null);
				split.add(create);
				split.add(move);
				split.applyTransformation(this.orig);
				this.transformations.add(split);
				
			}
		}
		// move in
		// a simpler case
		for (String op : in) {
			// Where lies the operation currently that must be moved to the component?
			String compName = this.compMapper.getOperationToComponentO().get(op);

			CutTransformation cut = new CutTransformation(null);
			cut.setComponentName(compName);
			cut.setOperationName(op);
			this.numSteps++;
			//this.transformations.add(cut);
			//cut.applyTransformation(this.orig);

			PasteTransformation paste = new PasteTransformation(null);
			paste.setOperationName(op);
			paste.setComponentName(origC);
			this.numSteps++;
			//this.transformations.add(paste);
			//paste.applyTransformation(this.orig);
			
			MoveTransformation move = new MoveTransformation(null);
			move.getSteps().add(cut);
			move.getSteps().add(paste);
			move.applyTransformation(this.orig);
			this.transformations.add(move);
			// If after moving the operation its old componet becomes empty,
			// and this component is not mapped to some component, we can assume,
			// that we must merge. Thus delete it
			if (this.orig.getComponents().get(compName).getOperations().size() == 0
					&& !this.compMapper.getOriginallToGoal().containsKey(compName)) {
				DeleteTransformation delete = new DeleteTransformation(null);
				delete.setComponentName(compName);
				delete.applyTransformation(this.orig);
				this.numSteps++;
				this.transformations.add(delete);
				
				MergeTransformation merge = new MergeTransformation(null);
				merge.add(move);
				merge.add(delete);
				this.transformations.remove(this.transformations.size()-1);
				this.transformations.add(merge);
			}

		}
	}

	private List<String> getOperationToAdd(AssemblyComponent orig, AssemblyComponent goal) {

		EMap<String, AssemblyOperation> origOps = orig.getOperations();
		EMap<String, AssemblyOperation> goalOps = goal.getOperations();
		List<String> result = new ArrayList<String>();
		for (Entry<String, AssemblyOperation> op : goalOps.entrySet()) {
			if (!origOps.containsKey(op.getKey())) {
				result.add(op.getKey());
			}
		}
		return result;

	}

	private List<String> getOperationsToMove(AssemblyComponent orig, AssemblyComponent goal) {
		// TODO Auto-generated method stub
		EMap<String, AssemblyOperation> origOps = orig.getOperations();
		EMap<String, AssemblyOperation> goalOps = goal.getOperations();
		List<String> result = new ArrayList<String>();
		for (Entry<String, AssemblyOperation> op : origOps.entrySet()) {
			if (!goalOps.containsKey(op.getKey())) {
				result.add(op.getKey());
			}
		}
		return result;
	}

}
