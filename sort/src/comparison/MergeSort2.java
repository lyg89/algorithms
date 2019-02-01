package comparison;

import java.util.Arrays;

/**
 * @description: merge sort method
 * @author: liyaguang
 * @create: 2019-02-01 23:00
 **/
public class MergeSort2 {

    public static void main(String[] args) {

        int[] arr = new int[]{5, 9, 7, 4, 22, 2, 65, 1, 45};
        System.out.println("before merge sort, the array is: ");
        System.out.println(Arrays.toString(arr));

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("\nend merge sort, the array is: ");
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    public static void merge(int[] arr, int p, int q, int r) {
        System.out.println(p + " " + q + " " + r);
        int[] L = new int[q - p + 1];
        int[] R = new int[r - q];

        for (int i = p; i < q + 1; i++) {
            L[i - p] = arr[i];
        }
        for (int i = q + 1; i < r + 1; i++) {
            R[i - q - 1] = arr[i];
        }

        System.out.print("L: ");
        System.out.println(Arrays.toString(L));

        System.out.print("R: ");
        System.out.println(Arrays.toString(R));

        int x = 0, y = 0;
        int i = p;
        while (x < L.length && y < R.length) {
            if (L[x] <= R[y]) {
                arr[i] = L[x];
                x++;
            } else {
                arr[i] = R[y];
                y++;
            }
            i++;
        }

        if (x == L.length) {
            while (y < R.length) {
                arr[i] = R[y];
                i++;
                y++;
            }
        } else {
            while (x < L.length) {
                arr[i] = L[x];
                i++;
                x++;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
