package ekkel.book.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
class Cymric extends Manx {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Cymric> {
        @Override
        public Cymric create() {
            return new Cymric();
        }
    }
    public Cymric(String name) {
        super(name);
    }

    public Cymric() {
        super();
    }
}
