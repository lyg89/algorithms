//Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k
//]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0. 
//
// Notice that the solution set must not contain duplicate triplets. 
//
// 
// Example 1: 
// Input: nums = [-1,0,1,2,-1,-4]
//Output: [[-1,-1,2],[-1,0,1]]
// Example 2: 
// Input: nums = []
//Output: []
// Example 3: 
// Input: nums = [0]
//Output: []
// 
// 
// Constraints: 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 4800 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Code15_ThreeSum {
    public static void main(String[] args) {
        Solution solution = new Code15_ThreeSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {

            if (nums == null || nums.length == 0) {
                return Collections.emptyList();
            }

            Arrays.sort(nums);
            int n = nums.length;
            ArrayList<List<Integer>> res = new ArrayList<>();
            for (int first = 0; first < n; first++) {
                if (first > 0 && nums[first] == nums[first-1]) {
                    continue;
                }

                int target = -nums[first];
                int third = n - 1;
                for (int second = first + 1; second < third; second++) {
                    if (second != first + 1 && nums[second] == nums[second - 1]) {
                        continue;
                    }

                    while (nums[second] + nums[third] > target) {
                        third--;
                    }
                    if (second == third) {
                        break;
                    }
                    if (nums[second] + nums[third] == target) {
                        ArrayList<Integer> hit = new ArrayList<>(3);
                        hit.add(nums[first]);
                        hit.add(nums[second]);
                        hit.add(nums[third]);
                        res.add(hit);
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}