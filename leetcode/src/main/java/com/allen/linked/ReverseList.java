package com.allen.linked;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * Definition for singly-linked list.
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 */
public class ReverseList {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.append(2).append(3).append(4).append(5);
        head.print("Input:");

        ListNode reverseList = solution(head);
        reverseList.print("Output:");

    }

    /**
     * 循环处理
     * @param head
     * @return
     */
    public static ListNode solution(ListNode head) {
        ListNode headNode = null;
        ListNode preNode = null;
        while (head != null) {
            headNode = head;
            head = head.next;
            headNode.next = preNode;
            preNode = headNode;
        }
        return preNode;
    }


}