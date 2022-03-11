package p8recursive_and_dp;

/**
 * @author liyaguang11
 * @date 2022/3/10
 */
public class Code10_LongestCommonSubsequence {

    public static int commonSubString(String text1, String text2) {
        if (text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }

        char[] array1 = text1.toCharArray();
        char[] array2 = text2.toCharArray();
        int m = array1.length;
        int n = array2.length;
        int[][] dp = new int[m][n];

        if (array1[0] == array2[0]) {
            dp[0][0] = 1;
        }
        // fill up the first row
        for (int i = 1; i < n; i++) {
            if (dp[0][i - 1] == 1 || array1[0] == array2[i]) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            if (dp[i - 1][0] == 1 || array1[i] == array2[0]) {
                dp[i][0] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (array1[i] == array2[j]) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(commonSubString("abc", "def"));
    }
}
