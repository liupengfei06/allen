package com.allen.linked;


import lombok.Data;
import lombok.Getter;

@Getter
public class ListNode {

    int val;

    ListNode next;

    ListNode(int x) {
        val = x;
    }

    /**
     * 追加链表
     * @param nextVal
     * @return
     */
    ListNode append(int nextVal) {
        this.next = new ListNode(nextVal);
        return next;
    }

    /**
     * 打印单链表
     *
     * @param tag
     */
    void print(String tag) {
        ListNode curr = this;
        String result = tag + ": " + val;
        while (curr.next != null) {
            result += " -> " + curr.next.val;
            curr = curr.next;
        }
        System.out.println(result);
    }
}
