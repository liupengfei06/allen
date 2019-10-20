package com.allen.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ArraysTest {

    public static void main(String[] args) {
//        intDemo();
//        integerDemo();

        arr2List();
    }

    public static void arr2List() {

        // 最简便的方法(推荐)
        List list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        System.out.println("list=" + list.getClass());

        Integer[] myArray = {1, 2, 3};
        List list1 = Arrays.stream(myArray).collect(Collectors.toList());
        System.out.println("list1=" + list1.getClass());

        //基本类型也可以实现转换（依赖boxed的装箱操作）
        int[] myArray2 = {1, 2, 3};
        List list2 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
        System.out.println("list2=" + list2.getClass());

    }

    public static void integerDemo() {
        Integer[] myArray = {1, 2, 3};
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size());//1
        System.out.println(myList.get(0));//数组地址值
//        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException

        myList.add(4);//运行时报错：UnsupportedOperationException
        myList.remove(1);//运行时报错：UnsupportedOperationException
        myList.clear();//运行时报错：UnsupportedOperationException

    }


    public static void intDemo() {
        int[] myArray = {1, 2, 3};
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size());//1
        System.out.println(myList.get(0));//数组地址值
//        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
        int[] array = (int[]) myList.get(0);
        System.out.println(array[0]);//1
    }


}
