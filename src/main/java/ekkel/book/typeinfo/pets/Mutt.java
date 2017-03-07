package ekkel.book.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
class Mutt extends Dog {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Mutt> {
        @Override
        public Mutt create() {
            return new Mutt();
        }
    }
    public Mutt(String name) {
        super(name);
    }

    public Mutt() {
        super();
    }
}
