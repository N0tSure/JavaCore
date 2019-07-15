package com.artemsirosh.tij.concurrency;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created at 15-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class PrioritizedTaskConsumer implements Runnable {

    private final PriorityBlockingQueue<PrioritizedTask> queue;

    public PrioritizedTaskConsumer(PriorityBlockingQueue<PrioritizedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted())
                queue.take().run();
        } catch (InterruptedException exc) {
            // legal task ending
        }

        System.out.println("PrioritizedTaskConsumer: stopping.");
    }
}
