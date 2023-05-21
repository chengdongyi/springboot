package com.example.java8.stream.api;

import com.example.java8.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2.5 map 映射
 * 2.6 flatMap
 * 注：flatMap(Function f) 相当于两个 List 的合并方法 addAll(Collection<? extends E> c)
 */
public class MapTest {

    private List<Employee> employees = Arrays.asList(
            new Employee(1004, "李四", 19, 6666.0),
            new Employee(1003, "张三", 18, 8888.0),
            new Employee(1005, "王五", 20, 7777.0),
            new Employee(1007, "田七", 22, 5555.0),
            new Employee(1006, "赵六", 21, 9999.0));

    /*
     * 1. map 映射
     * map(Function f)
     */
    @Test
    void testMap() {
        // 1. 获取工资大于7000的员工姓名
        List<String> names = employees.stream().filter(emp -> emp.getSalary() > 7000)
                .map(Employee::getName).collect(Collectors.toList());
        // [张三, 王五, 赵六]
        System.out.println(names);
    }

    /*
     * 2. flatMap
     * flatMap(Function f)
     * 1. 单词提取
     */
    @Test
    void testFlatMap1() {
        List<String> words = Arrays.asList("hello", "hello;word", "java", "c++;python");
        List<String> result = words.stream()
                // 将单词按照空格切合，返回Stream<String[]>类型的数据
                .map(word -> word.split(";"))
                // 将Stream<String[]>转换为Stream<String>
                .flatMap(Arrays::stream)
                // 去重
                .distinct()
                .collect(Collectors.toList());
        // [hello, word, java, c++, python]
        System.out.println(result);

    }

    /*
     * 2. 元素抽取
     */
    @Test
    void testFlatMap2() {
        List<Employee> emps = Arrays.asList(
                new Employee(1004, "张三;李四", 19, 6666.0),
                new Employee(1003, "王五;赵六", 18, 8888.0));


        List<String> names1 = emps.stream().map(Employee::getName).flatMap(name -> Arrays.stream(name.split(";"))).collect(Collectors.toList());
        System.out.println(names1);
        List<String> names2 = emps.stream().map(Employee::getName).map(name -> name.split(";")).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println(names2);
    }

    /*
     * 3. 元素抽取
     */
    @Test
    void testFlatMap3() {
        List<String> hobby1 = Arrays.asList("java", "c", "音乐");
        List<String> hobby2 = Arrays.asList("c++", "c", "游戏");
        User zhansan = new User(1, "张三", hobby1);
        User lisi = new User(2, "李四", hobby2);
        ArrayList<User> users = new ArrayList<>();
        users.add(zhansan);
        users.add(lisi);

        // 将集合中每个用户的爱好进行计算，取并集
        List<String> result = users.stream()
                .map(user -> user.hobby)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class User {
        int id;
        String name;
        List<String> hobby;
    }

    @Test
    void testFlatMap4() {
//        List<Employee> employees1 = Arrays.asList(
//                new Employee(1004, "1002", "李四", 19, 6666.0),
//                new Employee(1003, "1001", "张三", 18, 8888.0),
//                new Employee(1005, "1001", "王五", 20, 7777.0),
//                new Employee(1007, "1003", "田七", 22, 5555.0),
//                new Employee(1006, "1003", "赵六", 21, 9999.0));
//        List<Employee> employees2 = Arrays.asList(
//                new Employee(1006, "1004", "李白", 18, 5200.0),
//                new Employee(1007, "1004", "杜甫", 20, 11314.0),
//                new Employee(1008, "1004", "李清照", 18, 9860.0),
//                new Employee(1009, "1004", "王之涣", 19, 6890.0));
//        // 查询出为 1001 部门员工信息，匹配出与该部门员工信息年龄相仿的员工信息
//        List<Employee> list6 = employees1.stream().filter(employees -> employees.getEmpId().equals("1001")).distinct().
//                flatMap(emp -> employees2.stream().filter(emp2 -> emp.getAge().toString().equals(emp2.getAge().toString()))).
//                collect(Collectors.toList());
//        list6.forEach(System.out::println);
    }

}
