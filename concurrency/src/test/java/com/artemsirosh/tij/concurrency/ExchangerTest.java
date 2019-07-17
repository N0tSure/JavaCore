package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ExchangerTest {

    @Test
    void producerConsumerExchangeTest() throws InterruptedException {
        final int size = 10;
        final int delay = 5;
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Exchanger<List<Fat>> exchanger = new Exchanger<>();
        final SerialNumberGenerator serialMumGen = new SerialNumberGenerator();

        executor.execute(new ExchangerProducer<>(() -> new Fat(serialMumGen.nextSerialNumber()), exchanger, size));
        executor.execute(new ExchangerConsumer<>(exchanger));

        TimeUnit.SECONDS.sleep(delay);
        executor.shutdownNow();
    }


}
