package com.artemsirosh.tij.concurrency.pairs;

/**
 * Created at 10-06-2019
 *
 * PairManager -- component that operates {@link Pair} in multi-thread
 * environment. Implementation should be thread safe.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */

public interface PairManager {

    /**
     * Returns a {@link Pair}.
     * @return {@link Pair} instance.
     */
    Pair getPair();

    /**
     * Increments {@link Pair} values.
     */
    void increment();

    /**
     * Returns {@link Pair} manipulator task, that bind to current
     * manager. This task increments pairs values.
     * @return a new pairs manipulator task instance.
     */
    Runnable pairManipulator();


    /**
     * Returns {@link Pair} checker that bind to current manager. This task
     * check state of pairs in given manager.
     * @return a new pairs checker task.
     */
    Runnable pairChecker();
}
