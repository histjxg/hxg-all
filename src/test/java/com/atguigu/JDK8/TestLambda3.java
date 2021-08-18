package com.atguigu.JDK8;

import com.suixingpay.profit.atguigu.JDK8.MyFunction;
import com.suixingpay.profit.atguigu.JDK8.MyFunction2;
import com.suixingpay.profit.atguigu.JDK8.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 调用Collections.sort()方法，通过定制排序比较两个Employee（先按年龄
 * 比，年龄相同按姓名比，）使用lambda作为参数传递
 */
public class TestLambda3 {
    List<Employee> employees = Arrays.asList(new Employee(12,"张三",65.0),
            new Employee(18,"张三",9999.99),
            new Employee(38,"李四",5555.99),
            new Employee(50,"王五",6666.66),
            new Employee(16,"赵六",3333.33),
            new Employee(8,"田七",7777.77)

    );
    @Test
    public void test1(){
        Collections.sort(employees,(e1,e2)->{
            if (e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void test2(){
        String s = strHandler("\t\t\t 我大尚硅谷威武", str -> str.trim());
        System.out.println(s);
        strHandler("abcdef",str ->
                 str.toUpperCase());

    }

    //处理字符串
    public String strHandler(String st,MyFunction mf){
        return mf.getValue(st);

    }
    @Test
    public void test3(){
        op(100l,200l,(x,y)-> x+y);
        op(100l,200l,(x,y)-> x*y);
    }

    //对于两个long型的运算
    public void op(Long l1,Long l2,MyFunction2<Long,Long> mf){
        System.out.println(mf.getValue(l1,l2));

    }
}
