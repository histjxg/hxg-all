package com.suixingpay.profit.atguigu.JDK8.entity;

public class Godness {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Godness() {
    }

    public Godness(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Godness{" + "name='" + name + '\'' + '}';
    }
}
