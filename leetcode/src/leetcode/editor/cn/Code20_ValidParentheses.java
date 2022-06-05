//Given a string s containing just the characters '(', ')', '{', '}', '[' and ']
//', determine if the input string is valid. 
//
// An input string is valid if: 
//
// 
// Open brackets must be closed by the same type of brackets. 
// Open brackets must be closed in the correct order. 
// 
//
// 
// Example 1: 
//
// 
//Input: s = "()"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: s = "()[]{}"
//Output: true
// 
//
// Example 3: 
//
// 
//Input: s = "(]"
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 104 
// s consists of parentheses only '()[]{}'. 
// 
// Related Topics 栈 字符串 
// 👍 3296 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Stack;

public class Code20_ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new Code20_ValidParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            if (s.length() % 2 == 1) {
                return false;
            }

            HashMap<Character, Character> map = new HashMap<>(3);
            map.put(')', '(');
            map.put(']', '[');
            map.put('}', '{');

            Stack<Character> stack = new Stack<>();
            for (char ch : s.toCharArray()) {
                if (map.containsKey(ch)) {
                    if (stack.isEmpty() || !stack.peek().equals(map.get(ch))) {
                        return false;
                    }
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }

            return stack.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}