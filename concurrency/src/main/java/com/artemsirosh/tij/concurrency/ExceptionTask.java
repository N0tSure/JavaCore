package com.artemsirosh.tij.concurrency;

/**
 * Created at 08-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ExceptionTask implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException("=(");
    }
}
