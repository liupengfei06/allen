package com.allen.linked;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * Given a linked arr, swap every two adjacent nodes and return its head.
 * You may not modify the values in the arr's nodes, only nodes itself may be changed.
 * Input: 1->2->3->4
 * Output: 2->1->4->3
 */
public class SwapPairs {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.append(2).append(3).append(4).append(5);
        head.print("Input");

        ListNode result = solution(head);

        result.print("Output");
    }

    /**
     * 循环
     *
     * @param head
     * @return
     */
    public static ListNode solution(ListNode head) {
        ListNode top = new ListNode(0);
        ListNode curr = top;
        curr.next = head;

        while (curr.next != null && curr.next.next != null){
            ListNode first = curr.next;
            ListNode second = curr.next.next;
            first.next = second.next;
            second.next = first;
            curr.next = second;
            curr = first;
        }

        return top.next;
    }


}