package sortoflineartime;

import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int[] arr = new int[] { 97, 53, 88, 59, 26, 41, 88, 31, 22 };
        arr = radixSort(arr, 9, 2, 10);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 
     * @param array 排序序列
     * @param n     排序序列元素数
     * @param d     排序位数（两位数传入2）
     * @param r     基
     */
    public static int[] radixSort(int[] array, int n, int d, int r) {
        // 内部桶排序时用以保存各桶中的元素数
        int[] count = new int[r];
        // 按照不同基位进行桶排序时存放元素
        int[] tmpArray = new int[n];
        // 模除数，计算桶位
        int radix = 1;

        // 临时变量，用于计算每个元素的桶位，避免重复声明
        int k = 0;

        // 进行d轮计数排序（/桶排序）
        for (int i = 0; i < d; i++) {
            // 每轮循环，须重新初始化count数组
            for (int j = 0; j < r; j++) {
                count[j] = 0;
            }

            // 遍历原始数组，计算每个桶位的元素数，存储于count数组
            for (int j = 0; j < n; j++) {
                k = (array[j] / radix) % r;
                count[k]++;
            }
            // 计数器对应位数值修改为下一位的开始位置
            for (int j = 1; j < r; j++) {
                count[j] = count[j] + count[j - 1];
            }
            
            // 进行桶排序过程
            for (int j = n - 1; j >= 0; j--) {
                // 计算元素桶位
                k = (array[j] / radix) % r;
                count[k]--;
                tmpArray[count[k]] = array[j];
            }
            
            // 回写到原数组
            for (int j = 0; j < n; j++) {
                array[j] = tmpArray[j];
            }

            // 基位递增
            radix *= r;
        }
        return array;

    }
}
