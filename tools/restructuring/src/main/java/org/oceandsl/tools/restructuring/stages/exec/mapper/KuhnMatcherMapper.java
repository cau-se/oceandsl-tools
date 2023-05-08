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

import kieker.model.analysismodel.assembly.AssemblyComponent;
import kieker.model.analysismodel.assembly.AssemblyModel;
import kieker.model.analysismodel.assembly.AssemblyOperation;

import org.oceandsl.tools.restructuring.util.RestructurerTools;

/**
 *
 * @author Serafim Simonov
 * @since 1.3.0
 */
public class KuhnMatcherMapper extends AbstractComponentMapper {

    private final SimpleWeightedGraph<String, DefaultEdge> graph;
    private final Set<String> s = new HashSet<>();
    private final Set<String> t = new HashSet<>();
    private final Matching<String, DefaultEdge> matching;

    private HashMap<String, String> operationToComponentO = new HashMap<>();
    private HashMap<String, String> operationToComponentG = new HashMap<>();

    private HashMap<String, HashMap<String, Integer>> traceModell = new HashMap<>();
    private HashMap<String, String> goalToOriginal = new HashMap<>();
    private HashMap<String, String> originalToGoal = new HashMap<>();

    public KuhnMatcherMapper(final AssemblyModel orig, final AssemblyModel goal, final String originalModelName,
            final String goalModelName) {
        super(originalModelName, goalModelName);
        this.original = RestructurerTools.cloneModel(orig);
        this.goal = RestructurerTools.alterComponentNames(goal);
        this.s.addAll(this.original.getComponents().keySet());
        this.t.addAll(this.goal.getComponents().keySet());
        this.graph = new SimpleWeightedGraph<>(DefaultEdge.class);
        this.populateOperationTocomponentG();
        this.populateOperationToComponentO();

        if (this.operationToComponentO.size() != this.operationToComponentG.size()) {
            throw new Error("Some operations are lost?");
        }
        // System.out.println("Same comps:" +
        // operationToComponentO.values().equals(operationToComponentG.values()));
        // init mappings

        // create graphp

        // initial graph
        for (final Entry<String, AssemblyComponent> c : this.original.getComponents().entrySet()) { // each
            // component
            // is
            // a
            // vertex

            final String origVertex = c.getKey();

            this.graph.addVertex(origVertex); // create vertex from original component
            for (final Entry<String, AssemblyOperation> ops : c.getValue().getOperations().entrySet()) { // iterate
                                                                                                         // through
                // operations in
                // original
                // componen

                final String goalVertex = this.operationToComponentG.get(ops.getKey());
                if (!this.graph.containsVertex(goalVertex)) { // vertex was not added yet , thus
                                                              // simply create and edge
                    this.graph.addVertex(goalVertex); // component on goal as vertex
                    final DefaultEdge edge = this.graph.addEdge(origVertex, goalVertex);// add edge
                    this.graph.setEdgeWeight(edge, 1); // weight is 1 first encounter
                } else { // component already in the graph
                         // check if there is already a connection between current orig and goal
                         // components
                    DefaultEdge edge = this.graph.getEdge(origVertex, goalVertex);

                    if (edge != null) { // edge exists. Simply adjust the weights
                        final double currentWeight = this.graph.getEdgeWeight(edge);
                        this.graph.setEdgeWeight(edge, currentWeight + 1);
                    } else {
                        // no edges. Create a simple edge
                        if (origVertex.equals(goalVertex)) {
                            System.out.println(origVertex + " " + goalVertex);
                        }
                        edge = this.graph.addEdge(origVertex, goalVertex); // add edge
                        this.graph.setEdgeWeight(edge, 1); // weight is 1 first encounter
                    }
                }

            }
            // e.getValue().getOperations();
            //

        }
        assert this.graph.vertexSet()
                .size() == (this.original.getComponents().size() + this.goal.getComponents().size());
        // add dummies to equlize partitions
        /*
         * if(this.orig.getComponents().size()<this.goal.getComponents().size()) { //stock ip orig
         * vertices with dummies int diff =
         * this.goal.getComponents().size()-this.orig.getComponents().size(); for(int
         * i=0;i<diff;i++) { String dummy = "dummy"+i; this.graph.addVertex(dummy);
         * this.s.add(dummy); for(Entry <String,
         * AssemblyComponent>e:this.goal.getComponents().entrySet()) { DefaultEdge
         * edge=this.graph.addEdge(dummy, e.getKey()); this.graph.setEdgeWeight(edge, 0); } }
         *
         * }else if(this.orig.getComponents().size()>this.goal.getComponents().size()) { //stock up
         * goal vertices with dummies int diff =
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
         * for(String o:this.s) { for(String g : this.t) { if(!this.graph.containsEdge(o,g)) {
         * DefaultEdge edge=this.graph.addEdge(o, g); this.graph.setEdgeWeight(edge, 0); } } }
         */
        // System.out.println(this.s.size());
        // System.out.println(this.t.size());

        // assert this.s.size()==this.t.size();
        // System.out.println(this.s.size()*this.t.size());
        // System.out.println(this.graph.edgeSet().size());
        // assert this.s.size()*this.t.size()==this.graph.edgeSet().size();
        final MaximumWeightBipartiteMatching<String, DefaultEdge> matcher = new MaximumWeightBipartiteMatching<>(
                this.graph, this.s, this.t);
        this.matching = matcher.getMatching();
        this.computeOriginalComponentNames();
        // System.out.println("Size of gto "+this.goalToOriginal.size());
        // System.out.println("Size of otg " + this.originallToGoal.size());
        // System.out.println("Num of matching"+this.matching.getEdges().size());

    }

