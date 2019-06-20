package com.artemsirosh.tij.concurrency.interrupting;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created at 20-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class MutexBlockedTask implements Runnable {

    private final Lock lock = new ReentrantLock();

    MutexBlockedTask() {
        lock.lock();
    }

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
            System.out.println(this + ": lock acquired.");
        } catch (InterruptedException exc) {
            System.out.println(this + ": interrupted due lock acquisition.");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
