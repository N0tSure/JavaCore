package com.artemsirosh.tij.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
class Rat extends Rodent {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<Rat> {
        @Override
        public Rat create() {
            return new Rat();
        }
    }
    public Rat(String name) {
        super(name);
    }

    public Rat() {
        super();
    }
}
