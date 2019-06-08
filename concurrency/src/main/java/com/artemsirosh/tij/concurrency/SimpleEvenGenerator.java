package com.artemsirosh.tij.concurrency;

/**
 * Created at 08-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SimpleEvenGenerator extends EvenGenerator {

    private int currentValue = 0;

    @Override
    public int next() {
        ++currentValue;
        ++currentValue;
        return currentValue;
    }
}
