package com.atguigu.Stream;
import	java.util.ArrayList;

import com.suixingpay.profit.atguigu.JDK8.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 1Stream 的三个步骤
 * 1。创建Stream
 * 2。中间操作
 * 3。终止操作
 */
public class TestStreamAPI1 {
    //创建Stream
    @Test
    public void test(){
        //1. 可以通过collection系列集合提供的stream方法或parallelStream()
        List<String> list = new ArrayList<String> ();
        Stream<String> stream = list.stream();

        //2.通过Arrays中的静态方法获取Stream六
        Employee[] employee = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employee);

        //3.通过Stream类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2);
        stream3.forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random());

    }

}
