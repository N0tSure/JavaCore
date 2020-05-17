package com.artemsirosh.tij.concurrency;

/**
 * Created on 16 May, 2020.
 *
 * @author Artemis A. Sirosh
 */
public class SimpleHolder implements HeavyHolder {

    private Heavy heavy;

    @Override
    public Heavy getHeavy() {
        if (heavy == null) {
            Thread.yield();
            heavy = new Heavy();
        }

        return heavy;
    }
}
