package com.artemsirosh.tij.concurrency;

import com.artemsirosh.tij.finisher.Finisher;
import com.artemsirosh.tij.finisher.Finishers;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * Created at 29-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class MapComparisonDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ExecutorService comparisonExecutor = Executors.newSingleThreadExecutor();
        final ExecutorService testTaskExecutor = Executors.newFixedThreadPool(10);
        final ExecutorService controlService = Executors.newSingleThreadExecutor();
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

        try {
            if (!comparisonExecutor.awaitTermination(1, TimeUnit.MINUTES)) {
                finisherFuture.get();
            }
        } finally {
            stopExecutors(comparisonExecutor, testTaskExecutor, controlService);
        }
    }

    private static void stopExecutors(ExecutorService... executors) {
        Arrays.stream(executors).forEach(ExecutorService::shutdownNow);
    }
}
