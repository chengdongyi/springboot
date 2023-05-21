package com.example.java8.stream.api;

import com.example.java8.domain.Employee;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 2.7 Sorted 排序
 */
public class SortedTest {

    private List<Employee> employees = Arrays.asList(
            new Employee(1004, "李四", 19, 6666.0),
            new Employee(1003, "张三", 18, 8888.0),
            new Employee(1005, "王五", 22, 7777.0),
            new Employee(1007, "田七", 22, 5555.0),
            new Employee(1006, "赵六", 22, 9999.0));

    @Test
    void testList() {

        List<String> lists = Arrays.asList("123acb", "123abc", "acbd", "abcd", "ddee", "ddaa");
        lists.sort(Comparator.reverseOrder());
        System.out.println("倒序:" + lists);

        lists.sort(Comparator.naturalOrder());
        System.out.println("正序:" + lists);

        // java8 Stream.sorted() 自然排序
        lists.stream().sorted().forEach(System.out::println);
    }

    /**
     * 对 Map 进行自然排序**
     */
    @Test
    void testMap() {

        // Map 自然排序
        Map<String, Object> maps = new HashMap<>();
        maps.put("abcd", 3);
        maps.put("123acb", 2);
        maps.put("ddee", 6);
        maps.put("123abc", 1);
        maps.put("ddaa", 5);
        maps.put("acbd", 4);

        maps.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(map -> System.out.println(map.getKey() + " : " + map.getValue()));

        Map<String, Object> result = new LinkedHashMap<>();
        maps.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(map -> result.put(map.getKey(), map.getValue()));
    }

    /**
     * 按 Id 排序
     */
    @Test
    void test1() {

        // 正序
        employees.stream().sorted(Comparator.comparing(Employee::getId)).forEach(System.out::println);
        System.out.println("-------------------------------------------");
        // 倒序
        employees.stream().sorted(Comparator.comparing(Employee::getId).reversed()).forEach(System.out::println);
    }

    /**
     * 按年龄进行排序(倒序) ，年龄相同按照工资进行排序(正序)
     */
    @Test
    void test2() {
        employees.stream().sorted(Comparator.comparing(Employee::getAge).reversed().thenComparing(Comparator.comparing(Employee::getSalary)))
                .forEach(System.out::println);
    }

}
