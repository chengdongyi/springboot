package com.example.java8;

import com.example.java8.domain.Employee;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 三、方法引用与构造器引用
 *
 * 1. 方法引用：
 *  当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用!
 *
 *  方法引用可以看做是Lambda表达式深层次的表达。换句话说，方法引用就是Lambda表达式，
 *  也就是函数式接口的一个实例，通过方法的名字来指向一个方法，可以认为是Lambda表达式的一个语法糖。
 *
 *  使用格式：类（或对象）:: 方法名
 *      1.1 对象 :: 实例方法
 *      1.2 类名 :: 静态方法
 *      1.3 类名 :: 实例方法
 *
 * 方法引用使用的要求:
 *      要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型相同!
 *
 * 2. 构造器引用
 *      和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致。
 *      抽象方法的返回值类型即为构造器所属的类的类型
 *      2.1 类名 :: new
 *
 * 3. 数组引用
 *      可以把数组看做是一个特殊的类，则写法与构造器引用一致。
 *      3.1 类型[] :: new
 */
public class MethodRefTest {

    /**
     * 1.1 对象 :: 实例方法
     * Consumer 中的 void accept(T t)
     * PrintStream 中的 void printLn(T t)
     */
    @Test
    void test1(){
        PrintStream ps = System.out;
        Consumer<String> con = (str) -> ps.println(str);
        con.accept("Hello World！");

        System.out.println("--------------------------------");

        Consumer<String> con2 = ps::println;
        con2.accept("Hello Java8！");

        Consumer<String> con3 = System.out::println;
    }

    /**
     * Supplier 中的 T get()
     * Employee 中的 String getName()
     */
    @Test
    void test2(){
        Employee emp = new Employee(101, "张三", 18, 9999.99);

        Supplier<String> sup = () -> emp.getName();
        System.out.println(sup.get());

        System.out.println("----------------------------------");

        Supplier<String> sup2 = emp::getName;
        System.out.println(sup2.get());
    }

    /**
     * 1.2 类名 :: 静态方法
     * Function 中的 R apply(T t);
     * Math 中的 long round(double a)
     */
    @Test
    void test3(){
        Function<Double, Long> fun = d -> Math.round(d);
        System.out.println(fun.apply(12.5));

        System.out.println("--------------------------------------------------");

        Function<Double, Long> fun2 = Math::round;
        System.out.println(fun2.apply(12.2));
    }

    /**
     * Comparator 中的 int compare(T o1, T o2)
     * Integer 中的 int compare(int x, int y)
     */
    @Test
    void test4(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        System.out.println("-------------------------------------");

        Comparator<Integer> com2 = Integer::compare;
    }

    /**
     * 1.3 类名 :: 实例方法
     * Comparator 中的 int compare(T o1, T o2)
     * String  中的 int s1.compareTo(s2)
     */
    @Test
    void test5(){
        Comparator<String> com = (s1, s2) -> s1.compareTo(s2);
        System.out.println(com.compare("abc", "abd"));

        System.out.println("-------------------------------------");

        Comparator<String> com2 = String :: compareTo;
        System.out.println(com2.compare("abc", "abd"));
    }

    /**
     * BiPredicate 中的 boolean test(T t, U u)
     * String  中的 boolean t.equals(u)
     */
    @Test
    void test6(){
        BiPredicate<String, String> bp = (t, u) -> t.equals(u);
        System.out.println(bp.test("abcde", "abcde"));

        System.out.println("-----------------------------------------");

        BiPredicate<String, String> bp2 = String::equals;
        System.out.println(bp2.test("abc", "abc"));
    }

    /**
     * Function 中的 R apply(T t)
     * Employee 中的 String getName()
     */
    @Test
    void test7(){
        Employee employee = new Employee(101, "张三", 18, 9999.99);

        Function<Employee, String> fun = emp -> emp.getName();
        System.out.println(fun.apply(employee));

        System.out.println("-----------------------------------------");

        Function<Employee, String> fun2 = Employee::getName;
        System.out.println(fun2.apply(employee));
    }

    /**
     * 2 构造器引用
     * Supplier 中的 T get()
     * Employee 中的 空参数构造器 Employee()
     */
    @Test
    void test8() {
        Supplier<Employee> sup = () -> new Employee();
        System.out.println(sup.get());

        System.out.println("------------------------------------");

        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());
    }

    /**
     * 3 数组引用
     * Function 中的 R apply(T t)
     */
    @Test
    void test9() {
        Function<Integer, String[]> fun = length -> new String[length];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        System.out.println("--------------------------");

        Function<Integer, String[]> fun2 = String[]::new;
        String[] strs2 = fun2.apply(20);
        System.out.println(strs2.length);
    }
}
