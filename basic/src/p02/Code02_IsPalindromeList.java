package p02;

import java.util.Stack;

public class Code02_IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // need n extra space
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        Node pop;
        while (!stack.isEmpty()) {
            pop = stack.pop();
            if (pop.value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // need n/2 extra space
    public static boolean isPalindrome2(Node head) {
        // 0 或 1个元素时，直接返回true
        if (head == null || head.next == null) {
            return true;
        }

        // 定位 下中点 或 中点下一个
        Node midOrDown = head.next;
        Node fast = head;
        // 2  s=2, f=1
        // 3  s=3, f=3
        // 4  s=3, f=3
        // 5  s=4, f=5
        while (fast.next != null && fast.next.next != null) {
            midOrDown = midOrDown.next;
            fast = fast.next.next;
        }
        //System.out.println();
        //System.out.println("midOrDown is " + midOrDown.value);
        Stack<Node> stack = new Stack<>();
        Node cur = midOrDown;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            if (pop.value != head.value) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    // need O(1) extra space
    public static boolean isPalindrome3(Node head) {
        // 0 或 1个元素时，直接返回true
        if (head == null || head.next == null) {
            return true;
        }

        // 定位 上中点 或 中点
        Node midOrUp = head;
        Node fast = head;
        // 2  s=1, f=1
        // 3  s=2, f=3
        // 4  s=2, f=3
        // 5  s=3, f=5
        while (fast.next != null && fast.next.next != null) {
            midOrUp = midOrUp.next;
            fast = fast.next.next;
        }

        Node cur = midOrUp.next;
        midOrUp.next = null;

        // 链表后半部分反转
        Node pre = midOrUp;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        Node last = pre;
        // 开始比对
        cur = pre;
        Node left = head;
        boolean res = true;
        while (left != null && cur != null) {
            if (left.value != cur.value) {
                res = false;
                break;
            }
            left = left.next;
            cur = cur.next;
        }

        // 恢复链表
        pre = null;
        cur = last;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        //System.out.println("3执行完结果：");
        //printLinkedList(head);
        return res;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }

}
