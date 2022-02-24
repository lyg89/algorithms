package p7graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author liyaguang11
 * @date 2022/2/23
 */
public class Code06_Dijkstra {

    /**
     * dijkstra algorithm
     * no negative weight
     *
     * @param from start node
     * @return reach the all other nodes of min distance
     */
    public static HashMap<Node, Integer> dijkstra1(Node from) {
        // 从from点触发，存储到达其他所有点的最短距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);

        // 存储已经使用过的途经点，再次经过时不再重复使用
        HashSet<Node> selectedNodes = new HashSet<>();
        // 获取当前未达到的最短距离节点
        Node minNode = getMinDistanceNodeAndUnSelectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            // 当前最短路径节点，作为途经点，到达其他邻接点的路径值变化，与原来到达路径值比较，取最小更新
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

    /**
     * dijkstra algorithm implements using min heap structure
     *
     * @param from start node
     * @return reach the all other node distance
     */
    public static HashMap<Node, Integer> dijkstra2(Node from, int size) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(new NodeRecord(from, 0));

        while (!nodeHeap.isEmpty()) {
            NodeRecord minNodeRecord = nodeHeap.pop();
            distanceMap.put(minNodeRecord.node, minNodeRecord.distance);

            for (Edge edge : minNodeRecord.node.edges) {
                nodeHeap.addOrUpdateOrIgnore(new NodeRecord(edge.to, edge.weight));
            }
        }

        return distanceMap;
    }

    private static class NodeRecord {
        private Node node;
        private int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private static class NodeHeap {
        private Node[] nodes;
        private Map<Node, Integer> heapIndexMap;
        private Map<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            this.nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public void addOrUpdateOrIgnore(NodeRecord nodeRecord) {
            Node node = nodeRecord.node;
            // 判断是否为新增操作
            if (!heapIndexMap.containsKey(node)) {
                distanceMap.put(node, nodeRecord.distance);
                heapIndexMap.put(node, size);
                nodes[size] = node;
                insertHeapify(size++);
            } else if (heapIndexMap.get(node) != -1) {
                // 更新情况的处理
                distanceMap.put(node, Math.min(distanceMap.get(node), nodeRecord.distance));
                insertHeapify(heapIndexMap.get(node));
            }
        }

        private void insertHeapify(int index) {

        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        public NodeRecord pop() {
            Node pop = nodes[0];
            NodeRecord record = new NodeRecord(pop, distanceMap.get(pop));
            swap(0, size-1);
            nodes[size-1] = null;
            heapIndexMap.put(pop, -1);
            distanceMap.remove(pop);
            heapify(0, --size);
            return record;
        }

        private void heapify(int index, int size) {

        }

        public boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        public boolean inHeap(Node node) {
            if (heapIndexMap.containsKey(node)) {
                return heapIndexMap.get(node) != -1;
            }
            return false;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}
