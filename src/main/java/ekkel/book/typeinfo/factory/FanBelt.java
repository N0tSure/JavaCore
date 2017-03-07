package ekkel.book.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class FanBelt extends Belt {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<FanBelt> {
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}
