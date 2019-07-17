package com.artemsirosh.tij.concurrency.greenhouse;

import com.artemsirosh.tij.concurrency.FinisherTask;

import java.util.List;
import java.util.function.Function;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public interface Greenhouse {

    /**
     * Get last measurement, update using given {@link Function} and saves new
     * {@link Measurement}. This method acquire monitor of current
     * {@link GreenhouseScheduler}, therefore given function or context where it
     * created must not use resource from another thread, which can try acquire
     * lock on this instance.
     *
     * @param measurementUpdateFunction it takes as argument last measurement and
     *                                  result of function will be saved as new
     *                                  last result.
     */
    void getAndUpdateMeasurement(Function<Measurement, Measurement> measurementUpdateFunction);

    /**
     * Return termination task which when executes send signal to given
     * {@link FinisherTask} and take results to it.
     * @param finisherTask tasks stopping hook
     * @return task which when executes stopping tasks
     */
    Runnable getTerminationTask(FinisherTask<List<Measurement>> finisherTask);

    /**
     * Schedule one-time execution of given task with given delay.
     * @param event scheduled task
     * @param delay delay of task execution
     */
    void schedule(Runnable event, long delay);
}
