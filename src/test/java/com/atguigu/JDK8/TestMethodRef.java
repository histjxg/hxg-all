package com.atguigu.JDK8;
import java.util.function.BiFunction;
import	java.util.function.BiPredicate;

import com.suixingpay.profit.atguigu.JDK8.entity.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *  方法引用：若lambda体中的内容有方法实现了，我们可以使用"方法引用"
 *   （可以理解方法引用是lambda 表达式的另外一种表现形式）
 *
 *   方法引用的三种方式：
 *   对象::实例方法名
 *   类::静态方法名
 *   类::实例方法名
 *
 *
 *   //lambda体中的调用的方法参数的列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致，才可以用
 *   方法引用
 *
 *   2。lambda参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时
 *   可以使用ClassName::Name
 *
 *   3.注意需要调用的函数构造器的函数列表要与函数接口中的抽方法的参数列表要保持一追
 *
 *   三：数组引用
 */
public class TestMethodRef {
    //对象::实例方法名
    @Test
    public void test(){
        Consumer consumer = x-> System.out.println(x);
        PrintStream ps = System.out;

        Consumer consumer1 = ps::print;
        consumer1.accept("abajfds");

    }

    @Test
    public void test1(){
        Employee employee = new Employee();
        Supplier<String> supplier = ()->employee.getName();
        String str = supplier.get();
        System.out.println(str);

        Supplier<String> supplier2 = employee::getName;
        String str2 = supplier.get();
        System.out.println(str2);
    }


    //类::静态方法名
    @Test
    public void test2(){
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        Comparator<Integer> com2 = Integer::compare;

    }

    //类::实例方法名

  @Test
    public void test3(){
        //第一个参数是调用者，第二个参数是作为调用者的参数，可以用类::实例方法名
        BiPredicate<String, String> com = (x,y)->x.equals(y);
        BiPredicate<String, String> com2 = String::equals;

  }

  //构造器引用
    @Test
    public void test4(){
        Supplier<Employee> supplier = ()->new Employee();
        //构造器引用方式
        Supplier<Employee> supplier1 = Employee::new;
        Employee employee = supplier1.get();
        System.out.println(employee);

    }

    //构造器引用
    @Test
    public void test5(){
        Function<Integer, Employee> supplier = (x)->new Employee(x);
        //构造器引用方式
        Supplier<Employee> supplier1 = Employee::new;
        Employee employee = supplier1.get();
        System.out.println(employee);

        Function<Integer, Employee> function = (x)->new Employee(x);
        Function<Integer, Employee> function1 = Employee::new;
        function1.apply(100);

       // BiFunction<Integer,Double, Employee> biFunction = (x)->new Employee(x);


    }
  //数组引用
    @Test
    public void test7(){
        Function<Integer, String[]> fun = x->new String[x];
        String[] str2 = fun.apply(10);
        System.out.println(str2.length);


        Function<Integer, String[]> fun1 =String[]::new;
        String[] str3 = fun1.apply(10);
        System.out.println(str3.length);



    }

}
