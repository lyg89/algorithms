package p7graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author liyaguang11
 * @date 2022/2/21
 */
public class Code01_BFS {

    public static void bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        Set<Node> set = new HashSet<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.value);
            for (Node next : node.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
