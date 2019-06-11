package com.artemsirosh.tij.concurrency.garden;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created at 11-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class OrnamentalGardenTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 10})
    void shouldCountTurns(int sleepTime) throws InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final OrnamentalGarden garden = new OrnamentalGarden();
        IntStream.range(0, 5)
                .mapToObj(garden::newEntrance)
                .forEach(executor::execute);

        TimeUnit.SECONDS.sleep(sleepTime);
        garden.cancel();
        executor.shutdown();

        if (!executor.awaitTermination(250, TimeUnit.MILLISECONDS))
            System.out.println("Some task not terminated.");

        System.out.println("Total: " + garden.getTotalCount());
        System.out.println("Sun of Entrances: " + garden.sumEntrances());
    }
}
