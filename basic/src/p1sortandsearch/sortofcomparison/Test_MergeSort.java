package p1sortandsearch.sortofcomparison;

import java.util.Arrays;

/**
 * @description:
 * @author: liyaguang
 * @create: 2018-12-12 12:54
 **/
public class Test_MergeSort {

    public static void main(String[] args) {
        int[] result = mergeSort(new int[]{1, 3, 4, 6, 2, 5, 7, 9, 8});
        System.out.println(Arrays.toString(result));
    }


    public static int[] mergeSort(int[] arrayInt) {

        if (arrayInt.length <= 1) {
            return arrayInt;
        }

        // 9
        int n = arrayInt.length;
        // 4
        int middle = n / 2;

        // 0,1,2,3
        int[] firstArray = new int[middle];

        // 0,1,2,3,4
        int[] secondArray = new int[n - middle];
        for (int i = 0; i < n; i++) {
            if (i < middle) {
                firstArray[i] = arrayInt[i];
            } else {
                secondArray[i - middle] = arrayInt[i];
            }
        }
        System.out.println("赋值数组：first-" + Arrays.toString(firstArray));
        System.out.println("赋值数组：second-" + Arrays.toString(secondArray));

        mergeSort(firstArray);
        mergeSort(secondArray);
        merge(firstArray, secondArray, arrayInt);
        return arrayInt;
    }

    public static void merge(int[] first, int[] second, int[] arrOri) {
        int i = 0, j = 0, k = 0;
        // f = [9], s=[8], a=[9,8]
        while (i < first.length && j < second.length) {
            if (first[i] <= second[j]) {
                arrOri[k] = first[i];
                i++;
            } else {
                arrOri[k] = second[j];
                j++;
            }
            k++;
        }

        if (i == first.length) {
            while (j < second.length) {
                arrOri[k] = second[j];
                k++;
                j++;
            }
        } else {
            while (i < first.length) {
                arrOri[k] = first[i];
                k++;
                i++;
            }
        }
        System.out.println("归并因子：" + Arrays.toString(first) + "+" + Arrays.toString(second));
        System.out.println("归并结果：" + Arrays.toString(arrOri));
    }
}
