package com.suixingpay.profit.atguigu.JDK8;

import com.suixingpay.profit.atguigu.JDK8.entity.Employee;

public class FiterEmployeesByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee o) {
        return o.getAge()>=35;
    }
}
