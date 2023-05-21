package com.example.java8.stream.api;

import com.example.java8.domain.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 4.4 distinct 去重
 *
 * 注：对象进行去重时，需重写  hashCode() 和 equals() 方法，否则去重失败
 * 使用 Lombok 时，@Data 注解默认包含 get()、 set()、toString()、hashCode() 和 equals() 方法
 */
public class DistinctTest {

    private List<Employee> employees = Arrays.asList(
            new Employee(3, "张三", 18, 8888.0),
            new Employee(4, "李四", 19, 6666.0),
            new Employee(4, "李四", 19, 6666.0),
            new Employee(5, "王五", 20, 7777.0));

    private List<Integer> lists = Arrays.asList(1, 2, 2, 2, 3, 3, 3, 4, 5);

    @Test
    void test1() {
        List<Integer> list = lists.stream().distinct().collect(Collectors.toList());
        // 结果: [1, 2, 3, 4, 5]
        System.out.println(list.toString());
    }

    @Test
    void test2() {
        employees.stream().distinct().forEach(System.out::println);

        // Employee(id=3, name=张三, age=18, salary=8888.0)
        // Employee(id=4, name=李四, age=19, salary=6666.0)
        // Employee(id=5, name=王五, age=20, salary=7777.0)
    }
}
