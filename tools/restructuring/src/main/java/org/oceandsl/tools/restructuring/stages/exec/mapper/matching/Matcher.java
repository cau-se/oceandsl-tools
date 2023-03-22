package org.oceandsl.tools.restructuring.stages.exec.mapper.matching;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.oceandsl.tools.restructuring.stages.exec.mapper.AbstractMapper;
import org.oceandsl.tools.restructuring.stages.exec.mapper.ComponentsMapper;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm.Matching;
import org.jgrapht.alg.matching.KuhnMunkresMinimalWeightBipartitePerfectMatching;
import org.jgrapht.graph.*;
public class Matcher extends AbstractMapper{
	private ComponentsMapper compMapper;
	private AssemblyModel orig;
	private AssemblyModel goal;
	
	private SimpleWeightedGraph <String, DefaultEdge> graph;
	private Set <String >s = orig.getComponents().keySet();
	private Set<String> t = goal.getComponents().keySet();
	private Matching <String, DefaultEdge>matching;
	
	private  HashMap <String, String> operationToComponentO = new HashMap<String, String>();
	private  HashMap<String, String> operationToComponentG = new HashMap<String, String>();
	
	private  HashMap<String,HashMap<String, Integer>> traceModell = new HashMap<String, HashMap<String,Integer>>();
	private  HashMap<String,String> goalToOriginal = new HashMap<String,String>();
	private  HashMap<String,String> originallToGoal = new HashMap<String,String>();
	
	public Matcher(AssemblyModel orig, AssemblyModel goal) {
		this.orig = orig;
		this.goal = goal;
		this.graph = new SimpleWeightedGraph<String, DefaultEdge>(DefaultEdge.class);
		//init mappings
		populateOperationTocomponentG();
		populateOperationToComponentO();
		
		//create graphp
		
		for(Entry<String, AssemblyComponent> c: this.orig.getComponents().entrySet()) { //each component is a vertex
			
			String origVertex = c.getKey();
			this.graph.addVertex(this.operationToComponentG.get(origVertex)); //create vertex from original component
			for(Entry<String, AssemblyOperation> ops: c.getValue().getOperations().entrySet()) { // iterate through  operations in original components
				
				String goalVertex = this.operationToComponentG.get(ops.getKey()); //get the goal component containing the current operation
				
				if(!this.graph.containsVertex(goalVertex)) { // vertex was not added yet , thus simply create and edge
					this.graph.addVertex(goalVertex); // component on goal as vertex
					DefaultEdge edge = this.graph.addEdge(origVertex, goalVertex); //add edge
					this.graph.setEdgeWeight(edge, 1); //weight is 1 first encounter
				}else { //component already in the graph
						//check if there is already a connection  between current orig and goal components
					DefaultEdge edge = this.graph.getEdge(origVertex, goalVertex);
					
					if(edge!=null) { //edge exists. Simply adjust the weights
					    double currentWeight = this.graph.getEdgeWeight(edge);
					    this.graph.setEdgeWeight(edge,currentWeight+1);
					}else {
						// no edges. Create a simple edge
						edge = this.graph.addEdge(origVertex, goalVertex); //add edge
						this.graph.setEdgeWeight(edge, 1); //weight is 1 first encounter
					}
				}
				
			}
			//e.getValue().getOperations();
			//
			
		}
		assert this.graph.vertexSet().size() == this.orig.getComponents().size()+this.goal.getComponents().size();	
		
		if(this.orig.getComponents().size()<this.goal.getComponents().size()) { //stock ip orig vertices with dummies
			int diff = this.orig.getComponents().size()-this.goal.getComponents().size();
			for(int i=0;i<diff;i++) {
				String dummy = "dummy"+i;
				this.graph.addVertex(dummy);
				this.s.add(dummy);
				for(Entry <String, AssemblyComponent>e:this.goal.getComponents().entrySet()) {
					DefaultEdge edge=this.graph.addEdge(dummy, e.getKey());
					this.graph.setEdgeWeight(edge, 0);
				}
			}
			
		}else if(this.orig.getComponents().size()>this.goal.getComponents().size()) { //stock up goal vertices with dummies
			int diff = this.goal.getComponents().size()-this.orig.getComponents().size();
			for(int i=0;i<diff;i++) {
				String dummy = "dummy"+i;
				this.graph.addVertex(dummy);
				this.t.add(dummy);
				for(Entry <String, AssemblyComponent>e:this.orig.getComponents().entrySet()) {
					DefaultEdge edge=this.graph.addEdge(e.getKey(), dummy);
					this.graph.setEdgeWeight(edge, 0);
				}
		}
		}
		KuhnMunkresMinimalWeightBipartitePerfectMatching<String, DefaultEdge> matcher = new KuhnMunkresMinimalWeightBipartitePerfectMatching<String,DefaultEdge>(graph, s, t);
		this.matching = matcher.getMatching();
		
		
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
		Graph<String, DefaultEdge> graph = this.matching.getGraph();
		for(DefaultEdge e: this.matching.getEdges()) {
			if(graph.getEdgeWeight(e)>0) {
				String source = graph.getEdgeSource(e);
				String target = graph.getEdgeTarget(e);
			this.operationToComponentG.put(source, target);
			this.operationToComponentO.put(target, source);

			}
			//graph.getEdgeWeight(e)
		}
		
		
	}
	
	private void populateOperationTocomponentG() {
		Graph<String, DefaultEdge> graph = this.matching.getGraph();
		for(DefaultEdge e: this.matching.getEdges()) {
			if(graph.getEdgeWeight(e)>0) {
				String source = graph.getEdgeSource(e);
				String target = graph.getEdgeTarget(e);
			this.operationToComponentG.put(source, target);
			this.operationToComponentO.put(target, source);

			}
			//graph.getEdgeWeight(e)
		}
		
	}
	
	
}
