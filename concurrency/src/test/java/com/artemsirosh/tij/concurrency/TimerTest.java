package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class TimerTest {

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000})
    @DisplayName("Execution of task after delay")
    void exploreTimer(int timersNum) throws InterruptedException {
        final Timer timer = new Timer();
        final Random random = new Random(42);
        Stream.generate(() -> new SleeperTask(TimeUnit.MILLISECONDS, random.nextInt(100)))
                .map(SimpleTimerTask::new)
                .limit(timersNum)
                .forEach(task -> timer.schedule(task, random.nextInt(5000)));

        TimeUnit.SECONDS.sleep(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000})
    @DisplayName("Delayed execution of tasks by ScheduledExecutorService")
    void schedulerThreadPool(int taskNum) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        final Random random = new Random(42);
        Stream.generate(() -> new SleeperTask(TimeUnit.MILLISECONDS, random.nextInt(100)))
                .limit(taskNum)
                .forEach(task -> executor.schedule(task, random.nextInt(5), TimeUnit.SECONDS));

        TimeUnit.SECONDS.sleep(5);
        executor.shutdownNow();
    }

    private static class SimpleTimerTask extends TimerTask {

        private final SleeperTask sleeperTask;

        SimpleTimerTask(SleeperTask sleeperTask) {
            this.sleeperTask = sleeperTask;
        }

        @Override
        public void run() {
            sleeperTask.run();
        }
    }
}
