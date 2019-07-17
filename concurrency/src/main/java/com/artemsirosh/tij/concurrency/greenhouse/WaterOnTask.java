package com.artemsirosh.tij.concurrency.greenhouse;

import java.util.function.Function;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class WaterOnTask implements GreenhouseActionTask {

    private final GreenhouseScheduler scheduler;

    public WaterOnTask(GreenhouseScheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * Water pulverization turning on increases humidity on 20% and decreases temperature
     * on 10%.
     */
    @Override
    public void run() {
        scheduler.getAndUpdateMeasurement(measurement -> updateMeasurement(
                temperature -> subtractPercentFromValue(temperature, DECIMAL_PART),
                humidity -> addPercentsToValue(humidity, FIFTH_PART),
                Function.identity(),
                measurement
        ));

        System.out.println("Water pulverization turned on.");
    }
}
