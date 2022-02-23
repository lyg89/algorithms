package p7graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author liyaguang11
 * @date 2022/2/23
 */
public class Code06_Dijkstra {

    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);

        HashSet<Node> selectedNodes = new HashSet<>();
        Node minNode = getMinDistanceNodeAndUnSelectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            ArrayList<Edge> edges = minNode.edges;
            for (Edge edge : edges) {
                Node to = edge.to;
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distanceMap.get(minNode) + edge.weight);
                } else {
                    distanceMap.put(to, Math.min(distanceMap.get(to), distanceMap.get(minNode) + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceNodeAndUnSelectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    private static Node getMinDistanceNodeAndUnSelectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        int minDistance = Integer.MAX_VALUE;
        Node minNode = null;
        for (Node node : distanceMap.keySet()) {
            if (!selectedNodes.contains(node) && distanceMap.get(node) < minDistance) {
                minNode = node;
                minDistance = distanceMap.get(node);
            }
        }
        return minNode;
    }
}
