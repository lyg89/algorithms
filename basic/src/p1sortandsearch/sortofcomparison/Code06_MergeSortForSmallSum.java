package p1sortandsearch.sortofcomparison;

import static common.Utils.generateRandomArray;
import static common.Utils.printArray;

/**
 * @description: 最小和
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以，数组的小和为：1+1+3+1+1+3+4+2 = 16
 * @author: Flash
 * @create: 2021-08-21
 **/
public class Code06_MergeSortForSmallSum {

    private static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid)
                + process(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int pl = l;
        int pr = mid + 1;

        int[] help = new int[r - l + 1];
        int i = 0;

        int res = 0;
        while (pl <= mid && pr <= r) {
            res += arr[pl] < arr[pr] ? (r - pr + 1) * arr[pl] : 0;
            // 注意：两边元素相等时，要移动右指针，以免少计算了大于左侧元素的情况
            help[i++] = arr[pl] < arr[pr] ? arr[pl++] : arr[pr++];
        }

        while (pl <= mid) {
            help[i++] = arr[pl++];
        }

        while (pr <= r) {
            help[i++] = arr[pr++];
        }

        i = 0;
        for (int j = l; j <= r; j++) {
            arr[j] = help[i++];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
}
