package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SemaphoreTest {

    private static <T> T getItemFromPoolWithoutNulls(Pool<T> pool) {
        try {
            return pool.checkOut();
        } catch (InterruptedException exc) {
            throw new RuntimeException(exc);
        }

    }

    private static <T> T getItemFromPoolNullable(Pool<T> pool) {
        try {
            return pool.checkOut();
        } catch (InterruptedException exc) {
            System.out.println("Interrupted while acquiring item.");
        }

        return null;
    }

    @Test
    void semaphorePoolingFatObjects() throws InterruptedException {
        final int size = 25;
        final ExecutorService executor = Executors.newCachedThreadPool();
        final List<Fat> fats = IntStream.range(1, size + 1)
                .mapToObj(Fat::new)
                .collect(Collectors.toList());

        final Pool<Fat> pool = new Pool<>(fats);

        IntStream.range(1, size + 1)
                .mapToObj(id -> new CheckoutTask<>(id, pool))
                .forEach(executor::execute);

        System.out.println("main: all checkout tasks are created.");

        Stream.generate(() -> getItemFromPoolWithoutNulls(pool))
                .limit(size)
                .forEach(item -> {
                    System.out.print("main: ");
                    item.operation();
                });

        final Future<?> blocked = executor.submit(() -> getItemFromPoolNullable(pool));

        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);

        System.out.println("Checking Fat objects from list:");
        fats.forEach(pool::checkIn);
        fats.forEach(pool::checkIn);

        executor.shutdownNow();

    }
}
