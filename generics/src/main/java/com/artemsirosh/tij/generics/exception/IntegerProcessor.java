package com.artemsirosh.tij.generics.exception;

import java.util.List;

/**
 * Created by cresh on 17.12.16.
 */
class IntegerProcessor implements Processor<Integer,SecondFailure> {
    private static int count = 2;
    @Override
    public void process(List<Integer> resultCollector) throws SecondFailure {
        if (count-- == 0) {
            resultCollector.add(42);
        } else {
            resultCollector.add(11);
        }
        if (count < 0)
            throw new SecondFailure();
    }
}
