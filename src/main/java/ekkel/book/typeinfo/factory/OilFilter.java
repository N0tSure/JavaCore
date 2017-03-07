package ekkel.book.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class OilFilter extends Filter {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<OilFilter> {
        @Override
        public OilFilter create() {
            return new OilFilter();
        }
    }
}
