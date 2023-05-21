package com.example.java8.stream.api;

import com.alibaba.fastjson2.JSON;
import com.example.java8.domain.Employee;
import com.example.java8.domain.Product;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 3.3 collect 收集
 */
public class CollectorsTest {

    private List<Employee> employees = Arrays.asList(
            new Employee(1004, "李四", 19, 6666.0),
            new Employee(1003, "张三", 18, 8888.0),
            new Employee(1005, "王五", 22, 7777.0),
            new Employee(1007, "田七", 22, 5555.0),
            new Employee(1006, "赵六", 22, 9999.0));

    @Test
    public void test1() {

        List<String> list = employees.stream().map(Employee::getName).collect(Collectors.toList());

        Set<Employee> set = employees.stream().filter(emp -> emp.getSalary() > 7000).collect(Collectors.toSet());

        HashSet<Employee> hashSet = employees.stream().filter(emp -> emp.getSalary() > 7000).collect(Collectors.toCollection(HashSet::new));

        List<Employee> arrayList = employees.stream().filter(emp -> emp.getSalary() > 7000).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     *  joining(): 拼接输入元素到一个String中(有序的)
     */
    @Test
    void testJoining() {

        String names = employees.stream().map(Employee::getName).collect(Collectors.joining());
        // 结果：李四张三王五田七赵六
        System.out.println(names);

        names = employees.stream().map(Employee::getName).collect(Collectors.joining(","));
        // 结果：李四,张三,王五,田七,赵六
        System.out.println(names);

        names = employees.stream().map(Employee::getName).collect(Collectors.joining(",", "[", "]"));
        // 结果：[李四,张三,王五,田七,赵六]
        System.out.println(names);
    }

    /**
     *  counting(): 计算元素的个数
     */
    @Test
    void testCounting() {
        // 薪资大于 7000 的员工总数
        Long count = employees.stream().filter(emp -> emp.getSalary() > 7000).collect(Collectors.counting());
        System.out.println(count);
    }

    /**
     *  minBy(): 最小值
     */
    @Test
    void testMinBy() {
        Double salary = employees.stream().map(Employee::getSalary).collect(Collectors.minBy(Comparator.naturalOrder())).get();
        System.out.println(salary); // 5555.0
    }

    /**
     *  maxBy(): 最大值
     */
    @Test
    void testMaxBy() {
        Double salary = employees.stream().map(Employee::getSalary).collect(Collectors.maxBy(Comparator.naturalOrder())).get();
        System.out.println(salary); // 9999.0
    }

    /**
     * summingInt/summingLong/summingDouble 求和
     */
    @Test
    void testSumming() {
        int sum = employees.stream().collect(Collectors.summingInt(Employee::getAge));
        System.out.println("总年龄: " + sum);
    }

    /**
     * averagingInt/averagingLong/averagingDouble 平均数
     */
    @Test
    void testAveraging() {
        double avg = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("平均工资：" + avg);
    }

    /**
     *  summarizingInt/summarizingLong/summarizingDouble 统计
     */
    @Test
    void testSummarizing() {
        // 统计 薪水
        DoubleSummaryStatistics summaryStatistics = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("总数：" + summaryStatistics.getCount());
        System.out.println("总薪资：" + summaryStatistics.getSum());
        System.out.println("最大薪资：" + summaryStatistics.getMax());
        System.out.println("最小薪资：" + summaryStatistics.getMin());
        System.out.println("平均薪资：" + summaryStatistics.getAverage());

        // 统计 年龄
        IntSummaryStatistics statistics = employees.stream().mapToInt(Employee::getAge).summaryStatistics();
        System.out.println("总条目数: " + statistics.getCount());
        System.out.println("总年龄: " + statistics.getSum());
        System.out.println("最大年龄: " + statistics.getMax());
        System.out.println("最小年龄: " + statistics.getMin());
        System.out.println("平均年龄: " + statistics.getAverage());

        // 统计 年龄
        IntSummaryStatistics collect = employees.stream().map(Employee::getAge).collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(collect.toString());

        // 统计 name 的长度
        IntSummaryStatistics intSummary = employees.stream().map(Employee::getName).collect(Collectors.summarizingInt(String::length));
        System.out.println(intSummary.toString());
    }

    private static List<Product> products = Arrays.asList(
            new Product(1L, 1, 15.0, "面包", "零食"),
            new Product(2L, 2, 2.0, "饼干", "零食"),
            new Product(3L, 2, 30.0, "月饼", "零食"),
            new Product(4L, 3, 10.0, "青岛啤酒", "啤酒"),
            new Product(5L, 10, 15.0, "百威啤酒", "啤酒"));

    /**
     * groupingBy 分组
     */
    @Test
    void testGroupingBy() {
        // 1. 按产品种类进行分组
        Map<String, List<Product>> map = products.stream().collect(Collectors.groupingBy(Product::getCategory));
        System.out.println(JSON.toJSONString(map));
        // {"啤酒":[{"category":"啤酒","id":4,"name":"青岛啤酒","num":3,"price":10.0},{"category":"啤酒","id":5,"name":"百威啤酒","num":10,"price":15.0}],"零食":[{"category":"零食","id":1,"name":"面包","num":1,"price":15.0},{"category":"零食","id":2,"name":"饼干","num":2,"price":2.0},{"category":"零食","id":3,"name":"月饼","num":2,"price":30.0}]}

        // 2. 按照几个属性拼接分组
        Map<String, List<Product>> map2 = products.stream().collect(Collectors.groupingBy(item -> item.getCategory() + "_" + item.getName()));
        System.out.println(JSON.toJSONString(map2));
        // {"零食_月饼":[{"category":"零食","id":3,"name":"月饼","num":2,"price":30.0}],"零食_面包":[{"category":"零食","id":1,"name":"面包","num":1,"price":15.0}],"啤酒_百威啤酒":[{"category":"啤酒","id":5,"name":"百威啤酒","num":10,"price":15.0}],"啤酒_青岛啤酒":[{"category":"啤酒","id":4,"name":"青岛啤酒","num":3,"price":10.0}],"零食_饼干":[{"category":"零食","id":2,"name":"饼干","num":2,"price":2.0}]}

        // 3. 根据不同条件分组
        Map<String, List<Product>> map3 = products.stream().collect(Collectors.groupingBy(item -> {
            if(item.getNum() < 3) {
                return "库存不足";
            }else {
                return "库存充足";
            }
        }));
        System.out.println(JSON.toJSONString(map3));
        //  {"库存不足":[{"category":"零食","id":1,"name":"面包","num":1,"price":15.0},{"category":"零食","id":2,"name":"饼干","num":2,"price":2.0},{"category":"零食","id":3,"name":"月饼","num":2,"price":30.0}],"库存充足":[{"category":"啤酒","id":4,"name":"青岛啤酒","num":3,"price":10.0},{"category":"啤酒","id":5,"name":"百威啤酒","num":10,"price":15.0}]}

        // 4. 多几分组
        Map<String, Map<String, List<Product>>> map4 = products.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.groupingBy(item -> {
            if (item.getNum() < 4) {
                return "库存不足";
            } else {
                return "库存充足";
            }
        })));
        System.out.println(JSON.toJSONString(map4));
        // {"啤酒":{"库存不足":[{"category":"啤酒","id":4,"name":"青岛啤酒","num":3,"price":10.0}],"库存充足":[{"category":"啤酒","id":5,"name":"百威啤酒","num":10,"price":15.0}]},"零食":{"库存不足":[{"category":"零食","id":1,"name":"面包","num":1,"price":15.0},{"category":"零食","id":2,"name":"饼干","num":2,"price":2.0},{"category":"零食","id":3,"name":"月饼","num":2,"price":30.0}]}}


