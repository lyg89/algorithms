/**
 * @description: InsertionSort
 * @author: liyaguang
 * @create: 2019-04-02 13:12
 **/
public class S147InsertionSort {

    public static ListNode insertionSortList(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode pre = head;
        ListNode current = head.next;

        while (current != null) {

            // confirm the right position
            int curValue = current.val;
            ListNode i = head;
            ListNode iPre = null;
            while (i != null && i != current && i.val <= curValue) {
                iPre = i;
                i = i.next;
            }

            if (i == current) {
                pre = current;
                current = current.next;
            } else {
                pre.next = current.next;
                current.next = i;
                if (iPre != null) {
                    iPre.next = current;
                } else {
                    // i is the first value, then modify head reference
                    head = current;
                }
                current = pre.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        //ListNode l1 = new ListNode(4);
        //ListNode l2 = new ListNode(2);
        //ListNode l3 = new ListNode(1);
        //ListNode l4 = new ListNode(3);
        //l1.next = l2;
        //l2.next = l3;
        //l3.next = l4;
        //
        //ListNode listNode = insertionSortList(l1);
        //System.out.println(listNode);


        ListNode l11 = new ListNode(-1);
        ListNode l12 = new ListNode(5);
        ListNode l13 = new ListNode(3);
        ListNode l14 = new ListNode(4);
        ListNode l15 = new ListNode(0);
        l11.next = l12;
        l12.next = l13;
        l13.next = l14;
        l14.next = l15;
        ListNode listNode = insertionSortList(l11);
        System.out.println(listNode);
    }

}
