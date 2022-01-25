package p02;

/**
 * @author liyaguang11
 * @date 2022/1/21
 */
public class Code05_FindFirstIntersectNode {

    public static class Node {
        public int val;
        public Node next;

        public Node(int data) {
            this.val = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        // 得到入环节点
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        // 都为空的情况下不存在环，判断是否相交
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }

        // 都存在环的情况下的处理
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }

        // 一个有环、一个没环的情况，直接返回空
        return null;
    }

    /**
     * 两个有环链表，返回第一个相交节点，如果不想交返回null
     *
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        if (loop1 == loop2) {
            Node cur1 = head1;
            Node cur2 = head2;
            int n = 0;
            // 注意这里终止节点为入环节点loop1
            while (cur1 != loop1) {
                cur1 = cur1.next;
                n++;
            }
            // 注意这里终止节点为入环节点loop2
            while (cur2 != loop2) {
                cur2 = cur2.next;
                n--;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0) {
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }
        // 从loop1的next节点开始遍历环，遍历一圈后，如果没有找到loop2节点，证明不相交
        Node cur1 = loop1.next;
        while (cur1 != loop1) {
            if (cur1 == loop2) {
                return loop1;
            }
            cur1 = cur1.next;
        }
        return null;
    }

    /**
     * 找到链表第一个入环节点，如果无环，返回null
     *
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        int count = 0;
        // 注意这里判断的是next是否为空，因为要保留最后一个节点的引用，以判断两个链表结尾节点是否相同
        Node cur1 = head1;
        while (cur1.next != null) {
            count++;
            cur1 = cur1.next;
        }

        Node cur2 = head2;
        while (cur2.next != null) {
            count--;
            cur2 = cur2.next;
        }

        if (cur1 != cur2) {
            return null;
        }

        cur1 = count > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        count = Math.abs(count);

        while (count > 0) {
            cur1 = cur1.next;
            count--;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }


    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

    }
}
