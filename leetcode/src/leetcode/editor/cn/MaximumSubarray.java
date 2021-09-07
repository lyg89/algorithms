//Given an integer array nums, find the contiguous subarray (containing at least
// one number) which has the largest sum and return its sum. 
//
// A subarray is a contiguous part of an array. 
//
// 
// Example 1: 
//
// 
//Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//Output: 6
//Explanation: [4,-1,2,1] has the largest sum = 6.
// 
//
// Example 2: 
//
// 
//Input: nums = [1]
//Output: 1
// 
//
// Example 3: 
//
// 
//Input: nums = [5,4,-1,7,8]
//Output: 23
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 3 * 104 
// -105 <= nums[i] <= 105 
// 
//
// 
// Follow up: If you have figured out the O(n) solution, try coding another solu
//tion using the divide and conquer approach, which is more subtle. 
// Related Topics 数组 分治 动态规划 
// 👍 3663 👎 0


package leetcode.editor.cn;

public class MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length < 1) {
                return 0;
            }

            //int[] dp = new int[nums.length];
            //dp[0] = nums[0];

            // 空间优化，只应用前一个元素即可
            int dpPre = nums[0];
            int maxSub = nums[0];
            for (int i = 1; i < nums.length; i++) {
                //dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
                // 计算当前和：前序和小于0的情况下，丢弃前序元素，仅保留当前元素
                dpPre = Math.max(dpPre, 0) + nums[i];

                maxSub = Math.max(dpPre, maxSub);
            }
            return maxSub;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}