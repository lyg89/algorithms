package p1sortandsearch.binarysearch;

/**
 * @description: <url>https://leetcode-cn.com/problems/find-peak-element/</url>
 * @author: Flash
 * @create: 2021-08-05 16:25
 **/
public class Code04_BSPeak {

    /**
     * 在arr上，找局部最大
     *
     * @param arr
     * @return
     */
    public static int findPeakElement(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        if (arr.length == 1 || arr[0] > arr[1]) {
            return 0;
        }

        if (arr[arr.length - 1] > arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int left = 1;
        int right = arr.length - 2;
        int mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] < arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 1};
        System.out.println(findPeakElement(arr));
    }


}
