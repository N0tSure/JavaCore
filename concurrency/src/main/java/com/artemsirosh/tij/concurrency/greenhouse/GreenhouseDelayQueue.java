package com.artemsirosh.tij.concurrency.greenhouse;

import com.artemsirosh.tij.concurrency.FinisherTask;
import com.google.common.collect.ImmutableList;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.function.Function;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class GreenhouseDelayQueue implements Greenhouse {

    private final DelayQueue<com.artemsirosh.tij.concurrency.DelayedTask> queue;
    private final Deque<Measurement> measurements;

    public GreenhouseDelayQueue(DelayQueue<com.artemsirosh.tij.concurrency.DelayedTask> queue) {
        this.queue = queue;
        this.measurements = new LinkedList<>();
    }

    @Override
    public void getAndUpdateMeasurement(Function<Measurement, Measurement> measurementUpdateFunction) {
        synchronized (this) {
            measurements.add(measurementUpdateFunction.apply(measurements.peekLast()));
        }
    }

    @Override
    public Runnable getTerminationTask(FinisherTask<List<Measurement>> finisherTask) {
        return () -> finisherTask.setResult(ImmutableList.copyOf(measurements));
    }

    @Override
    public void schedule(Runnable event, long delay) {
        queue.add(new DelayedTask(delay, event));
    }

    private static class DelayedTask extends com.artemsirosh.tij.concurrency.DelayedTask {

        private final Runnable task;

        private DelayedTask(long delay, Runnable task) {
            super(0, (int) delay);
            this.task = task;
        }

        @Override
        public void run() {
            task.run();
        }
    }
}
