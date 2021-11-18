package sortoflineartime;

public class RadixSort {

    // only for no-negative value
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    public static void radixSort(int[] arr, int L, int R, int maxBits) {

        // 循环位数
        // 初始化 count[] 数组，个数 10 表示十进制位，数值对应的原数组中的个数是多少

        // 转换 count 数组，表示小于等于当前位值得原数组中数据个数是多少

        // 从后往前遍历原数组，定位元素当前位上的具体数值，放到help数组中


        // help元素替代原来的arr元素。
    }


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

    public static void main(String[] args) {
        System.out.println(maxbits(new int[]{10, 100, 99999}));
    }
}
