package com.artemsirosh.tij.concurrency.garden;

/**
 * Created at 11-06-2019
 *
 * Entrance in ornamental garden. Manages value of turnstile turns. Can be
 * executed as task.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public interface Entrance extends Runnable {

    /**
     * Returns value of turnstile turns.
     * @return number of turns
     */
    int getValue();

}
