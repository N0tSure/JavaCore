package com.artemsirosh.tij.concurrency.greenhouse;

import com.artemsirosh.tij.concurrency.FinisherTask;
import com.google.common.collect.ImmutableList;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created at 16-07-2019
 *
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class GreenhouseScheduler {

    private final Deque<Measurement> measurements;
    private final ScheduledExecutorService scheduledExecutor;

    public GreenhouseScheduler(ScheduledExecutorService scheduledExecutor) {
        this.measurements = new LinkedList<>();
        this.scheduledExecutor = scheduledExecutor;
    }

    /**
     * Get last measurement, update using given {@link Function} and saves new
     * {@link Measurement}. This method acquire monitor of current
     * {@link GreenhouseScheduler}, therefore given function or context where it
     * created must not use resource from another thread, which can try acquire
     * lock on this instance.
     *
     * @param updateMeasurementFunction it takes as argument last measurement and
     *                                  result of function will be saved as new
     *                                  last result.
     */
    public void getAndUpdateMeasurement(Function<Measurement, Measurement> updateMeasurementFunction) {
        synchronized (this) {
            measurements.addLast(updateMeasurementFunction.apply(measurements.peekLast()));
        }
    }

    public Runnable getTerminationTask(FinisherTask<List<Measurement>> finisherTask) {
        return () -> finisherTask.setResult(ImmutableList.copyOf(measurements));
    }

    public void schedule(Runnable event, long delay) {
        scheduledExecutor.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initialDelay, long period) {
        scheduledExecutor.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
    }

}
