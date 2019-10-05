package com.artemsirosh.tij.concurrency;

import com.artemsirosh.tij.finisher.Finisher;
import com.artemsirosh.tij.finisher.Finishers;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * Created at 29-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class MapComparisonDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ExecutorService comparisonExecutor = Executors.newSingleThreadExecutor(
                new NamingThreadFactory("ComparisonRunner")
        );
        final ExecutorService testTaskExecutor = Executors.newFixedThreadPool(
                10, new NamingThreadFactory("TestRunner")
        );
        final ExecutorService controlService = Executors.newSingleThreadExecutor(
                new NamingThreadFactory("ShutdownController")
        );
        final Consumer<Runnable> testTaskExecutionConsumer = testTaskExecutor::execute;
        final Finisher<?> finisher = Finishers.newNetworkFinisher(4545);
        final Future<?> finisherFuture = controlService.submit(finisher);

        comparisonExecutor.execute(
                new SynchronizedHashMapComparison(10, 0, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                new SynchronizedHashMapComparison(9, 1, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                new SynchronizedHashMapComparison(5, 5, testTaskExecutionConsumer)
        );

        comparisonExecutor.execute(
                new ConcurrentHashMapComparison(10, 0, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                new ConcurrentHashMapComparison(9, 1, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                new ConcurrentHashMapComparison(5, 5, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                ReadWriteMapComparison.createUnFair(10, 0, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                ReadWriteMapComparison.createUnFair(9, 1, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                ReadWriteMapComparison.createUnFair(5, 5, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                ReadWriteMapComparison.createFair(10, 0, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                ReadWriteMapComparison.createFair(9, 1, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                ReadWriteMapComparison.createFair(5, 5, testTaskExecutionConsumer)
        );

        try {
            if (!comparisonExecutor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Wait for localhost:4545 signal.");
                finisherFuture.get();
            }
        } finally {
            stopExecutors(comparisonExecutor, testTaskExecutor, controlService);
        }
    }

    private static void stopExecutors(ExecutorService... executors) {
        Arrays.stream(executors).forEach(ExecutorService::shutdownNow);
    }

    private static class NamingThreadFactory implements ThreadFactory {
        private final String name;
        private final AtomicLong counter;

        private NamingThreadFactory(String name) {
            this.name = name;
            this.counter = new AtomicLong(0);
        }

        @Override
        public Thread newThread(Runnable r) {
            final Thread result = new Thread(r);
            result.setName(String.format("%s #%s", name, counter.incrementAndGet()));
            return result;
        }
    }
}
