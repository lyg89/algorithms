package sortofcomparison;

import java.util.Arrays;

import static common.Utils.*;

/**
 * @description: bubble sort
 * @author: Flash
 * @create: 2021-08-05 12:00
 **/
public class Code02_BubbleSort {

    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int e = arr.length - 1; e > 0; e--) {
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i + 1]) {
                    swapValue(arr, i, i + 1);
                }
            }
        }
    }

    private static void swapValue(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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
            bubbleSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }
}
