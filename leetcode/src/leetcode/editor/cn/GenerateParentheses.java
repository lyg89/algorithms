//Given n pairs of parentheses, write a function to generate all combinations of
// well-formed parentheses. 
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
// Related Topics 字符串 动态规划 回溯 
// 👍 2020 👎 0


package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public List<String> generateParenthesis(int n) {

            List<String> res = new LinkedList<>();
            this.generate(res, n, 0, 0, "");
            return res;
        }


        private void generate(List<String> res, int n, int leftNum, int rightNum, String curStr) {
            if (leftNum >= n && rightNum >= n) {
                res.add(curStr);
                return;
            }

            if (leftNum < n) {
                generate(res, n, leftNum + 1, rightNum, curStr + "(");
            }

            if (rightNum < leftNum) {
                generate(res, n, leftNum, rightNum + 1, curStr + ")");
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}