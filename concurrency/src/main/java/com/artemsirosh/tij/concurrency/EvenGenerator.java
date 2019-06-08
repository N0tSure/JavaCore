package com.artemsirosh.tij.concurrency;

/**
 * Created at 08-06-2019
 *
 * Generator which generates only even numbers.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
abstract class EvenGenerator implements IntGenerator {

    private volatile boolean canceled = false;

    @Override
    public void cancel() {
        this.canceled = true;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }
}
