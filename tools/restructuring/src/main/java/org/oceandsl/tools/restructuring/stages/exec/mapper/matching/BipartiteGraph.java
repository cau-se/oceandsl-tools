package org.oceandsl.tools.restructuring.stages.exec.mapper.matching;

import java.util.*;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;

public class BipartiteGraph implements Graph<Vertex,Edge> {
    private int leftSize; // size of left partition
    private int rightSize; // size of right partition
    private Map<Vertex, List<Edge>> adjacencyList; // adjacency list representation of graph

    public BipartiteGraph(List<Vertex> leftVertices, List<Vertex> rightVertices) {
        this.leftSize = leftVertices.size();
        this.rightSize = rightVertices.size();
        adjacencyList = new HashMap<>();
        for (Vertex vertex : leftVertices) {
            adjacencyList.put(vertex, new ArrayList<>());
        }
        for (Vertex vertex : rightVertices) {
            adjacencyList.put(vertex, new ArrayList<>());
        }
    }

    public int getLeftSize() {
        return leftSize;
    }

    public int getRightSize() {
        return rightSize;
    }
    
    public Map<Vertex, List<Edge>> getAdjacencyList(){
    	return this.adjacencyList;
    }

    public void addEdge(Vertex leftVertex, Vertex rightVertex, double weight) {
        if (!adjacencyList.containsKey(leftVertex)) {
            throw new IllegalArgumentException("Left vertex not in graph");
        }
        if (!adjacencyList.containsKey(rightVertex)) {
            throw new IllegalArgumentException("Right vertex not in graph");
        }
        Edge edge = new Edge(rightVertex, weight);
        adjacencyList.get(leftVertex).add(edge);
        Edge reverseEdge = new Edge(leftVertex, weight);
        adjacencyList.get(rightVertex).add(reverseEdge);
    }

    public List<Edge> getNeighbors(Vertex vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex not in graph");
        }
        return adjacencyList.get(vertex);
    }

    public boolean isBipartite() {
        int[] color = new int[leftSize + rightSize];
        Arrays.fill(color, -1);
        for (Vertex vertex : adjacencyList.keySet()) {
            if (color[getIndex(vertex)] == -1) {
                color[getIndex(vertex)] = 0;
                Queue<Vertex> queue = new LinkedList<>();
                queue.offer(vertex);
                while (!queue.isEmpty()) {
                    Vertex curr = queue.poll();
                    for (Edge edge : getNeighbors(curr)) {
                        Vertex neighbor = edge.getVertex();
                        if (color[getIndex(neighbor)] == -1) {
                            color[getIndex(neighbor)] = 1 - color[getIndex(curr)];
                            queue.offer(neighbor);
                        } else if (color[getIndex(neighbor)] == color[getIndex(curr)]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private int getIndex(Vertex vertex) {
        if (adjacencyList.containsKey(vertex)) {
            int index = 0;
            for (Vertex v : adjacencyList.keySet()) {
                if (v.equals(vertex)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

	@Override
	public Set<Edge> getAllEdges(Vertex sourceVertex, Vertex targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge getEdge(Vertex sourceVertex, Vertex targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier<Vertex> getVertexSupplier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier<Edge> getEdgeSupplier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge addEdge(Vertex sourceVertex, Vertex targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addEdge(Vertex sourceVertex, Vertex targetVertex, Edge e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vertex addVertex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addVertex(Vertex v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsEdge(Vertex sourceVertex, Vertex targetVertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsEdge(Edge e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsVertex(Vertex v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Edge> edgeSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int degreeOf(Vertex vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Edge> edgesOf(Vertex vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int inDegreeOf(Vertex vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Edge> incomingEdgesOf(Vertex vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int outDegreeOf(Vertex vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Edge> outgoingEdgesOf(Vertex vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAllEdges(Collection<? extends Edge> edges) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Edge> removeAllEdges(Vertex sourceVertex, Vertex targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAllVertices(Collection<? extends Vertex> vertices) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Edge removeEdge(Vertex sourceVertex, Vertex targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeEdge(Edge e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeVertex(Vertex v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Vertex> vertexSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex getEdgeSource(Edge e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex getEdgeTarget(Edge e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getEdgeWeight(Edge e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setEdgeWeight(Edge e, double weight) {
		// TODO Auto-generated method stub
		
	}

}
   
