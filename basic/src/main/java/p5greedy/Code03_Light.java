package p5greedy;

import java.util.HashSet;

/**
 * @author liyaguang11
 * @date 2022/2/18
 */
public class Code03_Light {

    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置 已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) {
            for (int i = 0; i < str.length; i++) {
                if (str[i] == '.' && !lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                    return Integer.MAX_VALUE;
                }
            }
            return lights.size();
        }
        int no = process(str, index + 1, lights);
        int yes = Integer.MAX_VALUE;
        if (str[index] == '.') {
            lights.add(index);
            yes = process(str, index + 1, lights);
            lights.remove(index);
        }
        return Math.min(no, yes);
    }

    public static int minLight2(String road) {
        char[] chars = road.toCharArray();
        int i = 0;
        int lights = 0;
        while (i < road.length()) {
            if (chars[i] == 'X') {
                i++;
                continue;
            }

            lights++;
            if (i + 1 == road.length()) {
                break;
            }

            if (chars[i + 1] == 'X') {
                i += 2;
            } else if (chars[i + 1] == '.') {
                i += 3;
            }

        }
        return lights;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
