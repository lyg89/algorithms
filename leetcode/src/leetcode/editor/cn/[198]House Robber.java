package leetcode.editor.cn;
//You are a professional robber planning to rob houses along a street. Each hous
//e has a certain amount of money stashed, the only constraint stopping you from r
//obbing each of them is that adjacent houses have security system connected and i
//t will automatically contact the police if two adjacent houses were broken into 
//on the same night. 
//
// Given a list of non-negative integers representing the amount of money of eac
//h house, determine the maximum amount of money you can rob tonight without alert
//ing the police. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//             Total amount you can rob = 1 + 3 = 4.
// 
//
// Example 2: 
//
// 
//Input: nums = [2,7,9,3,1]
//Output: 12
//Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 
//(money = 1).
//             Total amount you can rob = 2 + 9 + 1 = 12.
// 
//
// 
// Constraints: 
//
// 
// 0 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 动态规划 
// 👍 978 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[1];
        }

        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


// f[i][0] = f[i-1][1]
// f[i][1] = f[i-1][0]

//1. 暴力法
//2. DP
//① 重复子问题
//② 状态数组
//③ 递推方程

// 如果用a[i]表示从0到第i个房子所能偷取的最大值，那么再考虑递推方程的话是无法得出的，因为不能偷连续两个房子，
// a[i-1]不确定有没有偷第i-1个房子，因此a[i] = a[i-1] + nums[i] 可能不成立。
// 此时我们可以通过增加一个维度确定是否要偷第 i 间房子。此时可得出：
// a[i][0] = Max(a[i-1][0], a[i-1][1])
// a[i][1] = a[i-1][0] + nums[1]
// 两者取较大值，即为到第i间房，所能偷取的最高金额。


// 对状态数组的重新定义优化：第i天必偷的情况
// a[i] = max((a[i-2] + nums[i]), a[i-1])，最后的结果为所有情况中的最大值max(a)