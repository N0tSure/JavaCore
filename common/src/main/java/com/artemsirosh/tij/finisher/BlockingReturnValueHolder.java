package com.artemsirosh.tij.finisher;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Semaphore;

/**
 * Created at 15-09-2019
 *
 * Initially {@link BlockingReturnValueHolder} contains no value, and itself {@link Semaphore}
 * locked for {@code 1} permit. As some thread set the value permit releases lock.
 * This component for a single use only.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class BlockingReturnValueHolder<T> {

    private T value;
    private final Semaphore semaphore;

    BlockingReturnValueHolder() {
        semaphore = new Semaphore(1);
        if (!semaphore.tryAcquire())
            throw new IllegalStateException("Something wrong with internal Semaphore.");
    }

    /**
     * Return value if it was already set, in other case wait for some thread
     * set value.
     * @return a value
     * @throws InterruptedException if waiting has been interrupted
     */
    @Nullable T getValue() throws InterruptedException {
        semaphore.acquire();
        synchronized (this) {
            return value;
        }
    }

    /**
     * Take value for return and unlocks semaphore.
     * @param t a value
     */
    void setValue(@Nullable T t) {
        synchronized (this) {
            this.value = t;
        }
        semaphore.release();
    }
}
