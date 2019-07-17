package com.artemsirosh.tij.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class ExchangerConsumer<T> implements Runnable {

    private final Exchanger<List<T>> exchanger;

    private volatile T value;

    public ExchangerConsumer(Exchanger<List<T>> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final List<T> holder = exchanger.exchange(new ArrayList<>());
                value = holder.stream().findFirst().orElse(null);
            }
        } catch (InterruptedException exc) {
            // Acceptable way to exit
        }

        System.out.println("Final value: " + value);
    }


}
