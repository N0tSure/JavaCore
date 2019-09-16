package com.artemsirosh.tij.finisher;

/**
 * Created at 16-09-2019
 *
 * This component holds thread in which current finisher processing and send
 * an interruption signal to that thread. Thread safe.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ProcessingThreadHolder {

    private Thread processingThread;

    /**
     * Holds current {@link Thread} and assign to field.
     */
    synchronized void holdCurrentThread() {
        processingThread = Thread.currentThread();
    }

    /**
     * Interrupt holding thread.
     * @throws IllegalStateException if holds no thread
     */
    synchronized void interruptHoldingThread() {
        if (processingThread == null)
            throw new IllegalStateException("The Holder holds no thread.");

        processingThread.interrupt();
    }
}
