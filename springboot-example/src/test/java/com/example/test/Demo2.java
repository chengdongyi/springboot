package com.example.test;

/**
 * 步过 和 步入 调试的使用
 */
public class Demo2 {

    public static void main(String[] args) {

        int a = add(1, 2);
        System.out.println(a);

        int b = add(2, 3);
        System.out.println(b);

        int result = a + b;
        System.out.println(result);

        System.out.println("end...");
    }

    private static int add(int a, int b) {
        int result = a + b;
        return result;
    }

}
