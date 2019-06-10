package com.artemsirosh.tij.concurrency.pairs;

/**
 * Created at 10-06-2019
 *
 * {@link PairManager} implementation, that uses synchronized method to control
 * inner state.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SynchronizedMethodPairManager extends AbstractPairManager {

    @Override
    public synchronized void increment() {
        pair.incrementX();
        pair.incrementY();

        store(pair);
    }
}
