//Given two integers dividend and divisor, divide two integers without using 
//multiplication, division, and mod operator. 
//
// The integer division should truncate toward zero, which means losing its 
//fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be 
//truncated to -2. 
//
// Return the quotient after dividing dividend by divisor. 
//
// Note: Assume we are dealing with an environment that could only store 
//integers within the 32-bit signed integer range: [−2³¹, 2³¹ − 1]. For this problem, if 
//the quotient is strictly greater than 2³¹ - 1, then return 2³¹ - 1, and if the 
//quotient is strictly less than -2³¹, then return -2³¹. 
//
// 
// Example 1: 
//
// 
//Input: dividend = 10, divisor = 3
//Output: 3
//Explanation: 10/3 = 3.33333.. which is truncated to 3.
// 
//
// Example 2: 
//
// 
//Input: dividend = 7, divisor = -3
//Output: -2
//Explanation: 7/-3 = -2.33333.. which is truncated to -2.
// 
//
// 
// Constraints: 
//
// 
// -2³¹ <= dividend, divisor <= 2³¹ - 1 
// divisor != 0 
// 
// Related Topics 位运算 数学 👍 932 👎 0

package leetcode.editor.cn;

public class Code29_DivideTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new Code29_DivideTwoIntegers().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int divide(int dividend, int divisor) {
            if (dividend == 0) return 0;
            if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

            int sign = 1;
            if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
                sign = -1;
            }

            // 全部转为负值，避免溢出情况
            dividend = dividend > 0 ? -dividend : dividend;
            divisor = divisor > 0 ? -divisor : divisor;

            return sign * helper(dividend, divisor);
        }

        public int helper(int dividend, int divisor) {
            // dividend = -1, divisor =-10
            if (dividend >= divisor) {
                return dividend == divisor ? 1 : 0;
            }

            int result = 0;

            // 至少有一个满足要求
            int count = 1;
            int tempDivisor = divisor;
            while (dividend < tempDivisor && tempDivisor < 0) {
                dividend -= tempDivisor;
                result += count;

                tempDivisor += tempDivisor;
                count += count;
            }

            return result + helper(dividend, divisor);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}