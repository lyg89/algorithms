//Given two strings s and t of lengths m and n respectively, return the minimum 
//window substring of s such that every character in t (including duplicates) is i
//ncluded in the window. If there is no such substring, return the empty string ""
//. 
//
// The testcases will be generated such that the answer is unique. 
//
// A substring is a contiguous sequence of characters within the string. 
//
// 
// Example 1: 
//
// 
//Input: s = "ADOBECODEBANC", t = "ABC"
//Output: "BANC"
//Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' fr
//om string t.
// 
//
// Example 2: 
//
// 
//Input: s = "a", t = "a"
//Output: "a"
//Explanation: The entire string s is the minimum window.
// 
//
// Example 3: 
//
// 
//Input: s = "a", t = "aa"
//Output: ""
//Explanation: Both 'a's from t must be included in the window.
//Since the largest window of s only has one 'a', return empty string.
// 
//
// 
// Constraints: 
//
// 
// m == s.length 
// n == t.length 
// 1 <= m, n <= 105 
// s and t consist of uppercase and lowercase English letters. 
// 
//
// 
//Follow up: Could you find an algorithm that runs in O(m + n) time? Related Top
//ics 哈希表 字符串 滑动窗口 
// 👍 1307 👎 0


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring().new Solution();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minWindow(String s, String t) {
            Map<Character, Integer> window = new HashMap<>();
            Map<Character, Integer> needs = new HashMap<>();
            for (char c : t.toCharArray()) {
                needs.put(c, needs.getOrDefault(c, 0) + 1);
            }

            int l = 0;
            int r = 0;
            int valid = 0;
            int len = Integer.MAX_VALUE, start = 0;
            while (r < s.length()) {
                char c = s.charAt(r);
                r++;
                if (needs.containsKey(c)) {
                    // 先添加到window中，然后再与needs中数量比较
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (needs.get(c).equals(window.get(c))) {
                        valid++;
                    }
                }

                while (valid == needs.size()) {
                    if ((r - l) < len) {
                        len = r - l;
                        start = l;
                    }
                    char d = s.charAt(l);
                    l++;

                    if (needs.containsKey(d)) {
                        if (needs.get(d).equals(window.get(d))) {
                            valid--;
                        }
                        // 注意这里先进行上面的比较，然后window再自减
                        window.put(d, window.get(d) - 1);
                    }
                }
            }

            return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}