package p8recursive_and_dp;

public class Code08_NQueens {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }

    private static int process1(int curRow, int[] record, int n) {
        if (curRow == n) {
            return 1;
        }
        int num = 0;
        for (int curColumn = 0; curColumn < n; curColumn++) {
            if (isValidate(record, curRow, curColumn)) {
                record[curRow] = curColumn;
                num += process1(curRow + 1, record, n);
            }
        }

        return num;
    }

    public static int num2(int n) {
        if (n < 1 || n>32) {
            return 0;
        }

        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    private static int process2(int limit, int columnLim, int leftDiaLim, int rightDiaLim) {
        if (columnLim == limit) {
            return 1;
        }

        // 与limit相与的原因：1. 截掉高位无效部分的1  2. 截掉左侧限制移位后的溢出部分，防止干扰
        int targetPos = limit & (~(columnLim | leftDiaLim | rightDiaLim));
        int res = 0;
        while (targetPos != 0) {
            int mostRightOne = ((~targetPos) + 1) & targetPos;
            // leftDiaLim = ((leftDiaLim + mostRightOne) << 1) & limit ?
            res += process2(limit, columnLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim + mostRightOne) >> 1);
            targetPos -= mostRightOne;
        }

        return res;
    }

    private static boolean isValidate(int[] record, int r, int c) {
        for (int i = 0; i < r; i++) {
            if (record[i] == c) {
                return false;
            } else if (Math.abs(r - i) == Math.abs(c - record[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 13;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
    }
}