    @Override
    public HashMap<String, String> getOperationToComponentO() {
        return this.operationToComponentO;
    }

    @Override
    public void setOperationToComponentO(final HashMap<String, String> operationToComponentO) {
        this.operationToComponentO = operationToComponentO;
    }

    @Override
    public HashMap<String, String> getOperationToComponentG() {
        return this.operationToComponentG;
    }

    @Override
    public void setOperationToComponentG(final HashMap<String, String> operationToComponentG) {
        this.operationToComponentG = operationToComponentG;
    }

    @Override
    public HashMap<String, HashMap<String, Integer>> getTraceModell() {
        return this.traceModell;
    }

    @Override
    public void setTraceModell(final HashMap<String, HashMap<String, Integer>> traceModell) {
        this.traceModell = traceModell;
    }

    @Override
    public HashMap<String, String> getGoalToOriginal() {
        return this.goalToOriginal;
    }

    @Override
    public void setGoalToOriginal(final HashMap<String, String> goalToOriginal) {
        this.goalToOriginal = goalToOriginal;
    }

    @Override
    public HashMap<String, String> getOriginalToGoal() {
        return this.originalToGoal;
    }

    @Override
    public void setOriginalToGoal(final HashMap<String, String> originalToGoal) {
        this.originalToGoal = originalToGoal;
    }

    @Override
    public AssemblyModel getOriginal() {
        return this.original;

    }

    @Override
    public AssemblyModel getGoal() {
        return this.goal;

    }

    private void computeOriginalComponentNames() {
        final Graph<String, DefaultEdge> graph = this.matching.getGraph();
        for (final DefaultEdge e : this.matching.getEdges()) {
            if (graph.getEdgeWeight(e) > 0) {
                final String source = graph.getEdgeSource(e);
                final String target = graph.getEdgeTarget(e);
                // String trueTarget = target.substring(0, target.length()-3);
                this.goalToOriginal.put(target, source);
                this.originalToGoal.put(source, target);

            }
        }
        // graph.getEdgeWeight(e)
    }

    private void populateOperationToComponentO() {
        for (final Entry<String, AssemblyComponent> e : this.original.getComponents().entrySet()) {
            final Set<String> ops = e.getValue().getOperations().keySet();
            for (final String s : ops) {
                this.operationToComponentO.put(s, e.getKey());
            }
        }
    }

    private void populateOperationTocomponentG() {
        for (final Entry<String, AssemblyComponent> e : this.goal.getComponents().entrySet()) {
            final Set<String> ops = e.getValue().getOperations().keySet();
            for (final String s : ops) {
                this.operationToComponentG.put(s, e.getKey());
            }
        }
    }

}
