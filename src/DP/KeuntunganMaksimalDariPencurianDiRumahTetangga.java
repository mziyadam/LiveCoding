package DP;

public class KeuntunganMaksimalDariPencurianDiRumahTetangga {

        public int rob(int[] nums) {
            if(nums.length ==0) return 0;
            int prev1=0,prev2=0;
            for(int num:nums){
                int tmp = prev1;
                System.out.println("tmp/prev1 "+tmp);
                System.out.println("prev2+num "+(prev2+num));
                prev1=Math.max(prev2+num,prev1);
                prev2=tmp;
                System.out.println("new prev2 "+prev2);
            }
            return prev1;
        }

}
