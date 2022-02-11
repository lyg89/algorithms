package p4binarytreerecursive;

/**
 * @author liyaguang11
 * @date 2022/2/11
 */
public class Code04_IsFull {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    public static int h(Node head) {
        if (head == null) {
            return 0;
        }
        return Math.max(h(head.left), h(head.right)) + 1;
    }

    public static int n(Node head) {
        if (head == null) {
            return 0;
        }
        return n(head.left) + n(head.right) + 1;
    }

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }

        Info info = process(head);
        return (1 << info.height) - 1 == info.nodes;
    }

    private static class Info {
        private int nodes;
        private int height;

        public Info(int n, int h) {
            this.nodes = n;
            this.height = h;
        }
    }

    public static Info process(Node xNode) {
        if (xNode == null) {
            return new Info(0, 0);
        }

        Info leftInfo = process(xNode.left);
        Info rightInfo = process(xNode.right);
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        int h = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(nodes, h);
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
            if (isFull1(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
