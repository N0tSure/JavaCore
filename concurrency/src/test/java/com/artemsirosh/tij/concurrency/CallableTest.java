package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Created at 07-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class CallableTest {

    static <T> T handleStringFuture(Future<T> future) {
        try {
            return future.get();
        } catch (InterruptedException exc) {
            throw new RuntimeException("Task was interrupted", exc);
        } catch (ExecutionException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Test
    void callableDemo() {
        final ExecutorService executor = Executors.newCachedThreadPool();
        IntStream.range(0, 10)
                .mapToObj(TaskWithResult::new)
                .map(executor::submit)
                .map(CallableTest::handleStringFuture)
                .forEach(System.out::println);
    }

    @Test
    void fibonacciSum() throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final SumFibonacciTask fibonacciTask = new SumFibonacciTask(executor);
        Future<Long> future = fibonacciTask.runTask(5);
        Assertions.assertEquals(new Long(7), future.get());
    }
}
