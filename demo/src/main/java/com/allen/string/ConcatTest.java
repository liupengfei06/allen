package com.allen.string;

public class ConcatTest {

    public static void main(String[] args) {
        String tmp = "1";

        // 没有改变原值
        tmp.concat("2").concat("3");
        System.out.println(tmp);

        // 重新赋值
        tmp = tmp.concat("a").concat("b");
        System.out.println(tmp);

    }
}
