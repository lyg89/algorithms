//Given a linked list, return the node where the cycle begins. If there is no cy
//cle, return null. 
//
// There is a cycle in a linked list if there is some node in the list that can 
//be reached again by continuously following the next pointer. Internally, pos is 
//used to denote the index of the node that tail's next pointer is connected to. N
//ote that pos is not passed as a parameter. 
//
// Notice that you should not modify the linked list. 
//
// 
// Example 1: 
//
// 
//Input: head = [3,2,0,-4], pos = 1
//Output: tail connects to node index 1
//Explanation: There is a cycle in the linked list, where tail connects to the s
//econd node.
// 
//
// Example 2: 
//
// 
//Input: head = [1,2], pos = 0
//Output: tail connects to node index 0
//Explanation: There is a cycle in the linked list, where tail connects to the f
//irst node.
// 
//
// Example 3: 
//
// 
//Input: head = [1], pos = -1
//Output: no cycle
//Explanation: There is no cycle in the linked list.
// 
//
// 
// Constraints: 
//
// 
// The number of the nodes in the list is in the range [0, 104]. 
// -105 <= Node.val <= 105 
// pos is -1 or a valid index in the linked-list. 
// 
//
// 
// Follow up: Can you solve it using O(1) (i.e. constant) memory? 
// Related Topics 哈希表 链表 双指针 
// 👍 1129 👎 0


package leetcode.editor.cn;

import leetcode.common.ListNode;

public class LinkedListCycleIi {
    public static void main(String[] args) {
        Solution solution = new LinkedListCycleIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode slow = head;
            ListNode fast = head;

            // 注意这里的条件，只有前者时，内部fast赋值需要判断，但会导致错误的 slow==fast 的情况
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;

                // 快慢指针相遇时，证明存在环，此时定义新指针
                // 参考：https://lyg89.blog.csdn.net/article/details/114274086
                if (slow == fast) {
                    ListNode pit = head;
                    while (pit != slow) {
                        slow = slow.next;
                        pit = pit.next;
                    }
                    return pit;
                }
            }

            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}