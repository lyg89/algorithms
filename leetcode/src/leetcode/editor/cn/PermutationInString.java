//Given two strings s1 and s2, return true if s2 contains a permutation of s1, o
//r false otherwise. 
//
// In other words, return true if one of s1's permutations is the substring of s
//2. 
//
// 
// Example 1: 
//
// 
//Input: s1 = "ab", s2 = "eidbaooo"
//Output: true
//Explanation: s2 contains one permutation of s1 ("ba").
// 
//
// Example 2: 
//
// 
//Input: s1 = "ab", s2 = "eidboaoo"
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 1 <= s1.length, s2.length <= 104 
// s1 and s2 consist of lowercase English letters. 
// 
// Related Topics 哈希表 双指针 字符串 滑动窗口 
// 👍 418 👎 0


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {
    public static void main(String[] args) {
        Solution solution = new PermutationInString().new Solution();
        System.out.println(solution.checkInclusion("ab", "eidbaooo"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            Map<Character, Integer> window = new HashMap<>();
            Map<Character, Integer> needs = new HashMap<>();
            for (char c : s1.toCharArray()) {
                needs.put(c, needs.getOrDefault(c, 0) + 1);
            }

            int l = 0;
            int r = 0;
            int valid = 0;
            while (r < s2.length()) {
                char c = s2.charAt(r);
                r++;
                if (needs.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (needs.get(c).equals(window.get(c))) {
                        valid++;
                    }
                }

                while (r - l >= s1.length()) {
                    if (valid == needs.size()) {
                        return true;
                    }

                    char d = s2.charAt(l);
                    l++;

                    if (needs.containsKey(d)) {
                        if (needs.get(d).equals(window.get(d))) {
                            valid--;
                        }
                        window.put(d, window.get(d) - 1);
                    }
                }
            }

            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}