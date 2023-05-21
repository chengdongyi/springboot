package com.example.java8.stream.api;

import com.example.java8.domain.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 3.2 Reduce 归约
 */
public class ReduceTest {

    private List<Employee> employees = Arrays.asList(
            new Employee(1004, "李四", 19, 6666.0),
            new Employee(1003, "张三", 18, 8888.0),
            new Employee(1005, "王五", 22, 7777.0),
            new Employee(1007, "田七", 22, 5555.0),
            new Employee(1006, "赵六", 22, 9999.0));

    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Test
    void test1() {

        int sum = Arrays.stream(numbers).reduce(0, (a, b) -> a + b);    // 55
        int sum1 = Arrays.stream(numbers).reduce(10, (a, b) -> a + b);  // 65
        int sum2 = Arrays.stream(numbers).reduce(0, Integer::sum);      // 55
        System.out.println("sum:" + sum);
        System.out.println("sum1:" + sum1);
        System.out.println("sum2:" + sum2);

        int sum3 = lists.stream().reduce(0, (a, b) -> a - b);   // -55
        int sum4 = lists.stream().reduce(1, (a, b) -> a * b);   // 3628800
        System.out.println("sum3:" + sum3);
        System.out.println("sum4:" + sum4);

        // 求所有员工薪资总和
        Double salary = employees.stream().map(Employee::getSalary).reduce(0.0, Double::sum);
        System.out.println("salary:" + salary);
    }

    @Test
    void test2() {

        Optional<Double> sum = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        Optional<Double> min = employees.stream().map(Employee::getSalary).reduce(Double::min);
        Optional<Double> max = employees.stream().map(Employee::getSalary).reduce(Double::max);

        System.out.println(sum.get());
        System.out.println(min);
        System.out.println(max);
    }

    @Test
    void test3() {

        String[] arr = {"a", "b", "c", "d", "e"};
        String join = String.join("|", arr);
        String reduce = Arrays.stream(arr).reduce("", (a, b) -> a + "|" + b);
        System.out.println("join:" + join);
        System.out.println("reduce:" + reduce);
    }

}
