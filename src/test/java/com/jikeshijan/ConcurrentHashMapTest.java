package com.jikeshijan;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: huangxiaogen
 * @Date: 2020/05/09/上午9:21
 * @Description:
 */
@Slf4j
public class ConcurrentHashMapTest {
    @Test
    public void test() throws InterruptedException {
        int limit = 1000;
        ConcurrentHashMap<String, Long> concurrentHashMap = LongStream.rangeClosed(1, limit - 10)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(),
                        (o1, o2) -> o1, ConcurrentHashMap::new));
        log.info("init size:{}", concurrentHashMap.size());
        System.out.println("init size:{}"+ concurrentHashMap.size());

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int __ = 0; __ < 10; __++) {
            executorService.execute(() -> {
                int gap = limit - concurrentHashMap.size();
                log.debug("gap:{}", gap);
                System.out.println("gap:{}"+gap);

                concurrentHashMap.putAll(LongStream.rangeClosed(1, gap)
                        .boxed()
                        .collect(Collectors.toMap(i -> UUID.randomUUID().toString(), Function.identity())));
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);

        log.info("finish size:{}", concurrentHashMap.size());
        System.out.println("finish size:{}"+ concurrentHashMap.size());

    }
}
