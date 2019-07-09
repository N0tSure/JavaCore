package com.artemsirosh.tij.concurrency;

import com.artemsirosh.tij.concurrency.race.Horse;
import com.artemsirosh.tij.concurrency.race.HorseRace;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * Created at 09-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class CyclicBarrierTest {

    @Test
    void horseRaceDemo() throws ExecutionException, InterruptedException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final FinisherTask<Horse> finisherTask = new FinisherTask<>();
        final HorseRace race = new HorseRace(75, 7, finisherTask);

        Future<Horse> winnerFuture = executorService.submit(finisherTask);
        race.executeHorses(executorService);

        final Horse winner = winnerFuture.get();
        executorService.shutdownNow();

        System.out.println(winner + ": won!");
    }
}
