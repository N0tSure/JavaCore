package com.artemsirosh.tij.finisher;

import java.util.Optional;

/**
 * Created at 18-09-2019
 *
 * This {@link Finisher} implementation allow control task execution using
 * {@code JMX} interface. Instance of this {@link Finisher} should be registered
 * as {@code MBean}'s using {@link javax.management.MBeanServerConnection}.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 * @see JMXFinisherMBean
 * @see javax.management.MBeanServerConnection
 * @see javax.management.MBeanServer
 */
class JMXFinisher<T> implements Finisher<T>, JMXFinisherMBean {

    private final BlockingReturnValueHolder<T> blockingHolder;
    private final ReturnValueHolder<T> valueHolder;
    private final String taskName;

    JMXFinisher(BlockingReturnValueHolder<T> blockingHolder, ReturnValueHolder<T> valueHolder, String taskName) {
        this.blockingHolder = blockingHolder;
        this.valueHolder = valueHolder;
        this.taskName = taskName;
    }

    @Override
    public void setReturnValue(T t) {
        valueHolder.setValue(t);
    }

    @Override
    public Optional<T> getReturnValue() {
        return valueHolder.getValue();
    }

    @Override
    public void shutdown() {
        blockingHolder.setValue(valueHolder.getValue().orElse(null));
    }

    @Override
    public void shutdown(T t) {
        blockingHolder.setValue(t);
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public void finishTask() {
        this.shutdown();
    }

    @Override
    public T call() throws Exception {
        return blockingHolder.getValue();
    }
}
