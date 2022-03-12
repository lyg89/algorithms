package p8recursive_and_dp;

// 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
public class Code11_PalindromeSubsequence {

    public static int lpsl1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    private static int f(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }

        int p1 = f(str, L, R - 1);
        int p2 = f(str, L + 1, R);
        int p3 = str[L] == str[R] ? f(str, L + 1, R - 1) + 2 : f(str, L + 1, R - 1);
        return Math.max(Math.max(p1, p2), p3);
    }

    public static int lps(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        for (int L = n - 3; L >= 0; L--) {
            for (int R = L + 2; R < n; R++) {
                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
                if (str[L] == str[R]) {
                    dp[L][R] = Math.max(dp[L + 1][R - 1] + 2, dp[L][R]);
                }
            }
        }
        return dp[0][n - 1];
    }

}
