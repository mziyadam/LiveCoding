package Graph;

public class ApakahValidTree {
    // Parent array for Union-Find (Disjoint Set Union) data structure
    private int[] parent;

    /**
     * Determines if the given edges form a valid tree.
     * A valid tree must:
     * 1. Be connected (all nodes in one component)
     * 2. Have no cycles
     * 3. Have exactly n-1 edges for n nodes
     *
     * @param n     The number of nodes (labeled from 0 to n-1)
     * @param edges The edges represented as pairs of nodes
     * @return true if the edges form a valid tree, false otherwise
     */
    public boolean validTree(int n, int[][] edges) {
        // Initialize parent array where each node is its own parent initially
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Process each edge
        for (int[] edge : edges) {
            // Find the root parents of both nodes in the edge
            int parentA = find(edge[0]);
            int parentB = find(edge[1]);

            // If both nodes already have the same parent, adding this edge creates a cycle
            if (parentA == parentB) {
                return false;
            }

            // Union: Connect the two components by making one parent point to the other
            parent[parentA] = parentB;

            // Decrease the number of components
            n--;
        }

        // After processing all edges, we should have exactly 1 component
        // (started with n components, merged n-1 times)
        return n == 1;
    }

    /**
     * Finds the root parent of a node using path compression.
     * Path compression optimizes future lookups by making nodes point directly to root.
     *
     * @param x The node to find the root parent for
     * @return The root parent of node x
     */
    private int find(int x) {
        // If x is not its own parent, recursively find the root and compress path
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }
}
