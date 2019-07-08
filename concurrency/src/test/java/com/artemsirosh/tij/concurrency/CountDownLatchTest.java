package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created on 08 Jul, 2019.
 *
 * @author Artemis A. Sirosh
 */
class CountDownLatchTest {

    @Test
    void barrierDemo() throws InterruptedException {
        int size = 100;
        final ExecutorService executor = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(size);
        final Random random = new Random(47);

        IntStream.range(0, 10)
                .mapToObj(i -> new WaitingTask(i, latch))
                .forEach(executor::execute);

        IntStream.range(0, 100)
                .mapToObj(i -> new TaskPortion(i, random, latch))
                .forEach(executor::execute);

        System.out.println("Main: tasks launched.");
        executor.shutdown();
        TimeUnit.SECONDS.sleep(3);
    }
}
