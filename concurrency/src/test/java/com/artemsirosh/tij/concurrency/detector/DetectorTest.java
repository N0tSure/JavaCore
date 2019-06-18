package com.artemsirosh.tij.concurrency.detector;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created at 18-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class DetectorTest {

    @Test
    void shouldDetectPhotons() throws InterruptedException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final DefaultMonitor monitor = new DefaultMonitor();
        final List<DefaultSensor> sensors = Stream.of(
                "East", "South-East", "North-East", "North-West", "South-West", "West")
                .map(SensorDescriptor::new)
                .map(d -> new DefaultSensor(d, 100))
                .collect(Collectors.toList());

        sensors.forEach(sensor -> sensor.connect(monitor));
        sensors.forEach(executorService::execute);

        executorService.execute(monitor);

        TimeUnit.SECONDS.sleep(10);
        executorService.shutdownNow();
    }
}
