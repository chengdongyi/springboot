package com.example.java8.stream.api;

import com.example.java8.domain.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 4.1 filter 过滤
 * 4.2 limit 截取
 * 4.3 skip 跳过元素
 */
public class FilterTest {

    private List<Employee> employees = Arrays.asList(
            new Employee(1004, "李四", 19, 6666.0),
            new Employee(1003, "张三", 18, 8888.0),
            new Employee(1005, "王五", 20, 7777.0),
            new Employee(1007, "田七", 22, 5555.0),
            new Employee(1006, "赵六", 21, 9999.0));

    private List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

    /*
     * 4.1 filter 过滤
     * filter(Predicate<? super T> predicate)
     */
    @Test
    void test1() {

        // 1. 过滤 num > 5
        Predicate<Integer> p1 = i -> i > 5;

        // 2. 过滤 num < 20
        Predicate<Integer> p2 = i -> i < 20;

        // 3. 过滤 所有的奇数
        Predicate<Integer> p3 = i -> i % 2 == 0;

        List list = lists.stream().filter(p1.and(p2).and(p3)).collect(Collectors.toList());

        // 结果: [6, 8, 10, 12, 14]
        System.out.println(list.toString());

        // negate() : 取反
        list = lists.stream().filter(p1.and(p2).and(p3.negate())).collect(Collectors.toList());

        // 结果: [7, 9, 11, 13, 15]
        System.out.println(list.toString());
    }

    @Test
    void test2() {
        employees.stream().filter(emp -> emp.getSalary() > 7000).forEach(System.out::println);

        System.out.println("----------------------------------------");

        // 1. 过滤 年龄大于 18 岁
        Predicate<Employee> p1 = emp -> emp.getAge() > 18;
        // 2. 过滤 工资大于 7000
        Predicate<Employee> p2 = emp -> emp.getSalary() > 7000;
        // 2. 过滤 工号大于 7000
        Predicate<Employee> p3 = emp -> emp.getId() > 1005;
        employees.stream().filter(p1.and(p2).and(p3)).forEach(System.out::println);

        System.out.println("----------------------------------------");

        employees.stream().filter(emp -> {
            return emp.getAge() > 18 && emp.getSalary() > 7000 && emp.getId() > 1005;
        }).forEach(System.out::println);
    }

    /*
     * 4.2 limit 截取
     * limit(long maxSize)
     */
    @Test
    void test3() {
        // 1. 获取前 5 个元素
        List list = lists.stream().limit(5).collect(Collectors.toList());

        // 结果: [1, 2, 3, 4, 5]
        System.out.println(list.toString());

        System.out.println("----------------------------------------");

        // 2. 获取前 3 个对象
        employees.stream().limit(3).forEach(System.out::println);
    }
    /*
     * 4.3 skip 跳过元素
     */
    @Test
    void test4() {

        // 1. 跳过前 5 个元素
        List list = lists.stream().skip(5).collect(Collectors.toList());
        // 结果: [6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
        System.out.println(list.toString());

        // 2. 截取前 8 个元素, 跳过前 5 个元素
        list = lists.stream().limit(8).skip(5).collect(Collectors.toList());
        // 结果: [6, 7, 8]
        System.out.println(list.toString());

        // 3. 跳过前 5 个元素, 截取前 8 个元素
        list = lists.stream().skip(5).limit(8).collect(Collectors.toList());
        // 结果: [6, 7, 8, 9, 10, 11, 12, 13]
        System.out.println(list.toString());

        // 4. 跳过超过 list.size() 个数的元素
        list = lists.stream().skip(20).collect(Collectors.toList());
        // 结果: []
        System.out.println(list.toString());
    }
}

