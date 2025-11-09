package BinarySearch;

import java.util.PriorityQueue;

public class ElemenTerkecilKesekianDiMatrixTerurut {

        public static int kthSmallest(int[][] matrix, int k) {
            int m=matrix.length,n=matrix[0].length;
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2,o1));
            for(int i=0;i<m;++i){
                for(int j=0;j<n;++j){
                    maxHeap.offer(matrix[i][j]);
                    if(maxHeap.size()>k){
                        maxHeap.poll();
                    }
                }
            }
            return maxHeap.poll();
        }
}
