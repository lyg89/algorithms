import java.util.Arrays;

/**
 * @description: select sort method
 * @author: liyaguang
 * @create: 2018-12-03 13:26
 **/
public class SelectSort {
    
    public static void main(String[] args){

        int[] arrOriginal = new int[]{5, 9, 7, 4, 22, 2, 65, 1, 45};
        System.out.println("before select sort, the array is: ");
        System.out.println(Arrays.toString(arrOriginal));

        int smallestIdx = 0;
        for (int i = 0; i < arrOriginal.length - 1; i++) {
            smallestIdx = i;

            for (int j = i+1; j < arrOriginal.length; j++) {
                if (arrOriginal[j] < arrOriginal[smallestIdx]) {
                    smallestIdx = j;
                }
            }
            
            switchVal(arrOriginal, i, smallestIdx);
        }
        System.out.println("\nend select sort, the array is: ");
        System.out.println(Arrays.toString(arrOriginal));
    }

    public static void switchVal(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
