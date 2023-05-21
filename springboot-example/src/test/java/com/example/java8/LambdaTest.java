package com.example.java8;

import org.junit.jupiter.api.Test;

/**
 * 一、Lambda 表达式
 *
 * Lambda 表达式，也可称为闭包，允许把函数作为一个参数，使代码更简洁
 *
 * Lambda 表达式分为两部分：
 * 左侧：Lambda 表达式的参数列表(接口中的抽象方法的形参列表)
 * 右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体(重写的抽象方法的方法体)
 *
 * Lambda 的语法格式：
 * 语法格式一：无参数，无返回值
 *    () -> {System.out.println("Hello Lambda!")};
 *
 * 语法格式二：有一个参数，无返回值
 *    (String s) -> {System.out.println(s)};
 *
 * 语法格式三：数据类型可以省略，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
 *    (x, y) -> {return Integer.compare(x, y)};
 *
 * 语法格式四：若只有一个参数，小括号可以省略不写
 *    s -> {System.out.println(s)};
 *
 * 语法格式五：1.5 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *    Comparator<Integer> com = (x, y) -> {
 *        System.out.println("函数式接口");
 *        return Integer.compare(x, y);
 *    };
 *
 * 语法格式六：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
 *    Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 *
 * PS：Lambda 表达式的本质：作为函数式接口的实例
 */
public class LambdaTest {

    @Test
    void test1() {
        // jdk 1.7 前，必须是 final
        int num = 0;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };
        r.run();

        System.out.println("-------------------------------");

        Runnable r1 = () -> System.out.println("Hello Lambda!");
        r1.run();
    }

}
