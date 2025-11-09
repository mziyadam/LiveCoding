package DP;

import java.util.*;
public class KombinasiKembalianKoinTerkecil {

        public int coinChange(int[] coins, int amount) {
            int[] memo = new int[amount+1];
            Arrays.fill(memo,-2);
            return solve(coins,amount,memo);
        }
        public int solve(int[] coins, int amount, int[] memo) {
            if(amount<0) return -1;
            if(amount==0){
                return 0;
            }
            if(memo[amount]!=-2) return memo[amount];
            int minCount = Integer.MAX_VALUE;

            for(int coin:coins){
                int res = solve(coins,amount-coin, memo);
                if(res>=0&&res<minCount){
                    minCount = 1 + res;
                }
            }
            memo[amount]=(minCount==Integer.MAX_VALUE?-1:minCount);
            return memo[amount];
        }

}
