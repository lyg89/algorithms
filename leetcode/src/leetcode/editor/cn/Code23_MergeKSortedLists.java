//You are given an array of k linked-lists lists, each linked-list is sorted in 
//ascending order. 
//
// Merge all the linked-lists into one sorted linked-list and return it. 
//
// 
// Example 1: 
//
// 
//Input: lists = [[1,4,5],[1,3,4],[2,6]]
//Output: [1,1,2,3,4,4,5,6]
//Explanation: The linked-lists are:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//merging them into one sorted list:
//1->1->2->3->4->4->5->6
// 
//
// Example 2: 
//
// 
//Input: lists = []
//Output: []
// 
//
// Example 3: 
//
// 
//Input: lists = [[]]
//Output: []
// 
//
// 
// Constraints: 
//
// 
// k == lists.length 
// 0 <= k <= 10⁴ 
// 0 <= lists[i].length <= 500 
// -10⁴ <= lists[i][j] <= 10⁴ 
// lists[i] is sorted in ascending order. 
// The sum of lists[i].length will not exceed 10⁴. 
// 
// Related Topics 链表 分治 堆（优先队列） 归并排序 👍 2001 👎 0

package leetcode.editor.cn;

import leetcode.common.ListNode;

public class Code23_MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new Code23_MergeKSortedLists().new Solution();
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
        public ListNode mergeKLists(ListNode[] lists) {
            // solution 1
            //ListNode ans = null;
            //for (ListNode list : lists) {
            //    ans = mergeTwoList(list, ans);
            //}
            //return ans;

            // solution 2
            return merge(lists, 0, lists.length - 1);
        }

        public ListNode merge(ListNode[] lists, int l, int r) {
            if (l == r) {
                return lists[l];
            }

            if (l > r) {
                return null;
            }

            int mid = (l + r) << 1;
            return mergeTwoList(merge(lists, l, mid), merge(lists, mid, r));
        }

        public ListNode mergeTwoList(ListNode list1, ListNode list2) {

            if (list1 == null || list2 == null) {
                return list1 == null ? list2 : list1;
            }
            ListNode head = new ListNode(0);
            ListNode tail = head;
            ListNode cur1 = list1;
            ListNode cur2 = list2;

            while (cur1 != null && cur2 != null) {
                if (cur1.val <= cur2.val) {
                    tail.next = cur1;
                    tail = tail.next;
                    cur1 = cur1.next;
                } else {
                    tail.next = cur2;
                    tail = tail.next;
                    cur2 = cur2.next;
                }
            }
            tail.next = cur1 != null ? cur1 : cur2;
            return head.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}