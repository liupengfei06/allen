package com.allen.linked;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/linked-list-cycle
 * <p>
 * 判断链表是否有环
 */
public class HasCycle {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.append(2).append(3).append(4);

        System.out.println(solution2(head));

    }

    /**
     * 快慢指针
     *
     * @param head
     * @return
     */
    public static boolean solution(ListNode head) {
        if (head == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    /**
     * Set是否包含
     *
     * @param head
     * @return
     */
    public static boolean solution2(ListNode head) {
        if (head == null)
            return false;
        HashSet<ListNode> set = new HashSet<ListNode>();

        while (head.next != null) {
            if (set.contains(head.next)) {
                return true;
            } else {
                set.add(head);
                head = head.next;
            }
        }

        return false;
    }


}