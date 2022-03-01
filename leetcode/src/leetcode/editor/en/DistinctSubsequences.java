//Given two strings s and t, return the number of distinct subsequences of s 
//which equals t. 
//
// A string's subsequence is a new string formed from the original string by 
//deleting some (can be none) of the characters without disturbing the remaining 
//characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while 
//"AEC" is not). 
//
// The test cases are generated so that the answer fits on a 32-bit signed 
//integer. 
//
// 
// Example 1: 
//
// 
//Input: s = "rabbbit", t = "rabbit"
//Output: 3
//Explanation:
//As shown below, there are 3 ways you can generate "rabbit" from S.
//rabbbit
//rabbbit
//rabbbit
// 
//
// Example 2: 
//
// 
//Input: s = "babgbag", t = "bag"
//Output: 5
//Explanation:
//As shown below, there are 5 ways you can generate "bag" from S.
//babgbag
//babgbag
//babgbag
//babgbag
//babgbag 
//
// 
// Constraints: 
//
// 
// 1 <= s.length, t.length <= 1000 
// s and t consist of English letters. 
// 
// Related Topics String Dynamic Programming 👍 3108 👎 138


package leetcode.editor.cn;


import java.util.ArrayList;

public class DistinctSubsequences {
    public static void main(String[] args) {
        Solution solution = new DistinctSubsequences().new Solution();
        System.out.println(solution.numDistinct("babgbag", "bag"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private int count = 0;
        public int numDistinct(String s, String t) {
            //this.process(s, 0, "", t);
            System.out.println("count: " + count);
            return count;
        }

        //private void process(String s, int index, String curStr, String t) {
        //    if (curStr.equals(t)) {
        //        count++;
        //        return;
        //    }
        //    if (index == s.length()) {
        //        return;
        //    }
        //
        //    if (curStr.length() > t.length()) {
        //        return;
        //    }
        //
        //
        //    process(s, index + 1, curStr, t);
        //    process(s, index + 1, curStr + s.charAt(index), t);
        //}
    }
//leetcode submit region end(Prohibit modification and deletion)

}