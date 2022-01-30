package p2linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyaguang11
 * @date 2022/1/21
 */
public class Code04_CopyListWithRandom {

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int data) {
            this.val = data;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {

        Map<Node, Node> copyMap = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            copyMap.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        // 更新next、random指针
        cur = head;
        while (cur != null){
            copyMap.get(cur).next = copyMap.get(cur.next);
            copyMap.get(cur).random = copyMap.get(cur.random);
            cur = cur.next;
        }
        return copyMap.get(head);
    }


    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next;
        while (cur != null){
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }

        cur = head;
        while (cur != null){
            cur.next.random = cur.random != null ? cur.random.next : null;
            cur = cur.next.next;
        }

        cur = head;
        Node res = cur.next;
        Node copy;
        // 拆分为2个链表
        while (cur != null) {
            // copy节点
            copy = cur.next;
            // copy节点的下一个节点，原链表的下一个
            cur.next = copy.next;
            cur = copy.next;
            // copy节点的next指针指向
            copy.next = copy.next != null ? copy.next.next : null;

        }
        return res;
    }
}
