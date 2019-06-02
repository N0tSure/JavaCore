package com.artemsirosh.tij.generics.factories;

import com.artemsirosh.tij.typeinfo.factory.Factory;

/**
 * Created by cresh on 01.12.16.
 */
class IntegerFactory implements Factory<Integer> {
    @Override
    public Integer create() {
        return new Integer(0);
    }
}
