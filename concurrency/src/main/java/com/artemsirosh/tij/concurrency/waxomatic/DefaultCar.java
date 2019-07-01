package com.artemsirosh.tij.concurrency.waxomatic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 22 Jun, 2019.
 *
 * @author Artemis A. Sirosh
 */
public class DefaultCar implements WaxedCar, BuffedCar {

    private final Lock lock;
    private final Condition condition;
    private boolean waxOn;

    DefaultCar() {
        this.waxOn = false;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    @Override
    public void waxed() {
        lock.lock();
        try {
            waxOn = true;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void buffed() {
        lock.lock();
        try {
            waxOn = false;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while (!waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public synchronized void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }

    }
}
