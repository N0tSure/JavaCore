package com.artemsirosh.tij.concurrency.greenhouse;

import java.util.function.Function;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class WaterOffTask implements GreenhouseActionTask {

    private final Greenhouse greenhouse;

    public WaterOffTask(Greenhouse greenhouse) {
        this.greenhouse = greenhouse;
    }

    /**
     * Turning off water pulverization decreases humidity on 20% and increases
     * temperature on 10%.
     */
    @Override
    public void run() {
        greenhouse.getAndUpdateMeasurement(measurement -> updateMeasurement(
                temperature -> addPercentsToValue(temperature, DECIMAL_PART),
                humidity -> subtractPercentFromValue(humidity, FIFTH_PART),
                Function.identity(),
                measurement
        ));

        System.out.println("Water pulverization turned off.");
    }
}
