package com.artemsirosh.tij.concurrency.toastomatic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created at 02-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ToastOMaticTest {

    @Test
    void jamPipeDemo() throws InterruptedException, ExecutionException {
        final BlockingQueue<Toast> dryQueue = new LinkedBlockingQueue<>(),
                butteredQueue = new LinkedBlockingQueue<>(),
                jammedQueue = new LinkedBlockingQueue<>();

        final ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new ToasterTask(dryQueue));
        executor.execute(new ButtererTask(dryQueue, butteredQueue));
        executor.execute(new JammerTask(butteredQueue, jammedQueue));
        Future<List<Toast>> future = executor.submit(new EaterTask(jammedQueue));

        TimeUnit.SECONDS.sleep(5);
        executor.shutdownNow();

        List<Toast> errors = future.get();
        System.out.println("Errors: " + errors);
        Assertions.assertTrue(errors.isEmpty());
    }

    @Test
    void jamAndJellyPipelineDemo() throws ExecutionException, InterruptedException {
        final BlockingQueue<Toast> dryQueue = new LinkedBlockingQueue<>(),
                butteredQueue = new LinkedBlockingQueue<>(),
                peanutButterQueue = new LinkedBlockingQueue<>(),
                jellyQueue = new LinkedBlockingQueue<>(),
                jammedQueue = new LinkedBlockingQueue<>();

        final ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new ToasterTask(dryQueue));
        executor.execute(new ButtererTask(dryQueue, butteredQueue));
        executor.execute(new PeanutButtererTask(dryQueue, peanutButterQueue));
        executor.execute(new JammerTask(butteredQueue, jammedQueue));
        executor.execute(new JellierTask(peanutButterQueue, jellyQueue));
        executor.execute(() -> mergingTask(jammedQueue, jellyQueue));
        final Future<List<Toast>> future = executor.submit(new EaterTask(jammedQueue));

        TimeUnit.SECONDS.sleep(5);
        executor.shutdownNow();

        List<Toast> errors = future.get();
        System.out.println("Errors: " + errors);
        Assertions.assertTrue(errors.isEmpty());
    }


    private static void mergingTask(BlockingQueue<Toast> mainQueue, BlockingQueue<Toast> branchQueue) {
        try {
            while (!Thread.interrupted()) {
                mainQueue.put(branchQueue.take());
            }
        } catch (InterruptedException exc) {
            System.out.println("MergeTask: interrupted.");
        }
    }
}
