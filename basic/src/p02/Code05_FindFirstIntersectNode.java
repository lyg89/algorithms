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

    /**
     * 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
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

        cur1 = head1;
        cur2 = head2;
        Node cur = count > 0 ? cur1 : cur2;
        count = Math.abs(count);

        while (count > 0) {
            cur = cur.next;
            count--;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }
}
