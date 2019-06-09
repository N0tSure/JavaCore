package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class AtomicityTest {

    @Test
    void atomicEvenIncrementTest() throws InterruptedException, ExecutionException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final AtomicTask atomicTask = new AtomicTask();

        executor.execute(atomicTask);
        final Future<Integer> valueFuture = executor.submit(new AtomicChecker(atomicTask));

        try {
            final Integer value = valueFuture.get(5, TimeUnit.SECONDS);
            System.out.println(value == null ? "No odd detected." : value);
        } catch (TimeoutException exc) {
            System.out.println("Run out of time.");
        }

        executor.shutdownNow();
    }


}
