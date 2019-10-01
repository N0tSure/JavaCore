package com.artemsirosh.tij.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created at 29-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class AtomicEvolver implements Callable<Long> {

    private final Grid<AtomicInteger> grid;
    private final Random random;

    private long counter;

    private static void updateValue(AtomicInteger gene, int oldValue, int newValue) {
        if (!gene.compareAndSet(oldValue, newValue)) {
            System.out.println("Old value changed from: " + oldValue);
        }
    }

    AtomicEvolver(Grid<AtomicInteger> grid, Random random) {
        this.random = random;
        this.grid = grid;
    }

    @Override
    public Long call() {
        while (!Thread.interrupted()) {
            final int element = random.nextInt(grid.getElementsNumber());
            for (int i = 0; i < grid.getGenesNumbers(); i++) {
                final int previous = element - 1 < 0 ?  grid.getElementsNumber() - 1 : element - 1;
                final int next = element + 1 >= grid.getElementsNumber() ? 0 : element + 1;
                final int oldValue = grid.getGene(element, i).get();
                final int newValue = (oldValue + grid.getGene(previous, i).get() + grid.getGene(next, i).get()) / 3;
                grid.setGene(element, i, gene -> updateValue(gene, oldValue, newValue));

                counter++;
            }
        }

        return counter;
    }
}
