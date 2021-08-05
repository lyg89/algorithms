package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static common.Utils.generateRandomArray;
import static common.Utils.printArray;

/**
 * @description:
 * @author: Flash
 * @create: 2021-08-05 14:33
 **/
public class Code01_BSExist {

    private static int exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = sortedArr.length - 1;
        int mid;

        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (sortedArr[mid] == num) {
                return mid;
            }

            if (sortedArr[mid] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return sortedArr[left] == num ? left : -1;
    }


    // =================== for test start ===================
    public static int[] test(int[] sortedArr, int num) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < sortedArr.length; i++) {
            if (sortedArr[i] == num) {
                res.add(i);
            }
        }
        return res.size() == 0 ? new int[]{-1} : res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());


            int index = exist(arr, value);
            if (IntStream.of(test(arr, value)).noneMatch(x -> x == index)) {
                succeed = false;
                System.out.printf("find %d in: %s", value, Arrays.toString(arr));
                System.out.println();
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        //System.out.println(exist(new int[]{-47, -34, -12, 1, 9, 16, 31, 39, 73, 76}, -47));
    }
}
