package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created at 08-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class EvenGeneratorTest {

    private static void checkNumber(final IntGenerator generator) throws InterruptedException {
        checkNumber(generator, TimeUnit.SECONDS, 5, 5);
    }

    private static void checkNumber(
            final IntGenerator generator,
            final TimeUnit timeUnit,
            final int waitingTimeout,
            final int checkerCount
    ) throws InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        IntStream.range(0, checkerCount)
                .mapToObj(i -> new EvenCheckerTask(i, generator))
                .forEach(executor::execute);

        timeUnit.sleep(waitingTimeout);
        executor.shutdown();
    }

    @RepeatedTest(5)
    @DisplayName("Show how broken SimpleEvenGenerator")
    void simpleGeneratorTest() throws InterruptedException {
        checkNumber(new SimpleEvenGenerator());
    }

    @RepeatedTest(5)
    @DisplayName("Show SynchronizedEvenGenerator")
    void synchronizedGeneratorTest() throws InterruptedException {
        checkNumber(new SynchronizedEvenGenerator());
    }

    @RepeatedTest(5)
    @DisplayName("Show mutex EvenGenerator")
    void mutexGeneratorTest() throws InterruptedException {
        checkNumber(new MutexEvenGenerator());
    }

    @RepeatedTest(5)
    @DisplayName("Show atomic EvenGenerator")
    void atomicGeneratorTest() throws InterruptedException {
        checkNumber(new AtomicEvenGenerator());
    }
}
