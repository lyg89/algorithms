//Given two strings s and p, return an array of all the start indices of p's ana
//grams in s. You may return the answer in any order. 
//
// An Anagram is a word or phrase formed by rearranging the letters of a differe
//nt word or phrase, typically using all the original letters exactly once. 
//
// 
// Example 1: 
//
// 
//Input: s = "cbaebabacd", p = "abc"
//Output: [0,6]
//Explanation:
//The substring with start index = 0 is "cba", which is an anagram of "abc".
//The substring with start index = 6 is "bac", which is an anagram of "abc".
// 
//
// Example 2: 
//
// 
//Input: s = "abab", p = "ab"
//Output: [0,1,2]
//Explanation:
//The substring with start index = 0 is "ab", which is an anagram of "ab".
//The substring with start index = 1 is "ba", which is an anagram of "ab".
//The substring with start index = 2 is "ab", which is an anagram of "ab".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length, p.length <= 3 * 104 
// s and p consist of lowercase English letters. 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 596 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        Solution solution = new FindAllAnagramsInAString().new Solution();
        System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            Map<Character, Integer> window = new HashMap<>();
            Map<Character, Integer> needs = new HashMap<>();
            for (char c : p.toCharArray()) {
                needs.put(c, needs.getOrDefault(c, 0) + 1);
            }

            int l = 0;
            int r = 0;
            int valid = 0;
            List<Integer> res = new ArrayList<>();
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

                while ((r - l) >= p.length()) {
                    if (valid == needs.size() && !res.contains(l)) {
                        res.add(l);
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

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}