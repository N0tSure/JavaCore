package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created at 15-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class DelayQueueDemoTest {

    private static DelayedTask mapIdToDelayedTask(int id) {
        return new DelayedTask(id, (int) Math.round(Math.random() * 5000));
    }

    @Test
    void queueDemo() throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final List<DelayedTask> tasks = IntStream.range(0, 20)
                .mapToObj(DelayQueueDemoTest::mapIdToDelayedTask)
                .collect(Collectors.toList());

        System.out.println("Tasks: " + tasks);
        final DelayQueue<DelayedTask> queue = new DelayQueue<>(tasks);
        final FinisherTask<?> finisher = new FinisherTask<>();
        final EndSentinel endSentinel = new EndSentinel(20, 5000, finisher);
        queue.add(endSentinel);

        executor.execute(new DelayedTaskConsumer(queue));
        Future<?> finishFuture = executor.submit(finisher);
        finishFuture.get();

        executor.shutdownNow();
    }
}
