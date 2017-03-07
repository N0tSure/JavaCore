package ekkel.book.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
class Mouse extends Rodent {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Mouse> {
        @Override
        public Mouse create() {
            return new Mouse();
        }
    }
    public Mouse(String name) {
        super(name);
    }

    public Mouse() {
        super();
    }
}
