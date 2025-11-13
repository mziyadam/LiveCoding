package Graph;

import java.util.*;

class ApaRuteCourseMasukAkalUntukDiselesaikanSyaratnya {
    public int[] findOrder(int n, int[][] prerequisites) {

        List<Integer>[] adj = new List[n];
        int[] indegree = new int[n];
        List<Integer> ans = new ArrayList<>();
        for(int[] pair: prerequisites){
            int course= pair[0], prerequisite=pair[1];
            if(adj[prerequisite]==null){
                adj[prerequisite]=new ArrayList<>();
            }
            adj[prerequisite].add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(indegree[i] ==0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int current = queue.poll();
            ans.add(current);
            if(adj[current]!=null){
                for(int next:adj[current]){
                    indegree[next]--;
                    if(indegree[next]==0){
                        queue.offer(next);
                    }
                }
            }
        }
        if(ans.size()==n)
            return ans.stream().mapToInt(i -> i).toArray();
        return new int[0];
    }
}
