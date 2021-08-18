package com.atguigu.default1;

import com.suixingpay.profit.atguigu.JDK8.SubClass;
import org.junit.Test;

//类和接口的方法有冲突时候，选择类中的方法
public class TestDefault {
    @Test
    public void test(){
        SubClass subClass = new SubClass();
        String name = subClass.getName();
        System.out.println(name);
    }
}
