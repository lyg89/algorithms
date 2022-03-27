//You are climbing a staircase. It takes n steps to reach the top. 
//
// Each time you can either climb 1 or 2 steps. In how many distinct ways can 
//you climb to the top? 
//
// 
// Example 1: 
//
// 
//Input: n = 2
//Output: 2
//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps
// 
//
// Example 2: 
//
// 
//Input: n = 3
//Output: 3
//Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 45 
// 
// Related Topics 记忆化搜索 数学 动态规划 👍 2299 👎 0


package leetcode.editor.cn;

public class ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int climbStairs(int n) {
            if (n < 1) {
                return 0;
            }
            if (n == 1 || n == 2) {
                return n;
            }

            int[][] base = {{1, 1}, {1, 0}};
            int[][] matrixPower = matrixPower(base, n - 2);
            return 2 * matrixPower[0][0] + 1 * matrixPower[1][0];
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