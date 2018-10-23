import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given a string, find the length of the longest substring without repeating characters.
 * @author: liyaguang
 * @create: 2018-10-22 13:00
 **/
public class S3LongestSubstring {

    public static void main(String[] args) {
        System.out.println("返回结果：" + new S3LongestSubstring().lengthOfLongestSubstring("abcabcbb"));
        System.out.println("返回结果：" + new S3LongestSubstring().lengthOfLongestSubstring("bbbbb"));
        System.out.println("返回结果：" + new S3LongestSubstring().lengthOfLongestSubstring("pwwkew"));
        System.out.println("返回结果：" + new S3LongestSubstring().lengthOfLongestSubstring("aab"));
        System.out.println("返回结果：" + new S3LongestSubstring().lengthOfLongestSubstring("dvdf"));
        System.out.println("返回结果：" + new S3LongestSubstring().lengthOfLongestSubstring("abba"));
    }

    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        if (!"".equals(s)) {
            Map<String, Integer> uniqueSubStr = new HashMap<>(s.length());
            String[] arrArg = s.split("");
            int startIdx = 0;
            for (int i = 0; i < arrArg.length; i++) {
                if (uniqueSubStr.keySet().contains(arrArg[i]) && startIdx <= uniqueSubStr.get(arrArg[i])) {
                    String repeatChar = arrArg[i];
                    startIdx = uniqueSubStr.get(repeatChar) + 1;
                    uniqueSubStr.put(arrArg[i], i);
                } else {
                    uniqueSubStr.put(arrArg[i], i);
                }

                if ((i - startIdx + 1) > result) {
                    result = i - startIdx + 1;
                }
            }
        }
        return result;
    }
}
