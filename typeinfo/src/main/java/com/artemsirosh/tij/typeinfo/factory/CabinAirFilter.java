package com.artemsirosh.tij.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class CabinAirFilter extends Filter {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<CabinAirFilter> {
        @Override
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
    }
}
