package p8recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author liyaguang11
 * @date 2022/3/2
 */
public class Code02_PrintAllSubsquences {

    public static List<String> subs(String s) {
        List<String> ans = new ArrayList<>();
        process1(s, 0, "", ans);
        return ans;
    }

    private static void process1(String s, int index, String path, List<String> ans) {
        if (index == s.length()) {
            ans.add(path);
            return;
        }

        process1(s, index + 1, path, ans);
        process1(s, index + 1, path + s.charAt(index), ans);
    }

    private static List<String> subsNoRepeat(String s) {
        Set<String> set = new HashSet<>();
        process2(s, 0, "", set);
        return new ArrayList<>(set);
    }

    private static void process2(String s, int index, String path, Set<String> set) {
        if (index == s.length()) {
            set.add(path);
            return;
        }

        process2(s, index + 1, path, set);
        process2(s, index + 1, path + s.charAt(index), set);
    }


    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }
}
