//Given a linked list, swap every two adjacent nodes and return its head. You 
//must solve the problem without modifying the values in the list's nodes (i.e., 
//only nodes themselves may be changed.) 
//
// 
// Example 1: 
//
// 
//Input: head = [1,2,3,4]
//Output: [2,1,4,3]
// 
//
// Example 2: 
//
// 
//Input: head = []
//Output: []
// 
//
// Example 3: 
//
// 
//Input: head = [1]
//Output: [1]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is in the range [0, 100]. 
// 0 <= Node.val <= 100 
// 
// Related Topics 递归 链表 👍 1424 👎 0

package leetcode.editor.cn;

import leetcode.common.ListNode;

public class Code24_SwapNodesInPairs {
    public static void main(String[] args) {
        Solution solution = new Code24_SwapNodesInPairs().new Solution();
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
        public ListNode swapPairs(ListNode head) {
            ListNode newHead = new ListNode(-1);
            newHead.next = head;

            ListNode pre = newHead;
            while (head != null && head.next != null) {
                ListNode first = head;
                ListNode second = head.next;

                first.next = second.next;
                second.next = first;
                pre.next = second;

                pre = first;
                head = first.next;
            }


            return newHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}