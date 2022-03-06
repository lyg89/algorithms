package p5greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author liyaguang11
 * @date 2022/2/17
 */
public class Code01_LowestLexicography {

    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "" : ans.first();
    }

    private static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            TreeSet<String> next = process(removeIndexString(strs, i));
            for (String s : next) {
                ans.add(first+s);
            }
        }
        return ans;
    }

    private static String[] removeIndexString(String[] arr, int index) {
        String[] res = new String[arr.length - 1];
        int idx = 0;
        for (int j = 0; j < arr.length; j++) {
            if (j != index) {
                res[idx++] = arr[j];
            }
        }
        return res;
    }


    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new LowestLexicographyComparator());
        StringBuilder res = new StringBuilder("");
        for (String str : strs) {
            res.append(str);
        }
        return res.toString();
    }

    private static class LowestLexicographyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
