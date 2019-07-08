package com.artemsirosh.tij.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created on 08 Jul, 2019.
 *
 * @author Artemis A. Sirosh
 */
class TaskPortion implements Runnable {

    private final int id;
    private final CountDownLatch latch;
    private final SleeperTask sleeperTask;

    TaskPortion(int id, Random random, CountDownLatch latch) {
        this.latch = latch;
        this.id = id;
        this.sleeperTask = new SleeperTask(TimeUnit.MILLISECONDS, random.nextInt(2000));
    }

    @Override
    public void run() {
        sleeperTask.run();
        latch.countDown();
        System.out.println(this + ": completed.");
    }

    @Override
    public String toString() {
        return "TaskPortion #" + id;
    }
}
