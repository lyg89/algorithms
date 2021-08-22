package leetcode.editor.cn;
//Given an array of integers nums and an integer k, return the total number of c
//ontinuous subarrays whose sum equals to k. 
//
// 
// Example 1: 
// Input: nums = [1,1,1], k = 2
//Output: 2
// Example 2: 
// Input: nums = [1,2,3], k = 3
//Output: 2
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 2 * 104 
// -1000 <= nums[i] <= 1000 
// -107 <= k <= 107 
// 
// Related Topics 数组 哈希表 前缀和 
// 👍 1051 👎 0

public class SubarraySumEqualsK560 {

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public int subarraySum(int[] nums, int k) {
            int[] preSum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }
            int count = 0;
            for (int i = 1; i < preSum.length; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (preSum[i] - preSum[j] == k) {
                        count++;
                    }
                }
            }
            return count;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subarraySum(new int[]{-1, -1, 1}, 0));
    }
}

//leetcode submit region end(Prohibit modification and deletion)
