package com.artemsirosh.tij.concurrency;

/**
 * Created on 17 May, 2020.
 *
 * @author Artemis A. Sirosh
 */
public class SynchronizedHeavyHolder extends SimpleHolder {

    @Override
    public synchronized Heavy getHeavy() {
        return super.getHeavy();
    }
}
