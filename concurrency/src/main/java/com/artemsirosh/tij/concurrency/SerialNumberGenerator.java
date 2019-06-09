package com.artemsirosh.tij.concurrency;

/**
 * Created at 09-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class SerialNumberGenerator {

    private volatile int serialNumber = 0;

    synchronized int nextSerialNumber() {
        return serialNumber++;
    }
}
