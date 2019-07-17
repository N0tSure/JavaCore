package com.artemsirosh.tij.concurrency.greenhouse;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class ThermostatDayTask implements GreenhouseActionTask {

    private final Greenhouse greenhouse;

    public ThermostatDayTask(Greenhouse greenhouse) {
        this.greenhouse = greenhouse;
    }

    /**
     * Setting thermostat to day increases temperature on 10% and decreases
     * humidity on 10%.
     */
    @Override
    public void run() {
        greenhouse.getAndUpdateMeasurement(measurement -> updateMeasurement(
                temperature -> addPercentsToValue(temperature, DECIMAL_PART),
                humidity -> subtractPercentFromValue(humidity, DECIMAL_PART),
                thermostat -> Measurement.Thermostat.DAY,
                measurement
        ));

        System.out.println("Thermostat set to day mode.");
    }
}
