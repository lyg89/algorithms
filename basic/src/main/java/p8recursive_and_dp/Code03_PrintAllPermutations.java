package p8recursive_and_dp;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyaguang11
 * @date 2022/3/2
 */
public class Code03_PrintAllPermutations {

    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s.isEmpty()) {
            return ans;
        }
        List<String> rest = Lists.newArrayList(s.split(""));
        process(rest, "", ans);
        return ans;
    }

    private static void process(List<String> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
            return;
        }

        for (int i = 0; i < rest.size(); i++) {
            String cur = rest.remove(i);
            process(rest, path + cur, ans);
            rest.add(i, cur);
        }
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        //System.out.println("=======");
        //List<String> ans2 = permutation2(s);
        //for (String str : ans2) {
        //    System.out.println(str);
        //}
        //System.out.println("=======");
        //List<String> ans3 = permutation3(s);
        //for (String str : ans3) {
        //    System.out.println(str);
        //}

    }
}
