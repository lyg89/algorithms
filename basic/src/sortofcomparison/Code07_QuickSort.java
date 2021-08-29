package sortofcomparison;

public class Code07_QuickSort {

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);

            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }

            //Arrays.sort(arr2);
            //if (!isEqual(arr1, arr2)) {
            //    printArray(arr1);
            //    printArray(arr2);
            //    succeed = false;
            //    break;
            //}
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }

    private static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    private static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }

        if (L == R) {
            return L;
        }

        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            // 注意这里是小于等于区域放在左边，否则右边还是无序的
            if (arr[index] <= arr[R]) {
                swap(arr, ++lessEqual, index);
            }
            index++;
        }
        swap(arr, R, ++lessEqual);
        return lessEqual;
    }


    private static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] M2 = partition2(arr, L, R);

        process2(arr, L, M2[0] - 1);
        process2(arr, M2[1] + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }


    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int i = L + (int) (Math.random() * (R - L + 1));
        swap(arr, i, R);
        int[] equalArea = partition2(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }

    public static int[] partition2(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }

        if (L == R) {
            return new int[]{L, R};
        }

        int less = L - 1;
        int more = R;

        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, ++less, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, --more, index);
            }
        }
        swap(arr, R, more);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
