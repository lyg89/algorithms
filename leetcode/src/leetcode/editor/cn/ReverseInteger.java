package leetcode.editor.cn;

/**
 * @author liyaguang11
 * @date 2022/4/24
 */
public class ReverseInteger {
    public int reverse(int x) {
        int rev = 0;
        int pop = 0;

        while(x != 0 ){
            pop = x % 10;
            x /= 10;
            if(rev > Integer.MAX_VALUE/10 || (rev==Integer.MAX_VALUE/10 && pop > 7)) {
                return 0;
            }
            if(rev < Integer.MIN_VALUE/10 || (rev==Integer.MIN_VALUE/10 && pop < -8)) {
                return 0;
            }

            rev = rev * 10 + pop;

        }

        return rev;
    }
}
