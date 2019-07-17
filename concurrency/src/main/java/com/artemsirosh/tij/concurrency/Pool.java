package com.artemsirosh.tij.concurrency;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class Pool<T> {

    private final List<T> items;
    private final boolean[] checkedOut;
    private final Semaphore semaphore;

    public Pool(List<T> items) {
        this.items = ImmutableList.copyOf(items);
        this.checkedOut = new boolean[items.size()];
        this.semaphore = new Semaphore(items.size(), true);
    }

    public T checkOut() throws InterruptedException {
        semaphore.acquire();
        return getItem();
    }

    public void checkIn(T item) {
        if (releaseItem(item))
            semaphore.release();
    }

    private synchronized T getItem() {
        for (int i = 0; i < checkedOut.length; i++) {
            if (!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }

        return null;
    }

    private synchronized boolean releaseItem(T item) {
        final int index = items.indexOf(item);
        if (index >= 0 && checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }

        return false;
    }
}
