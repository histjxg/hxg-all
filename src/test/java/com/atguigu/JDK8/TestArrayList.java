package com.atguigu.JDK8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class TestArrayList {
    public static void main(String[] args) {
        String[] st  = {"A","B","C","D","E"};
        String[] dt  = new String[5];
        System.arraycopy(st, 0, dt, 0, 5);

        for(String str : st){
            System.out.print(" " + str +" ");   // A  B  C  D  E

        }
        System.out.println();
        for(String str : dt){
//            ArrayList
//            LinkedList
//            System.out.print(" " + str +" ");   // A  B  C  D  E
        }

        System.out.println("数组内对应位置的String地址是否相同:" + st[0] == dt[0]); // true
    }
}
