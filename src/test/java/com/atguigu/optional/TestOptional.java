package com.atguigu.optional;

import com.suixingpay.profit.atguigu.JDK8.entity.Employee;
import com.suixingpay.profit.atguigu.JDK8.entity.Godness;
import com.suixingpay.profit.atguigu.JDK8.entity.Man;
import com.suixingpay.profit.atguigu.JDK8.entity.NewMan;
import org.junit.Test;

import java.util.Optional;

public class TestOptional {
    /**
     * Optional 容器类的常用方法
     * Optional.of(T t)创建一个optional实例
     * Optional.empty():创建一个空的optional实例子
     * Optional.ofnullable(T t)若t不为null,创建Optional实例,否则创建空实例
     * IsPresent():判断是否包含值
     * orElse(T t):如果调用对象包含值,返回该值,否则返回s获取的值
     * orElseGet(Supplier s):如果调用对象包含值,返回该值,否则返回s获取的值
     * map(function f)如果对其处理，并返回处理后的Optional，否则返回Optional.empty
     * flatMap(Function f):与map类似，要求返回值必须是Optional
     */

    @Test
    public void test(){
        Optional<Employee> employee = Optional.of(new Employee());
        Employee employee1 = employee.get();
        System.out.println(employee1);

        Optional<Object> o = Optional.of(null);

    }

    @Test
    public void test2(){
        Optional<Employee> employee = Optional.empty();
        if (employee.isPresent())
        System.out.println(employee.get());

    }
    @Test
    public void test3(){
        Optional<Employee> employee = Optional.ofNullable(null);
        if (employee.isPresent())
        System.out.println(employee.get());

        Employee employee1 = employee.orElse(new Employee(18, "张三", 8888.88, Employee.Status.FREE));
        System.out.println(employee1);

        Employee employee2 = employee.orElseGet(()->new Employee());
        System.out.println(employee2);

    }
    @Test
    public void test4(){
        Optional<Employee> employee = Optional.ofNullable(new Employee(20, "张三", 8888.88, Employee.Status.VOCATION));
        Optional<String> optional = employee.map(e -> e.getName());

        Optional<String> s = employee.flatMap(e -> Optional.of(e.getName()));
        System.out.println(optional.get());
        System.out.println(s.get());
    }
    @Test
    public void test5(){
        Man man = new Man();
        String godnessName = getGodnessName(man);
        System.out.println(godnessName );

        Optional<Godness> godness = Optional.ofNullable(new Godness("波多老师"));
        Optional<NewMan> newMan = Optional.ofNullable(new NewMan(godness));
        System.out.println(getGodnessName2(newMan));
    }

    //获取一个女神的名字
    public String getGodnessName(Man man){
        if (null!=man){
            Godness godness = man.getGodness();
            if (godness!=null){
                return godness.getName();
            }

        }
        return "苍老师";
    }

    //获取一个女神的名字
    public String getGodnessName2(Optional<NewMan>  man){
      return man.orElse(new NewMan()).getGodness().orElse(new Godness("苍老师")).getName();
    }
}
