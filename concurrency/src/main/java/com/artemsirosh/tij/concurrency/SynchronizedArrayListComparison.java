package com.artemsirosh.tij.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created at 28-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SynchronizedArrayListComparison implements Runnable {

    private static final String DISPLAY_NAME = "Synchronized ArrayList";

    private final ContainerComparison<List<Integer>> comparison;

    SynchronizedArrayListComparison(
            int readersNumber, int writersNumber, Consumer<TestTask<?, ?>> taskExecutor
    ) {
        this.comparison = ListComparison.createContainerComparison(
                readersNumber, writersNumber, DISPLAY_NAME,
                SynchronizedArrayListComparison::newSynchronizedArrayList, taskExecutor
        );
    }

    @Override
    public void run() {
        comparison.runTest();
    }

    private static List<Integer> newSynchronizedArrayList(final int size) {
        return Collections.synchronizedList(new ArrayList<>(
                        IntStream.range(0, size)
                                .boxed()
                                .collect(Collectors.toList())
        ));
    }
}
