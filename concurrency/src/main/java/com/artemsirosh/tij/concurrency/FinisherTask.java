package com.artemsirosh.tij.concurrency;

import java.util.concurrent.Callable;

/**
 * Created at 09-07-2019
 *
 * Awaits for result.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class FinisherTask<T> implements Callable<T> {

    private T result;
    private volatile boolean finished;

    @Override
    public synchronized T call() throws Exception {

        while (!finished)
            wait();

        return result;
    }

    /**
     * Set result to local field and set current finisher to 'finished' state.
     * @param result instance
     */
    public synchronized void setResult(T result) {
        this.result = result;
        this.finished = true;
        notifyAll();
    }
}
