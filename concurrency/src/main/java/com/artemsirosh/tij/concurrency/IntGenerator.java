package com.artemsirosh.tij.concurrency;

/**
 * Created at 08-06-2019
 *
 * Generates a numbers (integers). Can be canceled.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public interface IntGenerator {

    /**
     * Generates next number.
     * @return a number
     */
    int next();

    /**
     * Cancels current generation process.
     */
    void cancel();

    /**
     * Returns state of this generator.
     * @return flag of generator's state
     */
    boolean isCanceled();
}
