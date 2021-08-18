package com.atguigu.JDK8;
import	java.util.function.Predicate;
import	java.util.function.Function;
import java.util.ArrayList;
import	java.util.function.Supplier;
import	java.util.List;
import	java.util.function.Consumer;

import org.junit.Test;

/**
 * java 四大核心接口
 *
 * Consumer<T> 消费型接口
 *     void accept(T t)
 *
 * Supplier<T> 供给行型接口
 *   T get();
 * Function<T,R> 函数型接口
 *
 *  R applay(T,t)
 *
 *  Predicate<T> 断言式接口，
 *   boolean test(T t)
 */
public class TestLambda4 {
    //Supplier<T> 供给行型接口
    //产生一些整数放在集合中
    @Test
    public void test1(){
        List<Integer> numList = getNumList(10, () -> (int) Math.random() * 100);

    }

    public List<Integer> getNumList(int num,Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    // Consumer<T>
    @Test
    public void test(){
        happy(100,m-> System.out.println("大保健"));
    }
    public void happy(double money,Consumer<Double> consumer){
        consumer.accept(money);
    }

    @Test
    public void test2(){
        String s = strHandler("\t\t\t  我尚硅谷威武  ", st -> st.trim());
        System.out.println(s);
        String substr = strHandler("我大尚硅谷威武  ",str->str.substring(2,5));
        System.out.println(substr);
    }

    //Function<T,R> 函数型接口处理字符串
    public String strHandler(String str,Function<String, String> fun){
        return fun.apply(str);
    }


    @Test
    public void test3(){
        List<String> list = new ArrayList<> ();
        list.add("hello");
        list.add("atguigu");
        list.add("lambda");
        list.add("www");
        list.add("ok");
        filterStr(list,s->s.length()>3);
    }
    //Predicate<T> 断言式接口，满足要求的放入集合中
    public List<String> filterStr(List<String> list ,Predicate<String> fun){
        List<String> stringList = new ArrayList<>();
        for (String s : list) {
            if (fun.test(s)){
                stringList.add(s);
            }
        }
        return stringList;
    }
}
