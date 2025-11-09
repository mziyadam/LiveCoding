package DP;

public class ApakahSubArrayBisaSetaraJumlahnya {

    public boolean canPartition(int[] nums) {
        int tot = 0;
        for(int num : nums) tot += num;

        if(tot % 2 != 0) return false;
        int target = tot / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for(int num : nums) {
            for(int i = target; i >= num; i--) {
                if(dp[i - num]) dp[i] = true;
            }
            if(dp[target]) return true; //found! early exit.
        }

        return dp[target];
    }
}
