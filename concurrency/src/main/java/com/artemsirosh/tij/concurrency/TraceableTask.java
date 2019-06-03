package com.artemsirosh.tij.concurrency;

import java.util.stream.IntStream;

/**
 * Created at 03-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class TraceableTask implements Runnable {

    private static int taskCount = 0;

    private final int id = taskCount++;

    public TraceableTask() {
        System.out.println(this + " constructed.");
    }

    @Override
    public void run() {
        IntStream.iterate(3, i -> i - 1).limit(3).forEach(i -> System.out.println(this + " says: " + i));
        System.out.println(this + " ended.");
    }

    @Override
    public String toString() {
        return String.format("Task#%d", id);
    }
}
