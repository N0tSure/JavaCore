package com.artemsirosh.tij.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class AtomicEvenGenerator extends EvenGenerator {

    private final AtomicInteger currentValue = new AtomicInteger(0);

    @Override
    public int next() {
        return currentValue.addAndGet(2);
    }
}
