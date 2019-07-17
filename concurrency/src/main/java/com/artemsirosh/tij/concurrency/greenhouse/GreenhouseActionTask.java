package com.artemsirosh.tij.concurrency.greenhouse;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public interface GreenhouseActionTask extends Runnable {

    BigDecimal DECIMAL_PART = new BigDecimal(0.1);
    BigDecimal FIFTH_PART = new BigDecimal(0.2);

    /**
     * Add given percent of given value to this value and return result. Must
     * have no side effects. Percent should presented by {@link BigDecimal}
     * float point value, for instance: {@code 0.1} is {@code 10%}.
     *
     * @param value to which percent part will added
     * @param percent percent part of value
     * @return resulting float value
     */
    default Float addPercentsToValue(final Float value, final BigDecimal percent) {
        final BigDecimal decimalValue = new BigDecimal(value);
        return decimalValue.add(decimalValue.multiply(percent)).floatValue();
    }

    /**
     * Subtract from given value percent part. Must have no
     * side effects. Percent should presented by {@link BigDecimal} float point
     * value, for instance: {@code 0.1} is {@code 10%}.
     *
     * @param value from which percent part will subtracted
     * @param percent percent part of value
     * @return resulting float value
     */
    default Float subtractPercentFromValue(final Float value, final BigDecimal percent) {
        final BigDecimal decimalValue = new BigDecimal(value);
        return decimalValue.subtract(decimalValue.multiply(percent)).floatValue();
    }

    /**
     * Update given measurement with given functions.
     *
     * @param updateTemperature updates temperature
     * @param updateHumidity updates humidity
     * @param updateThermostat updates thermostat value
     * @param measurement last environment measurement
     * @return a new environment measurement
     */
    default Measurement updateMeasurement(
            Function<Float, Float> updateTemperature,
            Function<Float, Float> updateHumidity,
            Function<Measurement.Thermostat, Measurement.Thermostat> updateThermostat,
            Measurement measurement) {
        return new Measurement(
                updateTemperature.apply(measurement.getTemperature()),
                updateHumidity.apply(measurement.getHumidity()),
                updateThermostat.apply(measurement.getThermostat())
        );
    }

}
