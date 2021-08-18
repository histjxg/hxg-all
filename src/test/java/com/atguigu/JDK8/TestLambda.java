package com.atguigu.JDK8;

import com.suixingpay.profit.atguigu.JDK8.FilterEmployeeBySalary;
import com.suixingpay.profit.atguigu.JDK8.FiterEmployeesByAge;
import com.suixingpay.profit.atguigu.JDK8.MyPredicate;
import com.suixingpay.profit.atguigu.JDK8.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class TestLambda {
    @Test
    public void test(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet ts = new TreeSet(com);

    }

    @Test
    public void test1(){
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        TreeSet ts = new TreeSet(com);
    }



    List<Employee> employees = Arrays.asList(new Employee(12,"张三",65.0),
            new Employee(18,"张三",9999.99),
            new Employee(38,"李四",5555.99),
            new Employee(50,"王五",6666.66),
            new Employee(16,"赵六",3333.33),
            new Employee(8,"田七",7777.77)

    );
    @Test
    public void test2(){
        List<Employee> employees = filterEmployees(this.employees);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    //获取当前公司中年龄大于35的员工信息
    public List<Employee> filterEmployees(List<Employee> employees){
        List<Employee> employees1 = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge()>=35){
                employees1.add(employee);
            }
        }
        return employees1;
    }

    //需求：获取当前公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployees2(List<Employee> employees){
        List<Employee> employees1 = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary()>=5000){
                employees1.add(employee);
            }
        }
        return employees1;
    }

    //优化方式一 策略设计模式
    @Test
    public void test3(){
        List<Employee> employees = filterEmployees(this.employees, new FiterEmployeesByAge());
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        List<Employee> employee1 = filterEmployees(this.employees, new FilterEmployeeBySalary());
        for (Employee employee : employee1) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployees(List<Employee> list,MyPredicate<Employee> mp) {
        List<Employee> employees1 = new ArrayList<> ();
        for (Employee employee : list) {
            if (!mp.test(employee)){
                employees1.add(employee);
            }
        }
        return employees1;
    }

    //优化方式二：匿名内部类
    @Test
    public void test5(){
        List<Employee> employees = filterEmployees(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary()>=5000;
            }
        });
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    //优化方式三：
    @Test
    public void test6(){
        List<Employee> employees = filterEmployees(this.employees, (e)->e.getSalary()<=5000);
        employees.forEach(System.out::println);
    }

    //优化方式四

    @Test
    public void test4(){
        this.employees.stream().filter((e)->e.getSalary()>=5000)
                .limit(2)
                .forEach(System.out::println);

        this.employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

}

