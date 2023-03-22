package org.oceandsl.tools.restructuring.stages.exec.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EMap;

import java.util.Set;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;

public class ComponentsMapper extends AbstractMapper{

	private ComponentsMapper compMapper;
	private AssemblyModel orig;
	private AssemblyModel goal;
	
	private  HashMap <String, String> operationToComponentO = new HashMap<String, String>();
	private  HashMap<String, String> operationToComponentG = new HashMap<String, String>();
	
	private  HashMap<String,HashMap<String, Integer>> traceModell = new HashMap<String, HashMap<String,Integer>>();
	private  HashMap<String,String> goalToOriginal = new HashMap<String,String>();
	private  HashMap<String,String> originallToGoal = new HashMap<String,String>();
	
	/**
	 * Cosntructor is used to
	 * @param orig
	 * @param goal
	 * @param compMapper
	 */
	public ComponentsMapper(AssemblyModel orig, AssemblyModel goal) {
		this.orig = orig;
		this.goal = goal;

		//init mappings
		populateOperationTocomponentG();
		populateOperationToComponentO();
		populateTraceModel();
		computeOriginalComponentNames();
		
	}
	
	
	public HashMap <String, String> getOperationToComponentO() {
		return operationToComponentO;
	}

	public void setOperationToComponentO(HashMap <String, String> operationToComponentO) {
		this.operationToComponentO = operationToComponentO;
	}
	

	public HashMap<String, String> getOperationToComponentG() {
		return operationToComponentG;
	}

	public void setOperationToComponentG(HashMap<String, String> operationToComponentG) {
		this.operationToComponentG = operationToComponentG;
	}

	public HashMap<String,HashMap<String, Integer>> getTraceModell() {
		return traceModell;
	}

	public void setTraceModell(HashMap<String,HashMap<String, Integer>> traceModell) {
		this.traceModell = traceModell;
	}

	public HashMap<String,String> getGoalToOriginal() {
		return goalToOriginal;
	}

	public void setGoalToOriginal(HashMap<String,String> goalToOriginal) {
		this.goalToOriginal = goalToOriginal;
	}

	public HashMap<String,String> getOriginallToGoal() {
		return originallToGoal;
	}

	public void setOriginallToGoal(HashMap<String,String> originallToGoal) {
		this.originallToGoal = originallToGoal;
	}
    
	public AssemblyModel getOrig() {
		return this.orig;
		
	}

	public AssemblyModel getGoal() {
		return this.goal;
		
	}
	private void  computeOriginalComponentNames() {
		Set<String> assignedComponentsG = new HashSet<String>();
		Set<String> assignedComponentsO = new HashSet<String>();
		for(Entry<String, HashMap<String,Integer>> goalComponent : this.traceModell.entrySet()) {
			ArrayList<Entry<String, Integer>> componentTraces = new ArrayList<>(goalComponent.getValue().entrySet());
			componentTraces.sort(Comparator.comparing(Entry::getValue));
			
			for(Entry<String, Integer> e : componentTraces) {
			     if(assignedComponentsO.contains(e.getKey())) {
			    	 continue;
			     }
			     assignedComponentsO.add(e.getKey());
			     assignedComponentsG.add(goalComponent.getKey());
			     this.goalToOriginal.put(goalComponent.getKey(), e.getKey());
			     this.originallToGoal.put(e.getKey(), goalComponent.getKey());
			     break;
			}
		}
		
	}
	

	
	private void populateTraceModel() {
		// For each component in the goal model...
				for(Entry<String, AssemblyComponent> entry : this.goal.getComponents().entrySet()) {
					
					AssemblyComponent comp = entry.getValue();
					String name = entry.getKey();
					
					// get operations of the current component
					final Set<String> ops = comp.getOperations().keySet();
					//here we store the components where operations actually originate from and count how many operations of each 
					// original component are in the "goal component"
					final HashMap<String, Integer> referencedComponents = new HashMap<String, Integer>();	
					
					// For each operation of current component..
					for(String op : ops) {
						
						
						//String opName = op.getKey();
						//look up the name of the "original component" for that operation
						String originalComponent = this.operationToComponentO.get(op);
						
						//Already found or not?
						if(referencedComponents.containsKey(originalComponent)) {
							referencedComponents.put(originalComponent, referencedComponents.get(originalComponent)+1);
						    
						}else {
							//first occurence
							referencedComponents.put(originalComponent,1);
						}
						
					}
					this.traceModell.put(name, referencedComponents);
					
				}
	}
	
	
	private void populateOperationToComponentO() {
		for(Entry<String, AssemblyComponent> e:this.orig.getComponents().entrySet()) {
			Set<String>ops = e.getValue().getOperations().keySet();
			for(String s : ops) {
				this.operationToComponentO.put(s, e.getKey());
			}
		}
	}
	
	private void populateOperationTocomponentG() {
		for(Entry<String, AssemblyComponent> e:this.goal.getComponents().entrySet()) {
			Set<String>ops = e.getValue().getOperations().keySet();
			for(String s : ops) {
				this.operationToComponentG.put(s, e.getKey());
			}
		}
	}
}
