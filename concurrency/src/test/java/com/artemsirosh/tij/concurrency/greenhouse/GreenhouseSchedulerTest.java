package com.artemsirosh.tij.concurrency.greenhouse;

import com.artemsirosh.tij.concurrency.FinisherTask;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class GreenhouseSchedulerTest {

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
}
