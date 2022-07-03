//Given the head of a linked list, reverse the nodes of the list k at a time, 
//and return the modified list. 
//
// k is a positive integer and is less than or equal to the length of the 
//linked list. If the number of nodes is not a multiple of k then left-out nodes, in 
//the end, should remain as it is. 
//
// You may not alter the values in the list's nodes, only nodes themselves may 
//be changed. 
//
// 
// Example 1: 
//
// 
//Input: head = [1,2,3,4,5], k = 2
//Output: [2,1,4,3,5]
// 
//
// Example 2: 
//
// 
//Input: head = [1,2,3,4,5], k = 3
//Output: [3,2,1,4,5]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is n. 
// 1 <= k <= n <= 5000 
// 0 <= Node.val <= 1000 
// 
//
// 
// Follow-up: Can you solve the problem in O(1) extra memory space? 
// Related Topics 递归 链表 👍 1701 👎 0

package leetcode.editor.cn;

import leetcode.common.ListNode;

public class Code25_ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new Code25_ReverseNodesInKGroup().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode originalHeadPre = new ListNode(-1);
            originalHeadPre.next = head;

            ListNode pre = originalHeadPre;
            while (head != null) {
                ListNode tail = pre;
                // 依次得到k个元素的首尾元素
                for (int i = 0; i < k; i++) {
                    tail = tail.next;
                    if (tail == null) {
                        return originalHeadPre.next;
                    }
                }

                ListNode nextHead = tail.next;
                // 倒转、接回源链表
                ListNode[] htGroup = this.myReverse(head, tail);
                pre.next = htGroup[0];
                htGroup[1].next = nextHead;

                pre = htGroup[1];
                head = nextHead;
            }

            return originalHeadPre.next;
        }

        public ListNode[] myReverse(ListNode head, ListNode tail) {
            ListNode newNext = tail.next;
            ListNode cur = head;

            while (newNext != tail) {
                ListNode curNext = cur.next;
                cur.next = newNext;

                newNext = cur;
                cur = curNext;
            }
            return new ListNode[]{tail, head};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}