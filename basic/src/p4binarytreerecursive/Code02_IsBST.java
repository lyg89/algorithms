package p4binarytreerecursive;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyaguang11
 * @date 2022/2/10
 */
public class Code02_IsBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        inTraversal(head, ans);

        for (int i = 1; i < ans.size(); i++) {
            if (ans.get(i) <= ans.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void inTraversal(Node node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        inTraversal(node.left, ans);
        ans.add(node.value);
        inTraversal(node.right, ans);
    }


    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    private static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        boolean isLeftBST = leftInfo == null ? true : leftInfo.isBST;
        boolean isRightBST = rightInfo == null ? true : rightInfo.isBST;

        boolean isLeftValueValid = leftInfo == null ? true : leftInfo.max < node.value;
        boolean isRightValueValid = rightInfo == null ? true : rightInfo.min > node.value;
        boolean isBST = isLeftBST && isRightBST && isLeftValueValid && isRightValueValid;

        int min = node.value;
        int max = node.value;
        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
            max = Math.max(leftInfo.max, max);
        }
        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max);
        }
        return new Info(isBST, min, max);
    }

    private static class Info {
        private boolean isBST;
        private int min;
        private int max;
        public Info(boolean i, int min, int max) {
            isBST = i;
            this.min = min;
            this.max = max;
        }
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
