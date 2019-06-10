package com.artemsirosh.tij.concurrency.pairs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created at 10-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class PairManagementTest {

    private static void testManagementApproaches(PairManager first, PairManager second) throws InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        Runnable
                firstManipulatorTask = first.pairManipulator(),
                secondManipulatorTask = second.pairManipulator();

        executor.execute(firstManipulatorTask);
        executor.execute(secondManipulatorTask);
        executor.execute(first.pairChecker());
        executor.execute(second.pairChecker());

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(first.getClass().getSimpleName() + " pairManipulator: " + firstManipulatorTask);
        System.out.println(second.getClass().getSimpleName() + " pairManipulator: " + secondManipulatorTask);

        executor.shutdownNow();
    }

    @Test
    @DisplayName("Compare synchronized method and critical section approaches")
    void synchronizedMethodVsCriticalSection() throws InterruptedException {
        testManagementApproaches(new SynchronizedMethodPairManager(), new CriticalSectionPairManager());
    }

    @Test
    @DisplayName("Compare synchronized method and reentrant lock approaches")
    void synchronizedMethodVsReentrantLock() throws InterruptedException {
        testManagementApproaches(new SynchronizedMethodPairManager(), new ReentrantLockPairManager());
    }
}
