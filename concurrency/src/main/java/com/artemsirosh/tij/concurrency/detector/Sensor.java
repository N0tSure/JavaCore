package com.artemsirosh.tij.concurrency.detector;

/**
 * Created at 18-06-2019
 *
 * Model of Geiger-Mueller (GM) detector. Could be connected to {@link Monitor}.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public interface Sensor {

    /**
     * Connect current sensor to {@link Monitor}.
     * @param monitor is tracking device which will connected.
     * @return descriptor of current sensor.
     */
    SensorDescriptor connect(Monitor monitor);

    /**
     * Disconnect {@link Monitor} from current sensor.
     * @param monitor which will be disconnected from sensor
     * @return descriptor of current sensor.
     */
    SensorDescriptor disconnect(Monitor monitor);
}
