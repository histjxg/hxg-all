package com.suixingpay.profit.jikeshijian.bingfabiancheng;

public class CounterSafeObject {
    private long value;
    synchronized long get(){
        return value;
    }
    synchronized long addOne(){
        return ++value;
    }
}
