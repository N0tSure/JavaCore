package com.artemsirosh.tij.concurrency;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created at 15-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class DelayedTask implements Runnable, Delayed {

    private final int id;
    private final int deltaInMills;
    private final long trigger;

    public DelayedTask(int id, int deltaInMills) {
        this.id = id;
        this.deltaInMills = deltaInMills;
        this.trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(deltaInMills, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
        final DelayedTask that = (DelayedTask) delayed;
        return Long.compare(this.trigger, that.trigger);
    }

    @Override
    public void run() {
        System.out.print(this + " ");
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public String toString() {
        return String.format("Task #%2$-2d [%1$-4d]", deltaInMills, id);
    }
}
