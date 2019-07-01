package com.artemsirosh.tij.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created at 01-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class LiftOffRunnerTask implements Runnable {

    private final BlockingQueue<LiftOff> queue;

    LiftOffRunnerTask(BlockingQueue<LiftOff> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run();
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(this + ": interrupted due taking.");
        }

        System.out.println(this + ": stopping.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
