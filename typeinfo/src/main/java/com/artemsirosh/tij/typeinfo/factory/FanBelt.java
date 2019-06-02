package com.artemsirosh.tij.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class FanBelt extends Belt {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<FanBelt> {
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}
