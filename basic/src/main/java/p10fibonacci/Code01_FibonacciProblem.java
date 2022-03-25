package p10fibonacci;

/**
 * @author liyaguang11
 * @date 2022/3/25
 */
public class Code01_FibonacciProblem {

    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int k1 = 1;
        int k2 = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = k1 + k2;
            k1 = k2;
            k2 = tmp;
        }
        return k2;
    }

    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int[][] base = {{1, 1}, {1, 0}};
        int[][] matrixPower = matrixPower(base, n - 2);
        return matrixPower[0][0] + matrixPower[1][0];
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
        int n = 19;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
        System.out.println("===");
    }
}
