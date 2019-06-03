package com.artemsirosh.tij.concurrency;

import com.artemsirosh.tij.generics.Fibonacci;

import java.util.stream.IntStream;

/**
 * Created at 03-06-2019
 *
 * @author Artem Sirosh 'Artem.Sirosh@t-systems.com'
 */
class FibonacciTask extends Fibonacci implements Runnable {

    private final int limit;

    FibonacciTask(int limit) {
        this.limit = limit;
    }

    @Override
    public void run() {
        IntStream.generate(this::next).limit(limit).forEach(i -> System.out.print(i + ", "));
    }
}
