package com.artemsirosh.tij.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class AirFilter extends Filter {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<AirFilter> {
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }
}
