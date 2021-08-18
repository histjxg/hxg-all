package com.atguigu.Stream;
import java.util.DoubleSummaryStatistics;
import	java.util.HashSet;

import com.suixingpay.profit.atguigu.JDK8.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 终止操作
 */
public class TestStreamAPI3 {
    /**
     * 查找与匹配
     * allMatch---检查是否匹配所有元素
     * anyMatch----检查是否至少匹配一个元素
     * noneMatch--检查是否没有匹配所有元素
     * findFirst----返回第一个元素
     * findAny--返回当前流中的任意元素
     * count----返回流中元素的总个数
     * max--返回流中最大值
     * min---返回流中最小值
     */

    public List<Employee> employees = Arrays.asList(new Employee(12,"张三",9999.99, Employee.Status.VOCATION),
            new Employee(18,"张三",9999.96, Employee.Status.FREE),
            new Employee(38,"李四",5555.99, Employee.Status.BUSY),
            new Employee(50,"王五",6666.66, Employee.Status.VOCATION),
            new Employee(16,"赵六",3333.33, Employee.Status.VOCATION),
            new Employee(8,"田七",7777.77, Employee.Status.VOCATION)

    );
    @Test
    public void test01(){
        boolean b = this.employees.stream().allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);

        boolean b1 = employees.stream().anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        boolean b2 = employees.stream().anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        Optional<Employee> first = employees.stream().sorted(
                (e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary())
        ).findFirst();
        System.out.println(first.get());

        Optional<Employee> any = employees.stream().filter(e -> e.getStatus().equals(Employee.Status.FREE)).findAny();
        System.out.println(any.get());

    }

    @Test
    public void test(){
        long count = employees.stream().count();
        System .out.println(count);

        Optional<Employee> max = employees.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());

        Optional<Employee> min = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min.get());
    }

    /**
     * 归约：
     * reduce（T identity  BinaryOperator） / reduce(BinaryOperator) ----可以将流中元素反复结合起来，得到一个值
     */

    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("-----------------");

        Optional<Double> reduce = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(reduce.get());

    }
    /**
     * 收集：collect --将流转换为其他形式，接收一个collect接口的实现，用于给stream中元素做汇总的方法
     */
    @Test
    public void test2(){
        List<String> collect = employees.stream().map(Employee::getName).collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("-----------------");
        Set<String> set = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("-----------------");
        HashSet<String> hashSet = employees.stream().map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);


    }

    @Test
    public void test3(){
        //总数
        Long aLong = employees.stream().map(Employee::getName).collect(Collectors.counting());
        System.out.println(Long.valueOf(aLong));
        //平均值
        Double collect = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(Double.valueOf(collect));
        //最大值
        Optional<Employee> max = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());
        //最小值
        Optional<Employee> min = employees.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(min.get());

    }

    //分组
    @Test
    public void test4(){
        Map<Employee.Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    //多级分组
    @Test
    public void test07(){
        Map<Employee.Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (((Employee) e).getAge() <= 35) {
                        return "青年";
                    } else if (((Employee) e).getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(map);

    }

    //分区
    @Test
    public void test5(){
        Map<Boolean, List<Employee>> collect = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        System.out.println(collect);
    }
    /**
     * 收集：collect -将流转换为其他形式，接收一个collector接口的实现，用于给stream中元素做汇总的方法
     *
     */
    @Test
    public void test6(){
        DoubleSummaryStatistics collect = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getSum());
        System.out.println(collect.getAverage());
        System.out.println(collect.getMax());
    }
    /**
     * 连接字符串
     */

    @Test
    public void test7(){
        String collect = employees.stream().map(Employee::getName).collect(Collectors.joining());
        System.out.println(collect);

    }



}
