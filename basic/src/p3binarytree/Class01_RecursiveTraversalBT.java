package p3binarytree;

/**
 * @author liyaguang11
 * @date 2022/1/29
 */
public class Class01_RecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        System.out.print("pre: ");
        pre(head);
        System.out.println();
        System.out.println("========");
        System.out.print("in: ");
        in(head);
        System.out.println();
        System.out.println("========");
        System.out.print("post: ");
        pos(head);
        System.out.println();
        System.out.println("========");

    }

    private static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.value + " ");
    }

    private static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.print(head.value + " ");
        in(head.right);
    }

    private static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        pre(head.left);
        pre(head.right);
    }
}
