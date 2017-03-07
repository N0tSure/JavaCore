package ekkel.book.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class AirFilter extends Filter {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<AirFilter> {
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }
}
