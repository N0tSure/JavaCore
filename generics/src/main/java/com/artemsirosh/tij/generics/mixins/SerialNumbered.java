package com.artemsirosh.tij.generics.mixins;

/**
 * Created by cresh on 17.12.16.
 */
class SerialNumbered implements Serial {
    private static long counter = 1;
    private final long serialNumber = counter++;

    @Override
    public long getSerialNumber() {
        return serialNumber;
    }
}
