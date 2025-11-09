package Graph;
import java.util.*;

public class ApakahPathAdaDiGraphDFS {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            Map<Integer,List<Integer>> mp=new HashMap<>();
            Set<Integer> visited=new HashSet<>();
            for(int i = 0; i<n; i++){
                mp.put(i,new ArrayList<>());
            }
            for(int i=0;i<edges.length;i++){
                mp.get(edges[i][1]).add(edges[i][0]);
                mp.get(edges[i][0]).add(edges[i][1]);
            }
            return dfs(mp,source,destination,visited);
        }
        public static boolean dfs(Map<Integer,List<Integer>> edges, int source, int destination,Set<Integer> visited){
            if(source==destination){
                return true;
            }
            if(visited.contains(source)){
                return false;
            }
            visited.add(source);
            for(int neighbor: edges.get(source)){
                boolean temp = dfs(edges,neighbor,destination,visited);
                if(temp){
                    return true;
                }
            }
            return false;
        }

}
