package com.suixingpay.profit.atguigu.designPatter.factory.factorymethod.pizzastore.order;

import com.suixingpay.profit.atguigu.designPatter.factory.factorymethod.pizzastore.pizza.BjCheesePizza;
import com.suixingpay.profit.atguigu.designPatter.factory.factorymethod.pizzastore.pizza.BjPepperPizza;
import com.suixingpay.profit.atguigu.designPatter.factory.factorymethod.pizzastore.pizza.Pizza;

public class BJOrderPizza extends OrderPizza{
    @Override
    Pizza createPizza(String orderType) {
        if (orderType.equals("pepper")){
            pizza= new BjPepperPizza();
        }else if (orderType.equals("cheese")){
            pizza= new BjCheesePizza();
        }
        return pizza;
    }
}
