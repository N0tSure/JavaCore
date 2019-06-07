package com.artemsirosh.tij.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created at 07-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SleeperTask implements Runnable {

    private static int sleeperCount = 0;

    private final int id;
    private final TimeUnit timeUnit;
    private final long delay;

    SleeperTask(TimeUnit timeUnit, long delay) {
        this.id = sleeperCount++;
        this.timeUnit = timeUnit;
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            this.timeUnit.sleep(delay);
            System.out.println(this + " slept " + delay + " " + timeUnit.name());
        } catch (InterruptedException exc) {
            System.err.println("Sleeper interrupted: " + exc);
        }
    }

    @Override
    public String toString() {
        return "Sleeper #" + id;
    }
}
