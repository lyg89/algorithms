package sortofcomparison;

import static common.Utils.generateRandomArray;
import static common.Utils.printArray;
import static sortofcomparison.Code06_MergeSortForSmallSum.copyArray;

/**
 * @description: 数组中的逆序对- <url> https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/ </url>
 * @author: Flash
 * @create: 2021-08-20
 **/
public class Code05_MergeSortForReversePairs {

    private static int reversePairs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid)
                + process(arr, mid + 1, R)
                + merge(arr, L, mid, R);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int point1 = left;
        int point2 = mid + 1;

        int[] help = new int[right - left + 1];
        int i = 0;

        int res = 0;

        while (point1 <= mid && point2 <= right) {
            if (arr[point2] < arr[point1]) {
                res += mid - point1 + 1;
            }
            help[i++] = arr[point1] <= arr[point2] ? arr[point1++] : arr[point2++];
        }

        while (point1 <= mid) {
            help[i++] = arr[point1++];
        }

        while (point2 <= right) {
            help[i++] = arr[point2++];
        }

        i = 0;
        for (int j = left; j <= right; j++) {
            arr[j] = help[i++];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reversePairs(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
