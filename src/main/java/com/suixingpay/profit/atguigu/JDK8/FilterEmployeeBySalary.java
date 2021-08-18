package com.suixingpay.profit.atguigu.JDK8;

import com.suixingpay.profit.atguigu.JDK8.entity.Employee;

public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary()>=5000;
    }
}
