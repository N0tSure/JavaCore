package com.artemsirosh.tij.concurrency.greenhouse;

import com.artemsirosh.tij.concurrency.FinisherTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class GreenhouseActionTaskTest {

    private GreenhouseScheduler scheduler;
    private FinisherTask<List<Measurement>> finisherTask;

    @BeforeEach
    void setUp() {
        this.scheduler = new GreenhouseScheduler(null);
        this.scheduler.getAndUpdateMeasurement(
                measurement -> new Measurement(30, 60, Measurement.Thermostat.DAY)
        );
        this.finisherTask = new FinisherTask<>();
    }

    private static void assertMeasurementWithoutTimestamp(Measurement expected, Measurement actual, String message) {
        Assertions.assertAll(
                message,
                () -> assertEquals(expected.getTemperature(), actual.getTemperature(), "Temperature in C"),
                () -> assertEquals(expected.getHumidity(), actual.getHumidity(), "Humidity in %"),
                () -> assertEquals(expected.getThermostat(), actual.getThermostat())
        );
    }

    @Test
    void lightOffTask() throws Exception {
        final LightOffTask task = new LightOffTask(scheduler);
        task.run();

        assertMeasurementWithoutTimestamp(
                new Measurement(24f, 66f, Measurement.Thermostat.DAY),
                terminate().get(1),
                "Light off action"
        );
    }

    @Test
    void lightOnTaskTest() throws Exception {
        final LightOnTask task = new LightOnTask(scheduler);
        task.run();

        final List<Measurement> results = terminate();

        assertMeasurementWithoutTimestamp(
                new Measurement(36.0f, 54.0f, Measurement.Thermostat.DAY),
                results.get(1),
                "Light on action");
    }

    @Test
    void thermostatDayTaskTest() throws Exception {
        scheduler = new GreenhouseScheduler(null);
        scheduler.getAndUpdateMeasurement(
                measurement -> new Measurement(30, 60, Measurement.Thermostat.NIGHT)
        );

        final ThermostatDayTask task = new ThermostatDayTask(scheduler);
        task.run();

        assertMeasurementWithoutTimestamp(
                new Measurement(33f, 54f, Measurement.Thermostat.DAY),
                terminate().get(1),
                "Thermostat day action"
        );
    }

    @Test
    void thermostatNightTaskTest() throws Exception {
        final ThermostatNightTask task = new ThermostatNightTask(scheduler);
        task.run();

        assertMeasurementWithoutTimestamp(
                new Measurement(27f, 66f, Measurement.Thermostat.NIGHT),
                terminate().get(1),
                "Thermostat night action"
        );
    }

    @Test
    void waterOffTaskTest() throws Exception {
        final WaterOffTask task = new WaterOffTask(scheduler);
        task.run();

        assertMeasurementWithoutTimestamp(
                new Measurement(33f, 48f, Measurement.Thermostat.DAY),
                terminate().get(1),
                "Water off action"
        );
    }

    @Test
    void waterOnTaskTest() throws Exception {
        final WaterOnTask task = new WaterOnTask(scheduler);
        task.run();

        assertMeasurementWithoutTimestamp(
                new Measurement(27f, 72f, Measurement.Thermostat.DAY),
                terminate().get(1),
                "Water on action"
        );
    }

    List<Measurement> terminate() throws Exception {
        scheduler.getTerminationTask(finisherTask).run();
        return finisherTask.call();
    }
}
