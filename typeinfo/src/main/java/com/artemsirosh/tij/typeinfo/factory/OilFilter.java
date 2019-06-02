package com.artemsirosh.tij.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class OilFilter extends Filter {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<OilFilter> {
        @Override
        public OilFilter create() {
            return new OilFilter();
        }
    }
}
