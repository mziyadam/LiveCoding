package Graph;

public class IsiJarakKe0DFS {
        public int[][] updateMatrix(int[][] mat) {
            int counter=0;
            dfs(mat,mat.length/2,mat[0].length/2,counter);
            return mat;
        }
        public void dfs(int[][] mat,int r,int c,int counter){
            if(mat[r][c]!=0){
                counter++;
                if(r>=1){
                    dfs(mat,r-1,c,counter);
                }
                if(c>=1){
                    dfs(mat,r,c-1,counter);
                }

                if(r+1<mat.length){
                    dfs(mat,r+1,c,counter);
                }
                if(c+1<mat[0].length){
                    dfs(mat,r,c+1,counter);
                }
            }
            mat[r][c]=counter;
        }

}
