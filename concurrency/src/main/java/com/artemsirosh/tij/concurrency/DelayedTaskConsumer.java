package com.artemsirosh.tij.concurrency;

import java.util.concurrent.DelayQueue;

/**
 * Created at 15-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class DelayedTaskConsumer implements Runnable {

    private final DelayQueue<DelayedTask> queue;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run();
            }
        } catch (InterruptedException exc) {
            System.out.println("DelayedTaskConsumer: interrupted.");
        }

        System.out.println("DelayedTaskConsumer: stopping.");
    }
}
