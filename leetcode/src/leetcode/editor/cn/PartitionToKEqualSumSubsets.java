//Given an integer array nums and an integer k, return true if it is possible to
// divide this array into k non-empty subsets whose sums are all equal. 
//
// 
// Example 1: 
//
// 
//Input: nums = [4,3,2,3,5,2,1], k = 4
//Output: true
//Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,
//3) with equal sums.
// 
//
// Example 2: 
//
// 
//Input: nums = [1,2,3,4], k = 3
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 1 <= k <= nums.length <= 16 
// 1 <= nums[i] <= 104 
// The frequency of each element is in the range [1, 4]. 
// 
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩 
// 👍 402 👎 0


package leetcode.editor.cn;

public class PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        Solution solution = new PartitionToKEqualSumSubsets().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            if (sum % k != 0) {
                return false;
            }


            boolean[] used = new boolean[nums.length];
            int target = sum / k;
            return backtrack(k, 0, nums, 0, used, target);
        }

        private boolean backtrack(int k, int curBucketSum, int[] nums, int start, boolean[] used, int target) {
            if (k == 0) {
                return true;
            }

            if (curBucketSum == target) {
                return backtrack(k - 1, 0, nums, 0, used, target);
            }

            for (int i = start; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }

                if (curBucketSum + nums[i] > target) {
                    continue;
                }

                used[i] = true;
                if (backtrack(k, curBucketSum + nums[i], nums, i + 1, used, target)) {
                    return true;
                }

                used[i] = false;
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}