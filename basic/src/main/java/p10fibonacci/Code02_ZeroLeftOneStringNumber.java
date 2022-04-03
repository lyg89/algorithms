package p10fibonacci;

/**
 * @author liyaguang11
 * @date 2022/3/25
 */
public class Code02_ZeroLeftOneStringNumber {

    public static int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    public static int process(int i, int n) {
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
    }

    public static int getNum2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int tmp;
        for (int i = 2; i < n + 1; i++) {
            tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }

    public static int getNum3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    private static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }

        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = multiplyMatrix(res, tmp);
            }
            tmp = multiplyMatrix(tmp, tmp);
        }
        return res;
    }

    // 两个矩阵乘完之后的结果返回
    public static int[][] multiplyMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i != 21; i++) {
            System.out.println(getNum1(i));
            System.out.println(getNum2(i));
            System.out.println(getNum3(i));
            System.out.println("===================");
        }

    }
}
