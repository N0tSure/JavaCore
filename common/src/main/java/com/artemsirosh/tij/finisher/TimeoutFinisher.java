package com.artemsirosh.tij.finisher;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created at 15-09-2019
 *
 * Represent finisher task, resolves when time exceed.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class TimeoutFinisher<T> implements Finisher<T> {

    private final TimeUnit timeUnit;
    private final long time;
    private final ReturnValueHolder<T> holder;
    private final ProcessingThreadHolder processingThreadHolder;

    TimeoutFinisher(TimeUnit timeUnit, long time) {
        this.timeUnit = timeUnit;
        this.time = time;
        this.holder = new ReturnValueHolder<>();
        this.processingThreadHolder = new ProcessingThreadHolder();
    }

    @Override
    public void setReturnValue(@NotNull T t) {
        this.holder.setValue(t);
    }

    @Override
    public @NotNull Optional<T> getReturnValue() {
        return this.holder.getValue();
    }

    @Override
    public T call() {
        processingThreadHolder.holdCurrentThread();
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException exc) {
            // way to end a task.
        }

        return holder.getValue().orElse(null);
    }

    @Override
    public void shutdown() {
        processingThreadHolder.interruptHoldingThread();
    }

    @Override
    public void shutdown(@NotNull T t) {
        holder.setValue(t);
        processingThreadHolder.interruptHoldingThread();
    }
}
