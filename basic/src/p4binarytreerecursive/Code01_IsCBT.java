package p4binarytreerecursive;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liyaguang11
 * @date 2022/2/10
 */
public class Code01_IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isCBT1(Node head) {
        if (head  == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        // 1. 任何节点，有右无左，直接false
        // 2. 一旦遇到左右孩子不双全，后续所有节点必须为叶子
        queue.add(head);
        Node l;
        Node r;
        boolean leafCheck = false;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            l = cur.left;
            r = cur.right;
            if (l == null && r != null) {
                return false;
            }
            if (leafCheck && (l != null || r != null)) {
                return false;
            }
            //if (!leafCheck && (l == null || r == null)){
            if (l == null || r == null){
                leafCheck = true;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r!= null) {
                queue.add(r);
            }
        }
        return true;
    }

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    // 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }

    public static Info process(Node X) {
        return new Info(true, true, 0);
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

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
