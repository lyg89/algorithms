package p9sliding_window;

import java.util.Stack;

/**
 * @author liyaguang11
 * @date 2022/3/22
 */
public class Code04_AllTimesMinToMax {

    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int max2(int[] arr) {
        int[] sums = new int[arr.length];
        sums[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            // 找到 i 的右侧，第一个比i大的元素停止。
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                //Integer R = stack.pop();
                //max = Math.max(max, (sums[i - 1] - (stack.isEmpty() ? 0 : sums[stack.peek()])) * arr[i]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            //Integer R = stack.pop();
            //max = Math.max(max, (sums[R - 1] - (stack.isEmpty() ? 0 : sums[stack.peek()])) * arr[R]);
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
