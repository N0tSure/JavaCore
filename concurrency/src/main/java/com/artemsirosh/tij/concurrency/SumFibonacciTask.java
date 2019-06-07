package com.artemsirosh.tij.concurrency;

import com.artemsirosh.tij.generics.Fibonacci;

import java.util.concurrent.Callable;
import java.util.stream.LongStream;

/**
 * Created at 07-06-2019
 *
 * @author Artem Sirosh 'Artem.Sirosh@t-systems.com'
 */
class SumFibonacciTask extends Fibonacci implements Callable<Long> {

    private final int limit;

    private static long fibonacciByNumber(long number) {
        int result;
        if (number == 1) {
            result = 0;
        } else if (number == 2) {
            result = 1;
        } else {
            int tmp, previous = 0;
            int current = 1;
            for (int i = 0; i < number - 2; i++) {
                tmp = current;
                current += previous;
                previous = tmp;
            }

            result = current;
        }

        return result;
    }

    SumFibonacciTask(int limit) {
        this.limit = limit;
    }

    @Override
    public Long call() throws Exception {
        return LongStream.rangeClosed(1, limit)
                .map(SumFibonacciTask::fibonacciByNumber)
                .sum();
    }



}
