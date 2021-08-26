package leetcode.common;

/**
 * @description: Definition for singly-linked list.
 * @author: liyaguang
 * @create: 2019-04-02 13:20
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
