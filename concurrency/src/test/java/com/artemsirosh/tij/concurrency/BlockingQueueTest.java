package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Created at 01-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class BlockingQueueTest {

    private static void test(
            Supplier<BlockingQueue<LiftOff>> queueSupplier, Supplier<SleeperTask> sleeperSupplier
    ) throws ExecutionException, InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final BlockingQueue<LiftOff> queue = queueSupplier.get();
        final LiftOffRunnerTask runner = new LiftOffRunnerTask(queue);
        final LiftOffProviderTask providerTask = new LiftOffProviderTask(queue);

        executor.execute(runner);
        executor.execute(providerTask);
        Future<?> future = executor.submit(sleeperSupplier.get());

        future.get();
        executor.shutdownNow();
    }

    @Test
    void boundedBlockingQueue() throws InterruptedException, ExecutionException {
        test(
                () -> new ArrayBlockingQueue<>(3),
                () -> new SleeperTask(TimeUnit.SECONDS, 2)
        );
    }

    @Test
    void unboundBlockingQueue() throws ExecutionException, InterruptedException {
        test(LinkedBlockingDeque::new, () -> new SleeperTask(TimeUnit.SECONDS, 2));
    }

    @Test
    void synchronousBlockingQueue() throws ExecutionException, InterruptedException {
        test(SynchronousQueue::new, () -> new SleeperTask(TimeUnit.SECONDS, 2));
    }

    private static class LiftOffProviderTask implements Runnable {

        private final BlockingQueue<LiftOff> queue;

        private LiftOffProviderTask(BlockingQueue<LiftOff> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    queue.put(new LiftOff());
                }
            } catch (InterruptedException exc) {
                System.out.println(getClass().getSimpleName() + ": interrupted.");
            }
        }
    }
}
