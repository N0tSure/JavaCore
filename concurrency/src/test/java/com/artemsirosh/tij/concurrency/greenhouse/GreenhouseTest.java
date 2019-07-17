package com.artemsirosh.tij.concurrency.greenhouse;

import com.artemsirosh.tij.concurrency.DelayedTask;
import com.artemsirosh.tij.concurrency.FinisherTask;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class GreenhouseTest {

    @Test
    void schedulerTest() throws ExecutionException, InterruptedException {
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        final GreenhouseScheduler scheduler = new GreenhouseScheduler(executorService);
        final FinisherTask<List<Measurement>> finisher = new FinisherTask<>();
        final Future<List<Measurement>> resultFuture = executorService.submit(finisher);

        scheduler.getAndUpdateMeasurement(
                measurement -> new Measurement(24f, 75f, Measurement.Thermostat.NIGHT)
        );

        scheduler.schedule(scheduler.getTerminationTask(finisher), 5000);
        scheduler.repeat(new BellTask(), 0, 1000);
        scheduler.repeat(new ThermostatNightTask(scheduler), 0, 2000);
        scheduler.repeat(new LightOnTask(scheduler), 0, 350);
        scheduler.repeat(new LightOffTask(scheduler), 0, 500);
        scheduler.repeat(new WaterOnTask(scheduler), 0, 400);
        scheduler.repeat(new WaterOffTask(scheduler), 0, 600);
        scheduler.repeat(new ThermostatDayTask(scheduler), 0, 1000);


        List<Measurement> results = resultFuture.get();
        executorService.shutdownNow();

        System.out.println("Results:");
        results.forEach(System.out::println);
    }

    @Test
    void delayQueueTest() throws ExecutionException, InterruptedException {
        final DelayQueue<DelayedTask> queue = new DelayQueue<>();
        final GreenhouseDelayQueue greenhouseDelayQueue = new GreenhouseDelayQueue(queue);
        final GreenhouseTaskExecutor taskExecutor = new GreenhouseTaskExecutor(queue);
        final FinisherTask<List<Measurement>> finisherTask = new FinisherTask<>();
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Future<List<Measurement>> resultFuture = executor.submit(finisherTask);

        greenhouseDelayQueue.schedule(new LightOnTask(greenhouseDelayQueue), 0);
        greenhouseDelayQueue.schedule(new LightOnTask(greenhouseDelayQueue), 500);
        greenhouseDelayQueue.schedule(new LightOffTask(greenhouseDelayQueue), 200);
        greenhouseDelayQueue.schedule(new LightOffTask(greenhouseDelayQueue), 700);
        greenhouseDelayQueue.schedule(new WaterOnTask(greenhouseDelayQueue), 400);
        greenhouseDelayQueue.schedule(new WaterOnTask(greenhouseDelayQueue), 800);
        greenhouseDelayQueue.schedule(new WaterOffTask(greenhouseDelayQueue), 500);
        greenhouseDelayQueue.schedule(new WaterOffTask(greenhouseDelayQueue), 900);
        greenhouseDelayQueue.schedule(new ThermostatNightTask(greenhouseDelayQueue), 700);
        greenhouseDelayQueue.schedule(new ThermostatDayTask(greenhouseDelayQueue), 1000);
        greenhouseDelayQueue.schedule(new BellTask(), 500);
        greenhouseDelayQueue.schedule(new BellTask(), 1000);
        greenhouseDelayQueue.schedule(greenhouseDelayQueue.getTerminationTask(finisherTask), 1010);


        greenhouseDelayQueue.getAndUpdateMeasurement(
                measurement -> new Measurement(30f, 75f, Measurement.Thermostat.DAY)
        );

        executor.execute(taskExecutor);
        final List<Measurement> result = resultFuture.get();
        executor.shutdownNow();

        System.out.println("Results:");
        result.forEach(System.out::println);
    }
}
