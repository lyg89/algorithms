//Given the head of a singly linked list, return the middle node of the linked l
//ist. 
//
// If there are two middle nodes, return the second middle node. 
//
// 
// Example 1: 
//
// 
//Input: head = [1,2,3,4,5]
//Output: [3,4,5]
//Explanation: The middle node of the list is node 3.
// 
//
// Example 2: 
//
// 
//Input: head = [1,2,3,4,5,6]
//Output: [4,5,6]
//Explanation: Since the list has two middle nodes with values 3 and 4, we retur
//n the second one.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is in the range [1, 100]. 
// 1 <= Node.val <= 100 
// 
// Related Topics 链表 双指针 
// 👍 386 👎 0


package leetcode.editor.cn;

import leetcode.common.ListNode;

public class MiddleOfTheLinkedList {
    public static void main(String[] args) {
        Solution solution = new MiddleOfTheLinkedList().new Solution();
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
        public ListNode middleNode(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode slow = head;
            ListNode fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}