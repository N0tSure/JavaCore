package ekkel.book.generics.factories;

import ekkel.book.typeinfo.factory.Factory;

/**
 * Created by cresh on 01.12.16.
 */
class IntegerFactory implements Factory<Integer> {
    @Override
    public Integer create() {
        return new Integer(0);
    }
}