        // 按子组收集数据
        // 1. 求总数
        Map<String, Long> countMap = products.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        System.out.println(JSON.toJSONString(countMap));
        // {"啤酒":2,"零食":3}

        // 2. 求和
        Map<String, Integer> sumMap = products.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingInt(Product::getNum)));
        System.out.println(JSON.toJSONString(sumMap));
        // {"啤酒":13,"零食":5}


        // 3. 按产品种类进行分组, 取每组产品数量最多的
        Map<String, Product> prodMap1 = products.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Product::getNum)), Optional::get)));
        System.out.println(JSON.toJSONString(prodMap1));
        // {"啤酒":{"category":"啤酒","id":5,"name":"百威啤酒","num":10,"price":15.0},"零食":{"category":"零食","id":2,"name":"饼干","num":2,"price":2.0}}

        // 4. 按产品种类进行分组, 取每组的产品名称
        Map<String, Set<String>> prodMap = products.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::getName, Collectors.toSet())));
        System.out.println(JSON.toJSONString(prodMap));
        // {"啤酒":["青岛啤酒","百威啤酒"],"零食":["面包","饼干","月饼"]}
    }

    /**
     * partitioningBy 分区
     */
    @Test
    void testPartitioningBy() {
        // 按照年龄分区
        Map<Boolean, List<Employee>> map = employees.stream().collect(Collectors.partitioningBy(emp -> emp.getAge() >= 20));
        System.out.println(JSON.toJSONString(map));
    }

}
