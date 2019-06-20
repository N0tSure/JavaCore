package com.artemsirosh.tij.concurrency.interrupting;

/**
 * Created at 20-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SynchronizedBlockedTask implements Runnable {

    SynchronizedBlockedTask() {
        new Thread(this::blocker).start();
    }

    @Override
    public void run() {
        System.out.println(this + ": trying to call blocked method.");
        blocker();
        System.out.println(this + ": stopping task.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    /**
     * Never release lock.
     */
    private synchronized void blocker() {
        while (true)
            Thread.yield();
    }
}
