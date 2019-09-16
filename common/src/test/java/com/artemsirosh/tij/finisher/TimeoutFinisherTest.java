package com.artemsirosh.tij.finisher;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created at 16-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class TimeoutFinisherTest {

    @Test
    public void shouldWaitTimeout() throws ExecutionException, InterruptedException {
        final long timeoutInMills = 500;
        final Finisher<Integer> finisher = new TimeoutFinisher<>(TimeUnit.MILLISECONDS, timeoutInMills);
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        final long startTime = System.currentTimeMillis();
        final Future<Integer> future = executorService.submit(finisher);
        future.get();
        final long delay = System.currentTimeMillis() - startTime;
        executorService.shutdownNow();

        System.out.println("Delay: " + delay + " mills.");
        Assert.assertTrue(delay >= timeoutInMills);
    }

    @Test
    public void shouldReturnProperValue() throws ExecutionException, InterruptedException {
        final Finisher<Integer> finisher = new TimeoutFinisher<>(TimeUnit.MILLISECONDS, 1);
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        finisher.setReturnValue(42);

        final Future<Integer> future = executorService.submit(finisher);
        final int result = future.get();
        executorService.shutdown();

        Assert.assertEquals(42, result);
    }

    @Test
    public void shouldProcessShutdownSignal() throws ExecutionException, InterruptedException {
        final Finisher<String> finisher = new TimeoutFinisher<>(TimeUnit.SECONDS, 10);
        final ExecutorService executorService = Executors.newCachedThreadPool();

        final Future<String> future = executorService.submit(finisher);

        final long startTime = System.currentTimeMillis();
        executorService.execute(() -> shutdownAffection(finisher, null));
        future.get();
        executorService.shutdownNow();

        final long duration = System.currentTimeMillis() - startTime;

        System.out.println("Waiting duration: " + duration + " mills.");
        Assert.assertTrue(duration >= 100);
        Assert.assertTrue(duration < 10_000);

    }

    @Test
    public void shouldReturnTheLastValueAfterShutdown() throws ExecutionException, InterruptedException {
        final Finisher<String> finisher = new TimeoutFinisher<>(TimeUnit.SECONDS, 10);
        final ExecutorService executorService = Executors.newCachedThreadPool();
        finisher.setReturnValue("foo");

        final Future<String> future = executorService.submit(finisher);
        executorService.execute(() -> shutdownAffection(finisher, "bar"));
        final String result = future.get();
        executorService.shutdownNow();

        Assert.assertEquals("bar", result);

    }

    private static void shutdownAffection(Finisher<String> finisher, String value) {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException exc) {
            // don't care
        } finally {
            if (value != null)
                finisher.shutdown(value);
            else
                finisher.shutdown();
        }
    }
}
