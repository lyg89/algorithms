//Write a function to find the longest common prefix string amongst an array of 
//strings. 
//
// If there is no common prefix, return an empty string "". 
//
// 
// Example 1: 
//
// 
//Input: strs = ["flower","flow","flight"]
//Output: "fl"
// 
//
// Example 2: 
//
// 
//Input: strs = ["dog","racecar","car"]
//Output: ""
//Explanation: There is no common prefix among the input strings.
// 
//
// 
// Constraints: 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] consists of only lower-case English letters. 
// 
// Related Topics 字符串 
// 👍 2248 👎 0

package leetcode.editor.cn;

public class Code14_LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new Code14_LongestCommonPrefix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            int minLength = Integer.MAX_VALUE;
            for (String str : strs) {
                if (str.length() < minLength) {
                    minLength = str.length();
                }
            }

            int low = 0;
            int high = minLength;
            while (low < high) {
                int mid = (high - low + 1) / 2 + low;
                if (isCommonPrefix(strs, mid)) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            return strs[0].substring(0, low);
        }

        private boolean isCommonPrefix(String[] strs, int end) {
            char[] basic = strs[0].substring(0, end).toCharArray();
            for (int i = 1; i < strs.length; i++) {
                for (int j = 0; j < end; j++) {
                    if (basic[j] != strs[i].charAt(j)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}