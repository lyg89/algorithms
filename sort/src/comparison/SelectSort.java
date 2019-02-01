package comparison;

import java.util.Arrays;

/**
 * @description: select sort method
 * @author: liyaguang
 * @create: 2018-12-03 13:26
 **/
public class SelectSort {

    public static void main(String[] args) {

        int[] arrOriginal = new int[]{5, 9, 7, 4, 22, 2, 65, 1, 45};
        System.out.println("before select sort, the array is: ");
        System.out.println(Arrays.toString(arrOriginal));

        // 最小元素下标存放临时变量
        int smallestIdx;
        // 每次待确定值的索引位置
        for (int i = 0; i < arrOriginal.length - 1; i++) {
            smallestIdx = i;

            // 遍历待排序序列，选出其中最小元素
            for (int j = i + 1; j < arrOriginal.length; j++) {
                if (arrOriginal[j] < arrOriginal[smallestIdx]) {
                    smallestIdx = j;
                }
            }

            // 将最小值移动到它对应的索引位置
            Utils.switchVal(arrOriginal, i, smallestIdx);
        }
        System.out.println("\nend select sort, the array is: ");
        System.out.println(Arrays.toString(arrOriginal));
    }
}
