package com.example.java8;

import com.example.java8.domain.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 四、Stream Api
 *
 * 1. Stream
 *   Stream 关注的是对数据的运算，与CPU打交道
 *   集合关注的是数据的存储，与内存打交道
 *
 * 2. Stream 注意事项
 *  ① Stream 自己不会存储元素。
 *  ② Stream 不会改变源对象。相反，它会返回一个持有结果的新 Stream。
 *  ③ Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *
 * 3. Stream 的 执行流程
 *  ① Stream 的实例化
 *  ② 中间操作（过滤、映射...）
 *  ③ 终止操作
 *
 * 4. 说明
 *  ① 一个中间操作链，对数据源的数据进行处理
 *  ② 一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 *
 * Stream 分为顺序流和并行流
 * Stream 的 四种创建方式
 *  ① 通过集合
 *  ② 通过数组
 *  ③ 通过 Stream 的 of()
 *  ④ 创建无限流
 *
 */
public class StreamApiTest {

    private List<Employee> employees = Arrays.asList(
            new Employee(1004, "李四", 19, 6666.0),
            new Employee(1003, "张三", 18, 8888.0),
            new Employee(1005, "王五", 20, 7777.0),
            new Employee(1007, "田七", 22, 5555.0),
            new Employee(1006, "赵六", 21, 9999.0));

    /*
     * 1. 通过集合创建 Stream
     */
    @Test
    void test1() {
        // 创建一个顺序流
        Stream<Employee> system = employees.stream();
        // 创建一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();
    }

    /*
     * 2. 通过数组创建 Stream
     */
    @Test
    void test2() {
        int[] arr = {1, 3, 5, 7, 9};
        IntStream stream = Arrays.stream(arr);
    }

    /*
     * 3. 通过 Stream 的 of() 创建 Stream
     */
    @Test
    void test3() {
        Stream<String> stream = Stream.of("java", "c++", "python");
    }

    /*
     * 4. 无限流
     */
    @Test
    void test4() {
        // 迭代 static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        // 遍历前10个元素
        Stream.iterate(0, t -> t +2).limit(10).forEach(System.out::println);

        // 生成 static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

}
