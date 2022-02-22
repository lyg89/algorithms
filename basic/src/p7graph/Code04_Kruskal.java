package p7graph;

import java.util.*;

/**
 * undirected graph only
 *
 * @author liyaguang11
 * @date 2022/2/22
 */
public class Code04_Kruskal {

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        // 图中所有边排序
        priorityQueue.addAll(graph.edges);
        HashSet<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge poll = priorityQueue.poll();
            if (!unionFind.isSameSet(poll.from, poll.to)) {
                unionFind.union(poll.from, poll.to);
                result.add(poll);
            }
        }
        return result;
    }

    // Union-Find Set
    public static class UnionFind {
        private Map<Node, Node> fatherMap;
        private Map<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public UnionFind(Collection<Node> nodes) {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findFather (Node cur) {
            Queue<Node> path = new LinkedList<>();
            while (fatherMap.get(cur) != cur) {
                path.add(cur);
                cur = fatherMap.get(cur);
            }

            while (!path.isEmpty()) {
                fatherMap.put(path.poll(), cur);
            }
            return cur;
        }

        public boolean isSameSet(Node a, Node b) {
            return this.findFather(a) == this.findFather(b);
        }

        public void union(Node a, Node b) {
            Node aFather = this.findFather(a);
            Node bFather = this.findFather(b);
            if (aFather != bFather) {
                Node bigNode = sizeMap.get(aFather) > sizeMap.get(bFather) ? aFather : bFather;
                Node smallNode = bigNode == aFather ? bFather : aFather;
                fatherMap.put(smallNode, bigNode);
                sizeMap.put(bigNode, sizeMap.get(aFather) + sizeMap.get(bFather));
                sizeMap.remove(smallNode);
            }
        }
    }
}
