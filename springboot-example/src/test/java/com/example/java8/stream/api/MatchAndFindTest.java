package com.example.java8.stream.api;

import com.example.java8.domain.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 3.1  匹配与查找
 */
public class MatchAndFindTest {

    private List<Employee> employees = Arrays.asList(
            new Employee(1004, "李四", 19, 6666.0),
            new Employee(1003, "张三", 18, 8888.0),
            new Employee(1005, "王五", 20, 7777.0),
            new Employee(1007, "田七", 22, 5555.0),
            new Employee(1006, "赵六", 21, 9999.0));

    @Test
    void test() {
        // 1. 是否所有的员工的年龄都大于20岁
        boolean allMatch = employees.stream().allMatch(emp -> emp.getAge() > 20);
        System.out.println(allMatch); // false

        // 2. 是否存在员工的工资大于 8000
        boolean anyMatch = employees.stream().anyMatch(emp -> emp.getSalary() > 8000);
        System.out.println(anyMatch); // true

        // 3. 是否没有 姓 "张" 的员工
        boolean noneMatch = employees.stream().noneMatch(emp -> emp.getName().startsWith("张"));
        System.out.println(noneMatch); // false

        // 4. 返回第一个元素
        Optional<Employee> opEmployee = employees.stream().findFirst();
        Employee employee = employees.stream().findFirst().get();
        System.out.println(employee);

        // 5. findAny 返回任意元素
        opEmployee = employees.stream().findAny();
        employee = employees.stream().findAny().get();
        System.out.println(employee);

    }

    @Test
    void test2() {
        // 6. 年龄都大于20岁的总人数
        long count = employees.stream().filter(emp -> emp.getAge() > 20).count();
        System.out.println(count);

        // 7. 返回最高工资
        Optional<Double> max = employees.stream().map(Employee::getSalary).max(Double::compareTo);
        System.out.println(max);

        Double salary = employees.stream().map(Employee::getSalary).max(Double::compareTo).get();
        System.out.println(salary);

        // 8. 返回最低工资的员工
        Optional<Employee> min = employees.stream().min((e1, e2) -> e1.getSalary().compareTo(e2.getSalary()));
        System.out.println(min);

        Employee employee = employees.stream().min((e1, e2) -> e1.getSalary().compareTo(e2.getSalary())).get();
        System.out.println(employee);
        employee = employees.stream().min(Comparator.comparing(Employee::getSalary)).get();
        System.out.println(employee);

        // 9. 内部迭代
        employees.stream().forEach(System.out::println);
    }
}
