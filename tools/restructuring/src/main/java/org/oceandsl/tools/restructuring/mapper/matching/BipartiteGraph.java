/***************************************************************************
 * Copyright (C) 2023 OceanDSL (https://oceandsl.uni-kiel.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package org.oceandsl.tools.restructuring.mapper.matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;

@Deprecated
public class BipartiteGraph implements Graph<Vertex, Edge> {
    private final int leftSize; // size of left partition
    private final int rightSize; // size of right partition
    private final Map<Vertex, List<Edge>> adjacencyList; // adjacency list representation of graph

    public BipartiteGraph(final List<Vertex> leftVertices, final List<Vertex> rightVertices) {
        this.leftSize = leftVertices.size();
        this.rightSize = rightVertices.size();
        this.adjacencyList = new HashMap<>();
        for (final Vertex vertex : leftVertices) {
            this.adjacencyList.put(vertex, new ArrayList<>());
        }
        for (final Vertex vertex : rightVertices) {
            this.adjacencyList.put(vertex, new ArrayList<>());
        }
    }

    public int getLeftSize() {
        return this.leftSize;
    }

    public int getRightSize() {
        return this.rightSize;
    }

    public Map<Vertex, List<Edge>> getAdjacencyList() {
        return this.adjacencyList;
    }

    public void addEdge(final Vertex leftVertex, final Vertex rightVertex, final double weight) {
        if (!this.adjacencyList.containsKey(leftVertex)) {
            throw new IllegalArgumentException("Left vertex not in graph");
        }
        if (!this.adjacencyList.containsKey(rightVertex)) {
            throw new IllegalArgumentException("Right vertex not in graph");
        }
        final Edge edge = new Edge(rightVertex, weight);
        this.adjacencyList.get(leftVertex).add(edge);
        final Edge reverseEdge = new Edge(leftVertex, weight);
        this.adjacencyList.get(rightVertex).add(reverseEdge);
    }

    public List<Edge> getNeighbors(final Vertex vertex) {
        if (!this.adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex not in graph");
        }
        return this.adjacencyList.get(vertex);
    }

    public boolean isBipartite() {
        final int[] color = new int[this.leftSize + this.rightSize];
        Arrays.fill(color, -1);
        for (final Vertex vertex : this.adjacencyList.keySet()) {
            if (color[this.getIndex(vertex)] == -1) {
                color[this.getIndex(vertex)] = 0;
                final Queue<Vertex> queue = new LinkedList<>();
                queue.offer(vertex);
                while (!queue.isEmpty()) {
                    final Vertex curr = queue.poll();
                    for (final Edge edge : this.getNeighbors(curr)) {
                        final Vertex neighbor = edge.getVertex();
                        if (color[this.getIndex(neighbor)] == -1) {
                            color[this.getIndex(neighbor)] = 1 - color[this.getIndex(curr)];
                            queue.offer(neighbor);
                        } else if (color[this.getIndex(neighbor)] == color[this.getIndex(curr)]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private int getIndex(final Vertex vertex) {
        if (this.adjacencyList.containsKey(vertex)) {
            int index = 0;
            for (final Vertex v : this.adjacencyList.keySet()) {
                if (v.equals(vertex)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public Set<Edge> getAllEdges(final Vertex sourceVertex, final Vertex targetVertex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Edge getEdge(final Vertex sourceVertex, final Vertex targetVertex) {
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
    public Edge addEdge(final Vertex sourceVertex, final Vertex targetVertex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addEdge(final Vertex sourceVertex, final Vertex targetVertex, final Edge e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Vertex addVertex() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addVertex(final Vertex v) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsEdge(final Vertex sourceVertex, final Vertex targetVertex) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsEdge(final Edge e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsVertex(final Vertex v) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Edge> edgeSet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int degreeOf(final Vertex vertex) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Set<Edge> edgesOf(final Vertex vertex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int inDegreeOf(final Vertex vertex) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Set<Edge> incomingEdgesOf(final Vertex vertex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int outDegreeOf(final Vertex vertex) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Set<Edge> outgoingEdgesOf(final Vertex vertex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean removeAllEdges(final Collection<? extends Edge> edges) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Edge> removeAllEdges(final Vertex sourceVertex, final Vertex targetVertex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean removeAllVertices(final Collection<? extends Vertex> vertices) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Edge removeEdge(final Vertex sourceVertex, final Vertex targetVertex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean removeEdge(final Edge e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeVertex(final Vertex v) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Vertex> vertexSet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vertex getEdgeSource(final Edge e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vertex getEdgeTarget(final Edge e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GraphType getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getEdgeWeight(final Edge e) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setEdgeWeight(final Edge e, final double weight) {
        // TODO Auto-generated method stub

    }

}
