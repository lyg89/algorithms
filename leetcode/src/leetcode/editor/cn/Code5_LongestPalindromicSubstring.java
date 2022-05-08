//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 5190 👎 0

package leetcode.editor.cn;

public class Code5_LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new Code5_LongestPalindromicSubstring().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            String res = "";
            for (int i = 0; i < s.length(); i++) {
                String p1 = palindrome(s, i, i);
                String p2 = palindrome(s, i, i+1);
                res = res.length() > p1.length() ? res : p1;
                res = res.length() > p2.length() ? res : p2;
            }
            return res;
        }

        private String palindrome(String s, int l, int r){
            while (l >= 0 && r <= s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            return s.substring(l, r);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}