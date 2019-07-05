package com.artemsirosh.tij.concurrency;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created at 06-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class FixedDinningPhilosophers {

    public static void main(String[] args) throws InterruptedException {
        final DinningPhilosophers.Parameters parameters = DinningPhilosophers.parseParameters(args);
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Chopstick[] chopsticks = Stream.generate(Chopstick::new)
                .limit(parameters.getSize())
                .toArray(Chopstick[]::new);

        final LocalTime startTime = LocalTime.now();
        System.out.println("Started at " + startTime + ".");

        for (int i = 0; i < parameters.getSize(); i++) {
            if (i < parameters.getSize() - 1)
                executor.execute(new Philosopher(chopsticks[i], chopsticks[i + 1], i, parameters.getPonderFactor()));
            else
                executor.execute(new Philosopher(chopsticks[0], chopsticks[i], i, parameters.getPonderFactor()));
        }

        if (parameters.isTimeout())
            TimeUnit.SECONDS.sleep(5);
        else
            DinningPhilosophers.listenSocketAndWait();

        executor.shutdownNow();

        final LocalTime endTime = LocalTime.now();
        System.out.println("Ended at " + endTime);
        System.out.println("Time elapsed: " + Duration.between(startTime, endTime));
    }
}
