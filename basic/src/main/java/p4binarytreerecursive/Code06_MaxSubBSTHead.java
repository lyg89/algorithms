package p4binarytreerecursive;

import java.util.ArrayList;

/**
 * @author liyaguang11
 * @date 2022/2/14
 */
public class Code06_MaxSubBSTHead {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead1(head.left);
        Node rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static Node maxSubBSTHead2(Node head) {
        if (head == null) {
            return null;
        }

        return process(head).maxSubBSTHead;
    }

    private static Info process(Node xNode) {
        if (xNode == null) {
            return null;
        }

        Info leftInfo = process(xNode.left);
        Info rightInfo = process(xNode.right);

        int min = xNode.value;
        int max = xNode.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }

        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }

        int maxSubBSTSize = 0;
        Node maxSubBSTHead = xNode;
        if (leftInfo != null) {
            maxSubBSTSize = leftInfo.maxSubBSTSize;
            maxSubBSTHead = leftInfo.maxSubBSTHead;
        }
        if (rightInfo != null && rightInfo.maxSubBSTSize > maxSubBSTSize) {
            maxSubBSTSize = rightInfo.maxSubBSTSize;
            maxSubBSTHead = rightInfo.maxSubBSTHead;
        }

        boolean validateLeft = leftInfo == null || (leftInfo.maxSubBSTHead == xNode.left && leftInfo.max < xNode.value);
        boolean validateRight = rightInfo == null || (rightInfo.maxSubBSTHead == xNode.right && rightInfo.min > xNode.value);
        if (validateLeft && validateRight) {
            maxSubBSTSize =
                      (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                    + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize)
                    + 1;
            maxSubBSTHead = xNode;
        }


        return new Info(maxSubBSTHead, maxSubBSTSize, min, max);
    }

    public static class Info {
        private Node maxSubBSTHead;
        private int maxSubBSTSize;
        private int min;
        private int max;

        public Info(Node maxSubBSTHead, int maxSubBSTSize, int min, int max) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBSTSize = maxSubBSTSize;
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
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
