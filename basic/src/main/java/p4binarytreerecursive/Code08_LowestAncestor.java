package p4binarytreerecursive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author liyaguang11
 * @date 2022/2/14
 */
public class Code08_LowestAncestor {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    public static Node lowestAncestor2(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }

        return process(head, o1, o2).lowestAncestor;
    }

    private static Info process(Node xNode, Node o1, Node o2) {
        if (xNode == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(xNode.left, o1, o2);
        Info rightInfo = process(xNode.right, o1, o2);
        boolean isFindO1 = leftInfo.isFindO1 || rightInfo.isFindO1 || xNode == o1;
        boolean isFindO2 = leftInfo.isFindO2 || rightInfo.isFindO2 || xNode == o2;
        Node lowestAncestor = null;
        if (leftInfo.lowestAncestor != null) {
            lowestAncestor = leftInfo.lowestAncestor;
        } else if (rightInfo.lowestAncestor != null) {
            lowestAncestor = rightInfo.lowestAncestor;
        } else if (isFindO1 && isFindO2) {
            lowestAncestor = xNode;
        }
        return new Info(isFindO1, isFindO2, lowestAncestor);
    }

    public static class Info {
        private boolean isFindO1;
        private boolean isFindO2;
        private Node lowestAncestor;
        public Info (boolean isFindO1, boolean isFindO2, Node lowestAncestor) {
            this.isFindO1 = isFindO1;
            this.isFindO2 = isFindO2;
            this.lowestAncestor = lowestAncestor;
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

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
