package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created at 15-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class PriorityBlockingQueueDemoTest {

    @Test
    void queueDemo() throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final PriorityBlockingQueue<PrioritizedTask> queue = new PriorityBlockingQueue<>();
        final FinisherTask<?> finisherTask = new FinisherTask<>();
        final Future<?> resultingFuture = executor.submit(finisherTask);
        final Future<List<PrioritizedTask>> originalTaskSequence =
                executor.submit(new PrioritizedTaskProducer(queue, finisherTask));
        executor.execute(new PrioritizedTaskConsumer(queue));

        resultingFuture.get();
        System.out.println("Original task sequence: " + originalTaskSequence.get());
        executor.shutdownNow();
    }
}
