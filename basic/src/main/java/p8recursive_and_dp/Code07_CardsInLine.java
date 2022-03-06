package p8recursive_and_dp;

public class Code07_CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int second = s1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    private static int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }

        int ls = s1(arr, L+1, R) + arr[L];
        int rs = s1(arr, L, R-1) + arr[R];

        return Math.max(ls, rs);
    }

    private static int s1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int lFirst = f1(arr, L + 1, R);
        int rFirst = f1(arr, L, R-1);
        // second one take card is passive, the first one must use the second on take the min value
        return Math.min(lFirst, rFirst);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        //System.out.println(win2(arr));
        //System.out.println(win3(arr));

    }
}
