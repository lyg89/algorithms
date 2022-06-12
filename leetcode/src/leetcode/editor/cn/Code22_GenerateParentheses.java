//Given n pairs of parentheses, write a function to generate all combinations 
//of well-formed parentheses. 
//
// 
// Example 1: 
// Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
// Example 2: 
// Input: n = 1
//Output: ["()"]
// 
// 
// Constraints: 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 👍 2693 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

public class Code22_GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new Code22_GenerateParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new LinkedList<>();
            recursive(0, 0, n, res, "");
            return res;
        }

        private void recursive(int leftNum, int rightNum, int n, List<String> res, String curStr) {
            if (leftNum >= n && rightNum >= n) {
                res.add(curStr);
                return;
            }
            if (leftNum < n) {
                recursive(leftNum + 1, rightNum, n, res, curStr + "(");
            }
            if (rightNum < leftNum) {
                recursive(leftNum, rightNum + 1, n, res, curStr + ")");
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}