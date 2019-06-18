package com.artemsirosh.tij.concurrency.detector;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created at 18-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class DefaultSensor implements Sensor, Runnable {

    private final List<Monitor> connectedMonitors;
    private final SensorDescriptor descriptor;
    private final Random random;
    private final int detectingRateBound;

    DefaultSensor(SensorDescriptor descriptor) {
        this(descriptor, 1000);
    }

    DefaultSensor(SensorDescriptor descriptor, int detectingRateBound) {
        this.descriptor = descriptor;
        this.detectingRateBound = detectingRateBound;
        this.connectedMonitors = new CopyOnWriteArrayList<>();
        this.random = new Random(42);
    }

    @Override
    public SensorDescriptor connect(Monitor monitor) {
        connectedMonitors.add(monitor);
        return descriptor;
    }

    @Override
    public SensorDescriptor disconnect(Monitor monitor) {
        connectedMonitors.remove(monitor);
        return descriptor;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                connectedMonitors.forEach(monitor -> monitor.count(descriptor));

                TimeUnit.MILLISECONDS.sleep(random.nextInt(detectingRateBound));
            }

            System.out.println(this + ": stopped.");
        } catch (InterruptedException exc) {
            System.out.println(this + ": interrupted by exception.");
        }
    }

    @Override
    public String toString() {
        return descriptor.toString();
    }
}
