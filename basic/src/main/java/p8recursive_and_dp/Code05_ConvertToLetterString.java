package p8recursive_and_dp;

/**
 * @author liyaguang11
 * @date 2022/3/2
 */
public class Code05_ConvertToLetterString {

    // str只含有数字字符0~9
    // 返回多少种转化方案
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }


    private static int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        //if (str[i] == 0) {
        //    return 0;
        //}
        int ans = process(str, i + 1);

        // if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
        if (str[i] == '1' && i + 1 < str.length) {
            ans += process(str, i + 2);
        } else if (str[i] == '2' && i + 1 < str.length) {
            if (str[i + 1] - '0' < 7) {
                ans += process(str, i + 2);
            }
        }
        return ans;
    }

    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            //int ans1 = dp1(s);
            //int ans2 = dp2(s);
            //if (ans0 != ans1 || ans0 != ans2) {
            //    System.out.println(s);
            //    System.out.println(ans0);
            //    System.out.println(ans1);
            //    System.out.println(ans2);
            //    System.out.println("Oops!");
            //    break;
            //}
        }
        System.out.println("测试结束");
    }
}
