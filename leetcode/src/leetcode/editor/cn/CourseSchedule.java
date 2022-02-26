//There are a total of numCourses courses you have to take, labeled from 0 to 
//numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai,
// bi] indicates that you must take course bi first if you want to take course ai.
// 
//
// 
// For example, the pair [0, 1], indicates that to take course 0 you have to 
//first take course 1. 
// 
//
// Return true if you can finish all courses. Otherwise, return false. 
//
// 
// Example 1: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: true
//Explanation: There are a total of 2 courses to take. 
//To take course 1 you should have finished course 0. So it is possible.
// 
//
// Example 2: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//Output: false
//Explanation: There are a total of 2 courses to take. 
//To take course 1 you should have finished course 0, and to take course 0 you 
//should also have finished course 1. So it is impossible.
// 
//
// 
// Constraints: 
//
// 
// 1 <= numCourses <= 10⁵ 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// All the pairs prerequisites[i] are unique. 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1137 👎 0


package leetcode.editor.cn;

import java.util.*;

public class CourseSchedule {
    public static void main(String[] args) {
        Solution solution = new CourseSchedule().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {

            Graph graph = this.createGraph(prerequisites, numCourses);
            Queue<Node> zeroQueue = new LinkedList<>();

            for (Node node : graph.nodes.values()) {
                if (node.in == 0) {
                    zeroQueue.offer(node);
                }
            }

            List<Node> topologySortList = new ArrayList<>();
            while (!zeroQueue.isEmpty()) {
                Node poll = zeroQueue.poll();
                topologySortList.add(poll);
                for (Edge edge : poll.edges) {
                    poll.out--;
                    edge.to.in--;
                    if (edge.to.in == 0) {
                        zeroQueue.offer(edge.to);
                    }
                }
            }
            return topologySortList.size() == numCourses;
        }

        Graph createGraph(int[][] matrix, int numCourses) {
            Graph graph = new Graph();
            for (int i = 0; i < matrix.length; i++) {
                // 拿到每一条边， matrix[i]
                int from = matrix[i][1];
                int to = matrix[i][0];
                if (!graph.nodes.containsKey(from)) {
                    graph.nodes.put(from, new Node(from));
                }
                if (!graph.nodes.containsKey(to)) {
                    graph.nodes.put(to, new Node(to));
                }
                Node fromNode = graph.nodes.get(from);
                Node toNode = graph.nodes.get(to);
                Edge newEdge = new Edge(fromNode, toNode);
                fromNode.nexts.add(toNode);
                fromNode.out++;
                toNode.in++;
                fromNode.edges.add(newEdge);
                graph.edges.add(newEdge);
            }
            for (int i = 0; i < numCourses; i++) {
                if (!graph.nodes.containsKey(i)) {
                    graph.nodes.put(i, new Node(i));
                }
            }
            return graph;
        }
    }



    class Graph {
        public Map<Integer, Node> nodes;
        public Set<Edge> edges;

        public Graph() {
            this.nodes = new HashMap<>();
            this.edges = new HashSet<>();
        }


    }

    class Edge {
        public Node from;
        public Node to;

        public Edge(Node from, Node to) {
            this.from = from;
            this.to = to;
        }
    }

    class Node {

        public int value;
        public int in;
        public int out;
        public ArrayList<Node> nexts;
        public ArrayList<Edge> edges;

        public Node(int value) {
            this.value = value;
            this.in = 0;
            this.out = 0;
            this.nexts = new ArrayList<>();
            this.edges = new ArrayList<>();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}