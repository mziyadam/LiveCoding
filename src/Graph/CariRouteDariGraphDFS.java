package Graph;

import java.util.*;

public class CariRouteDariGraphDFS {

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph,int start,int target) {

        List<List<Integer>> allPaths = new ArrayList<>();
        int n = graph.length;

        LinkedList<Integer> currentPath = new LinkedList<>();

        dfs(graph, start, currentPath,target,allPaths);

        return allPaths;
    }

    private static void dfs(int[][] graph, int node, LinkedList<Integer> currentPath,int target,List<List<Integer>> allPaths) {
        currentPath.add(node);

        if (node == target) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            for (int neighbor : graph[node]) {
                dfs(graph, neighbor, currentPath,target,allPaths);
            }
        }
        currentPath.removeLast();
    }
}