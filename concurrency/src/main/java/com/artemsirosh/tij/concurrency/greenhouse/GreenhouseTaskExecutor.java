package com.artemsirosh.tij.concurrency.greenhouse;

import com.artemsirosh.tij.concurrency.DelayedTask;

import java.util.concurrent.DelayQueue;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class GreenhouseTaskExecutor implements Runnable {

    private final DelayQueue<DelayedTask> queue;

    public GreenhouseTaskExecutor(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted())
                queue.take().run();
        } catch (InterruptedException exc) {
            // approach to stopping task
        }
    }
}
