package p1sortandsearch.sortofcomparison;

import java.util.Arrays;

import static common.Utils.generateRandomArray;
import static common.Utils.isEqual;

/**
 * @description: 归并排序
 * @author: Flash
 * @create: 2021-08-20
 **/
public class Code04_MergeSort {

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 2);
        process(arr, L, mid);
        process(arr, mid + 1, R);

        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int point1 = left;
        int point2 = mid + 1;

        int[] help = new int[right - left + 1];
        int i = 0;

        while (point1 <= mid && point2 <= right) {
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
    }

    // 迭代方式实现归并排序
    private static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        int mergeSize = 1;
        while (mergeSize < n) {

            int left = 0;
            while (left < n) {
                if (n - left <= mergeSize) {
                    break;
                }

                // 注意这里是下标，所以减一
                int mid = left + mergeSize - 1;
                // 注意这里可能越界，因此取了较小值
                int right = Math.min(mid + mergeSize, n - 1);
                merge(arr, left, mid, right);

                left = right + 1;
            }

            if (mergeSize > n / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    private static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            mergeSort(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        //int[] arr = generateRandomArray(10, 10);
        //printArray(arr);
        //mergeSort(arr);
        //printArray(arr);
    }
}
