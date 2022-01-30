package p2linkedlist;

import java.util.ArrayList;

public class Code01_LinkedListMid {

    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    /**
     * 求链表中点或上中点
     * @param head 链表头结点
     * @return 链表中点或上中点
     */
    private static Node midOrUpMidNode(Node head) {
        // 0个、1个、2个节点的时候，返回head
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // 3个或以上节点的时候
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static Node right1(Node head) {
        // 0个、1个、2个节点的时候，返回head
        if (head == null) {
            return head;
        }

        ArrayList<Node> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head);
            head = head.next;
        }
        return arr.get((arr.size() - 1) / 2);
    }

    /**
     * 求链表中点或下中点
     * @param head 链表头结点
     * @return 链表中点或上中点
     */
    private static Node midOrDownMidNode(Node head) {
        // 0个、1个、2个节点的时候，返回head
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // 3个或以上节点的时候
        // 3  2    s=2  f=2
        // 4  3    s=3  f=4
        // 5  3    s=3  f=4
        // 6  4    s=4  f=6
        // 7  4    s=4  f=6
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    public static Node midOrUpMidPreNode(Node head) {
        // 0个、1个、2个节点的时候，返回head
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        // 3个或以上节点的时候
        // 3  1    s=1  f=3
        // 4  1    s=1  f=3
        // 5  2    s=2  f=5
        // 6  2    s=2  f=5
        // 7  3    s=3  f=7
        // 8  3    s=3  f=7
        // 9  4    s=4  f=9
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 3) / 2);
    }

    public static Node midOrDownMidPreNode(Node head) {
        // 0个、1个、2个节点的时候，返回head
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        // 3个或以上节点的时候
        // 3  1    s=1  f=2
        // 4  2    s=2  f=4
        // 5  2    s=2  f=4
        // 6  3    s=3  f=6
        // 7  3    s=3  f=6
        // 8  4    s=4  f=8
        // 9  4    s=4  f=8
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
    }

    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next.next = new Node(8);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");


        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrUpMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
    }


}
