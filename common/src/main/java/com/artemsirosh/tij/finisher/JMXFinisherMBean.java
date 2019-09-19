package com.artemsirosh.tij.finisher;

import org.jetbrains.annotations.NotNull;

/**
 * Created at 18-09-2019
 *
 * This {@code MBean} interface allows to send a shutdown signal to task using
 * {@code JMX} user can distinguish tasks by name, each task name is name of
 * MBean instance.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public interface JMXFinisherMBean {

    /**
     * Name of task controlled by this MBean.
     * @return task name
     */
    @NotNull String getTaskName();

    /**
     * Sends shutdown signal to task.
     */
    void finishTask();
}
