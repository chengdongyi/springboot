package com.example.java8;

import com.example.java8.domain.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 二、函数式接口
 * <p>
 * 只包含一个抽象方法的接口，称为函数式接口
 * 可以在一个接口上使用 @Functionallnterface 注解，这样做可以检查它是否是一个函数式接口
 * <p>
 * Java8 内置的四大核心函数式接口
 * Consumer<T> 消费型接口: void accept(T t);
 * Supplier<T> 供给型接口: T get();
 * Function<T, R> 函数型接口: R apply(T t);
 * Predicate<T> 断言型接口: boolean test(T t);
 */
public class FunctionalTest {

    /*
     * 1. Consumer<T> 消费型接口: void accept(T t);
     */
    @Test
    void testConsumer1() {
        Employee emp = new Employee();
        //接受一个参数
        Consumer<Employee> consumer = employee -> employee.setName("张三");
        consumer.accept(emp);
        System.out.println(emp.getName());
    }

    @Test
    void testConsumer2() {
        Consumer<Double> consumer = money -> System.out.println("KFC，每次消费：" + money + "元");
        consumer.accept(20.0);
    }

    /*
     * 2. Supplier<T> 供给型接口: T get();
     */
    @Test
    void testSupplier1() {

        Supplier<String> supplierStr = String::new;
        System.out.println(supplierStr.get());

        // --------------------------------------------

        Supplier<Employee> supplier = Employee::new;
        Employee emp = supplier.get();
        emp.setName("张三");
        System.out.println(emp.getName());
    }

    @Test
    void testSupplier2() {

        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));

        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    /**
     * 需求：产生指定个数的整数，并放入集合中
     */
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>(num);

        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }

        return list;
    }

    @Test
    void testSupplier3() {

        Supplier<Integer> supplier = () -> (int) (Math.random() * 100);
        List<Integer> lists = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            Integer n = supplier.get();
            lists.add(n);
        }

        for (Integer num : lists) {
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        Supplier<Integer> supplier = () -> (int) (Math.random() * 100);
        List<Integer> lists = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            Integer n = supplier.get();
            lists.add(n);
        }

        for (Integer num : lists) {
            System.out.println(num);
        }
    }

    /*
     * 3. Function<T, R> 函数型接口: R apply(T t);
     */
    @Test
    void testFunction1() {
        Function<Integer, Integer> function1 = x -> x * 2;
        int num = function1.apply(4);
        System.out.println(num);// 8

        Function<Integer, String> function2 = x -> x * 2 + "dd";
        String strr = function2.apply(4);
        System.out.println(strr);// 8dd

        Function<String, String> strFunction1 = str -> new String(str);
        System.out.println(strFunction1.apply("aa"));//aa

        Function<String, String> strFunction2 = String::new;
        System.out.println(strFunction2.apply("bb"));//bb

        Function<String, Employee> objFunction1 = name -> {
            Employee emp = new Employee();
            emp.setName(name);
            return emp;
        };
        Employee emp = objFunction1.apply("王五");
        System.out.println(emp.getName());

    }

    @Test
    void testFunction2() {
        String newStr = strHandler("\t\t\t Java、C++、Python   ", str -> str.trim());
        System.out.println(newStr);

        String subStr = strHandler("Java、C++、Python", str -> str.substring(1, 4));
        System.out.println(subStr);
    }

    /*
     * 需求：用于处理字符串
     */
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    /*
     * 4. Predicate<T> 断言型接口: boolean test(T t);
     */
    @Test
    void testPredicate1() {

        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

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
    void testPredicate2() {
        List<String> list = Arrays.asList("Hello", "Java", "Lambda", "www", "ok");
        List<String> strList = filterStr(list, (s) -> s.length() > 3);

        for (String str : strList) {
            System.out.println(str);
        }
    }

    /**
     * 需求：将满足条件的字符串，放入集合中
     */
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();

        for (String str : list) {
            if (pre.test(str)) {
                strList.add(str);
            }
        }

        return strList;
    }
}
