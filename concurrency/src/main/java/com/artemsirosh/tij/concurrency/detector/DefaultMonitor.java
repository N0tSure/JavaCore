package com.artemsirosh.tij.concurrency.detector;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created at 18-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class DefaultMonitor implements Monitor, Runnable {

    private final Map<SensorDescriptor, Integer> sensorObservations;

    DefaultMonitor() {
        this.sensorObservations = new HashMap<>();
    }

    private static String cpsSummary(SensorDescriptor descriptor, int valuePerSecond) {
        return String.format("%s: %d", descriptor, valuePerSecond);
    }

    @Override
    public synchronized void count(SensorDescriptor descriptor) {
        int currentValue = sensorObservations.computeIfAbsent(descriptor, d -> 0);
        sensorObservations.put(descriptor, currentValue + 1);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                TimeUnit.SECONDS.sleep(1);

                synchronized (this) {
                    sensorObservations.entrySet().stream()
                            .map(entry -> cpsSummary(entry.getKey(), entry.getValue()))
                            .forEach(System.out::println);

                    System.out.println("========================\n");
                    sensorObservations.clear();
                }
            }
        } catch (InterruptedException exc) {
            System.out.println(this + " was interrupted due sleep.");
        }
    }
}
