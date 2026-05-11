package as4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph {
    private Map<Integer, Vertex> vertices;
    private Map<Integer, List<Edge>> adjacencyList;

    public Graph() {
        this.vertices = new HashMap<>();
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        vertices.putIfAbsent(v.getId(), v);
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
            System.out.println("Cannot add edge from " + from + " to " + to + ". Vertex not found.");
            return;
        }

        adjacencyList.get(from).add(new Edge(from, to));
    }

    public void printGraph() {
        System.out.println("Graph Adjacency List:");

        for (Integer vertexId : adjacencyList.keySet()) {
            System.out.print("Vertex " + vertexId + " -> ");

            List<Edge> edges = adjacencyList.get(vertexId);

            for (Edge edge : edges) {
                System.out.print(edge.getDestination() + " ");
            }

            System.out.println();
        }
    }

    public void bfs(int start) {
        if (!vertices.containsKey(start)) {
            System.out.println("Start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.print("BFS Traversal starting from " + start + ": ");

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (Edge edge : adjacencyList.get(current)) {
                int neighbor = edge.getDestination();

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println();
    }

    public void dfs(int start) {
        if (!vertices.containsKey(start)) {
            System.out.println("Start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new HashSet<>();

        System.out.print("DFS Traversal starting from " + start + ": ");
        dfsRecursive(start, visited);
        System.out.println();
    }

    private void dfsRecursive(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(current + " ");

        for (Edge edge : adjacencyList.get(current)) {
            int neighbor = edge.getDestination();

            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }
}