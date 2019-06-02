package com.artemsirosh.tij.containers;

/**
 * Created by cresh on 11.03.17.
 */
class HashType extends SetType {
    public HashType(int i) {
        super(i);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(i);
    }
}
