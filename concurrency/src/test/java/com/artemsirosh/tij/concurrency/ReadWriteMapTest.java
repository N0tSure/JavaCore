package com.artemsirosh.tij.concurrency;

import com.artemsirosh.tij.finisher.Finisher;
import com.artemsirosh.tij.finisher.Finishers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created at 04-10-2019
 *
 * @author Artem Sirosh 'Artem.Sirosh@t-systems.com'
 */
class ReadWriteMapTest {

    @Test
    void test() throws ExecutionException, InterruptedException {
        final Map<BigDecimal, BigDecimal> map = new ReadWriteMap<>(generateMap(), true);
        final Random random = new Random();
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Finisher<BigDecimal> finisher = Finishers.newSecondsTimeoutFinisher(30);
        final Future<BigDecimal> future = executor.submit(finisher);
        executor.execute(() -> writer(map, random));
        IntStream.range(0, 30).<Runnable>mapToObj(i -> (() -> reader(map, finisher))).forEach(executor::execute);

        final BigDecimal result = future.get();
        executor.shutdownNow();

        Assertions.assertNull(result);
    }

    private static Map<BigDecimal, BigDecimal> generateMap() {
        return IntStream.range(1, 101)
                .mapToObj(BigDecimal::new)
                .collect(Collectors.toMap(Function.identity(), Function.identity()));
    }

    private static BigDecimal estimationProcess(BigDecimal key, Random random) {
        final BigDecimal multiplicand = new BigDecimal(random.nextInt(10_000_000));
        return key.multiply(multiplicand);
    }

    private static void reader(Map<BigDecimal, BigDecimal> map, Finisher<BigDecimal> finisher) {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(1);
                for (int i = 1; i < map.size() + 1; i++) {
                    final BigDecimal key = new BigDecimal(i);
                    if (!validRelation(key, map.get(key))) {
                        finisher.shutdown(key);
                        return;
                    }
                }
            }
        } catch (InterruptedException exc) {
            // acceptable way to exit
        }
    }

    private static void writer(final Map<BigDecimal, BigDecimal> map, final Random random) {
        try {
            while (!Thread.interrupted()) {
                final BigDecimal key = new BigDecimal(1 + random.nextInt(map.size() + 1));
                map.put(key, estimationProcess(key, random));
                TimeUnit.MILLISECONDS.sleep(10);
            }
        } catch (InterruptedException exc) {
            // acceptable way to exit
        }
    }

    private static boolean validRelation(BigDecimal key, BigDecimal value) {
        final BigDecimal remainder = value.remainder(key);
        return BigDecimal.ZERO.compareTo(remainder) == 0;
    }
}
