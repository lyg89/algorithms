package p8recursive;

import java.util.Stack;

/**
 * @author liyaguang11
 * @date 2022/3/2
 */
public class Code04_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Integer pop = getBottom(stack);
        reverse(stack);
        stack.push(pop);
    }

    private static int getBottom(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        }
        int res = getBottom(stack);
        stack.push(pop);
        return res;
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }
}
