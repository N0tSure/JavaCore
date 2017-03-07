package ekkel.book.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
class Manx extends Cat {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Manx> {
        @Override
        public Manx create() {
            return new Manx();
        }
    }
    public Manx(String name) {
        super(name);
    }

    public Manx() {
        super();
    }
}
