package com.artemsirosh.tij.concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created at 29-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class CopyOnWriteArrayListComparison implements Runnable {

    private static final String DISPLAY_NAME = "CopyOnWriteArrayList";

    private final ContainerComparison<List<Integer>> comparison;

    private static List<Integer> newCopyOnWriteArrayList(int size) {
        return IntStream.range(0, size).boxed().collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    CopyOnWriteArrayListComparison(
            int readersNumber, int writersNumber, Consumer<TestTask<?, ?>> taskExecutor
    ) {
        this.comparison = ListComparison.createContainerComparison(
                readersNumber, writersNumber, DISPLAY_NAME,
                CopyOnWriteArrayListComparison::newCopyOnWriteArrayList, taskExecutor
        );
    }

    @Override
    public void run() {
        comparison.runTest();
    }
}
