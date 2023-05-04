package org.oceandsl.tools.restructuring.stages.exec.mapper.matching;

@Deprecated
public class Edge {
	private Vertex vertex;
	private double weight;

	public Edge(Vertex vertex, double weight) {
		this.vertex = vertex;
		this.weight = weight;
	}

	public Vertex getVertex() {
		return vertex;
	}

	public double getWeight() {
		return weight;
	}
}