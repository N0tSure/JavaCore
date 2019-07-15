package com.artemsirosh.tij.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created at 15-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {

    private final int id;
    private final int priority;

    PrioritizedTask(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    @Override
    public int compareTo(PrioritizedTask that) {
        return Integer.compare(this.priority, that.priority);
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep((int) Math.round(Math.random() * 250));
        } catch (InterruptedException exc) {
            // no - op: way to end task
        }

        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("Task #%2$d [%1$-3d]", priority, id);
    }
}
