package com.artemsirosh.tij.concurrency.pairs;

/**
 * Created at 10-06-2019
 *
 * Implementation of {@link PairManager} that protect only critical operations.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class CriticalSectionPairManager extends AbstractPairManager {

    @Override
    public void increment() {

        final Pair temp;
        synchronized (this) {
            pair.incrementY();
            pair.incrementX();
            temp = getPair();
        }

        store(temp);
    }
}
