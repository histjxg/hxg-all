package com.atguigu.JDK8;

import com.suixingpay.profit.atguigu.JDK8.MyFun;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda :表达式的基础语法 ——>箭头操作符，
 *
 * 左侧：Lambda参数列表
 * 右侧：lambda 表达式中所需执行的功能，即lambda体
 *
 * 语法格式一： 无参数 无返回值
 * （）-> (System.out.println();)
 *
 * 语法格式二：有一个参数，并且无返回值
 *  （x）-> (System.out.println(x);)
 *
 * 语法格式三：若只有一个参数，小括号可以省略不写
 *  x-> (System.out.println(x);)
 *
 *
 *  语法格式四：有两个以上的参数，有返回值 并且lambda体有多条语句
     *Comparator<Integer> com = (x,y)->{
     System.out.println("函数市接口");
     return Integer.compare(x,y);
     };
 *
 *
 * 语法格式五：若Lambda体中只有一条语句，return和大括号都可以省略不写
        Comparator<Integer> com = (x,y)-> Integer.compare(x,y);

   语法格式六：lambda表达式的参数列表的数据类型可以省略不屑，因为JVM编译器通过上下文
   推断出，数据类型，即  "类型推断"

 左右遇一括号省
 左测推断类型省

 lambda表达式需要函数接口的支持，
 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口，可以使用注解@FunctionalInterface修饰
  可以检查是否是函数式接口


 */
public class TestLambda2 {

    @Test
    public void test(){
        Runnable runnable = new Runnable(){

            @Override
            public void run() {
                System.out.println(" hello world");
            }
        };
        runnable.run();

        Runnable runnable1 = ()-> System.out.println("hello lambda");
        runnable1.run();
    }
    @Test
    public void test2(){
        Consumer consumer = (x)-> System.out.println(x);
        consumer.accept("我大尚硅谷威武");
    }

    @Test
    public void test3(){
        Consumer consumer = x-> System.out.println(x);
        consumer.accept("我大尚硅谷威武");
//        Integer;
    }

    @Test
    public void test4(){
        Comparator<Integer> com = (x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
        com.compare(1,2);

    }
    //若Lambda体中只有一条语句，return和大括号都可以省略不写
    //Comparator<Integer> com = (x,y)-> Integer.compare(x,y);
    @Test
    public void test5(){
        Comparator<Integer> com = (x,y)-> Integer.compare(x,y);
        com.compare(3,5);
    }

    @Test
    public void test6(){
        Integer num = operation(100,x->x*x);
        System.out.println(num);

        System.out.println(operation(200,y->y+200));

    }

    public Integer operation(Integer num, MyFun mf){
        return mf.getVaue(num);

    }

    @Test
    public void test05() {
        Integer a = 100, b = 100, c = 150, d = 150;
        System.out.println(a == b);
        System.out.println(c == d);
    }



}
