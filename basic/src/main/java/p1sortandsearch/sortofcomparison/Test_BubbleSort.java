package p1sortandsearch.sortofcomparison;

import java.util.Arrays;

/**
 * @description: bubble sort algorithms
 * @author: liyaguang
 * @create: 2018-12-03 13:16
 **/
public class Test_BubbleSort {

    public static void main(String[] args) {
        int[] arrOriginal = new int[]{5, 9, 7, 4, 22, 2, 65, 1, 45};
        int temp = 0;
        System.out.println("before sort, the array is: ");
        System.out.println(Arrays.toString(arrOriginal));

        for (int i = 0; i < arrOriginal.length; i++) {
            for (int j = arrOriginal.length - 1 ; j > i; j--) {
                if(arrOriginal[j] < arrOriginal[j-1]){
                    temp = arrOriginal[j];
                    arrOriginal[j] = arrOriginal[j-1];
                    arrOriginal[j-1] = temp;
                }
            }
        }
        System.out.println("\nend sort, the array is: ");
        System.out.println(Arrays.toString(arrOriginal));
    }
}
