package com.artemsirosh.tij.concurrency.garden;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.*;

/**
 * Created at 11-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class OrnamentalGardenTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 10})
    void shouldCountTurns(int sleepTime) throws InterruptedException, ExecutionException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final OrnamentalGarden garden = new OrnamentalGarden(5);

        final Future<Integer> entrancesSumFuture = executor.submit(garden);
        garden.startEntrances(executor);

        TimeUnit.SECONDS.sleep(sleepTime);
        executor.shutdown();
        garden.cancel();

        final int entrancesSum = entrancesSumFuture.get();
        if (!executor.awaitTermination(250, TimeUnit.MILLISECONDS))
            System.out.println("Some task not terminated.");

        System.out.println("Total: " + garden.getTotalCount());
        System.out.println("Sum of Entrances: " + entrancesSum);
        Assertions.assertEquals(garden.getTotalCount(), entrancesSum);
    }
}
