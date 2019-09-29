package com.artemsirosh.tij.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created at 29-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class MapComparisonDemo {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService comparisonExecutor = Executors.newSingleThreadExecutor();
        final ExecutorService testTaskExecutor = Executors.newFixedThreadPool(10);
        final Consumer<Runnable> testTaskExecutionConsumer = testTaskExecutor::execute;

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

        comparisonExecutor.awaitTermination(1, TimeUnit.MINUTES);
        comparisonExecutor.shutdownNow();
        testTaskExecutor.shutdownNow();
    }
}
