/**
 * @description:
 * @author: liyaguang
 * @create: 2018-10-15 19:21
 **/
public class S2AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

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

    public static ListNode revertListNode(ListNode ln) {
        ListNode pre = null;
        ListNode cur = ln;
        ListNode next = ln.next;

        while (cur != null) {
            cur.next = pre;

            pre = cur;
            cur = next;
            next = cur != null ? next.next : null;
        }

        return pre;
    }

    public static void main(String[] args) {
        //ListNode l1 = new ListNode(1);
        //ListNode l2 = new ListNode(2);
        //ListNode l3 = new ListNode(3);
        //ListNode l4 = new ListNode(4);
        //
        //l1.next = l2;
        //l2.next = l3;
        //l3.next = l4;
        //
        //System.out.println("原数据：");
        //ListNode temp = l1;
        //while (temp != null) {
        //    System.out.println(temp.val + " ");
        //    temp = temp.next;
        //}
        //
        //System.out.println("revert数据：");
        //temp = revertListNode(l1);
        //while (temp != null) {
        //    System.out.println(temp.val + " ");
        //    temp = temp.next;
        //}

        ListNode l11 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);

        l11.next = l12;
        l12.next = l13;

        ListNode l21 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);

        l21.next = l22;
        l22.next = l23;

        ListNode res = addTwoNumbers(l11, l21);
        while (res != null) {
            System.out.println(res.val + " ");
            res = res.next;
        }
    }
}

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
