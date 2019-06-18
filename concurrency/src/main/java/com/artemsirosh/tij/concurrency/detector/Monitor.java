package com.artemsirosh.tij.concurrency.detector;

/**
 * Created at 18-06-2019
 *
 * This device collect radiation data from {@link Sensor}s.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public interface Monitor {

    /**
     * Detects photon on given {@link Sensor}.
     * @param descriptor of device which detects photon.
     */
    void count(SensorDescriptor descriptor);

}
