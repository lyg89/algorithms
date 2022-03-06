package p3binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Code04_TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        // 当前层的节点数
        int curLevelNodes = 0;
        // 正在处理的层
        int processLevel = 1;
        // 最大宽度
        int max = 0;

        Map<Node, Integer> map = new HashMap<>();
        map.put(head, 1);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            Integer curLevel = map.get(curNode);
            if (curNode.left != null) {
                map.put(curNode.left, curLevel + 1);
                queue.offer(curNode.left);
            }

            if (curNode.right != null) {
                map.put(curNode.right, curLevel + 1);
                queue.offer(curNode.right);
            }

            if (curLevel == processLevel) {
                curLevelNodes++;
            } else {
                processLevel++;
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    private static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        // 当前层的节点数
        int curLevelNodes = 0;
        // 最大宽度
        int max = 0;

        Node curEnd = head;
        Node nextEnd = null;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if (curNode.left != null) {
                nextEnd = curNode.left;
                queue.offer(curNode.left);
            }

            if (curNode.right != null) {
                nextEnd = curNode.right;
                queue.offer(curNode.right);
            }

            curLevelNodes++;
            if (curNode == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

}
