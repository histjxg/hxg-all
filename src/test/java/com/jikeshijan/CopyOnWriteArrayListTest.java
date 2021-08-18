package com.jikeshijan;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: huangxiaogen
 * @Date: 2020/05/09/下午2:13
 * @Description:
 */
@Slf4j
public class CopyOnWriteArrayListTest {
    @Test
    public void testWrite() {
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        StopWatch stopWatch = new StopWatch();
        int loopCount = 100000;
        stopWatch.start("copyOnWriteArrayList");
        IntStream.rangeClosed(1, loopCount).parallel().forEach(__ -> copyOnWriteArrayList.add(
                ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();
        stopWatch.start("arrayList");
        IntStream.rangeClosed(1, loopCount).parallel().forEach(__ -> {
            synchronized (arrayList) {
                arrayList.add(ThreadLocalRandom.current().nextInt(loopCount));
            }
        });
        stopWatch.stop();
        stopWatch.start("synchronizedList");
        IntStream.range(0, loopCount).parallel().forEach(__ -> synchronizedList.add(ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        log.info(stopWatch.prettyPrint());
    }
}
