package com.artemsirosh.tij.finisher;

import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * Created at 16-09-2019
 *
 * Represent a task that may be used as finisher task, can return value.
 *
 * @author Artem Sirosh 'Artem.Sirosh@t-systems.com'
 */
public interface Finisher<T> extends Callable<T> {

    /**
     * Allows set return value without finishing task. In case of return value
     * is {@code null}, it means that no return value has been set.
     * @param t finisher task return value
     */
    void setReturnValue(T t);

    /**
     * Return value, which previously been set.
     * @return value
     */
    Optional<T> getReturnValue();

    /**
     * Immediately interrupt finisher, finisher returns value that it already
     * have, if current instance has no value it returns {@code null}.
     */
    void shutdown();

    /**
     * Immediately interrupt finisher, finisher discard value, which it has and
     * use provided one.
     * @param t return value
     */
    void shutdown(T t);
}
