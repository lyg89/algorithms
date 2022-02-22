package p7graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author liyaguang11
 * @date 2022/2/22
 */
public class Code03_TopologySort {

    // directed graph and no loop
    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroQueue.offer(node);
            }
        }
        List<Node> result = new LinkedList<>();
        while (!zeroQueue.isEmpty()) {
            Node poll = zeroQueue.poll();
            result.add(poll);
            for (Node next : poll.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(poll) == 0) {
                    zeroQueue.offer(next);
                }
            }
        }
        return result;
    }
}
