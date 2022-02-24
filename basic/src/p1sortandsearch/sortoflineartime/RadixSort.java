package p1sortandsearch.sortoflineartime;

import java.util.Arrays;

public class RadixSort {

    // only for no-negative value
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 获取数组中最大元素的位数
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    public static void radixSort(int[] arr, int L, int R, int digit) {
        int[] help = new int[R - L + 1];
        // 循环数组元素最大值的位数maxBits
        for (int i = 1; i <= digit; i++) {
            // 初始化 count[] 数组，长度为 10 表示十进制位，count[]元素的值是原数组中的值对应位等于其下标的个数
            int[] count = new int[10];
            for (int j = L; j <= R; j++) {
                // 得到arr[j]元素在i位上的值
                int curNum = getDigit(arr[j], i);
                count[curNum]++;
            }

            // 转换 count 数组，表示小于等于当前位值得原数组中数据个数是多少
            for (int idx = 1; idx < count.length; idx++) {
                count[idx] += count[idx - 1];
            }

            // 从后往前遍历原数组，定位元素当前位上的具体数值，放到help数组中
            for (int j = R; j >= L; j--) {
                int curBit = getDigit(arr[j], i);
                help[--count[curBit]] = arr[j];
            }

            // help元素替代原来的arr元素。
            for (int j = 0; j < help.length; j++) {
                arr[L + j] = help[j];
            }
        }
    }

    /**
     * 得到num数值在d位上的具体值
     * @param num
     * @param d
     * @return
     */
    public static int getDigit(int num, int d) {
        final int radix = 10;
        if (d > 1) {
            num /= (Math.pow(radix, (d - 1)));
        }

        num %= radix;
        return num;
    }

    /**
     * 找到数组中最大值的位数
     *
     * @param arr
     * @return
     */
    private static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }

        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }

        return res;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
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

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100000;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);
    }
}
