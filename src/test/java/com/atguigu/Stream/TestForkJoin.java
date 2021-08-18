package com.atguigu.Stream;
import java.time.Duration;
import java.time.Instant;
import	java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import com.suixingpay.profit.atguigu.JDK8.ForkjoinCalculate;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

public class TestForkJoin {
    @Test
    public void test(){
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkjoinCalculate(1,100000000000l );
        long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());

    }

    /**
     * 普通for
     */
    @Test
    public void test2(){
        Instant start = Instant.now();
        long sum = 0;
        for (long i = 0; i <=100000000000l ; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }
    /**
     * java8并行流
     *
     */
    @Test
    public void test3(){
        Instant start = Instant.now();
        LongStream.rangeClosed(0,100000000000l)
                .parallel()
                .reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }
}
