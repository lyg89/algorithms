//Given an array of integers nums and an integer limit, return the size of the 
//longest non-empty subarray such that the absolute difference between any two 
//elements of this subarray is less than or equal to limit. 
//
// 
// Example 1: 
//
// 
//Input: nums = [8,2,4,7], limit = 4
//Output: 2 
//Explanation: All subarrays are: 
//[8] with maximum absolute diff |8-8| = 0 <= 4.
//[8,2] with maximum absolute diff |8-2| = 6 > 4. 
//[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
//[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
//[2] with maximum absolute diff |2-2| = 0 <= 4.
//[2,4] with maximum absolute diff |2-4| = 2 <= 4.
//[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
//[4] with maximum absolute diff |4-4| = 0 <= 4.
//[4,7] with maximum absolute diff |4-7| = 3 <= 4.
//[7] with maximum absolute diff |7-7| = 0 <= 4. 
//Therefore, the size of the longest subarray is 2.
// 
//
// Example 2: 
//
// 
//Input: nums = [10,1,2,4,7,2], limit = 5
//Output: 4 
//Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute 
//diff is |2-7| = 5 <= 5.
// 
//
// Example 3: 
//
// 
//Input: nums = [4,2,2,2,4,4,2,2], limit = 0
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁹ 
// 0 <= limit <= 10⁹ 
// 
// Related Topics 队列 数组 有序集合 滑动窗口 单调队列 堆（优先队列） 👍 234 👎 0


package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public static void main(String[] args) {
        Solution solution = new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestSubarray(int[] nums, int limit) {
            if (nums == null || nums.length == 0 || limit < 0) {
                return 0;
            }
            int N = nums.length;
            Deque<Integer> minDeque = new LinkedList<>();
            Deque<Integer> maxDeque = new LinkedList<>();

            int result = 0;
            int R = 0;
            for (int L = 0; L < N; L++) {
                while (R < N) {
                    while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[R]) {
                        minDeque.pollLast();
                    }
                    minDeque.addLast(R);

                    while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[R]) {
                        maxDeque.pollLast();
                    }
                    maxDeque.addLast(R);

                    if ((nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()]) > limit) {
                        break;
                    }
                    R++;
                }
                result = Math.max(result, R - L);
                if (maxDeque.peekFirst() == L) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == L) {
                    minDeque.pollFirst();
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}