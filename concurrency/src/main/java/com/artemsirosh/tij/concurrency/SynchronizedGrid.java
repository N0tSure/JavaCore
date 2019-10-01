package com.artemsirosh.tij.concurrency;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

/**
 * Created at 01-10-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SynchronizedGrid<T> extends Grid<T> {

    private final ReentrantLock lock;

    SynchronizedGrid(Grid<T> grid) {
        super(grid);
        this.lock = new ReentrantLock();
    }

    @Override
    T getGene(int elementIndex, int geneIndex) {
        lock.lock();
        try {
            return super.getGene(elementIndex, geneIndex);
        } finally {
            lock.unlock();
        }
    }

    @Override
    void setGene(int elementIndex, int geneIndex, Function<T, T> mutator) {
        lock.lock();
        try {
            super.setGene(elementIndex, geneIndex, mutator);
        } finally {
            lock.unlock();
        }
    }
}
