package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SerialNumberTest {

    @Test
    void checkSerialNumber() throws InterruptedException, ExecutionException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final SerialNumberGenerator generator = new SerialNumberGenerator();
        final CircularSet serials = new CircularSet(1000);

        List<Future<Integer>> futures = Stream.generate(() -> new SerialNumberCheckingTask(generator, serials))
                .limit(5)
                .map(executor::submit)
                .collect(Collectors.toList());

        for (Future<Integer> future : futures) {
            try {
                Integer value = future.get(2, TimeUnit.SECONDS);
                if (value != null)
                    System.out.println("Duplicate: " + value);
            } catch (TimeoutException exc) {
                System.out.println("Time run out.");
            }
        }

        executor.shutdownNow();
    }
}
