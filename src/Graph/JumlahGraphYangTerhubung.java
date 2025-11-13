package Graph;

import java.util.List;

import java.util.*;

class JumlahGraphYangTerhubung {
    // Adjacency list to represent the graph
    private List<Integer>[] adjacencyList;
    // Boolean array to track visited nodes
    private boolean[] visited;

    /**
     * Counts the number of connected components in an undirected graph
     * @param n Number of nodes in the graph (0 to n-1)
     * @param edges Array of edges where each edge is [node1, node2]
     * @return Number of connected components
     */
    public int countComponents(int n, int[][] edges) {
        // Initialize the adjacency list for n nodes
        adjacencyList = new List[n];
        visited = new boolean[n];

        // Create an empty ArrayList for each node
        Arrays.setAll(adjacencyList, index -> new ArrayList<>());

        // Build the undirected graph by adding edges in both directions
        for (int[] edge : edges) {
            int nodeA = edge[0];
            int nodeB = edge[1];
            adjacencyList[nodeA].add(nodeB);
            adjacencyList[nodeB].add(nodeA);
        }

        // Count connected components by performing DFS from each unvisited node
        int componentCount = 0;
        for (int node = 0; node < n; node++) {
            // If DFS returns 1, we found a new component
            componentCount += dfs(node);
        }

        return componentCount;
    }

    /**
     * Depth-first search to explore all nodes in a connected component
     * @param currentNode The current node being explored
     * @return 1 if this node starts a new component, 0 if already visited
     */
    private int dfs(int currentNode) {
        // If node is already visited, it's part of an existing component
        if (visited[currentNode]) {
            return 0;
        }

        // Mark current node as visited
        visited[currentNode] = true;

        // Recursively visit all adjacent nodes
        for (int neighbor : adjacencyList[currentNode]) {
            dfs(neighbor);
        }

        // Return 1 to indicate we found a new component
        return 1;
    }
}