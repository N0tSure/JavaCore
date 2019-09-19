package com.artemsirosh.tij.finisher;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * Created at 16-09-2019
 *
 * {@link Finisher} is a special task used for receiving shutdown signal.
 * Instance becoming ready to signal receiving only after {@link #call()} method
 * will be invoked, therefore it should be used as {@link Callable}.
 * Example:
 * <pre>
 *     final ExecutorService executor = Executors.newCachedThreadPool();
 *     final Finisher&lt;Integer&gt; finisher = ...
 *     // Finisher waiting for shutdown signal
 *     final Future&lt;Integer&gt; future = executor.submit(finisher);
 *     executor.execute(task1);
 *     executor.execute(task2);
 *
 *     // current thread wait until a finisher receives shutdown signal
 *     final int result = future.get();
 *
 *     // after shutdown signal received stopping all tasks
 *     executor.shutdownNow();
 * </pre>
 *
 * {@link java.util.concurrent.Future} provided by finisher return value that
 * finisher instance contain.
 *
 * Each {@link Finisher} instance is a single use component, for getting
 * instances use {@link Finishers}.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 * @see Finishers
 * @see Callable
 * @see java.util.concurrent.Future
 */
public interface Finisher<T> extends Callable<T> {

    /**
     * Allows set return value without finishing task. In case of return value
     * is {@code null}, it means that no return value has been set.
     * @param t finisher task return value
     */
    void setReturnValue(@NotNull T t);

    /**
     * Return value, which previously been set. Returning not causes thread
     * waiting or sleeping, i.e. value will be returning immediately.
     * @return value
     */
    @NotNull Optional<T> getReturnValue();

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
    void shutdown(@NotNull T t);
}
