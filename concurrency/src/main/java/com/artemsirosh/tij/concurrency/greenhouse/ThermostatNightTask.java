package com.artemsirosh.tij.concurrency.greenhouse;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class ThermostatNightTask implements GreenhouseActionTask {

    private final GreenhouseScheduler scheduler;

    public ThermostatNightTask(GreenhouseScheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * Setting thermostat to night mode decreases temperature on 10% and
     * increases humidity on 10%.
     */
    @Override
    public void run() {
        scheduler.getAndUpdateMeasurement(measurement -> updateMeasurement(
                temperature -> subtractPercentFromValue(temperature, DECIMAL_PART),
                humidity -> addPercentsToValue(humidity, DECIMAL_PART),
                thermostat -> Measurement.Thermostat.NIGHT,
                measurement
        ));

        System.out.println("Thermostat set to night mode.");
    }
}
