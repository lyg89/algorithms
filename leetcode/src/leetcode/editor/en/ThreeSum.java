package leetcode.editor.en;
//Given an array nums of n integers, are there elements a, b, c in nums such tha
//t a + b + c = 0? Find all unique triplets in the array which gives the sum of ze
//ro. 
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
// Related Topics Array Two Pointers 
// 👍 9215 👎 962

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class ThreeSum {

    private List<List<Integer>> twoSum(int[] nums, int start, int end, int target, int firstVal) {
        List<List<Integer>> answer = new ArrayList<>();
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum == target) {
                List<Integer> result = new ArrayList<>(3);
                result.add(firstVal);
                result.add(nums[start]);
                result.add(nums[end]);
                answer.add(result);
                while (start < end && nums[start] == nums[start + 1]) {
                    start++;
                }
                start++;
                while (start < end && nums[end] == nums[end + 1]) {
                    end--;
                }
                end--;
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        return answer;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            List<List<Integer>> result = twoSum(nums, i + 1, nums.length - 1, -nums[i], nums[i]);
            if (result.size() > 0) {
                answer.addAll(result);
            }
        }
        return answer;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
