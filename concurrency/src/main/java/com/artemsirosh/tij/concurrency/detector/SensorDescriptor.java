package com.artemsirosh.tij.concurrency.detector;

import java.util.Objects;

/**
 * Created at 18-06-2019
 *
 * Abstraction which need to describe particular {@link Sensor}. Could
 * potentially contain calibration values for sensor.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class SensorDescriptor {

    private final String id;

    public SensorDescriptor(String id) {
        this.id = Objects.requireNonNull(id, "Id must be not null");
    }

    String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDescriptor that = (SensorDescriptor) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Sensor: " + id;
    }
}
