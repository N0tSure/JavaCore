package com.artemsirosh.tij.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class ExchangerProducer<T> implements Runnable {

    private final Supplier<T> itemSupplier;
    private final Exchanger<List<T>> exchanger;
    private final int size;

    private List<T> holder;

    public ExchangerProducer(Supplier<T> itemSupplier, Exchanger<List<T>> exchanger, int size) {
        this.itemSupplier = itemSupplier;
        this.exchanger = exchanger;
        this.size = size;
        this.holder = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                this.holder.addAll(
                        Stream.generate(itemSupplier)
                                .limit(size)
                                .collect(Collectors.toList())
                );

                this.holder = exchanger.exchange(this.holder);
            }
        } catch (InterruptedException exc) {
            // Acceptable way to exit
        }
    }
}
