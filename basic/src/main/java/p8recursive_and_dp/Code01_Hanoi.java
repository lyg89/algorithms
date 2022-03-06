package p8recursive_and_dp;

/**
 * @author liyaguang11
 * @date 2022/2/25
 */
public class Code01_Hanoi {

    public static void hanoi2(int n) {
        if (n > 0) {
            process(n, "left", "right", "mid");
        }
    }

    private static void process(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("mov 1 from " + from + " to " + to);
        } else {
            process(n - 1, from, other, to);
            System.out.println("mov " + n + " from " + from + " to " + to);
            process(n - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        //hanoi1(n);
        System.out.println("============");
        hanoi2(n);
//		System.out.println("============");
//		hanoi3(n);
    }
}
