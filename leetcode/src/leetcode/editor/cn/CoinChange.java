//You are given an integer array coins representing coins of different denominat
//ions and an integer amount representing a total amount of money. 
//
// Return the fewest number of coins that you need to make up that amount. If th
//at amount of money cannot be made up by any combination of the coins, return -1.
// 
//
// You may assume that you have an infinite number of each kind of coin. 
//
// 
// Example 1: 
//
// 
//Input: coins = [1,2,5], amount = 11
//Output: 3
//Explanation: 11 = 5 + 5 + 1
// 
//
// Example 2: 
//
// 
//Input: coins = [2], amount = 3
//Output: -1
// 
//
// Example 3: 
//
// 
//Input: coins = [1], amount = 0
//Output: 0
// 
//
// Example 4: 
//
// 
//Input: coins = [1], amount = 1
//Output: 1
// 
//
// Example 5: 
//
// 
//Input: coins = [1], amount = 2
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics 广度优先搜索 数组 动态规划 
// 👍 1463 👎 0


package leetcode.editor.cn;

public class CoinChange {
    public static void main(String[] args) {
        Solution solution = new CoinChange().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int coinChange(int[] coins, int amount) {
            //int[] dp = new int[amount + 1];
            //Arrays.fill(dp, amount + 1);
            //dp[0] = 0;
            //
            //for (int i = 1; i < dp.length; i++) {
            //    for (int coin : coins) {
            //        if (i - coin < 0) {
            //            continue;
            //        }
            //        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            //    }
            //}
            //
            //return dp[amount] == amount + 1 ? -1 : dp[amount];

            //int[] dp = new int[amount + 1];
            //dp[0] = 0;
            //
            //for (int i = 1; i < dp.length; i++) {
            //    dp[i] = Integer.MAX_VALUE;
            //    for (int coin : coins) {
            //        if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
            //            dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            //        }
            //    }
            //}
            //
            //return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];

            memo = new int[amount];
            return dp(coins, amount);
        }

        int[] memo;

        private int dp(int[] coins, int amount) {
            if (amount == 0) return 0;
            if (amount < 0) return -1;

            if (memo[amount - 1] != 0) {
                return memo[amount - 1];
            }

            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                int subRes = dp(coins, amount - coin);
                if (subRes == -1) continue;

                res = Math.min(res, subRes + 1);
            }

            memo[amount - 1] = res == Integer.MAX_VALUE ? -1 : res;
            return memo[amount - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}