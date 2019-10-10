package com.allen.linked;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * Definition for singly-linked arr.
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 */
public class ReverseList {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.append(2).append(3).append(4).append(5);
        head.print("Input");

         ListNode reverseList = solution(head);
//        ListNode reverseList = recursiveSolution(head, null);

        reverseList.print("Output");
    }

    /**
     * 循环
     *
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


    /**
     * 递归实现
     *
     * @param head
     * @param newNode
     * @return
     */
    public static ListNode recursiveSolution(ListNode head, ListNode newNode) {
        if (head == null) {
            return newNode;
        }
        ListNode next = head.next;
        head.next = newNode;
        return recursiveSolution(next, head);

    }

}