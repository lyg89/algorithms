//Given an integer array nums of length n and an integer target, find three inte
//gers in nums such that the sum is closest to target. 
//
// Return the sum of the three integers. 
//
// You may assume that each input would have exactly one solution. 
//
// 
// Example 1: 
//
// 
//Input: nums = [-1,2,1,-4], target = 1
//Output: 2
//Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
// 
//
// Example 2: 
//
// 
//Input: nums = [0,0,0], target = 1
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 3 <= nums.length <= 1000 
// -1000 <= nums[i] <= 1000 
// -104 <= target <= 104 
// 
// Related Topics 数组 双指针 排序 
// 👍 1152 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

public class Code16_ThreeSumClosest {
    public static void main(String[] args) {
        Solution solution = new Code16_ThreeSumClosest().new Solution();
        System.out.println(solution.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int ans = 10000000;
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int left = i + 1;
                int right = n - 1;
                while (left < right) {
                    int curSum = nums[i] + nums[left] + nums[right];
                    if (curSum == target) {
                        return target;
                    }

                    ans = Math.abs(ans - target) < Math.abs(curSum - target) ? ans : curSum;

                    if (curSum < target) {
                        int l0 = left + 1;
                        while (l0 < right && nums[l0 + 1] == nums[left]) {
                            l0++;
                        }
                        left = l0;
                    } else {
                        int r0 = right - 1;
                        while (left < r0 && nums[r0 - 1] == nums[right]) {
                            r0--;
                        }
                        right = r0;
                    }
                }

            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}