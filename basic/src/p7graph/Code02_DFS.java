package p7graph;

import java.util.*;

/**
 * @author liyaguang11
 * @date 2022/2/21
 */
public class Code02_DFS {

    public static void dfs(Node start) {
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();

        stack.push(start);
        set.add(start);
        // 入栈时打印
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
