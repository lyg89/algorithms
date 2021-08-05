package sortofcomparison;

import common.Utils;

import java.util.Arrays;

/**
 * @author liyaguang
 */
public class QuickSort2 {

    public static void main(String[] args) {
        int[] arrOriginal = new int[]{5, 9, 7, 4, 22, 2, 65, 1, 45};
        System.out.println("before sort, the arr is: ");
        System.out.println(Arrays.toString(arrOriginal));

        quickSortProcess(arrOriginal, 0, arrOriginal.length - 1);
        System.out.println("end sort, the arr is: ");
        System.out.println(Arrays.toString(arrOriginal));
        // 1. 选择轴值

        // 2. 将轴值与末尾元素交换，确定一个可交换的元素
        // 设定正向与反向游标指针
        // while 正idx < 反idx
        // 正向从起始元素开始，遇到比轴值大的元素时，与反向指针元素交换位置，正向指针暂停，break；否则正向+1
        // 反向从结束元素开始，遇。。。。小。。。。，。正。。。。。。。。。，反向指针暂停，break；。。反向-1

        // 3. 二分，递归调用
    }

    public static void quickSortProcess(int[] arr, int startIdx, int endIdx) {
        if (startIdx >= endIdx) {
            return;
        }
        // 1. 选择轴值
        int flagIndex = (startIdx + endIdx) / 2;
        int flagValue = arr[flagIndex];
        Utils.switchVal(arr, flagIndex, endIdx);

        int forwardIdx = startIdx;
        int reverseIdx = endIdx;

        while (forwardIdx < reverseIdx) {

            while (forwardIdx < reverseIdx && arr[forwardIdx] < flagValue) {
                forwardIdx++;
            }
            Utils.switchVal(arr, forwardIdx, reverseIdx);

            while (forwardIdx < reverseIdx && arr[reverseIdx] > flagValue) {
                reverseIdx--;
            }
            Utils.switchVal(arr, forwardIdx, reverseIdx);
        }

        // quickSortProcess(arr, startIdx, endIdx/2);
        // quickSortProcess(arr, (endIdx/2 + 1), endIdx);
        quickSortProcess(arr, startIdx, forwardIdx);
        quickSortProcess(arr, forwardIdx + 1, endIdx);
    }

}
