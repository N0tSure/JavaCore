package ekkel.book.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
class Pug extends Dog {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Pug> {
        @Override
        public Pug create() {
            return new Pug();
        }
    }
    public Pug(String name) {
        super(name);
    }

    public Pug() {
        super();
    }
}
