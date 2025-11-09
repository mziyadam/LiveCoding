package DP;

import java.util.*;
class KeuntunganMaksimalDariPencurianDiRumahTetanggaLingkaran {
    // Bottom Up
    // public int rob(int[] nums) {
    //     int n = nums.length;
    //     if (n == 1) return nums[0];
    //     if (n == 2) return Math.max(nums[0], nums[1]);

    //     // Case 1: exclude last house
    //     int[] dp = new int[n + 1];
    //     dp[1] = nums[0];
    //     dp[2] = Math.max(nums[0], nums[1]);

    //     for (int i = 3; i <= n - 1; i++) {
    //         int take = nums[i - 1] + ((i - 2) > 0 ? dp[i - 2] : 0);
    //         int skip = dp[i - 1];
    //         dp[i] = Math.max(take, skip);
    //     }

    //     int res1 = dp[n - 1];

    //     // Case 2: exclude first house
    //     dp = new int[n + 1];
    //     dp[1] = 0;
    //     dp[2] = nums[1];

    //     for (int i = 3; i <= n; i++) {
    //         int take = nums[i - 1] + ((i - 2) > 0 ? dp[i - 2] : 0);
    //         int skip = dp[i - 1];
    //         dp[i] = Math.max(take, skip);
    //     }

    //     return Math.max(dp[n], res1);
    // }


    // memorization
    int[] dpMemo = new int[101];  // Java equivalent of int dp[101] in C++

    int solve(int[] nums, int i, int n) {
        if (i > n) return 0;

        if (dpMemo[i] != -1) return dpMemo[i];

        int skip = solve(nums, i + 1, n);
        int steal = nums[i] + solve(nums, i + 2, n);

        return dpMemo[i] = Math.max(steal, skip);
    }

    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        Arrays.fill(dpMemo, -1);
        int take0thHouse = solve(nums, 0, n - 2);

        Arrays.fill(dpMemo, -1);
        int take1stHouse = solve(nums, 1, n - 1);

        return Math.max(take0thHouse, take1stHouse);
    }
}
