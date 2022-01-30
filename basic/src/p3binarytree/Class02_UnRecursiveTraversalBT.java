package p3binarytree;

import java.util.Stack;

/**
 * @author liyaguang11
 * @date 2022/1/29
 */
public class Class02_UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void pre(Node cur) {
        System.out.print("pre order: ");
        Stack<Node> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            System.out.print(cur.value + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        System.out.println();
    }

    public static void in(Node cur) {
        System.out.print("in order: ");
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }

    public static void pos1(Node cur) {
        System.out.print("post1 order: ");
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(cur);
        while (!s1.isEmpty()) {
            cur = s1.pop();
            s2.push(cur);
            if (cur.left != null) {
                s1.push(cur.left);
            }
            if (cur.right != null) {
                s1.push(cur.right);
            }
        }

        while (!s2.isEmpty()) {
            System.out.print(s2.pop().value + " ");
        }
        System.out.println();
    }

    public static void pos2(Node cur) {
        if (cur == null) {
            return;
        }
        System.out.print("post2 order: ");
        Stack<Node> stack = new Stack<>();
        Node h = cur;
        Node c;
        stack.push(cur);
        while (!stack.isEmpty()) {
            c = stack.peek();
            if (c.left != null && c.left != h && c.right != h) {
                stack.push(c.left);
            } else if (c.right != null && c.right != h) {
                stack.push(c.right);
            } else {
                h = stack.pop();
                System.out.print(h.value + " ");
            }
        }

        System.out.println();
    }



    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }
}
