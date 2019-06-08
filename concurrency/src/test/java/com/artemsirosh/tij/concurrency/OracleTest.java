package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class OracleTest {

    @Test
    void oracleAnswerChecking() throws InterruptedException {
        final Oracle oracle = new Oracle();
        final ExecutorService executor = Executors.newCachedThreadPool();
        IntStream.range(0, 5)
                .mapToObj(i -> new GreatAnswerChecker(i, oracle))
                .forEach(executor::execute);

        TimeUnit.SECONDS.sleep(5);
        executor.shutdownNow();
    }
}
