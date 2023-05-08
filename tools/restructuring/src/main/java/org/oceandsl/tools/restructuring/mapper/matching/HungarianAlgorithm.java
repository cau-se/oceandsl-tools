package org.oceandsl.tools.restructuring.mapper.matching;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Deprecated
public class HungarianAlgorithm {

	public static Map<Vertex, Vertex> getMaximumMatching(BipartiteGraph graph) {
		Map<Vertex, Vertex> matching = new HashMap<>();
		Map<Vertex, Boolean> visited = new HashMap<>();

		for (Vertex vertex : graph.getAdjacencyList().keySet()) {
			visited.put(vertex, false);
		}
		for (Vertex vertex : graph.getAdjacencyList().keySet()) {
			if (!visited.get(vertex)) {
				visited.put(vertex, true);
				for (Edge edge : graph.getAdjacencyList().get(vertex)) {
					if (matching.get(edge.getVertex()) == null) {
						matching.put(vertex, edge.getVertex());
						matching.put(edge.getVertex(), vertex);
						break;
					}
				}
			}
		}
		boolean foundAugmentingPath = true;
		while (foundAugmentingPath) {
			foundAugmentingPath = false;
			Queue<Vertex> queue = new LinkedList<>();
			Map<Vertex, Vertex> parent = new HashMap<>();
			Map<Vertex, Boolean> visitedNodes = new HashMap<>();
			for (Vertex vertex : graph.getAdjacencyList().keySet()) {
				visitedNodes.put(vertex, false);
			}
			for (Vertex vertex : matching.keySet()) {
				if (matching.get(vertex) == null) {
					visitedNodes.put(vertex, true);
					parent.put(vertex, null);
					queue.offer(vertex);
				}
			}
			while (!queue.isEmpty()) {
				Vertex curr = queue.poll();
				for (Edge edge : graph.getAdjacencyList().get(curr)) {
					Vertex neighbor = edge.getVertex();
					if (!visitedNodes.get(neighbor)) {
						visitedNodes.put(neighbor, true);
						parent.put(neighbor, curr);
						if (matching.get(neighbor) == null) {
							augmentPath(parent, curr, neighbor, matching);
							foundAugmentingPath = true;
							break;
						} else {
							visitedNodes.put(matching.get(neighbor), true);
							parent.put(matching.get(neighbor), neighbor);
							queue.offer(matching.get(neighbor));
						}
					}
				}
				if (foundAugmentingPath) {
					break;
				}
			}
		}
		return matching;
	}

	private static void augmentPath(Map<Vertex, Vertex> parent, Vertex start, Vertex end,
			Map<Vertex, Vertex> matching) {
		Vertex curr = end;
		while (curr != start) {
			Vertex prev = parent.get(curr);
			matching.put(curr, prev);
			matching.put(prev, curr);
			curr = prev;
		}
	}
}
