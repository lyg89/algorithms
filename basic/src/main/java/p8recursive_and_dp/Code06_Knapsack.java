package p8recursive_and_dp;

/**
 * @author liyaguang11
 * @date 2022/3/2
 */
public class Code06_Knapsack {

    public static int maxValue(int[] w, int[] v, int bag) {
        if (w.length == 0) {
            return 0;
        }

        return process(w, v, 0, bag);
    }

    private static int process(int[] w, int[] v, int index, int rest) {
        // warn：rest is negative basic condition occur
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }

        int no = process(w, v, index + 1, rest);
        int yes = process(w, v, index + 1, rest - w[index]);
        // check valid value
        yes = yes == -1 ? 0 : yes + v[index];
        return Math.max(no, yes);
    }

    private static int dpWay1(int[] w, int[] v, int bag) {
        if (w.length == 0) {
            return 0;
        }
        int n = w.length;
        int[][] dp = new int[n + 1][bag + 1];

        // dp[n][x] = 0 initialize with the java create array
        for (int index = n-1; index >=0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int no = dp[index+1][rest];
                int yes = -1;
                if (rest - w[index] >= 0) {
                    yes = dp[index + 1][rest - w[index]] + v[index];
                }
                dp[index][rest] = Math.max(no, yes);
            }

        }

        return dp[0][bag];
    }

    public static int dpWay(int[] w, int[] v, int bag) {
        int N = w.length;
        if (N == 0) {
            return 0;
        }

        int[][] dp = new int[N + 1][bag + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= bag; j++) {
                int no = dp[i + 1][j];
                int yes = -1;
                if (j - w[i] >= 0) {
                    yes = dp[i + 1][j - w[i]] + v[i];
                }
                dp[i][j] = Math.max(no, yes);
            }
        }

        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));
        System.out.println(dpWay1(weights, values, bag));
    }
}
