package com.artemsirosh.tij.concurrency.greenhouse;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created at 16-07-2019
 *
 * Represents environment measurement. Contains temperature and humidity data,
 * thermostat state and measurement timestamp.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public final class Measurement {

    private final float temperature;
    private final float humidity;
    private final Thermostat thermostat;
    private final Instant timestamp;

    private Measurement(float temperature, float humidity, Thermostat thermostat, Instant timestamp) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.thermostat = thermostat;
        this.timestamp = timestamp;
    }

    public Measurement(float temperature, float humidity, Thermostat thermostat) {
        this(temperature, humidity, thermostat, Instant.now());
    }

    /**
     * Returns measured temperature in celsius.
     * @return temperature value
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * Returns measured humidity in percents.
     * @return humidity value
     */
    public float getHumidity() {
        return humidity;
    }

    /**
     * Returns thermostat state.
     * @return thermostat state value
     */
    public Thermostat getThermostat() {
        return thermostat;
    }

    /**
     * Returns timestamp of measurement.
     * @return timestamp
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Float.compare(that.temperature, temperature) == 0 &&
                Float.compare(that.humidity, humidity) == 0 &&
                thermostat == that.thermostat &&
                timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, humidity, thermostat, timestamp);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Measurement.class.getSimpleName() + "[", "]")
                .add("temperature=" + temperature)
                .add("humidity=" + humidity)
                .add("thermostat=" + thermostat)
                .add("timestamp=" + timestamp)
                .toString();
    }

    public enum Thermostat {
        DAY, NIGHT
    }
}
