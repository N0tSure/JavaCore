package com.artemsirosh.tij.concurrency;

import java.util.function.*;

/**
 * Created at 29-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Grid<T> {

    private final int elementsNumber;
    private final int genesNumbers;
    private final T[][] gridStore;

    static <T> Grid<T> create(
            int elementsNumber, int genesNumbers,
            BiFunction<Integer, Integer, T[][]> gridSupplier, Supplier<T> geneSupplier
    ) {
        final T[][] store = gridSupplier.apply(elementsNumber, genesNumbers);
        for (int i = 0; i < elementsNumber; i++) {
            for (int j = 0; j < genesNumbers; j++) {
                store[i][j] = geneSupplier.get();
            }
        }

        return new Grid<>(elementsNumber, genesNumbers, store);
    }

    private Grid(int elementsNumber, int genesNumbers, T[][] gridStore) {
        this.elementsNumber = elementsNumber;
        this.genesNumbers = genesNumbers;
        this.gridStore = gridStore;
    }

    Grid(Grid<T> grid) {
        this(grid.elementsNumber, grid.genesNumbers, grid.gridStore);
    }

    int getElementsNumber() {
        return elementsNumber;
    }

    T getGene(int elementIndex, int geneIndex) {
        return gridStore[elementIndex][geneIndex];
    }

    int getGenesNumbers() {
        return genesNumbers;
    }

    void setGene(int elementIndex, int geneIndex, Function<T, T> mutator) {
        gridStore[elementIndex][geneIndex] = mutator.apply(getGene(elementIndex, geneIndex));
    }
}
