package com.artemsirosh.tij.finisher;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created at 15-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class BlockingReturnValueHolderTest {

    @Test
    public void shouldWaitForValue() throws ExecutionException, InterruptedException {
        final BlockingReturnValueHolder<Integer> holder = new BlockingReturnValueHolder<>();
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Future<Integer> future = executorService.submit(holder::getValue);

        final long startTime = System.currentTimeMillis();
        executorService.execute(() -> getValueChanger(holder, 500));
        int result = future.get();
        final long endTime = System.currentTimeMillis();
        executorService.shutdownNow();

        Assert.assertTrue(endTime - startTime >= 500);
        Assert.assertEquals(42, result);

    }

    private static void getValueChanger(BlockingReturnValueHolder<Integer> holder, long waitTime) {
        try {
            TimeUnit.MILLISECONDS.sleep(waitTime);
        } catch (InterruptedException exc) {
            // exiting task anyway
        } finally {
            holder.setValue(42);
        }
    }
}
