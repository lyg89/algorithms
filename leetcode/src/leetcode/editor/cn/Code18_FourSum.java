//Given an array nums of n integers, return an array of all the unique quadruple
//ts [nums[a], nums[b], nums[c], nums[d]] such that: 
//
// 
// 0 <= a, b, c, d < n 
// a, b, c, and d are distinct. 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// You may return the answer in any order. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,0,-1,0,-2,2], target = 0
//Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// Example 2: 
//
// 
//Input: nums = [2,2,2,2,2], target = 8
//Output: [[2,2,2,2]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 200 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// 
// Related Topics 数组 双指针 排序 
// 👍 1259 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code18_FourSum {
    public static void main(String[] args) {
        Solution solution = new Code18_FourSum().new Solution();
        System.out.println(solution.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(solution.fourSum(new int[]{2, 2, 2, 2, 2}, 8));
        System.out.println(solution.fourSum(new int[]{-2, -1, -1, 1, 1, 2, 2}, 0));


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            int n = nums.length;
            if (n < 4) {
                return res;
            }

            Arrays.sort(nums);
            for (int first = 0; first < n - 3; first++) {
                // 迭代到值相等元素，跳过处理
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }

                if ((long) nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > target) {
                    break;
                }

                if ((long) nums[first] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) {
                    continue;
                }

                for (int second = first + 1; second < n - 2; second++) {
                    if (second > first + 1 && nums[second] == nums[second - 1]) {
                        continue;
                    }
                    if ((long) nums[first] + nums[second] + nums[second + 1] + nums[second + 2] > target) {
                        break;
                    }
                    if ((long) nums[first] + nums[second] + nums[n - 1] + nums[n - 2] < target) {
                        continue;
                    }

                    int left = second + 1;
                    int right = n - 1;
                    while (left < right) {
                        long sum = nums[first] + nums[second] + nums[left] + nums[right];
                        if (sum == target) {
                            res.add(Arrays.asList(nums[first], nums[second], nums[left], nums[right]));

                            while (left < right && nums[left + 1] == nums[left]) {
                                left++;
                            }
                            left++;

                            while (left < right && nums[right - 1] == nums[right]) {
                                right--;
                            }
                            right--;
                        } else if (sum < target) {
                            left++;
                        } else {
                            right--;
                        }
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}