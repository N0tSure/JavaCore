package com.artemsirosh.tij.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created at 01-10-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SimpleEvolver implements Callable<Long> {

    private final Grid<Integer> grid;
    private final Random random;

    private long counter;

    SimpleEvolver(Grid<Integer> grid, Random random) {
        this.grid = grid;
        this.random = random;
    }

    @Override
    public Long call() {
        while (!Thread.interrupted()) {
            final int element = random.nextInt(grid.getElementsNumber());
            for (int i = 0; i < grid.getGenesNumbers(); i++) {
                final int previous = element - 1 < 0 ?  grid.getElementsNumber() - 1 : element - 1;
                final int next = element + 1 >= grid.getElementsNumber() ? 0 : element + 1;
                final int oldValue = grid.getGene(element, i);
                final int newValue = (oldValue + grid.getGene(previous, i) + grid.getGene(next, i)) / 3;
                grid.setGene(element, i, store -> newValue);
            }

            counter++;
        }

        return counter;
    }
}
