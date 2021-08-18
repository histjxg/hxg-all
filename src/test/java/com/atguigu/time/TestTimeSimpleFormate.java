package com.atguigu.time;

import com.suixingpay.profit.atguigu.JDK8.DateFormateThreadLocal;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestTimeSimpleFormate {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Callable<Date> callable = new Callable<Date>() {

            @Override
            public Date call() throws Exception {
               // return sdf.parse("20161218");
                return DateFormateThreadLocal.convert("20161218");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ArrayList<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(callable));
        }
        for (Future<Date> future:results) {
            System.out.println(future.get());
        }
        pool.shutdown();

     }

     @Test
    public void test03(){
        double sum=120; double heigt = 60;
         for (int i = 1; i <= 11; i++) {
             sum+=heigt*2;
             heigt =heigt*0.5;
         }
         System.out.println(sum);
         System.out.println(heigt);
     }
}
