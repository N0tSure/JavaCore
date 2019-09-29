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
public class ListComparisonDemo {

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService comparisonExecutor = Executors.newSingleThreadExecutor();
        final ExecutorService testTaskExecutor = Executors.newFixedThreadPool(10);
        final Consumer<TestTask<?, ?>> testTaskExecutionConsumer = testTaskExecutor::execute;

        comparisonExecutor.execute(
                new SynchronizedArrayListComparison(10, 0, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                new SynchronizedArrayListComparison(9, 1, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                new SynchronizedArrayListComparison(5, 5, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                new CopyOnWriteArrayListComparison(10, 0, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                new CopyOnWriteArrayListComparison(9, 1, testTaskExecutionConsumer)
        );
        comparisonExecutor.execute(
                new CopyOnWriteArrayListComparison(5, 5, testTaskExecutionConsumer)
        );


        comparisonExecutor.awaitTermination(1, TimeUnit.MINUTES);
        comparisonExecutor.shutdownNow();
        testTaskExecutor.shutdownNow();
    }
}
