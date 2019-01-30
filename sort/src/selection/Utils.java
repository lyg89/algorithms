package selection;

/**
 * sort algorithm use tools
 * @author liyaguang
 */
public class Utils {
    public static void switchVal(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
