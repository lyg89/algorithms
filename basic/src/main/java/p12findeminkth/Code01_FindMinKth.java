package p12findeminkth;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author liyaguang11
 * @date 2022/3/31
 */
public class Code01_FindMinKth {

    public static class MaxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    // 利用大根堆，时间复杂度O(N*logK)
    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        return maxHeap.peek();
    }

    // 改写快排，时间复杂度O(N)
    public static int minKth2(int[] array, int k) {
        int[] arr = copyArray(array);
        return process(arr, 0, array.length - 1, k - 1);
    }

    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i != ans.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    private static int process(int[] arr, int L, int R, int k) {
        // 1. 随机选一个数
        int i = L + (int) (Math.random() * (R - L + 1));
        swap(arr, i, R);
        // 2. partition
        int[] range = partition(arr, L, R);

        // 3. 选择一次继续
        if (k >= range[0] && k <= range[1]) {
            return arr[k];
        } else if (k < range[0]) {
            return process(arr, L, range[0] - 1, k);
        } else {
            return process(arr, range[1] + 1, R, k);
        }

    }

    private static int[] partition(int[] array, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (array[index] == array[r]) {
                index++;
            } else if (array[index] < array[r]) {
                swap(array, ++less, index++);
            } else if (array[index] > array[r]) {
                swap(array, --more, index);
            }
        }
        swap(array, r, more);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // 利用bfprt算法，时间复杂度O(N)
    public static int minKth3(int[] array, int k) {
        int[] arr = copyArray(array);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    private static int bfprt(int[] array, int L, int R, int index) {
        if (L == R) {
            return array[L];
        }
        int pivot = getMedianOfMedian(array, L, R);
        int[] range = partition(array, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return array[index];
        } else if (index < range[0]) {
            return bfprt(array, L, range[0] - 1, index);
        } else {
            return bfprt(array, range[1] + 1, R, index);
        }
    }

    private static int getMedianOfMedian(int[] array, int L, int R) {
        int offset = (R - L + 1) % 5 == 0 ? 0 : 1;
        int[] mArr = new int[(R - L + 1) / 5 + offset];

        for (int team = 0; team < mArr.length; team++) {
            int first = L + team * 5;
            mArr[team] = getMedian(array, first, Math.min(first + 4, R));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    private static int getMedian(int[] array, int L, int R) {
        insertionSort(array, L, R);
        //return array[L + (R - L) / 2];
        return array[(L + R) / 2];
    }

    private static void insertionSort(int[] array, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && array[j] > array[j + 1]; j--) {
                swap(array, j, j + 1);
            }
        }
    }

    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
