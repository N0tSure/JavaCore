package ekkel.book.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
class EgyptianMau extends Cat {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<EgyptianMau> {
        @Override
        public EgyptianMau create() {
            return new EgyptianMau();
        }
    }
    public EgyptianMau(String name) {
        super(name);
    }

    public EgyptianMau() {
        super();
    }
}
