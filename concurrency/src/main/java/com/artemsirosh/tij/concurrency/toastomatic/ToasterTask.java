package com.artemsirosh.tij.concurrency.toastomatic;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created at 01-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class ToasterTask implements Runnable {

    private final BlockingQueue<Toast> toastQueue;
    private final Random random;

    private int count;


    public ToasterTask(BlockingQueue<Toast> toastQueue) {
        this.toastQueue = toastQueue;
        this.random = new Random();
        this.count = 0;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                Toast toast = new Toast(count++);
                System.out.println(toast);

                toastQueue.put(toast);
            }
        } catch (InterruptedException exc) {
            System.out.println("ToasterTask: interrupted.");
        }
    }
}