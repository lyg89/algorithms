package p1sortandsearch.sortofcomparison;

import common.Utils;

import java.util.Arrays;

/**
 * heap sort algorithm
 *
 * @author liyaguang
 */
public class HeapSort {

    public static void main(String[] args) {

        int[] arr = new int[]{5, 9, 7, 4, 22, 2, 65, 1, 45};
        System.out.println("before select sort, the array is: ");
        System.out.println(Arrays.toString(arr));

        // 建最大堆
        Heap heap = new Heap();
        heap.buildMaxHeap(arr);

        // 选取堆顶最大元素，放置到数组对应位置后，调整最大堆
        for (int i = arr.length - 1; i > 0; i--) {
            Utils.switchVal(arr, i, 0);
            heap.setHeapSize(heap.getHeapSize() - 1);
            heap.maxHeapify(arr, 0);
        }

        System.out.println("\nend heap sort, the array is: ");
        System.out.println(Arrays.toString(arr));
    }

}
