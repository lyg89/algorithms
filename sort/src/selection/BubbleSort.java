package selection;

/**
 * @description: bubble sort algorithms
 * @author: liyaguang
 * @create: 2018-12-03 13:16
 **/
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 4, 6, 2, 5, 7, 9, 8};
        printArray(array);

        for (int i = 0; i < array.length; i++) {

            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }

        printArray(array);
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i);
            System.out.print(",");
        }
        System.out.println("----------");
    }
}
