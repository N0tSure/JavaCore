package com.artemsirosh.tij.concurrency.interrupting;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created at 20-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class IOBlockedTask implements Runnable {

    private final InputStream inputStream;

    IOBlockedTask(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        System.out.println(this + ": waiting for read from IO.");
        try {
            inputStream.read();
        } catch (IOException exc) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(this + ": interrupted from blocked IO.");
            } else {
                throw new RuntimeException(exc);
            }
        }

        System.out.println(this + ": stopping task.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[blocked by " + inputStream.getClass().getSimpleName() + "]";
    }
}
