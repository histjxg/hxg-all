package com.suixingpay.profit.atguigu.designPatter.factory.absfactory.order;

import com.suixingpay.profit.atguigu.designPatter.factory.absfactory.pizza.LDCheesePizza;
import com.suixingpay.profit.atguigu.designPatter.factory.absfactory.pizza.LDPepperPizza;
import com.suixingpay.profit.atguigu.designPatter.factory.absfactory.pizza.Pizza;

public class LDFactory implements AbsFactory{
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("~使用的抽象工厂模式～");
        Pizza pizza = null;

        if (orderType.equals("cheese")){
            pizza = new LDCheesePizza();
        }else if (orderType.equals("pepper")){
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
