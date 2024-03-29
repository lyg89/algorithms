//The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibon
//acci sequence, such that each number is the sum of the two preceding ones, start
//ing from 0 and 1. That is, 
//
// 
//F(0) = 0, F(1) = 1
//F(n) = F(n - 1) + F(n - 2), for n > 1.
// 
//
// Given n, calculate F(n). 
//
// 
// Example 1: 
//
// 
//Input: n = 2
//Output: 1
//Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
// 
//
// Example 2: 
//
// 
//Input: n = 3
//Output: 2
//Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
// 
//
// Example 3: 
//
// 
//Input: n = 4
//Output: 3
//Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
// 
//
// 
// Constraints: 
//
// 
// 0 <= n <= 30 
// 
// Related Topics 递归 记忆化搜索 数学 动态规划 
// 👍 316 👎 0


package leetcode.editor.cn;

public class FibonacciNumber {
    public static void main(String[] args) {
        Solution solution = new FibonacciNumber().new Solution();

        System.out.println(solution.fib(0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // method 01
        //public int fib(int n) {
        //
        //    if (n <= 1) {
        //        return n;
        //    }
        //    int first = 1;
        //    int second = 1;
        //
        //    int tmp = 0;
        //    for (int i = 3; i <= n; i++) {
        //        tmp = first + second;
        //
        //        first = second;
        //        second = tmp;
        //    }
        //    return second;
        //}

        public int fib(int n) {
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

        private int[][] matrixPower(int[][] m, int p) {
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
        public int[][] multiplyMatrix(int[][] m1, int[][] m2) {
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
    }
//leetcode submit region end(Prohibit modification and deletion)

}