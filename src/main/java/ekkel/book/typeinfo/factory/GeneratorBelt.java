package ekkel.book.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class GeneratorBelt extends Belt {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<GeneratorBelt> {
        @Override
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}
