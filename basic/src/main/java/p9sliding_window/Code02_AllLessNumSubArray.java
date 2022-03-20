package p9sliding_window;

import java.util.Deque;
import java.util.LinkedList;

public class Code02_AllLessNumSubArray {

    // 暴力的对数器方法
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        Deque<Integer> minDeque = new LinkedList<>();
        Deque<Integer> maxDeque = new LinkedList<>();

        int result = 0;
        int R = 0;
        for (int L = 0; L < N; L++) {
            while (R < N) {
                while (!minDeque.isEmpty() && arr[minDeque.peekLast()] >= arr[R]) {
                    minDeque.pollLast();
                }
                minDeque.addLast(R);

                while (!maxDeque.isEmpty() && arr[maxDeque.peekLast()] <= arr[R]) {
                    maxDeque.pollLast();
                }
                maxDeque.addLast(R);

                if ((arr[maxDeque.peekFirst()] - arr[minDeque.peekFirst()]) > sum) {
                    break;
                }
                R++;
            }
            result += R - L;
            if (maxDeque.peekFirst() == L) {
                maxDeque.pollFirst();
            }
            if (minDeque.peekFirst() == L) {
                minDeque.pollFirst();
            }
        }
        return result;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
