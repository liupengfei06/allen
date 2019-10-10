package com.allen.sort;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class RandomNumber {

    public static void main(String[] args) {
        int[] list = {1, 2, 3, 4, 5, 6, 7};
        int[] result = RandomNumber.getList(list);
        for (int i = 0; i < result.length; i++) {
            System.out.print("[" + result[i] + "] ");
        }
    }




    public static int[] getList(int[] list) {
        //数组长度
        int count = list.length;
        //结果集
        int[] resultList = new int[count];
        //用一个LinkedList作为中介
        LinkedList<Integer> temp = new LinkedList<Integer>();
        //初始化temp
        for (int i = 0; i < count; i++) {
            temp.add(list[i]);
        }
        //取数
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int num = rand.nextInt(count - i);
            resultList[i] = temp.get(num);
            temp.remove(num);
        }

        return resultList;
    }


}