package as4;

import java.util.Random;

public class Experiment {
    private static final int SMALL_SIZE = 10;
    private static final int MEDIUM_SIZE = 30;
    private static final int LARGE_SIZE = 100;

    private long bfsSmallTime;
    private long dfsSmallTime;
    private long bfsMediumTime;
    private long dfsMediumTime;
    private long bfsLargeTime;
    private long dfsLargeTime;

    private Graph generateRandomGraph(int numberOfVertices, int edgesPerVertex) {
        Graph graph = new Graph();

        for (int i = 0; i < numberOfVertices; i++) {
            graph.addVertex(new Vertex(i));
        }

        Random random = new Random(42);

        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < edgesPerVertex; j++) {
                int destination = random.nextInt(numberOfVertices);

                if (i != destination) {
                    graph.addEdge(i, destination);
                }
            }
        }

        return graph;
    }

    public void runTraversals(Graph graph) {
        graph.printGraph();

        System.out.println();
        graph.bfs(0);
        graph.dfs(0);
    }

    public void runMultipleTests() {
        System.out.println("=== Graph Traversal and Representation System ===");

        Graph smallGraph = generateRandomGraph(SMALL_SIZE, 2);
        Graph mediumGraph = generateRandomGraph(MEDIUM_SIZE, 4);
        Graph largeGraph = generateRandomGraph(LARGE_SIZE, 5);

        System.out.println("\n--- Small Graph (10 vertices) ---");
        runTraversals(smallGraph);

        long start;
        long end;

        start = System.nanoTime();
        smallGraph.bfs(0);
        end = System.nanoTime();
        bfsSmallTime = end - start;

        start = System.nanoTime();
        smallGraph.dfs(0);
        end = System.nanoTime();
        dfsSmallTime = end - start;

        System.out.println("\n--- Medium Graph (30 vertices) ---");

        start = System.nanoTime();
        mediumGraph.bfs(0);
        end = System.nanoTime();
        bfsMediumTime = end - start;

        start = System.nanoTime();
        mediumGraph.dfs(0);
        end = System.nanoTime();
        dfsMediumTime = end - start;

        System.out.println("\n--- Large Graph (100 vertices) ---");

        start = System.nanoTime();
        largeGraph.bfs(0);
        end = System.nanoTime();
        bfsLargeTime = end - start;

        start = System.nanoTime();
        largeGraph.dfs(0);
        end = System.nanoTime();
        dfsLargeTime = end - start;
    }

    public void printResults() {
        System.out.println("\n=== Experimental Results ===");
        System.out.printf("%-15s | %-15s | %-15s%n", "Graph Size", "BFS Time (ns)", "DFS Time (ns)");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-15s | %-15d | %-15d%n", "Small (10)", bfsSmallTime, dfsSmallTime);
        System.out.printf("%-15s | %-15d | %-15d%n", "Medium (30)", bfsMediumTime, dfsMediumTime);
        System.out.printf("%-15s | %-15d | %-15d%n", "Large (100)", bfsLargeTime, dfsLargeTime);
    }
}