package com.atguigu.Stream;

import com.suixingpay.profit.atguigu.JDK8.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestStreamAPI4 {
    /**
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表？
     * 给定【1，2，3，4，5】应该返回【1，4，9，16，25】
     */
    public List<Employee> employees = Arrays.asList(new Employee(12,"张三",9999.99, Employee.Status.VOCATION),
            new Employee(18,"张三",9999.96, Employee.Status.FREE),
            new Employee(38,"李四",5555.99, Employee.Status.BUSY),
            new Employee(50,"王五",6666.66, Employee.Status.VOCATION),
            new Employee(16,"赵六",3333.33, Employee.Status.VOCATION),
            new Employee(8,"田七",7777.77, Employee.Status.VOCATION)

    );
    @Test
    public void test1(){
        Integer[] nums= new Integer [] {
            1,2,3,4,5
        };
        Arrays.stream(nums).map(x->x*x).forEach(System.out::println);
    }
    @Test
    public void test2(){
        Optional<Integer> count = employees.stream().map(e -> 1).reduce(Integer::sum);
        System.out.println(count.get());

    }
}
