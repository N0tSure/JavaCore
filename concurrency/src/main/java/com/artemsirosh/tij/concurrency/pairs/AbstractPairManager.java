package com.artemsirosh.tij.concurrency.pairs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created at 10-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
abstract class AbstractPairManager implements PairManager {

    private final AtomicInteger checkCounter = new AtomicInteger(0);
    private final List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

    Pair pair = new Pair(0, 0);

    @Override
    public synchronized Pair getPair() {
        return new Pair(pair);
    }

    @Override
    public Runnable pairManipulator() {
        return this.new PairManipulator();
    }

    @Override
    public Runnable pairChecker() {
        return this.new PairChecker();
    }

    void store(Pair pair) {
        storage.add(pair);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException exc) {
            System.err.println("Storing of pairs was interrupted.");
        }
    }

    private class PairManipulator implements Runnable {

        private final AbstractPairManager pairManager;

        private PairManipulator() {
            this.pairManager = AbstractPairManager.this;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted())
                pairManager.increment();
        }

        @Override
        public String toString() {
            return "Pair: " + pairManager.pair + ", checkCounter: " + pairManager.checkCounter.get();
        }
    }

    private class PairChecker implements Runnable {

        private final AbstractPairManager pairManager;

        private PairChecker() {
            this.pairManager = AbstractPairManager.this;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                pairManager.checkCounter.incrementAndGet();
                pairManager.getPair().checkState();
            }
        }
    }
}
