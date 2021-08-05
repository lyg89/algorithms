package sortofcomparison;

import java.util.Arrays;

import static common.Utils.*;

/**
 * @description: selection sort for practice
 * @author: Flash
 * @create: 2021-08-05 11:36
 **/
public class Code01_SelectionSort {

    private static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swapValue(arr, i, minIndex);
        }
    }

    private static void swapValue(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // ================ for test start ================
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
            selectionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(5, 10);
        printArray(arr);
        selectionSort(arr);
        printArray(arr);
    }
}
