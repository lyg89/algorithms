//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 8039 👎 0

package leetcode.editor.cn;

import leetcode.common.ListNode;

public class Code2_AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new Code2_AddTwoNumbers().new Solution();
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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            //ListNode res = new ListNode(0);
            //ListNode cur = res;
            //boolean isUp = false;
            //while (l1 != null || l2 != null) {
            //    if (l1 != null) {
            //        cur.val = cur.val + l1.val;
            //        l1 = l1.next;
            //    }
            //    if (l2 != null) {
            //        cur.val = cur.val + l2.val;
            //        l2 = l2.next;
            //    }
            //    if (isUp) {
            //        cur.val = cur.val + 1;
            //    }
            //    if (cur.val >= 10) {
            //        cur.val = cur.val - 10;
            //        isUp = true;
            //        if (l1 == null && l2 == null) {
            //            cur.next = new ListNode(1);
            //            break;
            //        }
            //    } else {
            //        isUp = false;
            //    }
            //
            //    if (l1 != null || l2 != null) {
            //        cur.next = new ListNode(0);
            //        cur = cur.next;
            //    }
            //}
            //return res;

            ListNode result = new ListNode(0);

            int carry = 0;
            ListNode p = l1, q = l2, current = result;

            while (p != null || q != null) {
                int x = p != null ? p.val : 0;
                int y = q != null ? q.val : 0;
                int sum = x + y + carry;

                current.next = new ListNode(sum % 10);
                carry = sum / 10;

                current= current.next;

                p = p != null ? p.next : null;
                q = q != null ? q.next : null;
            }

            if (carry > 0){
                current.next = new ListNode(carry);
            }

            return result.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}