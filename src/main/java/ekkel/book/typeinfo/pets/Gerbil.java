package ekkel.book.typeinfo.pets;

/**
 * Created by cresh on 10.08.16.
 */
class Gerbil extends Rodent {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Gerbil> {
        @Override
        public Gerbil create() {
            return new Gerbil();
        }
    }
    public Gerbil(String name) {
        super(name);
    }

    public Gerbil() {
        super();
    }
}
