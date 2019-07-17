package com.artemsirosh.tij.concurrency.greenhouse;

import java.util.function.Function;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class LightOffTask implements GreenhouseActionTask {

    private final Greenhouse greenhouse;

    public LightOffTask(Greenhouse greenhouse) {
        this.greenhouse = greenhouse;
    }

    /**
     * Turning off light action decreases temperature on 20% and increases
     * humidity on 10%.
     */
    @Override
    public void run() {
        greenhouse.getAndUpdateMeasurement(measurement -> updateMeasurement(
                temperature -> subtractPercentFromValue(temperature, FIFTH_PART),
                humidity -> addPercentsToValue(humidity, DECIMAL_PART),
                Function.identity(),
                measurement
        ));
        System.out.println("Light turned off.");
    }
}
