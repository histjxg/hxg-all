package com.atguigu.Stream;
import	java.util.EnumSet;
import java.util.Optional;
import	java.util.stream.Collectors;
import	java.io.PrintWriter;

import com.suixingpay.profit.atguigu.JDK8.entity.Trader;
import com.suixingpay.profit.atguigu.JDK8.entity.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestTransaction {
    List<Transaction> transactions = null;
    @Before
    public void before(){
        Trader raou1 = new Trader("Raou1","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        transactions = Arrays
                .asList(new Transaction(brian, 2011, 300)
                        , new Transaction(raou1, 2012, 1000),
                        new Transaction(raou1, 2011, 400)
                      //  , new Transaction(raou1, 2011, 400),
                       , new Transaction(mario, 2012, 710),
                        new Transaction(mario, 2012, 700),
                        new Transaction(alan, 2012, 950));
    }
    //1.找出2011年发生的所有交易，并按交易额排序(从低到高)
    @Test
    public void test(){
        transactions.stream().filter(t->t.getYear()==2011)
                .sorted((e1,e2)->Integer.compare(e1.getValue(),e2.getValue()))
                .forEach(System.out::println);

    }
    //2.交易员都在那些不同的城市工作过
    @Test
    public void test1(){
        transactions.stream().map(e->e.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    //3。查找所有来自剑桥的交易员，并按姓名排序
    @Test
    public void test2(){
        transactions.stream()
                .filter(e -> e.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted((e1,e2)->e1.getName().compareTo(e2.getName()))
                .forEach(System.out::println);

    }
    //4.返回所有的交易员的姓名字符串，按字母顺序排序
    @Test
    public void test3(){
        transactions.stream()
                .map(e->e.getTrader().getName())
                .sorted((e1,e2)->e1.compareTo(e2))
                .forEach(System.out::println);
        System.out.println("--------------------");

        String reduce = transactions.stream().map(e -> e.getTrader().getName()).sorted((e1, e2) -> e1.compareTo(e2))
                .reduce("", String::concat);

        System.out.println(reduce);
        System.out.println("--------------------");

        transactions.stream()
                .map(t->t.getTrader().getName())
                .flatMap(TestTransaction::filterharacter)
                .sorted(
                ).forEach(System.out::print);

        System.out.println("--------------------");
        //忽略大小写
        transactions.stream()
                .map(t->t.getTrader().getName())
                .flatMap(TestTransaction::filterharacterIngore)
                .sorted((e1,e2)->e1.compareToIgnoreCase(e2)
                ).forEach(System.out::print);

    }
    //没有忽略大小写
    public static Stream<Character> filterharacter(String str){
        List<Character> list = new ArrayList<>();
        for (Character c:str.toCharArray()
             ) {
            list.add(c);
        }
        return list.stream();
    }
    //忽略大小写
    public static Stream<String> filterharacterIngore(String str){
        List<String> list = new ArrayList<>();
        for (Character c:str.toCharArray()
                ) {
            list.add(c.toString());
        }
        return list.stream();
    }
    //5.有没有交易员是在米兰工作过的
    @Test
    public void test4(){
        boolean milan = transactions.stream().anyMatch(t -> t.getTrader().getCity().equalsIgnoreCase("Milan"));
        System.out.println(milan);
    }
    //6.打印生活在剑桥的交易员的所有交易额
    @Test
    public void test5(){
        Optional<Integer> reduce = transactions.stream().map(t -> t.getValue()).reduce((x, y) -> x + y);
        System.out.println(reduce.get());
    }
    //7.所有文件中，最高的交易额是多少
    @Test
    public void test6(){
        Optional<Integer> max = transactions.stream().map(e -> e.getValue()).max(Integer::compare);
        System.out.println(max.get());
    }
    //8。找到交易额最小的交易
    @Test
    public void test7(){
        Optional<Transaction> min = transactions.stream()
                .min((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
        System.out.println(min.get());
    }

}
