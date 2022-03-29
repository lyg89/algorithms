package p11kmp;

public class Code01_KMP {

    public static int getIndexOf(String s, String m) {
        if(s.length() < m.length()) {
            return -1;
        }

        int x = 0;
        int y = 0;
        int[] nextArray = getNextArray(m.toCharArray());
        while (x < s.length() && y < m.length()) {
            if (s.charAt(x) == m.charAt(y)) {
                x++;
                y++;
            } else if (y == 0) {
                x++;
            } else {
                y = nextArray[y];
            }
        }

        return y == m.length() ? x - y : -1;
    }

    public static int[] getNextArray(char[] match){
        if (match == null || match.length == 0) {
            return new int[]{};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        if (match.length > 1) {
            next[1] = 0;
        }

        int i = 2;
        int cn = 0;
        while (i < match.length) {
            if (match[i - 1] == match[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }


    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
