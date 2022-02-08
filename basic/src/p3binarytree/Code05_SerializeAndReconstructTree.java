package p3binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Code05_SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(Node node, Queue<String> ans) {
        if (node == null) {
            ans.add(null);
            return;
        }
        ans.add(String.valueOf(node.value));
        pres(node.left, ans);
        pres(node.right, ans);
    }

    public static Node buildByPreQueue(Queue<String> prelist) {
        if (prelist == null || prelist.size() == 0) {
            return null;
        }
        return preb(prelist);

    }

    public static Node preb(Queue<String> prelist) {
        String value = prelist.poll();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(prelist);
        head.right = preb(prelist);
        return head;
    }

    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
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

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 10;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);

            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            //Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            //Node levelBuild = buildByLevelQueue(level);
            //if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
            if (!isSameValueStructure(preBuild, posBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }

    private static Node buildByPosQueue(Queue<String> pos) {
        if (pos == null || pos.size() == 0) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!pos.isEmpty()) {
            stack.push(pos.poll());
        }
        return posBuild(stack);
    }

    private static Node posBuild(Stack<String> stack) {
        String pop = stack.pop();
        if (pop == null) {
            return null;
        }


        Node newNode = generateNode(pop);
        newNode.right = posBuild(stack);
        newNode.left = posBuild(stack);
        return newNode;
    }

    private static Queue<String> posSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        posSerialProcess(head, ans);
        return ans;
    }

    private static void posSerialProcess(Node node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
            return;
        }
        posSerialProcess(node.left, queue);
        posSerialProcess(node.right, queue);
        queue.add(String.valueOf(node.value));
    }
}
