package com.example.test;

/**
 * 条件判断
 */
public class Demo4 {

    public static void main(String[] args) {
        for (int i = 1; i < 1000; i++) {
            execute(i);
        }

        System.out.println("循环结束。。");

        System.out.println(123);
    }

    private static void execute(int num) {
        int rs = ((num + 3) * 5 + 9) / num;
        System.out.println(rs);
    }
}
