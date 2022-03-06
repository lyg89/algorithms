package components;

/**
 * 异或运算
 */
public class XOR_Test {


    public static void main(String[] args) {
//        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
//        XOR_Test.switchVal(arr, 5, 5);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[]{1, 1, 2, 2, 1, 2, 1, 2, 3, 4, 4, 3, 4, 2};
        XOR_Test.printOddTimesNum2(arr);
    }

    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }

        int rightOne = eor & (-eor);
        int leftEor = 0;
        for (int i : arr) {
            if ((rightOne & i) != 0) {
                leftEor ^= i;
            }
        }
        System.out.println(leftEor + "， " + (eor ^ leftEor));
    }


    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        System.out.println(eor);
    }

    private static void switchVal(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
