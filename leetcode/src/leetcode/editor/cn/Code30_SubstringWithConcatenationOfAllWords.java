//You are given a string s and an array of strings words of the same length. 
//Return all starting indices of substring(s) in s that is a concatenation of each 
//word in words exactly once, in any order, and without any intervening characters. 
//
//
// You can return the answer in any order. 
//
// 
// Example 1: 
//
// 
//Input: s = "barfoothefoobarman", words = ["foo","bar"]
//Output: [0,9]
//Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" 
//respectively.
//The output order does not matter, returning [9,0] is fine too.
// 
//
// Example 2: 
//
// 
//Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//Output: []
// 
//
// Example 3: 
//
// 
//Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//Output: [6,9,12]
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁴ 
// s consists of lower-case English letters. 
// 1 <= words.length <= 5000 
// 1 <= words[i].length <= 30 
// words[i] consists of lower-case English letters. 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 782 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code30_SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        Solution solution = new Code30_SubstringWithConcatenationOfAllWords().new Solution();
        System.out.println(solution.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            // 定义返回结果变量
            List<Integer> res = new ArrayList<>();
            if (s == null || words == null || words.length == 0) {
                return res;
            }

            int num = words[0].length();
            int wl = words.length;
            int sl = s.length();

            for (int i = 0; i < num; i++) {
                if (i + wl * num > sl) {
                    break;
                }

                // 构建窗口，生成diff
                Map<String, Integer> diff = new HashMap<>();
                for (int start = i; start < wl * num; start = start + num) {
                    String word = s.substring(start, start + num);
                    diff.put(word, diff.getOrDefault(word, 0) + 1);
                }

                // 更新diff
                for (String word : words) {
                    diff.put(word, diff.getOrDefault(word, 0) - 1);

                    if (diff.get(word) == 0) {
                        diff.remove(word);
                    }
                }

                // 滑动窗口，更新diff
                for (int start = i; start < sl - wl * num + 1; start = start + num) {
                    // 当大于i时才滑动
                    if (start != i) {
                        String word = s.substring(start + wl * num - num, start + wl * num);
                        diff.put(word, diff.getOrDefault(word, 0) + 1);
                        if (diff.get(word) == 0) {
                            diff.remove(word);
                        }

                        String old = s.substring(start - num, start);
                        diff.put(old, diff.getOrDefault(old, 0) - 1);
                        if (diff.get(old) == 0) {
                            diff.remove(old);
                        }
                    }

                    if (diff.isEmpty()) {
                        res.add(start);
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}