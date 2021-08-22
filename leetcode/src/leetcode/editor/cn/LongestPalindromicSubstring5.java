package leetcode.editor.cn;//Given a string s, return the longest palindromic substring in s.
//
// 
// Example 1: 
//
// 
//Input: s = "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: s = "cbbd"
//Output: "bb"
// 
//
// Example 3: 
//
// 
//Input: s = "a"
//Output: "a"
// 
//
// Example 4: 
//
// 
//Input: s = "ac"
//Output: "a"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consist of only digits and English letters. 
// 
// Related Topics 字符串 动态规划 
// 👍 3997 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class LongestPalindromicSubstring5 {


    class Solution {
        public String longestPalindrome(String s) {
            String res = "";
            for (int i = 0; i < s.length(); i++) {
                // 找到以 s[i] 为中心的回文串
                String s1 = palindrome(s, i, i);
                // 找到以 s[i] 和 s[i+1] 为中心的回文串
                String s2 = palindrome(s, i, i + 1);
                res = res.length() > s1.length() ? res : s1;
                res = res.length() > s2.length() ? res : s2;
            }
            return res;
        }

        public String palindrome(String s, int l, int r) {
            while (l >= 0 && r < s.length()
                    && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            // 注意这里的下标位置不要越界
            return s.substring(l + 1, r);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
