package p7graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author liyaguang11
 * @date 2022/2/22
 */
public class Code05_Prim {

    public static Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        HashSet<Node> nodeSet = new HashSet<>();
        HashSet<Edge> result = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    edgePriorityQueue.offer(edge);
                }
                while (!edgePriorityQueue.isEmpty()) {
                    Edge pollEdge = edgePriorityQueue.poll();
                    if (!nodeSet.contains(pollEdge.to)) {
                        nodeSet.add(pollEdge.to);
                        result.add(pollEdge);

                        // 外层if判断防止循环
                        edgePriorityQueue.addAll(pollEdge.to.edges);
                    }


                }
            }
        }
        return result;
    }

    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和
    public static int prim(int[][] graph) {
        return 0;
    }
}
