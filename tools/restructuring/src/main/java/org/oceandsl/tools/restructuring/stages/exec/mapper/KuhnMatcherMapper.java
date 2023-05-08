package org.oceandsl.tools.restructuring.stages.exec.mapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm.Matching;
import org.jgrapht.alg.matching.MaximumWeightBipartiteMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.oceandsl.tools.restructuring.util.RestructurerTools;

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;

public class KuhnMatcherMapper extends AbstractComponentMapper {
	private ComponentsMapper compMapper;
	private final static String GOAL = "20_";
	private AssemblyModel orig;
	private AssemblyModel goal;

	private SimpleWeightedGraph<String, DefaultEdge> graph;
	private Set<String> s = new HashSet<String>();
	private Set<String> t = new HashSet<String>();
	private Matching<String, DefaultEdge> matching;

	private HashMap<String, String> operationToComponentO = new HashMap<String, String>();
	private HashMap<String, String> operationToComponentG = new HashMap<String, String>();

	private HashMap<String, HashMap<String, Integer>> traceModell = new HashMap<String, HashMap<String, Integer>>();
	private HashMap<String, String> goalToOriginal = new HashMap<String, String>();
	private HashMap<String, String> originallToGoal = new HashMap<String, String>();

	public KuhnMatcherMapper(AssemblyModel orig, AssemblyModel goal, String originalModelName, String goalModelName) {
		super(originalModelName, goalModelName);
		this.orig = RestructurerTools.cloneModel(orig);
		this.goal = RestructurerTools.alterComponentNames(goal);
		this.s.addAll(this.orig.getComponents().keySet());
		this.t.addAll(this.goal.getComponents().keySet());
		this.graph = new SimpleWeightedGraph<String, DefaultEdge>(DefaultEdge.class);
		populateOperationTocomponentG();
		populateOperationToComponentO();

		if (this.operationToComponentO.size() != this.operationToComponentG.size()) {
			throw new Error("Some operations are lost?");
		}
		// System.out.println("Same comps:" +
		// operationToComponentO.values().equals(operationToComponentG.values()));
		// init mappings

		// create graphp

		// initial graph
		for (Entry<String, AssemblyComponent> c : this.orig.getComponents().entrySet()) { // each component is a vertex

			String origVertex = c.getKey();

			this.graph.addVertex(origVertex); // create vertex from original component
			for (Entry<String, AssemblyOperation> ops : c.getValue().getOperations().entrySet()) { // iterate through
																									// operations in
																									// original
																									// componen

				String goalVertex = this.operationToComponentG.get(ops.getKey());
				if (!this.graph.containsVertex(goalVertex)) { // vertex was not added yet , thus simply create and edge
					this.graph.addVertex(goalVertex); // component on goal as vertex
					DefaultEdge edge = this.graph.addEdge(origVertex, goalVertex);// add edge
					this.graph.setEdgeWeight(edge, 1); // weight is 1 first encounter
				} else { // component already in the graph
							// check if there is already a connection between current orig and goal
							// components
					DefaultEdge edge = this.graph.getEdge(origVertex, goalVertex);

					if (edge != null) { // edge exists. Simply adjust the weights
						double currentWeight = this.graph.getEdgeWeight(edge);
						this.graph.setEdgeWeight(edge, currentWeight + 1);
					} else {
						// no edges. Create a simple edge
						if (origVertex.equals(goalVertex))
							System.out.println(origVertex + " " + goalVertex);
						edge = this.graph.addEdge(origVertex, goalVertex); // add edge
						this.graph.setEdgeWeight(edge, 1); // weight is 1 first encounter
					}
				}

			}
			// e.getValue().getOperations();
			//

		}
		assert this.graph.vertexSet().size() == this.orig.getComponents().size() + this.goal.getComponents().size();
		// add dummies to equlize partitions
		/*
		 * if(this.orig.getComponents().size()<this.goal.getComponents().size()) {
		 * //stock ip orig vertices with dummies int diff =
		 * this.goal.getComponents().size()-this.orig.getComponents().size(); for(int
		 * i=0;i<diff;i++) { String dummy = "dummy"+i; this.graph.addVertex(dummy);
		 * this.s.add(dummy); for(Entry <String,
		 * AssemblyComponent>e:this.goal.getComponents().entrySet()) { DefaultEdge
		 * edge=this.graph.addEdge(dummy, e.getKey()); this.graph.setEdgeWeight(edge,
		 * 0); } }
		 *
		 * }else if(this.orig.getComponents().size()>this.goal.getComponents().size()) {
		 * //stock up goal vertices with dummies int diff =
		 * this.orig.getComponents().size()-this.goal.getComponents().size(); for(int
		 * i=0;i<diff;i++) { String dummy = "dummy"+i; this.graph.addVertex(dummy);
		 * this.t.add(dummy); for(Entry <String,
		 * AssemblyComponent>e:this.orig.getComponents().entrySet()) { DefaultEdge
		 * edge=this.graph.addEdge(e.getKey(), dummy);
		 *
		 * this.graph.setEdgeWeight(edge, 0); } } }
		 *
		 * //add 0 edges if not exist yet
		 *
		 * for(String o:this.s) { for(String g : this.t) {
		 * if(!this.graph.containsEdge(o,g)) { DefaultEdge edge=this.graph.addEdge(o,
		 * g); this.graph.setEdgeWeight(edge, 0); } } }
		 */
		// System.out.println(this.s.size());
		// System.out.println(this.t.size());

		// assert this.s.size()==this.t.size();
		// System.out.println(this.s.size()*this.t.size());
		// System.out.println(this.graph.edgeSet().size());
		// assert this.s.size()*this.t.size()==this.graph.edgeSet().size();
		MaximumWeightBipartiteMatching<String, DefaultEdge> matcher = new MaximumWeightBipartiteMatching<String, DefaultEdge>(
				graph, s, t);
		this.matching = matcher.getMatching();
		computeOriginalComponentNames();
		// System.out.println("Size of gto "+this.goalToOriginal.size());
		// System.out.println("Size of otg " + this.originallToGoal.size());
		// System.out.println("Num of matching"+this.matching.getEdges().size());

	}

