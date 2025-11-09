package Graph;

import java.util.HashMap;
import java.util.Map;

public class CariBagianTengahGraphBFS {
    public int findCenter(int[][] edges) {
        Map<Integer,Integer> kv=new HashMap();
        for(int i=0;i<edges.length;i++){
            if(kv.get(edges[i][0])!=null){
                int temp = kv.get(edges[i][0]);
                temp++;
                kv.put(edges[i][0],temp);
            }else{
                kv.put(edges[i][0],1);
            }
            if(kv.get(edges[i][1])!=null){
                int temp = kv.get(edges[i][1]);
                temp++;
                kv.put(edges[i][1],temp);
            }else{
                kv.put(edges[i][1],1);
            }
        }
        int mid=0,res=0;
        for(Integer key:kv.keySet()){
            if(kv.get(key)>mid){
                mid=kv.get(key);
                res=key;
            }
        }
        return res;
    }
}
