package com.artemsirosh.tij.concurrency;

import java.util.concurrent.Callable;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SerialNumberCheckingTask implements Callable<Integer> {

    private final SerialNumberGenerator generator;
    private final CircularSet set;

    SerialNumberCheckingTask(SerialNumberGenerator generator, CircularSet set) {
        this.generator = generator;
        this.set = set;
    }

    @Override
    public Integer call() {
        int value = generator.nextSerialNumber();
        while (!Thread.currentThread().isInterrupted() && !set.contains(value)) {
            set.add(value);
            value = generator.nextSerialNumber();
        }

        return set.contains(value) ? value : null;
    }

}
