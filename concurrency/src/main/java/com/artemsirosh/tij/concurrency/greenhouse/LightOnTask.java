package com.artemsirosh.tij.concurrency.greenhouse;

import java.util.function.Function;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class LightOnTask implements GreenhouseActionTask {

    private final Greenhouse greenhouse;

    public LightOnTask(Greenhouse greenhouse) {
        this.greenhouse = greenhouse;
    }

    /**
     * Turning on light increases temperature in 20% and decreases humidity in
     * 10%.
     */
    @Override
    public void run() {
        this.greenhouse.getAndUpdateMeasurement(measurement -> updateMeasurement(
                temperature -> addPercentsToValue(temperature, FIFTH_PART),
                humidity -> subtractPercentFromValue(humidity, DECIMAL_PART),
                Function.identity(),
                measurement

        ));
        System.out.println("Light turned on.");
    }
}
