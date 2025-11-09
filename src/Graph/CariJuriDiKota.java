package Graph;

public class CariJuriDiKota {
    public int findJudge(int n, int[][] trust) {
        int temp=trust[0][0];
        for(int i=1;i<trust.length;i++){
            if(trust[i][0]!=temp){
                return -1;
            }
        }
        return temp;
    }
}
