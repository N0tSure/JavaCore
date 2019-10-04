package com.artemsirosh.tij.concurrency;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created at 04-10-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class ReadWriteMapComparison implements Runnable {

    private final ContainerComparison<Map<Integer, Integer>> comparison;

    static Runnable createFair(
            int readersNumber, int writersNumber, Consumer<Runnable> taskExecutor
    ) {
        return new ReadWriteMapComparison(
                readersNumber, writersNumber, "Fair ReadWriteMap", true, taskExecutor
        );
    }

    static Runnable createUnFair(
            int readersNumber, int writersNumber, Consumer<Runnable> taskExecutor
    ) {
        return new ReadWriteMapComparison(
                readersNumber, writersNumber, "ReadWriteMap", false, taskExecutor
        );
    }

    private static Map<Integer, Integer> createReadWriteMap(int size, boolean fair) {
        return new ReadWriteMap<>(
                IntStream.range(0, size)
                        .boxed()
                        .collect(Collectors.toMap(Function.identity(), Function.identity())),
                fair
        );
    }

    private ReadWriteMapComparison(
            int readersNumber, int writersNumber, String displayName, boolean fair, Consumer<Runnable> taskExecutor
    ) {
        this.comparison = MapComparisons.createMapComparison(
                readersNumber, writersNumber, displayName,
                i -> createReadWriteMap(i, fair), taskExecutor
        );
    }

    @Override
    public void run() {
        comparison.runTest();
    }
}
