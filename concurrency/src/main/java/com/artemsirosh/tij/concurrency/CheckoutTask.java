package com.artemsirosh.tij.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class CheckoutTask<T> implements Runnable {

    private final int id;
    private final Pool<T> pool;

    public CheckoutTask(int id, Pool<T> pool) {
        this.id = id;
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            final T item = pool.checkOut();
            System.out.println(this + ": checked out " + item);
            TimeUnit.SECONDS.sleep(1);

            System.out.println(this +": checking in " + item);
            pool.checkIn(item);
        } catch (InterruptedException exc) {
            // Acceptable way to terminate task
        }
    }

    @Override
    public String toString() {
        return "CheckoutTask #" + id;
    }
}
