package p8recursive_and_dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyaguang11
 * @date 2022/3/10
 */
public class Code09_StickersToSpellWord {

    public static int minStickers1(String[] stickers, String target) {
        int n = stickers.length;
        int[][] stickersCount = new int[n][26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                stickersCount[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process1(stickersCount, target, dp) == Integer.MAX_VALUE ? -1 : dp.get(target);
    }

    private static int process1(int[][] stickersCount, String t, Map<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }

        int[] targetCount = new int[26];
        for (char c : t.toCharArray()) {
            targetCount[c - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int[] stickerCount : stickersCount) {
            // 避免无限递归，栈溢出
            if (stickerCount[t.toCharArray()[0] - 'a'] <= 0) {
                continue;
            }

            StringBuilder sbRest = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (targetCount[i] > 0) {
                    int count = targetCount[i] - stickerCount[i];
                    for (int j = 0; j < count; j++) {
                        sbRest.append((char)('a' + i));
                    }
                }
            }

            min = Math.min(min, process1(stickersCount, sbRest.toString(), dp));
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minStickers1(new String[]{"with", "example", "science"}, "thehat"));
    }
}