	@Override
	public HashMap<String, String> getOperationToComponentO() {
		return operationToComponentO;
	}

	@Override
	public void setOperationToComponentO(HashMap<String, String> operationToComponentO) {
		this.operationToComponentO = operationToComponentO;
	}

	@Override
	public HashMap<String, String> getOperationToComponentG() {
		return operationToComponentG;
	}

	@Override
	public void setOperationToComponentG(HashMap<String, String> operationToComponentG) {
		this.operationToComponentG = operationToComponentG;
	}

	@Override
	public HashMap<String, HashMap<String, Integer>> getTraceModell() {
		return traceModell;
	}

	@Override
	public void setTraceModell(HashMap<String, HashMap<String, Integer>> traceModell) {
		this.traceModell = traceModell;
	}

	@Override
	public HashMap<String, String> getGoalToOriginal() {
		return goalToOriginal;
	}

	@Override
	public void setGoalToOriginal(HashMap<String, String> goalToOriginal) {
		this.goalToOriginal = goalToOriginal;
	}

	@Override
	public HashMap<String, String> getOriginalToGoal() {
		return originallToGoal;
	}

	@Override
	public void setOriginallToGoal(HashMap<String, String> originallToGoal) {
		this.originallToGoal = originallToGoal;
	}

	@Override
	public AssemblyModel getOriginal() {
		return this.orig;

	}

	@Override
	public AssemblyModel getGoal() {
		return this.goal;

	}

	private void computeOriginalComponentNames() {
		Graph<String, DefaultEdge> graph = this.matching.getGraph();
		for (DefaultEdge e : this.matching.getEdges()) {
			if (graph.getEdgeWeight(e) > 0) {
				String source = graph.getEdgeSource(e);
				String target = graph.getEdgeTarget(e);
				// String trueTarget = target.substring(0, target.length()-3);
				this.goalToOriginal.put(target, source);
				this.originallToGoal.put(source, target);

			}
		}
		// graph.getEdgeWeight(e)
	}

	private void populateOperationToComponentO() {
		for (Entry<String, AssemblyComponent> e : this.orig.getComponents().entrySet()) {
			Set<String> ops = e.getValue().getOperations().keySet();
			for (String s : ops) {
				this.operationToComponentO.put(s, e.getKey());
			}
		}
	}

	private void populateOperationTocomponentG() {
		for (Entry<String, AssemblyComponent> e : this.goal.getComponents().entrySet()) {
			Set<String> ops = e.getValue().getOperations().keySet();
			for (String s : ops) {
				this.operationToComponentG.put(s, e.getKey());
			}
		}
	}

}
