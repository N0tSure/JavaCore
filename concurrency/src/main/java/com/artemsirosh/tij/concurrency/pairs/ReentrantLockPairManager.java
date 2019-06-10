package com.artemsirosh.tij.concurrency.pairs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created at 10-06-2019
 *
 * This implementation of {@link AbstractPairManager} using reentrant locks to
 * synchronize resource access. Note that {@link AbstractPairManager#getPair()}
 * was overrode, despite of it implemented. In parent class {@code getPair}
 * synchronized using {@code synchronized} keyword. That means {@code this}
 * instance using as monitor.
 *
 * In current implementation {@link #increment()} uses {@link Lock} object as
 * mutex. Therefore {@code increment} and {@code getPair} should use same mutex.
 *
 * If current implementation of {@link #getPair()} will be removed, {@link Pair}
 * will got inconsistent state very quickly.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 * @see AbstractPairManager
 * @see Pair
 */
class ReentrantLockPairManager extends AbstractPairManager {

    private final Lock lock = new ReentrantLock();

    @Override
    public void increment() {
        final Pair temp;
        lock.lock();
        try {
            pair.incrementX();
            pair.incrementY();
            temp = getPair();
        } finally {
            lock.unlock();
        }

        store(temp);
    }

    /**
     * Uses current mutex.
     *
     * {@inheritDoc}
     */
    @Override
    public Pair getPair() {
        lock.lock();
        try {
            return new Pair(pair);
        } finally {
            lock.unlock();
        }
    }
}
