package com.artemsirosh.tij.concurrency.detector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created at 18-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
@ExtendWith(MockitoExtension.class)
class DefaultSensorTest {

    @Test
    @DisplayName("Should give a signal to connected monitor")
    void shouldGiveASignalToConnectedMonitor(@Mock Monitor monitor) throws InterruptedException {
        final SensorDescriptor descriptor = new SensorDescriptor("Test");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        DefaultSensor sensor = new DefaultSensor(descriptor, 1000);
        sensor.connect(monitor);

        executor.execute(sensor);
        TimeUnit.MILLISECONDS.sleep(100);
        executor.shutdownNow();

        Mockito.verify(monitor).count(descriptor);
    }

    @Test
    @DisplayName("Should not give a signal to disconnected monitor")
    void shouldNotSignalMonitor(@Mock Monitor monitor) throws InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        DefaultSensor sensor = new DefaultSensor(new SensorDescriptor("Test"), 10);
        sensor.connect(monitor);
        sensor.disconnect(monitor);

        executorService.execute(sensor);
        TimeUnit.SECONDS.sleep(1);
        executorService.shutdownNow();

        Mockito.verify(monitor, Mockito.never()).count(Mockito.any());
    }
}
