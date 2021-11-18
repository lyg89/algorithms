package sortoflineartime;

import java.util.Arrays;

/**
 * @description: COUNTING-SORT
 * @author: liyaguang
 * @create: 2019-02-25 19:27
 **/
public class CountSort2 {

    public static void main(String[] args) {
        //int[] arr = new int[]{1, 3, 3, 2, 2, 4, 1};
        int[] arr = new int[]{2, 5, 3, 0, 2, 3, 0, 3};
        System.out.println("before counting sort, the array is: ");
        System.out.println(Arrays.toString(arr));

        int[] result = countingSort(arr, 5);

        System.out.println("\nend counting sort, the array is: ");
        System.out.println(Arrays.toString(result));
    }

    private static int[] countingSort(int[] original, int k) {
        int[] tempK = new int[k + 1];
        int[] result = new int[original.length];

        for (int i = 0; i < original.length; i++) {
            tempK[original[i]] += 1;
        }

        for (int i = 1; i < tempK.length; i++) {
            tempK[i] = tempK[i] + tempK[i - 1];
        }

        for (int j = original.length - 1; j >= 0; j--) {
            result[tempK[original[j]] - 1] = original[j];
            tempK[original[j]] -= 1;
        }
        return result;
    }
}
