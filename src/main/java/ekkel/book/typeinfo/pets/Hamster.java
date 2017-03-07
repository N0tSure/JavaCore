package ekkel.book.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
class Hamster extends Rodent {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Hamster> {
        @Override
        public Hamster create() {
            return new Hamster();
        }
    }
    public Hamster(String name) {
        super(name);
    }

    public Hamster() {
        super();
    }
}
