//Given a string containing digits from 2-9 inclusive, return all possible lette
//r combinations that the number could represent. Return the answer in any order. 
//
//
// A mapping of digit to letters (just like on the telephone buttons) is given b
//elow. Note that 1 does not map to any letters. 
//
// 
//
// 
// Example 1: 
//
// 
//Input: digits = "23"
//Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// Example 2: 
//
// 
//Input: digits = ""
//Output: []
// 
//
// Example 3: 
//
// 
//Input: digits = "2"
//Output: ["a","b","c"]
// 
//
// 
// Constraints: 
//
// 
// 0 <= digits.length <= 4 
// digits[i] is a digit in the range ['2', '9']. 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1920 👎 0

package leetcode.editor.cn;

import java.util.*;

public class Code17_LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new Code17_LetterCombinationsOfAPhoneNumber().new Solution();
        System.out.println(solution.letterCombinations("23"));
        System.out.println(solution.letterCombinations("2"));
        System.out.println(solution.letterCombinations(""));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() == 0) {
                return new ArrayList<>();
            }
            Map<Character, String> map = new HashMap<>(8);
            map.put('2', "abc");
            map.put('3', "def");
            map.put('4', "ghi");
            map.put('5', "jkl");
            map.put('6', "mno");
            map.put('7', "pqrs");
            map.put('8', "tuv");
            map.put('9', "wxyz");

            List<String> result = new LinkedList<>();
            recursion(0, "", result, digits, map);
            return result;
        }

        private void recursion(int curIdx, String curStr, List<String> result, String digits, Map<Character, String> map) {
            if (curIdx == digits.length()) {
                result.add(curStr);
                return;
            }

            char c = digits.charAt(curIdx);
            char[] mapLetter = map.get(c).toCharArray();
            for (char next : mapLetter) {
                recursion(curIdx + 1, curStr + next, result, digits, map);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}