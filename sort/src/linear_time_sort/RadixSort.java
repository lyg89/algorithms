import java.util.Arrays;

/**
 * @description: Radix-SORT
 * @author: liyaguang
 * @create: 2019-03-06 23:58:14
 **/
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
     * @param d     排序位数
     * @param r     基
     */
    public static int[] radixSort(int[] array, int n, int d, int r) {
        int[] count = new int[r];
        int[] tmpArray = new int[n];
        int radix = 1;
        int k = 0;

        // 进行d轮计数排序
        for (int i = 0; i < d; i++) {

            // 计数器计算出原数组中的元素数
            for (int j = 0; j < n; j++) {
                k = (array[j] / radix) % r;
                count[k]++;
            }
            // 计数器对应位数值修改为下一位的开始位置
            for (int j = 1; j < r; j++) {
                count[j] = count[j] + count[j - 1];
            }

            for (int j = n-1; j >= 0; j--) {
                k = (array[j] / radix) % r;
                System.out.print(k);
                count[k]--;
                tmpArray[count[k]] = array[j];
            }

            for (int j = 0; j < n; j++) {
                array[j] = tmpArray[j];
            }

            radix *= r;
        }
        return array;

    }

}
