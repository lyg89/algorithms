package p12findeminkth;

/**
 * @author liyaguang11
 * @date 2022/3/31
 */
public class Code01_FindMinKth {
    // 利用大根堆，时间复杂度O(N*logK)
    public static int minKth1(int[] arr, int k) {
        return 0;
    }

    // 改写快排，时间复杂度O(N)
    public static int minKth2(int[] array, int k) {
        return process(array, 0, array.length - 1, k);
    }

    private static int process(int[] arr, int L, int R, int k) {
        // 1. 随机选一个数
        int i = L + (int) (Math.random() * (R - L + 1));
        swap(arr, i, R);
        // 2. partition
        int[] parRes = partition(arr, L, R);

        // 3. 选择一次继续
        if (k >= parRes[0] && k <= parRes[1]) {
            return parRes[0];
        } else if (k < parRes[0]) {
            return process(arr, 0, L - 1, k);
        } else {
            return process(arr, R + 1, arr.length - 1, k);
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
        swap(array, r, --more);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // 利用bfprt算法，时间复杂度O(N)
    public static int minKth3(int[] array, int k) {

        return 0;
    }

    private static int bfprt(int[] array, int L, int R, int index) {
        if (L == R) {
            return array[L];
        }

        return 0;
    }

    private static int getMedeianOfMedian(int[] array, int L, int R) {
        int offset = (R - L + 1) % 5 == 0 ? 0 : 1;
        int[] mArr = new int[(R - L + 1) % 5 + offset];

        for (int team = 0; team < mArr.length; team++) {

        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
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
