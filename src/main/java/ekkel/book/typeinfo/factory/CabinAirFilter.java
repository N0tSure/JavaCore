package ekkel.book.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class CabinAirFilter extends Filter {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<CabinAirFilter> {
        @Override
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
    }
}
