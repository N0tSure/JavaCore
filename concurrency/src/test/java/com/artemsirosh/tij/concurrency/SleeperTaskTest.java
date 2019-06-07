package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created at 07-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SleeperTaskTest {

    @Test
    void sleeperTest() throws InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        Stream.generate(() -> new SleeperTask(TimeUnit.SECONDS, Math.round(Math.random() * 10)))
                .limit(5)
                .forEach(executor::execute);

        TimeUnit.SECONDS.sleep(10);

        executor.shutdown();
        System.out.println("Executor shot down");
    }
}
