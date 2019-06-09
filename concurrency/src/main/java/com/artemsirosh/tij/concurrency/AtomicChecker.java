package com.artemsirosh.tij.concurrency;

import java.util.concurrent.Callable;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class AtomicChecker implements Callable<Integer> {

    private final AtomicTask atomicTask;

    AtomicChecker(AtomicTask atomicTask) {
        this.atomicTask = atomicTask;
    }

    @Override
    public Integer call() {

        int value = 0;
        while (!Thread.currentThread().isInterrupted() && ((value & 1) == 0)) {
            value = atomicTask.getValue();
        }

        return value;
    }
}
