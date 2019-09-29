package com.artemsirosh.tij.concurrency;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created at 29-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SynchronizedHashMapComparison implements Runnable {

    private final ContainerComparison<Map<Integer, Integer>> comparison;

    private static Map<Integer, Integer> createSynchronizedMap(int size) {
        return Collections.synchronizedMap(
                IntStream.range(0, size)
                        .boxed()
                        .collect(Collectors.toMap(Function.identity(), Function.identity()))
        );
    }

    SynchronizedHashMapComparison(int readersNumber, int writersNumber, Consumer<Runnable> taskExecutor) {
        this.comparison = MapComparisons.createMapComparison(
                readersNumber, writersNumber, "Synchronized HashMap",
                SynchronizedHashMapComparison::createSynchronizedMap, taskExecutor
        );
    }

    @Override
    public void run() {
        comparison.runTest();
    }
}
