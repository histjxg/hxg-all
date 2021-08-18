package com.atguigu.Stream;
import	java.io.PrintStream;

import com.suixingpay.profit.atguigu.JDK8.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 1Stream 的三个步骤
 * 1。创建Stream
 * 2。中间操作
 * 3。终止操作
 */
public class TestStreamAPI2 {
    //中间操作
    /**
     * 筛选和切片
     * filter：接收lambda 从流中排除某些元素
     * limit  截断流，使其元素不超过给定数量
     * skip（n）跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit（n）互补
     * distinct 筛选，通过流所生成的元素的hashcode（）和equals去除重复元素
     *
     */
    List<Employee> employees = Arrays.asList(new Employee(12,"张三",65.0),
            new Employee(18,"张三",9999.99),
            new Employee(38,"李四",5555.99),
            new Employee(50,"王五",6666.66),
            new Employee(16,"赵六",3333.33),
            new Employee(8,"田七",7777.77)

    );
    //内部求值，迭代操作由Stream API完成
    @Test
    public void test(){
        //中间操作，不会执行任何操作
        Stream<Employee> employeeStream = employees.stream().filter(e ->{
            System.out.println("Stream API 中间操作");
            return e.getAge() > 35;} );
        //终止操作：一次性执行全部内容，即"惰性求职"
        employeeStream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test1(){
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void test2(){
        employees.stream().filter(e->{
            System.out.println("短路");
            return e.getSalary()>5000;
        })
                .limit(2)
                .forEach(System.out::println);


    }
    @Test
    public void test3(){
        employees.stream().filter( e->{
            System.out.println("跳跃");
            return e.getSalary()>5000;})
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    //中间操作
    /**
     * 映射操作：接受lambda，将元素转换成其他形式或提取信息，接受一个函数作为参数
     * map该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatmap 接收以一个函数作为参数，将流中的每个值都换成一个流，然后把所有的流连接成一个流
     * 区别和add和addAll的区别一样
     */

    @Test
    public void test5(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        List list2= new ArrayList();
        list2.add("11");
        list2.add("22");
        list2.add(list);
        System.out.println(list2);
        list2.remove(list);
        list2.addAll(list);
        System.out.println(list2);

        list.stream()
                .map(str->str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("-------------------");
        employees.stream().map(Employee::getName)
                .forEach(System.out::println);

        Stream<Stream<Character>> streamStream = list.stream().map(TestStreamAPI2::filterCharater);

        streamStream.forEach((sm)->{
            sm.forEach(System.out::println);
        });
        System.out.println("----------------------------");

        Stream<Character> characterStream = list.stream().flatMap(TestStreamAPI2::filterCharater);
        characterStream.forEach(System.out::println);
    }

    public static Stream<Character> filterCharater(String str){
        List<Character> list = new ArrayList<>();
        for (Character c:str.toCharArray()
             ) {
            list.add(c);
        }

       return list.stream();
    }

    /**
     * 中间操作，
     * 排序：
     * sorted() 自然排序（Comparable）
     * Sorted（Comparator com）定制排序 Comparator
     */
    @Test
    public void test7(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("-------------------");
        employees.stream().sorted(
                (e1,e2)->{
                   if(e1.getAge().equals(e2.getAge())){
                       return e1.getName().compareTo(e2.getName());
                   } else {
                       return e1.getAge().compareTo(e2.getAge());
                   }
                }
        ).forEach(System.out::println);
       // System.co

    }

}
